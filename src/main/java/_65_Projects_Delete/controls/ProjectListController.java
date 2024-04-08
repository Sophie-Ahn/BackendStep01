package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.dao.ProjectDao;
import _65_Projects_Delete.vo.Project;

import java.util.List;
import java.util.Map;

@Component("/projectpDelete/list.do")
public class ProjectListController implements Controller {

    ProjectDao projectDao;

    public ProjectListController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        List<Project> projects = projectDao.selectList();

        System.out.println(projects);

        model.put("projects", projects);

        return "/project/ProjectList.jsp";
    }
}
