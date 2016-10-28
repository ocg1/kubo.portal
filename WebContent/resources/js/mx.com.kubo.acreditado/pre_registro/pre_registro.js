var varEmail     = false;
var varPass      = true;
var varConfEmail = false;
var varName      = false;
var varPromo     = true;

var RecaptchaOptions = 
{
        custom_translations : 
        {
        	incorrect_try_again : "Palabras incorrectas vuelva a intentarlo."
        },
        lang : 'es', // Unavailable while writing this code (just for audio challenge)
        theme : 'white' // Make sure there is no trailing ',' at the end of the RecaptchaOptions dictionary
       /*  theme : 'custom',
        custom_theme_widget: 'recaptcha_widget' */
};
        
function preRegAction()
{
	setTimeout(function()
	{ 
		waitWindow();
		
	}, 500);
}

function sendLogin(){
	displayMessageProcessing('msgprocessing',true);
	
	window.location.href="access.jsf";
	
}
        
function changePass(){
	displayMessageProcessing('msgprocessing',true);
	window.location.href="forgotpass.jsf";
	
}

function waitWindow()
{
	$("#prAction").click();
	displayMessageProcessing('msgprocessing',true);
}

function prueba(){
	$("#prAction").delay(500,function(){
		displayMessageProcessing('msgprocessing',true);
		return true;
	});
	
}
        
function acepta(){
	$("#valFlag").val("acepta");
	// $.fancybox.close();
	
	$('.declaracion_portal').hide();
	   $('.cargando').show();
	   showloader();
	   $('#prAction').trigger("click");
}
function showloader(){
	$('.loader').show();
	return true;
}

function noAcepta(){
	$("#valFlag").val("NO ACEPTO");
	// $.fancybox.close();

	
	$('.close_lightbox').trigger("click");
	
	/*
	alertify.confirm("¿Estás seguro que deseas abandonar tu registro?", function (e) {
		
		if (e) {
			$("#valFlag").val("NO ACEPTO");
			$('.close_lightbox').trigger("click");
			
			return true;
		} else { 
			return false;
		}
	}); 
	*/
	
}

function chechForward(){
	
	displayMessageProcessing('msgprocessing',false);
	
	$("#frmMail\\:mailforward").val($("#email").val());
	$("#frmMail\\:mailforward").trigger("blur");
	$("#frmMail\\:mailAction").click();
	
}
		
function changeMail(){
	
	displayMessageProcessing('msgprocessing',false);
	
	$("#frmMail\\:mailforwardChng").val($("#email").val());
	$("#frmMail\\:mailforwardChng").blur();
	$("#frmMail\\:mailforwardChng").blur( );
	$("#frmMail\\:mailforwardChng").blur( );
	setTimeout(function(){ $("#frmMail\\:changeMailAction").click(); } , 1000 );
	
}
        
function returnSelRegitration()
{
	
	if( $("#selectRegistration_Razon").val() != 0)
	{
		$("#selectRegistration_Razon").parent().removeClass("requiredClass");
	}
	
	if($("#havePromotor\\:0").is( ":checked" )  )
	{
		
		if( $("#selectRegistration_Razon").val() == 3)
		{
		
			if( $("#dvMsgPromotor").is(":hidden") ) 
			{
			
				if( $("#cbo-promotor").val() != "" )
				{
				
					$("#dvAutocompleteRecommended").css("display","block");
					$("#dvTextRecommended").css("display","none");
					
				}else{
					$("#dvAutocompleteRecommended").css("display","none");
					$("#dvTextRecommended").css("display","block");
				}
			
			}else{
				$("#dvAutocompleteRecommended").css("display","none");
				$("#dvTextRecommended").css("display","block");
				
			}
			
		}else{
			
			$("#dvAutocompleteRecommended").css("display","none");
			$("#dvTextRecommended").css("display","none");
			
		}
	
	}else{
		if( $("#selectRegistration_Razon").val() == 6){
		
			$("#havePromotor\\:0").click();
			$("#havePromotor\\:0").change();
			
		}
		
		if( $("#selectRegistration_Razon").val() == 3){
		
			$("#dvAutocompleteRecommended").css("display","none");
			$("#dvTextRecommended").css("display","block");
			
		}else{
			$("#dvAutocompleteRecommended").css("display","none");
			$("#dvTextRecommended").css("display","none");
		}
	}
	
	$("#inp_recommended_search_input").val("");
	$("#kubo_member").val("");
	$("#inp_recommended_search_input").blur();
	$("#kubo_member").blur();
}
		
function validRequeredField( element )
{
	if( $("#"+element).val() != "" ){
		$("#"+element).removeClass("requiredClass");
	}
}

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
		$("#codigo_postal").parent().children('.formError').remove();
		$("#codigo_postal").removeClass("vacio");
		
	} else {
		
		// $("div#continuar_registro").hide();
		alertify.error("¡Código postal incorrecto!");
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
		
/*
function changeMailAction(){ 
	$("#frmMail\\:changeMailAction").click();
}
*/