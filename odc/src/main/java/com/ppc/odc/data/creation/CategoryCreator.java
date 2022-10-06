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
    }

    public void initialize() {

        Set<OperationCategory> categories = new HashSet<>(
                Set.of(
                        OperationCategory.builder()
                                .name("Mischen")
                                .shortName("M")
                                .build(),
                        OperationCategory.builder()
                                .name("maschinelle Behandlung")
                                .shortName("MB")
                                .build(),
                        OperationCategory.builder()
                                .name("Abfüllen")
                                .shortName("ABF")
                                .build()
                )
        );

        operationCategoryRepository.saveAll(categories);
    }
}
