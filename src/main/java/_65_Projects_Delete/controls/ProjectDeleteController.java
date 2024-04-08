package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.bind.DataBinding;
import _65_Projects_Delete.dao.ProjectDao;

import java.util.Map;

@Component("/projectpDelete/delete.do")
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
