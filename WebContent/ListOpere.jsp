<%@page import="model.opera.OperaBean"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
if( session.getAttribute("utente")==null && session.getAttribute("guida")==null){
	response.sendRedirect("Log.jsp");
	return;
}
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
<html>
<head>
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
<link rel="stylesheet" href="css/ListOpere.css" />
</head>
<style>
body {
	font-family: Lucida Sans Unicode, Lucida Grande, sans-serif;
	background-image: url('img/sfondo.jpg');
	background-position: center;
	background-repeat: no-repeat;
}
</style>
<body>
	<%@include file="NavBar.jsp"%>

	<div class="container">
		<div id="content" class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<table class="table table-bordered table-dark table-hover">
					<tbody>

						
                            <!-- Cambiare con != null -->
							<% if(session.getAttribute("codiceValido")!=null){ %>

							 <tr>
							<td colspan="2">
							<form action="RicercaRecensione" method="post"
									style="text-align: center">

									<div class="form-group">
										<label for="exampleFormControlSelect1">Seleziona Opere
											in base al numero di stelle</label>
										<select name="stelle" required
											class="form-control" id="exampleFormControlSelect1">
											<option value="1">1 STELLA</option>
											<option value="2">2 STELLE</option>
											<option value="3">3 STELLE</option>
											<option value="4">4 STELLE</option>
											<option value="5">5 STELLE</option>
										</select>
									</div>
									<button class="btn btn-primary">CERCA</button>
								</form>
								</td>
								</tr>	
								

					 
					 <%}else if(session.getAttribute("guida")!=null){%>
							<tr>
							<td colspan="3">
	
					<form action="CercaOpera" class="form-inline" >
					<button class="btn btn-outline-info my-2 my-sm-0" onclick="CercaOpera?search=">Lista opere</button>
					
					    <input style="width:65%;margin-left:10%"name="search" class="form-control mr-sm-2" type="search" placeholder="opere visibili" aria-label="Search">
				    	<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Cerca</button>
				    	
				</form>
								

							</td>


						</tr>
								<%
					 
					 }
					 %>
				



						<%  if(opere.size() != 0){
				    	for(OperaBean opera: opere){ 
				    	  if(request.getSession().getAttribute("guida")!=null || request.getSession().getAttribute("biglietteria")!=null || opera.getStato().equals("visibile")  ){%>
						<tr onclick="searchMe(<%=opera.getId() %>)">
							<td id="copertina"><copertina>
								<img src="data:image/png;base64,<%=opera.getCopertina()%>"
									alt="NON VA"></copertina></td>
							<td>
								<h4><%=opera.getNome() %></h4> <br>
								<h5><%=opera.getAutore() %></h5>
							</td>

							<%if(request.getSession().getAttribute("guida")!=null||request.getSession().getAttribute("biglietteria")!=null){%>
							<td class="stato"><h5><%=opera.getStato() %></h5></td>
							<%} %>
						</tr>
						<%}else { %>
						<tr>
							<td><h5 style="text-align: center">Al momento non è
									disponibile presso il nostro Museo!</h5></td>
							<td>
								<h4><%=opera.getNome() %></h4> <br>
								<h5><%=opera.getAutore() %></h5>
							</td>

						</tr>
						<%}} 
					   		
					    } else{ %>
						<tr>
							<td><h5 style="text-align: center">Nessuna
									corrispondenza trovata!</h5></td>
						</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>

	</div>
	<footer id="footerOpere">
		<%@include file="Footer.jsp"%>
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