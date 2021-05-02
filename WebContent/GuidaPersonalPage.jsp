<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, model.opera.*"%>
<%@page import="model.evento.*"%>
<%@page import="java.sql.Date,java.sql.Timestamp"%>

<%
	if(request.getSession(false).getAttribute("guida")==null) {
		response.sendRedirect("Log.jsp");
		return;
	}

	ArrayList<OperaBean> opere = (ArrayList<OperaBean>) request.getAttribute("opere");
	ArrayList<EventoBean> eventi = (ArrayList<EventoBean>) request.getAttribute("eventi");
	if (eventi == null || opere == null) {
		response.sendRedirect(response.encodeRedirectURL("./GuidaPageServlet"));
		return;
	}



%>

<!DOCTYPE html>
<html>
<head>
	<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
			type="text/javascript"></script>
	<script src="scripts/PersonalPageUtente.js"></script>
	<link href="css/PersonalPageUtente.css" rel="stylesheet" type="text/css">
	<meta charset="UTF-8">
	<link rel="icon" type="image/x-icon" href="img/C09_Logo.png" />
	<title>PocketMuseum</title>
	<link rel="icon" type="image/x-icon" href="img/C09_Logo.png" />
	<link rel="stylesheet"
		  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js"
			crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
		  rel="stylesheet" type="text/css" />
	<link
			href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
			rel="stylesheet" type="text/css" />
	<link
			href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
			rel="stylesheet" type="text/css" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="css/styles.css" rel="stylesheet" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<link rel="stylesheet" href="css/Guida.css" />
</head>

<body>
<%@include file="NavBar.jsp"%>
<!-- SEZIONE UTENTE REGISTRATO -->


<!-- DATI PERSONALI -->
<!-- DATI PERSONALI -->
<!-- DATI PERSONALI -->
<!-- DATI PERSONALI -->

