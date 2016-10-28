// se crea un título por cada contrato
var contratoMediosTitulo = "Contrato de Servicios Financieros Electrónicos";
var contratoCaptacionTitulo ="Contrato de captación";
var contratoGarantiaTitulo = "Contrato de garantía líquida e irrevocable";
var contratoAcreditadoTitulo  = "Contrato de crédito";
var numeroContratosFirmados = 0;
var numeroChecksChecked = 0;
var numberCreditId = false;
var numberAcountInvestor = false;
var contratoMedios;
var contratoAcreditado;
var contratoCaptacion;
var contratoGarantia;
var width = $(window).width();
var documentosEnviados = false;
var actual;
var contratosFirmar;
var locacion = document.location.href; 
$(document).ready(function() {	
		
	     console.log(' *******      document.ready FirmaScript      ************');
	
		$(".sectionImprimir").remove(); //ocultar la seccion de imprimir
	
		// iniciar el bloque de firma se crea un script para cada contrato
		if ($("#canvas1").length){
			initF1 ('canvas1');
		}
		if ($("#canvas2").length){
			init2('canvas2');
		}
		if ($("#canvas3").length){
			init3('canvas3');
		}
		if ($("#canvas4").length){
			init4('canvas4');
		}

		$(window).resize(function() {
			 if (width >= 701) {
				 $(".bloqueContratoInner").addClass("small");		
			 } 	
		});
		//mostrar y ocultar botones de bloque firmar, viene en elscript de cada contrato  
		
		canvasFirma (firmaRealizada1, "captacion");
		canvasFirma (firmaRealizada2, "garantia");
	    canvasFirma (firmaRealizada3, "medios");
	    canvasFirma (firmaRealizada4, "acreditado");
	    
	  
		
	
		
		setTimeout(function(){
			  //recoger html de los pdfs, se crea uno por cada contrato
		    var contratoMedios = $("#htmlContratoMediosContenido").html();	
			$("#inputContratoMedios").val(contratoMedios);
			var tmp = $("#inputContratoMedios").val();
		

	        var contratoCaptacion = $("#htmlContratoCaptacionContenido").html();	
			$("#inputContratoCaptacion").val(contratoCaptacion);
		
			
			var contratoGarantia = $("#htmlContratoGarantiaContenido").html();	
			$("#inputContratoGarantia").val(contratoGarantia);
			
			
			var contratoAcreditado = $("#htmlContratoAcreditadoContenido").html();	
			$("#inputContratoAcreditado").val(contratoAcreditado);
			$("#inputContratoMedios").blur();
			$("#inputContratoCaptacion").blur();
			$("#inputContratoGarantia").blur();
			$("#inputContratoAcreditado").blur();
			
			
		},600);
	
		var user_agent = navigator.userAgent;
		$("#browser_data").val( user_agent );
		$("#browser_data").blur(  );

		setTimeout(function(){
			// bloquear checks si no hay  numberAcountInvestor
			bloquearCheck ();
			//investor o acreditado
			invOacre();
			//status firma
			statusFirmado (); //no se ocupa
			//png creado
			pngCreado();
			sliderFirmas ();
			// bloquear o desbloquear area de firmar dependiendo del check
			 checkTerminos('#aceptoContratoMedios', 'medios');
			 checkTerminos('#aceptoContratoAcreditado', 'acreditado');
			 checkTerminos('#aceptoContratoCaptacion', 'captacion');
			 checkTerminos('#aceptoContratoGarantia', 'garantia');
			 noHayBloquesFirma();
			 
		},300);
	
		
	
		setTimeout(function(){
			jsgeneraCopia();
		},3000);
		checkBrowser();
		
});


function checkBrowser()
{
	var nua = navigator.userAgent;
	if((nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 &&     nua.indexOf('AppleWebKit') > -1) && !(nua.indexOf('Chrome') > -1)){
		alertify.alert("Para realizar este procedimiento, es necesario que utilices: Mozilla Firefox, Google Chrome o Safari. Si cuentas con alguno de los navegadores mencionados, copia y pega el enlace en la correspondiente barra de direcciones.");

	}else {
	
	}
}



