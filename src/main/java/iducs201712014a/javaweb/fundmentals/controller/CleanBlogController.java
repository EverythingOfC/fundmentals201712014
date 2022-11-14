package iducs201712014a.javaweb.fundmentals.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

// form의 action = "contacts" 요청을 받는다. /는 webapp과 같은 lebel

@WebServlet(name = "CleanBlogController", value = "/contacts") // 자바 코드 안에서는 name으로 쓰고, url로는 value를 쓴다.
public class CleanBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 요청 정보를 읽어올 떄의 인코딩
        response.setContentType("text/html; charset=utf-8"); // 브라우저에 응답할 인코딩 지정

        // contact.jsp -> CleanBlogController -> result.jsp -> Web Browser
        request.getRequestDispatcher("result.jsp").forward(request,response); // 포워드 방식으로 데이터를 넘긴다.(페이지의 제어를 완전히 넘김.)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
