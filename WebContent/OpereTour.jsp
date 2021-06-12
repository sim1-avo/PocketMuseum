<%@page import="model.opera.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.utente.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	Integer media = (Integer) request.getAttribute("media");
    UtenteBean utente = null;

if( session.getAttribute("utente")==null){

	response.sendRedirect("Login.jsp");
	return;
}
utente = (UtenteBean) session.getAttribute("utente");


	OperaModelDM listaopere = new OperaModelDM();
	ArrayList<OperaBean> opere = (ArrayList<OperaBean>) request.getAttribute("opere");

    	if(opere == null){
    		String s = request.getParameter("search");
    		if(s == null){
    			s="";
    		}
    		response.sendRedirect(response.encodeRedirectURL("./CercaOpera?search=" + s));
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

  <title>PocketMuseum - Tour</title>

  <link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">

  <link rel="stylesheet" href="assets/css/starSlider.css">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<!-- OperTour -->
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
                               <!--CAMBIARE CON != null-->
       <%
        if(session.getAttribute("codiceValido") == null){%>
        <li>
        <form action="CercaOpera"  style="height:10px; margin-bottom:20px">
            <input id="srcOpr" name="search" class="mb-4 fw-normal" type="search" placeholder="Search" aria-label="Search">
            <button class="searchbtn" type="submit">
                    <img src="https://www.freepnglogos.com/uploads/search-png/search-icon-clip-art-clkerm-vector-clip-art-online-8.png" width="20px"/>
            </button>
        </form>
      </li>
      <li>
        <form id="ricrec" action="RicercaRecensione" method="post">
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
        </li>


    <%} %>

      </ul>
		<div class="ml-auto my-2 my-lg-0">
           <a href="Tour.jsp"> <button class="btn btn-dark rounded-pill"> Esci dal tour </button> </a>
        </div>
    </div>
  </div>
</nav>

<!-- ELENCO OPERE -->

<div class="page-section bg-dark fg-white" style="background-image: url(assets/img/pattern_1.svg);">
  <div class="container-fluid">

  <!--MIN WIDTH -->
    <div id="minwidth">
    <%if(opere.size() != 0){
    		  	for(OperaBean opera: opere){
    		   	  if(opera.getStato().equals("visibile")){
    		%>
              <div class="itemopera">
                <div class="avatar mt-3">

                              <div class="avatar-caption">
                                <p class="mb-0 fw-medium fg-primary" style="color:darkgoldenrod"><%=opera.getNome() %></p>
                                <div class="fs-vsmall fw-medium" style="color:black"><%=opera.getAutore() %></div>
                              </div>
                </div>
                <div class="captionopera">
                <img class="opera"  src="data:image/png;base64,<%=opera.getCopertina()%>" alt="NON VA">
                </div>

                <div class="avatar mt-3">
                    <div class="avatar-caption" style="overflow-y: scroll; height: 138px;">
                    <div class="fs-vsmall fw-medium" style="color:black"><%=opera.getDescrizione() %></div>
                </div>

                <form id="recensioneForm" action="AggiungiRecensione" method="get">

                    <input type="hidden" name="operaId" value="<%=opera.getId()%>">
                    <%if(utente!=null){ %>
                    <input type="hidden" name="email" value="<%=utente.getEmail()%>">
                    <%} %>
                    <br>
                    <textarea id="comment" name="commento" placeholder="Inserisci una recensione" rows="4" style="width: -webkit-fill-available;"></textarea>

                    <center><i class="fa fa-thumbs-down"></i> <input type="range" name=stelle min="1" max="5"> <i class="fa fa-thumbs-up"></i></center>
                    <br>
                    <center><input class="btn btn-primary rounded-pill" style="padding: revert;" type="submit" value="Invia recensione"></center>
                </form>
              </div>
          </div>
          <%}else{ %>
          <div></div>
          <%}
          }} %>


    </div>



  <!--MAX WIDTH -->
    <div id="maxwidth" class="owl-carousel stack-carousel mt-5 wow fadeInUp">
        <%if(opere.size() != 0){
		  	for(OperaBean opera: opere){
		   	  if(opera.getStato().equals("visibile")){
		   	  int idOp= opera.getId();
		%>
          <div class="item">
            <div class="avatar mt-3">
              <div class="avatar-caption">
                <p class="mb-0 fw-medium fg-primary" style="color:darkgoldenrod"> <%=opera.getNome() %></p>
                <div class="fs-vsmall fw-medium" style="color:black"><%=opera.getAutore() %></div>
              </div>
            </div>

            <div class="caption">
                <img  src="data:image/png;base64,<%=opera.getCopertina()%>" alt="NON VA">
            </div>

            <div class="avatar mt-3">
                <div class="avatar-caption" style="overflow-y: scroll; height: 138px;">
                <div class="fs-vsmall fw-medium" style="color:black"><%=opera.getDescrizione() %></div>
            </div>



            <form id="recensioneForm" action="AggiungiRecensione" method="get">

            <input type="hidden" name="operaId" value="<%=opera.getId()%>">
            <%if(utente!=null){ %>
            <input type="hidden" name="email" value="<%=utente.getEmail()%>">
            <%} %>
            <br>
            <textarea id="comment" name="commento" placeholder="Inserisci una recensione" rows="4" style="width: -webkit-fill-available;"></textarea>

            <center><i class="fa fa-thumbs-down"></i> <input type="range" name=stelle min="1" max="5"> <i class="fa fa-thumbs-up"></i></center>
            <br>
            <center><input class="btn btn-primary rounded-pill" style="padding: revert;" type="submit" value="Invia recensione"></center>
            </form>

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
<script src="assets/js/operetour.js"></script>


</body>
</html>
