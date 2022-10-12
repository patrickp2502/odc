package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    //Streaming just for fun - trying something new
    Stream<Operation> streamByStatus(OperationStatus operationStatus);

    @Query("SELECT DISTINCT o.batchId from Operation o where o.status = :status  order by o.batchId ASC ")
    List<String> getAllOperationIDsByStatus(@Param("status") OperationStatus operationStatus);


}


