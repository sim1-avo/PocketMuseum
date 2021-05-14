<%@page import="model.opera.OperaBean"%>
<%/*
	UtenteBean b = (UtenteBean) session.getAttribute("guida");
	if(b == null || !(b.getTipo().equals("guida"))){
		response.sendRedirect("./Homepage.jsp");
		return;
	}*/

	ArrayList<OperaBean> opere = (ArrayList<OperaBean>) request.getAttribute("opere");
	if(opere == null){
		response.sendRedirect(response.encodeRedirectURL("./RecuperaOpere"));
		return;
	}


	%>
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

                    <form id="formSelezione">
                    <div class="form-group">
                        <select multiple class="formOpere" >
                            <option onclick="nuovaOpera()" id="aggOp">Aggiungi opera</option>
                        <%for(OperaBean bean: opere){ %>
                            <option class="listOp" onclick="selezionaOpera(<%=bean.getId()%>)">
                              &#8226; <%=bean.getNome() %>
                            </option>
                        <%} %>
                        </select>
                    </div>
                    </form>
                    <span >Seleziona l'opera da rimuovere per riempire questo campo</span>
                    <form action="RimuoviOpera" method="post" class="form-inline" style="display: block" id="rimuoviForm">
                        <label for="inputPassword2" class="sr-only">Id opera: </label>
                        <input type="text" name="id" readonly class="mb-4 fw-normal" id="idOpera2" placeholder="id">
                        <button type="submit" id="btnRimuovi" class="btn btn-primary rounded-pill">Rimuovi opera</button>
                    </form>
                    <div class="img-place mobile-preview   shadow floating-animate">
                        <img src="assets/img/Restorer.svg" alt="">
                    </div>
                </div>
            </div>
      </div>
      <!--FINE SINISTRA -->

      <!-- DESTRA -->

      <div id="UserImg" class="col-lg-5 py-3 shadow wow zoomIn">
         <form method="post"  enctype="multipart/form-data" action="GestioneOperaServlet" id="formOpera" onsubmit="return validate()">

         				  <div class="imgCop">
         				    <div class="infimg">
         				        <p>Immagine</p>
         				        <img id="immagineOpera" style="color:grey"alt="Immagine">
         				    </div>
         				    <div class="infimg">
                                <p>Copertina</p>
                                <img  id="copertinaOpera" style="color:grey" alt="Copertina">
                            </div>
         				  </div>

                          <div class="form-group" id="changeOp">
         				    Nome
         				    <input type="text" id="nomeOpera" class="mb-4 fw-normal" name="nome" pattern="([a-zA-Z 0-9 , . ; : - ' � � � � � � �  � � � � � � �( )]{3,100})"
         						title="Il campo nome opera non rispetta il formato o la lunghezza" required placeholder="La Gioconda">
                            Autore
         				    <input type="text" id="autoreOpera" required class="mb-4 fw-normal" name="autore" pattern="([a-zA-Z 0-9 , . ; : - ' � � � � � � �  � � � � � � �]{3,50})"
         						title="Il campo autore non rispetta il formato o la lunghezza" placeholder="Leonardo Da Vinci">

                            Immagine
                            <input type="file" class="mb-4 fw-normal" name="immagine" id="loadFile" placeholder="">
         				  	 <p id="image-error" class="help-block text-danger"></p>

         				    Copertina
                            <input type="file" class="mb-4 fw-normal" id="loadFile2" name="copertina" placeholder="">
                             <p id="copertina-error" class="help-block text-danger"></p>

                            Descrizione
         				    <textarea style="color:black;" name="descrizione" cols="50" rows="5" id="descrizioneOpera" required class="mb-4 fw-normal" rows="3"></textarea>
         				  	<p id="textarea-error" class="help-block text-danger"></p>


         				  	<label id="statoOperaCorrente"></label><br>
         				    Seleziona stato opera: <br>
         				    <select id="selectStato" name="stato" required size="3">
         					  <option value="visibile">Visibile</option>
         					  <option value="nonVisibile">Non visibile</option>
         					  <option value="inMod">Da modificare</option>
         					</select>

         				  </div>

         				  <div id="containerId" class="form-group">
         				    <label>Id opera
         				    <input type="text" id="idOpera" readonly class="mb-4 fw-normal" name="id" placeholder="id" value="0">
         				  	</label>
         				  </div>

         				  <button type="submit" class="btn btn-primary rounded-pill">Invia dati</button>
         				</form>
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

<script src="assets/js/AddChangeRemoveOpere.js"></script>

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