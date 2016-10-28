
			var msg;
		
			function validaResp(){
				
			
				
				if( typeof marcamillion === 'undefined' ){
				
				}else
					if ( ($('#confPass2').val()).length>0 ){
				
						if( $('#pass2').val() != $('#confPass2').val() ){
							alert("El valor de los campos es diferente. Por favor vuelva a intentarlo.");
							$('#pass2').val("");
							$('#confPass2').val("")
							return false;
						}
						
					}
				return true;
				
			}
			function saveAction(xhr, status, args){
				if(args.saveAction){
					$("#clickAction").click();
					
				}else{
					$("#errorAction").click();
					$("#dvMsg").html( args.message );
					
				}
				
			}
			
			function showMsg(){
				//alert( msg )
				//$("#dvMsg").html(msg);
				$("#dvMsg").show();
				
			}
			
			function hideMsg(){
				$("#dvMsg").hide();
				$("#dvMsg").html("");
			}
			function showResp(xhr, status, args){				
				if(args.isvalid){
					$('html, body').animate({scrollTop:0}, 'slow');	
					$("#pnlButtonChange").show();	
					inicializaValores2 ();
					
					$(".formcheckbox.si_no").hide();
					$("#pnlContEmail input").removeClass("requiredClass");	
					$("#pnlContEmail input").val("");	
					$("article.si_contrasena").hide();	
					
					$("#dvButtonCheck").hide( "slow", function(){
						if ($(".checks input[value=1]").is(':checked')) {
					    	 $("#dvChangeMailCont .contrasena_modulo").hide();
					    	
					    }else {
					    	 $("#dvChangeMailCont .contrasena_modulo").show();
					    	
					    }
						$("#dvChangeMailCont").slideDown('slow');
					});
					$("article.no_contrasena").slideUp();
				}
				else{
					$("#pnlContEmail").hide();	
					$("#pnlButtonChange").hide();	
					$(".msgError").slideDown('slow',function(){
						$("#yourpass").addClass("requiredClass");
						setTimeout(function(){
								$(".msgError").hide( "slow");
								$("#yourpass").removeClass("requiredClass");
								
							}
							,3500);
					});
					
				}
				
			}