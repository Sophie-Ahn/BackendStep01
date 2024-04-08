package _61_Projects_Vo.controls;

import _61_Projects_Vo.annotation.Component;
import _61_Projects_Vo.bind.DataBinding;
import _61_Projects_Vo.dao.MemberDao;

import java.util.Map;

@Component("/memberPv/delete.do")
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
