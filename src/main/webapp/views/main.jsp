<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-image: url("/img/Airplane.png");
            background-size: cover;
            background-repeat: no-repeat;
        }

        .overlay {
            position: absolute;
            top: 10%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(0, 0, 0, 0.25);
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            font-size: 60px; /* 수정 */
        }

        .overlay span {
            font-size: 70px; /* 수정 */
        }

        .menu {
            position: absolute;
            top: 200px; /* 수정 */
            left: 50%;
            transform: translateX(-50%);
            text-align: center;
        }

        .menu a {
            display: block;
            margin-bottom: 10px;
            text-align: center;
            font-size: 18px;
            color: white;
            text-decoration: none;
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px 20px;
            border-radius: 5px;
            width: 300px;
        }
    </style>
</head>
<body>
<<<<<<< HEAD
<div class="overlay">
    <span>비행기 예약</span>
</div>
<div class="menu">
    <% boolean isLogin = false;
        String loginID = "";
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID")) {
                isLogin = true;
                loginID = cookies[i].getValue();
            }
        }
    %>

<%if (isLogin) { %>
    <a href="/reservation">예약</a>
    <br>
    <a href="/ticket">티켓</a>
    <br>
    <% } else if (isLogin == true && loginID.isEmpty() == false && loginID.equals("admin")) { %>
    <a href="/edit">개발자 페이지</a>
    <% } else if (isLogin == false) { %>
    <a href="/login">로그인</a>
    <br>
    <a href="/signup">회원가입</a>
    <% } %>
</div>
</html>
