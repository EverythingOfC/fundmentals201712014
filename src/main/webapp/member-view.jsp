<%@ page import="iducs201712014a.javaweb.fundmentals.model.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body{
        line-height: 2em;
        margin:10px;
    }

</style>
<body>
  <h1>등록 정보</h1>
  <div>
<%
    MemberDTO member = (MemberDTO)request.getAttribute("member"); // member객체에 getAttribute속성의 속성 값인 member객체를 형변환하고 대입한다.
%>
  <%= member.getFullname() + "님! 등록에 성공하셨습니다. "%> <br/>
  당신의 이메일 주소는 <%= member.getEmail() + "입니다."%> <br/>
  당신의 연락처는 <%= member.getTel() + "입니다."%><br/>
  당신의 메시지는 <%= member.getMessage() + "입니다."%>

      <!-- EL(Expression Language): attribute 처리 전담 -->
      <!-- requestScope: EL 기본 객체, request: JSP 기본 객체 -->

      ${member.fullname} - ${requestScope.member.email}

      <!-- attribute 속성에 지정된 이름으로 사용한다. -->
  </div>
</body>
</html>
