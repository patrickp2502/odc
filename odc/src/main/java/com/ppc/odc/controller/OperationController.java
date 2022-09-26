package com.ppc.odc.controller;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/operations")
@CrossOrigin(origins = "http://localhost:3000")
public class OperationController {

    private final OperationService operationService;


    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAll();
    }

    @GetMapping("/{id}")
    public Operation getOperation(@PathVariable long id) {
        return operationService.getOperationBy(id);
    }

    @GetMapping("/{id}/steps")
    public List<OperationStep> getOperationSteps(@PathVariable long id) {
        return operationService.getOperationSteps(id);
    }


    //fixme really a need for operationId?
    @GetMapping("/{operationId}/steps/{stepId}")
    public OperationStep getOperationStepBy(@PathVariable("stepId") long stepId) {
        return operationService.getOperationStepBy(stepId);
    }



}
