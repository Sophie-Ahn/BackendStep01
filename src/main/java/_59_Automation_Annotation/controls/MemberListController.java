package _59_Automation_Annotation.controls;

import _59_Automation_Annotation.annotation.Component;
import _59_Automation_Annotation.dao.MemberDao;

import java.util.Map;

@Component("/memberAppAno/list.do")
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
