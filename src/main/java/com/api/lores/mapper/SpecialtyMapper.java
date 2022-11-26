package com.api.lores.mapper;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.model.SpecialtyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecialtyMapper {

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyModel toModel(SpecialtyDto dto);

    SpecialtyDto toDto(SpecialtyModel model);
}