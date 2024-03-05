<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.entity.Invoice" %>

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
          <h2>Booked Room</h2>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="titlepage">
        <div class="filter-group">
          <select id="Sort" class="form-control filter-select" onchange="sort()">
            <option value="InvoiceID">Sort by ID</option>
            <option value="CheckInDate">Sort by Date</option>
            <option value="PaymentMethod">Sort by Total</option>
            <option value="paymentMethod">Sort by Payment Method</option>
            <option value="Note">Sort by Paid</option>
          </select>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Invoice ID</th>
          <th>Room ID</th>
          <th>Customer ID</th>
          <th>Check In Date</th>
          <th>Check Out Date</th>
          <th>Payment Method</th>
          <th>Total</th>
          <th>Created Date</th>
          <th>Note</th>

        </tr>
        </thead>
        <tbody>
        <% List<Invoice> invoiceList = (List<Invoice>) request.getAttribute("invoiceList");
          for (Invoice invoice : invoiceList) { %>
          <td><%= invoice.getInvoiceId() %></td>
          <td><%= invoice.getRoomId() %></td>
            <td><%= invoice.getCustomerId() %></td>
          <td><%= invoice.getCheckInDate() %></td>
          <td><%= invoice.getCheckOutDate() %></td>
          <td><%= invoice.getPaymentMethod() %></td>
          <td><%= invoice.getTotal() %></td>
        <td><%= invoice.getCreatedDate() %></td>
        <td>
            <form action="booking-update" method="post">
              <input type="hidden" name="invoiceId" value="<%= invoice.getInvoiceId() %>">
              <select name="status" onchange="this.form.submit()">
                <option value="Unpaid" <%= "Unpaid".equals(invoice.getNote()) ? "selected" : "" %>>Unpaid</option>
                <option value="Paid haft" <%= "Paid haft".equals(invoice.getNote()) ? "selected" : "" %>>Paid haft</option>
                <option value="Checked out" <%= "Checked out".equals(invoice.getNote()) ? "selected" : "" %>>Checked out</option>
                <option value="Cancelled" <%= "Cancelled".equals(invoice.getNote()) ? "selected" : "" %>>Cancelled</option>

              </select>
            </form>
          </td>
        <td>
            <a href="booking-update?invoiceId=<%= invoice.getInvoiceId() %>" class="btn btn-primary">Detail</a>
        </td>

        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
  </div>
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
  .filter-group {
    display: flex;
    justify-content: space-between;
    width: 100%;
    max-width: 600px;
    margin-top: 20px;
  }

  .filter-select {
    flex: 1;
    margin-right: 10px;
  }

  #filterBtn {
    width: fit-content;
    background-color: red;
    border: none;
    color: whitesmoke;
    padding: 10px 24px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 0px 2px;
    transition-duration: 0.4s;
    cursor: pointer;
    height: fit-content;
  }

  #filterBtn:hover {
    background-color: #ffffff;
    color: red;
  }
</style>
<script>
  function sort() {
    // Get selected values
    var SortBy = document.getElementById('Sort').value;


    var url = 'booking-list?Sort=' + SortBy;

    // Redirect to the new URL
    window.location.href = url;
  }
  window.onload = function() {
    var urlParams = new URLSearchParams(window.location.search);
    document.getElementById('Sort').value = urlParams.get('Sort') || 'all';

  }

  // function resetFilter() {
  //   // Redirect to the original page without any filters
  //   window.location.href = 'room-list';
  // }
</script>
</html>