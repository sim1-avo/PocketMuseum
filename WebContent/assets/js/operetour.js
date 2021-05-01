$(document).ready(function(){
  $("#minwidth").hide();
  $("#maxwidth").hide();

});
function(){
if(window.innerWidth <= 600){
  $("#minwidth").show();
  $("#maxwidth").hide();

  }else{
  $("#maxwidth").show();
  $("#minwidth").hide();

  }
}