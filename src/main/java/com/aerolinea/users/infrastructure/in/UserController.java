package com.aerolinea.users.infrastructure.in;

import java.sql.SQLException;
import java.util.Scanner;

import com.aerolinea.users.application.UserUseCase;
import com.aerolinea.users.domain.entity.User;
import com.aerolinea.users.infrastructure.out.UserRepository;

public class UserController {
    private UserUseCase userUseCase;
    private UserRepository userRepository;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    public void addUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter user name: ");
            String name = scanner.nextLine();

            System.out.println("Enter user email: ");
            String email = scanner.nextLine();

            System.out.println("ingrese el password");
            String password = scanner.nextLine();

            String escogerRol = """
                    ********************************************
                    Ingrese el numero del rol correspondiente:
                    1. Administrador
                    2. Agente de ventas
                    3. Tecnico de mantenimiento
                    4. cliente
                    ********************************************
                    """.formatted();
            System.out.println(escogerRol);
            int idRol = scanner.nextInt();
            scanner.nextLine();

            User user = new User();
            user.setNombre(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setIdRol(idRol);
            userUseCase.createUser(user);
        }

        System.out.println("User created successfully!");
    }

    public void updateUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter id to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter  new user name: ");
            String name = scanner.nextLine();

            System.out.println("Enter new user email: ");
            String email = scanner.nextLine();

            User user = new User();
            user.setId(id);
            user.setNombre(name);
            user.setEmail(email);

            userUseCase.updateUser(user);
        }

        System.out.println("User update successfully!");
    }

    public void deleteUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter id to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            userUseCase.deleteUser(id);
        }
        System.out.println("User delete successfully");
    }

    public boolean autenticarUsuario(String usuario, String contrasena) throws SQLException {
        User usuarioValido = userRepository.getUsuarioByCredentials(usuario, contrasena);
        return usuarioValido != null;
    }
}
