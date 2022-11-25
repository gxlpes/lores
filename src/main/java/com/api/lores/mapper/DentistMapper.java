package com.api.lores.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.api.lores.dto.DentistDto;
import com.api.lores.entity.DentistModel;

@Mapper
public interface DentistMapper {

    DentistMapper INSTANCE = Mappers.getMapper(DentistMapper.class);

    DentistModel toModel(DentistDto dto);
    DentistDto toDto(DentistModel model);
}