console.log("ofertas.js");

var claseSel = "";

$(document).ready(function()
{
	claseSel = "";
	
	var remote_command = $("div#ofertas-remote-command");
	
	if(remote_command.find(".check-automatic-aproved").length > 0)
	{
		remote_command.find(".check-automatic-aproved").trigger("click");
		
	} else {
	
		Ofertas.init_SAFI_credit_id();
	}		
});

Ofertas.init_panel_simulador = function()
{
	$("div#ofertas-remote-command").find("a.init-panel-simulador").trigger("click");
};

Ofertas.panel_simulador_oncomplete = function(xhr, status, args)
{
	var panel_simulador_ENABLED = args.panel_simulador_ENABLED;
	var max_ammount = args.max_ammount;
	var max_payment = args.max_payment;
	
	console.log("Ofertas.panel_simulador_oncomplete(): ");
	console.log("> panel_simulador_ENABLED = " + panel_simulador_ENABLED);
	console.log("> max_ammount = " + max_ammount);
	console.log("> max_payment = " + max_payment);
	
	if(panel_simulador_ENABLED)
	{
		$("div#ofertas-panel-simulador").show();
		$("div#simular-nuevo-prestamo").hide();		
		$("div#ofertas-panel-preaprobado").hide();
		$("div#panel-tabla-ofertas").hide(); 
	}
	
	closeFancy();
}

Ofertas.automatic_aproved_oncomplete = function(xhr, status, args)
{
	var automatic_aproved_ENABLED = args.automatic_aproved_ENABLED;
	
	console.log("> automatic_aproved_ENABLED = " + automatic_aproved_ENABLED);
	
	$("#panel-tabla-ofertas").hide();
	
	if(automatic_aproved_ENABLED)
	{
		$("div.txtConf1.mensajeConfirmacionPrestamo").show();
		
	} else {
		
		$("div.txtConf2.mensajeConfirmacionPrestamo").show();
	}
};

Ofertas.init_SAFI_credit_id = function()
{
	console.log("Ofertas.init_SAFI_credit_id():");
	
	var ofertas = $("#SAFI-credit-id").find("select")
	
	$.each(ofertas.find("option"), function(index, element)
	{
		if(index > 0)
		{
			var SAFI_credit_id = $(element).val();
			
			console.log("> " + SAFI_credit_id);	
			
			ofertas.val(SAFI_credit_id).trigger("change");
		}		
	});				
};

Ofertas.SAFI_credit_id_oncomplete = function(xhr, status, args)
{	
	var SAFI_credit_id    = args.SAFI_credit_id;
	var same_rate_ENABLED = args.same_rate_ENABLED;
	var      ofert_TOKEN  = args.ofert_TOKEN;
	var ofert_rate_TOKEN  = args.ofert_rate_TOKEN;
	
	console.log("Ofertas.SAFI_credit_id_oncomplete() = ");
	console.log("> SAFI_credit_id    = " + SAFI_credit_id);
	console.log("> same_rate_ENABLED = " + same_rate_ENABLED);
	console.log("> ofert_TOKEN       = " + ofert_TOKEN);
	console.log("> ofert_rate_TOKEN  = " + ofert_rate_TOKEN);
				
	$("div.renovacion-hidden").each(function(index, el)
	{
		$(el).hide();
	});
					
	if(SAFI_credit_id > 0)
	{
		$("div#simular-nuevo-prestamo").show();
		$("div#renovacion-" + SAFI_credit_id).show();	
	}
	
	if(!same_rate_ENABLED)
	{
		$("div#renovacion-" + SAFI_credit_id).find("h2.renovacion-ofert-rate-TOKEN").html(ofert_rate_TOKEN);
		
	} else {
		
		$("div#renovacion-" + SAFI_credit_id).find("h2.renovacion-ofert-rate-TOKEN").html(ofert_rate_TOKEN);
	}
	
	$("div#renovacion-" + SAFI_credit_id).find("h3.renovacion-ofert-TOKEN").html(ofert_TOKEN);

		
	closeFancy();
};

Ofertas.init_tabla_ofertas = function()
{
	$("div#panel-tabla-ofertas").show();
	$("div#simular-nuevo-prestamo").show();	
	$("div#ofertas-panel-simulador").hide();
	$("div#ofertas-panel-preaprobado").hide();	
	
	closeFancy();
};

