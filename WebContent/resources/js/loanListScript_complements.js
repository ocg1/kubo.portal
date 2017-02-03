var showMovil = false;
var width = $(window).width();	
var isSafari = !!navigator.userAgent.match(/Version\/[\d\.]+.*Safari/);
var userAgent = window.navigator.userAgent;

function  distribuir_quitar() {
	closeFancy();
	filtros ();

}

var totalAltura;
var alturaWindow;
var alturaElemento;
var positionActual;
function n_boton_distribuir_quitar() {
	if( $("#distribuir").is(":visible")) {
		$(".distribuir_quitar span").html("Distribuir");
	}
	if( $("#quitar").is(":visible")) {
		$(".distribuir_quitar span").html("Quitar");
	}	
	

	var width = $(window).width();	
	proyectosMovil ();
	$(window).resize(function() {
		var resizeId;
		   clearTimeout(resizeId);
		   resizeId = setTimeout(proyectosMovil, 300);   
	
	});
	
	 totalFondeado ();
	 numeracionTienda (); 
	 
}

function distribuirQuitar () {	
	$('#fixed .callActionMedium.full').click();
}


function invertir_function() {

	if(!$(".resumen_saldos_cuenta").is(":visible")){
		cerrarNotificacion();
		console.log("cerrar notification");
	}
	if($("#sidebar").length ){
		$(".tituloInv h2").html("Invertir");
	}
	closeFancy();
    var width = $(window).width();	
    var locacion = document.location.href; 
	filtros ();
	
	fondeo_previo ();
	n_boton_distribuir_quitar();
	proyectosMovil ();
		$(window).resize(function() {
			var resizeId;
			   clearTimeout(resizeId);
			   resizeId = setTimeout(proyectosMovil, 500);
			   
		});
	
	
		 $(".invertirOtroBtn").hide();
		 $(".saldosBtn").show();
		 romperRegreso();
		 
		 totalFondeado ();
		 numeracionTienda (); 
		 actionBotonRegresar = false;
		 if(locacion.indexOf('idLinkTabla')>0 ) { 
		   ChangeUrl('investments', 'investments.xhtml');
		 }

			 	
				if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i)) {
					  if(isSafari ){
						 setTimeout(function(){
							 $("#dvContInvFlot").addClass("iphone");
						 }, 300);
					  }
				}	
				mobileIphone ();
	
		
	
			
}
function invertir_function2() {
	closeFancy();
	var  ipad_format;
    var width = $(window).width();	
	filtros ();
	fondeo_previo ();
	setTimeout(function(){
		$("#recargar_proyectos").trigger("click");
	},300);
	n_boton_distribuir_quitar();
	 totalFondeado ();
	 numeracionTienda (); 
}