//investor o acreditado
function invOacre() {
	
	  if(locacion.indexOf('numberAcountInvestor') > 0) { 
		  console.log("numberAcountInvestor");
		  $("#pnlConAcreditado").hide();
		  $(".firmaAqui.acreditado").hide();
		  numberAcountInvestor = true;
		  contratosFirmar = "<ul>"+
	  		"<li>"+ contratoMediosTitulo  + "</li>"+
	  		"<li>"+ contratoCaptacionTitulo  + "</li>"+
	  		"<li>"+ contratoGarantiaTitulo  + "</li>"+
	  	 "</ul>"	;
	  } 
	  
	  if(locacion.indexOf('numberCreditId') > 0 ) { 
		  console.log("numberCreditId");
		  if (width >= 1024) {
			  $("#pnlConAcreditado").show();
			  $(".firmaAqui.acreditado").show();
			
		  }
		  contratosFirmar = "<ul>"+
	  		"<li>"+ contratoMediosTitulo  + "</li>"+
	  		"<li>"+ contratoAcreditadoTitulo  + "</li>"+
	  		"<li>"+ contratoCaptacionTitulo  + "</li>"+
	  		"<li>"+ contratoGarantiaTitulo  + "</li>"+
	  	 "</ul>"	;
		  numberCreditId = true;
	  }
}
//status de firmado
function statusFirmado () {
	console.log("contratoMedios="+contratoMedios);
	console.log("contratoAcreditado="+contratoAcreditado);
	console.log("contratoCaptacion="+contratoCaptacion);
	console.log("contratoGarantia="+contratoGarantia);
}


function pngCreado() {
	 $(".bloqueContrato").each(function() {
		   var secondClass= $(this).attr('class').split(' ')[1];
			 if($(this).find(".png").is(":visible")){
				 $(".firmaAqui."+secondClass).hide();
			 }
	 });
	
}
//DOCUMENT READY
function ejecutaCmdShowedCont(){
	console.log('ejecuta click cmdShowedCont');
	$("#cmdShowedCont").click();
}

//bloquear checks si no hay  numberAcountInvestor o numberCreditId
function bloquearCheck () {

	  if(locacion.indexOf('numberAcountInvestor')< 0  && locacion.indexOf('numberCreditId')< 0 ) { 
		  $(".bloquearCheck").show();
		  $(".bloquearCheck").css("height", "70");
	  }
}
//bloquear check cuando el pdf ya esta realizdo
function bloquearCheck2 (check) {
	$("#"+check+ " .bloquearCheck" ).show();
	$("#"+check+ " .bloquearCheck" ).css("height", "70");
}


function sliderFirmas () {
	 
	if($(".png").is(":visible")) {
		  
			 $(".sliderFirmas").addClass("show");
			 $(".velo").fadeIn();
			 if (width <= 700) {
				 setTimeout(function(){
					
					 
					  $('html, body').animate({
					       scrollTop: ($('.instrucciones').offset().top - 100)
					   },500);
				 }, 1500);
					
			  }
		  
		 console.log("si hay png");
	}else {
		 console.log("no hay png");
	}
}
function firmarNuevamente() {
	 $(".sliderFirmas").removeClass("show");
	 $(".velo").fadeOut(); 
}

//No hay bloques firma
function noHayBloquesFirma() {
	console.log("llego aquí");
	 if(!$(".firmaAqui").length ) {
		 if(documentosEnviados== true){
			 mostrarMensajeFinal()
			 $(".btnGeneraContratos").hide();
		 }else {
			 $(".mensajeFinal").show();
			 setTimeout(function(){
				 $.scrollTo($("#marcador").offset().top-100) ;	
			 }, 300);
		 }
	 }
}
// INICIO PROCEDIMIENTO
//al dar click en un bloque de firma sin aceptar términos y condiciones
function noAceptoTerminos(tipoContrato) {
	 alertify.labels.ok = "Aceptar";
	 alertify.alert("Acepta los términos y condiciones del "+"<strong>"+tipoContrato+"</strong> "+ "para poder firmarlo.");	
	
}
//Hacer checks en los contratos
function checkTerminos(btn, cajafirma)  {
	 var id = "#"+$(btn).attr("id");  
	 var tablaCheck = $(btn).find("table");
	 
	 if($(id+" "+"input").prop('checked')) {
		console.log($(id+" "+"input"));
		$(id+" "+".ui-chkbox-box").removeClass("vacio");
		$(".firmaAqui."+cajafirma+ " .bloquearFirma").hide();
		$(tablaCheck).removeClass("vacio");
		setTimeout(function(){ 
			$(btn).closest(".bloqueContrato").find("h2 .fa-times").trigger("click");
		 },200);
	  }else {
		  console.log($(id+" "+"input"));
		$(id+" "+".ui-chkbox-box").addClass("vacio");
		$(".firmaAqui."+cajafirma+ " .bloquearFirma").show();
		$(".firmaAqui."+cajafirma+ " .btnGris").click();
		$(tablaCheck).addClass("vacio");
	  } 
}

