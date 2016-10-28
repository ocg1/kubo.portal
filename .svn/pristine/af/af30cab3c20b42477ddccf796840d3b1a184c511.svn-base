function loadEvent()
		{
		
			
			var radioone = $('input:radio[value=1]');
			var radiotwo = $('input:radio[value=2]');
			
			if($("#question-one").find(":radio").is(":checked"))
				
			{
				
				$("#section-security-question").slideDown();
				
			} else if($("#question-two").find(":radio").is(":checked")) {
				$("#section-confirmation-mail").slideDown();
			}
			
			radioone.click(function() 
			{				
				$("#section-security-question").slideDown();
				$("#section-confirmation-mail").slideUp();
				radiotwo.attr('checked', false);
			});
			
			radiotwo.click(function() {
				$("#section-confirmation-mail").slideDown();
				$("#section-security-question").slideUp();
				radioone.attr('checked', false);
			});
		}
		
		function validateOnlyPass()
		{
			if(validaPass2())
			{
				
				hideloader();
				// displayMessageProcessing('msgprocessing',false);
				return true;
			}else{
				return false
			}
		}
		
		function validatepass()
		{	
		
			if($("#boolQuest").val() == 'true' )
			{
				if(validaConfRes('resp2','confresp2'))
				{					
					if(validaConfRes('resp3','confresp3'))
					{						
						if($("#preg").val()  == $("#preg2").val() || 
						   $("#preg").val()  == $("#preg3").val() || 
						   $("#preg2").val() == $("#preg3").val())
						{
							
							 alertify.error("Por tu seguridad debes elegir preguntas diferentes.");
						
							return false;
							
						}else{
						
							if(validaPass2())
							{		
								
								// displayMessageProcessing('msgprocessing',false);
								
								return true;								
							}
						}
					}
				}
			
				return false;
				
			} else {
				
			
				return validateOnlyPass();
			}
		}
		
		function validaConfRes( idResp,  idConfResp )
		{			
			if( $('#'+idResp).val() != $('#'+idConfResp).val() )
			{
				 alertify.error("El valor de las respuestas diferente. Por favor revise sus respuestas.");
				$('#'+idResp).val("");
				$('#'+idConfResp).val("");
				$('#'+idResp).focus();
				
				return false;
			
				
			}
	
			return true;			
		}
		
		function validaExistPass(xhr, status, args)
		{
			if(args.existPass)
			{
				$('#dvMsg').html( args.message );
				$('#dvMsg').show();
			}else{
				$('#dvMsg').hide();
			}
		}
		
	
		
		function checkPass(xhr, status, args) {
			if(args.is_success) {
				$('#light1').fadeOut();
			
				$("#light12").fadeIn("normal", function (){ $("#light12 .alerts").addClass("show");});
				 $('html, body').animate({scrollTop:0}, 'slow');				
			}else {
				hideloader();
				
			}
		}
