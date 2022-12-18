<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet" />
</head>
<style>

    .page-heading table{
        width:700px;
        margin-top:4vw;
    }
    .page-heading table td,.page-heading table th {
        padding:10px;
        border:2px solid black;
        border-collapse: collapse;
        text-align:left;
        font-size:0.7em;
    }
    .page-heading table th{
        text-align:center;
        color:black;
        font-size:1em;
    }
    .page-heading table td a:hover{
        color:red;
    }
    a{
        text-decoration: none;
        color:white;
    }

</style>
<body>
<!-- Navigation-->
<%@ include file="../main/nav.jsp"%>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../img/sky.png');">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="page-heading">
                    <h1>블로그 전체 목록</h1>
                    <table>
                        <tr>
                            <th>작성자</th>
                            <th>제목</th>
                            <th>이메일</th>
                            <th>등록일</th>
                        </tr>
                   <c:forEach var="b" items="${bloglist}">
                       <tr>
                           <td><a href="../blogs/read.do?id=${b.id}">${b.author}</a></td>
                           <td>${b.title}</td>
                           <td>${b.email}</td>
                       <td><fmt:formatDate value="${b.regdatetime}" pattern="yyyy년 M월 d일" type="date"/></td>
                       </tr>
                   </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<main class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe nostrum ullam eveniet pariatur voluptates odit, fuga atque ea nobis sit soluta odio, adipisci quas excepturi maxime quae totam ducimus consectetur?</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius praesentium recusandae illo eaque architecto error, repellendus iusto reprehenderit, doloribus, minus sunt. Numquam at quae voluptatum in officia voluptas voluptatibus, minus!</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut consequuntur magnam, excepturi aliquid ex itaque esse est vero natus quae optio aperiam soluta voluptatibus corporis atque iste neque sit tempora!</p>
            </div>
        </div>
    </div>
</main>
<!-- Footer-->
<%@ include file="../main/footer.jsp"%>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
