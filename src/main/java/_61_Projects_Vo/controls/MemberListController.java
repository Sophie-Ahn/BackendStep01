package _61_Projects_Vo.controls;

import _61_Projects_Vo.annotation.Component;
import _61_Projects_Vo.dao.MemberDao;

import java.util.Map;

@Component("/memberPv/list.do")
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
