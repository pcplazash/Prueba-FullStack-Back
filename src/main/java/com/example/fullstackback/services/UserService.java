package com.example.fullstackback.services;

import com.example.fullstackback.dtos.CredentialsDto;
import com.example.fullstackback.dtos.SignUpDto;
import com.example.fullstackback.dtos.UserDto;
import com.example.fullstackback.entities.UserEntity;
import com.example.fullstackback.exceptions.AppException;
import com.example.fullstackback.mappers.UserMapper;
import com.example.fullstackback.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){
        UserEntity user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(()-> new AppException("Usuario Desconocido", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Contraseña incorrecta", HttpStatus.BAD_REQUEST);

    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> oUser = userRepository.findByLogin(signUpDto.login());

        if(oUser.isPresent()){
            throw new AppException("El usuario ya está activo y en la base de datos", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userMapper.signUpToUser(signUpDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        UserEntity savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }




}
