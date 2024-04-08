package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;
import _66_Projects_Update.bind.DataBinding;
import _66_Projects_Update.dao.ProjectDao;

import java.util.Map;

@Component("/projectpUpdate/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {

    ProjectDao projectDao;
    public ProjectDeleteController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
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

        projectDao.delete(no);

        return "redirect:list.do";
    }
}
