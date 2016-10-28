console.log("mx.com.kubo.inversionista/registro/beneficiario/neighborhood.js");

Beneficiario.Domicilio.lista_neighborhood_oncomplete = function(xhr, status, args)
{
	var isValid = args.isValid;
	var beneficiarie_id = args.beneficiarie_id;
	
	var toarray = eval('('+ args.neighborhood +')');
	
	console.log("Domicilio.lista_colonias_oncomplete(): ");
	console.log("> isValid: " + isValid);
	console.log("> beneficiarie_id: " + beneficiarie_id);
	
	var lista_colonias_ID  = "#lista_colonias_" + beneficiarie_id;
	
	if(isValid)
	{					
		//this.init_lista_colonias(beneficiarie_id, toarray);		
			
		this.asignar_lista_colonias(lista_colonias_ID, args.neighborhood);
		
		this.init_neighborhood(beneficiarie_id);
	} 
};

Beneficiario.Domicilio.init_lista_colonias = function(beneficiarie_id, toarray)
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
	
	var element = $("div#vivienda_codigo_postal_" + beneficiarie_id)
	
	var objSection = element.parents(".sectionAddress");
	
	objSection.find(".neighborhood").empty().append(options);											
};

Beneficiario.Domicilio.asignar_lista_colonias = function (component_ID, colonias)
{
	var lista_colonias       = eval('( '+ colonias + ')');
	var options              = "";	
	var options_TOKEN        = "";
	var options_loaded       = 0;	
	var ONLY_ONE_OPTION      = 1;
		
	console.log("Domicilio.asignar_lista_colonias(): ");
	console.log("> component_ID = " + component_ID);
	console.log("> lista_colonias.length = " + lista_colonias.length);
	
	if(lista_colonias.length != 1)
	{
		options = "<option value = '0'></option>";
	}
				
	for (index in lista_colonias)
	{			
		var colonia_id   = lista_colonias[index].id;
		var colonia_name = lista_colonias[index].neighborhood;
		
		 if(index != 'remove')
		 {
			 options += "<option value = " + "'" + colonia_id + "'" + ">" + colonia_name + "</option>";
			 
			 options_loaded += 1;
			 options_TOKEN  += colonia_id + " - " + colonia_name + "\n";				 
		 }						
	}
	
	console.log("\noptions_loaded = "    + options_loaded);
	console.log("\noptions_TOKEN  = {\n" + options_TOKEN + "}");
			
	var lista_colonias_HTML = $(component_ID).find("select");
	
	lista_colonias_HTML.empty().append(options);	
	
/*	
	if(options_loaded == ONLY_ONE_OPTION)
	{
		var option     = lista_colonias_HTML.find("option").get(0);
		var colonia_id = $(option).attr("value");
		
		lista_colonias_HTML.val(colonia_id).trigger("change");
		
		console.log("\ncolonia_SELECTED = " + colonia_id);
	}
*/	
};

Beneficiario.Domicilio.init_neighborhood = function(beneficiarie_id)
{	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-neighborhood").trigger("click");

	console.log("Domicilio.init_neighborhood(): OK");		
};

Beneficiario.Domicilio.neighborhood_oncomplete = function(xhr, status, args)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var beneficiarie_id = args.beneficiarie_id;
	var neighborhood_id = args.neighborhood_id;
	var StateName = args.StateName;
	var TownName  = args.TownName;	
	
	console.log("Domicilio.neighborhood_oncomplete(): ");
	console.log(" > beneficiarie_id = " + beneficiarie_id);	
	console.log(" > neighborhood_id = " + neighborhood_id);	
	console.log(" > StateName       = " + StateName);
	console.log(" > TownName        =  " + TownName);
	
	$("div#delegacion_municipio_" + beneficiarie_id).find("input").val(TownName);	
	$("div#vivienda_estado_" + beneficiarie_id).find("input").val(StateName);
	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-change-control-zipcode").trigger("click");
	
	$("div#lista_colonias_" + beneficiarie_id).find("select").val(neighborhood_id).trigger("change");
};

Beneficiario.Domicilio.neighborhood_id_oncomplete = function(xhr, status, args)
{	
	var neighborhood_id = args.neighborhood_id;
	var beneficiarie_id = args.beneficiarie_id;
	
	console.log("Domicilio.neighborhood_id_oncomplete(): ");
	console.log(" > neighborhood_id = " + neighborhood_id);
	console.log(" > beneficiarie_id = " + beneficiarie_id);
	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-change-control-neighborhood").trigger("click");
};

Vivienda.showNeighText = function(beneficiarie_id)
{	
	var otra_colonia_ID   = "#dvNeighborhoodText_" + beneficiarie_id;
	var lista_colonias_ID = "#dvNeighborhoodSel_"  + beneficiarie_id;
	
	$(lista_colonias_ID).fadeOut("slow", function() 
	{ 
		$(".neighborhood").val("");
		$(otra_colonia_ID).fadeIn("slow"); 
	});	
};

Vivienda.showNeighSel = function(beneficiarie_id)
{	
	var otra_colonia_ID   = "#dvNeighborhoodText_" + beneficiarie_id;
	var lista_colonias_ID = "#dvNeighborhoodSel_"  + beneficiarie_id;
	
	$(otra_colonia_ID).fadeOut("slow", function()
	{ 
		$("#neighborhood_text_control").val(""); 
		$("#neighborhood_text_control").blur();  
		$(lista_colonias_ID).fadeIn("slow"); 															
	});	
};