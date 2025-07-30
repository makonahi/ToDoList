package makonahi.ToDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterChainConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.requestMatchers("/swagger-ui/**")
                        .permitAll()*/
                        //.requestMatchers("/v3/api-docs*/**")
             //           .permitAll());

        //http.csrf(AbstractHttpConfigurer::disable);

        return http.csrf(customizer -> customizer.disable()).
                authorizeHttpRequests(request -> request
                        .requestMatchers("login", "register").permitAll()
                        .requestMatchers("/tasks/hello", "/tasks", "/tasks/1").permitAll()
                        .anyRequest().authenticated()).
                httpBasic(Customizer.withDefaults()).
                sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }
}