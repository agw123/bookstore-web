package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class BookForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String title;
	private String author;
	private String category;
	private double price;
	private boolean availability;
	
	public BookForm() {};
	
	public BookForm(long id, String title, String author, String category, double price, boolean availability) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.availability = availability;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "BookForm [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category + ", price="
				+ price + ", availability=" + availability + "]";
	}
	
	

}