//mostrar botones, del bloque de firma
function canvasFirma (contratoFirmado, tipoContrato) {
		if(contratoFirmado == true) {
			$(".firmaAqui."+tipoContrato +" fieldset").show();
		}else {
			$(".firmaAqui."+tipoContrato +" fieldset").hide();
		}
}

//accion para regresar a vista contrato en la version mobile
function regresarContratosMobile() {
	$(".firmaAqui").hide();
	$(".bloqueContrato").show();
	$(".regresarBtn.mobile").hide();
	$(".firmarContratos.mobile").show();
	 clear1();
	 clear2();
	 clear3();
	 clear4();
}
//para extender el area para visualizar el pdf (zoom)
function contratoVistaCompleta(btn,contrato) {
	var height = $(window).height();
	 $("#"+contrato).closest(".bloqueContrato").addClass("full");
	 $("#"+contrato).removeClass("small");
	 $("#"+contrato).css("height", height - $(".bloqueContrato.full aside").height() - $(".bloqueContrato.full h2").height() - $(".btnDecargarVer").height() -20);
	 //$("#"+contrato).scroll_absolute({arrows:true});
	 $(".bloqueContrato .fullCon").hide();
	 $(".bloqueContrato .smallCon").show();
}
//para cerrar el area para visualizar el pdf (close)
function contratoSmall(btn, contrato) {
	 $("#"+contrato).addClass("small");
	 $("#"+contrato).closest(".bloqueContrato").removeClass("full");
	 $(".bloqueContrato .fullCon").show();
	 $(".bloqueContrato .smallCon").hide();
}

//al empezar a firmar contratos 

//empezar a firmar contrato con los contratos chequeados mobile
function firmaContratoMobile (){
	var numeroChecks =  $(".bloqueContrato:visible .aceptoContrato input").length;
	 numeroChecksChecked = 0;
     $(".bloqueContrato:visible .aceptoContrato input").each(function(dx) {
  
		console.log("numeroChecks"+numeroChecks);
		// console.log("numeroChecksChecked"+numeroChecksChecked);
		
		if($(this).prop('checked')){
			numeroChecksChecked = numeroChecksChecked+1;
			console.log("numeroChecksChecked"+numeroChecksChecked)
		}
		if(numeroChecks == dx+1){
			firmarContratoMobile2(numeroChecks);
		}
	});
	 
}
function firmarContratoMobile2 (numeroChecks){
	if(numeroChecks == numeroChecksChecked){
			
			$(".firmarContratos.mobile").hide();
			$(".regresarBtn.mobile").show();
			 
			firmaSiguiente ();
	}else {
		  alertify.labels.ok = "Aceptar";
		  alertify.alert("Para continuar debes aceptar los términos y condiciones de cada contrato.");
		  checksContratos();
	}

}
function continuarFirma(btn, checkBlock, tipoContrato, signature, firmaData, cmdSendDataSignature, recordVar, cacheCanvasVar) {

	var nombreCliente = $("#nombreCliente").val();
    alertify.labels.cancel = "Cancelar";
	alertify.labels.ok = "Aceptar";
	/*
	alertify.confirm(nombreCliente+": al aceptar, tu "+ " "+"<strong>"+tipoContrato+"</strong>"+" quedará firmado.", function (e) {
		if (e) {
		*/
					// Serialización de la firma
					$('#'+signature).val(recordVar.serialize());					
					$('#'+signature).blur();
					$('#'+firmaData).val(cacheCanvasVar.toDataURL());
					$('#'+firmaData).blur();
					$('#'+cmdSendDataSignature).click();
					$(btn).parent().parent().hide();
					$("#"+checkBlock+" .bloquearCheck").show(); 
					$("#"+checkBlock+" .bloquearCheck").css("height", $("#aceptoContratoMedios").height());
					$(".cargandoNuevo").addClass("show");
					$(".velo").fadeIn();
		/*			
			return true;
		} else { 
			return false;
		}
	}); 
	
	*/
} 
//REGRESO

