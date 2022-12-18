package iducs.javaweb.blog201712014.controller;

import iducs.javaweb.blog201712014.model.Member;
import iducs.javaweb.blog201712014.repository.MemberDAO;
import iducs.javaweb.blog201712014.repository.MemberDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MemberController",
    urlPatterns = {"/members/members", "/members/member-create","/members/member-detail",  // 해당 url패턴들에 요청이 전달된다.
            "/members/member-login" , "/members/member-delete","/members/member-logout",
            "/members/member-update","/members/member-search"}
)

        // form의 action값과 일치하면 해당 서블릿 호출
public class MemberController extends HttpServlet {

    MemberDAO memberDAOImpl = new MemberDAOImpl(); // MemberDAO 인터페이스의 다양한 구현 클래스를 대입할 수 있다.

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        request.setCharacterEncoding("utf-8"); // request객체의 인코딩을 설정, ISO-8859-1이 기본 인코딩

        HttpSession session = request.getSession(); // 세션 객체를 가져옴.
        String uri = request.getRequestURI(); // / 뒤의 URI만 얻어옴.
        String action = uri.substring(uri.lastIndexOf("/")+1); // 처음부터 마지막 /가 있는 인덱스의 위치까지 잘라온다.

        System.out.println(action);

        if(action.equals("members")){  // 회원관리

            String sort = request.getParameter("sort"); // 정렬 방식을 받아옴.

            List<Member> memberList = new ArrayList<Member>();

            if(sort==null) { // 정렬 방식이 선택되지 않았다면

                if ((memberList = memberDAOImpl.readList()) != null) {
                    request.setAttribute("members", memberList);
                    request.getRequestDispatcher("../members/member-result.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                }
            }

            else{   // 정렬 방식이 선택됐다면

                if ((memberList = memberDAOImpl.sortList(sort)) != null) {

                    request.setAttribute("members", memberList);
                    session.setAttribute("sort",sort); // 정렬방식 저장

                    request.getRequestDispatcher("../members/member-result.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                }

            }
        }
        else if(action.equals("member-search")){

        }
        else if(action.equals("member-create")){  // 회원가입

            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setName(request.getParameter("name"));
            m.setPw(request.getParameter("pw"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));

            if((ret = memberDAOImpl.create(m)) > 0) { // 정상 처리
                request.setAttribute("retMember",m);
                request.getRequestDispatcher("../status/create_success.jsp").forward(request,response);
            }
            else{
                request.getRequestDispatcher("../status/create_fail.jsp").forward(request,response);
            }

        }
        else if(action.equals("member-detail")){ // 회원 상세정보

            String email = request.getParameter("email");

            Member member = new Member();

            member.setEmail(email); // 프로퍼티 초기화

            Member retMember = null; // 상세 정보를 가져오기 위함.

            if((retMember = memberDAOImpl.read(member)) != null){ // 일치하는 회원정보를 읽음.
                request.getRequestDispatcher("../members/member-detail-form.jsp").forward(request,response);
            }
            else{                                                 // 못 읽었을 시
                request.getRequestDispatcher("../status/fail.jsp").forward(request,response);
            }



        }else if(action.equals("member-delete")){ // 회원 삭제

            String pw = request.getParameter("pw");

            Member member = new Member();

            member.setPw(pw);   // 프로퍼티 초기화

            memberDAOImpl.delete(member); // 회원 삭제 ( 일치하지않으면 어차피 삭제 안됨.)

            session.invalidate();
            request.getRequestDispatcher("../main/index.jsp").forward(request,response);

        }

        else if(action.equals("member-login")){ // 로그인

            String email = request.getParameter("email");
            String pw = request.getParameter("pw");

            Member member = new Member();

            member.setEmail(email); // 프로퍼티 초기화
            member.setPw(pw);   // 프로퍼티 초기화

            Member retMember = null; // 멤버를 반환하기 위한 객체 생성

            if((retMember = memberDAOImpl.login(member)) != null){ // 초기화된 멤버 클래스의 필드 2개가 객체에 담겨서 넘어감.

                session.setAttribute("logined",retMember.getEmail()); // 로그인 상태 유지
                session.setAttribute("message",retMember.getName());
                request.getRequestDispatcher("../main/index.jsp").forward(request,response);
            }
            else{                                                 // 로그인 실패
                request.setAttribute("message","로그인에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request,response);
            }
        }

        else if(action.equals("member-logout")){ // 로그아웃
            session.invalidate(); // 세션 제거
            request.getRequestDispatcher("../main/index.jsp").forward(request,response); // 메인 페이지로 이동
        }

        else if(action.equals("member-update")){ // 회원수정

            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setName(request.getParameter("name"));
            m.setPw(request.getParameter("pw"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));

            if((ret = memberDAOImpl.update(m)) > 0) { // 정상 처리

                session.setAttribute("myMember",m); // 회원 정보 갱신
                request.getRequestDispatcher("../members/member-detail-form.jsp").forward(request,response);
            }
            else{
                request.getRequestDispatcher("fail.jsp").forward(request,response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
