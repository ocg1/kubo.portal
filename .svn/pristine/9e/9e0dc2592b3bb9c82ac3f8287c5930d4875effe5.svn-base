console.log("forgot_password.js");

function loadEvent()
{
	var radioone = $('input:radio[name=question-one]');
	var radiotwo = $('input:radio[name=question-two]');
	
	if($("#question-one").find(":radio").is(":checked"))
	{
		$("#section-security-question").show();
		
	} else if($("#question-two").find(":radio").is(":checked")) {
		$("#section-confirmation-mail").show();
	}
	
	radioone.click(function() 
	{				
		$("#section-security-question").show();
		$("#section-confirmation-mail").hide();
		radiotwo.attr('checked', false);
	});
	
	radiotwo.click(function() {
		$("#section-confirmation-mail").show();
		$("#section-security-question").hide();
		radioone.attr('checked', false);
	});
}

function validateOnlyPass()
{
	if(validaPass())
	{
		displayMessageProcessing('msgprocessing',false);
		return true;
	}else{
		return false;
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
					
					alertify.alert("Por tu seguridad debes elegir preguntas diferentes.");
					
					return false;
					
				}else{
				
					if(validaPass())
					{							
						displayMessageProcessing('msgprocessing',false);
						
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
		alertify.alert("El valor de las respuestas es diferente. Por favor revise sus respuestas.");
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
