package com.ppc.odc.data.model;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
    private String batchId;
    @OneToMany
    private List<OperationStep> steps;
    private long duration;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    @ManyToOne
    private OperationStatus status;


}
