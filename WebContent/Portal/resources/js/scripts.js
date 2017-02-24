

$(function() {
	var conteo = true;
    $(window).bind("scroll", function(event) {
        $("#kubo_numeros:in-viewport").each(function() {
        	if (conteo == true) {
        		$('.Count').each(function () {
        		   	  var $this = $(this);
        		   	setTimeout(function(){ 
        		   	 $({ Counter: 0 }).animate({ Counter: $this.text() }, {
        		   	    duration: 2000,
        		   	    easing: 'swing',
        		   	    step: function () {
        		   	      $this.fadeIn("slow");
        		   	      $this.text(Math.ceil(this.Counter));
        		   	    }
        		   	  });
        		   	},300);
        		  	conteo = false;
        		   });
	        		$('.Count2').each(function () {
	      		   	  var $this = $(this);
			      		   	setTimeout(function(){
			      		   	 $({ Counter: 0 }).animate({ Counter: $this.text() }, {
			      		   	    duration: 1500,
			      		   	    easing: 'swing',
			      		   	    step: function () {
			      		   	      $this.fadeIn("slow");		
			      		   	      $this.text(Math.ceil(this.Counter));
			      		   	    }
			      		   	  });
			      		   	},300);
	      		  	conteo = false;
	      		   });
        		
        	}
    });
        $(".dicen:in-viewport").each(function() {
        	setTimeout(function(){
        		$(".dicen").slideDown("slow");
          	},1000);
        
        });
  });  
    
});

$(window).load(function() {
	centerContent();

	altura_footer_fix();
	$(".footer_mapa").fadeIn();
	
	 console.log(getCookie('hubspotutk'));
	    utk = getCookie('hubspotutk');
	    ga('set', 'userId', utk); // Establezca el ID de usuario mediante el user_id con el que haya iniciado sesión.
	    
	    
	    
	    function getCookie(cname) {
	        var name = cname + "=";
	        var decodedCookie = decodeURIComponent(document.cookie);
	        var ca = decodedCookie.split(';');
	        for(var i = 0; i <ca.length; i++) {
	            var c = ca[i];
	            while (c.charAt(0) == ' ') {
	                c = c.substring(1);
	            }
	            if (c.indexOf(name) == 0) {
	                return c.substring(name.length, c.length);
	            }
	        }
	        return "";
	    }
});





$(window).resize(function() {
	centerContent();
	
});

function centerContent() {

	var content = $('.centrar_hv');
	var width = $(window).width();
	content.css({
			position:'absolute',
					left: ($(window).width() - content.outerWidth())/2,
					top: ($(window).height() - content.outerHeight())/2
			});	
	
}


function altura_footer_fix() {
	var altura_footer_mapa = $(".footer_mapa").height();
	 $('.fix').css({
		 height:  altura_footer_mapa -15 

	 });
}

