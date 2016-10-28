console.log("domicilio.js");

Domicilio.init_focus_date = function(address_type_id)
{					
	var focus_date_ID = this.init_focus_date_ID(address_type_id);
	
	console.log("Domicilio.init_focus_date(): " + focus_date_ID);	
	
	$(focus_date_ID).trigger("click");
};

Domicilio.init_focus_date_ID = function(address_type_id)
{
	var focus_date;
	
	switch(address_type_id)
	{
		case 1:
		case 8:
			focus_date = "a#focus-domicilio";			
		break;
		
		case 9:			
			focus_date = "a#focus-domicilio-fiscal";
		break;
		
		default: break;
	}
	
	return focus_date;
};		

Domicilio.focus_on_complete = function(xhr, status, args)
{	
	var focus_date = args.focus_date;	
	
	console.log("Domicilio.focus_on_complete(): "+ focus_date);
};
var fecha1 = new Date();

var fecha2 = new Date();
Domicilio.zipcode_on_start = function() 
{		
	
	fecha1 = new Date();
	
	if( $("#zip_code").val().trim().length > 0 ){
	
	console.log( " length "+$("#zip_code").val().trim().length+" INIT BLUR ZIPCODE: " + fecha1 + " ");
	$(".loaderCP").show();
	$(".colDelEst").addClass("load");
	return true;

	
	}else{
		console.log( "INIT BLUR ZIPCODE VACIO: " + fecha1 + " ");
		
		return false;
	}
	
		
};

Domicilio.zipcode_on_complete = function(xhr, status, args) 
{		
	//var toarray = eval('('+ args.neighborhood +')');
	
	console.log( "1" );
	
	$("#init-domicilio").trigger("click");
	
	console.log( "2" );
	
	console.log("Domicilio.zipcode_on_complete(): ");
	
	console.log( "3" );
	//console.log(" > colonias = " + toarray);
	console.log("> isValid:   " + args.isValid);
	console.log( "4" );
	console.log("> TownName:  " + args.TownName);
	console.log( "5" );
	console.log("> StateName: " + args.StateName);
	console.log( "6" );
	console.log("> save_address_OK: " + args.save_address_OK);

		

	fecha2 = new Date();
	
	console.log( "REGRESA BLUR ZIPCODE: " + ( fecha2.getTime() - fecha1.getTime() ) + " milisegundos timestamp:  " + fecha2 + " " );
	$(".loaderCP").hide();
	$(".colDelEst").removeClass("load");
};

