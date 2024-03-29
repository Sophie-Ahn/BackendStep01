package _24_MemberListServlet_Divide_View.servlet;

import _24_MemberListServlet_Divide_View.vo.Member;

import javax.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/memberView/list")
public class MemberListServlet extends HttpServlet {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")
            );
            stmt = conn.createStatement();
            // DB 테이블 전체 리스트를 가져와서 rs에 저장한 상태
            rs = stmt.executeQuery(
                    "SELECT mno, mname,email, cre_date FROM members" +
                            " ORDER BY mno ASC"
            );
            /*
            * 회원목록을 list객체로 생성
            * MemberList.jsp를 호출하면서 list객체를 전달
            */
            List<Member> members = new ArrayList<>();
            while (rs.next()){
                members.add(new Member()
                        .setNo(rs.getInt("mno"))
                        .setName(rs.getString("mname"))
                        .setEmail(rs.getString("email"))
                        .setCreateDate(rs.getDate("cre_date"))
                );
            }

            // request 공간에 members라는 Key값으로 list를 저장한다.
            // 그러면 MemberList.jsp에서는 members라는 Key값으로 꺼낼 수 있다.
            req.setAttribute("members", members);

            // MemberListServlet객체에서 MemberList.jsp로
            // request객체와 response객체를 전달한다.
            RequestDispatcher rd = req.getRequestDispatcher(
                    "/member/MemberList.jsp"
            );

            // include방식으로 전달한다.
            rd.include(req, resp);

//            html코드와 html코드에 java값을 전달하는 부분
//            resp.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = resp.getWriter();
//            out.println("<html><head><title>회원목록</title></head>");
//            out.println("<body><h1>회원목록</h1></body>");
//            out.println("<p><a href='add'>신규회원</a></p>");
//            while (rs.next()){
//                out.println(
//                        rs.getInt("mno") + ", " +
//                                "<a href='update?no=" + rs.getInt("mno") +"'>"+
//                                rs.getString("mname") + "</a>, " +
//                                rs.getString("email") + ", " +
//                                rs.getDate("cre_date") + ", " +
//                                "<a href='delete?no=" + rs.getInt("mno") +"'>[삭제]</a>, " + "<br>"
//                );
//            }
//            out.println("</body></html>");
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
