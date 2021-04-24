<%@page import="model.opera.OperaBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.utente.UtenteBean"%>
  <%
    
    if(session.getAttribute("guida")==null){
    	response.sendRedirect("Log.jsp");
    	return;
    }
    %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PocketMuseum</title>
	<link rel="icon" type="image/x-icon" href="./img/favicon.png">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" href="./css/GestioneOpera.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/PageOpera.css"/>
	
	
	
</head>
<body style="background-image: url(img/sfondo.jpg);background-repeat:repeat-y;background-size:100%">

	<%/*
	UtenteBean b = (UtenteBean) session.getAttribute("guida");
	if(b == null || !(b.getTipo().equals("guida"))){
		response.sendRedirect("./Homepage.jsp");
		return;
	}*/
	
	ArrayList<OperaBean> opere = (ArrayList<OperaBean>) request.getAttribute("opere");
	if(opere == null){
		response.sendRedirect(response.encodeRedirectURL("./RecuperaOpere"));
		return;
	}

	
	%>
	
	<%@include file="NavBar.jsp" %>
	<style>
	.form-control option:hover, #selectStato option:hover {
    box-shadow: 0 0 10px 100px #343a40 inset!important;
    color:white!important;
    opacity:0.85!important;
}

	
	
	
	
	</style>
	<container>
		<div class="row" style="opacity:0.85!important">
		    <div class="col-lg-6">
		    	<form id="formSelezione">
		    	<div class="form-group">
				    <label for="exampleFormControlSelect2">Seleziona opera: </label>
				    <select size="20" multiple class="form-control" id="exampleFormControlSelect2">
				    	<option onclick="nuovaOpera()">Aggiungi opera</option>
				    <%for(OperaBean bean: opere){ %>
				     	<option onclick="selezionaOpera(<%=bean.getId()%>)"><%=bean.getNome() %></option>
				    <%} %>
				    </select>
				</div>
		    	</form>
		    	<form action="RimuoviOpera" method="post" class="form-inline" id="rimuoviForm">
			  <div class="form-group mb-2">
			    <span style="color: white">Seleziona l'opera da rimuovere per riempire questo campo</span>
			  </div>
			  <div class="form-group mx-sm-3 mb-2">
			    <label for="inputPassword2" class="sr-only">Id opera: </label>
			    <input type="text" name="id" readonly class="form-control" id="idOpera2" placeholder="id">
			  </div>
			  <button type="submit" id="btnRimuovi" class="btn btn-primary mb-2">Rimuovi opera</button>
			</form>
		    	
		    </div>
		    <div class="col-lg-6">
		    	<form method="post"  enctype="multipart/form-data" action="GestioneOperaServlet" id="formOpera" onsubmit="return validate()">
				  <div class="form-group">
				  	<img id="immagineOpera" style="color:grey"alt="sostituire immagine">
				    <label>Immagine
				    <input type="file" class="form-control" name="immagine" id="loadFile" placeholder="">
				  	</label>
				  	 <p id="image-error" class="help-block text-danger"></p>
				  </div>
				  <div class="form-group">
				  	<img  id="copertinaOpera" style="color:grey" alt="sostituire immagine">
				    <label>Copertina
				    <input type="file" class="form-control" id="loadFile2" name="copertina" placeholder="">
				  	</label>
				  	 <p id="copertina-error" class="help-block text-danger"></p>
				  </div>
				  <div class="form-group">
				    <label>Nome opera
				    <input type="text" id="nomeOpera" class="form-control" name="nome" pattern="([a-zA-Z 0-9 , . ; : - ' à è ì í ò ù é  À Á È É Ì Ò Ù( )]{3,100})"
						title="Il campo nome opera non rispetta il formato o la lunghezza" required placeholder="La Gioconda">
				  	</label>
				  </div>
				  <div class="form-group">
				    <label>Autore opera
				    <input type="text" id="autoreOpera" required class="form-control" name="autore" pattern="([a-zA-Z 0-9 , . ; : - ' à è ì í ò ù é  À Á È É Ì Ò Ù]{3,50})"
						title="Il campo autore non rispetta il formato o la lunghezza" placeholder="Leonardo Da Vinci">
				  	</label>
				  </div>
				  <div class="form-group">
				  	<label id="statoOperaCorrente"></label><br>
				    <label>Seleziona stato opera: <br>
				    
				    <select id="selectStato" name="stato" required size="3">
					  <option value="visibile">Visibile</option>
					  <option value="nonVisibile">Non visibile</option>
					  <option value="inMod">Da modificare</option>
					</select>
				  	</label>
				  </div>
				  <div id="containerId" class="form-group">
				    <label>Id opera
				    <input type="text" id="idOpera" readonly class="form-control" name="id" placeholder="id" value="0">
				  	</label>
				  </div>
				  <div class="form-group">
				    <label>Descrizione
				    <textarea style="color:black;" name="descrizione" cols="100" rows="10" id="descrizioneOpera" required class="form-control" rows="3"></textarea>
				  	 <p id="textarea-error" class="help-block text-danger"></p>
				  	</label>
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Invia dati</button>
				</form>
		    </div>
		  </div>
	</container>
	
	<br><br>
	<div class="row">
	    <div class="col-sm-4">
	    </div>
	    <div class="col-lg-4">
	    	
	    </div>
	    <div class="col-sm-4">
	    </div>
	</div>
		  
	
	<footer>
		<%@include file="Footer.jsp" %>
	</footer>

