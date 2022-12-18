<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Clean Blog - Start Bootstrap Theme</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
          rel="stylesheet" type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet"/>
</head>
<body>
<!-- Navigation-->
<%@ include file="../main/nav.jsp"%>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../img/sky.png')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="page-heading">
                    <h1>상세 정보</h1>
                    <span class="subheading">Have questions? I have answers.</span>
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
                <p>Want to get in touch? Fill out the form below to send me a message and I will get back to you as soon
                    as possible!</p>
                <div class="my-5">
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- * * SB Forms Contact Form * *-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- This form is pre-integrated with1 SB Forms.-->
                    <!-- To make this form functional, sign up at-->
                    <!-- https://startbootstrap.com/solution/contact-forms-->
                    <!-- to get an API token!-->
                    <form id="contactForm" action="update-form.do" method="get">

                        <input type="hidden" name="id" value="${requestScope.blog.id}"/>

                        <div class="form-floating">
                            <input class="form-control" name="author" id="author" type="text"
                                   placeholder="Enter your author..." data-sb-validations="" readonly value="${requestScope.blog.author}"/>
                            <label for="author">작성자</label>
                            <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                        </div>

                        <div class="form-floating">
                            <input class="form-control" name="title" id="title" type="text"
                                   placeholder="Enter your title..." data-sb-validations="" readonly value="${requestScope.blog.title}"/>
                            <label for="title">제목</label>
                            <div class="invalid-feedback" data-sb-feedback="pw:required">A name is required.</div>
                        </div>

                        <div class="form-floating">
                            <textarea class="form-control" name="content" id="content" placeholder="Enter your message here..." data-sb-validations="required" readonly>${requestScope.blog.content}</textarea>
                            <label for="content">내용</label>
                            <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div>
                        </div>

                        <div class="form-floating">
                            <input class="form-control" name="email" id="email" type="email"
                                   placeholder="Enter your email..." data-sb-validations="email" readonly value="${requestScope.blog.email}"/>
                            <label for="email">이메일</label>
                            <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                            <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                        </div>

                        <div class="form-floating">
                            <input class="form-control" name="regdatetime" id="reg" type="text"
                                   data-sb-validations="email" readonly value="${requestScope.blog.regdatetime}"/>
                            <label for="reg">등록일</label>
                        </div>

                        <br/>

                        <!-- Submit success message-->
                        <!---->
                        <!-- This is what your users will see when the form-->
                        <!-- has successfully submitted-->
                        <div class="d-none" id="submitSuccessMessage">
                            <div class="text-center mb-3">
                                <div class="fw-bolder">Form submission successful!</div>
                                To activate this form, sign up at
                                <br/>
                                <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                            </div>
                        </div>
                        <!-- Submit error message-->
                        <!---->
                        <!-- This is what your users will see when there is-->
                        <!-- an error submitting the form-->
                        <div class="d-none" id="submitErrorMessage">
                            <div class="text-center text-danger mb-3">Error sending message!</div>
                        </div>

                    <c:if test="${requestScope.blog.email eq sessionScope.logined}"> <!-- 로그인한 사용자의 블로그인 경우에만 수정 및 삭제할 수 있게 함 . -->
                        <div style="text-align:center;" class="button">
                            <button style="font-size:0.8em;" class="btn btn-primary text-uppercase" type="submit">블로그 내용 수정 폼</button>
                            <button style="font-size:0.8em;" class="btn btn-primary text-uppercase" type="button" onclick="location.href='../blogs/delete.do?id=${requestScope.blog.id}'">블로그 내용 삭제</button>
                        </div>
                    </c:if>

                    </form>

                </div>
            </div>
        </div>
    </div>
</main>
<!-- Footer-->
<%@ include file="../main/footer.jsp"%>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="../js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
