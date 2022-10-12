package com.ppc.odc.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Builder
public class Operator {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    OperationCategory operationCategory;

}
