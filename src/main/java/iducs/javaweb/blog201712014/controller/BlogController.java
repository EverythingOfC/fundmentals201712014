package iducs.javaweb.blog201712014.controller;

import iducs.javaweb.blog201712014.model.Blog;
import iducs.javaweb.blog201712014.model.Member;
import iducs.javaweb.blog201712014.repository.BlogDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "BlogController", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do",
        "/blogs/list.do", "/blogs/read.do",
        "/blogs/update-form.do", "/blogs/update.do", "/blogs/delete.do"})   // blog의 다양한 처리를 위한 url패턴

public class BlogController extends HttpServlet {   // BlogController

    private static final long serialVersionUID = 1L;
    public BlogController(){
        super();
    }   // 생성자

    // BlogDAOImpl repository = null;  // dao
    Blog blog = null;   // DTO
    List<Blog> blogList = null; // List<DTO>

    private static final String SAVE_DIR = "files";
    private String partName = null;
    private String partValue = null;

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
            member.setName((String) session.getAttribute("message"));

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
                response.sendRedirect("../status/success.jsp"); // 요청 정보를 새롭게 전달 ( 게시물이 반복 등록되면 안되므로)
            } else {
                request.setAttribute("message", "블로그 작성 실패");
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
        }else if(action.equals("update-form.do")) {

            blog = new Blog();

            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setAuthor(request.getParameter("author"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setEmail(request.getParameter("email"));
//            blog.setFilepath((String) request.getAttribute("filepath"));
            blog.setRegdatetime(Timestamp.valueOf(request.getParameter("regdatetime")));


            request.setAttribute("blog", blog);
            request.getRequestDispatcher("../blogs/blog-update-form.jsp").forward(request, response);

        }
        else if (action.equals("update.do")) {

//            String appPath = request.getServletContext().getRealPath("."); //
//            System.out.println(appPath);
//
//            String savePath = appPath + File.separator + SAVE_DIR;
//            System.out.println(appPath);
//
//            File fileSaveDir = new File(savePath);
//            if(!fileSaveDir.exists()) {
//                fileSaveDir.mkdir();
//            }
//
//            Collection<Part> parts = request.getParts();
//
//            for(Part part: parts){
//                partName = part.getName();  // 파라미터 이름 가져오기 ( 상품 )
//                if(part.getContentType() != null){
//
//                }
//                else{
//                    partValue = request.getParameter(partName);
//                }
//                request.setAttribute(partName,partValue);
//            }
            blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setAuthor(request.getParameter("author"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setEmail(request.getParameter("email"));
//            blog.setFilepath((String) request.getAttribute("filepath"));
            blog.setRegdatetime(Timestamp.valueOf(request.getParameter("regdatetime")));

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
