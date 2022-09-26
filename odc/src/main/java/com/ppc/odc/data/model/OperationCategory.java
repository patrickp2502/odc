package com.ppc.odc.data.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
public class OperationCategory {

    @Id
    @GeneratedValue
    long id;
    String name;
    String shortName;




}
