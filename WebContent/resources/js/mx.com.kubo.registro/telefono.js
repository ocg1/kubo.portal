console.log("telefono.js");

Telefono.init_focus_date = function(phone_type_id)
{	
	var remote_command = "div#telefono-remote-command";
	var PARTICULAR_FIJO    = 5;
	var PARTICULAR_CELULAR = 6;	
	
	var focus_date_ID;	
		
	switch(phone_type_id)
	{
		case PARTICULAR_FIJO:
			focus_date_ID = "a#focus-phone-fixed"
		break;
		
		case PARTICULAR_CELULAR:
			focus_date_ID = "a#focus-phone-cell";
		break;
	}
	
	console.log("Telefono.init_focus_date(): " + focus_date_ID);	
	
	$(remote_command).find(focus_date_ID).trigger("click");
};

Telefono.focus_on_complete = function(xhr, status, args)
{	
	var PARTICULAR_FIJO    = 5;
	var PARTICULAR_CELULAR = 6;	
	var inputEspejo;
	
	var focus_date    = args.focus_date;	
	var phone_type_id = args.phone_type_id;
	
	console.log("Telefono.focus_on_complete(): ");
	console.log(" > phone_type_id = " + phone_type_id);
	console.log(" > focus_date = " + focus_date);
	
	switch(phone_type_id)
	{
		case PARTICULAR_FIJO:			
		break;
		
		case PARTICULAR_CELULAR:						
			abrirSiguiente();
		break;
	}
};

Telefono.update_oncomplete = function(xhr, status, args)
{
	var change_control_OK = args.change_control_OK;
	
	console.log("Telefono.info_oncomplete(): ");
	
	console.log("> change_control_OK = " + change_control_OK);
};

function validacionLada(inputEspejo) 
{
	console.log("Telefono.validacionLada():");
	
	var inputEspejoValor = $(inputEspejo).val();
	var inputReal = $(inputEspejo).next("input");
	
	inputReal.val(inputEspejoValor);	
}

function validacionLada2(inputEspejo) 
{
	console.log("Telefono.validacionLada2():");
	
	var inputEspejo = $(inputEspejo);
	var inputEspejoValor = $(inputEspejo).val();
	
	var inputReal = $(inputEspejo).next("input");
	
	var posibleLada =  inputEspejoValor.slice(0,3);
	var restoNumero =  inputEspejoValor.slice(3);
	
	if(posibleLada == "044" ||posibleLada == "045")
	{
		inputReal.val(restoNumero);
	}
	
	inputReal.trigger("click");

	validacionLLenadoMirror (inputEspejo, posibleLada);	
}

function validacionLLenadoMirror (inputEspejo, posibleLada) 
{
	console.log("Telefono.validacionLLenadoMirror():");
	
	var inputEspejo = $(inputEspejo);
	var inputEspejoValor = inputEspejo.val();
	
	if(inputEspejoValor != "" )
	{		
		if($("#nacionalidad option:selected").val() == 1)
		{
			if(inputEspejoValor.length == 10 || inputEspejoValor.length  == 13 )
			{
				if(inputEspejoValor.length == 10 )
				{
					if(posibleLada != "044" && posibleLada != "045")
					{
						inputEspejo.removeClass("vacio");
						
						console.log("no incluye lada")
						
						$(".error-telefono").slideUp();
						$(".iconoValidacion").addClass("fa-check");
						$(".iconoValidacion").removeClass("fa-times");
						$(".iconoValidacion span").html("Correcto");

					} else {
							
						inputEspejo.addClass("vacio");
						
						console.log("10 e incluye lada")
						
						$(".error-telefono").slideDown();
						$(".iconoValidacion").removeClass("fa-check");
						$(".iconoValidacion").addClass("fa-times");
						$(".iconoValidacion span").html("Incorrecto");
						
					}
				}
					
				if(inputEspejoValor.length == 13 )
				{
					if( posibleLada == "044" || posibleLada == "045")
					{
						inputEspejo.removeClass("vacio");
						
						$(".error-telefono").slideUp();
						$(".iconoValidacion").addClass("fa-check");
						$(".iconoValidacion").removeClass("fa-times");
						$(".iconoValidacion span").html("Correcto");
						
					} else {
							
						inputEspejo.addClass("vacio");
						
						$(".error-telefono").slideDown();
						$(".iconoValidacion").removeClass("fa-check");
						$(".iconoValidacion").addClass("fa-times");
						$(".iconoValidacion span").html("Incorrecto");
					}
				}
				
			} else {
				
				inputEspejo.addClass("vacio");
					
				$(".error-telefono").slideDown();
				
				console.log("diferente a 10 o 13 caracteres");
				
				$(".iconoValidacion").removeClass("fa-check");
				$(".iconoValidacion").addClass("fa-times");
				$(".iconoValidacion span").html("incorrecto");
				
			}
				
		} else {
			
			inputEspejo.removeClass("vacio")
		}
		
	} else {
		
		console.log("vacio en blanco");
		
		inputEspejo.addClass("vacio");
	}
	
	console.log(posibleLada);
	
}

