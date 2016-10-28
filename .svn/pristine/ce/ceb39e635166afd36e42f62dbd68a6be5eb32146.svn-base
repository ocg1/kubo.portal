console.log("mx.com.kubo.registro/documentacion.js");

Documentacion.for_third_party_on_complete = function(xhr, status, args)
{	
	$("a.init-third-party-name").trigger("click");
	
	console.log("Documentacion.for_third_party_on_complete(): OK");
};

Documentacion.third_party_name_on_complete = function(xhr, status, args)
{
	var third_party_name = args.third_party_name;
	
	propios_terceros (); 
	
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	conjuntoCombos();
	console.log("Documentacion.third_party_name_on_complete(): " + third_party_name);
};

Documentacion.pr_first_name_on_complete = function(xhr, status, args)
{
	$("a.init-third-party-name").trigger("click");
	
	console.log("Documentacion.pr_first_name_on_complete(): OK");
};

Documentacion.pr_father_last_name_on_complete = function(xhr, status, args)
{
	$("a.init-third-party-name").trigger("click");
	
	console.log("Documentacion.pr_father_name_on_complete(): OK");
};

Documentacion.pr_mother_last_name_on_complete = function(xhr, status, args)
{
	$("a.init-third-party-name").trigger("click");
	
	console.log("Documentacion.pr_mother_last_name_on_complete(): OK");
};

function propios_terceros () 
{
	var propio_ENABLED  = $(".actuaPorCuenta input[value='P']").is(":checked");
	var tercero_ENABLED = $(".actuaPorCuenta input[value='T']").is(":checked")
	
	if(propio_ENABLED) 
	{
		propios ();
	}
		
	if(tercero_ENABLED) 
	{
		terceros ();
	}
		
/*		
	$(".actuaPorCuenta input[value='P']").click(function()
	{
		 propios ();	
	});
	
	$(".actuaPorCuenta input[value='T']").click(function()
	{		
		terceros ();
	});
*/	
}

function propios () 
{
	$(".terceros").slideUp();
	$(".propios").slideDown();
}

function terceros () 
{
	$(".propios").slideUp();
	$(".terceros").slideDown();	
}