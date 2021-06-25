	function validate(){
		var stringa = document.getElementById('descrizioneOpera').value;

		if(stringa == null){
			document.getElementById('textarea-error').innerHTML='Formato non rispettato';
			return false;
		}

		var img = document.getElementById('loadFile').value;

		if (img.length != 0) {
			var str = img.substr(img.length - 4, 4).toLowerCase();
			if(!(str == ".jpg" || str == ".png")) {
				document.getElementById('image-error').innerHTML='Formato immagine non rispettata';
				return false;
			}
			var dim = document.getElementById('loadFile').files[0].size;

			if(dim>1024*1024){
				document.getElementById('image-error').innerHTML='	Immagine troppo grande';
				return false;
			}

		}

		var copertina  = document.getElementById('loadFile2').value;

		if (copertina.length != 0) {
			var str2 = copertina.substr(copertina.length - 4, 4).toLowerCase();
			if(!(str2 == ".jpg" || str2 == ".png")) {
				document.getElementById('copertina-error').innerHTML='Formato copertina non rispettata';
				return false;
			}
			var dim2 = document.getElementById('loadFile2').files[0].size;

			if(dim2>1024*1024){
				document.getElementById('copertina-error').innerHTML='	Immagine troppo grande';
				return false;
			}
		}
		return true;
    }
	function nuovaOpera(){
		$('#btnRimuovi').prop('disabled', true);
		$("#containerId").css("display","none");
		$("#idOpera").val("0");
		$("#idOpera2").val("");
		$("#immagineOpera").css("display","none");
		$("#copertinaOpera").css("display","none");
		$("#nomeOpera").val("");
		$("#autoreOpera").val("");
		$("#statoOpera").val("");
		$("#descrizioneOpera").val("");
	}


	function selezionaOpera(id){
		$('#btnRimuovi').prop('disabled', false);
		$("#containerId").css("display","block");
		var xhr = new XMLHttpRequest();
		var stringa = "id=" + id + "&json=true";
		xhr.open("POST","CercaOperaById", false);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		//xhr.setRequestHeader("connection","close");

		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){

				var ob = JSON.parse(xhr.responseText);

				$("#nomeOpera").val(ob.nome);
				$("#autoreOpera").val(ob.autore);
				$("#statoOpera").val(ob.stato);
				$("#statoOperaCorrente").text("Stato opera corrente: " + ob.stato);
				$("#selectStato").val(ob.stato);
				$("#idOpera").val(ob.id);
				$("#idOpera2").val(ob.id);
				$("#descrizioneOpera").val(ob.descrizione);
			}
		}
		xhr.send(stringa);
		recuperaImmagini(id);
	}

	function recuperaImmagini(id){

		var xhr = new XMLHttpRequest();
		xhr.open("GET","RecuperaImmagini?id=" + id, false);
		/*xhr.setRequestHeader("connection","close");*/

		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){

				var r = xhr.responseText;

				var inizio = r.indexOf("ciao");

				var immagine = r.substring(0,inizio - 1);
				var copertina = r.substring(inizio + 4,r.length-1);



				var stringImmagine =  "data:image/png;base64," + immagine;
				var stringCopertina =  "data:image/png;base64," + copertina;



				if(stringImmagine.length>30){
					document.getElementById("immagineOpera").src = stringImmagine;
					document.getElementById("immagineOpera").style.display = "block";
				}
				else {
					document.getElementById("immagineOpera").style.display = "none";
				}
				if(stringCopertina.length>30){
					document.getElementById("copertinaOpera").src = stringCopertina;
					document.getElementById("copertinaOpera").style.display = "block";
				}
				else {
					document.getElementById("copertinaOpera").style.display = "none";
				}



			}
		}
		xhr.send();
	}
