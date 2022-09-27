package com.ppc.odc.data.creation;


import com.ppc.odc.data.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataCreatorConfiguration {
    public static int OPERATOR_COUNT = 30;
    public static int FEEDBACK_COUNT = 100;
    public static double FULLFILLED_FEEDBACK_FACTOR = 0.2;
    public static LocalDateTime FEEDBACK_STARTING_DATE = LocalDateTime.now();
    public static long FEEDBACK_TIME_SPAN_HOURS = 200;

    @Bean
    OperationStatusCreator operationStatusCreator(OperationStatusRepository operationStatusRepository) {
        return new OperationStatusCreator(operationStatusRepository);
    }

    @Bean
    OperationStepStatusCreator operationStepStatusCreator(OperationStepStatusRepository operationStepStatusRepository) {
        return new OperationStepStatusCreator(operationStepStatusRepository);
    }

    @Bean
    OperationCreator operationCreator(OperationRepository operationRepository,
                                      OperationStatusRepository operationStatusRepository,
                                      OperationStepRepository operationStepRepository,
                                      OperatorRepository operatorRepository,
                                      OperationStepStatusRepository operationStepStatusRepository,
                                      OperationCategoryRepository operationCategoryRepository) {
        return new OperationCreator(
                operationRepository,
                operationStatusRepository,
                operationStepRepository,
                operatorRepository,
                operationStepStatusRepository,
                operationCategoryRepository);

    }


}
