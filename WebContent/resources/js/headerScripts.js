
window.addEventListener('load', function () {
	$('.header').show();	
}, false);

function menuPrincipalHeader() {
	// event.stopPropagation();
	 var width = $(window).width();	
		if (width > 857) { 
			$(".menu_principal > div > ul").slideToggle();
		}
		$(".menu_principal article .fa-angle-down").toggleClass("fa-rotate-180");
}
var menuPincipalPrivado = function () {
 
	$(".menu_principal > div > ul li a" ).click(function() {
		 var width = $(window).width();	
		 if($(".kubo_container").hasClass("privado") && width > 857) {
			 $(".menu_principal > div > ul").slideUp();
		 }
	});
	
	
	$("body" ).click(function() {
		    var width = $(window).width();	
			if ($(".header_inner").hasClass("privado") && width > 857) { 
				$(".menu_principal > div > ul").slideUp();
				$(".menu_principal article .fa-angle-down").removeClass("fa-rotate-180");
			}
			
	});
	
	
	 
}


var menuPincipalPrivadoMobile = function (e) {

	  if(!  $('.menu_principal h6').hasClass("active")) {
		  $('.menu_principal').animate({right:"0"}, 300);
		  $('.menu_principal h6').addClass("active");
	  }else {
		  $('.menu_principal').animate({right:"-212px"}, 300);
		  $('.menu_principal h6').removeClass("active");
	  }
	
	 
}
var isClient = function () {
	
	 var width = $(window).width();	
	   if ($("#isClient").attr("value") == "true") {
		 
		   $(".menu_principal .perfilUsuario").html("Resumen de saldos");
	   }
	   if ($("#isClient").attr("value") == "false") {
		   $(".menu_principal .perfilUsuario").html("Mi solicitud"); 
		   
	   }
		if(!$("#isClient").length) {
			 $(".fechaAcesso").css("opacity", "1");
			 $(".fechaAcesso").show();
			 $(".privado .menu_principal article small").show();
		}
	   console.log($("#isClient").val());
}



var btnInversionistaGeneral = function () {
	var locacion = document.location.href; 
	setTimeout(function(){
		if(locacion.indexOf('tienda=true')>0) {  
			$('#projectListAction').trigger("click");
		}
 	},500);
	
}
var headerPrivado = function () {
	   if ($(".header_inner").hasClass("privado")) {
		
		   if ($(".content").is(':visible')) {
			   	$(".push_header").addClass("interior").show();
		   }
	   } 
	}



/*----- funciones 25 septiembre ------*/
function fechaResponsivo () {
	var width = $(window).width();
	if (width >= 851 ) { 
		
		if(!$(".contenedorFecha.actual .fechaAcesso.actual").length) {
			$(".menu_principal .fechaAcesso.actual").appendTo($(".contenedorFecha.actual"));
			$(".menu_principal .fechaAcesso.ultimo").appendTo($(".contenedorFecha.ultimo"));
		}
	}
	if (width <= 850) { 
		if(!$(".menu_principal .fechaAcesso.actual").length) {
			   $(".fechaAcesso.actual").insertAfter($(".menu_principal article"));
		   $(".fechaAcesso.ultimo").insertAfter($(".fechaAcesso.actual"));
		}
	}
	
}

var telefono = function (campoForm) {
	 $(campoForm+" small").replaceWith(function() { return $(this).contents(); });
			 $(campoForm+":contains('(55)')").html(function(_, html) {
			  return html.replace(/(\(55\))/g, '<small class="small">$1</small>');
	 });	
	   
}
	

$(window).load(function() {
	fechaResponsivo ();

});

$(window).resize(function() {
	fechaResponsivo();

});

