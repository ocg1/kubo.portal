console.log("menu_item.js");





function verficar_menu_item_SELECT(element)
{		
	var menu_idItem   = element;
	var menu_anterior = $("#anterior").val();
	
	console.log("verficar_menu_item_SELECT(): " + element);
	console.log("> menu_idItem   = " + menu_idItem);
	console.log("> menu_anterior = " + menu_anterior);
	
	if(menu_idItem == "menu1" && menu_anterior == "edad_fuera_de_rango")
	{		
		$("#hdNext\\:siguienteBasicosReview").click();
		
	} /*else if(menu_idItem == "menu2" && menu_anterior == "menu1") {
		
		var boton_siguiente = $("div#nextCHBASICOS01").html();
		
		var boton_siguiente_INV = $("div#nextInv").html();
		
		console.log("boton_siguiente = " + boton_siguiente);
		console.log("boton_siguiente = " + boton_siguiente_INV);
		
		if(boton_siguiente_INV == null)
		{
			$("#hdNext\\:siguienteBASICOS").trigger("click");
			
		} else {
			
			$("#hdNext\\:nextInvestor").trigger("click");
		}
				
	}*/ else {
		
		return itemFunction(element);
	}
	
	return false;
}

function itemFunction(element)
{
	
	
	
	


	


	
	console.log("itemFunction(): "+ element);
	
	var elmnt = $("#"+element+"");
	var flag = false;
	
	console.log("menu.idItem: "   + elmnt.attr("id"));
	console.log("menu.anterior: " + $("#anterior").val());
	
	if($("#anterior").val() == "menu1" ||
	   $("#anterior").val() == "menu2" || 
	   $("#anterior").val() == "menu6")
	{
		if($("#anterior").val() == "menu6")
		{
			flag  = validateFileUpload("N");
			
		} else	{
			
			flag  = validateFields();
			console.log("\t validateFields(): " + flag);
		}
		
	} else {
		
		flag = true;
	}
	
	if( flag && $("#anterior").val() == "menu1" && $("#area").val() == "L" )
	{
		
		if(isKuboMail($("#thisMail").val()))
		{
			flag = false;
		}
		
		if(flag && !isValidZipCode )
		{			
			flag = false;
			delayWrongZipCode();		
		}
		
//	/*cambio que te envia a la parte de siguiente siempre y cuando seas acreditado */	$("#hdNext\\:siguienteBASICOS").click();
//		
//		flag = false;
	}
	
	//Valida si la edad esta fuera de rango
	
	if(edad_fuera_de_rango_flag && elmnt.attr("id") == "menu2" && $("#area").val() == "L" )
	{
		flag = false;
	}
	
	//Valida si la edad esta fuera de rango
	
	if( flag && $("#anterior").val() == "menu1" && elmnt.attr("id") != "menu2" && $("#area").val() == "I" )
	{
		
		
		flag = true;
			
	}
	
	if( flag && $("#anterior").val() == "menu1" && elmnt.attr("id") == "menu2" && $("#area").val() == "I" )
	{
		
		$("#hdNext\\:nextInvestor").click();
		flag = false;
			
	}
	
	if(flag)
	{
		 flag = displayMessageProcessing('msgprocessing',true);
		 
			if(elmnt.hasClass("menuItem"))
			{
			//$(".menuItem").click(function(){
		//		var flag = true;
				//alert($("#anterior").val());
		//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
		//			flag  = validateFields();
		//			//alert("regreso: "+flag);
		//		}
				
				if(flag)
				{
					$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
					elmnt.removeClass('menuItem').addClass('menuItemSel');
					$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
					var myId= "#"+elmnt.attr("id")+"ItemBar";
					var myIdPor= "#"+elmnt.attr("id")+"Porcent";
					var myIdPorAnt= $("#selectedPor").val();
					var antId = $("#selected").val();
					$(myId).removeClass('displayNone').addClass('displayBlock');
					$(antId).removeClass('displayBlock').addClass('displayNone');
					$(myIdPor).removeClass('porcent').addClass('porcentSel');
					$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
					$("#selected").val(myId);
					$("#selectedPor").val(myIdPor);
					$("#anterior").val(elmnt.attr("id"));
				}
			//});
			}
			
			else if(elmnt.hasClass("menuItem1")){
			//$(".menuItem1").click(function(){
		//		var flag = true;
				//alert($("#anterior").val());
		//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
		//			flag  = validateFields();
		//			//alert("regreso: "+flag);
		//		}
				if(flag)
				{
					$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
					$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
					elmnt.removeClass('menuItem1').addClass('menuItem1Sel');
					var myId= "#"+elmnt.attr("id")+"ItemBar";
					var myIdPor= "#"+elmnt.attr("id")+"Porcent";
					var myIdPorAnt= $("#selectedPor").val();
					var antId = $("#selected").val();
					$(myId).removeClass('displayNone').addClass('displayBlock');
					$(antId).removeClass('displayBlock').addClass('displayNone');
					$(myIdPor).removeClass('porcent').addClass('porcentSel');
					$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
					$("#selected").val(myId);
					$("#selectedPor").val(myIdPor);
					$("#anterior").val(elmnt.attr("id"));
				}
			//});
			} else if(elmnt.hasClass("menuItem1")) {
			//$(".menuItemSel").click(function(){
				
		//		var flag = true;
				//alert($("#anterior").val());
		//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
		//			flag  = validateFields();
		//			//alert("regreso: "+flag);
		//		}
				if(flag)
				{
				
					$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
					$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
					elmnt.removeClass('menuItem').addClass('menuItemSel');
					var myId= "#"+elmnt.attr("id")+"ItemBar";
					var myIdPor= "#"+elmnt.attr("id")+"Porcent";
					var myIdPorAnt= $("#selectedPor").val();
					var antId = $("#selected").val();
					$(myId).removeClass('displayNone').addClass('displayBlock');
					$(antId).removeClass('displayBlock').addClass('displayNone');
					$(myIdPor).removeClass('porcent').addClass('porcentSel');
					$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
					$("#selected").val(myId);
					$("#selectedPor").val(myIdPor);
					$("#anterior").val(elmnt.attr("id"));
				}
			//});
			}
	}
			
	return flag;
	
	

}