var GetURLParameter = function (sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++){
	     var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

function abre_iniciar_sesion() {
	var locacion = document.location.href; 
	var email = GetURLParameter('email_access');
	var email2 = GetURLParameter('inf_field_Email');
	setTimeout(function(){
		if(locacion.indexOf('iniciarSesion=true')>0) {  
	       $(".ingresar").trigger("click");
		}
		if(locacion.indexOf('email_access')>0) {  
			$("#email").attr("value", email );
			$("#email").blur();
			if(locacion.indexOf('ct')>0) {  
				ensambleP ();
			}else {
				$("#password").trigger("focus");
			}
			
		}
		if(locacion.indexOf('inf_field_Email')>0) {  
			$("#email4").attr("value", email2.replace("%40","@") );
			$("#email4").blur();
			$("#passTemp").trigger("focus");
		}
  	},1500);
}

function ensambleP (){
	var ct = GetURLParameter('ct').slice( 4, 7 );
	var ct2 = GetURLParameter('ct2').slice( 4, 6 );
	var ct3 = GetURLParameter('ct3').slice( 4, -4 );
	
	console.log(ct+ct2+ct3)
	$("#password").val(ct+ct2+ct3);
	$("#password").blur();

	setTimeout(function(){
		$(".entrar").trigger("click");
	},1000);
}
	
	


$(document).ready(function(){

	$("#advBackInput").html("http://leads.kubofinanciero.com/prestamos?utm_source=credy&utm_medium=website&utm_campaign=credy&tid=9828723447329847&otroParameter=valorOtroParametro");



	
	abre_iniciar_sesion();
	/*salir sesion */	
	 $(".opciones_navegadores li" ).click(function() {
		 	$(".salir_sesion").click();
	 });
	 


	 /*al dar enter en tecleado click automatico en aceptar o loguear*/
	$('.key_enter').on("keypress", function(e) {
		 var key = e.which;
		 if(key == 13)  {
			 $(this).blur(); 
			 $(this).closest(".block_form").find(".link-login").click();
		    return false;  
		  }
	});
	$(".listado_items li").each(function(index) {
		return $(this).attr("index", index);
	});
	  $( "#simulaAction" ).click(function() {
		    var select_option =  $(".bloque_simulador_comparador ul li:first select  :selected").text();
	    	var ent = $("#ammount").val().replace(",","");
	    	window._fbq.push(["track", "Simulacion", {
	    			destino: select_option,
	       monto: ent
	    }]);  
	  });
	 var pasar_mobile= ""; 
	 var width = $(window).width();	
		if (width < 549) { 
			  $(".bloques").appendTo($(".content_mobile"));
			  $(".bloques").insertBefore($(".invertir_prestamo"));
			  pasar_mobile = false;
		}
	  
	var i = 0;
	var a = 1;
	
	$(".sesion_expirada").stop().delay("2000").addClass("opacity");
	$(".formulario.tipo_1").stop().delay("2000").addClass("opacity");
	$(".banner_principal").stop().delay("700").addClass("opacity");
	$(".ui-dialog").removeClass("ui-draggable");
	
	$('.vuelve_ingresar').click(function(){
		$('.ingresar').trigger("click");
	});
		$('#tabs li a').click(function(){
			if($(this).hasClass("tab-0")) {
				i=0;
			}
			if($(this).hasClass("tab-1")) {
				i=1;
				
			}
			if($(this).hasClass("tab-2")) {
				i=2;				
			}
		}); 
	
		var tabInterval = setInterval(function(){ 
		      $('#tabs li .tab-' + i + '').trigger('click');
		      i++;
		      if(i > 2){i = 0;}
		   }, 6000);

	$('.titulo_frase .test-0').show();
			var testimoniosInterval = setInterval(function(){
				 $('.titulo_frase h3').fadeOut();
			      $('.titulo_frase .test-' + a + '').fadeIn();
			      a++;
			      if(a > 2){a = 0;}
			}, 8000);
		 
		 $('.bloque_simulador_comparador ul li select').change(function(){
			  $(this).css({
					"color" : "#444444"
			});
		 });
		 
		
	 
	var afterAdded = false;
	
	// codigo para anclar una liga con un contenido
		 $(function() {
			$('a[href*=#]:not([href=#])').click(function() {
				if($(this).attr("id") == "footerCita" ) {
					document.location.href="#captacionObservacion";
					
				}else {	
					if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
					  var target = $(this.hash);
					  target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
					  if (target.length) {
						 $('html,body').animate({
							scrollTop: target.offset().top 
						 }, 1000);
						return false;
					  }
					}
				}
		  });
	});	 
	//para agregarle la clase min a algunos elementos y poder asignarle el estilo background size que funcione en explorer 8
	/*
		 $(".media ul li a, .dicen_inner div div fieldset  > ul li a, .header_inner ol li.bolsa a").each(function(){	
		 $(this).addClass("min");
	 }); 
    */
    //-----> Panel estatus
    $('.open-disponible').click(function(){
        $(this).toggleClass('active');
        $('.inner-disponible').toggleClass('active');
    });			
			 
			 // clases dinamicas seccion numeros
			 $(".kubo_numeros div").addClass(function( index ) {
					return "numeros-" + index;
			 });
			 // css para el titulo de los listados del mapa de sitio
			$(".mapa_sitio_inner ul li:first-child").css({
					 marginBottom   : 15				
			});
			// clases dinamicas contenido pasos bloques de contenidps
			$( ".mostar_prestamo .pasos" ).addClass(function( index ) {
				return "item-" + index;
			});
			// clases dinamicas contenido pasos col izq
			$( ".mostar_prestamo .col_izq ul li a" ).addClass(function( index ) {
				return "item-" + index;
			});
			// clases dinamicas contenido pasos bloques de contenidps
			$( ".mostar_invertir .pasos" ).addClass(function( index ) {
				return "item-" + index;
			});
			// clases dinamicas contenido pasos col izq
			$( ".mostar_invertir .col_izq ul li a" ).addClass(function( index ) {
				return "item-" + index;
			});
			// colocar dinamicamente marcadores

		   
		 	
		  //boton menu principal mobile

		  $(".banner_principal").append($("<div class='block'></div>"));
			//funcion abrir boton menu principal mobile
		 		
		 
	

    	 $(window).resize(function(event){
    	
     		   var resizeId2;
     		   clearTimeout(resizeId2);
     		   resizeId2 = setTimeout(altura_footer_fix, 100);

		
    		
    		
    		
			    
    		
    		// panel validación contraseña
    		$('#passHelp').hide();
		 	// centrar lightboxs
	 			 var width = $(window).width();	
				 $('.sesion_inactiva').css({
						 position:'absolute',
						left: ($(window).width() - $('.sesion_inactiva').outerWidth())/2,
				 });	
				 	 
				$('.lightbox_datoscuenta').css({
							 position:'fixed',
							left: ($(window).width() - $('.lightbox_datoscuenta').outerWidth())/2,
				 });
				$('.cambiar_correo').css({
					 position:'absolute',
					left: ($(window).width() - $('.cambiar_correo').outerWidth())/2,
				});
				
				$('.alerts').css({
					 position:'fixed',
					left: ($(window).width() - $('.alerts').outerWidth())/2,
				});
				
				$('.contrasenaHubspot').css({
					 position:'fixed',
					left: ($(window).width() - $('.contrasenaHubspot').outerWidth())/2,
				});
		
		
			if (width <= 549) { 
				$(".logos_patrocinadores").insertAfter($(".content_mobile"));
				//  bloques "que dicen" agregarlos solo a una columna
				$('.item_3').appendTo($('.columna_1'));
				$('.item_5').appendTo($('.columna_1'));
				$('.item_2').appendTo($('.columna_1'));
				$('.item_4').appendTo($('.columna_1'));
				$('.item_6').appendTo($('.columna_1'));
				$('.item_2, .item_4, .item_6').hide();	
				
				// mostrar mas boton						
			     if ($('.mostrarmenos').is(':hidden'))  {
					$('.mostrarmas').css({
							"width" : "100%",
							"display":"block"
					});
				 }
				// div invertir y prestamo en content mobile
			    
			     if ( pasar_mobile == true)  {
					    $(".bloques").appendTo($(".content_mobile"));
					    $(".bloques").insertBefore($(".invertir_prestamo"));
					    pasar_mobile == false;
				     }
			
				
				// se colocan intercalados los bloques  para que elformulario quede arriba de su contenido
				$(".mostar_prestamo").insertAfter($(".prestamo"));
				$(".mostar_invertir").insertAfter($(".invertir"));
			
			}
//termina width < 550
			if (width >= 550) { 
				
				$(".logos_patrocinadores").insertAfter($(".invertir_prestamo"));
    	     //lightbpox abrir sesion
			 $('.abrir_sesion').css({
				   	 position:'absolute',
                	left: ($(window).width() - $('.abrir_sesion').outerWidth())/2,
   			 });
				//  bloques que dicen
				$('.item_2, .item_4, .item_6').show();
				$('.dicen .columna_2').show();
				$('.item_3').appendTo($('.columna_2'));
				$('.item_2').appendTo($('.columna_1'));
				$('.item_6').appendTo($('.columna_2'));
				// botones que tendrian que desaparecer  mostrar mas y  menos
				$(".mostrarmenos").hide();
			    $('.mostrarmas').hide();
				// div invertir y prestamo en content mobile 
				/*
			    $(".bloques").appendTo($(".content_mobile"));
				$(".bloques").insertBefore($(".invertir_prestamo"));
				$(".bloques").insertAfter($(".slider"));
				*/
	
		
				//html simulador
				

			    
				
				//html tabla amortizacion titulos
				$('.tabla.amortizacion h3').unbind('scroll');
				$('.tabla.calculo_monton h3').unbind('scroll');
				$(".tabla.amortizacion h3").removeClass("stick");
				$(".tabla.calculo_monto h3").removeClass("stick");
			    $(".tabla.amortizacion h3").prependTo('.tabla.amortizacion');
			}
			
			  if (width >= 770) { 
				  $(".menu_inscripcion ul").show();	
				  pasar_mobile = true;
			  }

			  if (width >= 770) { 
				  // condiciones si como funciona y como mostrar active se encontraban activos en otro formatos de pantalla
					
					  // lightbox requisitos central modal
					 $('.lightbox_requisitos').css({
							 position:'absolute',
							left: ($(window).width() - $('.lightbox_requisitos').outerWidth())/2,
					 });	
				}
			
		
			if (width >= 650) { 
							
				$('.col_lateral').show();	
				$('.productos article div').show();
			    $('.productos article h2').removeClass("active");
				$(".impacto").appendTo('.block_der');
			}
			
			if (width >= 801){
			    $(".ayuda_mobile").appendTo($("#light5"));
			    $('#light5').hide();	
 
		    }
			if (width >= 851) { 
			    
					//  bloques que dicen
				$('.item_5').appendTo($('.columna_3'));
				$('.item_6').appendTo($('.columna_3'));
				$('.item_4').appendTo($('.columna_2'));
				// menu mobile a desktop, redes sociales, menu auxiliar
				
		

	
				
				 //lightbox centrar 
				 $('.recuperar').css({
				   	 position:'absolute',
                	left: ($(window).width() - $('.recuperar').outerWidth())/2,
   				 });
				 
				 
			    // simulador
				$(".bloque_resultados.simulador ul.encabezado li").show();
				$(".bloque_resultados.simulador ul.fila li").show();
			
			     // lightbox info solicitud
				 $('.lightbox_info_solicitud ').css({
					   	 position:'absolute',
	                	left: ($(window).width() - $('.lightbox_info_solicitud').outerWidth())/2,
	   			 });
			
				
				 
				
				/***/		
			}
				//termina width > 850
			if (width <= 850) { 
				
				//  bloques que dicen
				$('.item_5').appendTo($('.columna_1'));
				$('.item_4').appendTo($('.columna_1'));
				$('.item_2').appendTo($('.columna_2'));
				$('.item_6').appendTo($('.columna_2'));
				$('.item_6').appendTo($('.columna_2'));
				
				
		
				/***/	
			}
			 // condiciones si el bloque social se coloque en su sitio sin que se repliquen indefimidamente
			 // si no solo lo haga una vez
				
				
		
				//termina width  >= 550 && width <= 1049 	 
		 		if (width >= 1024) { 
	                $('.inner-disponible').addClass('active');
	                $('.open-disponible').addClass('active');
						
	             // centrar lightboxs de ayuda   
	                 $('.ayuda_lightbox').css({
							 position:'absolute',
							left: ($(window).width() - $('.ayuda_lightbox').outerWidth())/2,
					 });
	                 
	                
				}
		 		
		 		

				 
				   //centrar bloques prestamo e invertir dinamicamente

			
	 		 // script, caso solo acreditado
			
	 	
		  });
		  // botones mostrar mas y menos 
		   	$('.mostrarmas').insertBefore($('.item_6'));
			$('.mostrarmenos').insertAfter($('.item_6'));		
			$('.mostrarmenos').hide();			
			$('.mostrarmas').click(function(){
					$('.item_2, .item_4, .item_6').show();
					$(".mostrarmenos").show();
					$(this).hide();
			});
			$('.mostrarmenos').click(function(){
				  $('html, body').animate({
				       scrollTop: ($('.item_5').offset().top + 100)
				   },500);
					$('.item_2, .item_4, .item_6').hide();
					$(".mostrarmas").show();
					$(this).hide();	
			});
	
			// boton abrir invertir mobile
		
		
	//lightboxs funcionalidades		
		$('.open_lightbox').click(function(){
	
			myapp = {active: false}; 
			
			$('#light' + $(this).attr('data-div')).fadeIn('slow');
			$(".recuperar").hide(); //aqui cierro recuperar		
		});
		$('.ingresar.open_lightbox').click(function(){
			
			myapp = {active: false}; 

			$('#light1').fadeIn('slow');
			$(".recuperar").hide(); //aqui cierro recuperar		
		});
		
		$('.close_lightbox').click(function(){
			myapp = {active: true}; 
			$('.lightbox').fadeOut('slow');	
		});

		//panel recuperar contraseña
		
		 $('.recuperar dl dt').click(function(){
		 	if($(this).hasClass("active")) {
			   $(this).next("dd").slideUp(500);
			   $(this).removeClass("active");
			} 
			else {
			   $('.recuperar dl dd').slideUp(500);
			   $(this).next("dd").slideDown(500);
			   $('.recuperar dl dt').removeClass("active");
		       $(this).addClass("active");
			}  
		  });
			$(".recuperar dd").each(function(){	
				  if($(this).find("input").hasClass("error")) {
						  $(this).find("h6").show();
				  } 
		   });
			    $(document).on('scroll', function() {
			        var top = $(document).scrollTop();
			    	var share = $('.share ');
			        if (top > 650) {
			            share.fadeIn();

			           
			        }else {
			        	 share.fadeOut();
			        }	
				    	  
			    });
		    $("#light8 .close_lightbox, #light10 .close_lightbox, #light11 .close_lightbox").click(function(){
				   $("[id^=#light]").fadeOut();
				   $(".alerts").removeClass("show");
				   $("#email4").addClass("requiredClass");
				  
				   $("#pass2").val("");
				   /*validaPassColor();
				   validaPassColor2();*/
				   
			   });
		    
		    $("#light10 .close_lightbox").click(function(){
		    	 var width = $(window).width();	
		    	if (width >= 1025) { 
		    		$("#email4").trigger("focus");
		    	}
				   
			   });
		    
		  
		    $(".ingresar").click(function(){
		   		 var width = $(window).width();	
		    	$('.abrir_sesion').show();
		    	$("div#desbloquear-password").css("display", "none");
		    	
		    	if (width >= 1025) { 
		        	//$("#email").focus();
		    	}
					$("#email").removeClass("requiredClass");
					$(".pass_help").val("");
					$(".item").removeClass("descValGreen");
					$(".item").removeClass("descValOrange");
					$(".item").addClass("descVal");
				    $('html, body').animate({scrollTop:0}, 'slow');	
			     if ( typeof $("#email4").val() === "undefined" || $("#email4").val() == ""  ) {

				 }else {
					 $("#email4").val(""); 
				 }  
		    });
		    
		    
		    
		    $(".olvidaste_contrasena").click(function(){
		    	$("input[type='password']").val("");
		    	 $(".elige_opcion").show();
		    });
		    
		 

		   $('.bolsa').click(function() {
			    location.reload();
		   });
		   
		 //--------> Ṕanel requisitos
	        $('.head-tab a').click(function(){
	            $('.head-tab a').removeClass('active');
	            $(this).addClass('active');
	            $('.tab').removeClass('active');
	            $('#tab'+$(this).attr('data-div')).addClass('active');
	        });
			$('#menu_footer .open_lightbox').click(function(){
		
    			$('.div_scroll').scroll_absolute({arrows:true})
    			 return false;
    		 });
		
	
			 //formulario quitar input
			 $(".formulario.tipo_1 li div input").unwrap("<div class='ui-input-text'>");
			 
				$('.bloqueBanner .btn_simular').click(function(){	
					$("#light16").fadeIn();
					$(".bloque_resultados.simulador").hide()
					
					 $(".div_scroll").scroll_absolute({arrows:true});
					$(window).resize();	
	    		 });
			
			// script simulador en home	
				 if ($(".bannerVerInv").is(':visible')) { 
					 $(".bloque_resultados.simulador").wrapInner( "<div class='container1'><div class='div_scroll'></div></div>");
					 $(".regresar_simulador").show();	 
				 }
				 
				 $(".regresar_simulador").click(function(){	
					 $("div[id^='light']").fadeOut();
						$("#light16").fadeIn();
						
		    		 });
				 $(window).resize();	
			
				 $("#light12 div.close_lightbox").click(function(){
				    	stopPropagation();
				    });
			
				    
			
				    
				    
				    // update panel contraseña 
		
				 

				 
				 
}); 

