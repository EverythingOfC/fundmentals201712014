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
    }   // 생성자

    // BlogDAOImpl repository = null;  // dao
    Blog blog = null;   // DTO
    List<Blog> blogList = null; // List<DTO>

//    private static final String SAVE_DIR = "files";
//    private String partName = null;
//    private String partValue = null;

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        BlogDAOImpl dao = new BlogDAOImpl();    // 블로그 DAO

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blog/list.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환

        if (action.equals("post-form.do")) { // 블로그 작성 폼

            Member member = new Member();
            member.setEmail((String) session.getAttribute("logined"));
            member.setName((String) session.getAttribute("name"));

            request.setAttribute("loginedEmail", member.getEmail());
            request.setAttribute("loginedName", member.getName());

            request.getRequestDispatcher("../blogs/blog-post-form.jsp").forward(request, response);

        } else if (action.equals("post.do")) { // 블로그 작성
            Blog blog = new Blog();
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));
            // 데이터베이스 처리 요청

            if (dao.create(blog) > 0) { // 생성 성공 시
                request.setAttribute("blog", blog);
                request.setAttribute("message", "블로그 작성 성공");
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }

        } else if (action.equals("list.do")) {
            if ((blogList = dao.readList()) != null) {
                request.setAttribute("bloglist", blogList);
                request.getRequestDispatcher("../blogs/blog-list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 목록 가져오기 오류 - 불편을 드려 죄송합니다.");
                request.getRequestDispatcher("../errors/message.jsp").forward(request, response);
            }
        } else if (action.equals("read.do")) {
            Blog retBlog = null;
            blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            if ((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("../blogs/blog-read-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 읽기 오류 - 불편을 드려 죄송합니다.");
                request.getRequestDispatcher("../errors/message.jsp").forward(request, response);
            }
        } else if (action.equals("update.do")) {

            blog = new Blog();
            blog.setId(Long.parseLong((String) request.getAttribute("id")));
            blog.setTitle((String) request.getAttribute("title"));
            blog.setContent((String) request.getAttribute("content"));
            blog.setAuthor((String) request.getAttribute("author"));
            blog.setEmail((String) request.getAttribute("email"));

            if (dao.update(blog) > 0) {
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("../blogs/blog-read-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 업데이트 오류 - 불편을 드려 죄송합니다.");
                request.getRequestDispatcher("../errors/message.jsp").forward(request, response);

            }
        }
        else if(action.equals("delete.do")) {
            blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            if(dao.delete(blog) > 0) {
                request.getRequestDispatcher("list.do").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 업데이트 오류 - 불편을 드려 죄송합니다.");
                request.getRequestDispatcher("../error/message.jsp").forward(request, response);
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
