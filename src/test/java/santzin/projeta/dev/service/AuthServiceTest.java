package santzin.projeta.dev.service;

import org.hibernate.mapping.Any;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import santzin.projeta.dev.DTOs.auth.LoginRequestDTO;
import santzin.projeta.dev.mapper.UserMapper;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.UserRepository;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthServiceTest {

    @InjectMocks
     AuthService authService;

    @Mock
     UserRepository userRepository;

    @Mock
     AuthenticationManager authenticationManager;

    @Mock
     TokenService tokenService;

    @Mock
     UserMapper userMapper;



    @Test
    @DisplayName("Submit correct information and log in successfully.")
    void loginCase1() {
        LoginRequestDTO request = new LoginRequestDTO("italo@gmail.com", "1131");

        Authentication authMock = Mockito.mock(Authentication.class);

        UserModel userMock = Mockito.mock(UserModel.class);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authMock);

        Mockito.when(authMock.isAuthenticated()).thenReturn(true);

        Mockito.when(authMock.getPrincipal()).thenReturn(userMock);

        Mockito.when(tokenService.generateToken(Mockito.any())).thenReturn("Token");

        String result = authService.login(request);

        assertEquals("Token", result);
    }

    @Test
    @DisplayName("Informações enviadas incorretamente resultaram em falha no login.")
    void loginCase2() {
        LoginRequestDTO request = new LoginRequestDTO("italo@gmail.com", "1131");

        Authentication authMock = Mockito.mock(Authentication.class);

        UserModel userMock = Mockito.mock(UserModel.class);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authMock);

        Mockito.when(authMock.isAuthenticated()).thenReturn(false);

        assertThrows(RuntimeException.class, ()-> authService.login(request));
    }

    @Test
    @DisplayName("Enviou infos certa e deu bom")
    void registerCase1() {
    }

    @Test
    @DisplayName("Enviou email ja cadastrado e deu ruin")
    void registerCase2() {
    }
}