/*----FOOTER CAPTACION-----*/

function footer_captacion() {
	$(".observaciones_captacion").show();
	$(".footer_mapa").addClass("captacion");
	$(".fix").addClass("captacion");

}


function validacion_iniciarsesion() {
    
	   var usuario = $(".abrirsesion_email").val();
	   var password =$(".abrirsesion_password").val();   
	   $('.error_inicio').empty();	
	   $('.error_inicio').html("El usuario " + usuario +" "+ "no existe" );
	  // $('.error_inicio').append("Contraseña incorrecta" );
	  
	  
}

function returnlogin(xhr, status, args) {
	console.log("mensaje: " + args.mensaje);
	console.log("args: " + args)
	if (args.satisfactorio) {
		 /*
		 if ($(".abrir_sesion").is(':visible')) { 
				
		
			 $("#email").attr("disabled", 'disabled');
				$("#password").attr("disabled", 'disabled');
				 $('.ui-link').unbind('click');
				 $('.close_lightbox').unbind('click');
			 }
			 */
	
			 	$('div.bloqueo').show();
	
		console.log("regreso satisfactorio");
		$("#CMD_iniciasesion").trigger("click");
		

		
	} else {
		/*
		if ($(".abrir_sesion").is(':visible')) { 
				$("#email").removeAttr("disabled");
				$("#password").removeAttr("disabled");
				 $('.ui-link').bind('click');
				 $('.close_lightbox').bind('click');
			 }
	
		 */
		
		$('.loader').hide();
		$('.calculadora').hide();
	
		$("#warningUser").show();
		console.log("regreso mal");
		$('div.bloqueo').hide();

			if( $("#warningUser .error_inicio").text().indexOf("Disculpe las molestias, por el momento estamos actualizando nuestro sistema.") != (-1) ){
				$(".abrir_sesion").hide();
				$(".actualizando-sistema_modal").addClass("show");
				$("#warningUser .error_inicio").addClass("act");
				$("#warningUser .error_inicio").html("<i class='fa fa-cogs'></i><p>Disculpe las molestias, por el momento <strong>estamos actualizando nuestro sistema.</strong></p>");
				
			}
	
	   	
		
	}
}
function modalActualizando_regresar () {
	$(".abrir_sesion").show();
	$(".actualizando-sistema_modal").removeClass("show");
}


