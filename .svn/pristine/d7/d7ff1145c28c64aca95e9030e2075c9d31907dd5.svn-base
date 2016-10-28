function initModal(){
	
	$.fancybox({
		'width' : 320,
		'padding' : '0',
		'margin' : '0',
		'autoDimensions':false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'speedIn' : '20',
		'speedOut' : '10',
		'modal' : true,
		'type' : 'inline',
		'scrolling' : 'no',
		'overlayColor': '#333333',
		'centerOnScroll' : true,
		'href': '#dvLoggin',
		'onComplete' : function(){
			 $('#fancybox-content').height('auto');
			 $('#fancybox-content').children().eq(0).css('height','auto');
	    }
		
	});
	
	return true;
	
}

function initDiferentUser(){
	
	$("#contDvMsgDiferentUser").fadeOut("slow",function(){
		$("#contDvAccess").fadeIn("slow");
	});
	
	return true;
}


function createCustomHTMLDiagCntnt(status,total){
	
	var res = "";
	
	if(total != '-'){
		
		res = "<b>"+status+"</b><br /><br /><b>Total: "+total+"</b>";
		
	}else{
		res = "<b>"+status+"</b>";
	}
	
	return res;
	
}

function customHTMLDiagCnsltCnt(fecConsul){
	
	return "<div style='padding: 8px;' ><div style='width: 99%; margin-left: auto; margin-right: auto; text-align: center;'>Fecha de Consulta</div><br />KUBOFINANCIERO: <b>"+fecConsul+"</b></div>";
	
}


//function initDiferentUser_show_access(){
//	
//	$("#contProcessing").fadeOut("slow",function(){
//		
//		$("#contDvAccess").fadeIn("slow");
//		
//	});
//	
//	return true;
//}
