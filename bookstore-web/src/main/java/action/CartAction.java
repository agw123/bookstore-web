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

public class CartAction extends Action {

	private UserService userService = new UserService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAOimpl daoImpl = new DAOimpl();
		String path = mapping.getPath();
		CartItemForm cartItem = (CartItemForm) form;
		HttpSession session = request.getSession();
		UserForm user = (UserForm) session.getAttribute("user");
		System.out.println(user);
		CartForm cartDetail = null;

		switch (path) {
		case "/add-to-cart":

			System.out.println("form " + cartItem);

			System.out.println("book id: " + cartItem.getBookId());

			cartDetail = daoImpl.findCartByUser(user.getId());

			if (cartDetail == null) {

				int cartCreated = daoImpl.createCart(user.getId());
				System.out.println("cartCreated " + cartCreated);

				cartDetail = daoImpl.findCartByUser(user.getId());
				System.out.println("cartDetail " + cartDetail);

				CartItemForm newCartItem = new CartItemForm(cartDetail.getId(), cartItem.getBookId(), 1);
				userService.addToCart(newCartItem);

			} else {
				CartItemForm cartItemExisting = userService.findCartItemByCart(cartDetail.getId(), cartItem.getBookId());
				if (cartItemExisting != null) {
				userService.updateCartItem(cartItemExisting.getId(), cartItemExisting.getQuantity() + 1);
				} else {
					System.out.println("cartItemExisting not found "+cartItemExisting);
				}
			}
			return mapping.findForward("success");

		case "/show-cart":

			cartDetail = daoImpl.findCartByUser(user.getId());
			System.out.println("cart detail " + cartDetail);

			ArrayList<CartItemForm> cartItems = userService.showCart(cartDetail.getId());

			request.setAttribute("productsInCart", cartItems);
			System.out.println("productsInCart " + cartItems);
			return mapping.findForward("success");

		default:
			return mapping.findForward("error");
		}
	}
}
