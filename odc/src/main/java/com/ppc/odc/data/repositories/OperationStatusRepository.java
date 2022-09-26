package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.OperationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationStatusRepository extends JpaRepository<OperationStatus, Long> {
}
