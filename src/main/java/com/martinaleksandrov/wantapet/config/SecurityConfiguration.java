package com.martinaleksandrov.wantapet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http,
//                                           SecurityContextRepository securityContextRepository)
//            throws Exception {
//        http.authorizeHttpRequests().
//                // allow access to all static files (images, CSS, js)
//                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
////                requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll().
//                // the URL-s below are available for all users - logged in and anonymous
//                        requestMatchers("/", "/about", "/users/login", "/users/register", "/users/login-error").permitAll().
//                // allow just for admins
////                        requestMatchers("/admin").hasRole(UserRoleEnum.ADMIN.name()).
//                anyRequest().authenticated().
//                and().
//                // configure login with HTML form
//                        formLogin().
//                loginPage("/users/login").
//                // the names of the username, password input fields in the custom login form
//                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
//                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
//                // where do we go after login
//                        defaultSuccessUrl("/").//use true argument if you always want to go there, otherwise go to previous page
//                failureForwardUrl("/users/login-error").
//                and().
//                logout().//configure logout
//                logoutUrl("/users/logout").
//                logoutSuccessUrl("/").//go to homepage after logout
//                invalidateHttpSession(true).
////                and().
////                    rememberMe().
////                        rememberMeParameter("remember").
////                        key("remember Me Encryption Key").
////                        rememberMeCookieName("rememberMeCookieName").
////                        tokenValiditySeconds(10000).
//        and().
//                securityContext().
//                securityContextRepository(securityContextRepository);
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new ApplicationUserDetailsService(userRepository);
//    }

//    @Bean
//    public SecurityContextRepository securityContextRepository() {
//        return new DelegatingSecurityContextRepository(
//                new RequestAttributeSecurityContextRepository(),
//                new HttpSessionSecurityContextRepository()
//        );
//    }
}
