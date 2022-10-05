package com.ppc.odc.mapstruct.mappers;

import com.ppc.odc.data.model.Operation;
import com.ppc.odc.mapstruct.dtos.OperationGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    Operation operationGetDTOtoOperation(OperationGetDTO operationGetDTO);
    @Mapping(target = "status", source = "status.status" )
    OperationGetDTO operationToOperationGetDTO(Operation operation);



}