$(document).ready(function(){
	$(document).click(function() {
		 var width = $(window).width();	
		 if (width <= 850 && $('.menu_principal h6').hasClass("active")) { 
			  $('.menu_principal h6').trigger("click");
		  
		 }
		
	});
	$(".menuSup").addClass("log");

	btnInversionistaGeneral();
	isClient();
	
	if($(".header_inner").hasClass("privado")) {
		$(".push_header").addClass("interior");
	}
	var afterAdded = false;
	/* menu principal privado */
	menuPincipalPrivado();
/********/ 
	telefono("a[href='tel:6269 0024']");
	
	
/*salir sesion */	
 $(".opciones_navegadores li" ).click(function() {
	 	$(".salir_sesion").click();
 });
 

 /*funciones pertenecientes al header privado */	
 headerPrivado();
 
	//incluir menu privado 
	$(".menu_principal div").append($("<ol></ol>"));	
	$(".menu_principal div ol").insertBefore($(".menu_principal div ul"));
	
	 $(".menu_principal ul li").addClass(function( index ) {
			return "menu-" + index;
	 });  
	 
	
	  
	  $(".banner_principal").append($("<div class='block'></div>"));
	  
	  
	//funcion cerrar boton menu principal mobile		
	 $(window).resize(function(event){
		 var width = $(window).width();
		
	 $(function(){ //scroll menu principal escritorio
		   	var lastScroll = 0;
			  $(window).scroll(function(event){
				  var st = $(this).scrollTop () ;
				  if (st >  lastScroll){
					 
						  $( ".header").hide();
				
						
						
					 
						
				  }
				  else {
					  if(!$("#dvContInvFlot").length) {
						  $( ".header").fadeIn(200);
					  }else {
						  $( ".header").show();
						
						
							
							
					  }
					   
				  }
				  if (st <= 150){
					   $( ".header").show();
				  }
				  lastScroll = st;   
				  
			  });
			});	
	 
	 
		if (width >= 650) { 
			
		}
		if (width >= 851) { 
		
			// botones de sesion privada	
		    $(".menu_principal > div ol li.usuario").appendTo('.header_inner.privado > ol');
	        $(".menu_principal > div ol li.ayuda").appendTo('.header_inner.privado > ol');
		    $(".menu_principal > div ol li.logout").appendTo('.header_inner.privado >  ol');
			$(".header_inner.privado ol li.usuario").insertBefore('.header_inner.privado ol li.n_folio');
			$(".header_inner.privado ol li.ayuda").insertAfter('.header_inner.privado ol li.bolsa');
			$(".header_inner.privado ol li.logout").insertAfter('.header_inner.privado ol li.n_folio');
			 //lightbox centrar 
		
			// menu social media
			 $('.media').insertAfter('.header_inner > ol ');
			 $(".fecha_actual_ultimo_acceso").insertAfter($(".header_inner > ol"));
		}
		if (width <= 850) { 
			// menu mobile, redes sociales, menu auxiliar
		
	
		
			// botones de sesion privada	
		    $(".header_inner.privado ol li.usuario").appendTo('.menu_principal > div ol');
	        $(".header_inner.privado ol li.ayuda").appendTo('.menu_principal > div ol');
			$(".header_inner.privado ol li.logout").appendTo('.menu_principal > div ol');
		}
		if (($(window).width() > 851) &&  afterAdded) {
			afterAdded = false;
	    }
		
		if (($(window) .width() < 850) && ! afterAdded) {
		    	 $('.media').insertAfter($('.menu_principal div ul'));
		      afterAdded = true;
		 }
	 });	
	 
	
		 
		 
		 
	

});

function questionsalir(){
	$(".velo2").fadeIn();
	$(".alerts.cerrarSesion").addClass("show");	 
	
}
function aceptaSalir() {
	cancelarSalir();
	$('#btn_cerrar').trigger("click");
	ga('send', 'event', 'Button', 'Kueski', 'Rechazados');
}
function cancelarSalir() {
	$(".velo2").fadeOut("5000");
	$(".alerts.cerrarSesion").removeClass("show");	
}
function mensajeInicialAcceso () {
	
	setTimeout(function(){
		 var width = $(window).width();

		 console.log("mensajeInicial");
		 if(width <= 850 && $(".mensajeInicialAcceso").length ) { 
			 $(".mensajeInicialAcceso").addClass("show");    
			$(".velo2").fadeIn("slow");
			$("#MensajeVisto").trigger("blur");
		 }
	},300);
}
function mensajeInicialAccesoNuevo () {
	$(".mensajeInicialAcceso").addClass("show");    
	$(".velo2").fadeIn("slow");
}

function cerraAlert () {
	$(".velo2").fadeOut();
	$(".alerts").removeClass("show");
	$(".alertsComprobantes").removeClass("show");
	if($('.filaSeleccionada').length){
		$('html, body').animate({
		       scrollTop: ($('.filaSeleccionada').offset().top - 100)
		},1400);	
	}
}
function alertasCentrarH () {

	var content = $('.centrar_h');
	var width = $(window).width();
	content.css({
			position:'absolute',
					left: ($(window).width() - content.outerWidth())/2
					//top: ($(window).height() - content.outerHeight())/2
			});	
	var content2 = $('.centrar_horizontal');
		
		content2.css(
		{
			position:'absolute',
			left: ($(window).width() - content2.outerWidth())/2,
		
		});	
}

$(window).load(function() {
	alertasCentrarH ();

	
});

