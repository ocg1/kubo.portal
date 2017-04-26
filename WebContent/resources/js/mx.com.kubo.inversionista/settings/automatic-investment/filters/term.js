console.log("risk.js");

function getCadForTermChk()
{			
	var itemActualInv = 0;
	var maxIdSel = "";
	
	$(".clsTerm").each(function()
	{		
		if( $(this).hasClass("itemCheck") )
		{			
			var this_id_2 = $(this).attr("id");
			
			var this_sec_2 = this_id_2.split("_")[1];
			
			if(parseInt(this_sec_2) > parseInt( itemActualInv ) )
			{				
				//console.log('idSel: '+this_id_2);
				itemActualInv = parseInt(this_sec_2);
				// maxIdSel = this_id_2.split("_")[0];
				
				maxIdSel = this_id_2;
				
				//console.log('idProcesado: '+maxIdSel);
				
			}								
		}
	});
	
	console.log("Filters.getCadForTermChk(): " + maxIdSel);
	
	return maxIdSel;
}