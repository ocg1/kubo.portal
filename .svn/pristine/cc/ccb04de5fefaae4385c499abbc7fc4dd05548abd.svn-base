console.log("simulador.js");

function simOnStartVal(ammount_id, term_id)
{
/*	
	console.log("simOnStartVal(): ");
	console.log("ammount_id = " + ammount_id);
	console.log("   term_id = " + term_id);
*/	
	
	if( validaMontoMin(ammount_id,'simulator') )
	{
		if( validaPlazo(term_id) )
		{			
			loader();
			var ent = $("#" + ammount_id).val().replace(",","");
			var input = $("#" + ammount_id );
			
			if(!isNaN(ent))
			{
			 	/*if(parseFloat(ent)>(50000)){
				    input.value = "50,000";
				    //input.blur();
				    showRes()
					return false;
				 }else{*/
					return true;
			 	//}
			}else{
				alert("Cantidad invalida");
				input.value="";
				//showRes()
				return false;
			}			
		}		
	}
}

Simulador.info_oncomplete = function(xhr, status, args)
{
	var purpose_id   = args.purpose_id;
	var ammount      = args.ammount;
	var term_id      = args.term_id;
	var frequency_id = args.frequency_id;
	
	console.log("simulatorInfoOnComplete(): ");
	
	if(purpose_id != undefined)
	{
		console.log("> purpose_id = " + purpose_id);	
	}
	
	if(ammount != undefined)
	{
		console.log("> ammount = " + ammount);	
	}
	
	if(term_id != undefined)
	{
		console.log("> term_id = " + term_id);
	}
	
	if(frequency_id != undefined)
	{
		console.log("> frequency_id = " + frequency_id);
	}
	
	//$("#modifcaCondiciones").trigger("click");
	//showRes();
}

Simulador.init = function() 
{
	$("div#ofertas-remote-command").find("a.init-simulation").trigger("click");
};

Simulador.simulation_oncomplete = function(xhr, status, args)
{	
	var FROM_SIMULATOR = 2;
	
	console.log("simulation_oncomplete(): OK");
	
	Preaprobador.init_panel(FROM_SIMULATOR);
}



