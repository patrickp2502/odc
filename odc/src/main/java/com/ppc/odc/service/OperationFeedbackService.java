package com.ppc.odc.service;

import com.ppc.odc.data.model.OperationFeedback;
import com.ppc.odc.data.repositories.OperationFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationFeedbackService {

    private final OperationFeedbackRepository operationFeedbackRepository;

    public List<OperationFeedback> getAllOperationFeedbacks() {
        return operationFeedbackRepository.findAll();
    }





}