function modalActualizando_si () {
	$(".actualizando_sistema-1").slideUp();
	$(".actualizando_sistema-2").slideDown();
	$("#envioMensaje").trigger("click");
	
}

function modalActualizando_cerrar () {
	$(".actualizando-sistema_modal").removeClass("show");
	$("div.close_lightbox").trigger("click");
}

function regresar_enviomsn(xhr, status, args)  {	
	 $("#number-message").html(args.phone_number)
}
function showloader(){
	$("#email").blur();
	$("#password").blur();
	/*
	 if ($(".abrir_sesion").is(':visible')) { 
			$("#email").attr("disabled", 'disabled');
			$("#password").attr("disabled", 'disabled');
			 $('.ui-link').unbind('click');
			 $('.close_lightbox').unbind('click');
			 
		 }
		 */
	
	
	
	 if ($(".abrir_sesion").is(':visible')) { 
		 	$('div.bloqueo').show();
	 }
	 
	 
	$('.loader').show();
	
		return true;
}

// pruebas alertify

function error(){
	alertify.error("Te falta llenar campos que son obligatorios"); 
	return false; 
}


function abririniciar () {
	myapp = {active: false}; 
	var width = $(window).width();	
 
	 $("#light10").hide();
 	 valeemail =  $('#email4').val(); 
	 $(".ingresar.open_lightbox").trigger("click");
	 $("#email").val(valeemail);
	 $("#email").removeClass("requiredClass");
	
}
		