</body>

<script>
	function validate(){
		var stringa = document.getElementById('descrizioneOpera').value;
		
		if(!stringa.match(/^[a-zA-Z 0-9 , . ' ; : " \n \- à è ì í ò ù é À, Á, È, É, Ì, Ò, Ù ( ) ? ]{5,5000}$/)){
			document.getElementById('textarea-error').innerHTML='Formato non rispettato';
			return false;
		}
		
		var img = document.getElementById('loadFile').value;
		
		if (img.length != 0) {
			var str = img.substr(img.length - 4, 4).toLowerCase();
			if(!(str == ".jpg" || str == ".png")) {
				document.getElementById('image-error').innerHTML='Formato immagine non rispettata';
				return false;
			}
			var dim = document.getElementById('loadFile').files[0].size;
			
			if(dim>1024*1024){
				document.getElementById('image-error').innerHTML='	Immagine troppo grande';
				return false;
			}
			
		}
		
		var copertina  = document.getElementById('loadFile2').value;
		
		if (copertina.length != 0) {
			var str2 = copertina.substr(copertina.length - 4, 4).toLowerCase();
			if(!(str2 == ".jpg" || str2 == ".png")) {
				document.getElementById('copertina-error').innerHTML='Formato copertina non rispettata';
				return false;
			}
			var dim2 = document.getElementById('loadFile2').files[0].size;
			
			if(dim2>1024*1024){
				document.getElementById('copertina-error').innerHTML='	Immagine troppo grande';
				return false;
			}
		}
		return true;
    }
	function nuovaOpera(){
		$('#btnRimuovi').prop('disabled', true);
		$("#containerId").css("display","none");
		$("#idOpera").val("0");
		$("#idOpera2").val("");
		$("#immagineOpera").css("display","none");
		$("#copertinaOpera").css("display","none");
		$("#nomeOpera").val("");
		$("#autoreOpera").val("");
		$("#statoOpera").val("");
		$("#descrizioneOpera").val("");
	}

	
	function selezionaOpera(id){
		$('#btnRimuovi').prop('disabled', false);
		$("#containerId").css("display","block");
		var xhr = new XMLHttpRequest();
		var stringa = "id=" + id + "&json=true";
		xhr.open("POST","CercaOperaById", false);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.setRequestHeader("connection","close");
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				
				var ob = JSON.parse(xhr.responseText);
				
				$("#nomeOpera").val(ob.nome);
				$("#autoreOpera").val(ob.autore);
				$("#statoOpera").val(ob.stato);
				$("#statoOperaCorrente").text("Stato opera corrente: " + ob.stato);
				$("#selectStato").val(ob.stato);
				$("#idOpera").val(ob.id);
				$("#idOpera2").val(ob.id);
				$("#descrizioneOpera").val(ob.descrizione);
			}
		}
		xhr.send(stringa);
		recuperaImmagini(id);
	}
	
	function recuperaImmagini(id){
		
		var xhr = new XMLHttpRequest();
		xhr.open("GET","RecuperaImmagini?id=" + id, false);
		xhr.setRequestHeader("connection","close");
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				
				var r = xhr.responseText;
				
				var inizio = r.indexOf("ciao");
				
				var immagine = r.substring(0,inizio - 1);
				var copertina = r.substring(inizio + 4,r.length-1);
				
				
				
				var stringImmagine =  "data:image/png;base64," + immagine;
				var stringCopertina =  "data:image/png;base64," + copertina;
				
				
				
				if(stringImmagine.length>30){
					document.getElementById("immagineOpera").src = stringImmagine;
					document.getElementById("immagineOpera").style.display = "block";
				}
				else {
					document.getElementById("immagineOpera").style.display = "none";
				}
				if(stringCopertina.length>30){
					document.getElementById("copertinaOpera").src = stringCopertina;
					document.getElementById("copertinaOpera").style.display = "block";
				}
				else {
					document.getElementById("copertinaOpera").style.display = "none";
				}
				
				
				
			}
		}
		xhr.send();		
	}

</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</html>