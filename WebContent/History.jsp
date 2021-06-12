<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PocketMuseum</title>
</head>

<body>
<%@include file="NavigationBar.jsp"%>
<div class="position-realive bg-image" style="background-image: url(assets/img/pattern_1.svg); ">
    <div class="page-section">
        <div class="container wow fadeInUp">

            <!--UTENTE -->
            <% if(session.getAttribute("biglietteria")==null){%>
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
                                        <b style="color:white"><%=a.getCodice() %></b>
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
                  <div id="UserImg" class="col-lg-5 py-3 shadow floating-animate">
                     <div class="img-place mobile-preview  shadow wow zoomIn">
                          <img src="assets/img/history.svg" alt="">
                     </div>
                  </div>

                  <!--FINE DESTRA -->

                </div>
                  <!-- FINE UTENTE -->
            <%}else{%>

                  <!--BIGLIETTERIA -->

                  <div id="research" class="container">

                        <div class= "row d-flex justify-content-center" style="padding-bottom:20px;">
                            <div  style="padding-top: 20px;min-width: 300px;">
                                Da: <input type="date" id="fromDay" class="ora">
                            </div>
                            <div  style="padding-top: 20px; min-width: 300px;">
                                Al: <input type="date" id="toDay" class= "ora">
                            </div>
                            <div class= "col-md-4 d-flex justify-content-center" style="min-width: 300px;padding-top: 20px;">
                                <button type="button" class="btn btn-primary rounded-pill" style="width:150px;" onclick="recuperaBiglietti()">Cerca</button>
                            </div>
                        </div>

                </div>

                <div id="previewB" class="row">
                    <!--SINISTRA -->
                      <div class="col-lg-6 py-3 mt-lg-5">
                      <h1 class="mb-4" style= "color:darkgoldenrod">Storico degli acquisti!</h1>
                      <p class="mb-4"> Visualizza facilmente gli acquisti! <br> Seleziona il periodo di tempo per visualizzare il relativo incasso.

                      </div>
                    <!-- DESTRA -->
                      <div id="UserImg" class="col-lg-5 py-3 shadow floating-animate">
                           <div class="img-place mobile-preview shadow wow zoomIn">
                                <img src="assets/img/Revenue-bro.svg" alt="">
                           </div>
                      </div>
                </div>

                <div id="chartContainer" class="container">

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card-chart" style="margin-top: 40px;">
                                        <div class="card-body-chart" >
                                            <canvas id="chart" height="100"></canvas>
                                        </div>
                                    </div>
                                </div>
                           </div>

                           <div class="row">
                                <div class="col-md-12">
                                    <div class="card-chart" style="margin-top: 40px;">
                                        <div class="card-body-chart" >
                                            <b id="totaliVenduti">Biglietti totali venduti:</b>
                                            <br>
                                            <b id="totaleGuadagnato">Ricavato:</b>
                                        </div>
                                    </div>
                                </div>
                           </div>
                </div>

                <!-- INFO BIGLIETTI -->
                <div id="infotkt" class="container">
                        <div class="row">
                            <div class="table-responsive" style="height: 300px" id="tableContainer" >
                                <table class="table table-striped table-dark">
                                    <thead style="position:sticky;">
                                        <tr>
                                          <th scope="col">#</th>
                                          <th scope="col">CODICE</th>
                                          <th scope="col">DATA ACQUISTO</th>
                                          <th scope="col">UTENTE</th>
                                          <th scope="col">DATA INIZIO TURNO</th>
                                          <th scope="col">DATA FINE TURNO</th>
                                          <th scope="col">COSTO</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table"></tbody>
                                </table>
                            </div>
                        </div>
                </div>


            <%}%>
      <!--FINE BIGLIETTERIA -->
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>

<script src="assets/js/jquery-3.5.1.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="assets/vendor/wow/wow.min.js"></script>

<script src="assets/js/mobster.js"></script>

<script src="assets/js/utentepp.js"></script>

<script src="assets/js/historyBiglietteria.js"></script>


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