package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.*;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class OperationCreator implements DataCreator {
    private static final int MAX_STEP_DURATION = 8;
    private static final int MIN_STEP_DURATION = 1;
    private static final int MIN_OPERATION_STEP_COUNT = 1;
    private static final int MAX_OPERATION_STEP_COUNT = 3;
    private static final int OPERATION_COUNT = 1000;
    private static final int DATA_TIME_SPAN_HOURS = 1000;
    private final OperationRepository operationRepository;
    private final OperationStatusRepository operationStatusRepository;
    private final OperationStepRepository operationStepRepository;
    private final OperatorRepository operatorRepository;
    private final OperationStepStatusRepository operationStepStatusRepository;
    private final OperationCategoryRepository operationCategoryRepository;

    @Override
    public void initialize() {
        OperationResource resource = new OperationResource(
                operatorRepository.findAll(),
                operationCategoryRepository.findAll(),
                operationStatusRepository.findAll(),
                operationStepStatusRepository.findAll()
        );

        for (int i = 0; i < OPERATION_COUNT; i++) {
            createOperation(resource);
        }


    }

    private void createOperation(OperationResource resource) {
        LocalDateTime startTime = getRandomStartTime();
        List<OperationStep> steps = createChainedSteps(resource, startTime);
        String batchNumber = UUID.randomUUID().toString().substring(0,7);
        OperationStatus active = operationStatusRepository.findByStatus(Status.ACTIVE).orElseThrow();
        OperationStatus closed = operationStatusRepository.findByStatus(Status.CLOSE).orElseThrow();
        OperationStep lastStep = steps.get(steps.size() - 1);
        LocalDateTime endTime = lastStep.getStopTime();
        OperationStatus status = endTime == null ? active : closed;
        Operation operation = Operation.builder()
                .status(status)
                .batchId(batchNumber)
                .steps(steps)
                .startTime(startTime)
                .stopTime(endTime)
                .build();
        operationRepository.save(operation);
    }

    private List<OperationStep> createChainedSteps(OperationResource resource,
                                                   LocalDateTime initialTime) {
        List<OperationStep> chainedSteps = new ArrayList<>();
        LocalDateTime startTime = initialTime;
        int stepCount = new Random().nextInt(MAX_OPERATION_STEP_COUNT);
        stepCount = Math.max(stepCount, MIN_OPERATION_STEP_COUNT);
        OperationStepStatus active = operationStepStatusRepository.findByStatus(Status.ACTIVE).orElseThrow();
        OperationStepStatus closed = operationStepStatusRepository.findByStatus(Status.END).orElseThrow();
        List<OperationStepStatus> statusList = List.of(active, closed);
        for (int stepNumber = 0; stepNumber < stepCount; stepNumber++) {
            OperationStepStatus stepStatus = stepNumber < stepCount -1 ?
                    closed : statusList.get(new Random().nextInt(statusList.size()));
            OperationStep operationStep = createOperationStep(startTime, stepStatus, resource);
            chainedSteps.add(operationStep);
            startTime = operationStep.getStopTime();
        }
        return operationStepRepository.saveAll(chainedSteps);
    }

    private OperationStep createOperationStep(LocalDateTime startTime,
                                              OperationStepStatus status,
                                              OperationResource resource) {
        int operatorIndex = new Random().nextInt(resource.operators.size());
        int duration = new Random().nextInt(MAX_STEP_DURATION - MIN_STEP_DURATION) + MIN_STEP_DURATION;
        LocalDateTime stopTime = status.getStatus().equals(Status.END) ?
                startTime.plusHours(duration) :
                null;
        Operator operator = resource.operators.get(operatorIndex);
        return OperationStep.builder()
                .operator(operator)
                .category(operator.getOperationCategory())
                .status(status)
                .startTime(startTime)
                .stopTime(stopTime)
                .build();
    }

    private LocalDateTime getRandomStartTime() {
        int hours = new Random().nextInt(DATA_TIME_SPAN_HOURS);
        return LocalDateTime.now().minusHours(hours);
    }

     private record OperationResource(List<Operator> operators,
                                      List<OperationCategory> operationCategories,
                                      List<OperationStatus> operationStatuses,
                                      List<OperationStepStatus> operationStepStatuses) {}
}
