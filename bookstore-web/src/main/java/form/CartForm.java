package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class CartForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long userId;
	
	public CartForm() {}
	
	public CartForm(long id) {
		this.id = id;
		
	}

	public CartForm(long id, long userId) {
		this.id = id;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "CartForm [id=" + id + ", userId=" + userId + "]";
	}
	
	
	

}
