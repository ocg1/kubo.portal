/* codigo botones de Regreso */
var  actionBotonRegresar;
var idLinkTabla = "";
function regresarResumenSaldos() {
	$("#resumenInvest").trigger("click");
}
function recuperaIdURL () {	   
	   var locacion = document.location.href; 
	   	if(locacion.indexOf('idLinkTabla')>0) {  
	   		    idLinkTabla = GetURLParameterTwo('idLinkTabla');
	   			console.log(idLinkTabla);
	   	}
}
function recuperaIdBotonRegresar() {
	$('.summaryContentInv a').click(function(){	
		idLinkTabla = $(this).attr("id");
		console.log(idLinkTabla);
		changeUrlParam('idLinkTabla', idLinkTabla);
		   
	 });
}
function botonRegresar() {
	 console.log("idLinkTabla"+idLinkTabla);
	 console.log("actionBotonRegresar"+actionBotonRegresar);
	 
	 if ( idLinkTabla != "" && actionBotonRegresar == true) {
		 $("#pnlContentInvest").css("opacity", "0");	
		 $('.summaryContentInv #'+idLinkTabla).trigger("click");	   
					   
	 }else {
		 closeFancy();
		 $("#pnlContentInvest").css("opacity", "1");
	 }
	 recuperaIdBotonRegresar();
	
	 }

function inicioVerProyectos() {
	 console.log("idLinkTabla"+idLinkTabla);
	 if ( idLinkTabla != "" && actionBotonRegresar == true) {
		 
	 }else {
		 displayMessageProcessing('msgprocessing',true);
	 }
}
function llegadaVerProyectos() {
	actionBotonRegresar = false;
	$.fancybox.close();
}
function regresarSabana() {
	   var locacion = document.location.href; 
	if(locacion.indexOf('idLinkTabla')>0 ) {  
		actionBotonRegresar = true;
		console.log(actionBotonRegresar);
		$("#resumenInvest").trigger("click");
	}else {
		actionBotonRegresar = false;
		$("#projectListAction").trigger("click");
	}
}
function romperRegreso() {
	var locacion = document.location.href;
	if(locacion.indexOf('idLinkTabla')>0) {  
		
		idLinkTabla = "";  
		actionBotonRegresar = false;
}
}
