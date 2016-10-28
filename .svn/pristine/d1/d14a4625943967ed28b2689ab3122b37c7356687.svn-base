function format_input_monto(input_text, place)
{
	format_input(input_text, place);
}

function format_input(e, place)
{
	var input;
	var num;
	var ent;
	var dec;
	var point;
	
	input = e;
	
    num   = input.value.replace(",","");
    num   = num.replace(",","");
    num   = num.replace(",","");
    

	
	if((num.split(".")).length > 2)
	{
		alert("numero no valido");
		
		num = "";
		input.value = num;
		
	    return false;
	}
	
	if((num.split(".")).length > 1)
	{
		ent   = num.split(".")[0];
	 	dec   = num.split(".")[1];
	 	point = ".";
	} else {
		ent   = num.split(".")[0];
		dec   = "";
		point = "";
	}
	
	if(!isNaN(ent))
	{
		if(place == 'simulator')
		{
			
			var vmax2 = $("#montoMax").val();
			var vmax  = vmax2.split("\.")[0];
						
		    if(parseFloat(num) > parseFloat(vmax))
		    {		    	
		    	if((vmax.split("\.")).length > 1)
		    	{
		    		ent = vmax.split("\.")[0];
		    	 	dec = vmax.split("\.")[1];
		    	 	point = ".";
		    	} else {
		    		ent   = vmax.split(".")[0];
		    		dec   = "";
		    		point = "";
		    	}
		    	
		    	vmax = (vmax.split("."))[0];
		    	
		    	if(vmax.length > 3 && vmax.length <= 6)
		    	{
		    		vmax = vmax.substring(0,(vmax.length -3)) + "," 
		    			 + vmax.substring((vmax.length -3),vmax.length);
		    	} else if(vmax.length > 6 && vmax.length < 9)
		    	{
		    		vmax = + vmax.substring(0,(vmax.length -6))
		    			 + "," + vmax.substring(vmax.length -6,(vmax.length -3))
		    			 + "," + vmax.substring((vmax.length -3),vmax.length);
		    	}
		    		
		    	
		    	var sval = vmax + point + dec;
		    	
		    	input.value = sval;
		    	
			    
			    input.blur();
			    
			    alert("La cantidad no debe superar los $" + sval);
			    
		    	return false;		    
		    }
		}
    
    	if(ent.length>3&&ent.length <= 6)
    	{
    		ent = ent.substring(0,(ent.length -3)) + "," 
    			+ ent.substring((ent.length -3), ent.length);
    		
    	} else if(ent.length>6&&ent.length < 9)
    	{
    		ent = + ent.substring(0,(ent.length -6)) + "," 
    		    + ent.substring(ent.length -6,(ent.length -3)) + ","
    		    + ent.substring((ent.length -3),ent.length);
    	}
    		
    	input.value = ent + point + dec;
    	
	    return true;
    } else { 
    	alert('Solo se permiten numeros');
	    num = ""; 
	    input.value = "";
	    
	    return false;
    }
}

function simOnStartVal()
{	
	console.log("simulador.js");
	if( validaMontoMin('ammount','simulator') )
	{

		loader();
		
		var ent   = $("#ammount").val().replace(",","");
		var input = $("#ammount");
		
		if(!isNaN(ent))
		{
		 	if(parseFloat(ent)>(50000))
		 	{
			    input.value = "50,000";
		//input.blur();
			    showRes();
				return false;			    
			 } else {				 
				return true;
		 	} 	
		} else {
			alert("Cantidad invalida");
			input.value="";
//			showRes();
			
			return false;
		} 
	}	
}

function simOnStart()
{	
	if(validaMontoMin('ammount','simulator'))
	{
		loader();
		
		if(!validaPlazo('term'))
		{
//			showRes();
			return false;
		} else {
			return true;
		}
	}
}

function loader()
{	
	$("#resultadosSim").css("display","none");
	$("#loaderSim").css("display","block");
	
	return true;
}

function showRes()
{	
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}

function validaMontoMinSim(e, place){
	return validaMontoMin(e, place);
}

function validaPlazoSim(idTerm)
{
	return validaPlazo(idTerm);
}

