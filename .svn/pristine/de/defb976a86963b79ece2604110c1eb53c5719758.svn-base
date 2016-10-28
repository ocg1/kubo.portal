function distribuirBeneficiarios_reset() {
	
	console.log("entro al reset beneficiarios");
	setTimeout(function(){	
		var numero_beneficiarios = $("#lstPercBenefi td input").length;
		console.log(numero_beneficiarios);		
		var primer_beneficiario = $("#lstPercBenefi table tr:first-child input").val(); 
		console.log(primer_beneficiario);
		var beneficiarios_mismo_monto = 0;
		
		$("#lstPercBenefi td input").each(function(){
			if($(this).val() == primer_beneficiario && $(this).val() != 0 ) {
				beneficiarios_mismo_monto = beneficiarios_mismo_monto + 1
			}
			console.log($(this).val());
		});
		
		console.log(beneficiarios_mismo_monto);
		
		if( numero_beneficiarios == beneficiarios_mismo_monto ) {
			$(".distribuir_benef").addClass("active");
			$(".distribuir_benef").html("Limpiar porcentaje");
			
		}else {
			$(".distribuir_benef").removeClass("active");
			$(".distribuir_benef").html("Distribuir equitativamente a beneficiarios");
			console.log("no distribuidos");
		}
	
	}, 500);
}

function distribuirBeneficiarios() {
	var numero_beneficiarios = $("#lstPercBenefi td input").length;
	var primer_beneficiario = $("#lstPercBenefi table tr:first-child input"); 
	
	var primer_beneficiario_valor = $("#lstPercBenefi table tr:first-child input").val(); 
 		primer_beneficiario_valor  = Number(primer_beneficiario_valor.replace(/[^0-9\.]+/g,""));
 	
	var ultimo_beneficiario = $("#lstPercBenefi table tr:last-child input"); 
	
	var distribucion_igualT = 100/numero_beneficiarios;
		distribucion_igualT = Math.floor(distribucion_igualT*100);
	var	distribucion_igual = distribucion_igualT/100;
		console.log("esta es la distribucion_igual--------------- "+distribucion_igual);
	
	if($(".distribuir_benef").hasClass("active")) {
		 borrar_beneficiario ();
	}else {
		console.log("numero_beneficiarios"+ numero_beneficiarios);

		$("#lstPercBenefi td input").each(function(){	
		  $(this).val(distribucion_igual);
		

			console.log($(this).val());
			$(this).blur();
			if($(this).is(ultimo_beneficiario)){
				var n = (numero_beneficiarios-1)*distribucion_igual;
				
				console.log("total menos uno -- "+n);
				var residuo = 100 - parseFloat(n); 	
				if(numero_beneficiarios < 10) {
					residuo = (Math.floor(residuo*100))/100;
				}else {
					residuo = (Math.round(residuo*100))/100;
				}
				console.log("este es el residuo"+ residuo );
				$("#lstPercBenefi table tr:first-child input").val(residuo );
				primer_beneficiario.blur();
				 Beneficiario.verificPersentage();
				
					 $("#total_suma").val(residuo+n);
				
			}else {
			
			}

		
		});  
 
		
		$(".distribuir_benef").addClass("active");
		$(".distribuir_benef").html("Limpiar porcentaje");
		
	}
	
	
}

function  borrar_beneficiario () {
	setTimeout(function(){	
		$("#lstPercBenefi td input").val("0");
		$(".distribuir_benef").removeClass("active");
		$(".distribuir_benef").html("Distribuir equitativamente a beneficiarios");
		$("#lstPercBenefi td input").each(function(){			
			$(this).blur();
		});
		Beneficiario.verificPersentage();
	}, 500);
}

