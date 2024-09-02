package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import form.SearchForm;
import form.UserForm;
import service.UserService;

import java.util.ArrayList;

public class BookAction extends Action {

	private UserService userService = new UserService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = mapping.getPath();
		HttpSession session = request.getSession();
		UserForm user = (UserForm) session.getAttribute("user");
		
		ServletContext context = getServlet().getServletContext();
		

		switch (path) {
		case "/all-books":
			context.setAttribute("appTitle", "Bookstore");
			ArrayList<BookForm> books = userService.getAllBooks();
			System.out.println(books);
			request.getSession().setAttribute("booksList", books);
			break;
		case "/search-book":
			context.setAttribute("appTitle", "Bookstore");
			SearchForm searchForm = (SearchForm) form;
			String searchString = request.getParameter("searchString");
			ArrayList<BookForm> searchResults = userService.searchBooks(searchForm.getSearchParam());
			request.setAttribute("books", searchResults);
			request.getSession().setAttribute("booksList", searchResults);
			break;
		default:
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
}
