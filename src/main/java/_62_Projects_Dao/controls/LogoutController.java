package _62_Projects_Dao.controls;

import _62_Projects_Dao.annotation.Component;
import _62_Projects_Dao.dao.MemberDao;

import java.util.Map;

@Component("/authPd/logout.do")
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
