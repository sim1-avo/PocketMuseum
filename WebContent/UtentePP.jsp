<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, model.biglietto.*, model.utente.*, model.evento.*,java.sql.Date,java.sql.Timestamp" %>

<%

	UtenteBean utente= new UtenteBean();
	if(request.getSession(false).getAttribute("utente")==null) {
		response.sendRedirect("Login.jsp");
		return;
	}
	utente=(UtenteBean) request.getSession().getAttribute("utente");

    ArrayList<EventoBean> eventi = (ArrayList<EventoBean> ) request.getAttribute("eventi");

    if(eventi == null) {
        response.sendRedirect(response.encodeRedirectURL("./MostraEventiServlet"));
        return;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>Pocket Museum</title>

  <link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">

  <link rel="stylesheet" href="assets/css/flipbox.css">

  <link rel="stylesheet" href="assets/css/ticket.css">

  <link rel="stylesheet" href="assets/css/cardEvents.css">

</head>


<body>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="UtentePP.jsp">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav ml-lg-5 mt-3 mt-lg-0">

        <li class="nav-item">
          <a class="nav-link" onclick="ShowTour()" href="#tour">Tour</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#userdata" onclick="ShowUserData()">Your Data</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#history" onclick="ShowHistory()">History</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#events"onclick="ShowEvents()">Events</a>
        </li>



      </ul>
      <%if(session.getAttribute("utente") == null && session.getAttribute("biglietteria") == null){ %>

      <div class="ml-auto my-2 my-lg-0">
        <a href="./Login.jsp" ><button class="btn btn-primary rounded-pill" h> Login</button></a>
      </div>

		<%}else{ %>
		<div class="ml-auto my-2 my-lg-0">
        <a href="<%=response.encodeRedirectURL("./LogoutServlet")%>">
            <button class="btn btn-primary rounded-pill"> Logout</button>
        </a>
      </div>
		<%} %>


    </div>
  </div>
</nav>

<!-- TOUR -->
<div id="tour"><%@include file="Tour.jsp"%></div>

<!-- USER DATA - MENU -->
<div id="userdata"><%@include file="UserData.jsp"%></div>

<!-- HISTORY -->
<div id="history"><%@include file="History.jsp"%></div>

<!-- EVENTi -->
<div id="events"><%@include file="WEB-INF/Eventi.jsp"%></div>




<script>

</script>

<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>

<script src="assets/js/utentepp.js"></script>

<script src="assets/js/buytkt.js"></script>

<script src="js/scripts.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script

	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>

</body>
</html>



