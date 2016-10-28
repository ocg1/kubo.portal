	$(document).ready(function(){ 
		// tabla de amortización

			// clases ayudas
			 var classes2 = ['txt-0', 'txt-1', 'txt-2', 'txt-3', 'txt-4'];
			 $('.txt ul li').addClass(function(i, c) {
				return classes2[i % classes2.length];
			 });
			// iconos pantalla proyectos 
			 var classes3 = ['icons-0', 'icons-1', 'icons-2'];
			 $('.datos ol li').addClass(function(i, c) {
				return classes3[i % classes3.length];
			 });
			// iconos pantalla proyectos mosaico
			 var classes4 = ['iconsb-0', 'iconsb-1',  'iconsb-2'];
			 $('.datos2  > ul  li').addClass(function(i, c) {
				return classes4[i % classes4.length];
			 });
			// encabezados pantalla proyectos lista
			$( ".lista ul.encabezado > li" ).addClass(function( index ) {
				return "encabezado-" + index;		
			});			
			var index = 0;
			var classes = ['uno', 'dos', 'tres', 'cuatro', 'cinco', 'seis', 'siete', 'ocho'];
			$('.lista ul.fila > li').addClass(function(i, c) {
				return classes[i % classes.length];
		     });	
			
			// filtros pantalla proyectos (columna izquierda)
			
			$(".filtros > ol li.ordenar input").click(function() {
				$(".filtros > ol li.ordenar ul").slideToggle();
				$(this).toggleClass("active");
			});

			$(".filtros > ol li.ordenar ul li a").click(function() {
				$(".filtros > ol li.ordenar ul").slideUp();
			});
			
			$('.filtros_mobile').click(function(){
				$('.col_lateral').slideToggle();	
			  }); 
			  $('.cerrar_mobile').click(function(){
					$('.col_lateral').slideUp();				
			   }); 
			 $( ".col_lateral div h3").click(function() {
					$(this).next("div").slideToggle();
					$(this).toggleClass("active");
			 });
			 $('.limpiar').click(function(){
				  $('.col_lateral .nivel label, .proposito ol li, .tipo_cliente ul li').removeClass("active");
				  $('.monto input[type="text"]').val("");
				  $('.sexo li').removeClass("active");
				  $('.edad label select').prop('selectedIndex',0);
					
		     });	
			// pantalla proyectos  mosaico bloques de proyectos
			 
			  $(".mosaico > div:odd").css("float" , "right");
			  $(".productos article:odd").css("float" , "right"); 
			// boton invertir bloques mosaico boton invertir 
			$('.btn_invertir').click(function(){
                     $(this).toggleClass('active');
                        if($(this).hasClass("active")) {

                            $(this).val("Seleccionado");
                        }else {
                                $(this).val("Selecciona");
                   }
			});		
			  // checkbox para selecciona proyecto de la pantalla mosaico
			 $('.checkbox').click(function(){
				  $(this).toggleClass("active");
			}); 
			   // lightbox ayudas
			
			  $( ".ayuda_1 li" ).addClass(function( index ) {
						return "a1-indicacion-" + index;
			  });
			   $( ".ayuda_2 li" ).addClass(function( index ) {
						return "a2-indicacion-" + index;
			  });
			  $( ".ayuda_3 li" ).addClass(function( index ) {
						return "a3-indicacion-" + index;
			  });
			  $( ".ayuda_4 li" ).addClass(function( index ) {
						return "a4-indicacion-" + index;
			  });
			  
			  $( ".ayuda_mobile li" ).addClass(function( index ) {
						return "a-m-" + index;
			  });
			  

			   $(".ayuda_content > div").hide();
			   $('div.ayuda_1').show();
			   $('.ayuda_lightbox ul li a:eq(0)').addClass("active");
			  
			  $('.ayuda_lightbox ul li a').click(function(){
					clase = $(this).attr('class');
					$('div.'+clase).hide()
					.siblings(".ayuda_content > div").fadeOut();
					$('div.'+clase).fadeIn();
					$(".ayuda_lightbox ul li a").removeClass("active");
					$(this).addClass("active");	
			  }); 
			  $('.mosaico > div > h6').click(function(){
					var width = $ (window).width();	
					if (width <= 800) { 
						$(".ayuda_mobile").appendTo($(this).parent());
						$('.ayuda_mobile').fadeIn();
					}
				 });
				  $('.open_ayuda').click(function(){
					  var width = $ (window).width();	
					  $('#light5').fadeIn();	
					  if (width >= 1025) { 
							 $(".ayuda_mobile").hide();
					  }
					  if (width <= 1024) {
							$(".ayuda_mobile").show();
					  }		
				  }); 
				  $('.ayuda_mobile > a').click(function(){
						 $(this).parent().fadeOut();
				  });
				
				// tabla de amortizacion o tabla proyecto tipo lista
			 
			   $(".tabla div:even").css("background" , "#f3f3f3"); 
			   $(".tabla.amortizacion ol" ).clone().prependTo($(".tabla.amortizacion div"));

			   	// tipo de créditos version mobile 
			   
				  $('.productos article h2').click(function(){
						 var width = $(window).width();	
						
						 if (width < 650) { 
								if($(this).hasClass("active")) {
									   $(this).next("div").slideUp(500);
									   $(this).removeClass("active");
								} 
								else {
									   $('.productos article div').slideUp(500);
									   $(this).next("div").slideDown(500);
									   $('.productos article h2').removeClass("active");
									   $(this).addClass("active");
								
								}  
						}

				 }); 
				  
				  // menu lateral version mobile
			      
				 	 
	
				
				
				//simulador
				
				  $('.btn_simular').click(function(){
						  	 $('.comienza').hide(); 
						  	var width = $(window).width();	
							 if (width <= 1024) { 
							  	 $('html, body').animate({
								       scrollTop: ($('div.calculadora').offset().top -150 )
								  },500);
							 }
				  });
			 
			 
			 
				  var number = 1;
			 
				  // comparar y agregar comparaciones
				  
				 $('.btn_comparar').click(function(){
				 $( ".num_comparaciones ul li").removeClass("active");
					     var comparaciones = $('<li>'+number+'</li>');
        				 var comparacionactiva =$(".num_comparaciones ul ").append(comparaciones);
						 comparaciones.addClass("active")
					 
					 if($(this).hasClass("active")) {
						 $('.comienza').hide();
					
						 $( "#cuanto option:selected").each(function(e) {
							if($(this).val() == 'aceptado') { 
								 $('.comparador_2').show("slow",
								 	function(){ $('.comparador_2 h6 strong').show("slow",  
										 	function(){ $('.comparador_2 h6 span').delay(2000).addClass("active").parent().parent().delay(2000).hide("slow", 
													function(){ $('.bloque_resultados').show("normal", function(){ $('.bloque_simulador_comparador form h2').fadeIn("fast" 
														
													);
													
													$('.cuanto_pediste').fadeOut("fast");
													$('.btn_comparar').val("AGREGAR COMPARACIÓN");
													$('.cuanto_pagas span').html("1");
													$('.como_pagas span').html("2");
													$('.consigna').insertAfter($(".comparador_4"));
													$('.consigna').show();
													
													
													}   
														
													);
													
													}   
										    )
										 }
									);
										
									 
								
							    });
																
								 	
							}
							else if($(this).val()  == 'rechazado') {
						    	 $('.comparador_3').show("slow");
	
							}
							
					 });
					 $(this).removeClass("active");
					 } else {
					
					 }
  					 number++;
				 });	
				   	
				 // asignar la clase active a las comparaciones
				 $( ".num_comparaciones ul li") .click(function(){
					  $( ".num_comparaciones ul li").removeClass("active");
				  	  $(this).addClass("active");
					
			     });
	
				 // limpiar los campos del simulador y comparador
				  $('.bloque_simulador_comparador a').click(function(e){
							$('.bloque_simulador_comparador form ul li select').prop('selectedIndex',0);
			
				  });
						
				  // FORMULARIO ASIGNAR CLASE C_LEFT Y C_RIGH	
						// $('.formulario.tipo_1 article >  ul > li:even').addClass("c_left");
				        // $('.formulario.tipo_1 article >  ul > li:odd').addClass("c_right");

				// Pestallas verde fosforecente modulos de formulario
				        
						$('.formulario article:eq(0)').show();
						$('.formulario h6:eq(0)').addClass("active");
						$('.formulario h6:not(".punteada")').click(function(){ // para la funcionalidad del acordeon de los formularios
							$('html, body').animate({scrollTop:200}, 'slow');
							
							if($(this).hasClass("active")) {
							   $(this).next("article").slideUp();
							   $(this).removeClass("active");
							}
							else {
								  $('.formulario article').slideUp();
								  $(this).next("article").slideDown();
								  $('.formulario h6').removeClass("active");
								  $(this).addClass("active");
							
							} 
				 }); 
				
				// menu lateral formularios		
				  $('.menu_inscripcion h5').click(function(){
					  var width = $(window).width();	
						 if (width < 851) { 
							 $(this).next('ul').slideToggle();
							 $(this).toggleClass("active");
						 }
				 }); 
				  // radiobuttons
				   $('.formcheckbox ol li').click(function(){ 
				   		$(this).parent().find("li").removeClass("active");
					    $(this).toggleClass("active");
				
				 });

			// pantalla grafica y mis numeros	   
			$('.btns .mis_num').click(function() {
				$('.btns .mis_graficas').removeClass("active")
				$(this).addClass("active")
				$(".graficas").slideUp();
				$(".mis_numeros").slideDown();
			
         		
        	}); 
		
			$('.btns .mis_graficas').click(function() {
				$('.btns .mis_num').removeClass("active");
				$(this).addClass("active")
				$(".mis_numeros").slideUp();
				$(".graficas").slideDown();
         		
        	}); 
		
		   // cargar imagenes y eliminar imagenes cargadas para formularios
			
		   $(window).load(function(){
				/* image preview */
				$('.cargar_imagen').change(function(){
					var boton= $(this);
					var oFReader = new FileReader();
					oFReader.readAsDataURL(this.files[0]);
					console.log(this.files[0]);
					oFReader.onload = function (oFREvent) {
					boton.parent().find(".imagen_ife").addClass("active").html('<img src="'+oFREvent.target.result+'">');
				
					boton.parent().find(".borrar").removeClass("disable");
					boton.parent().find(".fake-file input").addClass("disable");
				
					};
				});

})
 	 $('.borrar').addClass("disable");
	 $('.borrar').click(function(e){ 
			$(this).parent().find(".imagen_ife img").remove();  
			$(this).parent().find(".imagen_ife").removeClass("active")
			$(this).parent().find(".fake-file input").removeClass("disable");
			e.preventDefault()
		    $(this).addClass("disable");

	}); 
	 
	 // aspecto de zebra	
		  $(".tabla_5 table tr:even td").css('background-color', 'white');
		 // acordeon aviso legal
		  
		
		  
		  // acordeon ley de transparencia 
		   $('.transparencia h3').click(function(){
			   $(this).next("article").slideToggle();
		   });
		  
		   // cargar iframe creo que aqui este no es necesario
		   $("button").click(function(){
			    var iframe = $("#myiFrame");
			    iframe.attr("src", iframe.data("src")); 
			});
		   

		   
		   // lightboxs de las alertas de preregistro
		   $(".close_email").click(function(){
			   $("#email4").addClass("requiredClass");
		   });
		   
		   
		   
		   
		   	/*
			$("#blor").focus(function(event){
				var x=$(this);
				x.css("color","#f00");
			 
			});

			$("#blor").blur(function(event){
			  var x=$(this);
			  x.css("color","#97C627");
			});
			*/
		   /* funcion en la que se verifica que un elemento tenga contenido
		   $(document).ready(function(){
			   if ($("#box3").text().length > 0) {
			     $('#third').show();
			   }                                           
			 });
		   */
		  
		
				 
				 


//--------> colocar en horizontal lista checkbox de la checkboxs del apartado nivel
			        
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
	

// tabs para contenidos como menu aviso legal, ley de transparencia etc		       
				      
				      
				       
				       // formulario de preregistro si acepta o no las condiciones, una vez llenado el formulario
				  
	 });
		


