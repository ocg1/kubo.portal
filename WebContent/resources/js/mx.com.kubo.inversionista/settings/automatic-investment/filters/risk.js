console.log("risk.js");

function init_risk(input)
{
	var flagRisk = $(input).attr("id").split(":")[1];
	
	panel_risk_toggle(flagRisk);
	
	console.log("Risk.init_risk() = " + flagRisk);
}

function panel_risk_toggle(flagRisk)
{
	var GENERAL = 0;
	var DETALLE = 1;
	
	var risk_type = parseInt(flagRisk);
	
	switch(risk_type)
	{
		case GENERAL:
			$("#dvRiskDetail").hide();
			$( "#tbGeneralRisk").show();						
		break;
		
		case DETALLE:
			$("#dvRiskDetail").show();
			$("#tbGeneralRisk").hide();							
		break;
		
		default: break;
	}
}

function initViewRisk()
{			
	var flagRisk = $("input[name=flagRisk][value=0]'").is(":checked")	
	
	var type = $("input[name=flagRisk]").attr("id").split(":")[1];
	
	console.log("Risk.initViewRisk() = " + type);
	
	panel_risk_toggle(type);
	
/*	
	if(flagRisk)
	{		
		$("#dvRiskDetail").hide();
		$( "#tbGeneralRisk").show();
		
	}else{
		
		$("#dvRiskDetail").show();
		$("#tbGeneralRisk").hide();		
	}	
*/	
}

function getStringRisk()
{				
	var cadenaChk = "";
	
//	var flagRisk = $("input[name=flagRisk][value=0]'").is(":checked"); 
//	
//	var type = $("input[name=flagRisk]").attr("id").split(":")[1];
	
	
	
	var type = ( $("#tbGeneralRisk").is(":visible") ? "0" : "1" );
	
	var risk_type = parseInt(type);
	
	console.log("Filters.getStringRisk(): " + risk_type);
	
	if(risk_type < 1)
	{			
		if($("#chkA").is(":checked"))
		{
			cadenaChk = cadenaChk + "'A',";
		}
		
		if($("#chkB").is(":checked"))
		{
			cadenaChk = cadenaChk + "'B',";
		}
		
		if($("#chkC").is(":checked"))
		{
			cadenaChk = cadenaChk + "'C',";
		}
		
		if($("#chkD").is(":checked"))
		{
			cadenaChk = cadenaChk + "'D',";
		}
		
		if($("#chkE").is(":checked"))
		{
			cadenaChk = cadenaChk + "'E',";
		}
		
		if($("#chkF").is(":checked"))
		{
			cadenaChk = cadenaChk + "'F',";
		}
		
		if($("#chkG").is(":checked"))
		{
			cadenaChk = cadenaChk + "'G',";
		}
		
	} else {
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItem").each(function()
		{			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 )
				{
					strCadena += ",";
				}
				
				strCadena += "'" + $(this).attr("id") + "' ";
				item_num_risk++;			
			}				
		});
		
		if( strCadena.trim().length == 0 || item_num_risk == 0 )
		{
			alert( "Seleccione al menos un tipo de riesgo ");
			cadenaChk = strCadena = "";
		}
		
		cadenaChk = strCadena;		
	}
	
	console.log("Filters.getStringRisk(): " + cadenaChk);
		
	return cadenaChk;
}