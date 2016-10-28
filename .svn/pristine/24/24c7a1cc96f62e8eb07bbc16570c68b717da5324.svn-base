console.log("mx.com.kubo.mesa/solicitud/investor-category.js");

Resumen.init = function()
{
	console.log("Resumen.init(): OK");		
	
	$("#init-investor-category").trigger("blur");
	
/*	
	$("#sectionEditInstitutionalIvestor").off();
	
	$("#sectionEditInstitutionalIvestor").bind( "click", function() 
	{
		console.log("sectionEditInstitutionalIvestor: click");
		
		$("#init-investor-category").trigger("blur");
	});		
*/	
};

Resumen.kubo_property_on_complete = function (xhr, status, args)
{
	var kubo_property  = args.kubo_property;
	var category_id    = args.category_id;
	var category_TOKEN = args.category_TOKEN;
	var investor_category_ENABLED = args.investor_category_ENABLED;
	
	console.log("Resumen.kubo_property_on_complete(): ");
	console.log("> kubo_property  = " + kubo_property);
	console.log("> category_id    = " + category_id);	
	console.log("> category_TOKEN = " + category_TOKEN);
	console.log("> investor_category_ENABLED = " + investor_category_ENABLED);
	
	if(category_TOKEN != undefined && investor_category_ENABLED)
	{
		Resumen.init_lista_investor_category(category_id, category_TOKEN);
		
	} else {
		
		$("#panel-investor-category").html("").hide();
		
	}
};

Resumen.init_lista_investor_category = function(category_id, category_TOKEN)
{
	var select_HTML = "<select id = 'lista-investor-category' onchange = 'Resumen.init_investor_category_id(this)'>";
	
	select_HTML += "<option value = '-1'>Selecciona una categoría</option>";
	
	var category_TOKEN_size = category_TOKEN.split("::").length;
	
	console.log("category_TOKEN_size: " + category_TOKEN_size);
	
	for(var i = 1; i < category_TOKEN_size; i++)
	{
		var investor_category_id   = (category_TOKEN.split("::"))[i].split(";")[0];
		var investor_category_name = (category_TOKEN.split("::"))[i].split(";")[1];
		
		console.log(investor_category_id + ";" + investor_category_name);
		
		if(investor_category_id == category_id)
		{
			select_HTML += "<option value = '" + investor_category_id + "' selected='selected'>"+ investor_category_name + "</option>";
			
		} else {
			
			select_HTML += "<option value = '" + investor_category_id + "' >"+ investor_category_name + "</option>";
		}
		
	}		
	
	select_HTML += "</select> ";
	
	$("#panel-investor-category").html(select_HTML).show();
};

Resumen.init_investor_category_id = function (select_one_menu)
{
	var investor_category_id = select_one_menu.value;
	
	console.log("Resumen.init_investor_category_id (): " + investor_category_id);
	
	$("#input_investor_category_id").val(investor_category_id).trigger("blur");	
};

Resumen.investor_category_id_on_complete = function (xhr, status, args)
{
	var investor_category_id = args.investor_category_id;
	
	console.log("Resumen.investor_category_id(): " + investor_category_id);		
};