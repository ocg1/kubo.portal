$(document).ready(function(){	
	
	$("#caratula").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('contBuroEntidades.html #caratula');
	});
	$("#condiciones").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('contBuroEntidades.html #condiciones');
	});
	$("#infoKuboBuro").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('contBuroEntidades.html #dvInfoKuboBuro');
	});

	
	var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    var page = vars["page"];
    
    if(page=='comisiones')
    {
    	elementChild=$('.selected').parent();
		elementChild.find('.selected').removeClass('selected');
		$('#teminos').addClass('selected');
    	$("#contentAVCon").load('contBuroEntidades.html #terminos_cond');
    	
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
    	
    	$("#contentAVCon").load('contBuroEntidades.html #caratula');
    	
    }
	
});

function showChangeLeyTrans(dvName){
	$("#contentAVCon").load('contBuroEntidades.html #'+dvName);
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