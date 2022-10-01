package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.repositories.OperationRepository;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationStatusRepository operationStatusRepository;

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    public Operation getOperationBy(long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("operation not found by id = " + id));
    }

    public List<OperationStep> getOperationSteps(long id) {
        Operation operation = getOperationBy(id);
        return operation.getSteps();
    }

    public List<OperationStatus> getStatuses() {
        return operationStatusRepository.findAll();
    }



}
