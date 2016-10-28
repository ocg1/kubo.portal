console.log("notificaciones.js");

$(document).ready(function()
{	
	$("#actualizacion_datos").click(function()
	{
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #actualizacion_datos');
	});
	
	$("#cambioAviso").click(function()
	{
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #change_terminos');
		
	});
	
	$("#supervision").click(function()
	{
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #supervision');
	});

	
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) 
	{
	    vars[key] = value;
	});
	
	var page = vars["page"];
	
	console.log("page = " + page);
	
	if(page=='cambioAviso')
	{
		$("#cambioAviso").click();
		
	} else if(page=='supervision'){
		
		$("#supervision").click();
		
	} else if(page == 'actualizacion_datos'){
		
		$("#actualizacion_datos").click();
	}
	
});

function showChange(dvName){
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #'+dvName);
}

function returnAction(){
	
	$("#cambioAviso").click();
	
}