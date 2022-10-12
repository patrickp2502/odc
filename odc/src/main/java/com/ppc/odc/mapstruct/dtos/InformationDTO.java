package com.ppc.odc.mapstruct.dtos;

import com.ppc.odc.data.model.Operator;

import java.util.List;

public record InformationDTO(List<String> operatorNames,
                             List<String> activeBatchNumbers) {
}
