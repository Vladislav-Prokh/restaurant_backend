package delivery.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import delivery.app.services.CustomOAuth2UserService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService) throws Exception {
	    http
	        .authorizeHttpRequests(authz -> authz
	        		.requestMatchers("/login/oauth2/code/google").permitAll()
	        	.requestMatchers("/menu/beverages").permitAll()
	        	.requestMatchers("/menu/lunches").permitAll()
                .requestMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/menu/**").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.GET, "/orders").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("WAITER","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/orders").hasAnyRole("ADMIN")
               
                .requestMatchers("/employees").hasRole("ADMIN")
	            .requestMatchers("/login").permitAll()
	            .anyRequest().authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	            .userInfoEndpoint(userInfo -> userInfo
	                .userService(customOAuth2UserService)
	            )
	            .defaultSuccessUrl("http://localhost:8080/menu/beverages", true)
	        )
	        .logout(logout -> logout  
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	            )
	        
	    .sessionManagement(session -> 
        session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) 
            .invalidSessionUrl("/sessionExpired") 
            .maximumSessions(1)
            .expiredUrl("/sessionExpired")
	    );
	    

	    return http.build();
	}

}
