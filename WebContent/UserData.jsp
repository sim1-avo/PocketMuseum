
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PocketMuseum</title>
</head>
	<body>



<div class="position-realive bg-image" style="background-image: url(assets/img/pattern_1.svg);">
<div class="page-section">
  <div class="container">
    <div class="row">
    <!--SINISTRA -->
      <div class="col-lg-6 py-3 mt-lg-5">
            <div class="iconic-list">
                <div class="iconic-item wow fadeInUp">
                    <div class="flip-box">
                            <div class="flip-box-inner">

                              <div class="flip-box-front">
                              <% String nome =utente.getNome();
                              	 char n=nome.charAt(0);
                              	 String cognome =utente.getCognome();
                              	 char c=cognome.charAt(0);
                              %>
                                  <div class="marginborder">

                                    <div class="circleUser">
                                        <%=n%><%=c%>
                                    </div>

                                    <div class= "userData">
                                    <h1 style = "color:white"> <%=utente.getNome()%> <%=utente.getCognome()%> </h1>
                                    <p>E-mail: <%=utente.getEmail()%></p>
                                    <p>CF: <%=utente.getCf()%></p>
                                    </div>
                                   </div>
                              </div>

                                  <div class="flip-box-back">
                                    <img src="assets/logo.png" alt="" width="100">
                                    <h2 style = "color:#ba8600">PocketMuseum</h2>
                                    <p id="card">card</p>
                                  </div>
                            </div>

                    </div>
                    <br>
                    <p>Stanco della vecchia password? <br> Cambiala ora!</p>
                        <button id="changepsw" class="btn btn-primary rounded-pill" onclick="ShowChangePsw() ">Change</button>
                    <button id="annullachangepsw"class="btn btn-primary rounded-pill" onclick="AnnullaChangePsw() ">Annulla</button>



                </div>
            </div>
      </div>
      <!--FINE SINISTRA -->

      <!-- DESTRA -->
    <div id="ChangeData" class="col-lg-5 py-3">
      <form action="ModificaDati" style = "padding: 20px" method="get">
          <input type="hidden" value="psw" name="richiesta">
           <label> Current password </label>
           <br>
           <input class="mb-4 fw-normal" type="password" class="form-control" name="vecchio" pattern="([\w\W]{8,32})"
              title="Il campo vecchia password è errato">
            <br>
           <label >New password</label>
           <br>
           <input class="mb-4 fw-normal" type="password" class="form-control" name="nuovo" pattern="([\w\W]{8,32})"
      		  title="Il campo nuova password è errato">
            <br>
      	    <button type="submit" class="btn btn-primary rounded-pill" value="Modifica" >Save changes</button>

      </form>
    </div>
      <div id="UserImg" class="col-lg-5 py-3">
         <div class="img-place mobile-preview shadow wow zoomIn">
              <img src="assets/img/Placeholder-bro.svg" alt="">
         </div>
      </div>

      <!--FINE DESTRA -->
    </div>
  </div>
</div>

</div>

<script>

</script>

<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>

<script src="assets/js/utentepp.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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

</body>
</html>