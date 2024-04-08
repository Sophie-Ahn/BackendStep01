package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.dao.MemberDao;

import java.util.Map;

@Component("/memberpAdd/list.do")
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
