console.log("zipcode.js");

function container_editar_vivienda_TOKEN()
{
	$("#container_editar_vivienda_TOKEN").toggle("slow");
}

function callback_listener_codigo_postal(xhr, status, args) 
{
	var codigo_postal_valido = args.codigo_postal_valido;
	var is_estado_ENABLED    = args.is_estado_ENABLED;
	
	var delegacion_municipio = args.delegacion_municipio;
	var estado               = args.estado_name;
	var colonias             = args.colonias;
						
	console.log("callback_listener_codigo_postal():");
	
	console.log("codigo_postal_valido = " + codigo_postal_valido);
	console.log("is_estado_ENABLED      = " + is_estado_ENABLED);
	console.log("delegacion_municipio   = " + delegacion_municipio);
	console.log("estado                 = " + estado);		
	console.log("colonias               = " + colonias);
			
	if(codigo_postal_valido && is_estado_ENABLED)
	{	
		asignar_lista_de_colonias(colonias);
				
		$("#delegacion_municipio").find("input").val(delegacion_municipio);
		$("#vivienda_estado").find("input").val(estado);
		
	} else {
		
		$("#delegacion_municipio").find("input").val("");
		$("#vivienda_estado").find("input").val("");
	}			
}

function asignar_lista_de_colonias(colonias)
{
	var lista_colonias       = eval('( '+ colonias + ')');
	var options              = "";	
	var options_TOKEN        = "";
	var options_loaded       = 0;	
	var ONLY_ONE_OPTION      = 1;
			
	console.log("lista_colonias.length = " + lista_colonias.length);
	
	if(lista_colonias.length != 1)
	{
		options = "<option value = ''></option>";
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
	
	console.log("options_loaded = "    + options_loaded);
	console.log("options_TOKEN  = {" + options_TOKEN + "}");
			
	var lista_colonias_HTML = $("div#lista_colonias_por_codigo_postal").find("select");
	
	lista_colonias_HTML.empty().append(options);	
	
	if(options_loaded == ONLY_ONE_OPTION)
	{
		var option     = lista_colonias_HTML.find("option").get(0);
		var colonia_id = $(option).attr("value");
		
		lista_colonias_HTML.val(colonia_id).trigger("change");
		
		console.log("colonia_SELECTED = " + colonia_id);
	}
}

function init_vivienda_codigo_postal(codigo_postal)
{	
	var input_codigo_postal = $("div#container_editar_vivienda_TOKEN").find("div#vivienda_codigo_postal").find("input");
	
	$(input_codigo_postal).val(codigo_postal);
	
	console.log("init_vivienda_codigo_postal(): " + $(input_codigo_postal).val());
}