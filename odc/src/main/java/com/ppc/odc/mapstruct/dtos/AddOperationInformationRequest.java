package com.ppc.odc.mapstruct.dtos;


import java.time.LocalDateTime;


public record AddOperationInformationRequest(
        String operatorName,
        LocalDateTime timeStamp) {
}
