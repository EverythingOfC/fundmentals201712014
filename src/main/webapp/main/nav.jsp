<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-31
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="index.jsp">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">

                <li class="nav-item"><a style="color:black;" class="nav-link px-lg-3 py-3 py-lg-4"
                                        href="../main/index.jsp">메인 화면</a></li>


                <c:set var="member" scope="session" value="${myMember}"/> <!-- retMember속성의 값으로 member변수를 세팅 -->


                <c:if test="${member==null}"> <!-- 로그인 전 -->
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
                                            href="../members/member-login-form.jsp">로그인</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
                                            href="../members/member-create-form.jsp">회원가입</a></li>
                </c:if>


                <!-- 관리자 모드 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/members">회원 관리</a></li>

                <!-- 로그인 후 -->

                <c:if test="${member!=null}">

                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
                                            href="../members/member-detail-form.jsp?email=${member.email}">상세정보</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
                                            href="../members/member-update-form.jsp?email=${member.email}">회원수정</a></li>

                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-delete-form.jsp">회원탈퇴</a>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-logout">로그아웃</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>