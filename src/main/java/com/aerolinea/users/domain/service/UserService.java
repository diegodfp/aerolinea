package com.aerolinea.users.domain.service;

import com.aerolinea.users.domain.entity.User;

public interface UserService {
    void createUser(User user);
    User findUserById(int id);
    void updateUser(User userUpdate);
    void deleteUser(int id);
}