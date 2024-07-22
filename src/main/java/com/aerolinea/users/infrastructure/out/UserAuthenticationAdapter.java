package com.aerolinea.users.infrastructure.out;

import com.aerolinea.users.domain.entity.User;
import com.aerolinea.users.domain.service.UserAuthenticationPort;

public class UserAuthenticationAdapter implements UserAuthenticationPort {
    private final UserRepository userRepository;

    public UserAuthenticationAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUsuarioByCredentials(String username, String password) {
        return userRepository.getUsuarioByCredentials(username, password);
    }

    @Override
    public String obtenerRolUsuario(String username) {
        return userRepository.obtenerRolUsuario(username);
    }
}
