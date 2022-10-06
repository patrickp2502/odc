package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStatusRepository extends JpaRepository<OperationStatus, Long> {

    Optional<OperationStatus> findByStatus(Status status);


}