$.fn.digits = function(){ 
    return this.each(function(){ 
        $(this).text( $(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") ); 
    })
}

function disponible (){
	var monto_invertir = $("#ammountToInvFlot").val();
	var monto_invertir = Number(monto_invertir.replace(/[^0-9\.]+/g,""));	
	var monto_seleccionado = $("#valAmmountInvFlot").text();
	var monto_seleccionado = Number(monto_seleccionado.replace(/[^0-9\.]+/g,""));
	var disponible_val = monto_invertir - monto_seleccionado
	if (disponible_val  > 0) {
		 $(".disponible_val").html("$" + (monto_invertir-monto_seleccionado).toFixed(2));
		 $(".disponible_val").digits();
	} else if (disponible_val  < 0) {
		 $(".disponible_val").html("- $" + (monto_invertir-monto_seleccionado).toFixed(2).slice(1));
		 $(".disponible_val").addClass("red");
		 $(".disponible_val").digits();
	}
	 else if (disponible_val  == 0)  {
		 $(".disponible_val").html("$ 0.00")
	
	}

}
function cerrar_filtros() {
	if(!$(".dispersion").hasClass("show")){
		$(".velo").fadeOut();
		$(".tienda").removeClass("active");
		$(".alerts").removeClass("show");
	}
}

function filtroAbrir(tab) {
	 var width = $(window).width();	
	 if (width <= 1100) { 
	  if($(tab).hasClass("active")) {
		   $(tab).next("div").slideUp();
		   $(tab).removeClass("active");
		} 
		else {
		   $('.tienda .toggle_container').slideUp();
		   $('.tienda .expand_heading').removeClass("active");
		   $(tab).next("div").slideDown();
	       $(tab).addClass("active");
		  
		}  
	 }
}

function  abrir_panel_filtros (){
	 $("#sidebar").addClass("active");
	 $(".velo").fadeIn();
	  $('html, body').animate({
	       scrollTop: ($('#sidebar').offset().top-50)
	  },500);
} 

function filtros (){
	$(".scores").html("");
	$("#sidebar #filter_by_type .ui-checkbox-filter").each(function() {
	    tipo_score = $(this).attr("id").replace("chk", "");
		if($(this).is(":checked")) { 
			$(".scores").append(tipo_score+", ");
		}else {			
		}
	});
	if ($(".scores").is(':empty')){
		$(".scores").html("no definido");
	} 
	$("#sidebar #filter_by_funding :radio").each(function() {
		filtro_por_fondeo = $(this).next("label").text();
		if($(this).is(":checked")) { 
			$(".resultados .fondeo").html(filtro_por_fondeo);
		}else {
		
		}
	});
	 if ($("#gender_Hombre").hasClass("itemCheck")) {
		 $(".filtro_genero").html("Hombres");
	}
     if ($("#gender_Mujer").hasClass("itemCheck")) {
    	 $(".filtro_genero").html("Mujeres");
	}
     if ( $("#gender_Hombre").hasClass("itemCheck") &&  $("#gender_Mujer").hasClass("itemCheck") ) {
    	  $(".filtro_genero").html("Hombres y Mujeres");
	}
	var desde = $("#sidebar #inputFrom").val();
	var hasta = $("#sidebar #inputTo").val();
	 $(".no_definido").hide();
	$(".resultados .desde").html(desde);
	$(".resultados .hasta").html(hasta);
	if ($("#sidebar").is(":visible")) {
		 if ( ($("#inputFrom").val().length==0) &&  ($("#inputTo").val().length==0 )) {
			  $(".d").hide();
			  $(".h").hide();
			  $(".no_definido").show();
		 }else {
			 $(".d").show();
			 $(".h").show();
		 }
	}
		var plazo_meses;
		 if ($(".plazo_1").hasClass("itemCheck")) {
			plazo_meses = "6";
		}
		 if ($(".plazo_2").hasClass("itemCheck")) {
			plazo_meses = "9";
		}
		 if ($(".plazo_3").hasClass("itemCheck")) {
			plazo_meses = "12";
		}
		 if ($(".plazo_4").hasClass("itemCheck")) {
			plazo_meses = "15";
		}
		 if ($(".plazo_5").hasClass("itemCheck")) {
			plazo_meses = "36";
		}
		 $(".resultados .plazo").html(plazo_meses);
		 $(".proyectos_disponibles").html($("#pnlNumCredito span").text());	

		 fondeo_previo ();
		 n_boton_distribuir_quitar();
		 if($(".tienda").hasClass("active")) {
			 $("#sidebar").removeClass("active");
			 $(".velo").fadeOut();
		 }
		 revisarSeleccionados ();
		 
		 if($(".tablaTienda").is(":visible") &&  scrollea == true){
			 	scrollea = false;
				 $('html, body').animate({
				       scrollTop: ($('#encabezadoTablaTienda').offset().top - 100)
				 },1400);
			 
		 }
		
}

function aceptar_delayFuncInv () {
	cerrar_fondeados();
	delayFuncInv();
}
function cerrar_fondeados () {
	$(".velo").fadeOut();
	$(".velo2").fadeOut();
	$(".alerts").removeClass("show");
	$(".alertsComprobantes").removeClass("show");
	if($('.filaSeleccionada').length){
		$('html, body').animate({
		       scrollTop: ($('.filaSeleccionada').offset().top - 100)
		},1400);	
	}
	
}
function fondeo_previo () {
   	$(".numero_inversiones").each(function() {
   			var numero_inversiones = $(this).find("p").length;
   			$(this).find("h6 span").html(numero_inversiones);
   			if(numero_inversiones == 0) {
   				$(this).hide();
   				$(this).next(".monto_inversiones").hide();
   			}
   			if(numero_inversiones != 0) {
   				$(this).show();
   				$(this).next(".monto_inversiones").show();
   			}
   			if(numero_inversiones == 1) {
   				$(this).find("h6 small").html("inversion");
   			}
   		     else if (numero_inversiones > 1) {
   				$(this).find("h6 small").html("inversiones");
   			}
 	});

}

function  cerrar_monto_cero() {

	
}
function validacion_fondeadoPrevio() {
	displayMessageProcessing('msgprocessing', false);
	var conteo_elementos = $("ul.filaTienda li .inversion_input ").length;
	var i = 0;
	$("ul.filaTienda li .inversion_input ").each(function() {	
		i += 1;
		if($(this).is(":visible") && $(this).val() == 0) {

			$(this).closest("li").find("#dvContentLoanLst input[type='checkbox']").click();
		
		}else if($(this).is(":visible") && !$(this).hasClass(".evaluado") )  {
			
//			$(this).addClass(".evaluado");
//			$(this).focus();
//			$(this).blur();
			
		}
		
		if(!$(this).is(":visible") && $(this).closest("li").find("#dvContentLoanLst input[type='checkbox']").is(":checked")){
		
			$(this).closest("li").find("#dvContentLoanLst input[type='checkbox']").prop('checked', false); 
		
		}
		
		if(i == conteo_elementos) {
		   	
			validacion_fondeadoPrevio2();
		 }
	});
	
	

}



function validacion_fondeadoPrevio2() {

var inversion_input = $('.inversion_input');
$(".listado_yafondeados li").remove();
var num_proyectos = 0;
var inputs_vacios = inversion_input.filter(function () {
    return $(this).is(':visible') ;
    // return $.trim(this.value) != '' regresa visibles pero vacios;	    
}).length == 0;
if(inputs_vacios == true) {
	$(".velo").fadeIn();
	$(".sin_seleccionar").addClass("show");
}else {
	$(".listado_yafondeados li").remove();
	 var fondeado_previo = false; 
	$(".numero_inversiones").each(function(idx) {
		var numero_inversiones = $(this).find("p").length;
		var proyectoName = $(this).parent().parent().parent().find("#proyectNameTag").text();
	
		if( numero_inversiones > 0 && $(this).parent().parent().parent().find(".inversion_input").val() > 0 ) {
			if($(this).parent().parent().parent().find(".inversion_input").is(":visible")) {
				$(".listado_yafondeados").append("<li>"+idx+".-"+ proyectoName+"</li>");
		
				fondeado_previo = true; 
				num_proyectos = num_proyectos + 1;
					
			}
		}else {
		}	
	});
	if (fondeado_previo == true ) {
		var numeroProyectos = $(".listado_yafondeados li").length;			
		$(".velo").fadeIn();
		$(".fondeados").addClass("show");
		$(".numero_pro").html(num_proyectos);
		if(num_proyectos == 1) {
			$(".aviso_proyectos small").html("proyecto");
			$(".aviso_proyectos sub").html("el");
		}
		if (num_proyectos >= 2) {
		    $(".aviso_proyectos small").html("proyectos");
			$(".aviso_proyectos sub").html("los");
		}
	}
	else {
		delayFuncInv();	
	}
}	

}

/*verificacion score */
var verificationScore = function (elemt, scoreV) {
	var tooltip = $(elemt).closest("#verificationTestDiv").find(".nivel_verificacion");
	
	$(".nivel_verificacion").fadeOut();
	if(scoreV > 0 ) {
			tooltip.fadeIn();
			tooltip.find("p").each(function(idx) {
			if(idx   <  scoreV) {
				$(this).show();
			}
		});
	}
}
var closeVerificationScore = function () {
		 $(".nivel_verificacion").fadeOut();
}

function par () {
	$(".formato_tablas > tbody> tr:even").addClass("par");
}
function  proyectosMovil () {	
	var numberFila;
	
	 var width = $(window).width();
	 if (width >= 1101) { 
		 $(".saldo_cuenta").prependTo('#sidebar');		
	 }
	 if (width <= 1100) { 
		 $(".saldo_cuenta").insertBefore('.individual_paquete');				 
	 }
	 if (width >= 821) { 
			/*
		$("ul.filaTienda > li").css({
			height: ""
		});
	
		$("#creditos ul.filaTienda").each(function(idx) {
			var maxHeight = Math.max.apply(null, $(this).find("li").map(function ()	{
				return $(this).height();
			}).get());
			$(this).find("li").css({
						   height: maxHeight
			});  
		});  
	

	$("ul.filaTienda:odd").addClass("par");
	 */
		 if(!$("#pnlMsgSugMin .max_recomendada").length){
			$("#copyRecomendacion .max_recomendada").insertAfter($(".datoQueTuPerfil"));
			$(".maxRecMovil").removeClass("show");
			$(".velo2").fadeOut();
		 }	
			/*	
		
		*/
   
	 }
	if (width <= 820  && width >=651) {
		$(".filaTienda").removeClass("par");
		$(".filaTienda:odd").css("float", "right");
	 /*
		$("ul.filaTienda > li").css({
			height: ""
		});
		*/ 
	}
	
	if ( width <= 650) {
		$(".filaTienda:odd").css("float", "none");
		$(".filaTienda").removeClass("par");
		/*
		$("ul.filaTienda > li").css({
			height: ""
		}); 
		*/
	}
	
	if ($(".tablaTienda").is(":visible") && width <= 820 && showMovil == false ) { 
		 $(".max_recomendada").appendTo($("#copyRecomendacion"));
		 $(".maxRecMovil").addClass("show");
		 $(".velo2").fadeIn();
		 showMovil = true;
	}

	 if (width <= 1100  && width >=651) { 
			$(".tablaTipoProyectos ul:odd").addClass("derecha");
	 }
	 if (width >= 1101) {		
		 $(".tablaTipoProyectos ul:odd").removeClass("derecha");
	 }
	 kuboScoreTasa(width);
	 kuboScoreTasaDos(width);
	 if($("#ammountToInvFlot").length) {
		 disponible ();
	 }

		
			nuevoRepetido ();
		
	
}

function kuboScoreTasa() {
	 var width = $(window).width();
	if (width >= 821) { 
		 $(".modoLista .tasaAnualCelda .testForScores").each(function() {
			    $(this).appendTo( $(this).closest(".filaTienda").find(".kuboScoreCelda"));
		 });
	}
	if (width <= 820) {
		$(".modoLista .filaTienda .kuboScoreCelda .testForScores").each(function() {
		    $(this).appendTo( $(this).closest(".filaTienda").find(".tasaAnualCelda"));
	   });
	}	
}

function kuboScoreTasaDos() {
		 $(".modoMosaico .tasaAnualCelda .testForScores").each(function() {
			    $(this).appendTo( $(this).closest(".filaTienda").find(".kuboScoreCelda"));
		 });
}
var modoLista = true;
function filaMosaico() {
	if(modoLista == true) {

		$(".tablaTienda").removeClass("modoLista");
		$(".tablaTienda").addClass("modoMosaico");
		$("#dvContInvFlot").removeClass("modoLista");
		$("#dvContInvFlot").addClass("modoMosaico");

		 $(".btnCambiarFilaMosaico span").html("Lista");
		 $(".btnCambiarFilaMosaico i").removeClass("fa-th");
		 $(".btnCambiarFilaMosaico i").addClass("fa-bars");
		 
		 kuboScoreTasaDos();
		 modoLista = false;
	
		 
	}else {
		
		$(".tablaTienda").removeClass("modoMosaico");
		$(".tablaTienda").addClass("modoLista");
		$("#dvContInvFlot").removeClass("modoMosaico");
		$("#dvContInvFlot").addClass("modoLista");
		$(".btnCambiarFilaMosaico span").html("Mosaico");
		 $(".btnCambiarFilaMosaico i").removeClass("fa-bars");
		 $(".btnCambiarFilaMosaico i").addClass("fa-th");
		 
		 kuboScoreTasa()
		 modoLista = true;
		
	}
}


$(document).ready(function(){	
	
	revisarSeleccionados ();
	filtros ();
	 fondeo_previo ();
	 n_boton_distribuir_quitar();
	 saldo_kubo_impulso ();
	 proyectosMovil ();

	 $('#dvContentLoanLst input[type="checkbox"]').click(function(){
			// disponible ();
	 });
	 $("#invertion_value input[type='text']").keyup(function(){
		 disponible ();
	 });
	 $("#invertion_value input[type='text']").blur(function(){
		 disponible ();
	 });
	 $('body').click(function(){
		 if ($(".ui-tooltip").is(':visible')) {
		 setTimeout(function(){
		 $(".ui-tooltip").hide();
		 }, 500);
		 }
	 });
	 scrollBarraFlotante ();
	 numeracionTienda (); 
	   recuperaIdBotonRegresar();
	   recuperaIdURL ();  
	   $(".tituloInv h2").html($(".selectedMenuInv > a").text());
	   $(window).resize();
	   centrarPnlInv ();
	   $(window).resize(function() {
		   centrarPnlInv ();
			
		});
	   if($("#pnlInvButtonFlot").length){
			  var width = $(window).width();	
				 if (width <= 768) { 
					   $(".invertirOtroBtn").hide();
						 $(".saldosBtn").show();
				 }
		
	   }

	   encabezadosReset ();
});



function scrollBarraFlotante () {
	$(window).scroll(function() {
		 if( $("#encabezadoTablaTienda").offset() != null ){
		      var offset = $("#encabezadoTablaTienda").offset();
		      var offsetRecargar = $("#recargar_proyectos").offset();
		  	  var width = $(window).width();	
		      if( $("#recargar_proyectos").is(":visible"))	{
					     if( (parseInt($(this).scrollTop())+112) > parseInt(offsetRecargar.top -400) ){	
					    	 if(!$("input").is(":focus") ){
					    		 $("#accionScroll").trigger("click");
						    	console.log("accionScroll ---------------");
								console.log("Recarga Lista ---------------");
								console.log("Recarga Lista ---------------");
								console.log("Recarga Lista ---------------");
						     $("#accionScroll").remove(); 
					    	 }
					    	 
					    	
					     }
		      }
			  if( (parseInt($(this).scrollTop())+80) > parseInt(offset.top + 80) ){				
			 	 if( $( "#dvContInvFlot" ).is(":hidden") ){
			 				 	$( "#dvContInvFlot" ).slideDown( "normal" );
			 				 	
			 				 
			 	  }
			 	  $(".barraSecundaria.lista").slideDown( "normal" );
			 	  if (width <= 753) { 
						$( ".encabezadoFlotante" ).fadeIn("fast");
					}
			 	 $( ".header").css("position", "absolute");
			  }
			  
			 if( parseInt( offset.top + 80 ) > (parseInt($(this).scrollTop())+112) ){
			 				if( !$( "#dvContInvFlot" ).is(":hidden") ){
			 					if (width >= 753) { 
			 						$( "#dvContInvFlot" ).slideUp( "normal" );
			 					}
			 					
			 				
			 					
			 				}
			 				if (width <= 753) { 
		 						$( ".encabezadoFlotante" ).fadeOut("fast");
		 					}
			 				$(".barraSecundaria.lista").slideUp( "normal" );
			 				 $( ".header").css("position", "absolute");
			  }
			 
		 }

     });
}
function datosPersonalesComplete() {
		closeFancy();
		 $(".tituloInv h2").html("Datos personales");
}

function totalFondeado () {
	$(".filaTienda").each(function() {
		var filaProyecto = $(this);
		
		var montoFondear = filaProyecto.find($(".montoFondear")).text(), 
			montoFondear	=  Number(montoFondear.replace(/[^0-9\.]+/g,""));
			
		var disponibleFondear = filaProyecto.find($(".disponibleFondear")).text(),
			disponibleFondear =	Number(disponibleFondear.replace(/[^0-9\.]+/g,""));
		
		var porcentajeFondeo = ((montoFondear - disponibleFondear)*100)/montoFondear;
		var progresoFondeoLetra = filaProyecto.find($(".progresoFondeoLetra"));
		
			filaProyecto.find($(".progresoFondeo")).css({
				width: porcentajeFondeo+ "%"
			});
			
			progresoFondeoLetra.text(porcentajeFondeo.toFixed(2)+"%");
		
	});
}

function resetearFiltros () {
	
	$("#filter_by_type input[type='checkbox']").each(function() {
		if (!$(this).prop('checked')) {
			$(this).trigger("click");
		}
	});	
	$("#flagRisk input[value=0]").trigger("click");
	$("#cbo_filter_funding input[value=2]").trigger("click");
	$("#cbo_filter_previous_loan input[value=0]").trigger("click");

	$(".plazo table  .clsTerm, .genero table   .clsTerm, #pnlSearchDestiny table   .clsItemDest, #dvRiskDetail .clsRisk").each(function() {
		if (!$(this).hasClass('itemCheck')) {
			$(this).addClass("itemCheck");
		}
	});	

	$("#for_ammount input[type='number']").val("");
	$("#cmdBuscarFondeados").trigger("click");
	revisarSeleccionados ();
}

function numeracionTienda () {
	setTimeout(function(){
		var numberRegistro = 0;
		$("#creditos .filaTienda .numberRegistro").each(function(idx) {
			numberRegistro = numberRegistro + 1;
			$(this).html(numberRegistro);
			console.log(numberRegistro);
		
		});	
	},300);
	
}

function tiendaBotonMovil () {
	$('#projectListAction').trigger("click");
}


function isEmpty( el ){
    return !$.trim(el.html())
}

function porUsuario () {
$(".tablaTipoProyectos ul").each(function() {
	var proyecto = $(this);
	var numeracion = proyecto.find($(".numProyecto"));	
	var numeracionText = numeracion.text();	
	  if (!isEmpty(numeracion)) {
		  proyecto.css("border-top", "solid 1px #ccc");
		  if(proyecto.next("ul").length) {
		     if(isEmpty(proyecto.next("ul").find($(".numProyecto"))) ) {
				$('<div class="mismoUsuario">Usuario'+numeracionText+'</div>').insertBefore(proyecto);	
			 }
		  }
		  
		  if(proyecto.prev('ul').length) {
			     if(isEmpty(proyecto.prev('ul').find($('.numProyecto'))) ) {
			    	  proyecto.css('border-top', 'solid 2px #999');
				 }
		  }
	  }

});
}

function centrarPnlInv (){
	
		var content = $('#dvPreMyInvestments');
		var width = $(window).width();
		content.css({
				position:'fixed',
						left: ($(window).width() - content.outerWidth())/2
						
				});		
	
}

function saldosBotonMovil(){
	$("#resumenInvest").trigger("click");
}


function listaMosaico() {
	if($(".tablaPro").hasClass("mosaico")){
		$(".tablaPro").removeClass("mosaico");
		$(".tablaPro").addClass("lista");
		$(".lista-mosaico  i").removeClass("fa-bars");
		$(".lista-mosaico  i").addClass("fa-th");
		$(".barraSecundaria").removeClass("mosaico");
		$(".barraSecundaria").addClass("lista");
		
	}else{
		$(".tablaPro").removeClass("lista");
		$(".tablaPro").addClass("mosaico");
		$(".lista-mosaico  i").removeClass("fa-th");
		$(".lista-mosaico  i").addClass("fa-bars");

		$(".barraSecundaria").removeClass("lista");
		$(".barraSecundaria").addClass("mosaico");
	}
	
	
}

function limites(){
	$(".maxRecMovil").addClass("show");
	$(".velo2").fadeIn();


	
}


function btnSwitch(btn) {
	 $(btn).toggleClass("on");
	$(".clsChkHoldSel").trigger("click");
	revisarSeleccionados ();
	
}

function distribuirEnSeleccionados() {
		console.log( "distribuirEnSeleccionados" + $("#distribuirSel") );
		$("#distribuirSel").trigger("click");	
}

function seleccionadosPin (){
	$(".inversionCelda").each(function() {
		var inversionCelda = $(this);
		var inversionCeldaCheck = $(this).find("input:checkbox");
		
			if(inversionCeldaCheck.prop('checked')){
				inversionCelda.addClass("selected");
			}
	});
}

function removerSeleccionado(){
	$(".inversionCelda").removeClass("selected")
}

function revisarSeleccionados (){
	if($(".btnSwitch").hasClass("on")){
		 seleccionadosPin ();
	}else {
		removerSeleccionado();
	}
}

function mantenerSelDesc() {
	$(".toolt").toggleClass("muestra");
	
	
}

$(document).ready(function(){	
	
	$('.toolt, .encabezado, #fixed, .bloque_der').click(function(event){	
		event.stopImmediatePropagation();
		if($(".toolt").hasClass("muestra")){
		
			$(".toolt").removeClass("muestra");
		}
		 
	});
	
	mobileIphone ();
	if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i)) {
		  if(isSafari ){
			 setTimeout(function(){
				 $("#dvContInvFlot").addClass("iphone");
			 }, 300);
		  }
	} 
	
	raShow();
});


