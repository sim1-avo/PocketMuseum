<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Pocket Museum - Events</title>
</head>
<body>

<div class="position-realive bg-image" style="background-image: url(assets/img/pattern_1.svg);">
<div class="page-section">
  <div class="container">
   <%
   ArrayList<EventoBean> eventi = new ArrayList();
   EventoModelDM model = new EventoModelDM();
   eventi= (ArrayList<EventoBean> ) model.doRetrieveAll("DESC");


   if(session.getAttribute("biglietteria")==null && session.getAttribute("utente")==null && session.getAttribute("guida")==null){
       response.sendRedirect("Log.jsp");
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
           Date dataFine=new Date(ts.getTime());
        %>
        <div class="card floating-animate">
          <img class="imgEvento" src="data:image/png;base64,<%=evento.getImmagine()%>"  width: 100px;border-radius: 20px;" alt="">
          <b style="color:darkgoldenrod"><%=evento.getNome()%></b>
          <p class="title">Inizio: <%=dataInizio%> <br> Fine: <%=dataFine%></p>
          <p style="color:white; background-color: darkgoldenrod">PocketMuseum</p>

        </div>



        <%}}else{%>
            <tr><td><h3 style="text-align: center">Nessun evento in programma</h3></td></tr>
        <%}%>
           </div>



     </div>
  </div>
</div>

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