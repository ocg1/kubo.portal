console.log("risk.js");

function getStringRisk()
{		
	console.log("Filters.getStringRisk(): ");
	
	var cadenaChk = "";
	
	if( $("input[name=flagRisk][value=0]'").is(":checked")  )
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