package _60_Implement_Annotation.controls;

import _60_Implement_Annotation.annotation.Component;
import _60_Implement_Annotation.dao.MemberDao;

import java.util.Map;

@Component("/memberImpAno/list.do")
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
