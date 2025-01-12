package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import delivery.app.entities.Employee;
import delivery.app.repositories.EmployeeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/userinfo")
	public ResponseEntity<Map<String, Object>> getUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "No authentication information available"));
		}

		OAuth2User user = (OAuth2User) authentication.getPrincipal();

		Map<String, Object> attributes = user.getAttributes();

		Employee employee = employeeRepository.findByEmployeeEmail(attributes.get("email").toString());

		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Employee not found"));
		}

		Map<String, Object> modifiableAttributes = new HashMap<>(attributes);
		modifiableAttributes.put("role", employee.getRole().toString());
		modifiableAttributes.put("id", employee.getEmployeeId());
		return ResponseEntity.ok(modifiableAttributes);

	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		return ResponseEntity.ok().build();
	}

}