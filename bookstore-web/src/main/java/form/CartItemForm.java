package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class CartItemForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long cartId;
	private long bookId;
	private int quantity;
	
	public CartItemForm() {}
	
	public CartItemForm(long cartId, long bookId, int quantity) {
		this.cartId = cartId;
		this.bookId = bookId;
		this.quantity = quantity;
	}


	public CartItemForm(long id, long cartId, long bookId, int quantity) {
		this.id = id;
		this.cartId = cartId;
		this.bookId = bookId;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItemForm [id=" + id + ", cartId=" + cartId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
	}
	
	
	

}
