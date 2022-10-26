package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.Operator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationStepProcessor {

    private final OperationStepService operationStepService;
    private final OperationService operationService;


    public void processOperationSteps(Operation operation,
                                      Operator operator,
                                      LocalDateTime timeStamp) {
        OperationCategory collectedCategory = operator.getOperationCategory();
        Optional<OperationStep> optionalLatestStep = getLatestOperationStepOf(operation);


        if (needOfNewOperationStep(optionalLatestStep, collectedCategory)) {
            OperationStep newStep = operationStepService.createNewOperationStep(operator, timeStamp);
            log.info("newStep created");
            operationService.addNewStepToOperation(operation, newStep);
        }

        if (needOfStepUpdate(optionalLatestStep)) {
            operationStepService.endOperationStep(optionalLatestStep.get(), timeStamp);
        }

    }
    private boolean needOfStepUpdate(Optional<OperationStep> optionalLatestStep) {
        return optionalLatestStep.isPresent() &&
                optionalLatestStep.get().getStopTime() == null;
    }

    private boolean needOfNewOperationStep(Optional<OperationStep> optionalLatestStep,
                                           OperationCategory collectedCategory) {
        return optionalLatestStep.isEmpty() ||
                optionalLatestStep.get().getStopTime() != null ||
                !optionalLatestStep.get().getCategory().equals(collectedCategory);
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
