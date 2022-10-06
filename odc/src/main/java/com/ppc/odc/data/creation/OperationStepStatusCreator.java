package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.OperationStepStatus;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperationStepStatusRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class OperationStepStatusCreator {

    private final OperationStepStatusRepository operationStepStatusRepository;

    public void initialize() {
        List<OperationStepStatus> statusList = List.of(
                OperationStepStatus.builder().status(Status.ACTIVE).build(),
                OperationStepStatus.builder().status(Status.CLOSE).build(),
                OperationStepStatus.builder().status(Status.OPEN).build(),
                OperationStepStatus.builder().status(Status.END).build()
        );

        operationStepStatusRepository.saveAll(statusList);
    }
}
