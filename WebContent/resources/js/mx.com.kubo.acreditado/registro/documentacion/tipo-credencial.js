console.log("mx.com.kubo.acreditado/registro/tipo-credencial.js");

function listener_tipo_credencial(xhr, status, args)
{
	var IFE = 1;
	var INE = 2;
	var Pasaporte = 3;
	var Cartilla = 4;
	var Licencia = 5;
	var Cedula = 6;
	
	var tipo_credencial = args.tipo_credencial;
	
	console.log("listener_tipo_credencial(): " + tipo_credencial);
	
	switch(tipo_credencial)
	{
		case IFE:
			$(".ine").slideUp();
			$(".ife").slideDown();
			$(".ine_domicilio").slideDown();
		break;
		
		case INE:
			$(".ife").slideUp();
			$(".ine").slideDown();
			$(".ine_domicilio").slideDown();
		break;
		case Pasaporte:
			$(".ife").slideUp();
			$(".ine").slideUp();
			$(".ine_domicilio").slideUp();
		break;
		case Cartilla:
			$(".ife").slideUp();
			$(".ine").slideUp();
			$(".ine_domicilio").slideUp();			
		break;
		case Licencia:
			$(".ife").slideUp();
			$(".ine").slideUp();
			$(".ine_domicilio").slideUp();
		break;
		case Cedula:
			$(".ife").slideUp();
			$(".ine").slideUp();
			$(".ine_domicilio").slideUp();
		break;
	}
	conjuntoCombos();
}

function init_tipo_credencial()
{	
	console.log("init_tipo_credencial(): INIT");
	
	$.each($("#ine_ife").find("input:radio"), function(index, value)
	{
		console.log("init_tipo_credencial lets go")
		var IFE = "1";
		var INE = "2";
		var Pasaporte = "3";
		var Cartilla = "4";
		var Licencia = "5";
		var Cedula = "6";
		var tipo_credencial = $(this).val();
		var radio_selected  = $(this).is(":checked");
		
		console.log(tipo_credencial + " " + radio_selected);
		
		if(radio_selected)
		{												
			switch(tipo_credencial)
			{
			case IFE:
				$(".ine").slideUp();
				$(".ife").slideDown();
				$(".ine_domicilio").slideDown();
			break;
			
			case INE:
				$(".ife").slideUp();
				$(".ine").slideDown();
				$(".ine_domicilio").slideDown();
			break;
			case Pasaporte:
				$(".ife").slideUp();
				$(".ine").slideUp();
				$(".ine_domicilio").slideUp();
			break;
			case Cartilla:
				$(".ife").slideUp();
				$(".ine").slideUp();
				$(".ine_domicilio").slideUp();			
			break;
			case Licencia:
				$(".ife").slideUp();
				$(".ine").slideUp();
				$(".ine_domicilio").slideUp();
			break;
			case Cedula:
				$(".ife").slideUp();
				$(".ine").slideUp();
				$(".ine_domicilio").slideUp();
			break;
			}						
		}
	});
	
	console.log("init_tipo_credencial(): OK");
}