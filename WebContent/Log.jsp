
<%if(session.getAttribute("utente")!=null){ 

response.sendRedirect(response.encodeRedirectURL("Homepage.jsp"));
return;
}
if(session.getAttribute("guida")!=null){ 

response.sendRedirect(response.encodeRedirectURL("GuidaPersonalPage.jsp"));
return;
}

if(session.getAttribute("biglietteria")!=null){ 
response.sendRedirect(response.encodeRedirectURL("BiglietteriaPersonalPage.jsp"));
return;
}


%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>PocketMuseum</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">


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

<link rel="stylesheet" type="text/css" href="css/Login.css">
</head>
<body>


	<%@include file="NavBar.jsp"%>



	<div class="container">
		<div class="row d-flex justify-content-center" id="content" >
			<div class="col-md-6">
				<div>
					<form style="border-radius: 1.2em" id="logform" class="box" action="javascript:controlloPassword()">
						<h1>Login</h1>
						<p class="text-muted">Inserisci email e password</p>
						<input type="text" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$" 
								title="Il campo email è errato" required placeholder="email">
						<input type="password" id="password" name="Password" pattern="([\w\W]{8,32})"
						title="Il campo password è errato" required placeholder="Password">
						<p id="password-error" style="color:red"></p>
						<a class="forgot text-muted"
							onclick="showFormPass()" href="#">Password dimenticata?</a> <br>
						<a onclick="nascondilog()" class="forgot text-muted" href="#">Non
							sei registrato? Registrati!</a>
							 <input type="submit" name="" value="Login" href="#">
						<div class="col-md-12"></div>
					</form>

					<form action="RegistrazioneServlet" method="get" id="regform"
						class="box" style="display: none; border-radius: 1.2em" onsubmit="return validate()">
						<h1>Registrati</h1>
						<input type="text" name="Nome" pattern="([A-Za-z]{1,19})" 
						title="Il campo nome non rispetta il formato o la lunghezza" required placeholder="nome">
						<input type="text" name="Cognome"pattern="([A-Za-z']{1,19})" 
						title="Il campo cognome non rispetta il formato o la lunghezza" required placeholder="cognome">
						<input type="text" name="CF" pattern="([A-Z0-9]{16})" 
						title="Il campo CF non rispetta il formato o la lunghezza" required placeholder="codice fiscale">
						<input type="text" id="email" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$" 
								title="Il campo email non rispetta il formato o la lunghezza" required placeholder="email" onblur="checkEmail()">
								<p id="email-error" style="color: red"></p>
								<p id="email-length-error" class="help-block text-danger"></p>
						<input type="password" id="pass1" name="Password" pattern="([\w\W]{8,31})" 
						title="Il campo password non rispetta il formato o la lunghezza" required
							placeholder="password">
						<input type="password" id="pass2"
							name="VerificaPassword" pattern="([\w\W]{8,31})"
						title="Il campo password non rispetta il formato o la lunghezza" required placeholder="verifica password">
						<p id="textarea-error" class="help-block text-danger"></p>
						<a onclick="nascondireg()" class="forgot text-muted" href="#">Già
							sei registrato? Accedi!</a> <br>
						<input id="regbutton"
							type="submit" name="" value="Registrati" href="#">
						<div class="col-md-12"></div>

					</form>




					<form style="border-radius: 1.2em; display: none"
						onsubmit="messaggioRecupera()" id="passForm" method="post"
						class="box" action="RecuperaPassword">
						<h3 style="color: white">Recupera password</h3>

						<input type="text" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$" 
							title="Il campo email non rispetta il formato o la lunghezza" required
							placeholder="inserisci la tua email"> <input
							id="invia" type="submit" name="" value="Invia" href="#">

						<a onclick="nascondireg()" class="forgot text-muted" href="#">Accedi</a>
						<br>


					</form>
				</div>
			</div>
		</div>
	</div>






	<footer id="footer" style="margin-top: 40%">
		<%@include file="Footer.jsp"%>
	</footer>


</body>

<script>
	function controlloPassword(){
		
		var email = document.getElementById("logform").elements["email"].value;
		var password = document.getElementById("logform").elements["password"].value;
		var xhttp = new XMLHttpRequest();
        xhttp.open("GET","ControlloPassword?email="+email+"&password="+encodeURIComponent(password),true);
         
        xhttp.setRequestHeader("connection","close");
        
        xhttp.onreadystatechange = function() {
       	   	 if(xhttp.responseText === "true"){
       	   		window.location="LoginServlet?email="+email+"&Password="+encodeURIComponent(password);
       	   		document.getElementById("password-error").innerHTML="";
       	   	 }
       	   	 else document.getElementById("password-error").innerHTML="Email o Password Errata";
       	}
        	
        xhttp.send();
		
	}

	function checkEmail(){
		var email = document.getElementById("email").value;
		var xhttp = new XMLHttpRequest();
        xhttp.open("GET","UtenteDuplicatoControllo?email="+email,true);
         
        xhttp.setRequestHeader("connection","close");
        
        xhttp.onreadystatechange = function() {
       	   	 if(xhttp.responseText === "false"){
       	   		 document.getElementById("email-error").innerHTML="Utente già registrato"; 
       	   		 return false;
       	   	}
       	   	 else{
       	   		document.getElementById("email-error").innerHTML=""; 
       	   		 return true;
       	   		 }
        }
        	
        xhttp.send();
		
	}
	
	function validate() {
		
		var email = document.getElementById('email').value;
		
		if (email.length < 12 || email.length >31) {
			document.getElementById('email-length-error').innerHTML='Lunghezza email non valida';
			return false;
		}
		
		var stringa1 = document.getElementById('pass1').value;
		var stringa2 = document.getElementById('pass2').value;
		
		if(stringa1 != stringa2) {
			document.getElementById('textarea-error').innerHTML='Le due password non corrispondono';
			return false;
		}
		document.getElementById('textarea-error').innerHTML='';
		
		
		var stringa = document.getElementById("email-error").innerHTML;
		if(stringa === "Utente già registrato") {
			return  false;
		}
		
		return true;
		
	}
		
	
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<script>
	
	function messaggioRecupera(){
		if(alert("Se l'indirizzo di posta elettronica è presente nei nostri sistemi, riceverai a breve una mail con la tua password")){}
		else{}
	}
	
	function showFormPass(){
		
		
		$("#passForm").show();
		$("#logform").hide();
		$("#regform").hide();
		$("#footer").css("margin-top", "37%");
	}
	
	function nascondilog(){
		$("#logform").hide();
		$("#regform").show();
		$("#footer").css("margin-top", "50%");
	}
	function nascondireg(){
		$("#passForm").hide();
		$("#regform").hide();
		$("#logform").show();	
		$("#footer").css("margin-top", "40%");
	}
</script>

</html>
