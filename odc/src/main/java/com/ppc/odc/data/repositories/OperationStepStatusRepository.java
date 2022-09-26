package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.OperationStepStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationStepStatusRepository extends JpaRepository<OperationStepStatus, Long> {
}
