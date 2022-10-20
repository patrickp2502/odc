package com.ppc.odc.service;

import com.ppc.odc.data.model.*;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperatorRepository;
import com.ppc.odc.mapstruct.dtos.AddOperationInformationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationStepProcessor {

    private final OperatorRepository operatorRepository;
    private final OperationStepService operationStepService;
    private final OperationService operationService;


    public void updateOperationWithStepInformation(long operationId,
                                                   AddOperationInformationRequest informationRequest) {
        String operatorName = informationRequest.operatorName();
        Operator operator = operatorRepository.findByName(operatorName)
                .orElseThrow(() -> new EntityNotFoundException("no operator with name: " + operatorName));
        Operation operation = operationService.getOperationBy(operationId);
        LocalDateTime timeStamp = informationRequest.timeStamp();
        OperationCategory category = operator.getOperationCategory();
        Optional<OperationStep> optionalLatestStep = getLatestOperationStepOf(operation);
//todo rework needOfNewOperationStep -> also need if category is not the same -> close last, open new one or update

        if (needOfNewOperationStep(operation, optionalLatestStep)) {
            createNewOperationStep(operationId, operator, timeStamp, category);
            return;
        }

        OperationStep latestStep = optionalLatestStep.get();
        OperationCategory currentCategory = latestStep.getCategory();


    }

    private void createNewOperationStep(long operationId, Operator operator, LocalDateTime timeStamp, OperationCategory category) {
        OperationStepStatus operationStepStatus = operationStepService.getOperationStepStatusBy(Status.ACTIVE);
        OperationStep newStep = OperationStep.builder()
                .startTime(timeStamp)
                .operator(operator)
                .category(category)
                .status(operationStepStatus)
                .build();
        operationService.addNewStepToOperation(operationId, newStep);
    }

    private boolean needOfNewOperationStep(Operation operation, Optional<OperationStep> optionalLatestStep) {
        return optionalLatestStep.isEmpty() ||
                getLatestOperationStepOf(operation).get().getStopTime() != null;
    }


    private Optional<OperationStep> getLatestOperationStepOf(Operation operation) {
        List<OperationStep> steps = operation.getSteps();
        if (steps.isEmpty()) {
            return Optional.empty();
        }
        int lastIndex = steps.size() - 1;
        OperationStep latestStep = operation.getSteps().get(lastIndex);
        return Optional.of(latestStep);
    }


}
