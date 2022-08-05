package com.ppc.odc.data.creation;

import com.ppc.odc.data.model.OperationCategory;
import com.ppc.odc.data.repositories.OperationCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryCreator {

    private final OperationCategoryRepository operationCategoryRepository;


    public CategoryCreator(OperationCategoryRepository operationCategoryRepository) {
        this.operationCategoryRepository = operationCategoryRepository;
        initialize();
    }

    private void initialize() {

        Set<OperationCategory> categories = new HashSet<>(
                Set.of(
                        OperationCategory.builder()
                                .name("Mischen")
                                .build(),
                        OperationCategory.builder()
                                .name("maschinelle Behandlung")
                                .build(),
                        OperationCategory.builder()
                                .name("Abfüllen")
                                .build()
                )
        );

        operationCategoryRepository.saveAll(categories);
    }
}
