package _62_Projects_Dao.controls;

import _62_Projects_Dao.annotation.Component;
import _62_Projects_Dao.dao.MemberDao;

import java.util.Map;

@Component("/memberPd/list.do")
public class MemberListController implements Controller {

    MemberDao memberDao;

    public MemberListController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
