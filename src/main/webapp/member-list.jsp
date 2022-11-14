<%@ page import="iducs201712014a.javaweb.fundmentals.model.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 서성준
  Date: 2022-11-14
  Time: 오후 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>멤버 출력</title>
</head>
<body>
<h1>
<%
    request.setCharacterEncoding("utf-8"); // 인코딩 설정

    List<Member> memberList = (List<Member>)request.getAttribute("list"); // list라는 속성이름의 값을 받아옴.

    for(Member member : memberList){
        out.print(member.getTname()+"<br>"); // 값을 하나씩 출력함.
    }

%>
</h1>


</body>
</html>