//regresar de firmar un elemento
function regresaFirma( doc_origen, titulo ){
	$(".cargandoNuevo").removeClass("show");
	$(".velo").fadeOut();	

	 
	    var totalContratos = $(".firmaDigital canvas").length;
		var numeroDeContratos = $(".bloqueContrato").length;
		
		if(totalContratos ==  numeroDeContratos-1 && totalContratos > 1 ){
			
			console.log("totalContratos "+totalContratos+"numeroDeContratos "+numeroDeContratos );
			
			ocuparMismaFirma (); 
			
		}else{
			 alertify.labels.ok = "Aceptar";
			 alertify.alert('¡Listo! tenemos tu firma del '+"<strong>"+titulo+"<strong>"+'.');
			 if (width >= 1024) {
			   
				noHayBloquesFirma();
				
				
			 }
			 if (width <= 1024) {
					firmaRealizadaMobile(doc_origen, titulo);
			 }
			
		}
}




//secuencia de firma para la versión mobile
function firmaRealizadaMobile(doc_origen, titulo) {
	
	$(".firmaAqui."+doc_origen).hide()
		firmaSiguiente ();
		
}


function ocuparMismaFirma () {

    alertify.labels.cancel = "No";
	alertify.labels.ok = "Si";
 
	
	
		  alertify.confirm("<p>"+"Deseas aplicar la misma firma para los siguientes contratos:"+"</p>"+contratosFirmar		
				
			, function (e) {
				if (e) {
					ocuparFirmaPrevia ();
					return true;
				} else { 
					firmaSiguiente ();
					return false;
				}
		  });
	  
}

function ocuparFirmaPrevia () {
	 
	 if(width>=1024){
		 if($(".bloqueContrato:visible .aceptoContrato input").length == $(".bloqueContrato:visible .aceptoContrato input:checked").length ){
			 status ();
			
		 } else {
			 checksContratos();
			 alertify.labels.ok = "Aceptar";
			 alertify.alert("Para continuar debes aceptar los términos y condiciones de cada contrato.")
		 }	
	}
	if(width<=1024){	
		 status ();
			
	}
}
function status () {
	if($(".sliderFirmas").hasClass("show")) {
		$(".parrafoSlider").hide();
		$(".loaderFirmaSeleccionada").addClass("show");
		$(".sliderContainer").slideUp();
		
		 $(".rsTmb").each(function() {
			if($(this).closest(".rsThumb").hasClass("rsNavSelected")) {
			
				actual = $(this).attr("alt");
				$("#statusInput").val(actual);
				console.log("actual"+actual);
				$("#statusInput").blur();
				firmarlosTodos ();
			}
		});
		
	}else {
		firmarlosTodos ();
	}
	
}
function firmaSiguiente () {
	
	  if($(".firmaAqui").length ) {
		  $(".firmaAqui:eq(0)").show();
			 setTimeout(function(){
				  $.scrollTo($(".firmaAqui:eq(0)").offset().top-50) ;
			 }, 300);
		
	  }else {
		
		  mostrarMensajeFinal();
	  }
}

