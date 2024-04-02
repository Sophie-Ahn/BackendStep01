package _54_DI_Dao.listeners;

import _54_DI_Dao.controls.*;
import _54_DI_Dao.dao.MemberDao;
import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* 웹 어플리케이션이 실행되었을 때 자동으로 호출되는 클래스
* ServletContext 영역이 준비되었습니다...
*
* @WebListener를 안쓰면 web.xml에 따로 작성을 해야함
* */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {
//    Connection conn;
//    DBConnectionPool connPool;
    BasicDataSource ds;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");
        try {
            ServletContext sc = sce.getServletContext();
            ds = new BasicDataSource();
            ds.setDriverClassName(sc.getInitParameter("driver"));
            ds.setUrl(sc.getInitParameter("url"));
            ds.setUsername(sc.getInitParameter("username"));
            ds.setPassword(sc.getInitParameter("password"));

            MemberDao memberDao = new MemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("/authDi/login.do", new LoginController().setMemberDao(memberDao));
            sc.setAttribute("/authDi/logout.do", new LogoutController());
            sc.setAttribute("/memberDi/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/memberDi/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/memberDi/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/memberDi/delete.do", new MemberDeleteController().setMemberDao(memberDao));
//            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");
        try{
            if(ds != null) {
                ds.close();
            }
//            connPool.closeAll();
//            if(conn != null && conn.isClosed() == false){
//                conn.close();
//            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
