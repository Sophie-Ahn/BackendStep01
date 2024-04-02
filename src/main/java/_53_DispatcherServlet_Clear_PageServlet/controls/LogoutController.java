package _53_DispatcherServlet_Clear_PageServlet.controls;

import _53_DispatcherServlet_Clear_PageServlet.dao.MemberDao;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/authClearController/logout")
public class LogoutController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        // request 공간 대신 model로부터 꺼내어 사용한다.
        MemberDao memberDao = (MemberDao)model.get("memberDao");

        // request 공간 대신 model에 저장한다.
        model.put("members", memberDao.selectList());

        // redirect나 이동할 jsp 경로를 리턴한다.
        return "/member/MemberList.jsp";
    }


}
