package pl.partabelo.demo.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private IJwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = jwtProvider.getAuthentication(httpServletRequest);

        if(authentication != null && jwtProvider.validateToken(httpServletRequest)){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String a = "/api/noFilter/stats/percentageOfPaid";
        if(request.getRequestURI().startsWith("/api/spotify/song/current")) a = "/api/spotify/song/current";
        if(request.getRequestURI().startsWith("/api/spotify/queue")) a = "/api/spotify/queue";
        return request.getRequestURI().startsWith(a);
    }
}
