package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.OperationFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationFeedbackRepository extends JpaRepository<OperationFeedback, Long> {
}
