<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, model.biglietto.*, model.utente.*, model.evento.*,java.sql.Date,java.sql.Timestamp" %>

<%
	/*if(request.getSession(false).getAttribute("utente")==null) {
		response.sendRedirect("Login.jsp");
		return;
	*/
	if(session.getAttribute("biglietteria")==null && session.getAttribute("utente")==null && session.getAttribute("guida")==null){
           response.sendRedirect("Login.jsp");
           return;
       }

    	UtenteBean utente= new UtenteBean();
    	  if(session.getAttribute("utente") != null){
        	  utente = (UtenteBean) session.getAttribute("utente");
          }
          if(session.getAttribute("biglietteria") != null){
        	  utente = (UtenteBean) session.getAttribute("biglietteria");
        	}


%>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">

  <link rel="stylesheet" href="assets/css/flipbox.css">

  <link rel="stylesheet" href="assets/css/ticket.css">

  <link rel="stylesheet" href="assets/css/cardEvents.css">

  <link rel="stylesheet" href="assets/css/historyBiglietteria.css">
  <link rel="stylesheet" href="assets/css/AddChangeRemoveOpere.css">
</head>

<body>

<!-- NAVBAR UTENTE -->

<%if(session.getAttribute("utente") != null){ %>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="Tour.jsp">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav ml-lg-5 mt-3 mt-lg-0">

        <li class="nav-item">
          <a class="nav-link"  href="Tour.jsp">Tour</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="UserData.jsp">Your Data</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="History.jsp">History</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Eventi.jsp">Events</a>
        </li>



      </ul>
      <%if(session.getAttribute("utente") == null){ %>

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
<%}%>



<!-- NAVBAR BIGLIETTERIA -->

<%if(session.getAttribute("biglietteria") != null){ %>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="Tour.jsp">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav ml-lg-5 mt-3 mt-lg-0">

        <li class="nav-item">
          <a class="nav-link" onclick="Tour.jsp">Tour</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="UserData.jsp">Your Data</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="History.jsp">History</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Eventi.jsp">Events</a>
        </li>

      </ul>

      <%if(session.getAttribute("biglietteria") == null){ %>

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
<%}%>

<!-- NAVBAR GUIDA -->

<%if(session.getAttribute("guida") != null){ %>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="Opere.jsp">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav ml-lg-5 mt-3 mt-lg-0">

        <li class="nav-item">
          <a class="nav-link" href="Opere.jsp">Opere</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Eventi.jsp">Events</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="UserData.jsp">Your Data</a>
        </li>

      </ul>

      <%if(session.getAttribute("guida") == null){ %>

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
<%}%>






</body>
</html>