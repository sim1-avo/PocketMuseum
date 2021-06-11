<%@page import="model.biglietto.BigliettoModelDM"%>
<%@page import="model.biglietto.BigliettoModel, java.time.LocalDate "%>

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

        <!-- SINISTRA 1 -->
        <div id="start-left-tour" class="col-lg-6 wow fadeInUp">

			<%if (session.getAttribute("biglietteria") == null) { %>
                <h1 class="mb-4" style= "color:darkgoldenrod">Explore our museum!</h1>
                <p class="mb-4">A new way of getting to know art. <br> A new museum experience.</p>

                <%if (session.getAttribute("codiceValido") == null) { %>
                <input type="text" class="mb-4 fw-normal" id="inputPassword2" name="inputCode" placeholder="Insert your code">
                <button type="submit" id="codButton" class="btn btn-primary rounded-pill">Start the tour</button>
                <h4 style="color: red; display: none" id="prova"> Codice
                            non valido</h4><br><br>
                <%} else {%>
                    <button type="button" style="color:white;width:90%"  onclick="window.location.href='CercaOpera?search='" class="btn btn-primary rounded-pill">Continua la tua visita!</button>
                <%}%>
                <p class="mb-4">Come? Non hai ancora un biglietto? <br> Non sai cosa ti perdi, <b style="color:#ba8600">acquistalo</b> ora!</p>

            <%}else{ %>
                <h1 class="mb-4" style= "color:darkgoldenrod">Explore our museum!</h1>
                <h5 class="mb-4"> Acquista ora un biglietto!</h5>
            <%}%>


          <button type="button"  onclick="ShowBuyTicket()" class="btn btn-primary rounded-pill">Acquista biglietto</button>
        </div>
        <!-- FINE SINISTRA 1 -->

        <!-- SINISTRA 2 -->

                <div id="end-left-tour" class="col-lg-6 wow fadeInUp">

                <p style= "color:darkgoldenrod">&#x2190; <a id="tornaindietro" onclick="AnnullaShowBuyTicket()" >  Torna Indietro </a> </p>

                <div class="data">
                <b>Scegli una data: </b>
                <% LocalDate today = LocalDate.now();%>
                <input class="ora" type="date" id="data_inizio" min="<%=today%>" max="2021-12-31"></label>
                <button class="btn btn-primary rounded-pill" onclick="selezionaTurno()">Controlla data</button>
                </div>

                <form id="containerId" action="<%=response.encodeURL("AcquistaBigliettoServlet")%>" onsubmit="return validate()" method="GET">
                    <b>Turni disponibili</b>
                	<select  name="turno" class="ora" placeholder="Seleziona turno" required>
                	    <option> Seleziona il turno </option>
                		<option id="opzione1" value="09:00:00">09:00</option>
                		<option id="opzione2" value="11:15:00">11:15</option>
                		<option id="opzione3" value="13:30:00">13:30</option>
                		<option id="opzione4" value="15:45:00">15:45</option>
                	</select>
                	</p>


                	<%if(utente.getTipo().equals("reg")){ %>
                	<input type="hidden" name="mode" value="registrato">
                	<input type="hidden" name="email" value="<%=utente.getEmail()%>">
                    <input type="hidden" name="nome" value="<%=utente.getNome()%>">
                    <input type="hidden" name="cognome" value="<%=utente.getCognome()%>">
                	<%} else { %>
                	<input type="hidden" name="mode" value="biglietteria">
                	<%} %>

                    <p><label><b>Inserisci i dati della tua carta</b></label>
                	<input type="hidden" name="data_inizio" id="dataForm">
                	<%if(utente.getTipo().equals("biglietteria")){ %>
                	<p><label><b>Nome titolare carta </b><br>
                	<input class="mb-4 fw-normal" type="text" name="nometitolare" pattern="([A-Za-z ]{5,36})"
                		title="Il campo nome titolare non rispetta il formato o la lunghezza" required></label></p>
                	<%} %>

                	<div id="creditCard" class="wow zoomIn">
                        <img src="https://www.carteconto.org/wp-content/uploads/2018/04/carte-conto-mastercard.png " style="width:50px">
                        <h6> <b><%=utente.getNome()%> <%=utente.getCognome()%> </b> Credit Card</h6>
                        <br>
                        Numero carta
                        <input class="inputcard" type="text" name="numerocarta" pattern="([0-9]{12})"
                             title="Il campo numero carta non rispetta il formato o la lunghezza" required></label></p>
                            <div id="scadcvv">
                                Scadenza
                                <input class="inputcard" style="width: 100px" type="text" name="scadenza" id="scadenza" pattern="([0-1]{1}[0-9]{1}\/[0-9]{2})"
                                    title="Il campo scadenza non rispetta il formato o la lunghezza" required></label></p>
                                <p id="textarea-error" class="help-block text-danger"></p>

                                  CVV
                                <input class="inputcard" style="width: 46px" type="text" name="cvv" pattern="([0-9]{3})"
                                    title="Il campo CVV non rispetta il formato o la lunghezza" required></label></p>
                            </div>
                    </div>
                    <br>
            		Conferma pagamento <input type="submit" id="convalida" class="btn btn-primary rounded-pill" value="Acquista" >
                </form>


                </div>
                <!-- FINE SINISTRA 2 -->

        <!-- DESTRA 1-->
        <div class="col-lg-6 d-none d-lg-block">
          <div class="img-place mobile-preview shadow floating-animate">
            <img id="start-right-tour" class="wow zoomIn" src="assets/img/Art-bro.svg" alt="">
            <img id="end-right-tour" class="wow zoomIn" src="assets/img/buytkt.svg" alt="">

          </div>
        </div>
        <!-- FINE DESTRA 1 -->



      </div>
    </div>
  </div>

</div>




<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>

<script src="assets/js/utentepp.js"></script>

<script src="assets/js/buytkt.js"></script>

<script src="assets/js/codeTour.js"></script>


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