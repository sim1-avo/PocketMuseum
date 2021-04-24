<%@page import="model.biglietto.BigliettoModelDM"%>
<%@page import="model.biglietto.BigliettoModel"%>
<%@page import="model.utente.UtenteBean, java.util.ArrayList,model.utente.UtenteModel, model.utente.UtenteModelDM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
  UtenteBean utente = null;
  if(session.getAttribute("utente") == null && session.getAttribute("biglietteria") == null){
	  response.sendRedirect("./Log.jsp");
	  return;
  }
  if(session.getAttribute("utente") != null){
	  utente = (UtenteBean) session.getAttribute("utente");
  }
  if(session.getAttribute("biglietteria") != null){
	  utente = (UtenteBean) session.getAttribute("biglietteria");
	}
%>

<!DOCTYPE html>
<html>
<head>
<title>PocketMuseum</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/PageBiglietti.css" rel="stylesheet" type="text/css">
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<link rel="icon" type="image/x-icon" href="img/C09_Logo.png"/>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <!-- Font Awesome icons (free version)-->
   
   <!-- Google fonts-->
   <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
   <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
   <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="scripts/validationacquisto.js"></script>
  
   <style>
	.containerAcquisto {
		justify-content: center;
		align-items: center;
		padding: 5px;
		background-color: #353535;
		color: white;
		border-radius: 1.2em;
		opacity:0.85!important;
	}
	
	form, .data{
	  text-align: center;
	}
	
	#containerId{
	  display: none;
	}
	
	.containerAcquisto .data label {
		display: block;
		font: 1rem 'Fira Sans', sans-serif;
	}
	
	.containerAcquisto .data input, .containerAcquisto .data label {
		margin: .4rem 0;
	}
   </style>
</head>
<body id="bodyPageBiglietti">
  <jsp:include page="NavBar.jsp"></jsp:include>

	<div class="text-center">
        <h2 class="section-heading text-uppercase" style="text-shadow: 2px 0 0 #000, -2px 0 0 #000, 0 2px 0 #000, 0 -2px 0 #000, 1px 1px #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000;color:white">Pagina Acquisto biglietto</h2>
  </div>
	
	<div class="row">
	  <div class="col-sm-3"></div>
    <div class="col-sm-6 containerAcquisto">
			    
		  <div class="data">
		    <label for="start"><b>Scegli una data: </b><br><input type="date" id="data_inizio"
	   			  min="2021-01-01" max="2021-12-31"></label>
	   			<button class="btn btn-outline-primary" onclick="selezionaTurno()">Controlla data</button> 
	    </div>
			
			<br><br>
			
			<form id="containerId" action="<%=response.encodeURL("AcquistaBigliettoServlet")%>" onsubmit="return validate()" method="GET">	
				
				<p><label><b>Turni disponibili</b><br></label>
				<select name="turno" id="ora" required>
				  <option>- Seleziona il turno -</option>
					<option id="opzione1" value="09:00:00">09:00</option>
					<option id="opzione2" value="11:15:00">11:15</option>
					<option id="opzione3" value="13:30:00">13:30</option>
					<option id="opzione4" value="15:45:00">15:45</option>
				</select>
				</p>
				
				<br>
	
	      <%if(utente.getTipo().equals("reg")){ %>
	      <input type="hidden" name="mode" value="registrato">
	      <input type="hidden" name="email" value="<%=utente.getEmail()%>">
        <input type="hidden" name="nome" value="<%=utente.getNome()%>">
        <input type="hidden" name="cognome" value="<%=utente.getCognome()%>">
	      <%} else { %>
	      <input type="hidden" name="mode" value="biglietteria">
	      <%} %>
	      
	      <input type="hidden" name="data_inizio" id="dataForm">
	      <%if(utente.getTipo().equals("biglietteria")){ %>
				<p><label><b>Nome titolare carta </b><br><input type="text" name="nometitolare" pattern="([A-Za-z ]{5,36})"
						title="Il campo nome titolare non rispetta il formato o la lunghezza" required></label></p>
			<%} %>			
				<p><label><b>Numero carta </b><br><input type="text" name="numerocarta" pattern="([0-9]{12})" 
						title="Il campo numero carta non rispetta il formato o la lunghezza" required></label></p>
				<p><label><b>Scadenza </b><br><input type="text" name="scadenza" id="scadenza" pattern="([0-1]{1}[0-9]{1}\/[0-9]{2})" 
						title="Il campo scadenza non rispetta il formato o la lunghezza" required></label></p>
						<p id="textarea-error" class="help-block text-danger"></p>
				<p><label><b>CVV</b><br><input type="text" name="cvv" pattern="([0-9]{3})" 
						title="Il campo CVV non rispetta il formato o la lunghezza" required></label></p>
				
				<br>
				<p><input type="submit" id="convalida" class="card-link btn btn-outline-primary" value="Acquista" ></p>
				
			</form>
		</div>
		<div class="col-sm-3"></div>
	</div>
	<footer id="footerPageBiglietti"><jsp:include page="Footer.jsp"></jsp:include></footer>
</body>
<script>

function validate() {
	
	var data_scadenza = document.getElementById('scadenza').value;
	
	var arr1 = data_scadenza.split("/");
	
	var mese = Number(arr1[0]);
	
	if (mese < 1 || mese > 12) {
		document.getElementById('textarea-error').innerHTML='Mese non valido';
		return false;
	}
	
	var anno = Number(arr1[1]);
	
	anno += 2000;
	
	var today = new Date();
	
	var cur_anno = Number(today.getFullYear());
	
	if (anno < cur_anno) {
		document.getElementById('textarea-error').innerHTML='Carta scaduta';
		return false;
	}
	
	if (anno == cur_anno) {
		var cur_mese = Number(today.getMonth()+1);
		if (mese < cur_mese) {
			document.getElementById('textarea-error').innerHTML='Carta scaduta';
			return false;
		}
	}
	return true;
}

function selezionaTurno(){

	  var date = new Date($('#data_inizio').val());
	  var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		$("#containerId").css("display", "block");
		$("#dataForm").val($('#data_inizio').val());
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "ControlloDataServlet?day=" + day + "&month=" + month
				+ "&year=" + year, false);
		xhr.setRequestHeader("connection", "close");

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var array = xhr.responseText.split("&");
				
				if(array[0] == "false")
					$("#opzione1").css("display","none");
				else
					$("#opzione1").css("display", "block");

				if (array[1] == "false")
					$("#opzione2").css("display", "none");
				else
					$("#opzione2").css("display", "block");

				if (array[2] == "false")
					$("#opzione3").css("display", "none");
				else
					$("#opzione3").css("display", "block");

				if (array[3] == "false")
					$("#opzione4").css("display", "none");
				else
					$("#opzione4").css("display", "block");
			}
		}
		xhr.send();
	}
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>