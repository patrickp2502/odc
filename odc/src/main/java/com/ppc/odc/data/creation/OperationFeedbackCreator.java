package com.ppc.odc.data.creation;


import com.ppc.odc.data.model.OperationFeedback;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.repositories.OperationFeedbackRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.ppc.odc.data.creation.DataCreatorConfiguration.*;
@DependsOn("operatorCreator")
@Component
public class OperationFeedbackCreator {

    private final OperationFeedbackRepository operationFeedbackRepository;
    private final OperatorRepository operatorRepository;

    public OperationFeedbackCreator(OperationFeedbackRepository operationFeedbackRepository,
                                    OperatorRepository operatorRepository) {
        this.operationFeedbackRepository = operationFeedbackRepository;
        this.operatorRepository = operatorRepository;
        initialize();
    }

    private void initialize() {
        List<Operator> operators = operatorRepository.findAll();
        for (int i = 0; i < FEEDBACK_COUNT; i++) {
            operationFeedbackRepository.save(createOperationFeedback(
                    (i < FEEDBACK_COUNT * FULLFILLED_FEEDBACK_FACTOR),
                    operators.get(new Random().nextInt(operators.size()))));

        }
        System.out.println(operationFeedbackRepository.findAll());

    }

    private OperationFeedback createOperationFeedback(boolean fullFilled, Operator operator) {
        LocalDateTime startDate = FEEDBACK_STARTING_DATE;
        LocalDateTime stopDate = fullFilled ? startDate.minusHours(
                new Random().nextLong(FEEDBACK_TIME_SPAN_HOURS)) : null;


        return OperationFeedback.builder()
                .operator(operator)
                .category(operator.getOperationCategory())
                .start(startDate)
                .stop(stopDate)
                .build();


    }
}