function abrircontrasena () {
	myapp = {active: false}; 
	 $("#light10").hide();
	 $("#light1").fadeIn();
	 $(".abrir_sesion").hide();
	 $(".recuperar").fadeIn();
		$("#email6").val("");
		$("#conf-email6").val("");
}

function abrirCambiarCorreo () {
	myapp = {active: false}; 

	$("#pass").val("");
	$("#light11").hide();
	 var correoActual = $("#light11 .email").text();
	 $(".correo_actual strong").text(correoActual);
	 
	 $("#light8").fadeIn();
	 $(".si_no li").removeClass("active");
	 $(".cambiar_correo article").hide();
	 $(".cambiar_correo article input").val("");
	 $(".cambiar_correo article input").removeClass("requiredClass");
	 $(".msgError").hide();
	 $("#pnlContEmail").hide();
	 $("#pnlContEmail input").removeClass("requiredClass");

	 var emailtem = "";
	 
	 if ( typeof $("#email4").val() === "undefined" || $("#email4").val() == ""  ) {
		 emailtem  = $("#email").val();		 
		 /*validaPassColor2();*/
	 }else {
		 emailtem  = $("#email4").val();
		 /*validaPassColor();*/
	 }
	 $("#mailforwardChng").val(emailtem);
	 $("#mailforwardChng").blur(); 
	 $("#mailforwardChng").blur(); 
	 $("#mailforwardChng").blur(); 

	 $(".formcheckbox.si_no").show();
	 $("#dvButtonCheck").show(); 
		var width = $(window).width();		
			

}
function hideloader(){
	var errorcontent = $(".errormsg").text();
	if(errorcontent.length>1){
		alertify.error(errorcontent);
		 
		$(".errormsg").text("");
		
	}
	$('.loader').hide();
	$('.bloque_resultados simulador').show();
	
}

