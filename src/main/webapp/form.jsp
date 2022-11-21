<%--
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-24
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>

        #check{
            cursor:pointer;
        }

        #login{
            position:absolute;
            top:10%;
            left:25%;
            width: 40%;
            font-size: 2em;
        }
        fieldset {
            border: 2px solid #0dcaf0;
            text-align: center;
            font-weight: bold;
        }
        body {
            margin: 20px;
        }
        input {
            margin: 10px;
            padding: 10px;
            font-weight: bold;
            font-size:0.3em;
        }
        input[type=submit]{
            border-radius:10px;
            font-size:0.6em;
        }

        label {
            font-size:0.6em;
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>

<form id="login" action="members" method="post"> <!-- member-list로 url 매핑된 이름이 있는 서블릿 파일로 간다.경로는 중요하지 않다. 서블릿의 value와 관련이 있다. -->

    <fieldset>
        <legend>회원 가입</legend>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" value="" placeholder=" 1 ~ 10자 이내로 입력하세요."><br/>

        <label for="pw">비밀번호:</label>
        <input type="text" id="pw" name="pw" value="" placeholder=" 1 ~ 10자 이내로 입력하세요."><br/>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" placeholder="유효한 이메일을 입력하세요."><br/>

        <label for="tel">연락처:</label>
        <input type="tel" id="tel" name="tel" placeholder="'-'를 제외하고 입력하세요."><br/>

        <label for="address">주소: </label>
        <input type="text" id="address" name="address"><br/>

        <input type="submit" id="check" value="확인"/>
    </fieldset>
</form>

</body>
</html>
