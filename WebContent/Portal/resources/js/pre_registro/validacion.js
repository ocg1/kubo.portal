console.log("validacion.js");
$(document).ready(function() {


	$(".text_code").blur(function(event){
		pass_blur();
	});
	$(".pass_code").blur(function(event){
		pass_blur();
	});
	
	$(".text_code2").blur(function(event){
		pass_blur();
	});
	$(".pass_code2").blur(function(event){
		pass_blur();
	});

});

function pass_blur()  {
	if( $(".pass_code2, .text_code2").val().length>0 ) {
		if($("#confpass").val() == $("#pass").val() || $(".pass_code2").val() == $(".pass_code").val() || $(".text_code2").val() == $(".text_code").val()  ) {
			console.log($("#confpass").val());
			console.log($("#pass").val());
			$(".contrasena_segura span").hide();
			
			$("#confpass").removeClass('requiredClass');
			$(".text_code2").removeClass('requiredClass');
			$(".pass_code2").removeClass('requiredClass');
			
			
		}else {
			console.log($("#confpass").val());
			console.log($("#pass").val());
			$("#confpass").addClass('requiredClass');
			$(".text_code2").addClass('requiredClass');
			$(".pass_code2").addClass('requiredClass');
			$(".contrasena_segura span").show();
		} 
	}
}
function conf_blur()  {
	
}


function validar_pre_registro() 
{
	
	
	if($(".text_code").is(":visible") ) {
		$(".pass_show").trigger("click");
		$('#passHelp').hide();
	}
	if($(".text_code2").is(":visible") ) {
		$(".pass_show2").trigger("click");
		$('#passHelp').hide();
	}
	
	
	
	if($("#pass").val() == $("#confpass").val()) {
		/* $(".text_code2").removeClass("requiredClass");
		$(".pass_code2").removeClass("requiredClass");*/
	}
	
	

	
	$('#inp_recommended_search_input').removeClass('requiredClass');
	$('#cbo-promotor').removeClass('requiredClass');

	
	$('#email4').removeClass('requiredClass');
	$('#confemail').removeClass('requiredClass');
	
	var campos=document.signin.elements;
	var bandera=true;
	for(var i=0;i<campos.length;i++){
		if(campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("validatorClass") ){
			if(!$(campos[i]).is(":hidden")){
				if(campos[i].value==null || campos[i].value=="0" || campos[i].value==""){
					$(campos[i]).parent().addClass("requiredClass");					
					bandera=false;
					alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					break;
				}
				else
					$(campos[i]).parent().removeClass("requiredClass");
			}
		}
		
		if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass")){
			if(!$(campos[i]).is(":hidden")){
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value=="")){
					$(campos[i]).addClass("requiredClass");					
					bandera=false;
					alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					
					break;
				}
				else{
					if($("#codigo_postal").hasClass("requiredClass")) {
						$.scrollTo($("#codigo_postal").offset().top - 150);
						$("#codigo_postal").addClass("vacio");
						alertify.error("¡Código postal incorrecto!");
						return false;
					}
					$(campos[i]).removeClass("requiredClass");
				}
			}
		}
	}
	
	if( !bandera ){
		return false;
	}
	
	if($("#pass").hasClass("requiredClass")) {
		
		return false;
	}
	
	if($("#confpass").hasClass("requiredClass")) {
		if ($("#confpass").val() != $("#pass").val()) {
		return false;
		}
	}
	
	if( (parseInt($("#selectRegistration_Razon").val())==6) && ( $.trim( $('#cbo-promotor').val() )) == "" ){
		$('#cbo-promotor').addClass('requiredClass');
		//alert('#cbo-promotor');
		
		var element = ( $('#cbo-promotor').parent().parent() );
		var valTop = (element.offset().top ) - 180;
		
		$.scrollTo(valTop);
		
		return false;
	}
	
	if(varEmail && varPass && varConfEmail && varName && varPromo ){
	
		if(bandera){
			if($('#recaptcha_response_field').val()==''){
				//alert('#recaptcha_response_field');
				$.scrollTo('#recaptcha_response_field', 800, { axis:'y' });
				return false;
			}
			if( !($('#valInvestment').val() == 'true') )
				bandera = validaPass();
		}
		
		if( !$("#cbo-promotor").is(":hidden") && $("#cbo-promotor").val() == "" ){
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if( !$("#inp_recommended_search_input").is(":hidden") && $("#inp_recommended_search_input").val() == "" ){
			$('#inp_recommended_search_input').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#inp_recommended_search_input', 800, { axis:'y' });
			
			var element = ( $('#inp_recommended_search_input').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
	
		if(bandera){
			//displayMessageProcessing('msgprocessing',false);
			myapp = {active: false}; 
			 $('html, body').animate({scrollTop:0}, 'slow');
			 $('.cargando').hide();
			 $('.declaracion_portal').show();
			 
			 $('.container1').show();
		 	 $("#conCondiciones").fadeIn();
		
			 
		    $(".div_scroll").scroll_absolute({arrows:true});

			$("#btnAviso").click();
			
			return bandera;
			}
			
		else{
			
			
			
		}
		
	}else{
		
		if(!varEmail){
			$('#email4').addClass('requiredClass');
			//alert('#email4');
			//$.scrollTo('#email4', 800, { axis:'y' });
			
			var element = ( $('#email4').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varPass){
			
			
			var element = null;
			
			if( $("#pass").val() == "" ){
				
				$('#pass').addClass('requiredClass');
				element = ( $('#pass').parent().parent() );
				
			}else{
			
				$('#confpass').addClass('requiredClass');
				element = ( $('#confpass').parent().parent() );
				
			}
			//alert('#confpass');
			//$.scrollTo('#confpass', 800, { axis:'y' });
			
			
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		
		}
		if(!varConfEmail){
			$('#confemail').addClass('requiredClass');
			//alert('#confemail');
			//$.scrollTo('#confemail', 800, { axis:'y' });
			
			var element = ( $('#confpass').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varName){
			$('#name').addClass('requiredClass');
			//alert('#name');
				$.scrollTo('#name', 800, { axis:'y' });
			
			var element = ( $('#name').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varPromo){
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		
	}	
}