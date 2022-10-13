package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.model.Operator;
import com.ppc.odc.data.repositories.OperationCategoryRepository;
import com.ppc.odc.data.repositories.OperatorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class OperatorCreator {
    private final OperatorRepository operatorRepository;
    private final OperationCategoryRepository operationCategoryRepository;
    private final Stack<String> names = new Stack<>();

    public OperatorCreator(OperatorRepository operatorRepository,
                           OperationCategoryRepository operationCategoryRepository) {
        this.operatorRepository = operatorRepository;
        this.operationCategoryRepository = operationCategoryRepository;

    }

    private void initNames() {
        List<String> generatedNames = IntStream.iterate(0, i -> i + 1)
                .boxed()
                .map(number -> "user" + number)
                .limit(10).toList();
        names.addAll(generatedNames);
    }

    public void initialize() {
        initNames();
        List<Operator> operators = new ArrayList<>();
        for (int i = 0; i < DataCreatorConfiguration.OPERATOR_COUNT; i++) {
            operators.add(createRandomOperator());

        }
        operatorRepository.saveAll(operators);

    }

    private Operator createRandomOperator() {
        List<OperationCategory> possibleCategories = operationCategoryRepository.findAll();
        OperationCategory randomOperationCategory = possibleCategories
                .get(new Random().nextInt(possibleCategories.size()));

        return Operator.builder()
                .operationCategory(randomOperationCategory)
                .name(names.pop())
                .build();
    }
}
