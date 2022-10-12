package com.ppc.odc.data.creation;


import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.repositories.OperationStepRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.ppc.odc.data.creation.DataCreatorConfiguration.*;

@Component
@Deprecated
public class OperationStepCreator {

    private final OperationStepRepository operationStepRepository;
    private final OperatorRepository operatorRepository;

    public OperationStepCreator(OperationStepRepository operationStepRepository,
                                OperatorRepository operatorRepository) {
        this.operationStepRepository = operationStepRepository;
        this.operatorRepository = operatorRepository;
    }

    public void initialize() {
        List<Operator> operators = operatorRepository.findAll();
        for (int i = 0; i < FEEDBACK_COUNT; i++) {
            operationStepRepository.save(createOperationFeedback(
                    (i < FEEDBACK_COUNT * FULLFILLED_FEEDBACK_FACTOR),
                    operators.get(new Random().nextInt(operators.size()))));

        }
        System.out.println(operationStepRepository.findAll());

    }

    private OperationStep createOperationFeedback(boolean fullFilled, Operator operator) {
        LocalDateTime startDate = FEEDBACK_STARTING_DATE;
        LocalDateTime stopDate = fullFilled ? startDate.minusHours(
                new Random().nextLong(FEEDBACK_TIME_SPAN_HOURS)) : null;


        return OperationStep.builder()
                .operator(operator)
                .category(operator.getOperationCategory())
                .startTime(startDate)
                .stopTime(stopDate)
                .build();


    }
}
