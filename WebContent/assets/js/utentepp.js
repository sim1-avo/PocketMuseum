$(document).ready(function(){
  /*Pagine del navbar*/
  $("#tour").show();
  $("#userdata").hide();
  $("#history").hide();
  $("#events").hide();
  /*Solo per la guida*/
  $("#opere").show();
  /*Page Tour*/
  $("#end-right-tour").hide();
  $("#end-left-tour").hide();
  /*PageUserData*/
  $("#ChangeData").hide();
  $("#annullachangepsw").hide();

  /*Lato Biglietteria*/
  /*Pagine History*/
  $("#chartContainer").hide();
  $("#infotkt").hide();

  /*Lato Guida*/
  /*Page Opere*/


});
function ShowTour(){
        $("#history").hide();
        $("#userdata").hide();
        $("#events").hide();
        $("#tour").show();
}

function ShowUserData(){
        $("#history").hide();
        $("#tour").hide();
        $("#events").hide();
        /*Solo guida*/
        $("#opere").hide();
        $("#userdata").show();
}
function ShowHistory(){
        $("#userdata").hide();
        $("#tour").hide();
        $("#events").hide();
        $("#history").show();
}
function ShowEvents(){
        $("#userdata").hide();
        $("#tour").hide();
        $("#history").hide();
        /*Solo guida*/
        $("#opere").hide();
        $("#events").show();
}
/*Solo guida*/
function ShowOpere(){
        $("#userdata").hide();
        $("#events").hide();
        $("#opere").show();
}

/*Page Tour*/
function ShowBuyTicket(){
        $("#start-right-tour").hide();
        $("#start-left-tour").hide();
        $("#end-right-tour").show();
        $("#end-left-tour").show();

}

function AnnullaShowBuyTicket(){
        $("#start-right-tour").show();
        $("#start-left-tour").show();
        $("#end-right-tour").hide();
        $("#end-left-tour").hide();

}

/*Page UserData*/
function ShowChangePsw(){
        $("#ChangeData").show();
		$("#UserImg").hide();
		$("#annullachangepsw").show();
		$("#changepsw").hide();
}

function AnnullaChangePsw(){
        $("#ChangeData").hide();
		$("#UserImg").show();
		$("#annullachangepsw").hide();
		$("#changepsw").show();
}

/*LATO BIGLIETTERIA*/
/*Page History*/
function ShowChart(){

}