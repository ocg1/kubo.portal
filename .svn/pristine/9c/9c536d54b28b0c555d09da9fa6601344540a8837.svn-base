function validateformDeal() 
{
	var campos=document.frmCierre.elements;
	
	var campos = $("#cierre_general").find(".validatorClass");
	var bandera=true;
	for(var i=0;i<campos.length;i++)
	{
		if(campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("validatorClass") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value==null || campos[i].value=="0" || campos[i].value=="")
				{
					$(campos[i]).addClass("requiredClass");					
					bandera=false;
					$.scrollTo($(campos[i]), 800, { axis:'y' });
					
					break;
					
				} else {
					
					$(campos[i]).removeClass("requiredClass");
				}					
			}
		}
		
		if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass"))
		{
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value==""))
				{
					$(campos[i]).addClass("requiredClass");					
					bandera=false;
					$.scrollTo($(campos[i]), 800, { axis:'y' });
					break;
				} else {
					$(campos[i]).removeClass("requiredClass");
				}					
			}
		}
	}
	
	var AnswRadio=0;
	var radioNum=0;
	
	/*
	$(":radio").each(function()
	{
		if(!$(this).is(":hidden"))
		{
			if($(this).attr('checked'))
			{
				AnswRadio =(parseInt(AnswRadio)+1);
			}
			
			radioNum = parseInt(radioNum) +1;
		}
	});
	
	var radio =(parseInt(radioNum)/2);
	
	if(radio!= AnswRadio)
	{
		bandera=false;
		alert("¡Faltaron opciones por elegir!");
	}
	
	
		
	if($('#acSimple_input').val()=='' && !$('#acSimple_input').is(":hidden") )
	{
		$('#acSimple_input').addClass("requiredClass");
		bandera=false;
	}else{
		$('#acSimple_input').removeClass("requiredClass");
	}
	*/
	if(bandera)
	{
		return bandera;
		
	} else {
		//$.scrollTo('#scrollhere', 800, { axis:'y' });
		
		return bandera;
	}
	
	
	
	
}

function validateAmmount(){
	var monto =  $("#monto").val().replace("$","").replace(" ","").replace(",","");
	var montoMin = $("#montoMinCierre").val().replace("$","").replace(" ","").replace(",","");
	
	if(parseInt(monto)<parseInt(montoMin)){
		alert("Monto mayor al solicitado");
		return false;
	}
	return true;
	
}