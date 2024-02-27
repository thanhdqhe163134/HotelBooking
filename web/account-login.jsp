<%--
  Created by IntelliJ IDEA.
  User: faced
  Date: 2/27/2024
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Keto | Login</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    </head>
    <style>
        body {
            background-color: #f2f2f2;
        }

        .login-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .login-container h2 {
            text-align: center;
            color: #007BFF;
            margin-bottom: 30px;
        }

        .login-container label {
            color: #007BFF;
        }

        .login-container button {
            background-color: #007BFF;
            color: #fff;
        }
        .custom-checkbox {
            display: inline-block;
            padding: 10px; /* Tùy chỉnh giá trị padding sao cho phù hợp */
            border-radius: 5px; /* Đường viền cong */
            cursor: pointer; /* Biến con trỏ thành bàn tay khi trỏ vào */
        }

    </style>
<body style="background: url('images/banner1.jpg') no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">
<div class="container">
    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="input-username">Username</label>
                <input type="text" class="form-control" id="input-username" name="username" required value="${username}">
            </div>
            <div class="form-group" style="margin-bottom: 0">
                <label for="input-password">Password</label>
                <input type="password" class="form-control" id="input-password" name="password" required style="margin-bottom: 0" value="${password}">
            </div>
            <input type="hidden" value="<%= request.getParameter("id") %>" name="id">
            <label class="custom-checkbox" style="padding-left: 0">
                <input type="checkbox" name="rememberMe" value="rememberMe">
                Remember me
            </label>
            <br>
            <button type="submit" class="btn btn-primary">Login</button>
            <br>
            <div class="text-center">
                <p>Don’t have an account? <a href="Register.jsp">Sign up</a></p>
            </div>
        </form>

    </div>
</div>
</body>
</html>
