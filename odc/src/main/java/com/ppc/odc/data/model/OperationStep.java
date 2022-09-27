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
@ToString
public class OperationStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private OperationCategory category;
    @ManyToOne
    private OperationStepStatus status;
    private LocalDateTime start;
    private LocalDateTime stop;
    private LocalDateTime duration;
    @ManyToOne
    Operator operator;


}
