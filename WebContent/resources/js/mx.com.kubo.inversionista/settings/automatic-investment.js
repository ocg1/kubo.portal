console.log("automatic-investment.js");

AutomaticInvestment.alta = function()
{		
	$(".automatic-investment-filters").show();
	$(".automatic-investment-list").hide();			
	$(".automatic-investment-filters input").val("");
	$("#btns-alta-cancelar").show();	
	console.log("AutomaticInvestment.alta(): OK");
};

AutomaticInvestment.cancelar_alta = function()
{		
	$(".automatic-investment-filters").hide();
	$(".automatic-investment-list").show();
	$(".automatic-investment-filters input").val("");
	$(".alerta-confirmacion-crearRegla").fadeOut();
	
	$(".velo").fadeOut();
	$("#btns-alta-cancelar").hide();	
	console.log("AutomaticInvestment.cancelar_alta(): OK");
	
};


AutomaticInvestment.init_label = function(input)
{	
	var label;
	
	var label_OK = true;
	
	console.log("AutomaticInvestment.init_label(): " + $(input).val());
		
	try
	{
		label = input.value;
		
		console.log(" > label = " + label);
		
	} catch (e) {
		
		label_OK = false;
	}
		
	return label_OK;
};


AutomaticInvestment.label_oncomplete = function(xhr, status, args)
{
	var label = args.label;
	
	console.log("AutomaticInvestment.label_oncomplete(): " + label);		
	
	$(".guardar").show();
};

AutomaticInvestment.frequency_oncomplete = function(xhr, status, args)
{
	var frequency = args.frequency;
	//var frequency_label = args.frequency_label;
	
	
	console.log("AutomaticInvestment.frequency_oncomplete(): ");
	console.log(" > frequency = " + frequency);
/*	
	console.log(" > frequency_label = " + frequency_label);
*/	
};

AutomaticInvestment.save = function()
{
	console.log("AutomaticInvestment.save(): ");
	
	buildQueryFilter2();
};

AutomaticInvestment.save_oncomplete = function(xhr, status, args)
{
	var save_OK = args.save_OK;
	
	console.log("AutomaticInvestment.save_oncomplete(): " + save_OK);		
	
	closeFancy();
	
	if(save_OK)
	{
		filtros();
		
		$(".automatic-investment-filters").hide();
		$(".automatic-investment-list").show();	
		$(".alerta-confirmacion-crearRegla").fadeOut();
		$(".velo").fadeOut();
		$("#btns-alta-cancelar").hide();
		
	}
};

AutomaticInvestment.init_is_active = function(is_active, automatic_investment_id)
{
	var is_active_TOKEN =  is_active + "::" + automatic_investment_id;
	
	console.log("AutomaticInvestment.activar(): " + is_active_TOKEN);
	
	$("#init-is-active").val(is_active_TOKEN).trigger("click");
};

AutomaticInvestment.is_active_oncomplete = function(xhr, status, args)
{
	var is_active = args.is_active; 
	var   save_OK = args.save_OK;
	var automatic_investment_id = args.automatic_investment_id;
		
	console.log("AutomaticInvestment.is_active_oncomplete(): ");
	console.log(" > is_active = " + is_active);
	console.log(" > save_OK   = " + save_OK);
	console.log(" > automatic_investment_id   = " + automatic_investment_id);
	
	$(".automatic-investment-list").show();	
	
	closeFancy();
};

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

