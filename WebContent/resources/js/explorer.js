				$(document).ready(function(){ 
							   $("input, textarea").keyup(function(){
								  
										 $(this).css({
												color : "#000"
											});
									
								});
							   $("input, textarea").blur(function(){
								  
									   if ($(this).val().length==0 ) { 
										 $(this).css({
												color : "#999"
											});
									
									 }
								});
							
							   $("input, textarea").each(function (index, input) {
								   $(this).focus(function(){
								 // if ($(this).prev('.validatorClass').val().length==0) {
									// console.log("holi")
								//  }
								   }); 
								   								   
							   }); 
							});