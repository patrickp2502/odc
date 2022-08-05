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
public class OperationFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @ManyToOne
    OperationCategory category;
    LocalDateTime start;
    LocalDateTime stop;
    LocalDateTime duration;
    @ManyToOne
    Operator operator;


}
