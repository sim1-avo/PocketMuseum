<%
	String redirect="Homepage.jsp";
if(request.getSession(false).getAttribute("guida")!=null) {
	redirect="GuidaPersonalPage.jsp";
	
}
if(request.getSession(false).getAttribute("biglietteria")!=null) {
	redirect="BiglietteriaPersonalPage.jsp";
}
if(request.getSession(false).getAttribute("utente")!=null) {
	redirect="Homepage.jsp";
}
%>

<%@page import="model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>PocketMuseum</title>
		<link rel="stylesheet" href="css/PageOpera.css"/>
		<style>
			#navbarNav{
				background-color: #343a40!important;
			}
			
			#navBarBody{
				font-family: serif;
			}
			.btn-outline-info {
  				color: #fed136;
    			border-color: #fed136;
			}
			.btn-outline-info:hover {
				color: #fff;
				background-color: #fed136;
				border-color: #fed136;
			}
			
		</style>
	</head>
	<body id="navBarBody">
		<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark" style="max-height: 56px">
		  <a class="navbar-brand" href="<%=response.encodeRedirectURL(redirect)%>">
        <img src="./img/C09_Logo.png" style="width: 30px;height: 30px" alt="">
      </a>
		  <a class="navbar-brand" href="<%=response.encodeRedirectURL(redirect)%>">PocketMuseum</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">

		  <!-- cambiare con != null -->
	     	<%
          		if(session.getAttribute("codiceValido") == null){
       	   	%>
	           	<form action="CercaOpera" class="form-inline" style="height:10px; margin-bottom:20px">
				    <input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			    	<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Cerca</button>
			    	<%if(session.getAttribute("codiceValido") != null){%>
			    		<button class="btn btn-outline-info my-2 my-sm-0" style="min-width:150px;margin-left: 5px;align-self: center;" onclick="CercaOpera?search=">Lista opere</button>
			    	<%} %>
				</form>
			<%} %> 
		    <ul class="navbar-nav text-uppercase ml-auto">
		    <%if(session.getAttribute("guida")!=null){%>
		    		<a class="nav-link js-scroll-trigger" href="<%=response.encodeRedirectURL("./LogoutServlet")%>">Logout</a>
		    
		     <%}else{%>
		    
		    	<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Account
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			        <%if(session.getAttribute("utente")!=null){%> 
			          <a class="dropdown-item" href="PersonalPage.jsp">Area personale</a>
			          <%}
			          else if(session.getAttribute("guida")!=null || session.getAttribute("biglietteria")!=null) {%>
			          <a class="dropdown-item" href="<%=redirect%>">Area personale</a>
			          <%}
			           if(session.getAttribute("utente") == null && session.getAttribute("biglietteria") == null){ %><a class="dropdown-item" href="./Log.jsp">Login</a>
			          <%}else{ %><a class="dropdown-item" href="<%=response.encodeRedirectURL("./LogoutServlet")%>">Logout</a><%} %>
		        	</div>
		      	</li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%=response.encodeRedirectURL("./MostraEventiServlet")%>">Eventi</a></li>

				<%} %>
		        
		        
		        
		             <% if(session.getAttribute("utente") != null || session.getAttribute("biglietteria") != null){ %>
			          
		        	
	            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="PageAcquisto.jsp">Acquista</a></li>
	         
		        <%} %>
           	</ul>
        
		  </div>
		</nav>
	</body>
	
	
	        <!-- Bootstrap core JS-->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Third party plugin JS-->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
		<!-- Contact form JS-->
		<script src="assets/mail/jqBootstrapValidation.js"></script>
		<script src="assets/mail/contact_me.js"></script>
		<!-- Core theme JS-->
		
		
		
		
		
		
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>