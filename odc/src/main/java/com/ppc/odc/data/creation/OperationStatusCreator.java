package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OperationStatusCreator {
    private final OperationStatusRepository operationStatusRepository;

    public void initialize() {
        List<OperationStatus> statusList = List.of(
                OperationStatus.builder().status(Status.OPEN).build(),
                OperationStatus.builder().status(Status.ACTIVE).build(),
                OperationStatus.builder().status(Status.END).build(),
                OperationStatus.builder().status(Status.CLOSE).build()
        );

        operationStatusRepository.saveAll(statusList);
    }


}
