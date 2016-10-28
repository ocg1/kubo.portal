console.log("vivienda/validacion_formulario_edicion_vivienda.js");

function isNumberKey(evt)
{
	console.log("isNumberKey");
	
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	 
	 if (charCode > 31 && (charCode < 48 || charCode > 57))
	 {
		 return false;   	 
	 }
	           
	 return true;
}

function validateZipCode(component) 
{			
	console.log("\nvalidateZipCode(): " + component);
	
	var vivienda_codigo_postal = $("#" + component);
	
	var valor = vivienda_codigo_postal.find("input").val();
	
	var is_zip_code_valid = false;
	
	if(valor != "" && valor.length >= 5)
	{
		//$(vivienda_codigo_postal).parent().children('.formError').remove();		
		//fieldCount();
		
		is_zip_code_valid = true;
			
	} else if(valor == "") {	   
		
		is_zip_code_valid = false;
		
	} else if(valor.length < 5) {
		
		$(vivienda_codigo_postal).validationEngine('showPrompt', '*Minimo 5 números ej.01010','error','centerRight', true);
		
		is_zip_code_valid = false;		
	}   
	
	console.log("validateZipCode " + is_zip_code_valid);
	
	return is_zip_code_valid;
}	

function validar_formulario_edicion_vivienda()
{
	console.log("validar_formulario_edicion_vivienda(): INIT");		
	
	var is_validacion_OK = true;
	
	var motivo_del_cambio    = $("div#motivo_edicion_vivienda").find("textarea").val();
	var msg_error_formulario = $("div#msg_error_edicion_vivienda");
	
	var msg_error = "Se requiere ingresar el motivo del cambio";
	
	console.log("validar_formulario_edicion_vivienda(): " + motivo_del_cambio);
	
	if($.trim(motivo_del_cambio).length < 1)
	{
		is_validacion_OK = false;
		$(msg_error_formulario).find("p").html(msg_error);
		$(msg_error_formulario).show("fast");
		
	} else {
		
		$(msg_error_formulario).hide("fast");
		
		displayMessageProcessing('msgprocessing',false);
	}
	
	console.log("validar_formulario_edicion_vivienda(): " + is_validacion_OK);
				
	return is_validacion_OK;
}


function showNeighText()
{	
	$("#dvNeighborhoodSel").fadeOut("slow", function() 
	{ 
		$(".neighborhood").val("");
		$("#dvNeighborhoodText").fadeIn("slow"); 
	});	
}

function showNeighSel()
{	
	$("#dvNeighborhoodText").fadeOut("slow", function()
	{ 
		$("#neighborhood_text_control").val(""); 
		$("#neighborhood_text_control").blur();  
		$("#dvNeighborhoodSel").fadeIn("slow"); 															
	});	
}