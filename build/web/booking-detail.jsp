<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.entity.Invoice" %>
<%@ page import="model.entity.Service" %>

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
                    <h2>Invoice Detail</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <%
        Invoice invoice = (Invoice) request.getAttribute("invoice");
        List<Service> services = invoice.getServiceList();
    %>
    <div class="invoice-info">
        <p>Invoice ID: <%= invoice.getInvoiceId() %></p>
        <p>Room ID: <%= invoice.getRoomId() %></p>
        <p>Customer ID: <%= invoice.getCustomerId() %></p>
        <p>Check In Date: <%= invoice.getCheckInDate() %></p>
        <p>Check Out Date: <%= invoice.getCheckOutDate() %></p>
        <p>Payment Method: <%= invoice.getPaymentMethod() %></p>
        <p>Note: <%= invoice.getNote() %></p>
    </div>

    <h3>Services</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <% if (services != null) { %>
        <% for (Service service : services) { %>
        <tr>
            <td><%= service.getName() %></td>
            <td>$<%= service.getPrice() %></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
    <h2 style="color: red">Total: $<%= invoice.getTotal() %></h2>
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
    .invoice-info {
        background-color: #f9f9f9;
        padding: 20px;
        margin-bottom: 20px;
        border-radius: 5px;
    }

    .services-list {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 20px;
    }

    .service-item {
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 5px;
    }
</style>
<script>

</script>
</html>