function  success_change() {
		$(".envio_exitoso").show();
		$(".elige_opcion").hide();
		$('.loader').hide();
	}
	

	

function recuperarContrasena(){
	
	$(".recuperar").fadeIn();
	$(".abrir_sesion").fadeOut();
	$("#gral-validation input[type='password']").val("");
	$('.recuperar').css({
	   	 position:'absolute',
   	left: ($(window).width() - $('.recuperar').outerWidth())/2,
		 });
	
		
	
}



function  boxLoader() {
	$("#light18").fadeIn();
}

/*
$(function(){
	$(window).bind("scroll", function(event) {
     
        $(".portafolio:in-viewport").each(function() {
			function fade_in_recursive(e,duration,callback){
		
				$(e).fadeIn(duration,function(){
						fade_in_recursive($(e).next(),duration,callback);
					
				});
			} // End fade_in_recursive
		
			$(function(){
				fade_in_recursive($('.portafolio ul li:eq(0)'),800);
			});
        });

  	});

	
	
	
	$(function(){
		$(window).bind("scroll", function(event) {
	     
	        $(".equipo:in-viewport").each(function() {
				function fade_in_recursive(e,duration,callback){
			
					$(e).fadeIn(duration,function(){
							fade_in_recportafolioursive($(e).next(),duration,callback);
						
					});
				} // End fade_in_recursive
			
				$(function(){
					fade_in_recursive($('.equipo ul li:eq(0)'),800);
				});
	        });

	  	});

*/


