package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.OrderForm;
import form.PaymentForm;
import form.UserForm;
import service.UserService;

public class OrderAction extends Action {

	private UserService userService = new UserService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = mapping.getPath();
		HttpSession session = request.getSession();
		UserForm user = (UserForm) session.getAttribute("user");

		switch (path) {
		case "/confirm-order":
			return mapping.findForward("success");

		case "/payment":
			PaymentForm paymentForm = (PaymentForm) form;
			long paymentId = userService.pay(paymentForm);
			 session.setAttribute("paymentId", paymentId);
			return mapping.findForward("success");

		case "/send-order":
			OrderForm orderDetails = (OrderForm) form;
			orderDetails.setPaymentId((long) session.getAttribute("paymentId"));
			userService.sendOrder(orderDetails);
			return mapping.findForward("success");

		default:
			return mapping.findForward("error");
		}
	}
}
