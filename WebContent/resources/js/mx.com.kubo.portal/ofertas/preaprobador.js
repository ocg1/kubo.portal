console.log("preaprobador.js");

Preaprobador.init_panel = function(where_to_recieve_data)
{
	console.log("Preaprobador.init_panel():");	
	console.log("where_to_recieve_data = " + where_to_recieve_data);
	
	$("div#ofertas-remote-command").find("input.init-panel-preaprobado").val(where_to_recieve_data).trigger("click");
};

Preaprobador.init_panel_oncomplete = function(xhr, status, args)
{
	var ammount = args.ammount;
	var term_id = args.plazo;
	var interes = args.interes;
	var mx_cat  = args.mx_cat;
	var cuota   = args.pago_mensual;
	var panel_simulador_ENABLED   = args.panel_simulador_ENABLED;
	var panel_preaprobado_ENABLED = args.panel_preaprobado_ENABLED;
	
	console.log("Preaprobador.init_panel_oncomplete(): ");
	console.log("> ammount = " + ammount);
	console.log("> plazo   = " + term_id);
	console.log("> interes = " + interes);
	console.log("> CAT     = " + mx_cat);
	
	console.log("> pago mensual = " + cuota);
	console.log("> panel_simulador_ENABLED   = " + panel_simulador_ENABLED);
	console.log("> panel_preaprobado_ENABLED = " + panel_preaprobado_ENABLED);
	
	$("#parser-ammount").html("$" + ammount);
	$("#parser-term-frequency").html(term_id);
	$("#parser-rate").html(interes + "%");
	$("#parser-cat").html(mx_cat + "%");	
	$("#parser-payment").html("$" + cuota);
	
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
	var reasignador_OK = args.reasignador_OK;
	
	console.log("Preaprobador.obtener_prestamo_oncomplete():");
	console.log("> reasignador_OK = " + reasignador_OK);
	
	$("div.txtConf2.mensajeConfirmacionPrestamo").show();
	$("div#ofertas-panel-preaprobado").hide();
	
	closeFancy();
};