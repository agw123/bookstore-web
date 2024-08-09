package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PaymentForm;
import service.UserService;

public class PaymentAction extends Action {

    private UserService userService = new UserService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        PaymentForm paymentForm = (PaymentForm) form;
        long paymentId = userService.pay(paymentForm);
        request.setAttribute("paymentId", paymentId);
        return mapping.findForward("success");
    }
}

