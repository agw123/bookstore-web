package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class PaymentForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String iban;
	private String cvvCode;
	private String exDate;
	
	public PaymentForm() {}


	public PaymentForm(long id, String iban, String cvvCode, String exDate) {
		this.id = id;
		this.iban = iban;
		this.cvvCode = cvvCode;
		this.exDate = exDate;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

	@Override
	public String toString() {
		return "PaymentForm [id=" + id + ", iban=" + iban + ", exDate=" + exDate + "]";
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	

}
