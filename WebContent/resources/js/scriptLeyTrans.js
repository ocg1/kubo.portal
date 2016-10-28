$(document).ready(function()
{	
	
	$("#caratula").click(function()
	{		
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #caratula');
	});
	
	$("#caratula_captacion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #caratula_captacion');
	});
	
	$("#condiciones").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #condiciones');
	});
	
	$("#condiciones_captacion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #condiciones_captacion');
	});
	
	$("#terminos").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #terminos_cond');
	});
	
	$("#terminos_captacion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #terminos_captacion');
	});
	
	$("#privacidad").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
			
		$("#contentAVCon").load('contLeyTrans.html #AvisoPriv');
	});
	
	$("#tasas_ahorro_inversion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
			
		$("#contentAVCon").load('contLeyTrans.html #tasas_ahorro_inversion_PDF');
	});
	
	$("#privCandi").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #privCandiCont');
	});
	
	$("#captacion_formato_aclaracion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #captacion_formato_aclaracion');
	});
	
	$("#redessociales").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #redessocialesCont');
	});
	
	$("#contrato_captacion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #contrato_captacion');
	});
	
	$("#sucursalesPago").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #sucursalesPago');
	});
	
	$("#sucursalesPago_captacion").click(function()
	{
		$("#menu_ley_transparencia").find('.selected').removeClass('selected');
		
		$(this).addClass('selected');
		
		$("#contentAVCon").load('contLeyTrans.html #sucursalesPago');
	});
	
	var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    var page = vars["page"];
    if(page=='comisiones'){
    	elementChild=$('.selected').parent();
		elementChild.find('.selected').removeClass('selected');
		$('#teminos').addClass('selected');
    	$("#contentAVCon").load('contLeyTrans.html #terminos_cond');
    }else if(page=='condiciones'){
    	
    	$("#condiciones").click();
    	
    }else if(page=='tasas'){
    	
    	$("#privacidad").click();
    	
    }else if(page=='aclaracion'){
    	
    	$("#privCandi").click();
    	
    }
    else if(page=='contrato'){
    	
    	$("#redessociales").click();
    	
    }
    else if(page=='contratosAnteriores'){
    	
    	$("#contratosAnteriores").click();
    	
    }
    else if(page=='sucursalesPago'){
    	
    	$("#sucursalesPago").click();
    	
    }
    else{
    	
    	$("#contentAVCon").load('contLeyTrans.html #caratula');
    	
    }
	
});

function showChangeLeyTrans(dvName){
	$("#contentAVCon").load('contLeyTrans.html #'+dvName);
}

function returnAction(dvName){
	
	$("#"+dvName).click();
	
}

function divShow(divName){
	$( "#"+divName ).slideDown();
}

function divHide(divName){
	$( "#"+divName ).slideUp();
}