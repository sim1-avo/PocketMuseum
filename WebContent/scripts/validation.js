function valida() {
// Variabili associate ai campi del modulo
	var nome = document.forms.regForm.Nome.value;
	var cognome = document.forms.regForm.Cognome.value;
	var pass = document.forms.regForm.Password.value;
	var conferma= document.forms.regForm.Conferma.value;
	var email = document.forms.regForm.email.value;
	var cf = document.forms.regForm.CF.value;
   
   if ((nome.length < 3) || (nome.length > 16)) {
	   alert("Il campo Nome non e' valido.");
	   
	   document.forms.regForm.Nome.focus();
	   return false;
	   }
   
   if ((cognome.length > 20)) {
	   alert("Il campo Cognome non e' valido.");
	   document.forms.regForm.Cognome.focus();
	   return false;
	   }
   
// Espressione regolare dell'email
   var email_valid = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;
   if (!email_valid.test(email)|| (email.length < 16) || (email.length > 32)) 
   {
      alert("Devi inserire un indirizzo mail corretto");
      document.forms.regForm.email.focus();
      return false;
   }
   
  if ((pass.length < 6)||(pass.length > 32)) 
   {
    alert("Scegli una password, minimo 6 caratteri");
    document.forms.regForm.Password.focus();
    return false;
   }
  //Effettua il controllo sul campo CONFERMA PASSWORD
    if (pass != conferma) {
	   alert("La password confermata e' diversa da quella scelta, controllare.");
	   document.forms.regForm.Conferma.value = "";
	   document.forms.regForm.Conferma.focus();
	   return false;
	   }
   
   if ((cf.length < 16) || (cf.length > 16)) {
	   alert("Il campo CF non e' valido.");
	   document.forms.regForm.CF.focus();
	   return false;
	   }
       
}