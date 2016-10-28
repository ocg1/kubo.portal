console.log("mx.com.kubo.acreditado/registro/mi-prestamo/domicilio/neighborhood.js");

ActividadEconomica.Domicilio.lista_neighborhood_oncomplete = function(xhr, status, args)
{
	var isValid = args.isValid;
	var address_type_id = args.address_type_id;
	
	var toarray = eval('('+ args.neighborhood +')');
	
	console.log("Domicilio.lista_colonias_oncomplete(): ");
	console.log("> isValid:   " + isValid);
	
	if(isValid)
	{					
		this.init_lista_colonias(address_type_id, toarray);

		this.init_neighborhood(address_type_id);
			
	} else {
		
		element.validationEngine('showPrompt', '*Código postal incorrecto!','error','centerRight', true);
		
		objSection.find(".neighborhood").empty();
		objSection.find(".town").val("");
		objSection.find(".state").val("");				
	} 
};

ActividadEconomica.Domicilio.init_lista_colonias = function(address_type_id, toarray)
{
	console.log("Domicilio.init_lista_colonias():");
	
	var options = "";		
	
	if(toarray.length != 1)
	{
		options = "<option value='0'></option>";
	}
	
    for (target in toarray)
    {
	   if(target!='remove')
		  {
		   	options += "<option value='" + toarray[target].id + "'>" + toarray[target].neighborhood + "</option>";
		  }             			        
    }
    	
	console.log("> options: " + options);
	
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var element;
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			element = $("#business-zipcode").find("input");		
		break;
		
		case EMPLEO:
			element = $("#input_zip_code").find("input");
		break;
	}	
	
	var objSection = element.parents(".sectionAddress");
	
	objSection.find(".neighborhood").empty().append(options);											
};

ActividadEconomica.Domicilio.init_neighborhood = function(address_type_id)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	switch(address_type_id)
	{
		case NEGOCIO:
			$("div#business-domicilio").find("a.init-neighborhood").trigger("click");
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-neighborhood").trigger("click");
		break;
	}	
	
	console.log("Domicilio.init_neighborhood(): OK");		
};

ActividadEconomica.Domicilio.neighborhood_oncomplete = function(xhr, status, args)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var address_type_id = args.address_type_id;
	var neighborhood_id = args.neighborhood_id;
	var StateName = args.StateName;
	var TownName  = args.TownName;	
	
	console.log("Domicilio.neighborhood_oncomplete(): ");
	console.log(" > neighborhood_id = " + neighborhood_id);	
	console.log("> StateName: " + StateName);
	console.log("> TownName:  " + TownName);
	
	var element;
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			element = $("#business-zipcode").find("input");		
		break;
		
		case EMPLEO:
			element = $("#input_zip_code").find("input");
		break;
	}		
	
	var objSection = element.parents(".sectionAddress");
	
	element.parent().children('.formError').remove();
	
	this.init_town(objSection, StateName);
	this.init_state(objSection, TownName);
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			$("div#business-domicilio").find("a.init-change-control-zipcode").trigger("click");
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-change-control-zipcode").trigger("click");
		break;
	}			
	
	objSection.find(".neighborhood").val(neighborhood_id).trigger("change");
};

ActividadEconomica.Domicilio.neighborhood_id_oncomplete = function(xhr, status, args)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var neighborhood_id = args.neighborhood_id;
	var address_type_id = args.address_type_id;
	
	console.log("Domicilio.neighborhood_id_oncomplete(): ");
	console.log(" > neighborhood_id = " + neighborhood_id);	
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			$("div#business-domicilio").find("a.init-change-control-neighborhood").trigger("click");	
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-change-control-neighborhood").trigger("click");
		break;
	}	
	
	
};

ActividadEconomica.Domicilio.init_town = function(objSection, town)
{
	objSection.find(".town").val(town);
};

ActividadEconomica.Domicilio.init_state = function(objSection, state)
{		
	objSection.find(".state").val(state);
};