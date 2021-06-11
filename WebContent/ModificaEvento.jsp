<%@page import="model.utente.UtenteBean"%>
<%@ page import="model.evento.EventoBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%

    if(session.getAttribute("guida")==null){
        response.sendRedirect("Log.jsp");
        return;
    } else if (request.getAttribute("evento") == null) {
        response.sendRedirect("GuidaPersonalPage.jsp");
        return;
    }
    EventoBean evento = (EventoBean) request.getAttribute("evento");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="assets/css/maicons.css">

      <link rel="stylesheet" href="assets/vendor/animate/animate.css">

      <link rel="stylesheet" href="assets/vendor/owl-carousel/css/owl.carousel.min.css">

      <link rel="stylesheet" href="assets/css/bootstrap.css">

      <link rel="stylesheet" href="assets/css/mobster.css">

      <link rel="stylesheet" href="assets/css/flipbox.css">

      <link rel="stylesheet" href="assets/css/ticket.css">

      <link rel="stylesheet" href="assets/css/cardEvents.css">

      <link rel="stylesheet" href="assets/css/historyBiglietteria.css">

      <link rel="stylesheet" href="assets/css/AddChangeRemoveOpere.css">

      <link rel="stylesheet" href="css/ModificaEvento.css">

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

    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <meta charset="ISO-8859-1">
    <title>PocketMuseum</title>
</head>

<body>
<%if(session.getAttribute("guida") != null){ %>

<nav class="navbar navbar-expand-lg navbar-light navbar-floating">
  <div class="container">
    <a class="navbar-brand" href="GuidaPP.jsp">
      <img src="assets/logo.png" alt="" width="40">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
         <div class="ml-auto my-2 my-lg-0">
                <a href="GuidaPP.jsp" ><button class="btn btn-primary rounded-pill" h> Indietro </button></a>
         </div>
    </div>
  </div>
</nav>
<%}%>
<div class="position-realive bg-image" style="background-image: url(assets/img/pattern_1.svg);">
  <div class="page-section">
    <div class="container">
      <div class="row">
      <!-- SINISTRA -->
          <div id="start-left-tour" class="col-lg-6 wow fadeInUp">
           <br>
           <center><h4 style="color:darkgoldenrod"> Modifica Evento: <br> <b> <%= evento.getNome()%></b></h4></center>
           <form enctype="multipart/form-data" id="editEvent" name="sentMessage" method="post" action="ModificaEvento" onsubmit="return validate()" style="text-align-last: center;">
                      <input class="mb-4 fw-normal" style="width: -webkit-fill-available;" name="Nome" id="name" type="text" placeholder="Nome*" pattern="([\w\W]{10,100})"
                             title="Il campo nome non rispetta il formato o la lunghezza" required="required" data-validation-required-message="Inserisci nome evento."
                             value="<%= evento.getNome()%>" >
                      <p class="help-block text-danger"></p>
                      <div id="date" style="display: flex;">
                          <input class="mb-4 fw-normal" style="width: -webkit-fill-available;" name="data_inizio" id="datain"
                                 type="date" placeholder="Data Inizio*" required="required"
                                 data-validation-required-message="Inserisci data inizio dell'evento."
                                 value="<%=evento.getCompatibleDataInizioInput()%>">
                          <p class="help-block text-danger"></p>

                          <input class="mb-4 fw-normal" style="width: -webkit-fill-available;" type="date" name="data_fine"
                                 id="datafin" placeholder="Data Fine" required="required"
                                 data-validation-required-message="Inserisci data fine dell'evento."
                                 value="<%=evento.getCompatibleDataFineInput()%>">
                          <p class="help-block text-danger"></p>
                          <p id="date-error" class="help-block text-danger error-field"></p>
                      </div>
                          <textarea rows="2" style="width: -webkit-fill-available;"
                                    class="mb-4 fw-normal" id="descrizione"
                                    name="Descrizione" placeholder="Descrizione evento*"
                                    required="required" data-validation-required-message="Inserisci descrizione dell'evento."><%= evento.getDescrizione()%>
                          </textarea>
                          <p id="textarea-error" class="help-block text-danger error-field"></p>

                          <b>Cambia Immagine (opzionale)</b>
                          <input name="Immagine" type="file"
                                 class="form-control-file" id="loadFile">
                          <p id="image-error" class="help-block text-danger error-field"></p>

                          <div id="success"></div>
                          <button class="btn btn-primary rounded-pill" id="sendMessageButton" type="submit">Modifica Evento</button>

                  <input type="hidden" name="idEvent" id="idEvent" value="<%= evento.getId() %>">
              </form>
          </div>

          <!-- FINE SINISTRA  -->

          <!-- DESTRA -->
              <div class="col-lg-6 d-none d-lg-block">
                <div class="img-place mobile-preview shadow floating-animate">
                  <img class="wow zoomIn" src="assets/img/dinosaur.svg" alt="">

                </div>
              </div>
          <!-- FINE DESTRA -->

      </div>
    </div>
  </div>
</div>


</body>
<script>
    function validate(){
        $(".error-field").text("");

        var data_iniziale = document.getElementById('datain').value;
        var data_finale = document.getElementById('datafin').value;

        var arr1 = data_iniziale.split("-");
        var arr2 = data_finale.split("-");

        var d1 = new Date(arr1[0],arr1[1]-1,arr1[2]);
        var d2 = new Date(arr2[0],arr2[1]-1,arr2[2]);

        var r1 = d1.getTime();
        var r2 = d2.getTime();

        if(r1>r2) {
            document.getElementById('date-error').innerHTML='Data fine evento antecedente a quella di inizio';
            return false;
        }

        var stringa = document.getElementById('descrizione').value;

        if(!stringa.match(/^[\W\w]{10,5000}$/)){
            document.getElementById('textarea-error').innerHTML='Lunghezza non rispettata';
            return false;

        }

        var img = document.getElementById('loadFile').value;

        if (img.length == 0) {
            return true;
        }

        var file = document.getElementById('loadFile');
        var str = img.substr(img.length - 4, 4).toUpperCase();
        if(!(str == ".JPG" || str == ".PNG")) {
            document.getElementById('image-error').innerHTML='Formato immagine non rispettata';
            return false;
        } else {
            document.getElementById('image-error').innerHTML='';
        }
        if(file.files.item(0).size>1048576) {
            document.getElementById('image-error').innerHTML='Grandezza immagine non rispettata';
            return false;
        } else {
            document.getElementById('image-error').innerHTML='';
        }

        return true;
    }

</script>




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
</html>