package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.bind.DataBinding;
import _63_Projects_List.dao.MemberDao;
import _63_Projects_List.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authPl/login.do")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", _63_Projects_List.vo.Member.class
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
                return "redirect:../memberPl/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
