console.log("busqueda.js");

/*
define(function()
{
	var modulo = 
	{		
		init: function()
		{
			console.log("busqueda.init(): OK");						
		}
	};
	
	return modulo;
});
*/

Busqueda.init = function()
{		
	$("#dvCntCancel").show();
	
	$("form").keypress(function(e) 
	{ 
		if (e.which == 13) 
		{
			return false;
		}
	});
			
	$("h2.expand_heading").click(function(event)
	{			
		var is_toggled = $("a#busqueda-solicitud").hasClass('change');
		
		console.log("expand_heading(): " + is_toggled);
		
		if(is_toggled)
		{
			$("div#dvCntSearchProfile").find("div.toggle_container").slideUp("slow");
			
			$("a#busqueda-solicitud").removeClass("change");
			
		} else {  
			
			$("div#dvCntSearchProfile").find("div.toggle_container").slideDown("slow");
			
			$("a#busqueda-solicitud").addClass("change");
		}
		
		event.preventDefault();
	});		
	
	console.log("Busqueda.init(): OK");
	acreInvRadio ();
};

Busqueda.init_css = function()
{
	var filtro_area = $("div#filtro_area table td").eq(0);
	
	filtro_area.css("width", "113px");
};


Busqueda.listener_input_autocomplete = function(input_autocomplete_ID)
{
	var search_SELECTOR = "#" + input_autocomplete_ID + " input";
	var search_VALUE    = $(search_SELECTOR).eq(1).val();
	
	var is_numeric = $.isNumeric(search_VALUE);
	
	if(is_numeric)
	{
		console.log("Busqueda.listener_input_autocomplete(): " + search_VALUE);
		
	} else {
		
		console.log("Busqueda.listener_input_autocomplete(): " + is_numeric);
	}
};

function acreInvRadio () {
	if($("#filtro_area input:radio[value=5]").prop('checked') ) {
		$("#radio-type-search1 td:eq(3)").addClass("ocultarTD");
		$("#filtro_area td:eq(0)").addClass("active");
		
		if($("#radio-type-search1 td:eq(3)").hasClass("active")){
			$("#radio-type-search1 td:eq(0) input:radio").trigger("click");
		}
		
		$("#radio-type-search1 td:eq(4)").removeClass("ocultarTD");
		$("#filtro_area td:eq(1)").removeClass("active");
		
		
	}
	
	if($("#filtro_area input:radio[value=6]").prop('checked') ) {
		$("#radio-type-search1 td:eq(3)").removeClass("ocultarTD");
		$("#filtro_area td:eq(0)").removeClass("active");
		
		
		$("#radio-type-search1 td:eq(4)").addClass("ocultarTD");
		$("#filtro_area td:eq(1)").addClass("active");
		
		if($("#radio-type-search1 td:eq(4)").hasClass("active")){
			$("#radio-type-search1 td:eq(0) input:radio").trigger("click");
		}
	} 
	
		var filtro = $("#filtro_prospecto input:radio:checked")
			
			$("#filtro_prospecto td").removeClass("active");
			
		filtro.closest("td").addClass("active");
		
		$("#search_VALUE_input").val(" ");
		$("#search_VALUE_input").blur()
		
		
}

Busqueda.changelabelSearch = function(componentoption, componentSearch, labelSearch, widgetvar)
{
	acreInvRadio ();
	
	console.log("Busqueda.changelabelSearch():");
	console.log("componentoption = " + componentoption);
	console.log("componentSearch = " + componentSearch);
	console.log("labelSearch     = " + labelSearch);
	console.log("widgetvar       = " + widgetvar);
	
	var elementSearch = $("#" + componentSearch);
	var elementLabel  = $("#" + labelSearch);
	var filtro_SELECTED = $(componentoption).val();
	
	console.log("filtro_SELECTED = " + filtro_SELECTED);
	
	switch(filtro_SELECTED)
	{
		case 2:
			elementLabel.text("Buscar por número de prospecto");
			elementSearch.val("");
			elementSearch.attr({
					  onkeypress: 'return isNumberKey(event);',
					  placeholder: 'Escribe el número de prospecto'
					  //placeholder: 'Escribe el número de folio'
					});	
			widgetvar.deactivate();
		break;
		
		case 3:
			elementLabel.text("Buscar por correo electronico");
			elementSearch.removeAttr("onkeypress");
			elementSearch.val("");
			elementSearch.attr('placeholder','Escribe el correo electronico');
			widgetvar.activate();
		break;
		
		default:
			elementLabel.text("Buscar por nombre completo");
			elementSearch.removeAttr("onkeypress");
			elementSearch.val("");
			elementSearch.attr('placeholder','Escribe el nombre');
			widgetvar.activate();
		break;
	}
};

