package _62_Projects_Dao.controls;

import _62_Projects_Dao.annotation.Component;
import _62_Projects_Dao.bind.DataBinding;
import _62_Projects_Dao.dao.MemberDao;

import java.util.Map;

@Component("/memberPd/delete.do")
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
