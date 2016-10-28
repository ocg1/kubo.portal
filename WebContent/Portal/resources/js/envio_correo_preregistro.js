	var varEmail     = false;
	var varPass      = true;
	var varConfEmail = false;
	var varName      = false;
	var varPromo     = true;

        var RecaptchaOptions = {
                custom_translations : {
                        incorrect_try_again : "Palabras incorrectas vuelva a intentarlo."
                },
                lang : 'es', // Unavailable while writing this code (just for audio challenge)
                theme : 'white' // Make sure there is no trailing ',' at the end of the RecaptchaOptions dictionary
               /*  theme : 'custom',
                custom_theme_widget: 'recaptcha_widget' */
        };
        
        function preRegAction(){
        	
        	setTimeout(function(){ waitWindow()},500);
        	
        }
        
        function sendLogin(){
        	
        	window.location.href="access.jsf";
        }
        
        function changePass(){
        	
        	window.location.href="forgotpass.jsf";
        	
        }
        
        function waitWindow(){
        	$("#prAction").click();
    	
        }
        
        
        
        function prueba(){
        	$("#prAction").delay(500,function(){
        	
        		return true;
        	});
        	
        }
        
        function acepta(){
            $('html, body').animate({scrollTop:0}, 'slow');
			$("#valFlag").val("acepta");
			   $('.declaracion_portal').hide();
	    	   $('.cargando').show();
	    	   showloader();
	    	   $('#prAction').trigger("click");
	    
	    	 
		}

        function aceptaAcreditado(){
            $('html, body').animate({scrollTop:0}, 'slow');
			$("#valFlag").val("acepta");
			  // $('.declaracion_portal').hide();
	    	 //  $('.cargando').show();
	    	 //  showloader();
	    	   $('#prAction').trigger("click");
	    	    $(".cargandoNuevo").addClass("show");
		    	$(".velo").fadeIn();
	    	 
		}
        
		function noAcepta(){
		/*		
			alertify.confirm("¿Estás seguro que deseas abandonar tu registro?", function (e) {
				
				if (e) {
					$("#valFlag").val("NO ACEPTO");
					$('#conCondiciones').hide();
					
					return true;
				} else { 
					return false;
				}
				
			
			}); 
			*/
			$('.close_lightbox').trigger("click");
	    	

	    
		}
        
		function chechForward(){
		


			$("#mailforward").val($("#email").val());
			$("#mailforward").blur();
			$("#mailforward").blur();
			$("#mailforward").blur();
			$("#mailAction").click();
			
		}

		function changeMail(){
			
			displayMessageProcessing('msgprocessing',false);
			
			$("#frmMail\\:mailforwardChng").val($("#email").val());
			$("#frmMail\\:mailforwardChng").blur();		
			$("#frmMail\\:changeMailAction").click();
		}
        
		
		function returnSelRegitration(){
			
			$("#inp_recommended_search_input").val("");
			$("#kubo_member").val("");
			$("#inp_recommended_search_input").blur();
			$("#kubo_member").blur();
		}
		
		function validRequeredField( element ){
			if( $("#"+element).val() != "" ){
				$("#"+element).removeClass("requiredClass");
			}
			
			
			
			
		}

		
		
		
		
		