package _41_ConnectionPool.listeners;

import _41_ConnectionPool.dao.MemberDao;
import _41_ConnectionPool.util.DBConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

/* 웹 어플리케이션이 실행되었을 때 자동으로 호출되는 클래스
* ServletContext 영역이 준비되었습니다...
*
* @WebListener를 안쓰면 web.xml에 따로 작성을 해야함
* */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    Connection conn;
    DBConnectionPool connPool;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");
        try {
            ServletContext sc = sce.getServletContext();
            connPool = new DBConnectionPool(
                    sc.getInitParameter("driver"),
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")
            );
            MemberDao memberDao = new MemberDao();
            memberDao.setDBConnectionPool(connPool);

//            Class.forName(sc.getInitParameter("driver"));
//            conn = DriverManager.getConnection(
//                    sc.getInitParameter("url"),
//                    sc.getInitParameter("username"),
//                    sc.getInitParameter("password")
//            );
//            MemberDao memberDao = new MemberDao();
//            memberDao.setConnection(conn);

            // connection객체대신 memberDao객체를 저장
            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");
        try{
            connPool.closeAll();
//            if(conn != null && conn.isClosed() == false){
//                conn.close();
//            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}