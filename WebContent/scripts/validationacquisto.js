
function controllaScadenza(scadenza){
	'use strict';
const isValidCardExpireDate = scadenza => {

		    // Il valore del campo del form è sintatticamente valido? (mm/aa)
		    if(!/^\d{2}\/\d{2}$/.test(scadenza)) {
		    	  return false;
		    }
		    let parts = scadenza.split('/');
		    let cardMonth = (parts[0][0] === '0') ? parseInt(parts[0][1], 10) : parseInt(parts[0], 10);
		    let cardYear = parseInt('20' + parts[1], 10);
		    let now = new Date();
		    let year = now.getFullYear();
		    let month = now.getMonth() + 1;
		    // Il mese è valido?
		    if(cardMonth > 12 || cardMonth < 1) {
		    	    return false;
		    }
		    // L'anno è superiore o uguale a quello corrente?
		    if(cardYear < year) {
		    	 return false;
		    }
		    /* Se l'anno coincide con quello corrente ma il mese è superiore,
		     * la carta è scaduta.
		     */
		    if(cardMonth > month && cardYear === year ) {
		     	  return false;
		    }
		    return true;
		};
}

function valida() {
	 
	var nome = document.forms.regForm.nome.value;
	var cognome = document.forms.regForm.cognome.value;
	var nomeTitolare = document.forms.regForm.nometitolare.value;
	var numeroCarta = document.forms.regForm.numerocarta.value;
	var scadenza = document.forms.regForm.scadenza.value;
	var cvv = document.forms.regForm.cvv.value;
		
	if((nome.length < 3) || (nome.length > 16)) {
		   alert("Il campo Nome non e' valido.");
		   document.forms.regForm.nome.focus();
		   return false;
	}
	   
	 if((cognome.length > 20)) {
		   alert("Il campo Cognome non e' valido.");
		   document.forms.regForm.cognome.focus();
		   return false;
	}
	 
	   if((nomeTitolare.length > 40)){
		   alert("Il campo Nome Titolare non e' valido.");
		   document.forms.regForm.nometitolare.focus();
		   return false;  
	   }
	   
	   var cardno = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
	   if(!cardno.test(numeroCarta) && (numeroCarta.length != 12)){
		   alert("Not a valid Visa credit card number!");
		   document.forms.regForm.numerocarta.focus();
	        return false;
	   }
	   
	  
	   if(controllaScadenza(scadenza)){
		   alert("Scadenza non valida"); 
		   document.forms.regForm.scadenza.focus();
	        return false;
	   }
		
		var cvvValide = /^[0-9]{3}$/;
		if(!cvvValide.test(cvv)){
			alert("Il campo cvv non e' valido!");
			document.forms.regForm.cvv.focus();
		     return false;
		}
		
}

