package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    public Operation getOperationBy(long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("operation not found by id = " + id));
    }


}
