
$(document).ready(function(){


	var afterAdded = false;

		  
	  
	  
		 $(function() {
			$('a[href*=#]:not([href=#])').click(function() {	
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
		  });
	});	 
	//ocultar lightbox $('.lightbox').hide();	
	$(".media ul li a, .dicen_inner div div fieldset  > ul li a, .header_inner ol li.bolsa a").each(function(){	
		 $(this).addClass("min");
	 }); 
    
    //-----> Widget 
    $('.open-disponible').click(function(){
        $(this).toggleClass('active');
        $('.inner-disponible').toggleClass('active');
    });
	 // agregar clase a elementos con background-size
	  // titulos version desktop y escritorio text shadow
			$(".slidesjs-slide").each(function(){	
				 var titulo = $(this).find("h2");
				 var desktop  = $(this).find(".desktop");
				 var mobile = $(this).find(".mobile");
				 $(titulo).clone().appendTo( mobile);
				 titulo.appendTo($(desktop));
				 $(this).addClass("ie ie9 ie8and9");
				  mobile.append("<div class='shade'></div>");
			});
				 // titulos text shadow
			$(".slidesjs-slide div h2").each(function(){
						var tituloh2 = $(this);
						var original = $(this).find("span");
						 original.addClass("shadow").clone().appendTo(tituloh2).removeClass("shadow");
							
			 });					
			//incluir menu privado 
			$(".menu_principal div").append($("<ol></ol>"));	
			$(".menu_principal div ol").insertBefore($(".menu_principal div ul"));
			 // clases dinamicas menu principal
			 $(".menu_principal ul li").addClass(function( index ) {
					return "menu-" + index;
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
			$('.bloques').append($("<div class='indicador2'><img src='images/invertir_activo.png'/></div>"));
			$('.bloques').append($("<div class='indicador'><img src='images/prestamo_active.png'/></div>"));
		   //funcion invertir y prestamo version escritorio y mobile
		   	var indicador = false;
		   $('.como_funciona, .indicador').click(function(){
				var width = $(window).width();	
				$(".pasos").hide();
				$(".mostar_prestamo .pasos:first").show();
				$('.mostar_invertir').slideUp();
				$('.mostar_prestamo').slideToggle();
				$(".col_izq ul li a").removeClass("active");
				$('.mostar_prestamo .col_izq ul li a:first').addClass("active"); 
				$('.funciona_filosofia').hide();
				if( width >= 550 && width <= 1023 ) { 
					
                        
				}
				
				 $(".como_funciona").addClass("active");
					
				if (width < 770) { 
					$(".mostar_prestamo .pasos").show();
				}
				if (indicador == false) {
					$('.indicador').fadeIn(100);
					$('.indicador2').fadeOut(100);
					$(".como_invertir").removeClass("active");
					indicador2 = false;
					indicador = true;
					if( width >= 550 && width <= 1023 ) { 
									
					}
				} else {
					$('.indicador').fadeOut(100);
					$(".como_funciona").removeClass("active");
					indicador = false;
					if( width >= 550 && width <= 1023 ) { 
						
					}
				}
		 });
		 	
			   	var indicador2 = false;
			    $('.como_invertir, .indicador2').click(function(){
						 var width = $(window).width();	
						$(".pasos").hide();
						$(".mostar_invertir .pasos:first").show();
						$('.mostar_prestamo').slideUp();
						$('.mostar_invertir').slideToggle();
						$(".col_izq ul li a").removeClass("active");
						$('.mostar_invertir .col_izq ul li a:first').addClass("active"); 	
						$('.funciona_filosofia').hide();
						$(".como_invertir").toggleClass("active");
						
						if( width >= 550 && width <= 1023 ) { 
				
						}
			
						if( width <= 770  ) { 
								$(".mostar_invertir .pasos").show();
						}
						if (indicador2 == false) {
								$('.indicador2').fadeIn(100);
								$('.indicador').fadeOut(100);
								$('.como_funciona').removeClass("active");
								
								indicador = false;
								indicador2 = true;
								if( width >= 550 && width <= 1023 ) { 
											
								}
						} else {
								$('.indicador2').fadeOut(100);
								indicador2 = false;
									if( width >= 550 && width <= 1023 ) { 
													
									}
							
						}
		 });
		  //boton menu principal mobile
		  $(".menu_principal").append($("<h6 class='open'></h6><h6 class='close'></h6>"));	
		  $(".banner_principal").append($("<div class='block'></div>"));
			//funcion abrir boton menu principal mobile
		  $('.menu_principal .open').click(function(){	
				$(this).hide();
				$('.menu_principal .close').show();
				$('.menu_principal').animate({right:"0"}, 300);
				$('.menu_principal .open').removeClass("active");
				$('.menu_principal .close').addClass("active");
		 });
	 	//funcion cerrar boton menu principal mobile
		 $('.menu_principal .close').click(function(){
				$(this).hide();
				$('.menu_principal .open').show();
				$('.menu_principal').animate({right:"-212px"}, 300);
				$('.menu_principal .close').removeClass("active");
				$('.menu_principal .open').addClass("active");					
		  });			
			//funcion seccion pasos       
			$('.col_izq ul li a').click(function(){
					clase = $(this).attr('class');
					$('div.'+clase).hide()
					.siblings(".pasos").slideUp();
					$('div.'+clase).slideDown();
					$(".col_izq ul li a").removeClass("active");
					$(this).addClass("active");	
		   });  
	   // push pasos seccion mobile
		 $(".pasos").append($("<div class='push_bottom'></div>"));	
	 	 
		  
    	$(window).resize(function(event){
    				
    				
    		
    		

    		
    		 $('#passHelp').hide();
    		
		
		 	// centrar lightbox sesion inactiva
	 			 var width = $(window).width();	
				 $('.sesion_inactiva').css({
						 position:'absolute',
						left: ($(window).width() - $('.sesion_inactiva').outerWidth())/2,
				 });	
				 	 
				$('.lightbox_datoscuenta').css({
							 position:'fixed',
							left: ($(window).width() - $('.lightbox_datoscuenta').outerWidth())/2,
				 });
			//  margen de abajo de los botones como fuciona y como invertir para la version mobile
//termina width < 380 	
			if (width <= 549) { 
	
				//  bloques que dicen agregarlos solo a una columna
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
				$(".bloques").appendTo($(".content_mobile"));
				$(".bloques").insertBefore($(".invertir_prestamo"));
				$(".bloques > h2").appendTo($(".banner_principal"));
				// se colocan intercalados los bloques  para que elformulario quede arriba de su contenido
				$(".mostar_prestamo").insertAfter($(".prestamo"));
				$(".mostar_invertir").insertAfter($(".invertir"));
				
			    // margen cuando los bloques no estan abiertos version mobile
			 
				 $("div.prestamo, div.invertir").css({
					
	   		 	 });
					
				// formulario que aparece si hay ligas activas desde escritorio
					if($(".como_funciona").hasClass("active")) {
					   $("div.prestamo article").show();
					   $("div.invertir article").hide();
					   $("div.prestamo > h2").hide();
					   $("div.prestamo > h3").show();
					}
					else {
						 if ($("div.prestamo > h3").is(':visible')) {
					  	 	$("div.prestamo article").show();
						 }else {
							$("div.prestamo article").hide();
						    $("div.prestamo > h3").hide();
						 	$("div.prestamo > h2").show();
						 }
					
					} 
					if($(".como_invertir").hasClass("active")) {
					   $("div.invertir article").show();
					   $("div.prestamo article").hide();
					   $("div.invertir > h2").hide();
					   $("div.invertir > h3").show(); 
					} 
					else {
						 if ($("div.invertir > h3").is(':visible')) {
					  	 	$("div.invertir article").show();
						 }else {
							$("div.invertir article").hide();
							$("div.invertir > h3").hide();
					  	    $("div.invertir > h2").show();
						 }
					 
					}	
				
	
					
				                     
					
					
					
					
					
					                
	
		
				
					
								
							
								

			}
//termina width < 550
			if (width >= 550) { 
			
	
							
		     $(".indicador img").attr('src', 'images/prestamo_active.png');
    	     $(".indicador2 img").attr('src', 'images/invertir_activo.png');
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
				$(".bloques").appendTo($(".content_mobile"));
				$(".bloques").insertBefore($(".invertir_prestamo"));
				$(".bloques").insertAfter($(".slider"));
				$(".mostar_prestamo, .mostar_invertir").appendTo($(".invertir_prestamo"));
				$( "div.prestamo article, div.invertir article, div.invertir h2, div.prestamo h2").show();
				$( "div.prestamo > h3, div.invertir > h3").hide();
		
				
				
				
				
				//html simulador
				$(".bloque_resultados.simulador ul.encabezado li").show();
				$(".bloque_resultados.simulador ul.fila li").show();
			    
				
				//html tabla amortizacion
				$('.tabla.amortizacion h3').unbind('scroll');
				$('.tabla.calculo_monton h3').unbind('scroll');
				$(".tabla.amortizacion h3").removeClass("stick");
				$(".tabla.calculo_monto h3").removeClass("stick");
			    $(".tabla.amortizacion h3").prependTo('.tabla.amortizacion');
		
			}
			
			  if (width >= 770) { 
			  $(".menu_inscripcion ul").show();
					
			  }
 		    if (width <= 770) { 
				 if($(".como_funciona").hasClass("active")) {
					 $(".mostar_prestamo .pasos").show();
				}
				 if($(".como_invertir").hasClass("active")) {
					 $(".mostar_invertir .pasos").show();
				}	
			}
		  if (width >= 770) { 
				 if($(".como_funciona").hasClass("active")) {
					 $('.mostar_prestamo').show();
					 $(".pasos").hide();
					 $(".mostar_prestamo .pasos:first").show();
					 $(".col_izq ul li a").removeClass("active");
					 $('.mostar_prestamo .col_izq ul li a:first').addClass("active"); 
				}
				 if($(".como_invertir").hasClass("active")) {
					 $('.mostar_invertir').show();
					 $(".pasos").hide();
					 $(".mostar_invertir .pasos:first").show();
					 $(".col_izq ul li a").removeClass("active");
					 $('.mostar_invertir .col_izq ul li a:first').addClass("active"); 
				}
				 $('.lightbox_requisitos').css({
						 position:'absolute',
						left: ($(window).width() - $('.lightbox_requisitos').outerWidth())/2,
				 });	
			}
			
			if (width <= 651) { 
				$('.txt-0 a').removeClass("tip");	
				
				$('.impacto').insertBefore('.relacion');
				
			}
			if (width >= 650) { 
							$('.col_lateral').show();	
							$('.productos article div').show();
							$('.productos article h2').removeClass("active");
							$('.menu_interior  ul').show(); 
						  $(".impacto").appendTo('.block_der');
			}
	
			if (width >= 801){
						  $(".ayuda_mobile").appendTo($("#light5"));
						  $('#light5').hide();	
						}
			
			if (width <= 800){
			
			}
			if (width >= 851) { 
	
					//  bloques que dicen
				$('.item_5').appendTo($('.columna_3'));
				$('.item_6').appendTo($('.columna_3'));
				$('.item_4').appendTo($('.columna_2'));
				// menu mobile a desktop, redes sociales, menu auxiliar
				
			    $('.ingresar').appendTo('.ingresar_lista');
				$('.requisitos').appendTo('.requisitos_lista');
				$('.menu_principal .close').hide();
				$('.menu_principal .open').hide();
		
				// botones de sesion privada	
			    $(".menu_principal > div ol li.usuario").appendTo('.header_inner.privado > ol');
		        $(".menu_principal > div ol li.ayuda").appendTo('.header_inner.privado > ol');
			    $(".menu_principal > div ol li.logout").appendTo('.header_inner.privado >  ol');
				$(".header_inner.privado ol li.usuario").insertBefore('.header_inner.privado ol li.n_folio');
				$(".header_inner.privado ol li.ayuda").insertAfter('.header_inner.privado ol li.bolsa');
				$(".header_inner.privado ol li.logout").insertAfter('.header_inner.privado ol li.ayuda');
				 //lightbox centrar 
				 $('.recuperar').css({
				   	 position:'absolute',
                	left: ($(window).width() - $('.recuperar').outerWidth())/2,
   				 });
				 
				 $(function(){ //scroll menu principal escritorio
			   	var lastScroll = 0;
				  $(window).scroll(function(event){
					  var st = $(this).scrollTop () ;
					  if (st >  lastScroll){
							$( ".header").hide();
					  }
					  else {
						    $( ".header").fadeIn();
					  }
					  if (st <= 150){
						   $( ".header").show();
					  }
					  lastScroll = st;  
				  });
				});	
			
				$(".bloque_resultados.simulador ul.encabezado li").show();
				$(".bloque_resultados.simulador ul.fila li").show();
			
			
			 $('.lightbox_info_solicitud ').css({
				   	 position:'absolute',
                	left: ($(window).width() - $('.lightbox_info_solicitud').outerWidth())/2,
   			 });
				$('.media').insertAfter('.header_inner > ol ');
		
			   
			
			}
//termina width > 850
			if (width <= 850) { 
				
				//  bloques que dicen
				$('.item_5').appendTo($('.columna_1'));
				$('.item_4').appendTo($('.columna_1'));
				$('.item_2').appendTo($('.columna_2'));
				$('.item_6').appendTo($('.columna_2'));
				$('.item_6').appendTo($('.columna_2'));
				
				// menu mobile, redes sociales, menu auxiliar
			
				
				$('.ingresar, .requisitos').appendTo('.menu_principal > div');
				$('.ingresar, .requisitos').insertBefore('.menu_principal > div > ul');
				$('.menu_principal .open').show();
			
				// botones de sesion privada	
			    $(".header_inner.privado ol li.usuario").appendTo('.menu_principal > div ol');
		        $(".header_inner.privado ol li.ayuda").appendTo('.menu_principal > div ol');
				$(".header_inner.privado ol li.logout").appendTo('.menu_principal > div ol');
				//menu scroll down desaparece,scroll up aparece 
				 $(function() { window.scrollTo(0, 0); });
					var currentScrollPosition = 0;
					$(window).on('touchmove', function(event) {
						if (window.scrollY > currentScrollPosition) {
							 $( ".header").hide();
						} else {
						 $( ".header").show();
						 $( ".header_inner.interior").css("background-color" , "transparent");
						}
						if (window.scrollY  <= 50){
							 $( ".header").show();
							 $( ".header_inner.interior").css("background-color" , "#fff");	 
         			 	}
			   		currentScrollPosition = window.scrollY;
				});	 
					
				
					
			}
			 if (($(window) .width() > 851) &&  afterAdded) {
			    	
			      afterAdded = false;
			    }
				   if (($(window) .width() < 850) && ! afterAdded) {
				    	 $('.media').insertAfter($('.menu_principal div ul'));
				      afterAdded = true;
				    }
			
//termina width < 850
			if( width >= 550 && width <= 1024 ) {
				if($(".como_invertir").hasClass("active")) { 
				
					
				}
				if($(".como_funciona").hasClass("active")) { 
							
				}
			}
//termina width  >= 550 && width <= 1049 	 
	 		if (width >= 1024) { 
                $('.inner-disponible').addClass('active');
                $('.open-disponible').addClass('active');
					
					    $('.ayuda_lightbox').css({
						 position:'absolute',
						left: ($(window).width() - $('.ayuda_lightbox').outerWidth())/2,
				 });	
			}
			
			
			
			var share = $('.share ');
			if (width <= 1024) { 
			 $(function() { window.scrollTo(0, 0); });
					var currentScrollPosition = 0;
					$(window).on('touchmove', function(event) {
						if (window.scrollY > 80) {
							 $( ".disponible").addClass("arriba");
						} else {
						 $( ".disponible").removeClass("arriba");
						}
						
						if (window.scrollY > 250) {
							 share.fadeIn();
						} else {
							share.fadeOut();
						}
			   		
				});	 
			
			
			}
			
			
			
						
					
					
			
				 $('.slidesjs-slide  h2').css({
							 position:'absolute',
							left: ($(window).width() - $('.slidesjs-slide  h2').outerWidth())/2,
				});
				   //centrar bloques prestamo e invertir dinamicamente
				 $('.bloques').css({
							position:'absolute',
							left: ($(window).width() - $('.bloques').outerWidth())/2,
				 });
				 
			
				 

				   
					
				    

					
						
						
				 
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
					$('.item_2, .item_4, .item_6').hide();
					$(".mostrarmas").show();
					$(this).hide();
			});
		 // agregar boton  cerrar a los bloques prestamo e invertir  para la version mobile
		
			  $(".prestamo").append($("<h3><a href='#prestamo_invertir'>Quieres un <strong>préstamo</strong></a></h3>"));	
			
			  $(".prestamo > h3").insertBefore('.prestamo article');
		
			  $(".invertir").append($("<h3><a href='#prestamo_invertir'>Quieres <strong>invertir</strong></a></h3>"));	
		
			  $(".invertir > h3").insertBefore('.invertir article');
			// funcion bloques prestamo e inertir version mobile	
	
			$( "div.prestamo > h2 a, div.invertir > h2 a").click(function() {
					 var width = $(window).width();	
					 if (width < 550) { 
					    $("a.como_funciona, a.como_invertir").removeClass("active");
						$(this).parent().parent().find("article").slideDown();
					    $(this).parent().hide();
						$(this).toggleClass("active");
						$(this).parent().parent().find("h3").show();
					    $(".indicador, .indicador2").hide();
						$(".bloques").css({
							"marginBottom" : 30
						});
					 }
			 });
			 // boton abrir prestamo mobile
			$( "div.prestamo > h2 a").click(function() {
				var width = $ (window).width();	
				if (width < 550) { 
					 $("div.invertir article").slideUp();
					 $(".mostar_invertir").slideUp();
					 $("div.invertir h2").show();
					 $("div.invertir > h3").hide();
				}
			});
			// boton abrir invertir mobile
			$( "div.invertir > h2 a").click(function() {
				 var width = $(window).width();	
				 if (width < 550) { 
					 $("div.prestamo article").slideUp();
					 $(".mostar_prestamo").slideUp();
					 $("div.prestamo h2").show();
					 $("div.prestamo > h3").hide();
				 }
			});
			// bloques prestamo invertir, boton cerrar
			$( "div.prestamo > h3 a, div.invertir > h3 a").click(function() {
				 var width = $(window).width();	
				 if (width < 550) { 	
						 $(this).parent().parent().find("article").slideUp();
						 $(".mostar_prestamo, .mostar_invertir").slideUp();	
						 $(this).parent().parent().find("h2").show();	
						 $(this).parent().hide();
						 $(".indicador, .indicador2").hide();
							 //if($(this).parent().is(":hidden")) {		
							 //}
						 $("a.como_funciona, a.como_invertir").removeClass("active");
						 if ($("div.prestamo > h2").is(':visible') && $("div.invertir > h2").is(':visible'))  {
								$(".bloques").css({
									"marginBottom" : 142
								});
	
						 } else {
								$(".bloques").css({
									"marginBottom" : 30
							    });
						 }
				  }
				  if (width < 401) { 	
						 if ($("div.prestamo > h2").is(':visible') && $("div.invertir > h2").is(':visible'))  {
								$(".bloques").css({
									"marginBottom" : 262
								});	
						}else {
							 $(".bloques").css({
									"marginBottom" : 30
								});
						}
					}
					 if (width < 340) { 	
						    if ($("div.prestamo > h2").is(':visible') && $("div.invertir > h2").is(':visible'))  {
								$(".bloques").css({
									"marginBottom" : 130
								});
							
							}else {
								 $(".bloques").css({
									"marginBottom" : 40
								});
							}
						}
					   if (width < 320) { 	
						    if ($("div.prestamo > h2").is(':visible') && $("div.invertir > h2").is(':visible'))  {
									$(".bloques").css({
										"marginBottom" : 30
									});
								
							}else {
									 $(".bloques").css({
										"marginBottom" : 30
									});
							}
						}
						indicador2 = false;
						indicador = false;	
	     });
		
	//lightboxs funcionalidades		
		$('.open_lightbox').click(function(){
			$('#light' + $(this).attr('data-div')).fadeIn('slow');
			$(".recuperar").hide(); //aqui cierro recuperar
			$('.abrir_sesion').show("fast", function(){
				
				$('.abrirsesion_email').trigger("blur");
				$('.abrirsesion_password').trigger("blur");
				
				
			}); //paar que siempre abra elbloque de inicio de sesion 
		});
		$('.close_lightbox').click(function(){
			$('.lightbox').fadeOut('slow');
		});
		$('.abrir_sesion p > a').click(function(){
		  		$(".recuperar").fadeIn();
				$(".abrir_sesion").fadeOut();
	    });
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
    
			
				
		  
		  
		 //funcion para mostar de manera random frases o testimonio del home 
			$(function() {
						 var testimonio = $(".titulo_frase_inner h3").get().sort(function(){ 
							  return Math.round(Math.random())-0.5; 
						 }).slice(0,1)
						 $(testimonio).show();	
				});	
				
				    var titulocomparador = $(".comparador_4 h5");   
					var positiontitulocomparador = titulocomparador.position();   
	
					var tituloamortizacion = $(".tabla.amortizacion h3");
					var positiontituloamortizacion = tituloamortizacion.position();
					
					var titulocalculo = $(".tabla.calculo_monto h3");
					var positiontitulocalculo = titulocalculo.position();    
					
				
   					 $(window).scroll(function() {
					    
						var width = $(window).width();	
						if(width <= 549) { 
						
							var windowpos = $(window).scrollTop();
							
						
					
							if (windowpos >= positiontituloamortizacion.top) {
								tituloamortizacion.addClass("stick");
								
							} else {
								tituloamortizacion.removeClass("stick"); 
	
							}
							if (windowpos >= positiontitulocalculo.top) {
								titulocalculo.addClass("stick");
								
							} else {
								titulocalculo.removeClass("stick"); 
							
							}
							
						
						
						}
						
									
					});	
					
			
		
				
			/*---barra share del home------*/
				 var share = $('.share ');
    
    $(window).scroll(function () {
        if ($(this).scrollTop() > 650) {
            share.fadeIn();
        } else {
            share.fadeOut();
        }
    });
	
	
	
	
					 
    //--------> Tab
        $('.head-tab a').click(function(){
            $('.head-tab a').removeClass('active');
            $(this).addClass('active');
            $('.tab').removeClass('active');
            $('#tab'+$(this).attr('data-div')).addClass('active');
        });
        

	
    	
        
		    $(window).resize();

			
		$( ".formulario h6").unbind('click', function() { alert("That tickles!") });
	
	   $('.sexo li label').click(function(){
		    $('.sexo li label').removeClass("active")
	   		$(this).toggleClass("active");
	   });
	
					
		 var _idCount = 1;
			$('.add').click(function() { 
				  var clone =  $('.beneficiarios > ul:last').clone(true).insertBefore(".btn_modal.open_lightbox").hide().fadeIn("slow");		
				   clone.find('input[id^=textboxDueDate]')
                 .attr('id','textboxDueDate_'+_idCount)
                 .removeClass('hasDatepicker').addClass("datepicker")
                 .datepicker()
				 _idCount++;
			});	

		 
			
			// Como hacer esto dinamicamente  
			
	       $( ".nivel  .ui-chkbox.ui-widget" ).addClass(function( index ) {
				return "chck-" + index;
		  });
	       $( ".nivel  .ui-selectmanycheckbox label" ).addClass(function( index ) {
				return "chck-" + index;
		  });
	       
	    
	       $('label.chck-0').appendTo($('div.chck-0'));
	       $('label.chck-1').appendTo($('div.chck-1'));
	       $('label.chck-2').appendTo($('div.chck-2'));
	       $('label.chck-3').appendTo($('div.chck-3'));
	       $('label.chck-4').appendTo($('div.chck-4'));

	       
	       
	       $('.menu_aviso_legal ul li a').removeClass("ui-commandlink");
	       $('.menu_aviso_legal ul li a:eq(0)').addClass("active");
	       
	       $('.content_tabs div:eq(0)').show();
	       $('.menu_aviso_legal ul li a').click(function(){
				clase = $(this).attr('class');
				$('div.'+clase).hide()
				.siblings("div").slideUp("slow");
				$('div.'+clase).slideDown("slow");
				$(".menu_aviso_legal ul li a").removeClass("active");
				
				
				
				
				
				$(this).addClass("active");	
	   });  
	    
	      
	      
  
    

}); 


 simulador = false;
function validacion_iniciarsesion() {
    
	   var usuario = $(".abrirsesion_email").val();
	   var password =$(".abrirsesion_password").val();   
	   $('.error_inicio').empty();	
	   $('.error_inicio').html("El usuario " + usuario +" "+ " no existe" );
	  // $('.error_inicio').append("Contraseña incorrecta" );
	  
	  
}



function returnlogin(xhr, status, args) {
	console.log("mensaje: " + args.mensaje);
	if (args.satisfactorio) {
		console.log("regreso satisfactorio");
		$("#CMD_iniciasesion").trigger("click");
	} else {
		$('.loader').hide();
		$('.calculadora').hide();
		$("#warningUser").show();
		console.log("regreso mal");
		
	}
}
function muestraRes(xhr, status, args) {
	 $('.bloque_resultados.simulador').slideDown();	
	 $('.loader').hide();
		$('.calculadora').hide();
		 simulador = true;
}





function columnas(){
	var classes = ['col_1', 'col_2', 'col_3', 'col_4'];
	$('.bloque_resultados.simulador ul.fila > li').addClass(function(i, c) {
	return classes[i % classes.length];
	});
}


function error(){
	alertify.error("Te falta llenar campos que son obligatorios"); 
	return false; 
}


function confirmar(){
	//un confirm
	alertify.confirm("<p> llenado todos los campos solicitados. Si deseas enviar, presiona aceptar. Si deseas corregir algun campo dale click en aceptar. </p>", function (e) {
		if (e) {
			alertify.success("Enviaste la información del formulario");
		} else { alertify.error("Puedes corregir la información  del formulario");
		}
	}); 
	return false
}



		 	  






