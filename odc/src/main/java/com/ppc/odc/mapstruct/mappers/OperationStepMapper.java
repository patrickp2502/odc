package com.ppc.odc.mapstruct.mappers;

import com.ppc.odc.data.model.OperationStep;
import com.ppc.odc.mapstruct.dtos.OperationStepGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationStepMapper {
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "status", source = "status.status")
    @Mapping(target = "operatorName", source = "operator.name")
    OperationStepGetDTO operationStepToOperationStepGetDTO(OperationStep operationStep);



}



