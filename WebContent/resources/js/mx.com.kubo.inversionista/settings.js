console.log("settings.js");

function openVerMás( val )
{
	// alert( $("#explica_"+val).is(":visible") );
	
	if( $("#explica_"+val).is(":visible") )
	{
	
		$("#explica_"+val).hide();
	
	}else{
		
		dibujaLimites( val );
		
		$("#explica_"+val).show();
		
	}
	
}

var valClick = 0;

function callBean( val )
{
	valClick = val;
	$("#callBean").trigger("click");
	
}

function formatCurrency(total) {
    var neg = false;
    if(total < 0) {
        neg = true;
        total = Math.abs(total);
    }
    return (neg ? "-$" : '$') + parseFloat(total, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString();
}

function editar( val ){
	
	$("#param_id_"+val).prop( "disabled", false ); 
	$("#btnOk_"+val).show();
	$("#param_id_"+val).focus();
	$(".clsIconEdit_"+val).hide();
	$("#btnCambiar_"+val).hide();
	
}

function returnChange(  ){
	
	$("#param_id_"+valClick).prop( "disabled", true ); 
	$("#btnOk_"+valClick).hide();
	$(".clsIconEdit_"+valClick).show();
	$("#btnCambiar_"+valClick).show();
	
	
	closeFancy();
}

function dibujaLimites( val )
{
	var valInit = $("#param_id_"+val ).val();
	
	if( parseInt( valInit ) > 100 )
	{
		alertify.alert( "Cantidad no válida" )
		$(".alertify-cover").show(); $("#alertify").show();
		$("#param_id_"+val ).val( 0 );
		$("#param_id_"+val ).blur();
		
		$(".alertify-button").click( function(){ $(".alertify-cover").hide(); $("#alertify").hide(); }  );
		
		return;
	}
	
	$("#dvContInfo_"+val).empty();
	
	var saldo =  $("#inSaldoActual").val() ;
	
	var saldoActual = formatCurrency( $("#inSaldoActual").val() );
	var montoInvertido;
	var montoLimite;
	var disponible;
	var proy;

	console.log( "saldo : " + $("#inSaldoActual").val() );
	console.log( " E5 :  " +  $("#inMontoInvertidoE5").val() );
	console.log( " FG : " + $("#inMontoInvertidoFG").val() );

	if( val == '1' )
	{
		var monTemp = $("#inMontoInvertidoE5").val();
		
		montoInvertido = formatCurrency( monTemp );
		
		var procTmp = $("#param_id_"+val ).val();
		
		var lim = (parseFloat(saldo) * parseFloat(procTmp)) / 100;
		
		montoLimite = formatCurrency( lim );
		
		var dis = parseFloat(lim) - parseFloat(monTemp);
		
		disponible = formatCurrency( dis );
		
		proy = "E5";
		
	}

	else if( val == '2' )
	{	
		var monTemp = $("#inMontoInvertidoFG").val();
		
		montoInvertido = formatCurrency( monTemp );
		
		var procTmp = $("#param_id_"+val ).val();
		
		var lim = ( parseFloat(saldo) * parseFloat(procTmp)) / 100;
		
		montoLimite = formatCurrency( lim );
		
		var dis = parseFloat(lim) - parseFloat(monTemp);
		
		disponible = formatCurrency( dis );
		
		proy = "F y G";
	}

	var htmlStr = "<table style='border: solid 1px #439539 ; margin-left: auto; margin-right: auto; ' >" +
					"<tr>" +
						"<td>SALDO TOTAL ACTUAL</td>" +
						"<td style='text-align: right;color: #439539;font-weight: bold;'>"+saldoActual+"</td>" +
					"</tr>" +
					"<tr>" +
						"<td>MONTO TOTAL INVERTIDO EN RIESGO "+proy+" ACTUALMENTE</td>" +
						"<td style='text-align: right;color: #439539;font-weight: bold;' >"+montoInvertido+"</td>" +
					"</tr>" +
					"<tr>" +
						"<td>MONTO LÍMITE PARA INVERTIR EN RIESGO "+proy+" </td>" +
						"<td style='text-align: right; color: #439539;font-weight: bold;  '>"+montoLimite+"</td>" +
					"</tr>" +
					"<tr>" +
						"<td>MONTO DISPONIBLE PARA INVERTIR EN RIESGO "+proy+" </td>" +
						"<td style='text-align: right; color: #439539;font-weight: bold;'>"+disponible+"</td>" +
					"</tr>" +
				"<table>";

	$("#dvContInfo_"+val).html( htmlStr );

}

function initViewRisk()
{	
	console.log("Settings.initViewRisk()");
	
	if( $("input[name=flagRisk][value=0]'").is(":checked") )
	{
		
		$("#dvRiskDetail").hide();
		$( "#tbGeneralRisk").show();
		
	}else{
		
		$("#dvRiskDetail").show();
		$("#tbGeneralRisk").hide();
		
	}
	
}

function seleccionaTerm( elmnt ){
	
	var itemActualInv = elmnt.split("_")[1];
	
	$(".clsTerm").each(function(){
		
		var this_id = $(this).attr("id");
		
		var tipo = this_id.split("_")[0];
		
		if( tipo != "gender" ){
		
			var this_id_2 = $(this).attr("id");
			
			var this_sec_2 = this_id_2.split("_")[1];
			
			if( parseInt( itemActualInv ) >= parseInt(this_sec_2) ){
				
				 $(this).addClass('itemCheck');
				
			}else{
				
				$(this).removeClass('itemCheck');
				
			}
		
		}
	});
}	

function initDestinyCheck()
{	
	console.log("Settings.initDestinyCheck(): ");
	
	$(".clsItemDest").each(function()
	{		
		$(this).removeClass("itemCheck");			
	});
	
	$("#destiny_str").val($("#destiny_str_val").val());		

	var elmnt = $("#destiny_str").val();
 
	var arr = elmnt.split(",");
	
	for( var i_des = 0 ; i_des < arr.length ; i_des++ )
	{		
		var s = arr[i_des];
		
		s = s.replace("'");
		
		console.log("purpose: "+s);
		
		$("#purpose_"+s+"").addClass("itemCheck");		
	}
}

