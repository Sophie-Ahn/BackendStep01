package _56_DataBinding.controls;

import _56_DataBinding.bind.DataBinding;
import _56_DataBinding.dao.MemberDao;

import java.util.Map;

@SuppressWarnings("serial")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }


}
