package com.ppc.odc.service;

import com.ppc.odc.data.model.*;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperatorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class OperationStepProcessorTest {

    @MockBean
    OperatorRepository operatorRepository;
    @MockBean
    OperationStepService operationStepService;
    @MockBean
    OperationService operationService;

    @Autowired
    OperationStepProcessor operationStepProcessor;

    @Test
    void OperationHasEmptyStepsCreatesNewStepWithInformationRequest() {
        OperationStepStatus active = OperationStepStatus.builder()
                .status(Status.ACTIVE)
                .build();
        OperationStepStatus end = OperationStepStatus.builder()
                .status(Status.END)
                .build();
        OperationCategory catA = OperationCategory.builder()
                .name("CAT A")
                .build();
        OperationCategory catB = OperationCategory.builder()
                .name("CAT B")
                .build();
        OperationCategory catC = OperationCategory.builder()
                .name("CAT C")
                .build();
        Operator operatorA = Operator.builder()
                .name("operator A")
                .operationCategory(catA)
                .build();
        Operator operatorBwithCatA = Operator.builder()
                .name("operator B")
                .operationCategory(catA)
                .build();
        Operator operatorC = Operator.builder()
                .name("operator C")
                .operationCategory(catC)
                .build();
        LocalDateTime startTime = LocalDateTime.now().minusHours(1);
        OperationStep operationStep = OperationStep.builder()
                .startTime(startTime)
                .operator(operatorA)
                .category(operatorA.getOperationCategory())
                .status(active)
                .build();
        Operation operation = Operation.builder()
                .steps(List.of(operationStep))
                .batchId("test")
                .build();

        LocalDateTime timeStamp = startTime.plusHours(2);

        operationStepProcessor.processOperationSteps(operation, operatorA, timeStamp);

        //todo call with open operation step, same operatorcategory, closes operationstep
        //Update operationStep is called


        //todo implement

    }


}