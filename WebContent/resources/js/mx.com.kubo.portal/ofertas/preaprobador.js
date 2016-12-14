console.log("preaprobador.js");

Preaprobador.init_panel = function(where_to_recieve_data)
{
	console.log("Preaprobador.init_panel():");	
	console.log("where_to_recieve_data = " + where_to_recieve_data);
	
	$("div#ofertas-remote-command").find("input.init-panel-preaprobado").val(where_to_recieve_data).trigger("click");
};

Preaprobador.init_panel_oncomplete = function(xhr, status, args)
{		
	var total_recibir      = args.total_recibir;
	var total_payment      = args.total_payment;
	var opening_commission = args.opening_commission;
	var ammount_commission = args.ammount_commission;
	var tabla_amoritizacion_URL = args.tabla_amoritizacion_URL;
	var ammount = args.ammount;	
	var term_id = args.plazo;
	var interes = args.interes;
	var mx_cat  = args.mx_cat;
	var cuota   = args.pago_mensual;	
	
	var   panel_simulador_ENABLED = args.panel_simulador_ENABLED;
	var panel_preaprobado_ENABLED = args.panel_preaprobado_ENABLED;
	var       max_payment_ENABLED = args.max_payment_ENABLED;		
	 
	for(i = 0; i < tabla_amoritizacion_URL.length; i++) 
	{	
		tabla_amoritizacion_URL = tabla_amoritizacion_URL.replace("::", "&");	 
	}
	
	console.log("Preaprobador.init_panel_oncomplete(): ");
	
	console.log("> total_recibir = " + total_recibir);
	console.log("> total_payment = " + total_payment);
	console.log("> opening_commission = " + opening_commission);
	console.log("> ammount_commission = " + ammount_commission);
	console.log("> ammount = " + ammount);
	console.log("> plazo   = " + term_id);
	console.log("> interes = " + interes);
	console.log("> CAT     = " + mx_cat);
	
	console.log("> pago mensual = " + cuota);
	console.log("> max_payment_ENABLED       = " + max_payment_ENABLED);
	console.log("> panel_simulador_ENABLED   = " + panel_simulador_ENABLED);
	console.log("> panel_preaprobado_ENABLED = " + panel_preaprobado_ENABLED);
	//console.log("> tabla_amoritizacion_URL   = " + tabla_amoritizacion_URL);	
	
	if(max_payment_ENABLED)
	{
		$("#parser-title").find("p").html("Préstamo sujeto a preaprobación:");
		
	} else {
		
		$("#parser-title").find("p").html("¡Tu préstamo ya está aprobado!");		
	}		
	
	$("#parser-ammount").html("$" + ammount);
	$("#parser-term-frequency").html(term_id);
	$("#parser-rate").html(interes + "% <span style='vertical-align:super;font-size: 11px;' >(a)</span>");
	$("#parser-cat").html(mx_cat + "% <span style='vertical-align:super;font-size: 11px;' >(b)</span>");	
	$("#parser-payment").html("$" + cuota);
	$("#parser-opening-commission").html(opening_commission + "%");
	$("#parser-total-recibir").html("$" + total_recibir);
	$("#parser-ammount-commission").html("$" + ammount_commission);
	$("#parser-total-payment").html("$" + total_payment);
	
	$("#link-tabla-amortizacion").attr("href", tabla_amoritizacion_URL);
	
	if(panel_preaprobado_ENABLED)
	{
		$("div#ofertas-panel-preaprobado").show();
	}
	
	if(!panel_simulador_ENABLED)
	{
		$("div#ofertas-panel-simulador").hide();
		$("div#panel-tabla-ofertas").hide();
	}
	
	closeFancy();
};

Preaprobador.regresar_simulador = function()
{
	Ofertas.init_tabla_ofertas();
};

Preaprobador.obtener_prestamo = function()
{
	$("div#ofertas-remote-command").find("a.init-obtener-prestamo").trigger("click");
};

Preaprobador.obtener_prestamo_oncomplete = function(xhr, status, args)
{
	var max_payment_ENABLED = args.max_payment_ENABLED;
	var loan_type_id = args.loan_type_id;
	
	console.log("Preaprobador.obtener_prestamo_oncomplete():");
	console.log("> loan_type_id = " + loan_type_id);
	console.log("> max_payment  = " + max_payment_ENABLED);	
	
	
	if($(".tablaUn.morado  input:checked").length != 0) 
	{
		$(".mensajeConfirmacionPrestamo").removeClass("amarillo");
    	$(".mensajeConfirmacionPrestamo").removeClass("azul");
    	$(".mensajeConfirmacionPrestamo").addClass("morado");    		
    }
	
    if($(".tablaUn.azul  input:checked").length != 0) 
    {
    	$(".mensajeConfirmacionPrestamo").removeClass("amarillo");
		$(".mensajeConfirmacionPrestamo").removeClass("morado");
		$(".mensajeConfirmacionPrestamo").addClass("azul")
    }
    
    if($(".tablaUn.amarillo  input:checked").length != 0) 
    {
        $(".mensajeConfirmacionPrestamo").removeClass("azul");
    	$(".mensajeConfirmacionPrestamo").removeClass("morado");
		$(".mensajeConfirmacionPrestamo").addClass("amarillo")
    }
    
	if(max_payment_ENABLED)
	{
		$("div.txtConf2.mensajeConfirmacionPrestamo").show();		
		
	} else {
		
		$("div.txtConf1.mensajeConfirmacionPrestamo").show();
	}
	
	$("div#ofertas-panel-preaprobado").hide();

	
	closeFancy();
};