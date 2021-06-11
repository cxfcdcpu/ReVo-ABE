package entity;

public class user {
	private String username;
	private String password;
	private String attributes;
	
	public user(String un, String pw, String attr) {
		this.username = un;
		this.password = pw;
		this.attributes = attr;
	}
	
	public String getHashedPassword() {
		return this.password;
	}
}
