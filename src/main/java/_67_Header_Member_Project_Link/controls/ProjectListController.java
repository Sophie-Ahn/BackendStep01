package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.dao.ProjectDao;
import _67_Header_Member_Project_Link.vo.Project;

import java.util.List;
import java.util.Map;

@Component("/projectpHeader/list.do")
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
