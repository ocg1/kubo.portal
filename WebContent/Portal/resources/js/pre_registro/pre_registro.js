console.log("pre_registro.js");

function listener_codigo_postal(xhr, status, args)
{
	console.log("listener_codigo_postal(): ");
	
	var codigo_postal_OK       = args.codigo_postal_OK;
	var zona_cobertura_ENABLED = args.zona_cobertura_ENABLED;
	
	console.log("> codigo_postal_OK       = " + codigo_postal_OK);
	console.log("> zona_cobertura_ENABLED = " + zona_cobertura_ENABLED);
	
	if(codigo_postal_OK)
	{
		// $("div#continuar_registro").show();
		$("#codigo_postal").removeClass("requiredClass");
		$("#codigo_postal").removeClass("vacio");
		alertaQuitar ('#codigo_postal');
	} else {
		
		// $("div#continuar_registro").hide();
	if($("#codigo_postal").val().length>=1){
		 alerta ("¡Código postal incorrecto!", "#codigo_postal");
	}else{
		alertaQuitar ('#codigo_postal');
	}
		
		$("#codigo_postal").addClass("requiredClass");
		$("#codigo_postal").addClass("vacio");
		// $("#codigo_postal").validationEngine('showPrompt', '*Código postal incorrecto!','error','centerRight', true);
	}

}

function init_lista_registration_reason()
{
	console.log("init_lista_registration_reason()");
	
	$("#selectRegistration_Razon option").each(function(index, value)
	{
	    var option_DISABLED = false;
	    	
	    if($(this).attr("disabled") == "disabled")
	    {
	    	option_DISABLED = true;
	    } 
	    
	    if (option_DISABLED) 
	    {	    	
	    	$(this).remove();
	    }
	});
}

function listener_email(xhr, status, args)
{
	var MEMBER_NOT_ACTIVE = 0;
	var MEMBER_ACTIVE     = 1;
	var MEMBER_NEW        = 2;
	var EMAIL_ERROR       = 3;
	
	var membership = args.warningMail;
	
	console.log("listener_email(): " + membership);
	
	switch(membership)
	{
		case MEMBER_NOT_ACTIVE:
			alertEmailConfimation();
			
			$('#email4').addClass('requiredClass');
			
			varEmail = false;
		break;
		
		case MEMBER_ACTIVE:
			alertEmailExits();
			
			$('#email4').addClass('requiredClass');
			
			varEmail = false;
		break;
		
		case MEMBER_NEW:
			
			$('#email4').removeClass('requiredClass');
			
			$('.confirmar').slideDown();
			$("#confemail").slideDown('fast');
			alertaQuitar ('#email4');
			varEmail = true;
		break;
		
		case EMAIL_ERROR:
			$('#email4').addClass('requiredClass');
			  if($("#email4").val().length>=1){
				  alerta ("Formato de correo incorrecto o el correo ya existe", "#email4");
			  }else{
					alertaQuitar ('#email4');
			  }
			// alertify.error('Formato de correo incorrecto');
			
			varEmail=false;
		break;
	}		
}

function alertEmailExits()
{
	var valeemail =  $('#email4').val(); 
	
	myapp = {active: false}; 
	
 	 $('.confirmar').slideUp('fast');
 	 $("#confemail").slideUp('fast');
 	
	 $('#confemail').val('');
	
	$("#light10").fadeIn("normal", function (){ $("#light10 .alerts").addClass("show");});
	
	$(".email").html(valeemail);
	
	var width = $(window).width();	
		
	console.log("alertEmailExits(): OK");
}

function alertEmailConfimation()
{
	myapp = {active: false}; 
	
 	 $('.confirmar').slideUp('fast');
 	$("#confemail").slideUp('fast');
 	 
	 $('#confemail').val('');
	var valeemail =  $('#email4').val(); 
	var correoSesion =  $('.correoSesion').text(); 
	
	$("div[id^='light']").fadeOut();
	$("#light11").fadeIn("normal", function (){ $("#light11 .alerts").addClass("show");});
	
	 
	 if ( typeof $("#email4").val() === "undefined" || $("#email4").val() == ""  ) {
		 $(".email").html(correoSesion);
		
	 }else {
		 $(".email").html(valeemail);
		 
	 } 
}