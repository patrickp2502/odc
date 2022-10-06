package com.ppc.odc.data.creation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MasterCreator implements ApplicationRunner {

    private final CategoryCreator categoryCreator;
    private final OperatorCreator operatorCreator;
    private final OperationStatusCreator operationStatusCreator;
    private final OperationStepStatusCreator operationStepStatusCreator;
    private final OperationCreator operationCreator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        categoryCreator.initialize();
        log.info("categories created");
        operatorCreator.initialize();
        log.info("operators created");
        operationStatusCreator.initialize();
        log.info("operation statuses created");
        operationStepStatusCreator.initialize();
        log.info("step statuses created");
        operationCreator.initialize();
        log.info("operations created");



    }
}
