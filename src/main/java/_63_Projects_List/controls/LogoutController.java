package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.dao.MemberDao;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authPl/logout.do")
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
