package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.DAOimpl;
import form.BookForm;
import form.CartForm;
import form.CartItemForm;
import form.UserForm;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAction extends Action {

	private UserService userService = new UserService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAOimpl daoImpl = new DAOimpl();
		String path = mapping.getPath();
		CartItemForm cartItemForm = (CartItemForm) form;
		HttpSession session = request.getSession();
		UserForm user = (UserForm) session.getAttribute("user");

		CartForm cartDetail = null;
		CartItemForm cartItem = null;

		switch (path) {
		case "/add-to-cart":

			// System.out.println("form " + cartItem);

			// System.out.println("book id: " + cartItem.getBookId());

			cartDetail = daoImpl.findCartByUser(user.getId());
			// System.out.println("cartDetail " + cartDetail);

			if (cartDetail == null) {

				int cartCreated = daoImpl.createCart(user.getId());
				// System.out.println("cartCreated " + cartCreated);
				if (cartCreated > 0) {
					cartDetail = daoImpl.findCartByUser(user.getId());
					CartItemForm newCartItem = new CartItemForm(cartDetail.getId(), cartItemForm.getBookId(), 1);
					userService.addToCart(newCartItem);
				} else {
					System.out.println("Something went wrong - try to add product to cart again");
				}
			} else if (cartDetail != null) {
				CartItemForm cartItemExisting = userService.findCartItemByCart(cartDetail.getId(),
						cartItemForm.getBookId());
				if (cartItemExisting != null) {
					userService.updateCartItem(cartItemExisting.getId(), cartItemExisting.getQuantity() + 1);
				} else {
					int newCartItem = userService
							.addToCart(new CartItemForm(cartDetail.getId(), cartItemForm.getBookId(), 1));
					System.out.println("cartItemExisting not found " + cartItemExisting);
				}
			}
			return mapping.findForward("success");

		case "/show-cart":

			cartDetail = daoImpl.findCartByUser(user.getId());
			System.out.println("cart detail " + cartDetail);

			ArrayList<CartItemForm> cartItems = null;
			ArrayList<BookForm> books = new ArrayList<>();
			Map<Long, Integer> quantityMap = new HashMap<>();
			if (cartDetail != null) {
				cartItems = userService.showCart(cartDetail.getId());
				System.out.println("****************");
				System.out.println("cart items " + cartItems);

				for (CartItemForm c : cartItems) {
					BookForm book = daoImpl.findBookById(c.getBookId());
					books.add(book);
					quantityMap.put(c.getBookId(), c.getQuantity());
				}
			}
			request.setAttribute("productsInCart", books);
			request.setAttribute("quantityMap", quantityMap);
			return mapping.findForward("success");

		default:
			return mapping.findForward("error");
		}
	}
}
