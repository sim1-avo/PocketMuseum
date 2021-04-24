<%@page import="model.biglietto.BigliettoModelDM"%>
<%@page import="model.biglietto.BigliettoModel"%>
<%@page import="model.biglietto.*, model.utente.*, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		<script src="scripts/PageBigliettiScripts.js" type="text/javascript"></script>
		<link href="css/PageBiglietti.css" rel="stylesheet" type="text/css">
		<link href="css/styles.css" rel="stylesheet" type="text/css">
		<link rel="icon" type="image/x-icon" href="img/C09_Logo.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>PocketMuseum</title>
</head>
<body id="bodyPageBiglietti">
<jsp:include page="NavBar.jsp"></jsp:include>
<div id="block_container"><div id="bloc1"><table class="table table-striped table-dark" id="tablePageBiglietti">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">CODICE</th>
      <th scope="col">COSTO</th>
      <th scope="col">DATA ACQUISTO</th>    
    </tr>
  </thead>
<tbody>
<%
ArrayList<AcquistaBean> acquista = new ArrayList<AcquistaBean>();
int i=1;
UtenteBean utente= new UtenteBean();
utente=(UtenteBean)session.getAttribute("utente");
acquista=(ArrayList<AcquistaBean>) request.getAttribute("acquista");
for(AcquistaBean a : acquista){
	%>
    <tr>
      <th scope="row"><%=i %></th>
      <td><a class="codici" href="#" id="<%=i%>" ><%=a.getCodice() %></a></td>
      <td><a id="Costo<%=i%>" ><%=a.getCosto() %></a></td>
      <td><a><%=a.getDataAcquisto() %></a></td>
      <td><input id="dataIn<%=i%>" type="hidden" value="<%=a.getInizioTurno()%>"></td>
      <td><input id="dataFi<%=i%>" type="hidden" value="<%=a.getFineTurno()%>"></td>
    </tr>
	
	<%
	i++;
}
%>
  </tbody>
</table></div>
<div class="card" id="bloc2">
  <div class="card-body">
    <h5 class="card-title"></h5>
    <h6 class="card-subtitle"></h6>
    <p class="card-text text1"></p>
    <p class="card-text text2"></p>
    <div><a href="#" id="chiudi" class="card-link">Chiudi</a></div>
  </div>
</div></div>

<!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <footer id="footerPageBiglietti"><jsp:include page="Footer.jsp"></jsp:include></footer>
    </body>
</html>
