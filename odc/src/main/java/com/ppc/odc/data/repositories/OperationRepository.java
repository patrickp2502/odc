package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
