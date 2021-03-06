console.log("datos-personales.js");

DatosPersonales.focus_on_complete = function(xhr, status, args)
{	
	var focus_date = args.focus_date;	
	
	console.log("DatosPersonales.focus_on_complete(): "+ focus_date);
};

DatosPersonales.gender_on_complete = function(xhr, status, args)
{
	var gender_id         = args.gender_id;
	var change_control_OK = args.change_control_OK;
	
	console.log("DatosPersonales.gender_on_complete(): " + gender_id);
	console.log("> change_control_OK = " + change_control_OK);
	
	$("a#init-panel-rfc-curp").trigger("click");
};

DatosPersonales.citizenship_on_complete = function(xhr, status, args)
{	
	var citizenship       = args.citizenship;
	var country_id        = args.country_id;
	var change_control_OK = args.change_control_OK;
	
	console.log("DatosPersonales.citizenship_on_complete(): "+ citizenship);
	console.log("> country_id        = " + country_id);
	console.log("> change_control_OK = " + change_control_OK);		
	
	$("a#init-panel-rfc-curp").trigger("click");
	//seleccionDomicilio ();
	
};

function seleccionDomicilio (){
	/*
	if( $("#nacionalidad option:selected").val() ==  "1" ){
		$("#seleccionDomicilio input[value='0']").trigger("click");
		$("#seleccionDomicilio").change();
	}
*/
}

DatosPersonales.country_id_on_complete = function(xhr, status, args)
{	
	var country_id = args.country_id;
	var change_control_OK = args.change_control_OK;
	
	console.log("DatosPersonales.country_id_on_complete(): " + country_id);
	console.log("> change_control_OK = " + change_control_OK);		
	
	$("a#init-panel-rfc-curp").trigger("click");
};

DatosPersonales.state_id_on_complete = function(xhr, status, args)
{	
	var state_id = args.state_id;
	var change_control_OK = args.change_control_OK;
	
	console.log("DatosPersonales.state_id_on_complete(): " + state_id);
	console.log("> change_control_OK = " + change_control_OK);	
	
	$("a#init-panel-rfc-curp").trigger("click");
};

DatosPersonales.CURP_generator_on_complete = function(xhr, status, args)
{
	var generator_OK = args.generator_OK;
	
	console.log("DatosPersonales.CURP_generator_on_complete(): " + generator_OK);
};

DatosPersonales.birthdate_on_complete = function(xhr, status, args)
{		
	var fecha_OK = args.fecha_OK;
	
	console.log("DatosPersonales.birthdate_on_complete(): " + fecha_OK);
	
	llenoVacioFecha();
	
	if(fecha_OK)
	{
		$("a#init-panel-rfc-curp").trigger("click");
	}	
};

