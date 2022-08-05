package com.ppc.odc.data.model;

import jdk.jfr.Name;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Builder
public class OperationCategory {

    @Id
    @GeneratedValue
    long id;
    String name;



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