Ofertas.combo_checkBox  = function(input, ofert_id, table_id, renovationNumber , clase ) 
{
	var frequency;
	var ammount;
	var payment;
	var oferta_TOKEN;
	
	claseSel = clase;
	
	var parent_id = "tabla-oferta-" + table_id + "-"+ renovationNumber;
	
	$("div#panel-tabla-ofertas").find("div.fa-check").each(function (index, el)
	{				
		$(el).removeClass("fa-check");	
	});
	
	$(input).parent().parent().addClass("fa-check");
	
	
	$("div.btn-oferta-continuar").each(function (index, el)
	{
		$(el).hide();
	});
	
	$("div#" + parent_id).find("div.btn-continuar-" + table_id + "-" + renovationNumber).show();
	  
	frequency = $("div#" + parent_id).find("div.ofert-frequency-" + table_id + "-" + renovationNumber).html();
    ammount   = $("div#" + parent_id).find("td.ofert-ammount-" + ofert_id).html();
    payment   = $("div#" + parent_id).find("td.ofert-payment-" + ofert_id).html();
    
    console.log("Ofertas.combo_checkBox():");
    console.log("> oferta    = " + parent_id);
    console.log("> frequency = " + frequency);
    console.log("> ammount   = " + ammount);
    console.log("> payment   = " + payment);    
    
    var oferta_TOKEN = frequency + "::" + ammount + "::" + payment + "::" + parent_id;
    
    $("div#ofertas-remote-command").find("input.init-oferta").val(oferta_TOKEN).trigger("click");
};

Ofertas.ofert_oncomplete = function(xhr, status, args)
{
	var frequency    = args.frequency;
	var term_id      = args.term_id;
	var frequency_id = args.frequency_id;
	var ammount      = args.ammount;
	var payment      = args.payment;
	var oferta_SELECTED = args.oferta_SELECTED;
	
    console.log("Ofertas.ofert_oncomplete():");
    console.log("> oferta       = " + oferta_SELECTED);
    console.log("> frequency    = " + frequency);
    console.log("> term_id      = " + term_id);
    console.log("> frequency_id = " + frequency_id);    
    console.log("> ammount      = " + ammount);
    console.log("> payment      = " + payment);    
    
    closeFancy();
};

Ofertas.init_simulation_ofert = function()
{			
	$("div#ofertas-remote-command").find("a.init-simulation-ofert").trigger("click");
};

Ofertas.simulation_ofert_oncomplete = function(xhr, status, args)
{
	var FROM_TABLA_OFERTAS = 1;
	
	var cat = args.cat;	
	
    console.log("Ofertas.simulation_ofert_oncomplete():");
    console.log("> cat = " + cat);
    
    Preaprobador.init_panel(FROM_TABLA_OFERTAS);
    
    closeFancy();
    
    $(".prestamoAprobado").removeClass("amarillo");
    $(".prestamoAprobado").removeClass("azul");
	$(".prestamoAprobado").removeClass("morado");
	
	//if($(".tablaUn.azul  input:checked").length != 0) {
	if(claseSel == 'azul'	) {
    	
    	$(".prestamoAprobado").removeClass("amarillo");
		$(".prestamoAprobado").removeClass("morado");
		$(".prestamoAprobado").addClass("azul")
    }
    //else if($(".tablaUn.morado  input:checked").length != 0) {
	
	else if(claseSel == 'morado'	) {
    	
    	   $(".prestamoAprobado").removeClass("amarillo");
    	    $(".prestamoAprobado").removeClass("azul");
    		$(".prestamoAprobado").addClass("morado");
    }
    //else  if($(".tablaUn.amarillo  input:checked").length != 0) {
	else if(claseSel == 'amarillo'	) {
    	
        $(".prestamoAprobado").removeClass("azul");
    	$(".prestamoAprobado").removeClass("morado");
		$(".prestamoAprobado").addClass("amarillo")
    }
    
};

function prestamoAprobadoTable ()
{
	//$( ".prestamoAprobado tr:odd" ).css( "background-color", "#f1f1f1" );
}

function loader()
{	
	$("#resultadosSimNeg").css("display","none");
	$("#loaderSimNeg").css("display","block");
	
	 return true;
}

function showRes()
{	
	console.log("showRes()");
	
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}