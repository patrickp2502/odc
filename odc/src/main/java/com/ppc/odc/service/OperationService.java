package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.repositories.OperationRepository;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import com.ppc.odc.mapstruct.dtos.OperationGetDTO;
import com.ppc.odc.mapstruct.dtos.OperationStepGetDTO;
import com.ppc.odc.mapstruct.mappers.OperationMapper;
import com.ppc.odc.mapstruct.mappers.OperationMapperImpl;
import com.ppc.odc.mapstruct.mappers.OperationStepMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationStatusRepository operationStatusRepository;
    private final OperationMapper operationMapper;
    private final OperationStepMapper operationStepMapper;

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }


    public List<OperationGetDTO> getAllOperationDTOs() {
        return getAll().stream()
                .map(operationMapper::operationToOperationGetDTO)
                .collect(Collectors.toList());
    }


    public Operation getOperationBy(long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("operation not found by id = " + id));
    }

    public List<OperationStep> getOperationSteps(long id) {
        Operation operation = getOperationBy(id);
        return operation.getSteps();
    }

    public List<OperationStepGetDTO> getOperationStepsDTOs(long id) {
        return getOperationSteps(id).stream()
                .map(operationStepMapper::operationStepToOperationStepGetDTO)
                .collect(Collectors.toList());
    }


    public List<OperationStatus> getStatuses() {
        return operationStatusRepository.findAll();
    }



}
