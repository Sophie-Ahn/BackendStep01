package _34_EL.servlet;

import _34_EL.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/memberel/list")
public class MemberListServlet extends HttpServlet {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            // ServletContext영역에 conn이라는 이름으로 저장된 객체를 꺼내서 사용
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
//            Class.forName(sc.getInitParameter("driver"));
//            conn = DriverManager.getConnection(
//                    sc.getInitParameter("url"),
//                    sc.getInitParameter("username"),
//                    sc.getInitParameter("password")
//            );
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
//            rs = null; // 예외를 발생하기 위해서 일부러 null값 넣음
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
            resp.setContentType("text/html;charset=UTF-8");
            /*서블릿이나 jsp에서 다른 서블릿/jsp로 이동하는방법
                    1) redirect: 브라우저한테 다른 주소로 새로 접속해 request를 공유할 수 없다.(기존 request를 가지고 요청하는 것이 아닌, 새로운 요청이기 때문에)
                                브라우저의 url창은 기존 주소로 접속했다가, 새로운 주소로 표시된다.
                    2) 기존 request을 전달하는 요청: 기존 request가 아직 브라우저에 응답으로 가지않고, 서버 내부에서 경로 이동만 하는 방식
                        * 브라우저가 처음 접속한 주소가 브라우저에 그대로 표현되는 것
                        (다만 서버 내부에서는 경로를 이동)
                        a) include : 일시 호출하고 다시 원래 호출한 곳으로 제어권이 넘어온다.
                                    MemberListServlet -> MemberList.jsp -> MemberListServlet 응답 -> Browser
                        b) foward : 호출과 동시에 브라우저에 응답 제어권을 넘긴다.
                                    MemberListServlet -> MemberList.jsp -> Browser
                    */
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
//            throw new ServletException(e);
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
            // ServletContext에 공유하는 Connection는 닫으면 다른 곳에서 사용 못한다.
//            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
