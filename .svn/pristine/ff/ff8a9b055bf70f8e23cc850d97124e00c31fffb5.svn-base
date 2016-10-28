

$(document).ready(function() {
	

	
	$(window).resize(function(event){
		 var width = $(window).width();	
		 var referencia = $(".contEdoCuentafieldset").width();	
		if (width < 1001) { 
			
	   		$('.tabla_scroll').css({
				"width" : referencia -30
			 });
		}
		 });
	


	
	 $(window).resize();
});
var flagDisEdo = false;

function cargaScriptEdo()
{						
	$(".btnMorePayment").click(function(event)
	{								
		var element = $("#contDescPago");
		var vleft = (($(this).offset().left)+25);
		var vtop =  ($(this).offset().top);
		var contId = ""+$(this).attr('id')+"Cont";
		
		$("#contDescPagoCont").html($("#"+contId).html());
		
		//$("#contDescPago").offset({ top: vtop, left: vleft });
		
		$("#contDescPago").css({
			
			top: (vtop-130)+"px",left: vleft+"px"
			
		});

		if(!flagDisEdo){
			
			$("#contDescPago").fadeIn("slow");
			flagDisEdo=true;
			
		}
	});
	
	//drawChart();
	
}

  /* function drawChart() {
    var data = google.visualization.arrayToDataTable([
      ['Task', 'Hours per Day'],
      ['Work',     11],
      ['Eat',      2],
      ['Commute',  2],
      ['Watch TV', 2],
      ['Sleep',    7]
    ]);

    var options = {
      is3D: true,
      chartArea: {top:0,width:"80%",height:"95%"}
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);


  } */

function hideDesc(){
	
	$("#contDescPago").fadeOut("slow");
	flagDisEdo=false;
	
}

function validaUser(){
	
	if(confirm( "Recuerda que para poder realizar esta acción debes contar previamente "+
				"con una cuenta Pademobile y tener saldo suficiente para realizar el pago.\n\n"+
				"Si decides continuar, te transferiremos al portal de Pademobile para que realices tu pago." )){
		
		return true;
		
	}
	
	return false;
}

function completeActionEdo(){
	
	closeFancy();
	cargaScriptEdo();
	
}

function showWaitCount(){
	
	$("#dvshow").hide();
	
	$("#dvWaitEdo").show();
	return true;
}

function showTicket(xhr, status, args){
	
	$("#dvWaitEdo").hide();
	$("#dvshow").show();
	
	//$('#fancybox-content').load("#dvshow")
	
	$.fancybox.resize();
	
	
	
	/* var str = args.cuerpo;
	alert(str); */
	/* $("#lblHtml").val(str); */
	
}

function clasefnTckt(){
	
	$.fancybox.close();
	
}

