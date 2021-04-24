<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, model.biglietto.*, model.utente.*"%>

<%
	UtenteBean utente= new UtenteBean();
	if(request.getSession(false).getAttribute("utente")==null) {
		response.sendRedirect("Log.jsp");
		return;
	}

	utente=(UtenteBean) request.getSession().getAttribute("utente");
	ArrayList<AcquistaBean> acquista =(ArrayList<AcquistaBean>) request.getAttribute("acquista");
	if(acquista==null){
		response.sendRedirect(response.encodeRedirectURL("PageBigliettiServlet"));
		return;
	}

%>
<!DOCTYPE html>
<html>
<head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="scripts/PageBigliettiScripts.js" type="text/javascript"></script>
<meta charset="UTF-8">


<title>PocketMuseum</title>
<link rel="icon" type="image/x-icon" href="img/C09_Logo.png" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>


<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />

<link href="css/styles.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="css/PersonalPageUtente.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="NavBar.jsp"></jsp:include>
<div class="container light-style flex-grow-1 container-p-y" id="content">

    
    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-general">Generale</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-change-password">Cambia password</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-acquisti">Acquisti</a>
            </div>
        </div>
        <div class="col-md-9">
          <div class="tab-content">
            <div class="tab-pane fade active show" id="account-general" style="height:550px!important!;">
			<div style="height:460px!important;">
              <div class="card-body media align-items-center">
             
				<div class="circle">
				<%
				String nome =utente.getNome();
				char n=nome.charAt(0);
				
				String cognome =utente.getCognome();
				char c=cognome.charAt(0);
				%>
				  <span class="initials" style="color:black"><%=n%><%=c%></span>
				</div>
                 
                 
              </div>
              <hr class="border-light m-0">

              <div class="card-body">
                <div class="form-group">
                  <label class="form-label">Nome</label>
                  <input disabled type="text" class="form-control mb-1" style="color:black" value="<%=utente.getNome()%>">
                </div>
                <div class="form-group">
                  <label class="form-label">Cognome</label>
                  <input disabled type="text" class="form-control" style="color:black" value="<%=utente.getCognome()%>">
                </div>
                <div class="form-group">
                  <label class="form-label">E-mail</label>
                  <input disabled type="text" class="form-control mb-1" style="color:black"value="<%=utente.getEmail()%>">
                </div>
                <div class="form-group">
                  <label class="form-label">Codice Fiscale</label>
                  <input disabled type="text" class="form-control" style="color:black" value="<%=utente.getCf()%>">
                </div>
                
              </div>

            </div>
			</div>
			 <div class="tab-pane fade" id="account-change-password" >
			  <div style="height:460px!important;">
              <div class="card-body pb-2" >
				<form action="ModificaDati" method="get">
				
				
				<div class="card-body">
					<input type="hidden" value="psw" name="richiesta">
									
					<div class="form-group">
                    	<label class="form-label">Current password</label>
                    	<input type="password" class="form-control" name="vecchio" pattern="([\w\W]{8,32})"
						title="Il campo vecchia password è errato">
                    </div>
				</div>
				
				<div class="card-body">
					<div class="form-group">
                  		<label class="form-label">New password</label>
                  		<input type="password" class="form-control" name="nuovo" pattern="([\w\W]{8,32})"
						title="Il campo nuova password è errato">
                	</div>
				</div>
				<div class="card-body">
					               
				    <div class="text-right mt-3">
				      <button type="submit" class="btn btn-primary" value="Modifica" >Save changes</button>
				    </div>
				</div>
			</form>
               

                
   
			</div>	
              </div>
            </div>
            
            
          <div class="tab-pane fade" id="account-acquisti">
            <div id="scrollOpere" style="height:460px!important; overflow:auto;">
              <table class="table table-bordered table-dark table-hover">
					<tbody>
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
					int i=1;
					for(AcquistaBean a : acquista){
						%>
					<tr>
						<th scope="row"><%=i %></th>
						<td><a class="codici" href="#" id="<%=i%>" > <%=a.getCodice() %></a></td>
						<td><a id="Costo<%=i%>"><%=a.getCosto() %></a></td>
						<td><a><%=a.getDataAcquisto() %></a></td>
						<input id="dataIn<%=i%>" type="hidden" value="<%=a.getInizioTurno()%>">
						<input id="dataFi<%=i%>" type="hidden" value="<%=a.getFineTurno()%>">
					</tr>

					<%
					i++;
					}
					%>
					</tbody>
				</table>
				</div>
            </div>
         
          
       
          </div>
        </div>
      </div>
    </div>
     <div class="card" id="bloc2" style="height:fit-content;display:none">
			<div class="card-body">
				<h4 class="card-title"></h4>
				<h5 class="card-subtitle"></h5>
				<p class="card-text text1"></p>
				<p class="card-text text2"></p>
				<div>
					<a href="#" id="chiudi" class="card-link">Chiudi</a>
				</div>
			</div>
		</div>
 </div>
 
  
 	<footer id="footerPageEvent" style="margin-top:7%">
		<div class="container">

			<%@include file="Footer.jsp"%>

		</div>
	</footer>

</body>


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
</html>