function raShow(){
	setTimeout(function(){ 
		if($( "#chart_area_div path[fill='#fdc93d']" ).length -2 > 4){
			$(".datosOnlyRa .ra_inversionistas").show();
		}else{
			$(".datosOnlyRa .nuevos-inversionistas").show();
		}
	},300);
}
function nuevoRepetido () {
	
	setTimeout(function(){
	

	$(".filaTienda").each(function() {
		var fila = $(this);
		var tipoCreditoget = fila.find($(".tipoCreditoget"));
		var nuevoRepetido = fila.find($(".nuevoRepetido"));
	
		if( tipoCreditoget.text().indexOf("Primera vez") != (-1)) {
			nuevoRepetido.html("Nuevo")
		}
		if( tipoCreditoget.text().indexOf("Repetido") != (-1)) {
			nuevoRepetido.html("Repetido")
		}
	});
	},300);
}


function menuComp( comprobante ){
	$(".menuComprobantesBox").addClass("show");
	$(".velo2").fadeIn()

	$('.compInversion').attr("onclick","showAmort"+comprobante+"()");
	$('.compGarantia').attr("onclick","showGarantia"+comprobante+"()");
	$('.compTuspagos').attr("onclick","showPagos"+comprobante+"()");
	
	
	
	$('.compGarantia, .compInversion, .compTuspagos').click(function(event){	
		$(".menuComprobantesBox").removeClass("show");
		$(".velo2").fadeOut();
		
	});
	
}