function validaMontoMin(e, place)
{
	//alert("validaMontoMin simulador.js");
	
	var input;
	var num;
	var ent;
	var dec;
	var point;
	
	input = $("#" + e);
	
    num = (input.val()).replace(",", "");
    num = num.replace(",", "");
    num = num.replace(",", "");

	if(num.indexOf("\\.") != (-1))
	{		
		if((num.split("\\.")).length > 2)
		{
			alert("numero no valido");
			
			num = "";
			
			input.val(num);
			
		    return false;
		}
		
		if((num.split("\\.")).length>1)
		{
			ent   = num.split("\\.")[0];
		 	dec   = num.split("\\.")[1];
		 	point = ".";
		} else {
			ent   = num.split("\\.")[0];
			dec   = "";
			point = "";
		}
		
	} else {
		ent   = num;
		dec   = "";
		point = "";
	}
	
	var vmax2 = $("#montoMax").val();
	//var vmax  = vmax2.split("\.")[0];
	var vmax  = vmax2;
	//alert("monto maximo: "+vmax);
	if(place == 'simulator')
	{		
		var vmin2 = $("#montoMin").val();
		var vmin  = vmin2.split("\.")[0];
			
	    if(parseFloat(ent) < parseInt(vmin) || parseFloat(ent) > parseInt(vmax)) 
	    {	    	
	    	if(parseFloat(ent) > parseFloat(vmax))
	    	{
	    		vmin = vmax;
	    	}
		    	
	    	if(vmin.length > 3 && vmin.length <= 6)
	    	{
	    		vmin = vmin.substring(0, (vmin.length -3)) +","
	    		     + vmin.substring((vmin.length - 3), vmin.length);
	    		
	    	} else if(vmin.length>6&&vmin.length < 9) {
	    		
	    		vmin = + vmin.substring(0, (vmin.length - 6)) + "," 
	    		       + vmin.substring( vmin.length - 6, (vmin.length - 3)) + "," 
	    		       + vmin.substring((vmin.length - 3), vmin.length);
	    	}		    		
	    	
	    	var sval = vmin + point + dec;
	    	
	    	input.val(sval);
	    				    
	    	if(parseFloat(ent) < parseInt(vmin)) 
	    	{
	    		alert("La cantidad debe ser mayor a los $" + sval);
	    		input.blur();
	    		return false;
	    		
	    	} else if( parseFloat(ent) > parseFloat(vmax)) {
	    		alert("La cantidad debe ser menor a los $" + sval);
	    		input.blur();
	    		return false;
	    	}		    	
		    			    	
		    
		    
	    	
	    }
	}
    
	if(ent.length > 3 && ent.length <= 6)
	{
		ent = ent.substring(0,(ent.length - 3)) + "," 
			+ ent.substring((ent.length - 3), ent.length);
		
	} else if(ent.length > 6 && ent.length < 9) {
		ent = + ent.substring(0,(ent.length - 6)) + "," 
		      + ent.substring( ent.length - 6, (ent.length -3)) + ", " 
		      + ent.substring((ent.length - 3), ent.length);
	}
		
	input.val(ent + point + dec);    	    	
	
    return true;   	
}

function validaPlazo(idTerm,idFrequency)
{
	var term =  $("#"+idTerm).val();
	var freq = null;
	
	if(idFrequency == null){
		freq =  $("#frecuProyect").val();
	}else{
		freq =  $("#"+idFrequency).val();
	}
	
	Console.log(""); 
	Console.log("*****************************************");
	Console.log("************SIMULADOR********************");
	Console.log("*****************************************");
	
	Console.log("");
	Console.log("Term: " +term +" frequency: "+freq);
	Console.log("");
	
	Console.log("*****************************************");
	
	Console.log("");
	
	//Semanal
	if(parseInt(freq) == 1 )
	{		
		var vterm    = $("#termMax").val();		
		var vSemanas = (parseInt(vterm) / 12) * 52;
		
		if(parseInt(term) > parseInt(vSemanas))
		{			
			alert("El plazo no debe superar las " + vSemanas + " semanas");
			
			$("#"+idTerm).val( vSemanas );
			$("#"+idTerm).change();
			
			return false;
			
		}
		
	} else if(parseInt(freq) == 2 ) { //Catorcenal
		
		var vterm       = $("#termMax").val();		
		var vCatorcenas = ((parseInt(vterm) / 12 ) * 52 ) / 2;
		
		if(parseInt(term) > parseInt(vCatorcenas))
		{			
			alert("El plazo no debe superar las " + vCatorcenas + " catorcenas");
			
			$("#"+idTerm).val( vCatorcenas );
			$("#"+idTerm).change();
			
			return false;
		}
		
	} else if(parseInt(freq) == 3 ) { //Quincenal
		
		var vterm = $("#termMax").val();
		
		vterm = parseInt(vterm)*2;
		
		if(parseInt(term) > parseInt( vterm ))
		{			
			alert("El plazo no debe superar las " + vterm + " quincenas");
			
			$("#"+idTerm).val( vterm );
			$("#"+idTerm).change();
			
			return false;
		}
		
	} else if(parseInt(freq) == 4 ){ //Mensual
		
		var vterm = $("#termMax").val();
		
		if(parseInt(term) > parseInt(vterm))
		{		
			alert("El plazo no debe superar los " + vterm + " meses");
			
			$("#"+idTerm).val( vterm );
			$("#"+idTerm).change();
			
			return false;
		}
	}else{
		return false;
	}
	
	return true;
}