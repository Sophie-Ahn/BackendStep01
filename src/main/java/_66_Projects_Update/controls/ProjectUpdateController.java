package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;
import _66_Projects_Update.bind.DataBinding;
import _66_Projects_Update.dao.ProjectDao;
import _66_Projects_Update.vo.Project;

import java.util.Map;

@Component("/projectpUpdate/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectUpdateController setMemberDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "project", Project.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");

        if(project.getTitle()==null) {
            Integer no = (Integer)model.get("no");
            Project detailInfo = projectDao.selectOne(no);
            model.put("project", detailInfo);
            return "/project/ProjectUpdateForm.jsp";

        }else {
            projectDao.update(project);
            return "redirect:list.do";
        }
    }
}
