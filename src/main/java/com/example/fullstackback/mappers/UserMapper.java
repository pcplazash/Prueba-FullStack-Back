package com.example.fullstackback.mappers;
import com.example.fullstackback.dtos.SignUpDto;
import com.example.fullstackback.dtos.UserDto;
import com.example.fullstackback.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel ="spring")
public interface UserMapper {

    UserDto toUserDto(UserEntity user);

    @Mapping(target = "password", ignore=true)
    UserEntity signUpToUser(SignUpDto signUpDto);
}
