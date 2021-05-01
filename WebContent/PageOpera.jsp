<%@page import="model.opera.RecensioneBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.opera.OperaBean"%>
<%
	String s = request.getParameter("id");
	Integer media = (Integer) request.getAttribute("media");
	OperaBean bean = (OperaBean) request.getAttribute("opera");
	UtenteBean utente = null;
	
	if(session.getAttribute("utente") != null){
		utente = (UtenteBean) session.getAttribute("utente");
	}else if(session.getAttribute("guida") != null){
		utente = (UtenteBean) session.getAttribute("guida");
	}else if(session.getAttribute("biglietteria") != null){
		utente = (UtenteBean) session.getAttribute("biglietteria");
	}
	if(utente == null && request.getSession().getAttribute("codiceValido") == null){
		response.sendRedirect(response.encodeRedirectURL("./Homepage.jsp"));
		return;	
	}
	
	if (s == null) {
		response.sendRedirect(response.encodeRedirectURL("./Homepage.jsp"));
		return;
	} else {
		if (media == null && bean == null) {
			response.sendRedirect(response.encodeRedirectURL("./CercaOperaById?id=" + s));
			return;
		}
	}
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>PocketMuseum</title>
        <link rel="icon" type="image/x-icon" href="img/C09_Logo.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/PageOpera.css"/>
    </head>
    <body id="page-top">
    	<style>
	    	#footerOpera{
				clear: both;
			    position: relative;
			    bottom: 0;
			    margin-top: 100px;
			}
    	</style>
		
		<%@include file="NavBar.jsp" %>
        
		<div>
		<div class="row" id="content">
		  <div class="row">
		  <%if(request.getSession().getAttribute("guida")!=null
		  ||request.getSession().getAttribute("biglietteria")!=null
		  || bean.getStato().equals("visibile")){ %>
		    <div class="col-sm-6">
		    
		    	<img class="copertina iclass" src="data:image/png;base64,<%=bean.getImmagine()%>" alt="non va">
		    </div>
		    <div class="col-sm-6">
		    <div class="esterno">
		    	<div id="descrizione">
					<p>
						<span style="font-size:1.4em;font-weight: bold"><%=bean.getNome()%></span><br>
						<%=bean.getAutore()%><br><br>
						Descrizione:<br>
						<%=bean.getDescrizione()%><br>
					</p>
					<br>
				</div>
				</div>
		    </div>
		    <% }else { 
		     response.sendRedirect(response.encodeRedirectURL("./CercaOperaById?id=" + (bean.getId()+1)));
		     System.out.println("passo a opera : "+ bean.getId()+1);
		    } %>
		  </div>
		
		  <div class="row">
		    <div class="col-sm-3">
		    	<div>
					<i class="fas fa-arrow-circle-left freccia " onClick="frecciaSx(<%=bean.getId()%>)"></i>
				</div>
		    </div>
		    <div class="col-sm-6">
		    	<div id="recensioni">
					<span id="stella1" onclick="addReview(1)" class="fa fa-star"></span>
					<span id="stella2" onclick="addReview(2)" class="fa fa-star"></span>
					<span id="stella3" onclick="addReview(3)" class="fa fa-star"></span>
					<span id="stella4" onclick="addReview(4)" class="fa fa-star"></span>
					<span id="stella5" onclick="addReview(5)" class="fa fa-star"></span>
					<form id="recensioneForm" action="AggiungiRecensione" method="get" style="display:none">
						<input type="hidden" name="operaId" value="<%=bean.getId() %>">
						<%if(utente!=null){ %>
						<input type="hidden" name="email" value="<%=utente.getEmail() %>">
						<%} %>
						 
						<input id="numStelle" type="hidden" name="stelle">
					</form>
				</div>
		    </div>
		    <div class="col-sm-3">
		    	<div>
					<i class="fas fa-arrow-circle-right freccia" onClick="frecciaDx(<%=bean.getId()%>)"></i>
				</div>
		    </div>
		  </div>
		</div>
		</div>
       <footer id="footerOpera">
       		<%@include file="Footer.jsp" %>
       </footer>
        
        
      
       <script src="js/scripts.js"></script>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script>
    
    	<%
    	switch (media) {
    		case 1:
    			%> $("#stella1").addClass("checked");
    			<%
    			break;
    		case 2:
    			%>
    			$("#stella1").addClass("checked");
    			$("#stella2").addClass("checked");
    			<%
    			break;
    		case 3:
    			%> 
    			$("#stella1").addClass("checked");
    			$("#stella2").addClass("checked");
    			$("#stella3").addClass("checked");
    			<%
    			break;
    		case 4:
    			%> 
    			$("#stella1").addClass("checked");
    			$("#stella2").addClass("checked");
    			$("#stella3").addClass("checked");
    			$("#stella4").addClass("checked");
    			<%
    			break;
    		case 5:
    			%>
    			$("#stella1").addClass("checked");
    			$("#stella2").addClass("checked");
    			$("#stella3").addClass("checked");
    			$("#stella4").addClass("checked");
    			$("#stella5").addClass("checked");
    			<%
    			break;
    		default:
    			break;
    	}
		%>    
		
    	function frecciaSx(id){
    		id = id - 1;
    		if(id<=0) id=1;
    		window.location.replace("./PageOpera.jsp?id=" + id);
    	}
		function frecciaDx(id){
			id = id + 1;
			window.location.replace("./PageOpera.jsp?id=" + id);
    	}
		function addReview(num){
			$("#numStelle").val(num);
			$("#recensioneForm").submit();
		}
    
    </script>
    
</html>
