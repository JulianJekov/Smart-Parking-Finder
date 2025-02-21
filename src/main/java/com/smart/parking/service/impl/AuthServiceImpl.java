package com.smart.parking.service.impl;

import com.smart.parking.model.dto.auth.UserRegisterDTO;
import com.smart.parking.model.entity.User;
import com.smart.parking.model.enums.Role;
import com.smart.parking.repository.UserRepository;
import com.smart.parking.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByEmail(userRegisterDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email address already in use");
        }
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRole(userRegisterDTO.getRole());
        userRepository.save(user);
    }
}
