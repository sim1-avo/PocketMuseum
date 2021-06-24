<%if(session.getAttribute("utente")!=null){

response.sendRedirect(response.encodeRedirectURL("Index.jsp"));
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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>Pocket Museum - Login</title>

<link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">
</head>
<body>

<%@include file="NavigationBar.jsp"%>

<div class="page-hero-section bg-image hero-home-2" style="background-image: url(assets/img/mah3.png);">
  <div class="hero-caption">
    <div class="container fg-white h-100">
      <div class="row align-items-center h-100">

        <!-- LOGIN -->

          <form  id="logform" class="box" action="javascript:controlloPassword()">
			<div class="col-lg-6 wow fadeInUp">
			<h1 class="mb-4 fw-normal">Pocket Museum</h1>
            <input class="mb-4 fw-normal" type="text" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$"
				title="Il campo email è errato" required placeholder="email">
		    <br>

		    <input class="mb-4 fw-normal" type="password" id="password" name="Password" pattern="([\w\W]{8,32})"
				title="Il campo password è errato" required placeholder="Password">
				<p id="password-error" style="color:red"></p>
            <br>
            <a class="forgot text-muted" onclick="showFormPass()" href="#">Password dimenticata?</a>

			<br>
			<input class="btn btn-primary rounded-pill" type="submit" name="" value="Login" href="#">
  		   </div>
  		   </form>

        <!-- FINE LOGIN -->

        <!-- REGISTRAZIONE -->


        <form action="RegistrazioneServlet" method="get" id="regform" class="box wow fadeInUp" style="display: none; border-radius: 1.2em"  onsubmit="return validate()">

                    <h1 class="mb-4 fw-normal">Registrati</h1>
                      <input class="mb-4 fw-normal" type="text" name="Nome" pattern="([A-Za-z]{1,19})"
                         title="Il campo nome non rispetta il formato o la lunghezza" required placeholder="nome">

                      <input class="mb-4 fw-normal" type="text" name="Cognome"pattern="([A-Za-z']{1,19})"
                         title="Il campo cognome non rispetta il formato o la lunghezza" required placeholder="cognome">

                      <input class="mb-4 fw-normal" type="text" name="CF" pattern="([A-Z0-9]{16})"
                         title="Il campo CF non rispetta il formato o la lunghezza" required placeholder="codice fiscale">

                      <input class="mb-4 fw-normal" type="text" id="email" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$"
                         title="Il campo email non rispetta il formato o la lunghezza" required placeholder="email" onblur="checkEmail()">
                      <p id="email-error" style="color: red"></p>
                      <p id="email-length-error" class="help-block text-danger"></p>

                      <input class="mb-4 fw-normal" type="password" id="pass1" name="Password" pattern="([\w\W]{8,31})"
                         title="Il campo password non rispetta il formato o la lunghezza" required
                           	placeholder="password">

                      <input class="mb-4 fw-normal" type="password" id="pass2"
                         name="VerificaPassword" pattern="([\w\W]{8,31})"
                           	title="Il campo password non rispetta il formato o la lunghezza" required placeholder="verifica password">
                      <p id="textarea-error" class="help-block text-danger"></p>

                      <a onclick="nascondireg()" class="forgot text-muted" href="#">
                        Sei registrato? Accedi!</a> <br>

        			  <input class="btn btn-primary rounded-pill" id="regbutton"
        				type="submit" name="" value="Registrati" href="#">



        </form>

        <!-- FINE REGISTRAZIONE -->

        <!-- RECUPERA PASSWORD -->

        <form style="border-radius: 1.2em; display: none"
        	onsubmit="messaggioRecupera()" id="passForm" method="post"
        		class="box wow fadeInUp" action="RecuperaPassword">

            <h3 style="color: white">Recupera password</h3>
            <br> <br>

            <input class="mb-4 fw-normal" type="text" name="email" pattern="([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$"
        	    title="Il campo email non rispetta il formato o la lunghezza" required
        		    placeholder="inserisci la tua email">
            <input class="btn btn-primary rounded-pill" id="invia" type="submit" name="" value="Invia" href="#">
            <br>
            <a onclick="nascondireg()" class="forgot text-muted" href="#"> &#8592; Torna indietro e accedi</a>
        		<br>
        </form>

        <!-- FINE RECUPERA PASSWORD -->


        <div class="col-lg-6 d-none d-lg-block wow zoomIn">
          <div class="img-place mobile-preview shadow floating-animate">
          	<img src="assets/img/app_preview_1.png" alt="">
          </div>          
        </div>
      </div>
    </div>
  </div>
</div>

<div class="page-section no-scroll">
  <div class="container">
    <h2 class="text-center wow fadeIn">Our Main Features</h2>

    <div class="row justify-content-center mt-5">
      <div class="col-lg-10">
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-4 py-3 wow fadeInLeft">
            <div class="card card-body border-0 text-center shadow pt-5">
              <div class="svg-icon mx-auto mb-4">
                <img style="filter:sepia(1)" src="assets/img/icons/payment.png" alt="">
              </div>
              <h5 class="fg-gray">Secure Payment</h5>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 py-3 wow fadeInUp">
            <div class="card card-body border-0 text-center shadow pt-5">
              <div class="svg-icon mx-auto mb-4">
                <img style="filter:sepia(1)" src="assets/img/icons/customizable.png" alt="">
              </div>
              <h5 class="fg-gray">Enjoy the Art</h5>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 py-3 wow fadeInRight">
            <div class="card card-body border-0 text-center shadow pt-5">
              <div class="svg-icon mx-auto mb-4">
                <img style="filter:sepia(1)" src="assets/img/icons/concept.png" alt="">
              </div>
              <h5 class="fg-gray">Discover our events</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>

<script src="assets/js/login.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

</body>
</html>