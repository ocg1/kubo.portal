
var contratoMediosTitulo = "Contrato de Servicios Financieros Electrónicos";
var contratoCaptacionTitulo ="Contrato de captación";
var contratoGarantiaTitulo = "Contrato de garantía líquida e irrevocable";
var numeroContratosFirmados = 0;


$(document).ready(function() {	
		
	     console.log(' *******      document.ready FirmaScript      ************');
	
		$(".sectionImprimir").remove(); //ocultar la seccion de imprimir
	
		// iniciar el bloque de firma
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
			var width = $(window).width();
			 if (width >= 701) {
				 $(".bloqueContratoInner").addClass("small");		
			 }
			 	
		});
		//mostrar y ocultar botones de bloque firmar 
		
		canvasFirma (firmaRealizada1, "captacion");
		canvasFirma (firmaRealizada2, "garantia");
	    canvasFirma (firmaRealizada3, "medios");
	    canvasFirma (firmaRealizada4, "acreditado");
	    //recoger html de los pdfs
	    var contratoMedios = $("#htmlContratoMediosContenido").html();	
		$("#inputContratoMedios").val(contratoMedios);
		var tmp = $("#inputContratoMedios").val();
		$("#inputContratoMedios").blur();

        var contratoCaptacion = $("#htmlContratoCaptacionContenido").html();	
		$("#inputContratoCaptacion").val(contratoCaptacion);
		$("#inputContratoCaptacion").blur();
		
		var contratoGarantia = $("#htmlContratoGarantiaContenido").html();	
		$("#inputContratoGarantia").val(contratoGarantia);
		$("#inputContratoGarantia").blur();
		
		var contratoAcreditado = $("#htmlContratoAcreditadoContenido").html();	
		$("#inputContratoAcreditado").val(contratoAcreditado);
		$("#inputContratoAcreditado").blur();
			
		var user_agent = navigator.userAgent;
		$("#browser_data").val( user_agent );
		$("#browser_data").blur(  );
		
		// bloquear checks si no hay  numberAcountInvestor
		setTimeout(function(){
			bloquearCheck ();
		},300);
	
		
		$('#full-width-slider').royalSlider({
		    arrowsNav: true,
		    loop: false,
		    keyboardNavEnabled: true,
		    controlsInside:true,
		    imageScaleMode: 'fill',
		    arrowsNavAutoHide: true,
		    autoScaleSlider: true, 
		    autoScaleSliderWidth: 1051,     
		    autoScaleSliderHeight: 500,
		    controlNavigation: 'bullets',
		    thumbsFitInViewport: false,
		    navigateByClick: true,
		    startSlideId: 0,
		     autoPlay: {
		                // autoplay options go gere
		                enabled: true,
		                pauseOnHover: false,
		                delay: 7000,

		            },
			
		    transitionType:'move',
		    globalCaption: false,
		    deeplinking: {
		      enabled: true,
		      change: false
		    },
		    /* size of all images http://help.dimsemenov.com/kb/royalslider-jquery-plugin-faq/adding-width-and-height-properties-to-images */
		    imgWidth: 1051,
		    imgHeight: 500
		  });
});

//DOCUMENT READY
function ejecutaCmdShowedCont(){
	console.log('ejecuta click cmdShowedCont');
	$("#cmdShowedCont").click();
}

