

$(document).ready(function(){
	$(".sesion_expirada").stop().delay("2000").addClass("opacity");
	/*
	$("#codigo_postal").blur(function(event){	
		var codigoerror = $(".formErrorContent").text();
		 if(codigoerror.length>1){
				alertify.error(codigoerror);
				
				console.log(codigoerror);
			}
	}); 
	*/
	$(".formulario.tipo_1").stop().delay("2000").addClass("opacity");

	
	$('.key_enter').live("keypress", function(e) {
		 var key = e.which;
		
		 if(key == 13)  // the enter key code
		  {
			 $(this).blur(); 
			 $(this).closest(".block_form").find(".link-login").click();
			// $(this).parents(".block_form").eq(0).find(".link-login").click();
		    return false;  
		  }
	}); 
	//  variable para la version responsiva del menu social para su colocacion en version escitorio y telefono
	
	var afterAdded = false;
	
	// codigo para anclar una liga con un contenido
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
	//para agregarle la clase min a algunos elementos y poder asignarle el estilo background size que funcione en explorer 8
	$(".media ul li a, .dicen_inner div div fieldset  > ul li a, .header_inner ol li.bolsa a").each(function(){	
		 $(this).addClass("min");
	 }); 
    
  
				
			//incluir menu privado 
			$(".menu_principal div").append($("<ol></ol>"));	
			$(".menu_principal div ol").insertBefore($(".menu_principal div ul"));
			 // clases dinamicas menu principal
			 $(".menu_principal ul li").addClass(function( index ) {
					return "menu-" + index;
			 });      

			 // css para el titulo de los listados del mapa de sitio
			
			// colocar dinamicamente marcadores

		   
		  //boton menu principal mobile
		  $(".menu_principal").append($("<h6 class='open'></h6><h6 class='close'></h6>"));	
	
			//funcion abrir boton menu principal mobile
		  $('.menu_principal .open').click(function(){	
				$(this).hide();
				$('.menu_principal .close').show();
				$('.menu_principal').animate({right:"0"}, 300);
				$('.menu_principal .open').removeClass("active");
				$('.menu_principal .close').addClass("active");
				$('.menu_principal .open').hide();
		 });
	 	//funcion cerrar boton menu principal mobile
		 $('.menu_principal .close').click(function(){
				$(this).hide();
				$('.menu_principal .open').show();
				$('.menu_principal').animate({right:"-212px"}, 300);
				$('.menu_principal .close').removeClass("active");
				$('.menu_principal .open').addClass("active");					
		  });			
 		
    	$(window).resize(function(event){
    	
			var activaMenu = $(".menu_interior li .active").text();

   
     		$(".menu_interior h6").text(activaMenu);
			
			 
    		
    		 $(function(){ //scroll menu principal escritorio
				   	var lastScroll = 0;
					  $(window).scroll(function(event){
						  var st = $(this).scrollTop () ;
						  if (st >  lastScroll){
								$( ".header").hide();
						  }
						  else {
							    $( ".header").fadeIn(200);
						  }
						  if (st <= 150){
							   $( ".header").show();
						  }
						  lastScroll = st;  
						  
						  
					  });
					});	
			    
    		
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
		
		
		
	
			
//termina width < 550
			if (width >= 550) { 

			
			 $('.abrir_sesion').css({
				   	 position:'absolute',
                	left: ($(window).width() - $('.abrir_sesion').outerWidth())/2,
   			 });
			
			
		
			   
			}
			
			  if (width >= 770) { 
				  $(".menu_inscripcion ul").show();	
			  }
	 
			  if (width >= 770) { 
				 
					  // lightbox requisitos central modal
					 $('.lightbox_requisitos').css({
							 position:'absolute',
							left: ($(window).width() - $('.lightbox_requisitos').outerWidth())/2,
					 });	
				}
			

			if (width >= 650) { 
							
			
				$('.menu_interior  ul').show(); 
				
			}
			
			if (width >= 851) { 
			    
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

			     // lightbox info solicitud
				 $('.lightbox_info_solicitud ').css({
					   	 position:'absolute',
	                	left: ($(window).width() - $('.lightbox_info_solicitud').outerWidth())/2,
	   			 });
				// menu social media
				 $('.media').insertAfter('.header_inner > ol ');
				 $(".fecha_actual_ultimo_acceso").insertAfter($(".header_inner > ol"));
			}
				//termina width > 850
			if (width <= 850) { 
				  $(".fecha_actual_ultimo_acceso").insertAfter($("..header_inner.privado div > ol"));

				
				// menu mobile, redes sociales, menu auxiliar
				$('.ingresar, .requisitos').appendTo('.menu_principal > div');
				$('.ingresar, .requisitos').insertBefore('.menu_principal > div > ul');
				$('.menu_principal .open').show();
			
				// botones de sesion privada	
			    $(".header_inner.privado ol li.usuario").appendTo('.menu_principal > div ol');
		        $(".header_inner.privado ol li.ayuda").appendTo('.menu_principal > div ol');
				$(".header_inner.privado ol li.logout").appendTo('.menu_principal > div ol');
				
				//menu principal version mobilescroll down desaparece,scroll up aparece 
				 	
			}
			 // condiciones si el bloque social se coloque en su sitio sin que se repliquen indefimidamente
			 // si no solo lo haga una vez
				if (($(window).width() > 851) &&  afterAdded) {
					afterAdded = false;
			    }
				
				if (($(window) .width() < 850) && ! afterAdded) {
				    	 $('.media').insertAfter($('.menu_principal div ul'));
				      afterAdded = true;
				 }

			
			 if($(".menu_principal .open").is(':visible')) {
 			 	$('.menu_principal .close').hide();
	 		 }
	 		 if($(".menu_principal .close").is(':visible')) {
				 	$('.menu_principal .open').hide();
	 		 }
			
	 		 // script, caso solo acreditado
			 $(".prestamo h2 a").trigger("click");
	 	
		  });
	
	
		
	//lightboxs funcionalidades		
		$('.open_lightbox').click(function(){
			myapp = {active: false}; 
			$('#light' + $(this).attr('data-div')).fadeIn('slow');
			$(".recuperar").hide(); //aqui cierro recuperar
			 
			
	
			
		});
		$('.close_lightbox').click(function(){
			myapp = {active: true}; 
			$('.lightbox').fadeOut('slow');
			
			
		
			
			
	
	
			
			
		});

		



		    $("#light8 .close_lightbox, #light10 .close_lightbox, #light11 .close_lightbox").click(function(){
				   $("[id^=#light]").fadeOut();
				   $(".alerts").removeClass("show");
				   $("#email4").addClass("requiredClass");
				  
				   $("#pass2").val("");
				   validaPassColor();
				   validaPassColor2();
				   
			   });
		    $("#light10 .close_lightbox").click(function(){
		    	 var width = $(window).width();	
		    	if (width >= 1025) { 
		    		$("#email4").trigger("focus");
		    	}
				   
			   });
		    
		    $(".ingresar").click(function()
		    {
		    	var width = $(window).width();
		    	
		    	$('.abrir_sesion').show();

		    	
		    	if (width >= 1025) 
		    	{ 
		 
		        	$("#email").focus();
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
	   $('.error_inicio').html("El usuario " + usuario +" "+ " no existe" );
	  // $('.error_inicio').append("Contraseña incorrecta" );
	  
	  
}

function returnlogin(xhr, status, args) {
	console.log("mensaje: " + args.mensaje);
	
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
		
	}
}

function showloader(){
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
		 validaPassColor2();
	 }else {
		 emailtem  = $("#email4").val();
		 validaPassColor();
	 }
	 $("#mailforwardChng").val(emailtem);
	 $("#mailforwardChng").blur(); 
	 $("#mailforwardChng").blur(); 
	 $("#mailforwardChng").blur(); 

	 $(".formcheckbox.si_no").show();
	 $("#dvButtonCheck").show(); 
		var width = $(window).width();		
			

}


function  success_change() {
		$(".envio_exitoso").show();
		$(".elige_opcion").hide();

	}
	

	

function recuperarContrasena()
{	
	$(".recuperar").fadeIn();
	$(".abrir_sesion").fadeOut();
	$("#gral-validation input[type='password']").val("");
	
	$('.recuperar').css(
	{
	   	 position:'absolute',
	   	 left: ($(window).width() - $('.recuperar').outerWidth()) / 2,
	});
}





