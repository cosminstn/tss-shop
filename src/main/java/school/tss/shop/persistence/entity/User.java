package school.tss.shop.persistence.entity;

public class User {

	private long id;
	private String username;
	private String password;
	private Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public enum Role {
		MANAGEMENT,
		CUSTOMER;

		public String getId() {
			return name().toUpperCase();
		}

		public static Role getRoleById(String id) {
			for(Role role : Role.values()) {
				if (role.name().equals(id)) {
					return role;
				}
			}
			return null;
		}


	}

}
