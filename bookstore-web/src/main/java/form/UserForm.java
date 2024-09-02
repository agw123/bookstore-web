package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private String password;
	
	public UserForm() {}
	
	public UserForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserForm(long id, String username) {
		this.id = id;
		this.username = username;
	}

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

	@Override
	public String toString() {
		return "UserForm [id=" + id + ", username=" + username + ", password=" + password + "]";
	}	
	
}
