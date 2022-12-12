package iducs.javaweb.blog201712014.controller;

import iducs.javaweb.blog201712014.model.Blog;
import iducs.javaweb.blog201712014.model.Member;
import iducs.javaweb.blog201712014.repository.BlogDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogController", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do",
        "/blogs/list.do", "/blogs/detail.do",
        "/blogs/update-form.do", "/blogs/update.do", "/blogs/delete.do"})   // blog의 다양한 처리를 위한 url패턴

public class BlogController extends HttpServlet {   // BlogController

    private static final long serialVersionUID = 1L;
    public BlogController(){
        super();
    }

    BlogDAOImpl repository = null;  // DAO
    Blog blog = null;   // DTO
    List<Blog> blogList = null;


    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();   // uri를 얻어옴.
        String contextPath = request.getContextPath();  // 컨텍스트 페스를 얻어옴. uri에서 컨텍스트 패스의 길이 + 1 부터 command에 저장
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blog/list.do가 반환됨

        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환


        BlogDAOImpl dao = new BlogDAOImpl();    // 블로그 DB 접근 객체

        if(action.equals("post-form.do")) { // 블로그 작성 폼

            Member member = new Member();
            member.setEmail((String) session.getAttribute("logined"));
            member.setName((String) session.getAttribute("name"));

            request.setAttribute("loginedEmail", member.getEmail());
            request.setAttribute("loginedName", member.getName());

            request.getRequestDispatcher("../blogs/blog-post-form.jsp").forward(request, response);

        }else if(action.equals("post.do")){ // 블로그 작성
            Blog blog = new Blog();
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));
            // 데이터베이스 처리 요청

            if(dao.create(blog)>0){ // 생성 성공 시
                request.setAttribute("blog",blog);
                request.setAttribute("message","블로그 작성 성공");
                request.getRequestDispatcher("../status/create_success.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("../status/create_fail.jsp").forward(request,response);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request,response);
    }
}
