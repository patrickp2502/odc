package com.ppc.odc.mapstruct.dtos;

import java.util.List;

public record InformationDTO(List<String> operatorNames,
                             List<String> activeBatchIds) {
}