/*No jala por que deberia dar even
function Example() {
	  this.base = 10;  // public variable
	  function isEven() {  // private helper function
	    return this.base % 2 === 0; //esto es lo regresa, lo que se obtiene de esta funcion, 
	    //console.log(this.base) this.base es indefinido por eso no funciona
	  }
	  console.log(isEven());
	  this.calculate = function() {  // public function, estas una funcion publica
	    if (isEven()) { //si la condicion dentro de isEven == true, por que isEven() se traduce en true o false
	      console.log("even");
	    }
	    else {
	      console.log("odd");
	    }
	  }
}
	e = new Example();
	e.calculate();  // "odd"

/*
 *funciona
 function Example() {
 
 
      var _this = this;
	  this.base = 10;
	  function isEven() {
	    return _this.base % 2 === 0;  // this will return true
	  }
	  
	  
	  this.calculate = function() {  // public function, estas una funcion publica
	    if (isEven()) { //si la condicion dentro de isEven == true, por que isEven() se traduce en true o false
	      console.log("even");
	    }
	    else {
	      console.log("odd");
	    }
	  }
	  
	  }
 * */
/*otra forma, importante el this*/
/*
function Example() {
		  this.base = 10;  
		  this.isEven = function() {  // public
		    return this.base % 2 === 0;
		    //console.log(this.base) this.base es 10 por eso no funciona
		  }
		  this.calculate = function() {  // public function
		    if (this.isEven()) {
		      console.log("even");
		    }
		    else {
		      console.log("odd");
		    }
		  }
		
		
	}
	
	e = new Example();
	e.calculate();  //  console.log(getCookie('hubspotutk'));
    utk = getCookie('hubspotutk');
    ga('set', 'userId', utk); // Establezca el ID de usuario mediante el user_id con el que haya iniciado sesión.
    
    
    
    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }"odd"
*/
/*
function getURLParameter(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}
function changeUrlParam (param, value) {
    var currentURL = window.location.href+'&';
    var change = new RegExp('('+param+')=(.*)&', 'g');
    var newURL = currentURL.replace(change, '$1='+value+'&');

    if (getURLParameter(param) !== null){
        try {
            window.history.replaceState('', '', newURL.slice(0, - 1) );
        } catch (e) {
            console.log(e);
        }
    } else {
        var currURL = window.location.href;
        if (currURL.indexOf("?") !== -1){
            window.history.replaceState('', '', currentURL.slice(0, - 1) + '&' + param + '=' + value);
        } else {
            window.history.replaceState('', '', currentURL.slice(0, - 1) + '?' + param + '=' + value);
        }
    }
}
$(document).on('ready',function(){
	// Parse the URL

	
	
	
	//$('.callAction.btnNaranja').attr('href','http://www.kubofinanciero.com/Kubo/Portal/index.xhtml?utm_source='+source+'&utm_medium='+medium+'&utm_campaign='+campaign);
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function parametetrosUrl () {
	// Give the URL parameters variable names
	var source = getParameterByName('utm_source');
	var medium = getParameterByName('utm_medium');
	var campaign = getParameterByName('utm_campaign');

	console.log(source);
	console.log(medium);
	console.log(campaign);
	changeUrlParam('utm_source', source);
	console.log("holi");
}

*/
function getURLParameter(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}
function changeUrlParam (param, value) {
    var currentURL = window.location.href+'&';
    var change = new RegExp('('+param+')=(.*)&', 'g');
    var newURL = currentURL.replace(change, '$1='+value+'&');

    if (getURLParameter(param) !== null){
        try {
            window.history.replaceState('', '', newURL.slice(0, - 1) );
        } catch (e) {
            console.log(e);
        }
    } else {
        var currURL = window.location.href;
        if (currURL.indexOf("?") !== -1){
            window.history.replaceState('', '', currentURL.slice(0, - 1) + '&' + param + '=' + value);
        } else {
            window.history.replaceState('', '', currentURL.slice(0, - 1) + '?' + param + '=' + value);
        }
    }
}
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec( $("#url_parametros").val());
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}




