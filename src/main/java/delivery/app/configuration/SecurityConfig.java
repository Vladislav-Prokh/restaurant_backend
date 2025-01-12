package delivery.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import delivery.app.services.CustomOidcUserService;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	
	 private final CustomOidcUserService customOidcUserService;

	    public SecurityConfig(CustomOidcUserService customOidcUserService) {
	        this.customOidcUserService = customOidcUserService;
	    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authz -> authz
	        		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	            .requestMatchers("/api/auth/logout").permitAll()
		        .requestMatchers("/api/auth/userinfo").permitAll()
                .requestMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/menu/**").hasRole("ADMIN")
            	.requestMatchers(HttpMethod.GET,"/menu/meals").hasRole("ADMIN")
            	.requestMatchers(HttpMethod.GET,"/menu/desserts").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/orders").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("WAITER","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/orders").hasAnyRole("ADMIN")
                .requestMatchers("/employees").hasRole("ADMIN")
            	.requestMatchers("/menu/beverages").permitAll()
	        	.requestMatchers("/menu/lunches").permitAll()
	            .anyRequest().authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	                .userInfoEndpoint(userInfo -> userInfo.oidcUserService(customOidcUserService))
	        		.defaultSuccessUrl("http://localhost:4200/menu/lunches", true)	
	            )
	        .logout(logout -> logout  
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	            )
	        
	        .sessionManagement(session -> 
	        session
	            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) 
	            .invalidSessionStrategy((request, response) -> {
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.getWriter().write("Session expired");
	            })
	     
	    );
	   
	    return http.build();
	}
}
