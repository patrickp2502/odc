package com.ppc.odc.controller;

import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.service.OperationStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/steps")
public class OperationStepController {

    private final OperationStepService operationStepService;

    @GetMapping("/{id}")
    public OperationStep getOperationStep(@PathVariable long id) {
        return operationStepService.getOperationStepBy(id);
    }


}
