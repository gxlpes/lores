package com.api.lores.mapper;

import com.api.lores.dto.TreatmentDto;
import com.api.lores.model.TreatmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TreatmentMapper {

    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);

    TreatmentModel toModel(TreatmentDto dto);

    TreatmentDto toDto(TreatmentModel model);
}