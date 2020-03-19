package application;

public final class UserSession {
	private static UserSession curruser;
	private String fname, lname, email, username;
	
	UserSession(String fname, String lname, String email, String username) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
	}
	
	public void logout() {
		curruser = null;
		username = fname = lname = email = null;
	}
	
	public static UserSession getCurrentUser() {
		return curruser;
	}
	
	public static UserSession login(String fname, String lname, String email, String username) {
		curruser = new UserSession(fname, lname, email, username);
		return curruser;
	}
	public String getUsername() {
		return this.username;
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public String getEmail() {
		return this.email;
	}
}
