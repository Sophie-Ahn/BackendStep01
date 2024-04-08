package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authpUpdate/logout.do")
public class LogoutController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        System.out.println("LogOutController::execute() - get 요청");

        HttpSession session = (HttpSession) model.get("session");
        session.removeAttribute("member");
        session.invalidate();

        return "redirect:login.do";
    }


}
