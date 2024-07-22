package com.aerolinea.users.domain.service;

public interface UserLoginUseCase {
    boolean authenticateUser(String username, String password);
    String obtenerRolUsuario(String username);
}
