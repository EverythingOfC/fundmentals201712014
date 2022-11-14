<%-- JSP 주석(comment)
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-31
  Time: 오후 2:11
  To change this template use File | Settings | File Templates.

  <%@: 지시자, <%: 스크립트 릿, <%= : 표현식 , <%!: 선언문 , <%--: 주석
  pageEncoding: 읽을 때
  contentType: 쓸 때
  page 지시자

--%>
<!-- HTML 주석 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<%
  // 자바 문법에 따라 작성함. JSP 기본 객체를 사용할 수 있음
  String name = new String("인덕");
  String param = request.getParameter("iducs");
  out.println("스크립트릿(scriptlet)" + param );

  String[] arrStr = {"인덕","컴소","Java","Web","2A"};

  request.setAttribute("attr",arrStr);

  for(String str : arrStr){
      out.print("<h1>" + str + "</h1>");
  }


%>
<%= "표현식 (Expression) : 스크립트릿에서 출력 편리성 향상 " + param %>

<c:out value="EL & JSTL 활용"/>

<c:forEach items = "${attr}" var = "item">
  <img src="/img/post-sample-image.jpg"/>
  <h2>${item}</h2>
</c:forEach>

</body>
</html>
