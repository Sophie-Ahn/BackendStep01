package _54_DI_Dao.controls;

import _54_DI_Dao.dao.MemberDao;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/memberDi/list")
public class MemberListController implements Controller {

    MemberDao memberDao;

    public MemberListController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        System.out.println("MemberListController::execute() 호출");

//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
