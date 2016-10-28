console.log("estatus/asignar_lista_motivos_de_cambio.js");

function asignar_lista_motivos_de_cambio(xhr, status, args)
{	
	console.log("\nasignar_lista_motivos_de_cambio()");
	console.log("lista_motivos_de_cambio");
	
	var lista_motivos = args.lista_motivos_de_cambio;		
	
	if( lista_motivos != "undefined" )
	{	
		var select_HTML = "<select "
					     + "id       = 'select_lista_de_motivos' "
					     + "class    = 'elementStlSelMonth' "
				
					     + "onchange = 'set_selected_motive(this)'>" 
					     + "<option value = ''></option>";
				
		var lista_motivos_size = lista_motivos.split("::").length;
		
		console.log("lista_motivos_size: " + lista_motivos_size + "\n");
		
		for(var i = 1; i < lista_motivos_size; i++)
		{
			var motive_id          = (lista_motivos.split("::"))[i].split(";")[0];
			var motive_description = (lista_motivos.split("::"))[i].split(";")[1];
			
			console.log(motive_id + ";" + motive_description);
			
			select_HTML += "<option value = '" + motive_id + "' >"+ motive_description + "</option>";
		}
		
		select_HTML += "</select> ";
		
		$("#dvContMotiveSelStatus").html(select_HTML);	
	}
}