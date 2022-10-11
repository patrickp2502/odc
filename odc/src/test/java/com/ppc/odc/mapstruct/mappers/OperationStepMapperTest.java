package com.ppc.odc.mapstruct.mappers;

import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.OperationStepStatus;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.mapstruct.dtos.OperationStepGetDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperationStepMapperTest {
    OperationStepMapper mapper;

    @BeforeAll
    void init() {
        mapper = new OperationStepMapperImpl();
    }

    @Test
    void mapOperationStepToOperationStepGetDTO() {
        LocalDateTime stopTime = LocalDateTime.now();
        LocalDateTime startTime = stopTime.minusHours(1);
        OperationStepStatus status = OperationStepStatus.builder()
                .status(Status.ACTIVE)
                .build();

        OperationCategory category = OperationCategory.builder()
                .name("test")
                .shortName("test")
                .build();
        Operator operator = Operator.builder()
                .name("test")
                .operationCategory(category)
                .build();

        OperationStep step = OperationStep.builder()
                .id(1)
                .startTime(startTime)
                .stopTime(stopTime)
                .category(category)
                .operator(operator)
                .status(status)
                .build();

        OperationStepGetDTO expectedDTO = new OperationStepGetDTO(
                step.getId(),
                category.getName(),
                step.getStartTime(),
                step.getStopTime(),
                operator.getName(),
                status.getStatus()
        );

        OperationStepGetDTO resultDTO = mapper.operationStepToOperationStepGetDTO(step);

        System.out.println("expectedDTO = " + expectedDTO.toString());
        System.out.println("resultDTO = " + resultDTO.toString());
        Assertions.assertEquals(resultDTO, expectedDTO);
    }

    @Test
    void operationStepGetDTOtoOperationStep() {
    }
}