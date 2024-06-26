package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.dao.MemberDao;

import java.util.Map;

@Component("/memberPl/list.do")
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
