package com.api.lores.mapper;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.model.AppointmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentModel toModel(AppointmentDto dto);

    AppointmentDto toDto(AppointmentModel model);
}