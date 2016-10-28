console.log("validacion.js");

function validateformPR() 
{
	
	$('#inp_recommended_search_input').removeClass('requiredClass');
	$('#cbo-promotor').removeClass('requiredClass');
	$('#pass').removeClass('requiredClass');
	$('#confpass').removeClass('requiredClass');
	$('#email').removeClass('requiredClass');
	$('#confemail').removeClass('requiredClass');
	
	var campos = document.signin.elements;
	var bandera = true;
	
	for(var i=0; i < campos.length; i++)
	{
		if(campos[i].tagName.toUpperCase() == 'SELECT' && $(campos[i]).hasClass("validatorClass") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value==null || campos[i].value=="0" || campos[i].value=="")
				{
					$(campos[i]).parent().addClass("requiredClass");					
					bandera=false;
					//alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					break;
					
				} else {
					
					$(campos[i]).parent().removeClass("requiredClass");
				}					
			}
		}
		
		if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass"))
		{
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value==""  ))
				{
					$(campos[i]).addClass("requiredClass");					
					bandera=false;
					//alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					
					break;
				} else {
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
	
	if( !bandera )
	{
		return false;
	}
	
	if( (parseInt($("#selectRegistration_Razon").val())==6) && ( $.trim( $('#cbo-promotor').val() )) == "" )
	{
		$('#cbo-promotor').addClass('requiredClass');
		//alert('#cbo-promotor');
		
		var element = ( $('#cbo-promotor').parent().parent() );
		var valTop = (element.offset().top ) - 180;
		
		$.scrollTo(valTop);
		
		return false;
	}
	
	//varEmail = true;
	
	if(varEmail && varPass && varConfEmail && varName && varPromo )
	{
	
		if(bandera)
		{
			if($('#recaptcha_response_field').val()=='')
			{
				//alert('#recaptcha_response_field');
				$.scrollTo('#recaptcha_response_field', 800, { axis:'y' });
				return false;
			}
			
			if( !($('#valInvestment').val() == 'true') )
			{
				bandera = validaPass();
			}				
		}
		
		if( !$("#cbo-promotor").is(":hidden") && $("#cbo-promotor").val() == "" )
		{
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if( !$("#inp_recommended_search_input").is(":hidden") && $("#inp_recommended_search_input").val() == "" )
		{
			$('#inp_recommended_search_input').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#inp_recommended_search_input', 800, { axis:'y' });
			
			var element = ( $('#inp_recommended_search_input').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
	
		
		if(bandera)
		{
		
			//displayMessageProcessing('msgprocessing',false);
		 $('html, body').animate({scrollTop:0}, 'slow');
			 $("#conCondiciones").fadeIn();
			 $('.cargando').hide();
			 $('.declaracion_portal').show();
			 
			 $('.container1').show();
		 	 
		
			 
		  $(".div_scroll").scroll_absolute({arrows:true});
		    
			// $("#btnAviso").click();
			
			return bandera;
			
		} else {
			
			return bandera;
		}
		
	} else {
		
		if(!varEmail)
		{
			$('#email').addClass('requiredClass');
			
			//alert('#email');
			//$.scrollTo('#email', 800, { axis:'y' });
			
			var element = ( $('#email').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if(!varPass)
		{
			
			var element = null;
			
			if( $("#pass").val() == "" )
			{
				
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
		
		if(!varConfEmail)
		{
			$('#confemail').addClass('requiredClass');
			//alert('#confemail');
			//$.scrollTo('#confemail', 800, { axis:'y' });
			
			var element = ( $('#confpass').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if(!varName)
		{
			$('#name').addClass('requiredClass');
			//alert('#name');
			//$.scrollTo('#name', 800, { axis:'y' });
			
			var element = ( $('#name').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if(!varPromo)
		{
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}				
	}	
}