$(window).resize(function() {
	
	var resizeId2;
	 clearTimeout(resizeId2);
	 resizeId2 = setTimeout(alertasCentrarH, 100);
	 
});

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      var token =response.authResponse.accessToken;
      document.getElementById('token').innerHTML = 'Token: '+token;
      testAPI();
     
      
      
     
      console.log("response.status === 'connected");
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
      console.log("response.status === not_authorized");
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
      console.log("response.status === no esta logueado en facebook");
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '246197265768625',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.5' // use graph api version 2.5
  });

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
	  statusChangeCallback(response);
  });

  };
  function fb_login(btn){
	    $(btn).addClass("active");
	    FB.login(function(response) {

	    	statusChangeCallback(response);
	    	
	    }, {
	        scope: 'publish_stream,email'
	    });
	}
  
 
  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI(usrID) {
	   
	  console.log("entro testAPI");
	  
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', { locale: 'en_US', fields: 'name, email' }, function(response) {
      console.log(JSON.stringify(response));
      console.log('Successful login for: ' + response.name);
     // document.getElementById('boton').remove();
      document.getElementById('status').innerHTML =
        'Gracias por iniciar sesión, ' + response.name + '!'+ ' Tu correo es: '+response.email;
    });
    FB.api('/me/picture', function(response) {
      console.log(JSON.stringify(response));
      console.log(response.data.url);
     // document.getElementById('pic').innerHTML =
     //  '<img src="'+response.data.url+'" alt="foto">';
    });
    FB.api('/me', { locale: 'en_US', fields: 'name, email' }, function(response) {
      console.log(JSON.stringify(response));
      console.log(response);
      if($(".loginFacebook").hasClass("active")){
    	  setTimeout(function(){
    		  $("#emailFB").val(response.email);
      	      $("#emailFB").blur();
      	      $("#idFB").val(response.id);
      	      $("#idFB").blur(); 
      	      $("#logFac").trigger("click");
      	      
    	 	},500);
    	   console.log("response.email"+response.email);
    	   console.log("response.id"+response.id);
      }
      if(!$(".loginFacebook").hasClass("active")){
    	  console.log("no has dado click en el boton loginFacebook");
    	  
      }
      
      if($(".regisFB").hasClass("active")){
    	
    	
    	  registrateFacebook (response);
      }
      
      if(!$(".regisFB").hasClass("active")){
    	  console.log("no has dado click en el boton registrateFacebook");
      }
      if($(".vincularFacebook").hasClass("active")){
  
    	  vincularFacebook (response);
      }
      if(!$(".vincularFacebook").hasClass("active")){
    	  console.log("no has dado click en el boton vincularFacebook");
      }
    });
 
  }

  function registrateFacebook (response){
	  $(".loginFacebook").removeClass("active");
      $("#emailFB2").val(response.email);
      $("#emailFB2").blur();
      
      $("#idFB2").val(response.id);
      $("#idFB2").blur(); 
     
     
      
  }
  
  function validoIdFB (xhr, status, args){
	  var index =   $("#emailFB2").val().indexOf("@");
      var substring = $("#emailFB2").val().substr(0, index);
      var locacion = document.location.href; 
      if(args.isFBValid == true){
    	  
    	   	  $("#email4").val( $("#emailFB2").val());
	    	   
    	   	  if(substring.length >= 4){
			      $("#passTemp").val(substring);
	    	   	}else {
	    	        $("#passTemp").val(substring+"00000");
	    	   	}
    	   	  
    	   	  if($(".imagenEscritorio.inversionista").length){
    	   	  	  $("#selectRegistration_Razon").val("1");
        	   	  $("#selectRegistration_Razon").change();
    	   	  }
		      $("#email4").blur();
		      $("#email4").change();
		      $("#passTemp").keyup();
		      $("#passTemp").blur();
		    
		      setTimeout(function(){
		    	  $("#continuar_registro").trigger("click");
		      },400);
		  
		      setTimeout(function(){
			      	if(locacion.indexOf('selectedReg')>0) {  
			      		registrateEmail();
			      	}
			      	
			      	if($("#email4").val() == "" ) {  
			      		registrateEmail();
			      	}
		      },400);
		      
	  }else {
		  $(".regisFB").removeClass("active");
		  $(".bloqueRF").hide();
	  }
	  
  }
  
  
  
  function vincularFacebook (response){
	  console.log("entro al vincularFacebook");
	  setTimeout(function(){
		  console.log("entro al setimeOut vincularFacebook");
	   $("#idFB3").val(response.id);
	   $("#idFB3").blur();
	   console.log(response.id);
		},300);
	// $("#logFac2").trigger("click");
  }
  
  function regresarLogin() {
  	$(".noReconoce").fadeOut(); 
  	$(".bloque-FB").hide();
  }



  function usuarioCliente(args){
  	$(".usuarioLabel").hide();
  	$(".bloque-FB").hide();
  
  	

  	$("#email").css({
        "opacity": "0",
        "height": "0",
        "margin": "0",
        "padding": "0"
      });
  	
  	
  	$(".usuarioCliente").slideDown();
  	$(".noReconoce").fadeOut(); 
  	$(".contraKubo").html("Para ingresar usa tu contraseña <strong>segura de Kubo</strong><span onclick='expCont();'>?</span>");
  	
  	$(".nombreCliente").html(args.nombreCompleto);
  	$("#email").val(args.email_kubo);
  	$("#email").blur();
  	if(args.imgen_kubo == 'SIN_IMAGEN'){
  		console.log('SIN_IMAGEN');
  	}else{
  	  	$(".fotoPerfilCliente").attr("src", "https://www.kubofinanciero.com/Kubo/"+args.imgen_kubo);
  	}

  
  }


  function loader (){
  	  $(".loader").show();
  }
  function loader2 (){
  	  $(".loader2").show();
  }
  
  function removeloader (){
  	  $(".loader").hide();
  }
  function returnLoginFB(xhr, status, args) {
		$("#warningUser").hide();
	
  	console.log("logginStatus: "+args.logginStatus);
  	var logginStatus = parseInt(args.logginStatus);
  	$(".emailFBUser").html( $("#emailFB").val());
  	switch(logginStatus)
  	{
  		case 1:
  			console.log("click al boton");
  			$("#CMD_iniciasesion").trigger("click");
  			loader ();
  		break;
  		
  		case 2:
  			console.log("no encontrado");
  			$(".noReconoce").fadeIn(); 
  		break;
  		case 3:
  			console.log("contraseña segura");
  			usuarioCliente(args);
  		break;
  		case 4:
  			$("#warningUser").show();
  			console.log("sesion ocupada");
  		
  		break;
  		case 5:
  			
  			$("#warningUser").show();
  			console.log("sesion ocupada2");
  			// $(".cuentaBloqueada").fadeIn(); 
  		break;
  		default:
  			console.log("holi");
  	}
  	removeloader ();
  }

  function registroRapido() {
	  $("#emailFB").val();
	  $("#idFB").val();
  }


