package com.api.lores.mapper;

import com.api.lores.dto.UserDto;
import com.api.lores.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel toModel(UserDto dto);

    UserDto toDto(UserModel model);
}