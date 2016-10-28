$(document).ready(function()
			{
				ReferenciasPersonales.init_access_CONFIG();	
				
				
				
			});	
			
			
				function muestraAdd() {
					if( !$(".addReferencia").is(":visible") ){
						$(".addReferencia").slideDown( "slow");
						$("#agregaRef").hide();
					}else if( $(".addReferencia").is(":visible") ){
						$(".addReferencia").slideUp( "slow");
					}
					
				}
				
				
				
				function cancelaAdd(){
					if( $(".addReferencia").is(":visible") ){
						$(".addReferencia").slideUp( "slow");
						$("#agregaRef").show();
					}
					
				}
				
			
			
			function initEdit(){
				
				return true;
				
			}
			
			function closeRem(){
				
				$(".velo").hide();
				$(".velo").removeClass("show");
				
				$(".clsConfirmDelete").hide();
				$(".clsConfirmDelete").removeClass("show");
				
			}
			
			function closeEdit(){
				
				$(".velo").hide();
				$(".velo").removeClass("show");
				
				$(".clsEditRef").hide();
				$(".clsEditRef").removeClass("show");
			}
			
			function oncompleteEdit(){
				
				$(".velo").show();
				
				$(".velo").addClass("show");
				
				$(".clsEditRef").show();
				$(".clsEditRef").addClass("show");
				$('.clsEditRef').css({
					 position:'fixed',
					left: ($(window).width() - $('.clsEditRef').outerWidth())/2,
				});
				return true;
				
			}
			
		function oncompleteCargaRemove(){
				
				$(".velo").show();
				
				$(".velo").addClass("show");
				
				$(".clsConfirmDelete").show();
				$(".clsConfirmDelete").addClass("show");
				$('.clsConfirmDelete').css({
					 position:'fixed',
					left: ($(window).width() - $('.clsConfirmDelete').outerWidth())/2,
				});
				return true;
				
			}
		
		function saveAction(){
			$("#cmdSaveRefAction").trigger("click");
		}
		
		function updateAction(){
			$("#cmdUpdateRefAction").trigger("click");
		}
		function remAction(){
			$("#cmdRemRefAction").trigger("click");
		}
		
		function initVelo(){
			
			$(".velo").hide();
			$(".velo").removeClass("show");
			
			
		}

		$(document).ready(function(){	
			$(window).resize();
		 	$(window).resize(function(event){
				$('.clsConfirmDelete').css({
					 position:'fixed',
					left: ($(window).width() - $('.clsConfirmDelete').outerWidth())/2+ ""
				});
		 	});
		 });