Domicilio.neighborhood_on_complete = function(xhr, status, args)
{
	var neighborhood_id = args.neighborhood_id;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.neighborhood_on_complete(): ");
	console.log("> neighborhood_id: " + neighborhood_id);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.neighborhood_text_on_complete = function(xhr, status, args)
{
	var neighborhood_text = args.neighborhood_text;
	var save_address_OK   = args.save_address_OK;
	
	console.log("Domicilio.neighborhood_text_on_complete(): ");
	console.log("> neighborhood_text: " + neighborhood_text);
	console.log("> save_address_OK:   " + save_address_OK);
};

Domicilio.street_on_complete = function(xhr, status, args)
{
	var street = args.street;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.street_on_complete(): ");
	console.log("> street: " + street);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.address_number_on_complete = function(xhr, status, args)
{
	var address_number  = args.address_number;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.address_number_on_complete(): ");
	console.log("> address_number:  " + address_number);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.apt_number_on_complete = function(xhr, status, args)
{
	var apt_number      = args.apt_number;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.apt_number_on_complete(): ");
	console.log("> apt_number:      " + apt_number);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.mx_manzana_on_complete = function(xhr, status, args)
{
	var mx_manzana  = args.mx_manzana;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.mx_manzana_on_complete(): ");
	console.log("> mx_manzana:      " + mx_manzana);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.mx_lote_on_complete = function(xhr, status, args)
{
	var mx_lote         = args.mx_lote;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.mx_lote_on_complete(): ");
	console.log("> mx_lote: " + mx_lote);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.residence_on_complete = function(xhr, status, args)
{
	var residence_id = args.residence_id;
	
	console.log("Domicilio.residence_on_complete(): ");
	console.log("> residence_id: " + residence_id);
};

Domicilio.first_reference_on_complete = function(xhr, status, args)
{
	var first_reference = args.first_reference;
	var save_address_OK = args.save_address_OK;
	
	console.log("Domicilio.first_reference_on_complete(): ");
	console.log("> first_reference: " + first_reference);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.second_reference_on_complete = function(xhr, status, args)
{
	var second_reference = args.second_reference;
	var save_address_OK  = args.save_address_OK;
	
	console.log("Domicilio.second_reference_on_complete(): ");
	console.log("> second_reference: " + second_reference);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.description_on_complete = function(xhr, status, args)
{
	var description       = args.description;
	var save_address_OK  = args.save_address_OK;
	
	console.log("Domicilio.description_on_complete(): ");
	console.log("> description:     " + description);
	console.log("> save_address_OK: " + save_address_OK);
};

Domicilio.init_neighborhood_text_ENABLED = function(neighborhood_text_ENABLED, address_type_id)
{
	console.log("Domicilio.init_neighborhood_text_ENABLED():");
	console.log(" > address_type_id = " + address_type_id);
	console.log(" > neighborhood_text_ENABLED = " + neighborhood_text_ENABLED);
	
	var ajax_TOKEN = neighborhood_text_ENABLED  + "::" + address_type_id;
	
	$("#init-neighborhood-text-ENABLED").val(ajax_TOKEN).trigger("click");
	
	
};

Domicilio.neighborhood_text_ENABLED_on_complete = function(xhr, status, args)
{
	var neighborhood_text_ENABLED = args.neighborhood_text_ENABLED;
	var address_type_id = args.address_type_id;
	
	console.log("Domicilio.neighborhood_text_ENABLED_on_complete():");
	console.log(" > address_type_id = " + address_type_id);	
	console.log(" > neighborhood_text_ENABLED = " + neighborhood_text_ENABLED);
	
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	if($("#neighborhood_text").length){
		$("#neighborhood_text").trigger("focus");
	}
		
	

};

Domicilio.legal_address_on_complete = function(xhr, status, args)
{
	var is_legal_address  = args.is_legal_address;
	var fiscal_ENABLED    = args.fiscal_ENABLED;
	var save_address_OK   = args.save_address_OK;
	
	console.log("Domicilio.legal_address_on_complete(): ");		
	console.log("> is_legal_address: " + is_legal_address);
	console.log("> fiscal_ENABLED:   " + fiscal_ENABLED);
	console.log("> save_address_OK: " + save_address_OK);

	$("#init-domicilio-fiscal").trigger("click");	
	
	closeFancy();


};

Domicilio.domicilio_on_complete_nuevo = function(){
	
	fecha2 = new Date();
	
	console.log( "FINALIZA TRIGGER CLICK DOMICILIO : " + ( fecha2.getTime() - fecha1.getTime() ) + " milisegundos fINAL 2 timestamp:  " + fecha2 + " "  );
};

Domicilio.domicilio_on_complete = function(xhr, status, args)
{
	var is_legal_address  = args.is_legal_address;
	var fiscal_ENABLED    = args.fiscal_ENABLED;
	
	console.log("Domicilio.domicilio_on_complete(): ");		
	console.log("> is_legal_address: " + is_legal_address);
	console.log("> fiscal_ENABLED:   " + fiscal_ENABLED);
	
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	
	fecha2 = new Date();
	
	console.log( "FINALIZA TRIGGER CLICK DOMICILIO : " + ( fecha2.getTime() - fecha1.getTime() ) + " milisegundos fINAL "  );
	
};

Domicilio.domicilio_fiscal_on_complete = function(xhr, status, args)
{
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	
	console.log("Domicilio.domicilio_fiscal_on_complete(): OK");
};

function init_mapa()
{
	 
	$('.mapClass').bind(
    {							
		change: function(event) 
		{
			generateStringAddress(this);
			event.preventDefault(); 
		}
	});
			    
	$(".validatorClass").bind("change blur", function(event) 
	{
		
		
		fieldCount();event.preventDefault();
		
		if($(this).val() != "" )
		{
			$(this).removeClass("requiredClass");
		}					
	});					
}

function inicia_mapa()
{
	
	if(map == null)
	{
		initialize($("#zip_code"));
						
	} else {
		
		initWithLatLong($("#zip_code"));
	
	}
}

function tabKey( sigCampo){
	var keyCode = keyCode || which; 
	
		  if (keyCode == 9) {
			  setTimeout(function(){
			  $(sigCampo).trigger("click");
			  console.log(sigCampo);
				},3000);
		  } 

	
}
