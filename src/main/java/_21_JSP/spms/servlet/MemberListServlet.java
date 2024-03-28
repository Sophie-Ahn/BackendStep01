package _21_JSP.spms.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*get요청은 소스에 하지 않고 tomcat xml에 저장
* 파라미터에 꺄내기 전에 해야함
* 이 설정을 안해주면 한글이 깨져서 저장됨
* 이 설정을 추가하로 해줄 것*/
@SuppressWarnings("serial")
@WebServlet("/memberJSP/list")
public class MemberListServlet extends HttpServlet {
    Connection conn = null; // DB 서버와의 연결 객체
    Statement stmt = null; // SQL문
    ResultSet rs = null; // Select문의 결과 정보
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MemberListServlet::doGet() 호출");
        try{
            // 메모리에 클래스 로딩
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")
            );
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "SELECT mno, mname,email, cre_date FROM members" +
                            " ORDER BY mno ASC"
            );

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>회원목록</title></head>");
            out.println("<body><h1>회원목록</h1></body>");
            out.println("<p><a href='add'>신규회원</a></p>");
            while (rs.next()){
                out.println(
                        rs.getInt("mno") + ", " +
                                "<a href='update?no=" + rs.getInt("mno") +"'>"+
                                rs.getString("mname") + "</a>, " +
                                rs.getString("email") + ", " +
                                rs.getDate("cre_date") + ", " +
                                "<a href='delete?no=" + rs.getInt("mno") +"'>[삭제]</a>, " + "<br>"
                );
            }
            out.println("</body></html>");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
