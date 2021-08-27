package pl.partabelo.demo.security.jwt;

import org.springframework.security.core.Authentication;
import pl.partabelo.demo.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

public interface IJwtProvider {
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