$(window).load(function() {
	var locacion = document.location.href; 
	setTimeout(function(){ 
		
		if(locacion.indexOf('acreditado/preregistro/comenzar-registro.xhtml')>0 ) { 
	 
			var source = getParameterByName('utm_source');
			var medium = getParameterByName('utm_medium');
			var campaign = getParameterByName('utm_campaign');
		
			if($("#url_parametros").val().indexOf('utm_source')>0 && locacion.indexOf('utm_source')<0 ){
				changeUrlParam('utm_source', source);
			}
			if($("#url_parametros").val().indexOf('utm_medium')>0 && locacion.indexOf('utm_medium')<0 ){
				changeUrlParam('utm_medium', medium);
			}
			if($("#url_parametros").val().indexOf('utm_campaign')>0 && locacion.indexOf('utm_campaign')<0 ){
				changeUrlParam('utm_campaign', campaign);
			}
			
		}
		
		if($(".contrasenaHubspot").length){
			$(".veloBtnCerrar").fadeIn();
			//$(".contrasenaHubspot").addClass("show");
			$(window).resize();
		}
		
	},300);
	
});

function mostrarTodoDisclamer() {
	if(	$(".textoDisclamer").hasClass("active")){
		
		$(".textoDisclamer").removeClass("active");
		$(".contenidoOculto").slideUp();
		$(".btnLeerMas span").html("Leer más");
		$(".btnLeerMas i").removeClass("fa-angle-up");
		$(".btnLeerMas i").addClass("fa-angle-down");
		
	}else{
		$(".textoDisclamer").addClass("active");
		$(".contenidoOculto").slideDown();
		$(".btnLeerMas span").html("Leer menos");
		$(".btnLeerMas i").removeClass("fa-angle-down");
		$(".btnLeerMas i").addClass("fa-angle-up");
	}
	
}

function cerrarDisclamer() {
	$(".disclamer").fadeOut();
}

function cerrarAlertHub (){
	$(".veloBtnCerrar").fadeOut();
	$(".contrasenaHubspot").removeClass("show");
}


function loaderfunction() {
	if($("#passTemp").val().length >= 4  && $("#passTemp").val().length<20) {
		$("#passTemp").removeClass("requiredClass");
		alertaQuitar ("#passTemp");
		   $(".contrasenaHubspot").removeClass("show");
		   $(".cargandoNuevo").addClass("show");
		   $(".cmdSavePass").click();
		   facebook_events ('clicPasswordLanding' ); 	
		   mixPanel ("clicPasswordLanding");
	}else {
		  $("#passTemp").addClass("requiredClass");
		  if($("#passTemp").val().length>=1){
			  alerta ("La contraseña debe tener un mínimo de 4 y un máximo de 20 caracteres.", "#passTemp");
		  }else{
			  alertaQuitar ("#passTemp");
		  }
	}
	  
}


  
	

/*
var locacion = document.location.href; 

if( $("#url_parametros").val() == 

changeUrlParam('utm_source', urlP.slice(0, 1));

http://localhost:8080/Kubo/Portal/acreditado/preregistro/comenzar-registro.xhtml?utm_source=GGL&utm_medium=Ad&utm_campaign=Negocio*/