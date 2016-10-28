console.log("mx.com.kubo.mesa/solicitud/documentacion.js");

Documentacion.file_type_on_complete = function(xhr, status, args)
{
	var file_type_id = args.file_type_id;
	var reca_ENABLED = args.reca_ENABLED;
	var size_limit   = args.size_limit;
	
	console.log("Documentacion.file_type_on_complete(): ")
	console.log("> file_type_id = " + file_type_id);
	console.log("> reca_ENABLED = " + reca_ENABLED);	
	console.log("> size_limit   = " + size_limit);
};

Documentacion.reca_id_on_complete = function(xhr, status, args)
{
	var reca_id = args.reca_id;
	
	console.log("Documentacion.reca_id_on_complete(): " + reca_id);
};