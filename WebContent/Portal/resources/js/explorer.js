

				$(document).ready(function(){ 
				
			

							   $("input, textarea").keyup(function(){
								   if ($(this).attr("placeholder")) {
										 $(this).css({
												color : "#000"
											});
									 }
								});
							   $("input, textarea").blur(function(){
								   if ($(this).attr("placeholder")) {
									   if ($(this).val().length==0 ) { 
										 $(this).css({
												color : "#999"
											});
									   }
									 }
								});
							
					
					
							});
				
				
				