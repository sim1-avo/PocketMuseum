/**
 * Scripts per Page Biglietti
 */

$(document).ready(function() {
    $(".codici").click(function(event) {
        var idCodice=(event.target.id);
		var id="#"+idCodice
		var codice=$(id).text();
		var costo=$("#Costo"+idCodice).text();
		var dataIn=$("#dataIn"+idCodice).val();
		var dataFi=$("#dataFi"+idCodice).val();
		$(".card-title").html("Codice Biglietto: "+ codice);
		$(".card-subtitle").html("Costo Biglietto: "+ costo);
		$(".text1").html("Data Inizio Turno: "+ dataIn);
		$(".text2").html("Data Fine Turno: "+ dataFi);
		$("#bloc2").show();
    });
});
$(document).ready(function() {
    $("#chiudi").click(function() {
		$("#bloc2").hide();
	});
});