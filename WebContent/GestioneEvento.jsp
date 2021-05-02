<%@page import="model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%

    if(session.getAttribute("guida")==null){
        response.sendRedirect("Log.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/GestioneEvento.css" rel="stylesheet" type="text/css">
    <link href="css/styles.css" rel="stylesheet" type="text/css">
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


    <link href="css/styles.css" rel="stylesheet" />
    <meta charset="ISO-8859-1">
    <title>PocketMuseum</title>
</head>

<%

    UtenteBean  u= (UtenteBean) session.getAttribute("guida");
    if(u==null){

        response.sendRedirect("log.jsp");
        return;
    }



%>

<body>
<jsp:include page="NavBar.jsp"></jsp:include>

<div class="container"  style="opacity:0.85!important">
    <div class="text-center">
        <h2 class="section-heading text-uppercase" style="text-shadow: 2px 0 0 #000, -2px 0 0 #000, 0 2px 0 #000, 0 -2px 0 #000, 1px 1px #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000;color:white">Crea Evento</h2>

    </div>
    <form  style="background-color:#212529; padding:5%" enctype="multipart/form-data" id="contactForm" name="sentMessage" method="post" action="InserisciEventoServlet" onsubmit="return validate()">
        <div class="row align-items-stretch mb-5" >
            <div class="col-md-6">
                <div class="form-group">
                    <input class="form-control" name="Nome" id="name" type="text" placeholder="Nome*" pattern="([\w\W]{10,100})"
                           title="Il campo nome non rispetta il formato o la lunghezza" required="required" data-validation-required-message="Inserisci nome evento.">
                    <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                    <input class="form-control" name="data_inizio" id="datain" type="date" placeholder="Data Inizio*" required="required" data-validation-required-message="Inserisci data inizio dell'evento.">
                    <p class="help-block text-danger"></p>
                </div>
                <div class="form-group mb-md-0">
                    <input class="form-control" type="date" name="data_fine" id="datafin" placeholder="Data Fine" required="required" data-validation-required-message="Inserisci data fine dell'evento.">
                    <p class="help-block text-danger"></p>
                    <p id="date-error" class="help-block text-danger error-field"></p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group form-group-textarea mb-md-0">
                    <textarea style="color:black;" class="form-control" id="descrizione" name="Descrizione" placeholder="Descrizione evento*" required="required" data-validation-required-message="Inserisci descrizione dell'evento."></textarea>
                    <p id="textarea-error" class="help-block text-danger error-field"></p>
                </div>

                <div class="form-group">

                    <input style="color:white" name="Immagine" required type="file" class="form-control-file" id="loadFile">
                    <p id="image-error" class="help-block text-danger error-field"></p>
                </div>

                <div class="text-center">
                    <div id="success"></div>
                    <button class="btn btn-primary btn-xl text-uppercase" id="sendMessageButton" type="submit">CREA</button>
                </div>
            </div>


        </div>
    </form>
</div>

<footer style="margin-top:600px" >

    <jsp:include page="Footer.jsp"></jsp:include>


</footer>


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