package _62_Projects_Dao.controls;

import _62_Projects_Dao.annotation.Component;
import _62_Projects_Dao.bind.DataBinding;
import _62_Projects_Dao.dao.MemberDao;
import _62_Projects_Dao.vo.Member;

import java.util.Map;

@Component("/memberPd/update.do")
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
