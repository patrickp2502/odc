package com.ppc.odc;

import com.ppc.odc.data.model.OperationFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/operations")
public class OperationController {

    private final OperationFeedbackRepository operationFeedbackRepository;


    @GetMapping
    public List<OperationFeedback> getAllOperationFeedback() {



        return

    }


}