function clienteBitacora (bitacora) {
	
	

		changeUrlParam('noInversion', bitacora);
		displayMessageProcessing('msgprocessing', false);
		
		
}



function recuperaNoInversion () {	   
	    var locacion = document.location.href; 
	   	if(locacion.indexOf('noInversion')>0) {  
	   		     var  noInversion = GetURLParameterTwo('noInversion');
	   		 $(".fondeokuboid").each(function() {
					var celdaNumeroInv = $(this);
					if(celdaNumeroInv.text() == noInversion ) {
						celdaNumeroInv.closest("ul").addClass("filaSeleccionada");
					}
			});
	   			
	   	}
	   	
}


function comprobantesAlertas(btn, proList){
	$(btn).val('');							
	$(btn).val(proList);
	$(btn).blur();
	$(".velo2").fadeIn();
	document.location.href="#header";
		setTimeout(function(){ 
			alertasCentrarH ();
			$("#dvEdoCuenta").addClass("show");
		},200);
		
	
}




function detalleRa (){
	$(".detalleRaModal").fadeIn();
	$(".velo").fadeIn();
	centerContenido();
	  $('html, body').animate({
	       scrollTop: ($('.tituloInv').offset().top-50)
	  },1000);
}

function cerrarRa() {
	$(".detalleRaModal").fadeOut();
	$(".velo").fadeOut();
}

