package delivery.app.DTO;

public class RoleRequest {
	private String role;

	public RoleRequest(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public RoleRequest() {
		super();
	}
	
}
