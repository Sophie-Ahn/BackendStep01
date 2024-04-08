package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.dao.ProjectDao;
import _64_Project_Add.vo.Project;

import java.util.List;
import java.util.Map;

@Component("/projectpAdd/list.do")
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