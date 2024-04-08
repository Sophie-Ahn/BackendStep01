package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.bind.DataBinding;
import _64_Project_Add.dao.MemberDao;
import _64_Project_Add.vo.Member;

import java.util.Map;

@Component("/memberpAdd/update.do")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", _64_Project_Add.vo.Project.class,
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");

        if(member.getEmail() == null){
            Integer no = (Integer)model.get("no");
            Member detailInfo = memberDao.selectOne(no);
            model.put("member", detailInfo);
            return "/member/MemberUpdateForm.jsp";

        } else {
            memberDao.update(member);
            return "redirect:list.do";
        }
    }


}
