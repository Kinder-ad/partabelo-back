package pl.partabelo.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.IUserService;
import pl.partabelo.demo.utils.SecurityUtils;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userService.findByUsername(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .username(email)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