function seleccionaTerm( elmnt )
{
	console.log("AutomaticInvestment.seleccionaTerm(): ");
	
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
	console.log("AutomaticInvestment.initDestinyCheck(): ");
	
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

function filtroAbrir(tab) 
{
	console.log("AutomaticInvestment.filtroAbrir(): ");
	
	 var width = $(window).width();
	 
	 if (width <= 1100) 
	 { 
		 if($(tab).hasClass("active")) 
		 {
		   $(tab).next("div").slideUp();
		   $(tab).removeClass("active");
		   
		 } else {
			 
		   $('.tienda .toggle_container').slideUp();
		   $('.tienda .expand_heading').removeClass("active");
		   
		   $(tab).next("div").slideDown();
	       $(tab).addClass("active");		  
		}  
	 }
}

function seleccionAll()
{	
	console.log("AutomaticInvestment.seleccionAll(): ");
	
	var f = false;
	
	if($("#dvSelAllRisk").html() == 'Seleccionar todos')
		f = true;
	
	$(".ui-checkbox-filter").each(function(){
		
			$(this).attr('checked', f);
		
	});
	
	if($("#dvSelAllRisk").html() == 'Seleccionar todos')
	{
		$("#dvSelAllRisk").html( 'Quitar Selección' );
	
	}else
		{
			$("#dvSelAllRisk").html('Seleccionar todos')
		}
	
}

function seleccionAllDet()
{
	console.log("AutomaticInvestment.seleccionAllDet(): ");
	
	var f = false;
	
	if($("#dvSelAllRiskDet").html() == 'Seleccionar todos')
		f = true;
	
	$(".clsItem").each(function(){
			
			if(f)
			{
				$(this).addClass('itemCheck');
			
			}else
			{
				$(this).removeClass('itemCheck');
			}
		
	});
	
	if($("#dvSelAllRiskDet").html() == 'Seleccionar todos')
	{
		$("#dvSelAllRiskDet").html( 'Quitar Selección' );
	
	}else
		{
			$("#dvSelAllRiskDet").html('Seleccionar todos')
		}
	
	
	var item_num_risk = 0;
	var strCadena = "";
	
	$(".clsItem").each(function(){
		
		if($(this).hasClass("itemCheck"))
		{
			if( item_num_risk != 0 ){
				strCadena+=",";
			}
			
			strCadena+= $(this).attr("id");
			item_num_risk++;
		
		}
			
	});
	
	$("#cadena1").val( strCadena );
	$("#risk_str").val( strCadena );
	
	
}

function seleccionDestiny()
{ 
	console.log("AutomaticInvestment.seleccionDestiny(): ");
	
	var f = false;
	
	if($("#dvSelAllDestiny").html() == 'Seleccionar todos')
		f = true;
	
	$(".clsItemDest").each(function(){
			
			if(f)
			{
				$(this).addClass('itemCheck');
			
			}else
			{
				$(this).removeClass('itemCheck');
			}
		
	});
	
	if($("#dvSelAllDestiny").html() == 'Seleccionar todos')
	{
		$("#dvSelAllDestiny").html( 'Quitar Selección' );
	
	}else
		{
			$("#dvSelAllDestiny").html('Seleccionar todos')
		}
	
	
	var item_num_risk = 0;
	var strCadena = "";
	
	$(".clsItemDest").each(function(){
		
		if($(this).hasClass("itemCheck"))
		{
			if( item_num_risk != 0 ){
				strCadena+=",";
			}
			
			strCadena+= $(this).attr("id");
			item_num_risk++;
		
		}
			
	});
	
	$("#destiny_str").val( strCadena );
	$("#destiny_str_val").val( strCadena );
}

function esInteger(e)
{
	console.log("AutomaticInvestment.esInteger(): ");
	
	var charCode;
	
	if (navigator.appName == "Netscape")
	{
		charCode = e.which;
	}
	else{
		charCode = e.keyCode;
	}	
	if (48 > charCode || charCode > 57){
		if(charCode == 8 || charCode == 0){
			return true;
		}
		else{
			return false;
		}
	}
	else{
		return true;
	}
} 
function abrirConfirmacionAlta () {
	
	if($("#nombre-regla").val() != "") {
		$(".nombre-regla").html($("#nombre-regla").val());
		$(".alerta-confirmacion-crearRegla").fadeIn();
		$(".velo").fadeIn();
		
	}else{
		$("#nombre-regla").addClass("error-style-input");
	}
	$('html, body').animate({
	       scrollTop: ($('.content').offset().top - 100)
	},1400);
}

function errorStyle (){
	if($("#nombre-regla").val() != "") {
		$("#nombre-regla").removeClass("error-style-input");
	}else{
		$("#nombre-regla").addClass("error-style-input");
	}
	
}