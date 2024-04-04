package _57_Automation_FrontController.controls;

import _57_Automation_FrontController.dao.MemberDao;

import java.util.Map;

@SuppressWarnings("serial")
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
