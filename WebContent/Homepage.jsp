<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import=" java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%


if(session.getAttribute("guida")!=null){ 

response.sendRedirect(response.encodeRedirectURL("GuidaPersonalPage.jsp"));
return;
}

if(session.getAttribute("biglietteria")!=null){ 
response.sendRedirect(response.encodeRedirectURL("BiglietteriaPersonalPage.jsp"));
return;
}


	if ((request.getParameter("fineTurno")) != null) {
%>
<script type='text/javascript'>
	if (alert('La sua visita è giunta al termine, grazie e arrivederci')) {
	}
</script>
<%
	}
%>

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>PocketMuseum</title>
<link rel="icon" type="image/x-icon" href="img/C09_Logo.png" />
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
<link href="css/Homepage.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<jsp:include page="NavBar.jsp"></jsp:include>

	<div class="row">
		<div class="col-md-6" id="inserimentoCodice">
			<%
				if (session.getAttribute("guida") == null && session.getAttribute("biglietteria") == null) {
			%>
			<%if (session.getAttribute("codiceValido") == null) { %>
					<h5>Inserisci qui il tuo codice per accedere alla
						visita:</h5><br>
					<label for="inputPassword2" class="sr-only"></label> <input
						style="float: right" type="text" class="form-control"
						id="inputPassword2" name="inputCode" placeholder="AAAA0123AAAA"><br><br><br>
					<button type="submit" id="codButton" class="btn btn-primary mb-2">Inizia!</button>
					<h4 style="color: red; display: none" id="prova">Codice
						non valido</h4><br><br>
				  <h5>Non hai ancora acquistato un biglietto?</h5><br>
			    <button type="button" style="color:black;width:90%" onclick="acquistaOra()" class="btn btn-primary mb-2">Acquista ora!</button>
			<%} else {%>
				<button type="button" style="color:black;width:90%"  onclick="window.location.href='CercaOpera?search='" class="btn btn-primary mb-2">Continua la tua visita!</button>
				<%}} %>
			
		</div>
		
		<div class="col-md-6" id="descrizioneMuseo">
		  <h5>Il nostro museo:</h5><br>
		  <img alt="" src="img/museo.jpg" width="100%" style="border-radius: 1.2em"><br><br>
			<h5>Il museo PocketMuseum è una raccolta, di manufatti
				relativi a uno o più settori della cultura (tra cui in particolare,
				per tradizione, l'arte e la scultura), della scienza e della tecnica. Lo statuto
				dell'International Council of Museums lo definisce un'istituzione
				permanente, al servizio della società e del
				suo sviluppo. Aperto al pubblico compie ricerche che riguardano le
				testimonianze materiali e immateriali dell'umanità e del suo
				ambiente, le acquisisce, le conserva, le comunica e, soprattutto, le
				espone a fini di studio, educazione e diletto.</h5>
		</div>
	</div>

	<!-- Footer-->

	<footer style="margin-top: 80px">
		<%@include file="Footer.jsp"%>
	</footer>

	<script src="js/scripts.js"></script>
	<script type="text/javascript">
	$(document).on(
			"click",
			"#codButton",
			function() {
				var codice = $("#inputPassword2").val();
				const regex=RegExp("[a-zA-Z]{4}[0-9]{4}[a-zA-Z]{4}");
				if(codice.length < 12 || codice.length > 12){
					$("#prova").fadeIn(1000);

					$("#prova").html("Codice non valido");

					$("#prova").fadeOut(3000);
					return;
				}else if(!regex.test(codice)){
					console.log(regex.test(codice));
					$("#prova").fadeIn(1000);

					$("#prova").html("Codice non valido");

					$("#prova").fadeOut(3000);
					return;
				}
				var currentdate = new Date();
				var datetime = currentdate.getFullYear() + "-"
						+ (currentdate.getMonth() + 1) + "-"
						+ currentdate.getDate() + " "
						+ (currentdate.getHours() < 10 ? '0' : '')
						+ currentdate.getHours() + ":"
						+ (currentdate.getMinutes() < 10 ? '0' : '')
						+ currentdate.getMinutes() + ":"
						+ (currentdate.getSeconds() < 10 ? '0' : '')
						+ currentdate.getSeconds();

				$("#data").val(datetime);
				console.log(datetime);
				$.get('/PocketMuseum_C09/CodiceServlet', {
					data : datetime,
					codice : codice
				}, function(responseText) {
					if (responseText == "non trovato") {

						$("#prova").fadeIn(1000);

						$("#prova").html("Codice non valido");

						$("#prova").fadeOut(3000);
					} else {
						window.location.href="./ListOpere.jsp";
					}
				});
			})
				
	 function acquistaOra(){
			  window.location.href = "./PageAcquisto.jsp";
	 }
	</script>





	<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
		integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>
</html>
