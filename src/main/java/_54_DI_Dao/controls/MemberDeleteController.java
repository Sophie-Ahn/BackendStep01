package _54_DI_Dao.controls;

import _54_DI_Dao.dao.MemberDao;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/memberDi/delete")
public class MemberDeleteController implements Controller {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }
    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
