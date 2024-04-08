package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.bind.DataBinding;
import _65_Projects_Delete.dao.MemberDao;

import java.util.Map;

@Component("/memberpDelete/delete.do")
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
