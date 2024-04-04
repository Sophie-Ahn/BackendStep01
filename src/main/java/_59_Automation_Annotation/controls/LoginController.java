package _59_Automation_Annotation.controls;

import _59_Automation_Annotation.annotation.Component;
import _59_Automation_Annotation.bind.DataBinding;
import _59_Automation_Annotation.dao.MemberDao;
import _59_Automation_Annotation.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authAppAno/login.do")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member loginInfo = (Member)model.get("loginInfo");
        if(loginInfo.getEmail() == null){
            System.out.println("LoginController::execute() - get 요청");
            return "/auth/LoginForm.jsp";
        } else {
            Member member = memberDao.exist(
                    loginInfo.getEmail(),
                    loginInfo.getPassword()
            );

            if(member != null){
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../memberAppAno/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
