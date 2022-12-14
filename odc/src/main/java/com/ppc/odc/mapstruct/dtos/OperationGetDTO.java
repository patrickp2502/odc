package com.ppc.odc.mapstruct.dtos;

import com.ppc.odc.data.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class OperationGetDTO {
    private final long id;
    private final String batchId;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;
    private final Status status;

}
