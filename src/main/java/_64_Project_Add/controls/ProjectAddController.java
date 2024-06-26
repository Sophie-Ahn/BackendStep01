package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.bind.DataBinding;
import _64_Project_Add.dao.ProjectDao;
import _64_Project_Add.vo.Project;

import java.util.Map;

@Component("/projectpAdd/add.do")
public class ProjectAddController implements Controller, DataBinding {
    ProjectDao projectDao;
    public ProjectAddController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
        return this;
    }
    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "project", _64_Project_Add.vo.Project.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");
        if(project.getTitle() == null) {
            return "/project/ProjectForm.jsp";
        }else {
            projectDao.insert(project);
            return "redirect:list.do";
        }
    }
}
