;$(document).ready(function() {
var slider = document.getElementById("stelle");
    slider.oninput = function() {
		console.log("stelle");
		var stelle = $("#stelle").val();
		console.log(stelle);
           $.post('/PocketMuseum/RicercaRecensione', {
           	 stelle : stelle
           	 },
           	 function(responseText) {
             		if(responseText=="non trovato"){

             			$("#prova").fadeIn(1000);

             			$("#prova").html("Codice non valido");

                  		$("#prova").fadeOut(3000);

             		}else{
             			window.location.replace("./PageOpera.jsp?id=1");
             		}
                  }
           	 );

     };
});