<div id="content">
	<div id="blocco" class="row">
		<div class="col-12 col-md-8" id="divTable">
			<table style="width: 60% !important; margin-left: 5%"
				   class="table table-striped table-dark" id="tableUserPage">
				<tr>
					<td>Email</td>
					<td>email</td>


					<td></td>
				</tr>
				<tr>
					<td class="primo">Password</td>
					<td>****</td>

					<td><input class="btn btn-primary btnmodificapsw"
							   type="button" value="Modifica"></td>

				</tr>

			</table>
		</div>
		<div class="col-6 col-md-4" class="card" id="bloccoModificaPsw">
			<form action="ModificaDati" onsubmit="validatemodificaPW()" method="get">
				<div class="card-body">
					<input type="hidden" value="psw" name="richiesta">
					<div style="display: inline">
						<h6 style="float: left" class="card-title">Vecchia Password:</h6>
						<input name="vecchio" style="float: right" type="password" pattern="([\w\W]{8,32})"
							   title="Il campo vecchia password è errato" required>
					</div>
				</div>
				<div class="card-body">
					<div style="display: inline">
						<h6 style="float: left" class="card-title">Nuova Password:</h6>
						<input name="nuovo" style="float: right" type="password" pattern="([\w\W]{8,32})"
							   title="Il campo nuova password è errato" required>
					</div>
				</div>
				<div class="card-body">
					<div>
						<a href="#" id="chiudipsw" class="card-link">Chiudi</a><input
							style="float: right" type="submit" class="btn btn-primary"
							value="Modifica">
					</div>
				</div>
			</form>
		</div>
	</div>



	<!-- FINE DATI PERSONALI -->
	<!-- FINE DATI PERSONALI -->
	<!-- FINE DATI PERSONALI -->
	<!-- FINE DATI PERSONALI -->
	<!-- FINE DATI PERSONALI -->



	<div class="row">
		<div class="col-md-6">
			<button
					onclick="window.location.href='GestioneOpera.jsp'"
					type="button" class=" mybutton btn btn-secondary d-block" style="width:80%;margin-left:10%">Gestione
				Opera</button>
		</div>
		<div class="col-md-6">
			<button
					onclick="window.location.href='GestioneEvento.jsp'"
					type="button" class=" mybutton btn btn-secondary d-block" id="eventoButton" style="width:80%;margin-left:10%">
				Crea Nuovo Evento
			</button>
		</div>
	</div>








	<div class="row">

		<!-- OPERE -->
		<!-- OPERE -->
		<!-- OPERE -->
		<!-- OPERE -->
		<!-- OPERE -->
		<div class="col-sm-6">
			<div id="scrollOpere" style="height:450px!important; overflow:auto;width:90%;margin-left:5%">
				<table class="table table-bordered table-dark table-hover" style="width:90%;margin-left:5%;">
					<tbody>


					<tr>
						<td colspan="4">
							<form action="CercaOpera" class="form-inline" >
								<input style="width:85%;"name="search" class="form-control mr-sm-2" type="search" placeholder="opere visibili" aria-label="Search">
								<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Cerca</button>

							</form>
						</td>
					</tr>
					<tr>
						<th><h3>OPERE</h3></th>
						<th><h3>NOME</h3></th>
						<th><h3>AUTORE</h3></th>
					</tr>

					<%  if(opere.size() != 0){
						for(OperaBean opera: opere){
							if(request.getSession().getAttribute("guida")!=null || request.getSession().getAttribute("biglietteria")!=null || opera.getStato().equals("visibile")  ){%>
					<tr onclick="searchMe(<%=opera.getId() %>)">
						<td style="width: 150px; height:150px" id="copertina">
							<copertina>
								<img src="data:image/png;base64,<%=opera.getCopertina() %>"
									 alt="NON VA">
							</copertina>
						</td>
						<td>
							<h4><%=opera.getNome() %></h4>
						</td>
						<td class="descrizione"><h4><%=opera.getAutore() %></h4></td>
						<%if(request.getSession().getAttribute("guida")!=null||request.getSession().getAttribute("biglietteria")!=null){%>
						<td class="stato"><h4><%=opera.getStato() %></h4></td>
						<%} %>
					</tr>
					<%} else { %>
					<tr>
						<td><h4 style="text-align: center">Al momento non è
							disponibile presso il nostro Museo!</h4></td>
						<td>
							<h4><%=opera.getNome() %></h4>
						</td>
						<td class="descrizione"><h4><%=opera.getAutore() %></h4></td>
					</tr>
					<%}}

					} else{ %>
					<tr>
						<td><h4 style="text-align: center">Nessuna
							corrispondenza trovata!</h4></td>
					</tr>
					<%}%>
					</tbody>
				</table>
			</div>
		</div>

		<!-- FINE OPERE -->
		<!-- FINE OPERE -->
		<!-- FINE OPERE -->
		<!-- FINE OPERE -->
		<!-- FINE OPERE -->
		<!-- FINE OPERE -->
		<!-- FINE OPERE -->



		<!-- EVENTI -->
		<!-- EVENTI -->
		<!-- EVENTI -->
		<!-- EVENTI -->
		<!-- EVENTI -->
		<!-- EVENTI -->
		<!-- EVENTI -->

		<div class="col-sm-6" id ="sectionEvents">
			<% if(request.getAttribute("alertSuccess") != null || request.getAttribute("alertError") != null ) {
				String classAlert = request.getAttribute("alertSuccess") != null ? "alert-success" : "alert-danger";
			%>
			<!-- Sezione Alert Eventi -->
			<div class="alert <%= classAlert %> alert-dismissible fade show" role="alert">
				<% if (request.getAttribute("messageAlert") != null) { %> <%= request.getAttribute("messageAlert")%> <% } %>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<!-- END Sezione Alert Eventi -->
			<% } %>

			<div id="scrollEventi" style="height:450px!important; overflow:auto;width:90%;margin-left:5%">
				<table id="eventTable" class="table table-bordered table-dark table-hover" style="width:90%;margin-left:5%">
					<tbody>

					<tr>
						<th><h3>EVENTI</h3></th>
						<th><h3>DESCRIZIONE</h3></th>
						<th><h3>INIZIO</h3></th>
						<th><h3>FINE</h3></th>
						<th><h3>AZIONI</h3></th>
					</tr>
					<%if(eventi.size()!=0){
						for(EventoBean evento : eventi){
							Timestamp ts=evento.getDataInizio();
							Date dataInizio=new Date(ts.getTime());

							Timestamp ts2=evento.getDataFine();
							Date dataFine=new Date(ts2.getTime());
					%>
					<tr>
						<td style="width: 150px; height: 150px" id="copertina"><img
								src="data:image/png;base64,<%=evento.getImmagine()%>"
								alt="NON VA"></td>
						<td>
							<h4><%=evento.getNome()%></h4>
							<h4><%=evento.getDescrizione()%></h4>
						</td>
						<td>
							<h4><%=dataInizio%></h4>
						</td>
						<td>
							<h4><%=dataFine%></h4>
						</td>
						<td class="actionEvents">
							<ul>
								<li>
									<a href="ModificaEventoPage?idEvent=<%= evento.getId() %>"><i class="far fa-edit"></i></a>
								</li>
								<li>
									<a href="EliminaEvento?idEvent=<%= evento.getId() %>"><i class="far fa-trash-alt"></i></a>
								</li>
							</ul>
						</td>
					</tr>
					<%}}else{%>
					<tr>
						<td><h4 style="text-align: center">NESSUN EVENTO IN
							PROGRAMMA</h4></td>
					</tr>
					<%}%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<footer id="footerPageEvent">
	<div class="container">
		<%@include file="Footer.jsp"%>
	</div>
</footer>

</body>

<script>
	function searchMe(id){
		window.location.replace("./PageOpera.jsp?id=" + id);
	}
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
		integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
		crossorigin="anonymous"></script>
<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</html>