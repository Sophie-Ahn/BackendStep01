package _60_Implement_Annotation.controls;

import _60_Implement_Annotation.annotation.Component;
import _60_Implement_Annotation.bind.DataBinding;
import _60_Implement_Annotation.dao.MemberDao;
import _60_Implement_Annotation.vo.Member;

import java.util.Map;

@Component("/memberImpAno/update.do")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", Member.class,
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
