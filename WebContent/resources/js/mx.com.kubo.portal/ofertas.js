console.log("ofertas.js");

Ofertas.init_panel_simulador = function()
{
	//$("div#ofertas-remote-command").find("a.init-panel-simulador").trigger("click");
	
	$("div#ofertas-panel-simulador").show();
	$("div#simular-nuevo-prestamo").hide();		
	$("div#ofertas-panel-preaprobado").hide();
	$("div#panel-tabla-ofertas").hide(); 
};

/*
Ofertas.panel_simulador_oncomplete = function(xhr, status, args)
{
	var panel_simulador_ENABLED = args.panel_simulador_ENABLED;
	
	console.log("panel_simulador_oncomplete(): ");
	console.log("> panel_simulador_ENABLED = " + panel_simulador_ENABLED);
	
	if(panel_simulador_ENABLED)
	{
		$("div#ofertas-panel-simulador").show();
		$("div#simular-nuevo-prestamo").hide();		
		$("div#ofertas-panel-preaprobado").hide();
		$("div#panel-tabla-ofertas").hide(); 
	}
	
	closeFancy();
}
*/

Ofertas.init_tabla_ofertas = function()
{
	$("div#panel-tabla-ofertas").show();
	$("div#simular-nuevo-prestamo").show();	
	$("div#ofertas-panel-simulador").hide();
	$("div#ofertas-panel-preaprobado").hide();	
	
	closeFancy();
};

Ofertas.combo_checkBox  = function(input, ofert_id, table_id, renovationNumber) 
{
	var frequency;
	var ammount;
	var payment;
	var oferta_TOKEN;		
	
	var parent_id = "tabla-oferta-" + table_id + "-"+ renovationNumber;
	
	$("div#panel-tabla-ofertas").find("div.fa-check").each(function (index, el)
	{				
		$(el).removeClass("fa-check");	
	});
	
	$(input).parent().addClass("fa-check");
	
	
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
};

function prestamoAprobadoTable ()
{
	$( ".prestamoAprobado tr:odd" ).css( "background-color", "#f1f1f1" );
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

/*
$(document).ready(function()
{
	// Smart Tab
		$('#tabs').smartTab({autoProgress: false,stopOnFocus:true,transitionEffect:'vSlide'});
	
	$('#investment').click(function(){
			 
		location.href = "../jsf/preregistro.jsf?role=investment";
	
	})

	$(".btnNext1").click(function(){
		
		$(".unbound").fadeOut(500,function(){ $(".prestamoAprobado").fadeIn(500) });
		
	});
	
	$(".clsback").click(function(){
		
		$(".prestamoAprobado").fadeOut(500,function(){ $(".unbound").fadeIn(500) });
		
	});
	
	$(".btnGetLoan").click(function(){
		
		$(".prestamoAprobado").fadeOut(500,function(){ $(".txtConf1").fadeIn(500) });
		
	});
	
});
*/