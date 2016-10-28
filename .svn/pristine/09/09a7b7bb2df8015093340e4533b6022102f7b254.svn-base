
	

			var myInterval;
			
			function iniInvest(){
				
				$("#cmdActionInv").trigger( "click" );
				
			}
			
			var flagInv		= false;
			var flagNoInv 	= false;
			
			function completeInv(){
				
				$("#dvProgressInv").fadeOut(500,function(){
					
					$("#dvResumenInv").fadeIn(500,function(){
						
						$(".invDesc").click( function(){
							
							$( "#dvInvDesc" ).toggle( "blind",function(){
								
								if( !flagInv ){
								
									cargaInversiones();
									
								}else{
									$("#dvWaitInv").hide();
									$("#dvInfInv$").show();
									
								}
								
							} );
							
						});
						$(".sumaDiasPonderados .invDesc").trigger("click");
						$(".noInvDesc").click( function(){
							
							$( "#dvNoInvDesc" ).toggle( "blind",function(){
								
								if( !flagInv ){
									
									cargaNoInversiones();
									
								}else{
									
									$("#dvWaitNoInv").hide();
									$("#dvInfNoInv$").show();
									$(document).ready(function(){
										
										diasPonderados ();
										
								});
								}
								
							} 
							
							);
							
						});
						
					});
					
				});
				
			}
			
		
			function diasPonderados () {
				var diasPonderadoSuma = 0;
				$(".investment_action tr.filaProyecto").each(function() {
						var filaProyecto = $(this) ;
						var totInv = $(".celda_monto_total_inv strong").text();
							totInv = Number(totInv.replace(/[^0-9\.]+/g,""));

						var invProy = filaProyecto.find(".invProy").text();
							invProy = Number(invProy.replace(/[^0-9\.]+/g,""));
						
						var diasInversion = filaProyecto.find(".diasInversion").text();
						 	diasInversion =  Number(diasInversion.replace(/[^0-9\.]+/g,""));
									
						var tasa  = filaProyecto.find(".tasa").text();
							tasa =  Number(tasa.replace(/[^0-9\.]+/g,""));
							
							var perInvTotal = invProy/totInv;
							var tasaPonderada = tasa*perInvTotal;
							var diasPonderado = diasInversion*perInvTotal;
							
							diasPonderadoSuma = diasPonderadoSuma + diasPonderado;
							
							console.log("diasPonderadoSuma"+diasPonderadoSuma);
							
							filaProyecto.find(".diasPonderados").html(diasPonderado.toFixed(1)+ " Días");
							
							$(".sumaDiasPonderados span").html(diasPonderadoSuma.toFixed(0)+ " Días");
							
							console.log("perInvTotal"+perInvTotal);
							console.log("tasaPonderada"+tasaPonderada);
							console.log("diasPonderado"+diasPonderado);
							filaProyecto.find(".tasa").html(tasa.toFixed(2)+ "%");
							
						});
						
					
					}
		
			function completaCargaInv(xhr, status, args){
			
				var arrayInv =eval('('+ args.invList +')');
				
				var strInv = ' <table class="investment_action" style="margin-left: auto;margin-right: auto;"> ' +
							  ' <tr>  ' +
							 // ' 		<th>Proyecto</th>  ' +
							  ' 		<th>kubo<br/>score</th>  ' +
							  ' 		<th>Inversión</th>  ' +
							  ' 		<th>Tasa</th>  ' +
							  ' 		<th>Días inversión</th>  ' +
						
							//  ' 		<th>Días ponderados</th>  ' +
							  ' </tr> ';
				
				var li = 0;
				
				for (target in arrayInv){
					 if(target!='remove'){
						 
						 var inversion=arrayInv[target];
						
						 li++;
						 
						 strInv += '<tr class="filaProyecto"> ' +
									
						 			//'   <td>'+ inversion.proyect_loan_id+'</td>' +
									
									'   <td> ' +
									'		<div class="dvRisk'+ inversion.kubo_score_a+'"> ' +
									'			<p class="statusLeft">'  + inversion.kubo_score_a+'</p> ' +
									'			<p class="statusRight">' + inversion.kubo_score_b+'</p> ' +
									'		</div> ' +
									'	</td> ' +
									
									'	<td class="invProy" > ' +
											
											inversion.ammountStr +
											
									'	</td> ' +
									
									'	<td class="tasa"> ' +
									
									inversion.tasa+ 
									'%'+
									' </td>'+
									'	<td class="diasInversion"> ' +
									
									inversion.dias_inversion+ 
									
									' </td>'+
									//'	<td class="diasPonderados"> ' +
									
									//'dias pond'+ 
									//'%'+
									//' </td>'+
									
									'</tr>' ;
								
					 }
				}
				
				
				
				strInv += '</table> ';
				
				if( li == 0 ){
					
					strInv = ' <table style="margin-left: auto;margin-right: auto;"> ' +
					  ' <tr>  ' +
					  ' 		<th style="padding-right: 35px;">Sin Proyectos Fondeados</th>  ' +
					  ' </tr> ' + 
					  '</ table>';
					
				}
				
				$("#dvInfInv").html( strInv );
				
				$("#dvWaitInv").fadeOut("slow",function(){
					$("#dvInfInv").fadeIn();
					$(".monto_total_inv").fadeIn();
				});
				
				
				 diasPonderados ();
			
			}
			
			
			
			function completaCargaNoInv(xhr, status, args){
				var arrayInv =eval('('+ args.invList +')');
				
				var strInv = ' <table style="margin-left: auto;margin-right: auto;"> ' +
							  ' <tr>  ' +
							  ' 		<th >Proyecto</th>  ' +
							  ' 		<th >Riesgo</th>  ' +
							  ' 		<th>Monto</th>  ' +
							  ' 		<th>Motivo</th>	' +
							  ' </tr> ';
				
				var li = 0;
				
				for ( target in arrayInv ){
					
					 if ( target != 'remove' ){
						 
						var inversion=arrayInv[target];
						
						li++;
						 strInv += '<tr> ' +
									
						 			'   <td>'+ inversion.proyect_loan_id+'</td>' +
									
									'   <td> ' +
									'		<div class="dvRisk'+ inversion.kubo_score_a+'"> ' +
									'			<p class="statusLeft">'  + inversion.kubo_score_a+'</p> ' +
									'			<p class="statusRight">' + inversion.kubo_score_b+'</p> ' +
									'		</div> ' +
									'	</td> ' +
									
									'	<td> ' +
											
											inversion.ammountStr +
											
									'	</td> ' +
									'	<td>'+inversion.motivo+'</td>' +
									'</tr>' ;
								
					 }
				}
				
				strInv += '</table> ';
				
				if( li == 0 ){
					
					strInv = ' <table style="margin-left: auto;margin-right: auto;"> ' +
					  ' <tr>  ' +
					  ' 		<th style="padding-right: 35px;">Sin Proyectos NO Fondeados</th>  ' +
					  ' </tr> ' + 
					  '</ table>';
					
				}
				
				$("#dvInfNoInv").html( strInv );
				
				$("#dvWaitNoInv").fadeOut("slow",function(){
					$("#dvInfNoInv").fadeIn();
				});
			}
			
			
			function cargaInversiones(){
				$( "#cmdCargaInv" ).trigger( "click" );
			}
			
			function cargaNoInversiones(){
				$( "#cmdCargaNoInv" ).trigger( "click" );
			}
			
			function updateInvest(){
				
				var porc = parseInt ( $("#porcentage").val() );
				
				if( porc == 100 ){
				
					myStopFunction();
					
				}else{
				
					$("#cmdUpdateInv").trigger( "click" );
					
				}
				
			}
			
			function returnInitInv(){
				
				myInterval = setInterval(function(){ updateInvest(); }, 500);
				return true;
			} 
			
			function myStopFunction() {
			    clearInterval(myInterval);
			    $("#cmdCompleteInv").trigger( "click" );
			}
			
			function showDivInvDesc(){
				$( ".dvInvDesc" ).toggle( "blind" );
			}
			
			

		

