package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authpAdd/logout.do")
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
