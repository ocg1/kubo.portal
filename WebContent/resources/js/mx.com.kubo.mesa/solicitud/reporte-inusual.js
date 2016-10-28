console.log("mx.com.kubo.mesa/solicitud/reporte-inusual.js");

Inusual.init_modal = function()
{			
	$("div#modal-reporte-inicial").addClass("show");
	
	$(".velo").fadeIn();
};

Inusual.close_modal = function()
{	
	$("div#modal-reporte-inicial").removeClass("show");
	
	$(".velo").fadeOut("show");
};

Inusual.behavior_id_on_complete = function(xhr, status, args)
{	
	var behavior_id = args.behavior_id;
	
	console.log("Inusual.behavior_id_on_complete(): " + behavior_id);
};

Inusual.comments_on_complete = function(xhr, status, args)
{	
	var comments = args.comments;
	
	console.log("Inusual.comments_on_complete(): " + comments);
};

Inusual.reporte_on_complete = function(xhr, status, args)
{
	var persist_OK = args.persist_OK;
	
	console.log("Inusual.reporte_on_complete(): " + persist_OK);
	
	this.close_modal();
};