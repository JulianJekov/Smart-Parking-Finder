package com.smart.parking.service;

import com.smart.parking.model.dto.auth.UserRegisterDTO;

public interface AuthService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
