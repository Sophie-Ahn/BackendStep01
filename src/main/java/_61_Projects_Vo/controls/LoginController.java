package _61_Projects_Vo.controls;

import _61_Projects_Vo.annotation.Component;
import _61_Projects_Vo.bind.DataBinding;
import _61_Projects_Vo.dao.MemberDao;
import _61_Projects_Vo.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authPv/login.do")
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
                return "redirect:../memberPv/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