function ayudaRaTooltip() {
	$(".ayudaRaTooltip").show("500");
	
	
}

function cerrarTooltip () {
	$(".ayudaRaTooltip").hide("500");

}
function cerrarNotificacion(element) {
	$(".notificacionNuevaSeccion").stop().css({'top':'125px', 'opacity':'0', 'pointer-events': 'visible'});
}


function  temporalBlur (){
	displayMessageProcessing('msgprocessing',false);
	$(".ejecutandoBlur").html("se ejecuto el blurrrssss");
}

function mobileIphone (){

	if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i)) {
		  if(isSafari ){
			   
			    	$(window).scroll(function() {
			    			
			    		  totalAltura = $("body").height();
						     alturaWindow = $(window).height();
							 alturaElemento = $("#dvContInvFlot").height();
						     positionActual = $(this).scrollTop();
				   	 	if(!$("#ammountToInvFlot").is(":focus") ){
					    	$("#dvContInvFlot").removeAttr("style");
					    	$("#dvContInvFlot").attr("style", "top:"+(positionActual+alturaWindow -alturaElemento+40)+"px !important" )
				   	 	}else{
				   	 		if(alturaWindow <= 600){
				   	 			$("#dvContInvFlot").removeAttr("style");
				   	 			$("#dvContInvFlot").attr("style", "top:"+(positionActual+alturaWindow -alturaElemento+40-190)+"px !important" )
				   	 		}else{
				   	 			$("#dvContInvFlot").removeAttr("style");
				   	 			$("#dvContInvFlot").attr("style", "top:"+(positionActual+alturaWindow -alturaElemento+40-400)+"px !important" )
				   	 		}
				   	 	}
			    	});
			   	 
		  }
		}
		else {
	
		}
	
}


