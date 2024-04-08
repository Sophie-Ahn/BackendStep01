package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.bind.DataBinding;
import _65_Projects_Delete.dao.MemberDao;
import _65_Projects_Delete.vo.Member;
import _65_Projects_Delete.vo.Project;

import java.util.Map;

@Component("/memberpDelete/update.do")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", _65_Projects_Delete.vo.Member.class,
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
