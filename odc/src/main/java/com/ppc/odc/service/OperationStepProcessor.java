package com.ppc.odc.service;

import com.ppc.odc.data.model.*;
import com.ppc.odc.data.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationStepProcessor {

    private final OperationStepService operationStepService;
    private final OperationService operationService;


    public void processOperationSteps(Operation operation,
                                      Operator operator,
                                      LocalDateTime timeStamp) {
        OperationCategory category = operator.getOperationCategory();
        Optional<OperationStep> optionalLatestStep = getLatestOperationStepOf(operation);
//todo rework needOfNewOperationStep -> also need if category is not the same -> close last, open new one or update
        if (needOfNewOperationStep(operation, optionalLatestStep)) {
            OperationStep newStep = createNewOperationStep(operator, timeStamp, category);
            operationService.addNewStepToOperation(operation, newStep);
            return;
        }
        OperationStep latestStep = optionalLatestStep.get();
        OperationCategory currentCategory = latestStep.getCategory();
    }

    private OperationStep createNewOperationStep(Operator operator, LocalDateTime timeStamp, OperationCategory category) {
        OperationStepStatus operationStepStatus = operationStepService.getOperationStepStatusBy(Status.ACTIVE);
        return OperationStep.builder()
                .startTime(timeStamp)
                .operator(operator)
                .category(category)
                .status(operationStepStatus)
                .build();
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
