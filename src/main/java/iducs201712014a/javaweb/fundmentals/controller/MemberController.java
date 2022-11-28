package iducs201712014a.javaweb.fundmentals.controller;

import iducs201712014a.javaweb.fundmentals.model.Member;
import iducs201712014a.javaweb.fundmentals.model.MemberDTO;
import iducs201712014a.javaweb.fundmentals.repository.MemberDAO;
import iducs201712014a.javaweb.fundmentals.repository.MemberDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MemberController",
    urlPatterns = {"/members", "/member-create","/member-detail"}
)
        // form의 action값과 일치하면 해당 서블릿 호출
public class MemberController extends HttpServlet {

    MemberDAO memberDAOImpl = new MemberDAOImpl(); // MemberDAO 인터페이스의 다양한 구현 클래스를 대입할 수 있다.

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession(); // 세션 객체를 가져옴.
        String uri = request.getRequestURI(); // / 뒤의 URI만 얻어옴.
        String action = uri.substring(uri.lastIndexOf("/")+1); // 처음부터 마지막 /가 있는 인덱스의 위치까지 잘라온다.
        System.out.println(action);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 전체 목록 조회
        doProcess(request,response);
        List<Member> memberList = new ArrayList<Member>();

        if ((memberList = memberDAOImpl.readList()) != null){
            request.setAttribute("members",memberList);
            request.getRequestDispatcher("result.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("error.jsp").forward(request,response);
            response.sendRedirect("error.jsp");
        }

        // request.setAttribute("list",memberList);
        // RequestDispatcher rd = request.getRequestDispatcher("member-list.jsp");

        // rd.forward(request,response); // 포워드함수를 통해 제어를 이동한다.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);

        request.setCharacterEncoding("utf-8");
        int ret = 0;
        Member m = new Member();
        m.setEmail(request.getParameter("email"));
        m.setName(request.getParameter("name"));
        m.setPw(request.getParameter("pw"));
        m.setPhone(request.getParameter("tel"));
        m.setAddress(request.getParameter("address"));

        if((ret = memberDAOImpl.create(m)) > 0) { // 정상 처리
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("fail.jsp").forward(request,response);
        }
    }

}
