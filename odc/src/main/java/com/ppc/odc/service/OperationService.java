package com.ppc.odc.service;

import com.ppc.odc.data.model.*;
import com.ppc.odc.data.model.enums.Status;
import com.ppc.odc.data.repositories.OperationRepository;
import com.ppc.odc.data.repositories.OperationStatusRepository;
import com.ppc.odc.data.repositories.OperationStepRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import com.ppc.odc.mapstruct.dtos.AddOperationInformationRequest;
import com.ppc.odc.mapstruct.dtos.InformationDTO;
import com.ppc.odc.mapstruct.dtos.OperationGetDTO;
import com.ppc.odc.mapstruct.dtos.OperationStepGetDTO;
import com.ppc.odc.mapstruct.mappers.OperationMapper;
import com.ppc.odc.mapstruct.mappers.OperationStepMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationStatusRepository operationStatusRepository;
    private final OperationMapper operationMapper;
    private final OperationStepMapper operationStepMapper;
    private final OperatorRepository operatorRepository;
    private final OperationStepService operationStepService;
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

    public void addNewStepToOperation(Operation operation, OperationStep newStep) {
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

    /**
     * validates the
     */
    public void processStepInformationRequest(long operationId, AddOperationInformationRequest request) {
        Operation operation = getOperationBy(operationId);
        String operatorName = request.operatorName();
        Operator operator = operatorRepository.findByName(operatorName)
                .orElseThrow(() -> new EntityNotFoundException("Operator not found by name "+ operatorName));
        processOperationSteps(operation, operator, request.timeStamp());
    }

    public void processOperationSteps(Operation operation,
                                      Operator operator,
                                      LocalDateTime timeStamp) {
        log.info("processing steps");
        OperationCategory collectedCategory = operator.getOperationCategory();
        Optional<OperationStep> optionalLatestStep = getLatestOperationStepOf(operation);


        if (needOfNewOperationStep(optionalLatestStep, collectedCategory)) {
            OperationStep newStep = operationStepService.createNewOperationStep(operator, timeStamp);
            log.info("newStep created");
            addNewStepToOperation(operation, newStep);
        }

        if (needOfStepUpdate(optionalLatestStep)) {
            log.info("update step infromations");
            operationStepService.endOperationStep(optionalLatestStep.get(), timeStamp);
        }

    }
    private boolean needOfStepUpdate(Optional<OperationStep> optionalLatestStep) {
        return optionalLatestStep.isPresent() &&
                optionalLatestStep.get().getStopTime() == null;
    }

    private boolean needOfNewOperationStep(Optional<OperationStep> optionalLatestStep,
                                           OperationCategory collectedCategory) {
        return optionalLatestStep.isEmpty() ||
                optionalLatestStep.get().getStopTime() != null ||
                !optionalLatestStep.get().getCategory().equals(collectedCategory);
    }

    private Optional<OperationStep> getLatestOperationStepOf(Operation operation) {
        List<OperationStep> steps = operation.getSteps();
        if (steps.isEmpty()) {
            return Optional.empty();
        }
        int lastIndex = steps.size() - 1;
        OperationStep latestStep = operation.getSteps().get(lastIndex);
        return Optional.of(latestStep);
    }





}
