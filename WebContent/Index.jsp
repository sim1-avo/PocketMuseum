<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>Pocket Museum</title>

  <link rel="shortcut icon" href="assets/logo.png" type="image/x-icon">

  <link rel="stylesheet" href="assets/css/maicons.css">

  <link rel="stylesheet" href="assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

  <link rel="stylesheet" href="assets/css/bootstrap.css">

  <link rel="stylesheet" href="assets/css/mobster.css">

</head>


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

<body>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="#">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav ml-lg-5 mt-3 mt-lg-0">
      <!--  
        <li class="nav-item dropdown active">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Home</a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item active" href="index.html">Homepage 1</a>
            <a class="dropdown-item" href="index-2.html">Homepage 2</a>
          </div>
        </li>
        -->
        <li class="nav-item">
          <a class="nav-link" href="#">Events</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
      </ul>
      <%if(session.getAttribute("utente") == null && session.getAttribute("biglietteria") == null){ %>
      
      <div class="ml-auto my-2 my-lg-0">
        <a href="./Login.jsp" >
            <button class="btn btn-primary rounded-pill" h> Login</button>
        </a>
      </div>
      
		<%}else{ %>
		<div class="ml-auto my-2 my-lg-0">
        <button class="btn btn-dark rounded-pill"> <a href="<%=response.encodeRedirectURL("./LogoutServlet")%>">Logout</a></button>
      </div>
		<%} %>
		        	
      
    </div>
  </div>
</nav>
  
<div class="page-hero-section bg-image hero-home-1" style="background-image: url(assets/img/bg_hero1.png);">
  <div class="hero-caption pt-5">
    <div class="container h-100">
      <div class="row align-items-center h-100">
        <div class="col-lg-6 wow fadeInUp">
          <h1 class="mb-4" style= "color:darkgoldenrod">Explore our museum!</h1>
          <p class="mb-4">A new way of getting to know art. <br> A new museum experience.</p>
          
          			<%
				if (session.getAttribute("guida") == null && session.getAttribute("biglietteria") == null) {
			%>
			<%if (session.getAttribute("codiceValido") == null) { %>
			<input type="text" class="mb-4 fw-normal" id="inputPassword2" name="inputCode" placeholder="Insert your code">
          <a href="#" class="btn btn-primary rounded-pill">Start the tour</a>
          <%} else {%>
				<button type="button" style="color:black;width:90%"  onclick="window.location.href='CercaOpera?search='" class="btn btn-primary mb-2">Continua la tua visita!</button>
				<%}} %>
        </div>
        <div class="col-lg-6 d-none d-lg-block wow zoomIn">
          <div class="img-place mobile-preview shadow floating-animate">
            <img src="assets/img/app_preview_1.png" alt="">
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

</body>
</html>



