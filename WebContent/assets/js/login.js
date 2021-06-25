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
       	   		 document.getElementById("email-error").innerHTML="Utente gi� registrato";
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
		if(stringa === "Utente gi� registrato") {
			return  false;
		}

		return true;

	}

	function messaggioRecupera(){
		if(alert("Se l'indirizzo di posta elettronica � presente nei nostri sistemi, riceverai a breve una mail con la tua password")){}
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