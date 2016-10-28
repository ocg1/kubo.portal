console.log("mi_prestamo.js");

MiPrestamo.ocultar_address_HOME = function()
{
	console.log("MiPrestamo.ocultar_address_HOME(): INIT");

	var lista_job_same_address = $("div.job_is_same_address");
	var lista_length           = $(lista_job_same_address).length;
	
	console.log("MiPrestamo.ocultar_address_HOME(): " + lista_length);
	
	if(lista_length > 0)
	{
		$.each($(lista_job_same_address), function(index, value)
		{							
			var is_same_address = $(this).find("input:radio[value~='N']").is(":checked");
			
			if(is_same_address)
			{
				var domicilio_ID = $(this).find("input:hidden").val();
				
				console.log("MiPrestamo.ocultar_address_HOME(): " + domicilio_ID);
				
				$("div#" + domicilio_ID).html("");
			}									
		});
	}
};

							
function returnSellSigma()
{	
	if($("#uibus\\:0\\:rdSellSigma\\:0:checked" ).val() + " is checked!")
	{
		$("#uibus\\:0\\:sigmaNumber").val("");
	}
	
	fieldCount();	
}

function questionReceiptPayroll(component) 
{
	var clientId = component.replace(/:/g, '\\:');
	
	var elementRadio = $("input[name=" + clientId + "]:checked");	
	
	if(elementRadio.val() == '1') 
	{	    	
		$("#"+clientId).parent().parent().next().slideUp();	
		
	} else if(elementRadio.val() == '2') {
		
		$("#"+clientId).parent().parent().next().slideDown();
	}
	
	fieldCount();
	
	return true;  
}


