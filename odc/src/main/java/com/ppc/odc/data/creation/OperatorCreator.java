package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.repositories.OperationCategoryRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class OperatorCreator {
    private final OperatorRepository operatorRepository;
    private final OperationCategoryRepository operationCategoryRepository;

    public OperatorCreator(OperatorRepository operatorRepository,
                           OperationCategoryRepository operationCategoryRepository) {
        this.operatorRepository = operatorRepository;
        this.operationCategoryRepository = operationCategoryRepository;
        initialize();

    }

    private void initialize() {
        List<Operator> operators = new ArrayList<>();
        for (int i = 0; i < DataCreatorConfiguration.OPERATOR_COUNT; i++) {
            operators.add(createRandomOperator());
        }
        operatorRepository.saveAll(operators).forEach(operator -> System.out.println("operator = " + operator));

    }

    private Operator createRandomOperator() {
        List<OperationCategory> possibleCategories = operationCategoryRepository.findAll();
        OperationCategory randomOperationCategory = possibleCategories
                .get(new Random().nextInt(possibleCategories.size()));

        return Operator.builder()
                .operationCategory(randomOperationCategory)
                .build();


    }
}
