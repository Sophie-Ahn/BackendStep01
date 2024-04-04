package _56_DataBinding.controls;

import _56_DataBinding.bind.DataBinding;
import _56_DataBinding.dao.MemberDao;
import _56_DataBinding.vo.Member;

import java.util.Map;

@SuppressWarnings("serial")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", _56_DataBinding.vo.Member.class,
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        if (model.get("member") == null) {
            Integer no = (Integer)model.get("no");
            Member member = memberDao.selectOne(no);
            model.put("member", member);

            return "/member/MemberUpdateForm.jsp";
        } else {
            Member member = (Member)model.get("member");
            memberDao.update(member);

            return "redirect:list.do";
        }
    }


}
