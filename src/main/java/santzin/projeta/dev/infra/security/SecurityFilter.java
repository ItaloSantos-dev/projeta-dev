package santzin.projeta.dev.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import santzin.projeta.dev.exception.FailedLoginException;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.UserRepository;
import santzin.projeta.dev.service.TokenService;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token!=null){
            String email = this.tokenService.authenticateToken(token);

            UserModel user = (UserModel) this.userRepository.findByEmail(email)
                    .orElseThrow(()-> new FailedLoginException("Token inválido"));

            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        if (auth==null)
            return null;
        return auth.replace("Bearer ", "");
    }
}
