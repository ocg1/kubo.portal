
/*if(navigator.userAgent.indexOf("Chrome") != -1 || (navigator.userAgent.indexOf("MSIE") != -1 ) || (!!document.documentMode == true ))
{*/
	if($("#enabledAnalytics").val() == 'S' && $("#area").val() == "L") {
		var interactions = new Interactoa({
			interactions : true,
			interactionElement : "contenidoForm",
			interactionEvents : [  'click', 'keypress'],
			endpoint : '/Kubo/SrvInteractor',
			async : false
		});
	}
	
	
	
	
 /* }*/
