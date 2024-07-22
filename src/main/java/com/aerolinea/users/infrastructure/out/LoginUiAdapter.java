    package com.aerolinea.users.infrastructure.out;


    import com.aerolinea.users.domain.service.UserAuthenticationPort;
    import com.aerolinea.users.domain.service.UserLoginUseCase;

    public class LoginUiAdapter  implements UserLoginUseCase {

        private final UserAuthenticationPort authenticationPort;
        private final UserRepository userRepository;
    
        public LoginUiAdapter(UserAuthenticationPort authenticationPort, UserRepository userRepository) {
            this.authenticationPort = authenticationPort;
            this.userRepository = userRepository;
        }
    
        @Override
        public boolean authenticateUser(String username, String password) {
            return authenticationPort.getUsuarioByCredentials(username, password) != null;
        }
    
        @Override
        public String obtenerRolUsuario(String username) {
            return userRepository.obtenerRolUsuario(username);
        }
    }
