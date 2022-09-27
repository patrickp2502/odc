package com.ppc.odc.data.model;


import com.ppc.odc.data.model.enums.Status;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class OperationStepStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Status status;
}
