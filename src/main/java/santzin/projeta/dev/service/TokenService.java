package santzin.projeta.dev.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.model.UserModel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${projeta.dev.security.token.secret.user}")
    private String secretUser;

    private Instant getExpiresAt(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    public String generateToken(UserModel user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretUser);
            return JWT.create()
                    .withIssuer("projection.dev")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpiresAt())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Deu ruin gerar token");
        }
    }

    public String authenticateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretUser);
            return JWT.require(algorithm)
                    .withIssuer("projection.dev")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            return "";
        }
    }


}