function firmarlosTodos (){
	$(".cargandoNuevo").addClass("show");
	$(".velo").fadeIn();
	 $("#inputSeguimientoContratos").val("");
	
	 if(!$(".canvas").length ){
		 if($(".png.medios").is(":visible")) {
			 $("#inputSeguimientoContratos").val("#medios");
		 }
		
		 if($(".png.captacion").is(":visible")) {
			 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#captacion");
		 }
		 if($(".png.garantia").is(":visible")) {
			 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#garantia");
		 }
		 if($(".png.credito").is(":visible")) {
			 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#acreditado");
		 }
		 
	 }else {
		 if($("#canvas1").length) {
			 //medios
			 $("#inputSeguimientoContratos").val("#medios");
			
		 }
		 if($("#canvas2").length) {
			 //captacion
			 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#captacion");
		 }
		 if($("#canvas3").length) {
			//garantias 
			 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#garantia");
		 }
		 if($("#canvas4").length) {
				//garantias 
				 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#acreditado");
		 }
	 }
	
	 
	$("#inputSeguimientoContratos").blur();
	$("#cmdAllSigned").click();
	$.scrollTo($("#marcador").offset().top-100) ;
}
//pintar check vacios
function checksContratos() {
	 $(".bloqueContrato aside input").each(function() {
		 if($(this).prop('checked')) {
			 $(this).parent().parent().find(".ui-chkbox-box").removeClass("vacio");
			 $(this).closest(".aceptoContrato").removeClass("vacio");
		 }else {
			 $(this).parent().parent().find(".ui-chkbox-box").addClass("vacio");
			 $(this).closest(".aceptoContrato").addClass("vacio");
		 }
  
  });

}	
function regresaFirmaTodos() {
	mostrarMensajeFinal();
}

//mostrar mensaje final
function mostrarMensajeFinal() {
	 $(".sliderFirmas").removeClass("show");
	 $(".velo").fadeOut(); 
	 $(".cargandoNuevo").removeClass("show");
	 $(".velo").fadeOut();
	 $(".firmaAqui").hide();
	 $(".instrucciones").hide();
	 $(".firmarContratos").hide();
	 $(".mensajeFinal").show();
	 if(!$(".png").is(":visible")) {
		 $(".bloquearCheck").show();
		 $(".bloquearCheck").css("height", $("#aceptoContratoMedios").height());
	 }
	 setTimeout(function(){
		 $.scrollTo($("#marcador").offset().top-100) ;	
	 }, 300);
	
	 alertify.labels.ok = "Aceptar";
	 alertify.alert("Listo los contratos han sido firmados");
	
	
}
function generaContratos (){
	$(".cargandoNuevo").addClass("show");
	$(".velo").fadeIn(function(){
		$("#cmdGenerateContract").trigger("click");
	});
}
//ocultar el link del pdf que esta "lleno"
function pdfNoGenerado(link) {
	setTimeout(function(){
		$("."+link).hide();
	},100);
}
//cuando se ha realizado un pdf
function pdfHecho (check, linkVacio, LinkLleno, contrato, aceptoCheck, zoom ) {
	setTimeout(function(){
			bloquearCheck2 (check); 
			$("."+linkVacio).hide();
			$("."+LinkLleno).show();
			$("#"+contrato).hide();
			$("#"+aceptoCheck).hide();
			$(zoom).css("marginRight", "-100px");
			
		},100);
		
}
//cuando se tienen todos los pdfs
function todoFirmado () {
	setTimeout(function(){
		$(".instrucciones").hide();
		$(".mensajeFinal").show();
		$.scrollTo($("#marcador").offset().top-150) ;
		},300);
}
//enviar a validacion 
function enviarValidacion() {
	$(".cargandoNuevo").removeClass("show");
	$(".velo").fadeOut(function(){
    alertify.labels.ok = "Aceptar";
	alertify.alert("Se han mandado los contratos, para el proceso de validación.");
    $(".mensajeFinal article .callAction").hide();
		
	});
}

//elimminar firma
function eliminarFirma(firma, contrato) {
	$(".png."+firma).hide();
	 if (width >= 1024) {
		 $(".firmaAqui."+firma).show();
	 }

}

function jsgeneraCopia(){
	
	$("#cmdSendCopyContrct").trigger("click");
	
}

function redirectCopy( xhr, status, args){
	
	if( args.url_copy != null ){
		
		//window.open(args.url_copy, '_blank');
		console.log( "redirect: " + args.url_copy);
		$("#redirectCopy").attr("href", "../"+args.url_copy);
	
				
		 
	
	}
	
}
















/*anterior code */









