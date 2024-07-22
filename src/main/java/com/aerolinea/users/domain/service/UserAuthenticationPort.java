package com.aerolinea.users.domain.service;

import com.aerolinea.users.domain.entity.User;

public interface UserAuthenticationPort {
    User getUsuarioByCredentials(String username, String password);
    String obtenerRolUsuario(String nombreUsuario);
}
