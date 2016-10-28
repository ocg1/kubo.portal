function showRes()
{
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}

function loader()
{				
	$("#resultadosSim").css("display","none");
	$("#loaderSim").css("display","block");
	
	 return true;
}

function simOnStartVal()
{			
	console.log("menu.js");
	
	if( validaMontoMin('ammount','simulator') )
	{
		//alert("regresa true");
		loader();
		var ent = $("#ammount").val().replace(",","");
		var input = $("#ammount");
		if(!isNaN(ent)){
		 	/* if(parseFloat(ent)>(50000)){
			    input.value = "50,000";
			    //input.blur();
			    showRes()
				return false;
			    
			 }else{ */
				 
				return true;
		 	// }
		}else{
			alert("Cantidad invalida");
			input.value="";
			//showRes()
			return false;
		}
	 
	}
	
}

function simOnStart(){
	
	if( validaMontoMin('ammount','simulator') ){
	
		loader();
		if(!validaPlazo('term')){
			//showRes();
			return false;
		}else{
			return true;
		}
	}
}


function validaRelationShipBasic(xhr, status, args)
{
	var res = args.isRelation;
	
	 // var res2 = args.sameProspect; // comentado hasta nuevo aviso RMB si se valida persona repetido para cleintes 
	var res2 = 'N';
	
	if(res == 'S')
	{
		closeMessageProcessing();
		setTimeout('showRelPersonCont();',600);
		return false;
	} else if(res2 == 'S') {
		closeMessageProcessing();
		setTimeout('showSamePersonCont();',600);
		return false;
	}else{
		
		$("#hdNext\\:siguienteBASICOSACTION").click();
		return true;
	}
	
	
}


function showRelPersonCont(){
	
	$('#dvRelPerson').show( "blind", 500 );
	
}

function showSamePersonCont(){
	
	$('#dvSamePerson').show( "blind", 500 );
	
}

function msgSameOff(){
	
	$('#dvSamePerson').hide( "blind", 500 );
	
}

function msgOff(){
	
	$('#dvRelPerson').hide( "blind", 500 );
	
}