// Funciones del simulador
	simulador = false;
	
// loader gif, calculadora
	function muestraRes(xhr, status, args) {
		
		 $('.bloque_resultados.simulador').slideDown();	

		 
		 $('.loader').hide();
		 
		 $('.calculadora').hide();
			 simulador = true;
	}
	function showloader2(){
		
		if(	simulador == false ) { 
			$('.calculadora').show();
		}else {
			$('.loader').show();
		}
		return true;
	}
	
	// crear clases para las columnas del  simulador
	function columnas(){

		 $('html, body').animate({scrollTop:0}, 'slow');
		
			
		var classes = ['col_1', 'col_2', 'col_3', 'col_4'];
		$('.bloque_resultados.simulador ul.fila > li').addClass(function(i, c) {
		return classes[i % classes.length];
		});
		
		$('.regular a').click(function(){ 
	  		var width = $(window).width();	
	 	    if (width < 850) { 
	  	$(".bloque_resultados ol li a").removeClass("active");
			$(".bloque_resultados.simulador ul.encabezado li").removeClass("active");
		    $(".bloque_resultados.simulador ul.fila li").removeClass("active");						
			$(".col_encabezado_2").addClass("active");
		 	$(this).addClass("active");
		    $('.col_2').addClass("active"); 
			
			 
			 	$(".bloque_resultados ul.fila li.col_3, .bloque_resultados ul.fila li.col_4").hide();
				$(".bloque_resultados ul.fila li.col_2").show();
				$(".col_encabezado_3, .col_encabezado_4").hide();
				$(".col_encabezado_2").show();
		
			 }
	  });

	  $('.muy_bueno a').click(function(){ 
		var width = $(window).width();	
			 if (width <850) { 
		    $(".bloque_resultados ol li a").removeClass("active");
			$(".bloque_resultados.simulador ul.encabezado li").removeClass("active");	
			$(".bloque_resultados.simulador ul.fila li").removeClass("active");				
			$(".col_encabezado_3").addClass("active");
		 	$(this).addClass("active");
		    $('.col_3').addClass("active"); 
		
				$(".bloque_resultados ul.fila li.col_2, .bloque_resultados ul.fila li.col_4").hide();
				$(".bloque_resultados ul.fila li.col_3").show();
				$(".col_encabezado_2, .col_encabezado_4").hide();
				$(".col_encabezado_3").show();
			 }
	  });
	  
	  $('.maximo a').click(function(){ 
	  
	  var width = $(window).width();	
	  if (width < 850) { 
	 	    $(".bloque_resultados ol li a").removeClass("active");
			$(".bloque_resultados.simulador ul.encabezado li").removeClass("active");		
		    $(".bloque_resultados.simulador ul.fila li").removeClass("active");				
			$(".col_encabezado_4").addClass("active");
		 	$(this).addClass("active");
		    $('.col_4').addClass("active"); 
			
				$(".bloque_resultados ul.fila li.col_2, .bloque_resultados ul.fila li.col_3").hide();
				$(".bloque_resultados ul.fila li.col_4").show();
				$(".col_encabezado_2, .col_encabezado_3").hide();
				$(".col_encabezado_4").show();
			 }
	  });
		$(".div_scroll").scroll_absolute({arrows:true});
	}
	// funciones de  preregistro
	
	// validar promotor
	function validapromotor () {
		
		$("#cbo-promotor").val("");
		// $("#cbo-promotor").blur();
		
			  if($("#havePromotor\\:0").is( ":checked" )  ){
				  $(".no_promotor").show();
				  
				  
			  }else {
				  $(".no_promotor").hide(); 
				  if($("#selectRegistration_Razon").val() == 6){
					  $("#selectRegistration_Razon").val("");
				  }
			  }
			  
		  }
	// seleccionar como llegaste aquí
	function validaregistration () {
		
		if($("#selectRegistration_Razon").val() == 3){
			
			  if( $("#cbo-promotor").is(":hidden") ) {
				
					  $(".price_shoes").hide();
					  $(".otra_razon").hide();
					  $(".recomendado").show();
					  $(".Autocomplete").hide();
					 
			  } else {
				  
					 if ( $("#cbo-promotor").val() != "" ){
						  
						 if ( $("#cbo-promotor").hasClass("requiredClass")) {
							  $(".price_shoes").hide();
							  $(".otra_razon").hide();
							  $(".recomendado").show();
							  $(".Autocomplete").hide();
							  
						  }
						  else {
							  $(".price_shoes").hide();
							  $(".otra_razon").hide();
							  $(".recomendado").hide();
							  $(".Autocomplete").show();
							  
						  }
						  
					  } else {
						  $(".price_shoes").hide();
						  $(".otra_razon").hide();
						  $(".recomendado").show();
						  $(".Autocomplete").hide();
						  
					  }
			  }
	  }
	  else  if($("#selectRegistration_Razon").val() == 6){
		  $("#havePromotor\\:0").click();
	  }
	  else  if($("#selectRegistration_Razon").val() == 7){
		  $(".price_shoes").hide();
		  $(".recomendado").hide();
		  $(".Autocomplete").hide();
		  $(".otra_razon").show();

	  }
	  else  if($("#selectRegistration_Razon").val() == 8){
		  $(".price_shoes").show();
		  $(".otra_razon").hide();
		  $(".recomendado").hide();
		  $(".Autocomplete").hide();
		
	  }
	  else {
		  $(".price_shoes").hide();
		  $(".recomendado").hide();
		  $(".Autocomplete").hide();
		  $(".otra_razon").hide();
	  }
	  /*
		if( $("#selectRegistration_Razon").val() == 0){
			$("#selectRegistration_Razon").parent().addClass("requiredClass");
		}else {
			$("#selectRegistration_Razon").parent().removeClass("requiredClass");
		}
		*/	
	}
	
	