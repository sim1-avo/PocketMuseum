/**
 * Script relativi all'utente registrato
 */

$(document).ready(function() {
    $("#chiudipsw").click(function() {
		$("#bloccoModificaPsw").hide();
	});
});


$(document).ready(function() {
    $(".btnmodificapsw").click(function() {
		$("#bloccoModificaPsw").show();
	});
});