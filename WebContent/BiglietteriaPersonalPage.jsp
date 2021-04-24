<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
if(request.getSession(false).getAttribute("biglietteria")==null) {
	response.sendRedirect("Log.jsp");
	return;
}
%>  
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="img/C09_Logo.png"/>
		<title>PocketMuseum</title>
		<link rel="icon" type="image/x-icon" href="img/C09_Logo.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
        
       
		
	</head>
	<style>
		body{
			font-family: Lucida Sans Unicode, Lucida Grande, sans-serif;
			background-image: url('img/sfondo.jpg');
			background-position: center; 
			background-repeat: no-repeat;
			
		}
		
		label{
			font-family: Lucida Sans Unicode, Lucida Grande, sans-serif;
			color:white;
			font-size: xx-large;
			padding-right: 10px; 
		}
		#research {
			background-color: rgba(0,0,0,0.6);
			box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			border-radius: 10px;
					
		}
		#chartContainer {
			background-color: rgba(0,0,0,0.6);
			border-radius: 10px;
			margin-top:30px;
			margin-bottom:30px;
			padding-bottom: 40px;
		}
		#content{
			margin-bottom:20px;	
		}
		#tableContainer{
			background-color: rgba(0,0,0,0.6);
			box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			border-radius: 10px;
			
			padding-bottom: 40px;
			margin-bottom:40px;
		}
		#result {
			border-radius: 10px;
			background-color: white;
			height: 300px;
			margin-top: 40px;
		}
		
		table{
			border-radius: 0px;
			
		}
		
		
	</style>
	<body onload="setDate(),recuperaBiglietti()" onresize="recuperaBiglietti()">
	

  
  <br><br>
	
		<%@include file="NavBar.jsp" %> 
		
		<div id="research" class="container">
			
				<div class= "row d-flex justify-content-center" style="padding-bottom:20px;">
	  				<div class= "col-md-4  d-flex justify-content-center" style="padding-top: 20px;min-width: 300px;">
	  					<label for="fromDay">Da:</label>
	  					<input type="date" id="fromDay">
	  				</div>
	  				<div class= "col-md-4 d-flex justify-content-center" style="padding-top: 20px; min-width: 300px;">
	  					<label for="toDay">Al:</label>
	  					<input type="date" id="toDay">
	  				</div>
	  				<div class= "col-md-4 d-flex justify-content-center" style="min-width: 300px;padding-top: 20px;">
	  					<button type="button" class="btn btn-lg btn-warning" style="width:150px;" onclick="recuperaBiglietti()">Cerca</button>
	  				</div>
  				</div>
			
		</div>
		
		<div id="chartContainer" class="container">
		    
		    <div class="row">
		        <div class="col-md-12">
		            <div class="card" style="margin-top: 40px;">
		                <div class="card-body" >
		                    <canvas id="chart" height="100"></canvas>
		                </div>
		            </div>
		        </div>
	       </div>
	       
	       <div class="row">
		        <div class="col-md-12">
		            <div class="card" style="margin-top: 40px;">
		                <div class="card-body" >
		                    <h4 id="totaliVenduti">Biglietti totali venduti:</h4>
		                    <h4 id="totaleGuadagnato">Ricavato:</h4>
		                </div>
		            </div>
		        </div>
	       </div>
		</div>
		
		<div class="container">
		<div class="row">
			<div class="table-responsive" style="height: 300px" id="tableContainer" >
	        	<table class="table table-striped table-dark">
	          		<thead style="position:sticky;"> 
	                	<tr>
	                      <th scope="col">#</th>
					      <th scope="col">CODICE</th>
					      <th scope="col">DATA ACQUISTO</th>
					      <th scope="col">UTENTE</th>
					      <th scope="col">DATA INIZIO TURNO</th> 
					      <th scope="col">DATA FINE TURNO</th>
					      <th scope="col">COSTO</th>
	                 	</tr>
	               	</thead>
	                <tbody id="table"></tbody>
	            </table>
	        </div>
		</div>
		</div>
		
			<div id="blocco" class="row" style="margin-top: 100px;">
    <div class="col-md-8" id="divTable">
      <table style="width: 60% !important; margin-left: 10%"
        class="table table-striped table-dark" id="tableUserPage">
        <tr>
          <td class="primo">Email</td>
          <td>email</td>


          <td></td>
        </tr>
        <tr>
          <td class="primo">Password</td>
          <td>****</td>

          <td><input class="btn btn-primary btnmodificapsw"
            type="button" value="Modifica"></td>
        </tr>
      </table>
    </div>
    <div class="col-md-4" class="card" id="bloccoModificaPsw">
      <form action="ModificaDati" method="get">
        <div class="card-body">
          <input type="hidden" value="psw" name="richiesta">
          <div style="display: inline">
            <h6 style="float: left" class="card-title">Vecchia Password:</h6>
            <input name="vecchio" style="float: right" type="password" pattern="([\w\W]{8,32})"
						title="Il campo vecchia password è errato">
          </div>
        </div>
        <div class="card-body">
          <div style="display: inline">
            <h6 style="float: left" class="card-title">Nuova Password:</h6>
            <input name="nuovo" style="float: right" type="password" pattern="([\w\W]{8,32})"
						title="Il campo nuova password è errato">
          </div>
        </div>
        <div class="card-body">
          <div>
            <a href="#" id="chiudipsw" class="card-link">Chiudi</a><input
              style="float: right" type="submit" class="btn btn-primary"
              value="Modifica">
          </div>
        </div>
      </form>
    </div>
  </div>
		
 			
 			
 			
 			
		<footer id="footerOpere">
			<%@include file="Footer.jsp" %>
		</footer>
		
		
		
	</body>
	
	<script>
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
			
			var inizio= document.getElementById("fromDay").value;
			var fine= document.getElementById("toDay").value;
			var table= document.getElementById("table");
			var ricavato = document.getElementById("totaleGuadagnato");
			var venduti = document.getElementById("totaliVenduti");
			
			var xhttp = new XMLHttpRequest();
            xhttp.open("GET","RecuperaBiglietti?inizio="+inizio+"&fine="+fine,true);
             
            xhttp.setRequestHeader("connection","close");
            
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
                    
                    var colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];
                	
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
	
	
	</script>
	
	<script src="scripts/PersonalPageUtente.js"></script>
	<link href="css/PersonalPageUtente.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>