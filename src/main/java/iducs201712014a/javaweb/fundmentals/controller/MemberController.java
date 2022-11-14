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


@WebServlet(name = "MemberController", value = "/member-list") // form의 action값과 일치하면 해당 서블릿 호출
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO memberDAOImpl = new MemberDAOImpl(); // MemberDAO 인터페이스의 다양한 구현 클래스를 대입할 수 있다.
        List<Member> memberList = memberDAOImpl.readList();

        for(Member a : memberList){
            System.out.println(a.getTname());
        }

        request.setAttribute("list",memberList);
        request.getRequestDispatcher("member-list.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
