console.log("frequency.js");

function validaPlazo( idTerm )
{

	var term =  $("#"+idTerm).val();
	var freq =  $("#frecuProyect").val();
	
	if(parseInt(freq) == 1 ){//Semanal
		
		var vterm = $("#termMax").val();
		
		var vSemanas = (parseInt(vterm)/12) * 52;
		
		if(parseInt(term)>parseInt(vSemanas)){
			
			alert("El plazo no debe superar las " + vSemanas + " semanas");
			$("#"+idTerm).val( vSemanas );
			$("#"+idTerm).change();
			
			return false;
			
		}
	}else if(parseInt(freq) == 2 ){//Catorcenal
		
		var vterm = $("#termMax").val();
		
		var vCatorcenas = ( ( parseInt( vterm ) / 12 ) * 52 ) / 2;
		
		if(parseInt(term)>parseInt(vCatorcenas)){
			
			alert("El plazo no debe superar las " + vCatorcenas + " catorcenas");
			$("#"+idTerm).val( vCatorcenas );
			$("#"+idTerm).change();
			
			return false;
		}
		
	}else if(parseInt(freq) == 3 ){//Quincenal
		
		var vterm = $("#termMax").val();
		
		vterm = parseInt(vterm)*2;
		
		if(parseInt(term) > parseInt( vterm )){
			
			alert("El plazo no debe superar las " + vterm + " quincenas");
			$("#"+idTerm).val( vterm );
			$("#"+idTerm).change();
			
			return false;
		}
		
	}else if(parseInt(freq) == 4 ){//Mensual
		
		var vterm = $("#termMax").val();
		
		if(parseInt(term)>parseInt(vterm)){
			
			alert("El plazo no debe superar los "+vterm+" meses");
			$("#"+idTerm).val( vterm );
			$("#"+idTerm).change();
			
			return false;
		}
	}
	return true;
}