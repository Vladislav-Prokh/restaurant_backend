package delivery.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import delivery.app.services.CustomOAuth2UserService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService) throws Exception {
	    http
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/login").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	            .userInfoEndpoint(userInfo -> userInfo
	                .userService(customOAuth2UserService)
	            )
	            .defaultSuccessUrl("/", true)
	        );

	    return http.build();
	}

}
