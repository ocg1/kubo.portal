$(document).ready(init_listeners);

function init_listeners(event)
{
	$("span#help").click(listener_panel_informacion_tipos_credito);
}

function listener_panel_informacion_tipos_credito(event)
{		
	event.preventDefault();
	
	var offset = $(this).offset(); 
	
	//alert("top: "+(parseInt(offset.top)-124)+" left: "+(parseInt(offset.left) + 34));
	
	var elmt = $("#helpCont");
	
	elmt.css("position", "absolute");
	
	elmt.offset(
	{ 
		top:  (parseInt(offset.top)  - 124), 
		
		left: (parseInt(offset.left) + 34)
	});
	
	elmt.show();		
}


function checkSelected()
{	
	var loantypedesc = "" ;
	var additionaltypedesc = "";
	
	var selectedloantype = "";
	
	var selected = $("input[type='radio'][name='loantype']:checked");
	
	if (selected.length > 0)
	{
		selectedloantype = selected.val();
	}		
	
	var selectedadditionalType = "";
	
	var selected2 = $("input[type='radio'][name='additionalType']:checked");
	
	var value_selected_OK         = true;
	var tipo_credito_selected_OK  = true;
	var tipo_consulta_selected_OK = true;
	
	if( typeof(selected.val()) == "undefined"  )
	{
		value_selected_OK        = false;
		tipo_credito_selected_OK = false;
	}
	
	if( typeof(selected2.val()) == "undefined"  )
	{
		value_selected_OK         = false;
		tipo_consulta_selected_OK = false;
	}
		
	if(value_selected_OK)
	{
		if (selected2.length > 0)
		{
			selectedadditionalType = selected2.val();
		}
					
		if( parseInt( selectedloantype ) == 1)
		{			
			loantypedesc = "Redocumentación";
			
		} else if( parseInt( selectedloantype ) == 2 ) {
			
			loantypedesc = "Crédito Adicional";
			
		} else if( parseInt( selectedloantype ) == 3 ) {
			
			loantypedesc = "Renovación";
			
		} else if( parseInt( selectedloantype ) == 4 ) {
			
			loantypedesc = "Nuevo";			
		}
		
		if( parseInt( selectedadditionalType ) == 1 )
		{			
			additionaltypedesc = "Con Nueva Consulta";
			
		} else if( parseInt( selectedadditionalType ) == 2) {
			
			additionaltypedesc = "Sin Nueva Consulta";			
		}
		
		var confirm_msg = "Esta seguro de querer habilitar al cliente para un crédito adicional con las siguientes caracteristicas:\n\n* "
			 			+ loantypedesc + "\n\n* " 
			 			+ additionaltypedesc + "\n";
		 

		alertify.confirm(confirm_msg, function (e) {
			
			if (e) {
			    var items = "";
				console.log("si acepto");
					$(".clCheck").each(function () 
					{				
						if($(this).is(':checked')) 
						{  					
							items += $(this).attr("id") + "MM";					
			        	} 				
			    	});
													
					$("#elementID").val(items);			
					$("#elementID").blur();
						
						displayMessageProcessing('msgprocessing', false);
						$("#generarNuevaSolicitud").click();
							return true;
						} else { 
							console.log("no acepto");
							return false;
						}
						
					
					}); 
			
	} else {
		
		if( ! tipo_credito_selected_OK )
		{
			alertify.alert( "Selecciona el tipo de crédito" );
			
		} else if( ! tipo_consulta_selected_OK) {
			
			alertify.alert( "Selecciona el tipo de consulta a realizar");
		}
					
		return false;		
	}		
}

function closeHelpCont()
{
	$("#helpCont").hide();
}
