package com.ppc.odc.controller;

import com.ppc.odc.data.model.Operation;
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
    public Operation getOperation(@RequestParam long operationId) {


        return null;
    }


}
