/**
 * Scripts di homepage
 */

$(document).on("click", "#codButton", function(){
	var codice=$("#inputPassword2").val();
	var currentdate = new Date(); 
    var datetime = currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getDate() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds();
    $("#data").val(datetime);
    console.log(datetime);
	 $.post('/PocketMuseum_C09/CodiceServlet', {data:datetime, codice: codice}, function(responseText) {
		if(responseText=="non trovato"){
			
			$("#prova").fadeIn(1000);
			
			$("#prova").html("Codice non valido");
			 
     		$("#prova").fadeOut(3000);
		}else{
			window.location.replace("./PageOpera.jsp?id=1");
		}
     });
})