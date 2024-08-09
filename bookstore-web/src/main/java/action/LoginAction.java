package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.UserForm;
import service.UserService;

public class LoginAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	

        HttpSession session = request.getSession();
        UserForm loginForm = (UserForm) form;
        String path = mapping.getPath();
        
        UserService userService = new UserService();

        switch (path) {
            case "/login":
                long loginSuccess = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());
                System.out.println(loginSuccess);
                if (loginSuccess != 0) {
                    session.setAttribute("user", new UserForm(loginSuccess, loginForm.getUsername()));
                    return mapping.findForward("success");
                } else {
                    return mapping.findForward("failure");
                }

            case "/logout":
                session.invalidate();
                return mapping.findForward("success");
        }
        return mapping.findForward("failure");
    }
}
