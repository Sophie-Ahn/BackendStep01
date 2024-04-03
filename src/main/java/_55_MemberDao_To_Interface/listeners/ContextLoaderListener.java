package _55_MemberDao_To_Interface.listeners;

import _55_MemberDao_To_Interface.controls.*;
import _55_MemberDao_To_Interface.dao.MysqlMemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");
        try {
            ServletContext sc = sce.getServletContext();

            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup(
                    "java:comp/env/jdbc/studydb");

            MysqlMemberDao memberDao = new MysqlMemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("/authInterface/login.do", new LoginController().setMemberDao(memberDao));
            sc.setAttribute("/authInterface/logout.do", new LogoutController());
            sc.setAttribute("/memberInterface/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/memberInterface/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/memberInterface/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/memberInterface/delete.do", new MemberDeleteController().setMemberDao(memberDao));
//            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");

    }
}
