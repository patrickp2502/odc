package com.ppc.odc.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class OperationStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private OperationCategory category;
    @ManyToOne
    private OperationStepStatus status;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private LocalDateTime duration;
    @ManyToOne
    Operator operator;


}