$(document).ready(function(){
	
		$('.ingresar.open_lightbox').click(function(){
		
			$(".regisFB").removeClass("active");

			$(".loginFacebook").show();
			$(".separacion").show();
			$("#emailFB").val("");
			$("#idFB").val("");
			$("#email").val("");
			$(".mensajeLogin").hide();

			  	$(".usuarioLabel").show();
			  	$(".bloque-FB").show();
			  
			  	

			  	$("#email").css({
			        "opacity": "1",
			        "height": "auto",
			        "padding": "8px 14% 8px 4%",
			        "margin": "0 0 14px"
			      });
			  	
			  	
			  	$(".usuarioCliente").hide();
			  	
			  	$(".contraKubo").html("Contraseña:");
		});
		preregistroInit();
		setTimeout(function(){
		
			console.log("#siteseal"+ $("#siteseal img").length);
		
			if ($("#siteseal img").length >1){
				$("#siteseal img:not(:eq(0))").hide();
			}
		},1200);
});

function terminosCondiciones() {	
	$(".terminosCondiciones").addClass("show");
	$(".veloBtnCerrar").fadeIn();
	$("div.close_lightbox").hide();
	if($(".abrir_sesion").is(":visible")){
		$(".abrir_sesion").css({
	        "opacity": "0",
	        "height": "1px",
	      });
	}
  
}

function cerrarAlert() {
	
	$(".alerts").removeClass("show");
	$(".veloBtnCerrar").fadeOut();
	$("div.close_lightbox").show();
	if($(".abrir_sesion").is(":visible")){
		$(".abrir_sesion").css({
	        "opacity": "1",
	        "height": "auto",
	 
	      });
	}
	
}


function expCont() {
	//$(".contraTooltip").fadeIn();
	//$(".veloInterno").fadeIn();
	
	alertify.alert("<p>Es la contraseña segura que te pedimos dar de alta al convertirte en cliente de Kubo.</p>" +
			"<p>Por tu seguridad y en cumplimiento a las normas vigentes, debemos solicitarte esta contraseña en cada acceso que realices, aunque hayas decidido logearte con facebook.</p>" +
			"<p>De esta forma aseguramos de mejor manera que nadie entre a tu cuenta sin autorización.</p>" +
			"");
}

function registrateEmail(btn) {
	$(".formRegis").addClass("open");
	$(".bloqueRF").hide();
	$(".registrateCorreo").hide();
}

function iniciarSesion() {
	$(".ingresar.open_lightbox").trigger("click");
	
	
}

var preregistroInit = function () {
	var locacion = document.location.href; 
	setTimeout(function(){
		if(locacion.indexOf('utm_source=FB')>0) {  
			$('.registrateFacebook').trigger("click");
		
		}
 	},1000);
	
}

function tagMananger () {
	/*
	(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
		new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
		j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
		'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
		})(window,document,'script','dataLayer','GTM-KD9SHG');  */
}
