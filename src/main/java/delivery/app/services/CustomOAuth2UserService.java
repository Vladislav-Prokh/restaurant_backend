package delivery.app.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import delivery.app.entities.Employee;
import delivery.app.entities.Role;
import delivery.app.repositories.EmployeeRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private EmployeeRepository employeRepository;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> attributes = oAuth2User.getAttributes();

		String name = attributes.getOrDefault("given_name", "").toString();
		String surname = attributes.getOrDefault("family_name", "").toString();
		String email = attributes.getOrDefault("email", "").toString();
		
		Employee employee = employeRepository.findByEmployeeEmail(email);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (employee == null) {
			employee = new Employee();
			employee.setEmployeeName(name);
			employee.setEmployeeSurName(surname);
			employee.setEmployeeEmail(email);
			employee.setRole(Role.DEFAULT);
			employeeService.saveEmployee(employee);
			authorities.add(new SimpleGrantedAuthority(employee.getRole().toString()));
		} else {
			SimpleGrantedAuthority empRole = new SimpleGrantedAuthority(employee.getRole().toString());
			authorities.add(empRole);
		}

		return new DefaultOAuth2User(authorities, attributes, "name");
	}

}
