$(document).on(
			"click",
			"#codButton",
			function() {
				var codice = $("#inputPassword2").val();
				const regex=RegExp("[a-zA-Z]{4}[0-9]{4}[a-zA-Z]{4}");
				if(codice.length < 12 || codice.length > 12){
					$("#prova").fadeIn(1000);

					$("#prova").html("Codice non valido");

					$("#prova").fadeOut(3000);
					return;
				}else if(!regex.test(codice)){
					console.log(regex.test(codice));
					$("#prova").fadeIn(1000);

					$("#prova").html("Codice non valido");

					$("#prova").fadeOut(3000);
					return;
				}
				var currentdate = new Date();
				var datetime = currentdate.getFullYear() + "-"
						+ (currentdate.getMonth() + 1) + "-"
						+ currentdate.getDate() + " "
						+ (currentdate.getHours() < 10 ? '0' : '')
						+ currentdate.getHours() + ":"
						+ (currentdate.getMinutes() < 10 ? '0' : '')
						+ currentdate.getMinutes() + ":"
						+ (currentdate.getSeconds() < 10 ? '0' : '')
						+ currentdate.getSeconds();

				$("#data").val(datetime);
				console.log(datetime);
				$.get('/PocketMuseum/CodiceServlet', {
					data : datetime,
					codice : codice
				}, function(responseText) {
					if (responseText == "non trovato") {

						$("#prova").fadeIn(1000);

						$("#prova").html("Codice non valido");

						$("#prova").fadeOut(3000);
					} else {
						window.location.href="./OpereTour.jsp";
					}
				});
			})

	 function acquistaOra(){
			  window.location.href = "./PageAcquisto.jsp";
	 }
