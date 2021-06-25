$(document).ready(function(){
  /*Pagine del navbar*/
  $("#tour").show();
  $("#userdata").hide();
  $("#history").hide();
  $("#events").hide();
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
});
function setDate(){
    var d = new Date();
    var n = d.getFullYear();

    var inizio= document.getElementById("fromDay").value = n+"-01-01";
    var fine= document.getElementById("toDay").value = n + "-" +(d.getMonth()+1) + "-" +  d.getDate();


}

var numRow = table.rows.length;
var tot=0.0;
var labs=[];
var dateVendita=[];
var dati=[];

function recuperaBiglietti(){
    $("#previewB").hide();
    $("#chartContainer").show();
    $("#infotkt").show();


    var inizio= document.getElementById("fromDay").value;
    var fine= document.getElementById("toDay").value;
    var table= document.getElementById("table");
    var ricavato = document.getElementById("totaleGuadagnato");
    var venduti = document.getElementById("totaliVenduti");

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET","RecuperaBiglietti?inizio="+inizio+"&fine="+fine,true);

    /*xhttp.setRequestHeader("connection","close");*/

    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status ==200){
            table.innerHTML=xhttp.responseText;
            numRow = table.rows.length;
            tot=0.0;
            labs=[];
            dateVendita=[];
            dati=[];

             for (var row = 0; row < numRow; row++) {
                tot= parseFloat(tot) + parseFloat(table.rows[row].cells[6].innerHTML);
                table.rows[row].cells[6].innerHTML = (parseFloat(table.rows[row].cells[6].innerHTML)).toFixed(2) + " €";
                dateVendita.push((table.rows[row].cells[2].innerHTML).substring(0, 7));

            }

            ricavato.innerHTML = "Ricavato: " + tot.toFixed(2) + " €";
            venduti.innerHTML = "Biglietti totali venduti: " + numRow;

            labs = dateRange(inizio,fine);



            for(var i=0;i<labs.length;i++){

                for(var j=0;j<dateVendita.length;j++){
                    if(dateVendita[j]===labs[i]) {
                        dati[i]++;
                    }

                }
            }

            var colors = ['#b8860b','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];

            var chLine = document.getElementById("chart");
            var chartData = {
              labels:labs,
              datasets: [{
                data: dati,
                backgroundColor: 'transparent',
                borderColor: colors[0],
                borderWidth: 4,
                pointBackgroundColor: colors[0]
              }]
            };

            if (chLine) {
              new Chart(chLine, {
              type: 'line',
              data: chartData,
              options: {
                scales: {
                  yAxes: [{
                    ticks: {
                      beginAtZero: false
                    }
                  }]
                },
                legend: {
                  display: false
                }
              }
              });
            }
        }
    };


    xhttp.send();

}

function dateRange(startDate, endDate) {
      var start      = startDate.split('-');
      var end        = endDate.split('-');
      var startYear  = parseInt(start[0]);
      var endYear    = parseInt(end[0]);
      var dates      = [];

      for(var i = startYear; i <= endYear; i++) {
        var endMonth = i != endYear ? 11 : parseInt(end[1]) - 1;
        var startMon = i === startYear ? parseInt(start[1])-1 : 0;
        for(var j = startMon; j <= endMonth; j = j > 12 ? j % 12 || 11 : j+1) {
          var month = j+1;
          var displayMonth = month < 10 ? '0'+month : month;
          dates.push([i, displayMonth].join('-'));
          dati.push(0);
        }
      }
      return dates;
    }

