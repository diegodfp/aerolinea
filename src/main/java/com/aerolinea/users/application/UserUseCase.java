package com.aerolinea.users.application;

import com.aerolinea.users.domain.entity.User;
import com.aerolinea.users.domain.service.UserService;

public class UserUseCase {
    private final UserService userService;

    public UserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void createUser(User user) {
        userService.createUser(user);
    }

    public void updateUser(User userUpdate) {
        userService.updateUser(userUpdate);
    }

    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    public User findUserById(int id) {
        return userService.findUserById(id);
    }

}
