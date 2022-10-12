package com.ppc.odc.mapstruct.mappers;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.mapstruct.dtos.OperationGetDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperationMapperTest {

    OperationMapper mapper;

    @BeforeAll
    void init() {
        mapper = new OperationMapperImpl();
    }

    @Test
    void testOperationGetDTOtoOperation() {
        LocalDateTime end = LocalDateTime.now();
        int id = 1;
        LocalDateTime start = end.minusHours(id);
        String batch = "batch";

        Status status = Status.ACTIVE;
        Operation operation = Operation.builder()
                .id(id)
                .stopTime(end)
                .startTime(start)
                .batchId(batch)
                .status(OperationStatus.builder()
                        .status(status)
                        .build())
                .build();

        OperationGetDTO expectedDto = new OperationGetDTO(
                id,
                batch,
                start,
                end,
                status);

        OperationGetDTO resultDto = mapper.operationToOperationGetDTO(operation);

        Assertions.assertEquals(expectedDto, resultDto);

    }
}