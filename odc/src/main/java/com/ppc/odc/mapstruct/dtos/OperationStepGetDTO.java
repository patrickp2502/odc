package com.ppc.odc.mapstruct.dtos;

import com.ppc.odc.data.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OperationStepGetDTO {
    private final long id;
    private final String category;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;
    private final String operatorName;
    private final Status status;
}
