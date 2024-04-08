package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;
import _66_Projects_Update.dao.MemberDao;

import java.util.Map;

@Component("/memberpUpdate/list.do")
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
