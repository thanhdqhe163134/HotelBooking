<%@ page import="model.entity.Customer" %>
<%@ page import="model.entity.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>keto</title>
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
<!-- body -->
<body class="main-layout">
<!-- loader  -->
<div class="loader_bg">
    <div class="loader"><img src="images/loading.gif" alt="#"/></div>
</div>
<!-- end loader -->
<!-- header -->
<%@include file="header.jsp" %>
<!-- end header inner -->
<!-- end header -->

<div class="back_re">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="title">
                    <h2>Add new room</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<%
    session = request.getSession(false);
    String username = null;
    if (session != null) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            username = loggedInUser.getUsername(); // Lấy username từ Account
        }
    }
%>

<div class="container">
    <form action="create-room" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="roomType">Room Type</label>
            <select class="form-control" id="roomType" name="roomType" required>
                <option value="Standard">Standard</option>
                <option value="Normal">Normal</option>
                <option value="Superior">Superior</option>
                <option value="Vip">Vip</option>
            </select>
        </div>
        <div class="form-group">
            <label for="roomPrice">Room Price</label>
            <input type="number" class="form-control" id="roomPrice" name="roomPrice" required>
        </div>
        <div class="form-group">
            <label for="roomDescription">Room Description</label>
            <textarea class="form-control" id="roomDescription" name="roomDescription" required></textarea>
        </div>
        <div class="form-group">
            <label for="roomImage">Room Image</label>
            <input type="file" class="form-control-file" id="roomImage" name="roomImage" required>
        </div>
        <input type="hidden" name="username" value="<%=username%>">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<!--  footer -->
<%@include file="footer.jsp" %>
<!-- end footer -->
<!-- Javascript files-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.0.0.min.js"></script>
<!-- sidebar -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/custom.js"></script>
</body>
<style>

</style>
<script>

</script>
</html>