Busqueda.init_membership_oncomplete = function(xhr, status, args)
{
	var email = args.email;
	var proyect_loan_TOKEN   = args.proyect_loan_TOKEN;
	var proyect_loan_ENABLED = args.proyect_loan_ENABLED;
	
	console.log("Busqueda.init_membership_oncomplete(): ");
	console.log("> email = " + email);
	console.log("> proyect_loan_ENABLED = " + proyect_loan_ENABLED);
	console.log("> proyect_loan_TOKEN   = " + proyect_loan_TOKEN);	
	
/*	
	if(proyect_loan_ENABLED)
	{
		$("#init-summary-request").trigger("click");
		
		displayMessageProcessing('msgprocessing',false);
	}
*/	
};

Busqueda.init_summary_request = function()
{	
	$("#init-summary-request").trigger("click");
	
	console.log("Busqueda.init_summary_request(): OK");
};

Busqueda.completSearch = function(xhr, status, args) 
{
	console.log("Busqueda.completSearch():");
	
	var isValid 	         = args.isValid;	
	var isPerson 	         = args.isPerson;
	var actividad_ENABLED    = args.actividad_ENABLED;
	var proyect_loan_ENABLED = args.proyect_loan_ENABLED;
	var investor_ENABLED     = args.investor_ENABLED;
	
	console.log("> isValid  = " + isValid);
	console.log("> isPerson = " + isPerson);	
	console.log("> proyect_loan_ENABLED = " + proyect_loan_ENABLED);
	console.log("> investor_ENABLED     = " + investor_ENABLED);
	console.log("> actividad_ENABLED    = " + actividad_ENABLED);	
	
	init_person(isPerson);
	init_acreditado(isValid);		
	init_investor(investor_ENABLED);
	init_actividad(actividad_ENABLED);
	init_not_finded(isPerson, isValid, investor_ENABLED);
};


function init_person(isPerson)
{
	console.log("init_person(): " + isPerson);
	
	if(isPerson)
	{		
		$('.profile').hide(); 
		 $('.profileControlTable').hide();
		 $("#dvCntDiagnostico").hide();
		 
		 $('#dvSumaryCnt').hide(); 		 
		 $('#dvCntBuroReport').hide(); 
		 
		 $("#dvCntActivityPerson").show();
		 //closeMessageProcessing();
		 
		 $("#error_msg").text("");
		 
		 setTimeout(function()
		 {
			 initActivity();
			 
		 },1000);		 		 		 
	}	
}

function init_acreditado(isValid)
{	
	console.log("Busqueda.init_acreditado(): " + isValid);
	
	if(isValid) 
	{
		closeMessageProcessing();
		
		Resumen.init();
		
		$("#error_msg").text("");				
	} 
}


function init_investor(investor_ENABLED)
{
	console.log("init_investor(): " + investor_ENABLED);
	
	if(investor_ENABLED)
	{								
		Solicitud.init_investor();
	}		
}

function init_actividad(actividad_ENABLED)
{
	console.log("init_actividad(): " + actividad_ENABLED);
	
	if(actividad_ENABLED)
	{
		
	}
}

function init_not_finded(isPerson, isValid, investor_ENABLED)
{
	if(!isPerson && !isValid && !investor_ENABLED) 
	{
		closeMessageProcessing();
		
		var search_VALUE = $("#inp_search_input").val();
		
		$("#error_msg").text("No se encontro la solicitud " + search_VALUE);		
	}
}
