package com.ppc.odc.service;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStatus;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperationRepository;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import com.ppc.odc.data.repositories.OperationStepRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import com.ppc.odc.mapstruct.dtos.InformationDTO;
import com.ppc.odc.mapstruct.dtos.OperationGetDTO;
import com.ppc.odc.mapstruct.dtos.OperationStepGetDTO;
import com.ppc.odc.mapstruct.mappers.OperationMapper;
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
    private final OperatorRepository operatorRepository;
    private final OperationStepRepository operationStepRepository;

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }


    public List<OperationGetDTO> getAllOperationDTOs() {
        return getAll().stream()
                .map(operationMapper::operationToOperationGetDTO)
                .collect(Collectors.toList());
    }


    /**
     *
     * @param id
     * @return
     * @throws EntityNotFoundException handled by RestResponseEntityExceptionHandler
     */
    public Operation getOperationBy(long id) throws EntityNotFoundException {
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

    public void addNewStepToOperation(long operationId, OperationStep newStep) {
        Operation operation = getOperationBy(operationId);
        OperationStep operationStep = operationStepRepository.save(newStep);
        operation.getSteps().add(operationStep);
        operationRepository.save(operation);

    }


    public List<OperationStatus> getStatuses() {
        return operationStatusRepository.findAll();
    }

    public InformationDTO getInformation() {
        List<String> operators = operatorRepository.getAllOperatorNames();
        OperationStatus status = operationStatusRepository.findByStatus(Status.ACTIVE)
                .orElseThrow(() -> new EntityNotFoundException("status not found!"));
        List<String> activeOperationIDs = operationRepository.getAllOperationIDsByStatus(status);
        return new InformationDTO(operators, activeOperationIDs);
    }






}
