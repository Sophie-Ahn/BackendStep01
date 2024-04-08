package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.bind.DataBinding;
import _63_Projects_List.dao.MemberDao;

import java.util.Map;

@Component("/memberPl/delete.do")
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
        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
