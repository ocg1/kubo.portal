$(document).ready(function(){	
	
	//redirectavisoLegal();


$("#teminos").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #terminos_cond');
});
$("#privacidad").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #AvisoPriv');
});
$("#privCandi").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #privCandiCont');
});
$("#redessociales").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #redessocialesCont');
});

$("#requisitos").click(function()
{	
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #requisitos');
});

$("#seguro").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #seguro');
});

$("#cambioAviso").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #change_terminos');
	
	
});

$("#cambio_aviso_captacion").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #change_terminos_captacion');
});

$("#supervision").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #supervision');
});

$("#requisitos_captacion").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #requisitos_captacion');
});

$("#fondo_de_protecccion").click(function()
{
	$("#contentAvLeg").find('.selected').removeClass('selected');
	
	$(this).addClass('selected');
	
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #fondo_de_protecccion');
});



var vars = {};
var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
    vars[key] = value;
});
var page = vars["page"];
if(page=='terminos'){
	
	$("#teminos").click();
	
}else if(page=='privacidad'){
	
	$("#privacidad").click();
	
}else if(page=='redessociales'){
	
	$("#redessociales").click();
	
}
else if(page=='requisitos'){
	
	$("#requisitos").click();
	
}
else if(page=='seguro'){
	
	$("#seguro").click();
	
}
else if(page=='cambioAviso'){
	
	$("#cambioAviso").click();
	
}
else{
	
	$("#teminos").click();
	
}
	
});

function showChange(dvName){
	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #'+dvName);
}

function returnAction(){
	
	$("#cambioAviso").click();
	
}