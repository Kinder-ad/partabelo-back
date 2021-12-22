package pl.partabelo.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableScheduling
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/api/noFilter/**")
//                .antMatchers("/api/spotify/song/current")
//                .antMatchers("/api/spotify/queue");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()
                .antMatchers("/api/spotify/song/current").permitAll()
                .antMatchers(HttpMethod.GET,"/api/spotify/queue").permitAll()
                .antMatchers("/api/noFilter/stats/percentageOfPaid").permitAll()
                .antMatchers("/api/user").hasAnyRole(Role.ADMIN.name(), Role.MOD.name())
                .antMatchers("/api/user/request").hasAnyRole(Role.ADMIN.name(),Role.MOD.name(),Role.USER.name())
                .antMatchers("/api/user/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/api/expenditure").hasAnyRole(Role.ADMIN.name(), Role.MOD.name())
                .antMatchers("/api/expenditure/**").hasAnyRole(Role.ADMIN.name(), Role.MOD.name())
                .antMatchers(HttpMethod.POST,"/api/spotify/song").hasAnyRole(Role.ADMIN.name(),Role.USER.name(),Role.MOD.name())
                .antMatchers(HttpMethod.GET,"/api/spotify/song").hasAnyRole(Role.ADMIN.name(),Role.USER.name(),Role.MOD.name())
                .antMatchers("/api/spotify/device").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/api/spotify/**").hasAnyRole(Role.ADMIN.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