function completeConfiguration() {
	$(".tituloInv h2").html("Configuración");
	if(!$(".resumen_saldos_cuenta").is(":visible")){
		cerrarNotificacion();
		console.log("cerrar notification");
	}
	
	closeFancy();
	filtros ();

}

function encabezadosReset (){
	if($("#pnlEdoCuentInv").length ){
		$(".tituloInv h2").html("Resumen de saldos");
		$("#resumenInvest").closest(".menu01Invest").addClass("selectedMenuInv");
	}
	if($("#sidebar").length ){
		$(".tituloInv h2").html("Invertir");
		$("#projectListAction").closest(".menu01Invest").addClass("selectedMenuInv");
	}
	if($("#pnlMovsInver").length ){
		$(".tituloInv h2").html("Movimientos");
		$("#cashWithdrawal").closest(".menu01Invest").addClass("selectedMenuInv");
	}
	if($("#buttons").length ){
		$(".tituloInv h2").html("Datos personales");
		$("#personalData").closest(".menu01Invest").addClass("selectedMenuInv");
	}
	if($("#confModulo").length ){
		$(".tituloInv h2").html("Configuración");
		$("#customAction").closest(".menu01Invest").addClass("selectedMenuInv");
	}
	
	$(".menu01Invest").click(function(){
		var elementSelect = ($(this).parents(".secondaryMenuInv")).find(".selectedMenuInv");
		elementSelect.removeClass("selectedMenuInv");
		$(this).addClass("selectedMenuInv");
	});
}

function modalTienda_regresar () {
	$("#resumenInvest").trigger("click");
}

function modalTienda_si () {
	$(".actualizando_sistema-1").slideUp();
	$(".actualizando_sistema-2").slideDown();
	$("#envioMensaje").trigger("click");
}

function modalTienda_cerrar () {
	$("#resumenInvest").trigger("click");
}
