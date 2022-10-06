package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.OperationStepStatus;
import com.ppc.odc.data.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStepStatusRepository extends JpaRepository<OperationStepStatus, Long> {

    Optional<OperationStepStatus> findByStatus(Status status);
}
