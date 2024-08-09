package form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class SearchForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String searchParam;
	
	public SearchForm() {}

	public SearchForm(String searchParam) {
		this.searchParam = searchParam;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
}
