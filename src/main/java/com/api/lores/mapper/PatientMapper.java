package com.api.lores.mapper;

import com.api.lores.dto.PatientDto;
import com.api.lores.model.PatientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientModel toModel(PatientDto dto);

    PatientDto toDto(PatientModel model);
}