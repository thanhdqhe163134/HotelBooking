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
<br>
<a href="booking-list" class="btn btn-primary">Back to list</a>
<div class="container">
  <%
    Invoice invoice = (Invoice) request.getAttribute("invoice");
    List<Service> allServices = (List<Service>) request.getAttribute("services");
    List<Service> selectedServices = invoice.getServiceList();
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

  <form action="booking-update" method="post">
    <input type="hidden" name="invoiceId" value="<%= invoice.getInvoiceId() %>">
    <div class="form-group">
      <% for (Service service : allServices) { %>
      <div>
        <label for="quantity<%=service.getServiceId()%>"><%=service.getName()%></label>
        <input type="number" id="quantity<%=service.getServiceId()%>" name="quantity<%=service.getServiceId()%>" min="0" value="<%=selectedServices.stream().filter(s -> s.getServiceId() == service.getServiceId()).count()%>">
      </div>
      <% } %>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
  <form action="booking-update" method="post">
    <input type="hidden" name="invoiceId" value="<%= invoice.getInvoiceId() %>">
    <input type="hidden" name="status" value="<%="Checked out"%>">
    <button type="submit" class="btn btn-danger">Check out</button>
    </form>
  <%
    double serviceTotal = 0;
    for (Service service : allServices) {
      long quantity = selectedServices.stream().filter(s -> s.getServiceId() == service.getServiceId()).count();
      serviceTotal += quantity * service.getPrice();
    }
    double roomTotal = invoice.getTotal() - (int) serviceTotal;
    double paid =  (invoice.getTotal() - (int) serviceTotal)/2;
  %>
  <h5>Service total: $<%= serviceTotal %></h5>
    <h5>Room total: $<%= roomTotal %></h5>
    <h5>Paid amount: $<%= paid %></h5>
  <h5>Need to pay: $<%= roomTotal - paid %></h5>
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