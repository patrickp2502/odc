package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OperationStepProcessorTest {

    @Mock
    private OperationStepService operationStepService;
    @Mock
    private OperationService operationService;
    private OperationStepProcessor operationStepProcessor;
    private OperationCategory catA;
    private OperationCategory catC;
    private Operator operatorA;
    private Operator operatorC;
    private LocalDateTime startTime;
    private LocalDateTime timeStamp;


    @BeforeEach
    void init() {
        operationStepProcessor = new OperationStepProcessor(
                operationStepService,
                operationService);
    }

    @BeforeEach
    void initTestData() {

        catA = OperationCategory.builder()
                .name("CAT A")
                .build();

        catC = OperationCategory.builder()
                .name("CAT C")
                .build();
        operatorA = Operator.builder()
                .name("operator A")
                .operationCategory(catA)
                .build();
        operatorC = Operator.builder()
                .name("operator C")
                .operationCategory(catC)
                .build();
        startTime = LocalDateTime.now().minusHours(1);
        timeStamp = startTime.plusHours(2);

    }

    @Test
    void operationWithActiveStepEndsByOperatorWithSameCategory() {
        OperationStep operationStep = OperationStep.builder()
                .id(1)
                .startTime(startTime)
                .operator(operatorA)
                .category(operatorA.getOperationCategory())
                .build();
        Operation operation = Operation.builder()
                .steps(List.of(operationStep))
                .batchId("test")
                .build();


        //process
        operationStepProcessor.processOperationSteps(operation, operatorA, timeStamp);

        //assert
        verify(operationStepService)
                .endOperationStep(operationStep, timeStamp);
    }

    @Test
    void OperationHasEmptyStepsCreatesNewStep() {
        Operation operation = Operation.builder()
                .batchId("test")
                .steps(Collections.emptyList())
                .build();

        //process
        operationStepProcessor.processOperationSteps(operation, operatorA, timeStamp);

        //assert
        verify(operationStepService).createNewOperationStep(operatorA, timeStamp);


    }

    @Test
    void operationWithActiveStepClosesStepAndCreatesNewOneWhenCalledByDifferentCategory() {
        OperationStep activeStep = OperationStep.builder()
                .id(1)
                .operator(operatorA)
                .category(operatorA.getOperationCategory())
                .startTime(startTime)
                .build();

        Operation operation = Operation.builder()
                .batchId("test")
                .steps(List.of(activeStep))
                .build();


        //process
        operationStepProcessor.processOperationSteps(operation, operatorC, timeStamp);

        //assert
        verify(operationStepService).createNewOperationStep(operatorC, timeStamp);
        verify(operationStepService).endOperationStep(activeStep, timeStamp);


    }


}