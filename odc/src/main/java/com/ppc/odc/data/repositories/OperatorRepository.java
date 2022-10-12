package com.ppc.odc.data.repositories;

import com.ppc.odc.data.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    @Query("select distinct o.name from Operator o order by o.name ASC ")
    List<String> getAllOperatorNames();

}
