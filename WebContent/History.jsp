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

                    <div id="alltkt">

                    <%
                    ArrayList<AcquistaBean> acquista = new ArrayList<AcquistaBean>();
                    AcquistaModelDM adao= new AcquistaModelDM();
                    acquista = (ArrayList<AcquistaBean>)adao.doRetrieveAllByEmail(utente.getEmail());
                    int i=1;
                    %>
                    <% for(AcquistaBean a : acquista){
                    	%>

                    <div id="ticket">
                        <div id="left-tkt">
                            #<%=i%>
                        </div>

                        <div id="right-tkt">
                        <b><%=a.getCodice() %></b>
                        <br>
                        <b>Acquistato il</b> <%=a.getDataAcquisto() %>
                        <br>
                        <b>Inizio turno</b> <%=a.getInizioTurno()%>
                        <br>
                        <b>Fine turno</b> <%=a.getFineTurno()%>
                        <br>
                        <b>Costo</b> <%=a.getCosto()%>&euro;
                        </div>
                    </div>

                    	<%
                    	i++;
                    }
                    %>
                    </div>


                </div>
            </div>
      </div>
      <!--FINE SINISTRA -->

      <!-- DESTRA -->
      <div id="UserImg" class="col-lg-5 py-3">
         <div class="img-place mobile-preview shadow wow zoomIn">
              <img src="assets/img/history.svg" alt="">
         </div>
      </div>

      <!--FINE DESTRA -->
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