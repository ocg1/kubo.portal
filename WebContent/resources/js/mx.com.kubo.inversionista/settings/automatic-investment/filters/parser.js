console.log("risk.js");

function buildQueryFilter2()
{
	console.log("Filters.buildQueryFilter(): ");
	
	scrollea = true;
/*	
	var cadStatus = getStringStatus();
*/	
	var cadRisk   = getStringRisk();
	var cadTerm   = getCadForTermChk();
	
	var cadgen = "";
	
	if($("#chk1").is(':checked') || $("#gender_Hombre").hasClass("itemCheck") ) 
	{ 
		 cadgen += "1";
	}
			
	 if($("#chk2").is(':checked') || $("#gender_Mujer").hasClass("itemCheck")) 
	 { 
		 
		 if( cadgen.length > 0)
		 {
				cadgen += ",";
		 }
		 
		 cadgen += "2";
	 }
	 
/*	 
	 $('#cadena3').val(cadStatus != "" ? cadStatus.substring(0,cadStatus.length - 1) : "");
*/	 
	 $('#cadena1').val(cadRisk   != "" ? cadRisk.substring(0, cadRisk.length - 1) : "");
	 $('#cadena2').val(cadTerm   != "" ? cadTerm : "");
	 $('#cadenaGender').val(cadgen);
	 
	 var strCadenaDest ="";
	 var item_num_destiny = 0;
	 
	 $(".clsItemDest").each(function()
	{			
		if($(this).hasClass("itemCheck"))
		{
			if( item_num_destiny != 0 )
			{
				strCadenaDest+=",";
			}
			
			var destinyChar = $(this).attr("id");
			
			strCadenaDest += destinyChar.replace("purpose_", "");
			
			item_num_destiny++;			
		}				
	});
		
		$("#destiny_str").val( strCadenaDest );
		$("#destiny_str_val").val( strCadenaDest );
		
		
		console.log(" >   cadRisk = " + cadRisk);
		//console.log(" > cadStatus = " + cadStatus);
		console.log(" >   cadTerm = " + cadTerm);
		console.log(" >    cadgen = " + cadgen);
	 
	 if(cadRisk.length > 0)
	 {
		 //cadStatus.length > 0 || 
		 
		if(cadTerm.length > 0 || cadgen.length > 0)
		{
			console.log("updateByFiltering.click():");
			
			//$('#updateByFiltering').trigger("click");	
			
			$('#save-automatic-investment').trigger("click");			
		}
	 }
}

function getStringStatus()
{		
	var cadStatus =  "";
	
	$(".statusclss").each(function( index )
	{
		
		if($(this).is(":checked"))
		{
			var str = $(this).attr("id").split("_")[1];
			
			cadStatus = cadStatus + "'" + str + "',";
		}		
	});
	
	console.log("Filters.getStringStatus(): " + cadStatus);
	
	return cadStatus;	
}