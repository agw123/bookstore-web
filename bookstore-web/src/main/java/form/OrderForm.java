package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class OrderForm extends ActionForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String shippingAddress;
	private long userId;
	private long paymentId;
	private long cartId;
	
	public OrderForm() {}

	
	public OrderForm(long id, String shippingAddress, long userId, long paymentId, long cartId) {
		super();
		this.id = id;
		this.shippingAddress = shippingAddress;
		this.userId = userId;
		this.paymentId = paymentId;
		this.cartId = cartId;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}


	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", shippingAddress=" + shippingAddress + ", userId=" + userId + ", paymentId="
				+ paymentId + ", cartId=" + cartId + "]";
	}

	
}
