package com.ppc.odc.service;

import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.repositories.OperationStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class OperationStepService {

    private final OperationStepRepository operationStepRepository;

    public OperationStep getOperationStepBy(long id) {
        return operationStepRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OperationStep not found by id = " + id));
    }

    public void updateOperationStep(long id, OperationStep operationStep) {
        if (!operationStepRepository.existsById(id)) {
            throw new EntityNotFoundException("OperationStep not found by id = " + id);
        }
        operationStep.setId(id);
        operationStepRepository.save(operationStep);
    }

    public OperationStep createOperationStep(OperationStep newOperationStep) {
        return operationStepRepository.save(newOperationStep);
    }
}
