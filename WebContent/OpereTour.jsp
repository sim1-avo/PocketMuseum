<%@page import="model.opera.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
if( session.getAttribute("utente")==null && session.getAttribute("guida")==null){
	response.sendRedirect("Log.jsp");
	return;
}
	OperaModelDM listaopere = new OperaModelDM();
	ArrayList<OperaBean> opere = (ArrayList<OperaBean>) listaopere.doRetrieveAll("ASC");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>PocketMuseum - Tour</title>

  <link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">

  <link rel="stylesheet" href="assets/css/starSlider.css">
</head>
<body>
<!-- Testimonials -->
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
      <!--

        -->
        <%
        if(session.getAttribute("codiceValido") == null){%>
        <form action="CercaOpera"  style="height:10px; margin-bottom:20px">
            <input id="srcOpr" name="search" class="mb-4 fw-normal" type="search" placeholder="Search" aria-label="Search">
            <button class="searchbtn" type="submit">
                    <img src="https://www.freepnglogos.com/uploads/search-png/search-icon-clip-art-clkerm-vector-clip-art-online-8.png" width="20px"/>
                    </button>
            <%if(session.getAttribute("codiceValido") != null){%>
                <button class="btn btn-primary rounded-pill"
                    style="min-width:150px;margin-left: 5px;align-self: center;"
                        onclick="CercaOpera?search=">Lista opere</button>
            <%} %>
        </form>

        <form action="RicercaRecensione" method="post" style="text-align: center">
            <div class="selectStar">
                <select name="stelle" required class="ora" id="exampleFormControlSelect1">
                    <option> Select number of star</option>
                    <option value="1">&#x2B50;</option>
                    <option value="2">&#x2B50;&#x2B50;</option>
                    <option value="3">&#x2B50;&#x2B50;&#x2B50;</option>
                    <option value="4">&#x2B50;&#x2B50;&#x2B50;&#x2B50;</option>
                    <option value="5">&#x2B50;&#x2B50;&#x2B50;&#x2B50;&#x2B50;</option>
                </select>
            </div>
            <button class="searchbtn">
                <img src="https://www.freepnglogos.com/uploads/search-png/search-icon-clip-art-clkerm-vector-clip-art-online-8.png" width="20px"/>
            </button>
        </form>


    <%} %>





      </ul>

		<div class="ml-auto my-2 my-lg-0">
        <button class="btn btn-dark rounded-pill"> <a href="UtentePP.jsp">Esci dal tour</a></button>
      </div>


    </div>
  </div>
</nav>
<div class="page-section bg-dark fg-white" style="background-image: url(assets/img/pattern_1.svg);">
  <div class="container-fluid">
    <div class="owl-carousel stack-carousel mt-5 wow fadeInUp">
        <%if(opere.size() != 0){
		  	for(OperaBean opera: opere){
		   	  if(request.getSession().getAttribute("guida")!=null
		   	    || request.getSession().getAttribute("biglietteria")!=null
		  	    || opera.getStato().equals("visibile")  ){
		%>
          <div class="item">
            <div class="ratings fs-small py-3">
              <span class="icon mai-star"></span>
              <span class="icon mai-star"></span>
              <span class="icon mai-star"></span>
              <span class="icon mai-star"></span>
              <span class="mai-star"></span>
            </div>

            <div class="avatar mt-3">
                          <div class="avatar-img">
                            <img src="assets/img/artist.svg" alt="">
                          </div>
                          <div class="avatar-caption">
                            <p class="mb-0 fw-medium fg-primary"><%=opera.getNome() %></p>
                            <div class="fs-vsmall fw-medium"><%=opera.getAutore() %></div>
                          </div>
            </div>
            <div class="caption">
            <img src="data:image/png;base64,<%=opera.getCopertina()%>" alt="NON VA">
            </div>

            <div class="avatar mt-3">
                <div class="avatar-caption" style="overflow-y: scroll; height: 138px;">
                <div class="fs-vsmall fw-medium" style="color:black"><%=opera.getDescrizione() %></div>
            </div>
          </div>
      </div>
      <%}else{ %>
      <div></div>
      <%}
      }} %>
    </div>
  </div>
</div>






<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>
<script src="assets/js/sendStar.js"></script>


</body>
</html>
