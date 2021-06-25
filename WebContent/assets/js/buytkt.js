function validate() {

	var data_scadenza = document.getElementById('scadenza').value;

	var arr1 = data_scadenza.split("/");

	var mese = Number(arr1[0]);

	if (mese < 1 || mese > 12) {
		document.getElementById('textarea-error').innerHTML='Mese non valido';
		return false;
	}

	var anno = Number(arr1[1]);

	anno += 2000;

	var today = new Date();

	var cur_anno = Number(today.getFullYear());

	if (anno < cur_anno) {
		document.getElementById('textarea-error').innerHTML='Carta scaduta';
		return false;
	}

	if (anno == cur_anno) {
		var cur_mese = Number(today.getMonth()+1);
		if (mese < cur_mese) {
			document.getElementById('textarea-error').innerHTML='Carta scaduta';
			return false;
		}
	}
	return true;
}

function selezionaTurno(){

	  var date = new Date($('#data_inizio').val());
	  console.log(date);
	  var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		$("#containerId").css("display", "block");
		$("#dataForm").val($('#data_inizio').val());
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "ControlloDataServlet?day=" + day + "&month=" + month
				+ "&year=" + year, false);

		xhr.setRequestHeader("connection", "close");

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var array = xhr.responseText.split("&");

				if(array[0] == "false")
					$("#opzione1").css("display","none");
				else
					$("#opzione1").css("display", "block");

				if (array[1] == "false")
					$("#opzione2").css("display", "none");
				else
					$("#opzione2").css("display", "block");

				if (array[2] == "false")
					$("#opzione3").css("display", "none");
				else
					$("#opzione3").css("display", "block");

				if (array[3] == "false")
					$("#opzione4").css("display", "none");
				else
					$("#opzione4").css("display", "block");
			}
		}
		xhr.send();
	}
