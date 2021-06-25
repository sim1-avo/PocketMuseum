<%@ page  import=" model.evento.EventoBean, java.util.ArrayList ,java.sql.Date,java.sql.Timestamp"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Pocket Museum - Events</title>

      <link rel="stylesheet" href="assets/css/bootstrap.css">

      <link rel="stylesheet" href="assets/css/mobster.css">

      <link rel="stylesheet" href="assets/css/cardEvents.css">


</head>
<body>
<%@include file="NavigationBar.jsp"%>


<div class="page-hero-section bg-image hero-home-1" style="background-image: url(assets/img/bg_hero1.png); height: auto;">

<div class="page-section">
  <div class="container">

  <%if(request.getSession(false).getAttribute("guida")!=null) {%>
      <center>
      <button class="btn btn-primary rounded-pill" id="creaEvento">Crea Evento 	&#11167;</button>
      </center>

      <form style="display: grid;    padding: 7px;     background-color: #6b6a6838; border-radius: 20px;" enctype="multipart/form-data" id="contactForm" name="sentMessage" method="post" action="InserisciEventoServlet" onsubmit="return validate()">
          Nome:<input class="rounded-pill" style="border-style: none;" name="Nome" id="name" type="text" placeholder="Nome*" pattern="([\w\W]{10,100})"
            title="Il campo nome non rispetta il formato o la lunghezza" required="required" data-validation-required-message="Inserisci nome evento.">
          <p class="help-block text-danger"></p>

          Data Inizio:<input class="rounded-pill" style="border-style: none;" name="data_inizio" id="datain" type="date" placeholder="Data Inizio*" required="required" data-validation-required-message="Inserisci data inizio dell'evento.">
          <p class="help-block text-danger"></p>

          Data Fine:<input class="rounded-pill" style="border-style: none;" type="date" name="data_fine" id="datafin" placeholder="Data Fine" required="required" data-validation-required-message="Inserisci data fine dell'evento.">
          <p class="help-block text-danger"></p>
          <p id="date-error" class="help-block text-danger error-field"></p>

          Descrizione:<textarea style="border-radius: 15px;" id="descrizione" name="Descrizione" placeholder="Descrizione evento*" required="required" data-validation-required-message="Inserisci descrizione dell'evento."></textarea>

          <p id="textarea-error" class="help-block text-danger error-field"></p>

          Copertina:<input style="color:white" name="Immagine" required type="file" class="form-control-file" id="loadFile">
          <p id="image-error" class="help-block text-danger error-field"></p>

          <div id="success"></div>
          <button class="btn btn-primary rounded-pill" style="justify-self: center" id="sendMessageButton" type="submit">CREA</button>
          </div>
      </form>
  <%}%>

   <%

       ArrayList<EventoBean> eventi = (ArrayList<EventoBean> ) request.getAttribute("eventi");

       if(eventi == null) {
           response.sendRedirect(response.encodeRedirectURL("./MostraEventiServlet"));
           return;
       }



   if(eventi.size()!=0){
   %>
    <div class="row justify-content-center mt-5 wow zoomIn">
   <%

    for(EventoBean evento : eventi){
           Timestamp ts=evento.getDataInizio();
           Date dataInizio=new Date(ts.getTime());
           Timestamp ts2=evento.getDataFine();
           Date dataFine=new Date(ts2.getTime());
        %>
        <div class="card floating-animate">
          <img class="imgEvento" src="data:image/png;base64,<%=evento.getImmagine()%>"  alt="">
          <b style="color:darkgoldenrod"><%=evento.getNome()%></b>
          <p class="title">Inizio: <%=dataInizio%> <br> Fine: <%=dataFine%></p>
          <p style="color:white; background-color: darkgoldenrod">PocketMuseum</p>

          <%if(request.getSession(false).getAttribute("guida")!=null) {%>
            <div id="cr" style="margin-bottom: 9px">
            <a class="changeRemove" href="ModificaEventoPage?idEvent=<%= evento.getId() %>">Modifica</a>
            <a class="changeRemove" href="EliminaEvento?idEvent=<%= evento.getId() %>">Elimina</a>
            </div>
          <%}%>

        </div>

        <%}}else{%>
            <tr><td><h3 style="text-align: center">Nessun evento in programma</h3></td></tr>
        <%}%>
           </div>



     </div>
  </div>
</div>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
 $("#contactForm").hide();
  $("#creaEvento").click(function(){
    $("#contactForm").toggle();
  });
});
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