function validacionLLenadoMirror2(campoTelefonoLada) 
{
	console.log("Telefono.validacionLLenadoMirror2(): ");
	
	$(campoTelefonoLada).blur();
}

function resetMirrorTelefono (container)
{
	console.log("Telefono.resetMirrorTelefono(): ");
	
	var contenedorTelefono = $(container);
	var inputFake = $(container).find(".validacionLadaField");
	var inputRealValue = inputFake.next("input").val();
	
	if(inputRealValue != "")
	{
		inputFake.val(inputRealValue);
		inputFake.blur();
	}
}

function validateTelephono(evt, input)
{
	console.log("Telefono.validateTelephono(): ");
	
	var campo = "Teléfono";
	
	if(input.id.indexOf("phoneFixed") != -1)
	{
		campo = "Teléfono";
	}
	
	if(input.id.indexOf("phoneCel") != -1)
	{
		campo = "Celular";
	}
	
	var splitNumero = input.value.split("");
	var valorEntrante = String.fromCharCode(evt.charCode);
	var error = false;
	
	
	for(var i = 0; i < splitNumero.length; i++)
	{
		if(splitNumero.length > 3)
		{
			//Numeros iguales (555555)
				if(valorEntrante == splitNumero[i] && valorEntrante == splitNumero[i+1] && valorEntrante == splitNumero[i+2] && valorEntrante == splitNumero[i+3] && valorEntrante == splitNumero[i+4])
				{
					error = true;
					input.value = "";
				}
				
		/*
			//Numeros consecutivos (12345)
				if(parseInt(valorEntrante) -4 == splitNumero[i] && parseInt(valorEntrante)-3 == splitNumero[i+1] && parseInt(valorEntrante)-2 == splitNumero[i+2] && parseInt(valorEntrante)-1 == splitNumero[i+3]){
					error = true;
					input.value = "";
				}
			//Numeros (54321)
				if((parseInt(valorEntrante) + 4 == splitNumero[i]) && (parseInt(valorEntrante) + 3 == splitNumero[i+1]) && (parseInt(valorEntrante) + 2 == splitNumero[i+2]) && (parseInt(valorEntrante) + 1 == splitNumero[i+3])){
					error = true;
					input.value = "";
				}
			//2 numeros iguales (151515)
				if((splitNumero[i] +""+ splitNumero[i+1] == splitNumero[i+2] +""+ splitNumero[i+3]) && (splitNumero[i] +""+ splitNumero[i+1] == splitNumero[i+4] +""+ splitNumero[i+5])){
					error = true;
					input.value = "";
				}
			*/
		}
	}
	
	if(error)
	{
		alertify.error('Número de ' + campo + ' incorrecto.');
		
		return false;
	}
	
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    {
    	return false;	
    }       

    return true;
}

function valueGreaterSix(input)
{
	console.log("Telefono.valueGreaterSix(): ");
	
	var campo = "Teléfono";
	
	if(input.id.indexOf("phoneFixed") != -1  || input.id.indexOf("phoneCel") != -1 )
	{
		if(input.id.indexOf("phoneFixed") != -1)
		{
			campo = "Teléfono";
		}
		
		if(input.id.indexOf("phoneCel") != -1)
		{
			campo = "Teléfono Celular";
		}
	}
	
	if(input.value.length <10 && input.value.length != 0)
	{
		alerta ('El '+ campo +' debe tener 10 dígitos, no olvides incluir tu LADA', "#"+$(input).attr("id"));
		
		console.log("#"+$(input).attr("id"));
		
	} else {
		
		alertaQuitar ("#"+$(input).attr("id"));
	}
}

function abrirSiguiente() 
{
	console.log("Telefono.abrirSiguiente(): ");
	
	var siguientePanelBtn = $("#phoneCel_employ").closest("section").next("div.section");
	
	if(!siguientePanelBtn.hasClass("active"))
	{
		siguientePanelBtn.trigger("click");
	}
}