//bloquear checks si no hay  numberAcountInvestor o numberCreditId
function bloquearCheck () {
	  var locacion = document.location.href; 
	  if(locacion.indexOf('numberAcountInvestor')< 0 || locacion.indexOf('numberCreditId')< 0 ) { 
		  $(".bloquearCheck").show();
		  $(".bloquearCheck").css("height", "70");
	  }
}
//bloquear check cuando el pdf ya esta realizdo
function bloquearCheck2 (bloqueoCheck) {
	$("#"+bloqueoCheck+ " .bloquearCheck" ).show();
	$("#"+bloqueoCheck+ " .bloquearCheck" ).css("height", "70");
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


//empezar a firmar contrato con los contratos chequeados
function firmaContratoMobile (){
	
	 if($("#aceptoContratoMedios input").prop('checked') && $("#aceptoContratoCaptacion input").prop('checked') && $("#aceptoContratoGarantia input").prop('checked')){
		 		
		 			$(".bloqueContrato").hide();
		 			$(".firmarContratos.mobile").hide();
		 			$(".regresarBtn.mobile").show();
		 			
		 				$(".firmaAqui.medios").show();
		 			    $.scrollTo($(".firmaAqui.medios").offset().top-50) ;
		 			
			  }else {
					 alertify.labels.ok = "Aceptar";
				  alertify.alert("Para continuar debes aceptar los términos y condiciones de cada contrato.");
				  checksContratos();
			  }
	 
}
//acciones de regreso en la version mobile
function regresarContratosMobile() {
	$(".firmaAqui").hide();
	$(".bloqueContrato").show();
	$(".regresarBtn.mobile").hide();
	$(".firmarContratos.mobile").show();
	 clear1();
}


//para extender el area para visualizar el pdf (zoom)
function contratoVistaCompleta(btn,contrato) {
	var width = $(window).width();	
	var height = $(window).height();
	 $("#"+contrato).closest(".bloqueContrato").addClass("full");
	 $("#"+contrato).removeClass("small");
	
	 $("#"+contrato).css("height", height - $(".bloqueContrato.full aside").height() - $(".bloqueContrato.full h2").height() -20);s
	 //$("#"+contrato).scroll_absolute({arrows:true});
	 $(".bloqueContrato .fa-search-plus").hide();
	 $(".bloqueContrato .fa-times").show();
}
//para cerrar el area para visualizar el pdf (close)
function contratoSmall(btn, contrato) {
	 $("#"+contrato).addClass("small");
	 $("#"+contrato).closest(".bloqueContrato").removeClass("full");
	 $(".bloqueContrato .fa-search-plus").show();
	 $(".bloqueContrato .fa-times").hide();
}

//al empezar a firmar contratos 
// click al dar click en continuar con el formato firmato escritorio y mobile
function continuarFirma(btn, checkBlock, tipoContrato, signature, firmaData, cmdSendDataSignature, recordVar, cacheCanvasVar) {

	var nombreCliente = $("#nombreCliente").val();
    alertify.labels.cancel = "Cancelar";
	alertify.labels.ok = "Aceptar";
	alertify.confirm(nombreCliente+": al aceptar, tu "+ " "+"<strong>"+tipoContrato+"</strong>"+" quedará firmado.", function (e) {
		if (e) {
					// Serialización de la firma
					$('#'+signature).val(recordVar.serialize());
					$('#'+signature).blur();
					
					// Base64 de render en formato png
					$('#'+firmaData).val(cacheCanvasVar.toDataURL());
					$('#'+firmaData).blur();
					$('#'+cmdSendDataSignature).click();
					
					$(btn).parent().parent().hide();
					
					$("#"+checkBlock+" .bloquearCheck").show(); 
					$("#"+checkBlock+" .bloquearCheck").css("height", $("#aceptoContratoMedios").height());
					$(".cargandoNuevo").addClass("show");
					$(".velo").fadeIn();
					
			return true;
		} else { 
			return false;
		}
		
	
	}); 
} 


//REGRESO

//regresar de firmar un elemento
function regresaFirmaMedios( doc_origen ){
	$(".cargandoNuevo").removeClass("show");
	$(".velo").fadeOut();
	var width = $(window).width();	
	 if (width >= 1024) {
		 
		 if( doc_origen == 'medios' ){
			 alertify.labels.ok = "Aceptar";
			 alertify.alert('¡Listo! tenemos tu firma del '+"<strong>"+contratoMediosTitulo+"<strong>"+'.');
		 }
		 else if( doc_origen == 'captacion' ){
			 alertify.labels.ok = "Aceptar";
			 alertify.alert('¡Listo! tenemos tu firma del '+"<strong>"+contratoCaptacionTitulo+"<strong>"+'.');
		 }else if( doc_origen == 'garantia' ){
			 alertify.labels.ok = "Aceptar";
			 alertify.alert('¡Listo! tenemos tu firma del '+"<strong>"+contratoGarantiaTitulo+"<strong>"+'.');
		 }
		 totalContratos();
		 
	 }
	 if (width <= 1024) {
		 if( doc_origen == 'medios' ){
			 firmaRealizadaMobile('medios','captacion', contratoMediosTitulo, contratoCaptacionTitulo);
		 }
		 else if( doc_origen == 'captacion' ){
			 firmaRealizadaMobile('captacion','garantia', contratoCaptacionTitulo, contratoGarantiaTitulo);
		 
		 }else if( doc_origen == 'garantia' ){
			 firmaRealizadaMobile('garantia','mensajeFinal', contratoGarantiaTitulo);
			 $.scrollTo($("#marcador").offset().top-150) ;
		 }
	
	 }
	
}
//regresar de firmar todos los contratos
function regresaFirmaTodos(){
	var width = $(window).width();	
	 if (width >= 1024) {
		 $(".instrucciones").hide();
		 mostrarMensajeFinal();
		
		
		 
	 }else if (width <= 1024) {
		 $(".instrucciones").hide();
		 $(".mensajeFinal").show();
		 $(".firmarContratos").hide();
		 alertify.labels.ok = "Aceptar";
		 alertify.alert("Listo los contratos han sido firmados");
		 $.scrollTo($(".mensajeFinal").offset().top-100) ;
	
	 }
		$(".cargandoNuevo").removeClass("show");
		$(".velo").fadeOut();
}




//Escritorio, preguntar si desea, ocupar la misma firma si hay contratos por firmar,  si no mostrar el mensaje final
function totalContratos() {
				var totalContratos = $(".firmaDigital canvas:visible").length;
				console.log(totalContratos);
				if(totalContratos == 0){
					mostrarMensajeFinal();
					scrollToMensajeFinal ();
				}
				
				if(totalContratos >= 1){
				    alertify.labels.cancel = "No";
					alertify.labels.ok = "Si";
					alertify.confirm("¿Deseas ocupar esta firma para los otros contratos?", function (e) {
						
						if (e) {
							 if($("#aceptoContratoMedios input").prop('checked') && $("#aceptoContratoCaptacion input").prop('checked') && $("#aceptoContratoGarantia input").prop('checked')
							 )
							  {
								 $("#inputSeguimientoContratos").val("");
								 
								 if($("#canvas1").is(":visible")) {
									 //medios
									 $("#inputSeguimientoContratos").val("#medios");
									
								 }
								 if($("#canvas2").is(":visible")) {
									 //captacion
									 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#captacion");
								 }
								 if($("#canvas3").is(":visible")) {
									//garantias 
									 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#garantia");
								 }
								 if($("#canvas4").is(":visible")) {
										//garantias 
										 $("#inputSeguimientoContratos").val($("#inputSeguimientoContratos").val()+"#acreditado");
								 }
								 $(".cargandoNuevo").addClass("show");
									$(".velo").fadeIn();
								 $("#inputSeguimientoContratos").blur();
								$("#cmdAllSigned").click();
								
								 mostrarMensajeFinal();
								 scrollToMensajeFinal ();
							 }else {
								 checksContratos();
								 alertify.labels.ok = "Aceptar";
								 alertify.alert("Para continuar debes aceptar los términos y condiciones de cada contrato.")
							 }	
							 
							 
							return true;
						} else { 
					
							$.scrollTo($("#marcador").offset().top) ;
						
							return false;
						}
					}); 
				}
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
	
//scrollear el mensaje final, dando click en los click, determinar el destino del scroll
function scrollToMensajeFinal () {
	var totalContratos = $(".firmaDigital canvas:visible").length;
	$('.alertify-buttons .alertify-button-ok').on('click',	function() {
		if(totalContratos == 0){
			$.scrollTo($(".mensajeFinal").offset().top-150) ;
			
		}
	});
	
}
//mostrar mensaje final
function mostrarMensajeFinal() {
	    $(".instrucciones").hide();
		$(".mensajeFinal").show();
		$(".firmaAqui").hide();
		
		$(".bloquearCheck").show();
		$(".bloquearCheck").css("height", $("#aceptoContratoMedios").height());
		

		$(".firmarContratos").hide();
}


//secuencia de firma para la versión mobile
function firmaRealizadaMobile(origen, destino, tipoContrato, tipoContratoDestino) {
	console.log(origen);
	$(".regresarBtn.mobile").hide();
	
	if( origen == "medios" ) {
	    alertify.labels.cancel = "No";
		 alertify.labels.ok = "Si";
		$(".firmaAqui."+origen).hide();
		// alertify.confirm("<p>"+"Deseas ocupar la firma del "+"<strong>"+tipoContrato+"</strong>"+" para:" +"</p>"+
		   alertify.confirm("<p>"+"El contrato se ha firmado. Deseas aplicar la misma firma para:" +
				"<ul>"+
					"<li>"+"El "+"<strong>"+contratoCaptacionTitulo+"</strong>"+".</li>"+
					"<li>"+"El "+"<strong>"+contratoGarantiaTitulo+"</strong>"+".</li>"+
				"</ul>"
				
				, function (e) {
					if (e) {
						$(".cargandoNuevo").addClass("show");
						$(".velo").fadeIn();
						$("#inputSeguimientoContratos").val("#captacion#garantia");
						$("#inputSeguimientoContratos").blur();
						$("#cmdAllSigned").click();
						//alertify.alert("Listo los contratos han sido firmados");
						//$(".mensajeFinal").show();
						$.scrollTo($("#marcador").offset().top-100) ;
						return true;
					} else { 
						$(".firmaAqui."+destino).show();
						$.scrollTo($(".firmaAqui."+destino).offset().top-100) ;
						return false;
					}
		});
	}
	if( origen == "captacion" ) {
	     alertify.labels.cancel = "No";
		 alertify.labels.ok = "Si";
		$(".firmaAqui."+origen).hide();
		alertify.confirm("<p>"+"Deseas ocupar la firma del "+"<strong>"+tipoContrato+"</strong>"+" para:" +"</p>"+
			
				"<ul>"+
				"<li>"+"El "+"<strong>"+contratoGarantiaTitulo+"</strong>"+"</li>"+
				"</ul>"
				, function (e) {
					if (e) {
						$(".cargandoNuevo").addClass("show");
						$(".velo").fadeIn();
						$("#inputSeguimientoContratos").val("#garantia");
						$("#inputSeguimientoContratos").blur();
						$("#cmdAllSigned").click();
//						alertify.alert("Listo los contratos han sido firmados.");
//						$(".mensajeFinal").show();
						$.scrollTo($("#marcador").offset().top-100) ;
						return true;
					} else { 
						$(".firmaAqui."+destino).show();
						$.scrollTo($(".firmaAqui."+destino).offset().top-100) ;
						return false;
					}
		});
	}
	if( origen == "garantia" ) {
		$(".mensajeFinal").show();
		$(".instrucciones").hide();
		$.scrollTo($("#marcador").offset().top-150) ;
	}
	
	
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
		$(".mensajeFinal").hide();
		$.scrollTo($("#marcador").offset().top-150) ;
		},100);
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