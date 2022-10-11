package com.ppc.odc.mapstruct.dtos;

import com.ppc.odc.data.model.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class OperationGetDTO {
    private final long id;
    private final String batchId;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;
    private final Status status;

}
