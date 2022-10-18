package com.ppc.odc.controller;

import com.ppc.odc.service.OperationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OperationControllerTest {
    private static final String baseURI = ("api/v1/operations");

    private static final long OPERATION_ID = 1;

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    OperationService operationService;

    @Test
    void getAllOperationsUsesOperationServiceMethodGetAllOperationDTOs() {
        webTestClient.get()
                .uri(baseURI)
                .exchange();
        verify(operationService).getAllOperationDTOs();
    }

    @Test
    void getOperationByIdUsesOperationServiceMethodGetOperation() {
        webTestClient.get()
                .uri(baseURI+"/"+OPERATION_ID)
                .exchange();
        verify(operationService).getOperationBy(OPERATION_ID);
    }

    @Test
    void getOperationStepsUsesOperationServiceMethodGetOperationStepsDTOs() {
       webTestClient.get()
               .uri(baseURI+"/"+OPERATION_ID+"/steps")
               .exchange();
        verify(operationService).getOperationStepsDTOs(OPERATION_ID);
    }

    @Test
    void getStatusesUsesOperationServiceMethodGetStatuses() {
        webTestClient.get()
                .uri(baseURI+"/statuses")
                .exchange();
        verify(operationService).getStatuses();

    }

    @Test
    void getInformationUsesOperationServiceMethodGetInformation() {
        webTestClient.get()
                .uri(baseURI+"/information")
                .exchange();
        verify(operationService).getInformation();
    }
}