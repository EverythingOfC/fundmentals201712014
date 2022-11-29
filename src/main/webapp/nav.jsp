<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-31
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="index.jsp"><%= request.getParameter("name")%>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <!-- 로그인 전 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-signin-form.jsp">로그인</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-create-form.jsp">회원가입</a>
                </li>

                <!-- 관리자 모드 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="members">회원 관리</a></li>

                <!-- 로그인 후 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-detail?email=${login.email}">Detail</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-update-form.jsp?email=${login.email}">회원수정</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-delete-form.jsp">회원탈퇴</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member-signout-form.jsp">로그아웃</a></li>
            </ul>
        </div>
    </div>
</nav>
