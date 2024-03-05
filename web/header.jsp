<%@ page import="model.entity.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
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
  <script src="js/custom.js"></script>
</head>
<!-- body -->
<body class="main-layout">
<!-- loader  -->
<div class="loader_bg">
  <div class="loader"><img src="images/loading.gif" alt="#"/></div>
</div>
<!-- end loader -->
<!-- header -->
<header>
  <%
    session = request.getSession(false);
    String role = null;
    if (session != null) {
      Account loggedInUser = (Account) session.getAttribute("loggedInUser");
      if (loggedInUser != null) {
        role = loggedInUser.getRole(); // Lấy role từ Account
      }
    }

    boolean isAdmin = "admin".equals(role); // Kiểm tra xem role có phải là admin không



  %>
  <!-- header inner -->
  <div class="header">
    <div class="container">
      <div class="row">
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
          <div class="full">
            <div class="center-desk">
              <div class="logo">
                <a href="home.jsp"><img src="images/logo.png" alt="#" /></a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
          <nav class="navigation navbar navbar-expand-md navbar-dark ">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarsExample04">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item <%=request.getServletPath().contains("/home") ? "active" : ""%>">
                  <a class="nav-link" href="home">Home</a>
                </li>
                <li class="nav-item <%=request.getServletPath().contains("/room-list") ? "active" : ""%>">
                  <a class="nav-link" href="room-list">Rooms</a>
                </li>
                <li class="nav-item <%=request.getServletPath().contains("/about") ? "active" : ""%>">
                  <a class="nav-link" href="about">About</a>
                </li>
                <li class="nav-item <%=request.getServletPath().contains("/gallery") ? "active" : ""%>">
                  <a class="nav-link" href="gallery">Gallery</a>
                </li>
                <li class="nav-item <%=request.getServletPath().contains("/contact") ? "active" : ""%>">
                  <a class="nav-link" href="contact">Contact</a>
                </li>
                <% if (request.getSession().getAttribute("loggedInUser") == null) { %>
                <li class="nav-item <%=request.getServletPath().contains("/login") ? "active" : ""%>">
                  <a class="nav-link" href="login">Login</a>
                </li>
                <% } else { %>
                <li class="nav-item <%=request.getServletPath().contains("/profile") ? "active" : ""%>">
                  <a class="nav-link" href="profile">Profile</a>
                </li>

                <% if(isAdmin) { %>
                <li class="nav-item <%=request.getServletPath().contains("/booking-list") ? "active" : ""%>">
                  <a class="nav-link" href="booking-list">Booking</a>
                <% } else {%>
                <li class="nav-item <%=request.getServletPath().contains("/booked-list") ? "active" : ""%>">
                  <a class="nav-link" href="booked-list">Booked</a>
                </li>
                <% } %>
                <li class="nav-item">
                  <a class="nav-link" href="logout">Logout</a>
                </li>
                <% } %>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </div>
  </div>
</header>
</body>
<style>

</style>

</html>

