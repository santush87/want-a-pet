package com.martinaleksandrov.wantapet.config;

import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.impl.WantAPetUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

//    private final String rememberMeKey;

//    public SecurityConfiguration(@Value("${wantapet.remember.me.key}")
//                                 String rememberMeKey) {
//        this.rememberMeKey = rememberMeKey;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                // Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/","/login", "/register", "/login-error").permitAll()
                        .requestMatchers("/catalog", "/catalog/dogs", "/catalog/cats", "/catalog/cats-and-dogs").permitAll()
                        .requestMatchers("/catalog/my-pets", "/catalog/edit/**",
                                "/catalog/my-adopted-pets", "/catalog/pets-to-send", "/catalog/pets-to-send/**").authenticated()
                        .requestMatchers("/users/my-account", "/users/my-account/edit/**").authenticated()
                        .requestMatchers("/add/cat", "/add/dog").authenticated()
                        // all other requests are authenticated.
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            // redirect here when we access something which is not allowed.
                            // also this is the page where we perform login.
                            .loginPage("/login")
                            // The names of the input fields (in our case in auth-login.html)
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/", true)
                            .failureForwardUrl("/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // the URL where we should POST something in order to perform the logout
                            .logoutUrl("/logout")
                            // where to go when logged out?
                            .logoutSuccessUrl("/")
                            // invalidate the HTTP session
                            .invalidateHttpSession(true);
                }
//        ).rememberMe(
//                rememberMe -> {
//                    rememberMe
//                            .key(rememberMeKey)
//                            .rememberMeParameter("rememberme")
//                            .rememberMeCookieName("rememberme");
//                }
        ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the "want a pet" users and roles
        // to representation which spring security understands.
        return new WantAPetUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
