package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.repositories.OperationRepository;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import com.ppc.odc.data.repositories.OperationStepRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import com.ppc.odc.mapstruct.mappers.OperationMapper;
import com.ppc.odc.mapstruct.mappers.OperationStepMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    @Mock
    private OperationStepService operationStepService;
    @Mock
    private OperationRepository operationRepository;
    @Mock
    private OperationStatusRepository operationStatusRepository;
    @Mock
    private OperationMapper operationMapper;
    @Mock
    private OperationStepMapper operationStepMapper;
    @Mock
    private OperatorRepository operatorRepository;
    @Mock
    private OperationStepRepository operationStepRepository;
    private OperationService operationService;
    private OperationCategory catA;
    private OperationCategory catC;
    private Operator operatorA;
    private Operator operatorC;
    private LocalDateTime startTime;
    private LocalDateTime timeStamp;


    @BeforeEach
    void init() {
        operationService = new OperationService(
                 operationRepository,
                 operationStatusRepository,
                 operationMapper,
                 operationStepMapper,
                 operatorRepository,
                 operationStepService,
                 operationStepRepository);
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
                .steps(new ArrayList<>(List.of(operationStep)))
                .batchId("test")
                .build();


        //process
        operationService.processOperationSteps(operation, operatorA, timeStamp);

        //assert
        verify(operationStepService)
                .endOperationStep(operationStep, timeStamp);
    }

    @Test
    void OperationHasEmptyStepsCreatesNewStep() {
        Operation operation = Operation.builder()
                .batchId("test")
                .steps(new ArrayList<>())
                .build();

        //process
        operationService.processOperationSteps(operation, operatorA, timeStamp);

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
                .steps(new ArrayList<>(List.of(activeStep)))
                .build();


        //process
        operationService.processOperationSteps(operation, operatorC, timeStamp);

        //assert
        verify(operationStepService).createNewOperationStep(operatorC, timeStamp);
        verify(operationStepService).endOperationStep(activeStep, timeStamp);


    }


}