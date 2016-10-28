console.log("editor-ingresos-egresos.js");

var IncomesExpenses = window.IncomesExpenses || {};

$(document).ready(function()
{
	IncomesExpenses.init_access_CONFIG();								
});		


$(window).load(function() 
{
	alertasCentrarH();	
});

function alertasCentrarH() 
{
	var content = $('.centrar_h');
	var width = $(window).width();
	
	content.css(
	{
		position:'absolute',
		left: ($('.content').width() - content.outerWidth()) / 2
		//top: ($(window).height() - content.outerHeight())/2
	});		
}

$(window).resize(function() 
{
	var resizeId2;
	
	clearTimeout(resizeId2);
	
	resizeId2 = setTimeout(alertasCentrarH, 100);
});

IncomesExpenses.init_access_CONFIG = function()
{		
	var user_agent = navigator.userAgent;
	
	var browser_width  = $(window).width();
	var browser_height = $(window).height();
	
	var access_CONFIG = browser_width + "::" + browser_height + "::" + user_agent;
	
	console.log("IncomesExpenses.init_access_CONFIG(): " + access_CONFIG);
	
	$("#init-access-CONFIG").val(access_CONFIG).trigger("click");
};

IncomesExpenses.init_access_on_complete = function(xhr, status, args)
{
	var init_access_OK = args.init_access_OK;		
	
	console.log("IncomesExpenses.init_access_on_complete(): " + init_access_OK);
};

IncomesExpenses.calculator_oncomplete = function(xhr, status, args)
{
	var calculator_OK = args.calculator_OK;
	var expense_id    = args.expense_id;
	
	console.log("calculator_oncomplete = " + calculator_OK);
	console.log(" > expense_id = " + expense_id);
	
	//$("div#expense_id_" + expense_id).find("input").focus();
};

function funcWaitIncome()
{
	$("#dvTotalIncomeRes").hide();
	$("#dvTotalIncomeWait").show();
	
	return true;
}

function funcWait()
{
	$("#dvTotalExpensesRes").hide();
	$("#dvTotalExpensesWait").show();
	
	return true;
}

IncomesExpenses.change_income_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	var ammount_modified = args.ammount_modified;
	
	console.log("IncomesExpenses.change_income_oncomplete: ");
	console.log(" > update_OK = " + update_OK);
	console.log(" > ammount_modified = " + ammount_modified);
	
	$("#dvTotalIncomeWait").hide();
	$("#dvTotalIncomeRes").show();
	
	$("#update-lstIncometb").trigger("click");
};

IncomesExpenses.change_expense_oncomplete = function(xhr, status, args)
{
	var update_OK        = args.update_OK;
	var ammount_modified = args.ammount_modified;
	var expense_id       = args.expense_id;
	
	console.log("IncomesExpenses.change_expense_oncomplete: ");
	console.log(" > update_OK  = " + update_OK);
	console.log(" > expense_id = " + expense_id);	
	console.log(" > ammount_modified = " + ammount_modified);	
	
	$("#dvTotalExpensesWait").hide();
	$("#dvTotalExpensesRes").show();
	
	//inicializaModal();
	
	$("#update-lstExpensestb").val(expense_id).trigger("click");
};
	
IncomesExpenses.show_other_income = function()
{	
	$("#show-other-income").trigger("click");
};

IncomesExpenses.show_detail_income = function()
{

	$(".detail_income_bussines").fadeIn();
	
	var object = $("#dvContDetailIncome");
	
	object.slideToggle("slow");			
	object.find("tbody td").css("padding","0 0 10px 10px");
	object.find("thead th").css("padding","0");
	object.find("tbody td.subtitle").attr("colspan","3");

	 	
};

IncomesExpenses.init_percentage = function(user_id)
{
	console.log("IncomesExpenses.init_percentage(): " + user_id);
	
	var CLIENT = 1;
	var MESA   = 2;
	
	switch(user_id)
	{
		case CLIENT:
			$("#init_percentage").val("cliente").trigger("click");	
		break;
		
		case MESA:
			$("#init_percentage").val("mesacontrol").trigger("click");	
		break;
	}
	
	$("#porcentClientDis").css("display","block");
};

function close_percentage()
{
	$("#init_percentage").val("").trigger("click");
	
	$("#porcentClientDis").css("display","none");	
}

IncomesExpenses.porcentage_on_complete = function(xhr, status, args)
{
	var dispListPorc     = args.dispListPorc;
	var dispListPorcWait = args.dispListPorcWait;
	
	console.log("IncomesExpenses.porcentage_on_complete(): ");
	console.log(" > dispListPorc     = " + dispListPorc);
	console.log(" > dispListPorcWait = " + dispListPorcWait);				
};

IncomesExpenses.ammount_minus_oncomplete = function(xhr, status, args)
{
	var liquidezCliControl = args.liquidezCliControl;
	var consolidate = args.consolidate;
	
	console.log("IncomesExpenses.ammount_minus_oncomplete(): ");
	console.log(" > consolidate = " + consolidate);
	console.log(" > liquidezCliControl = " + liquidezCliControl);
};

function  closeDetailIncome()
{
	var object = $("#dvContDetailIncome");
	
	$(".velo").fadeOut();
	$(".detail_income_bussines").fadeOut();
	
	object.slideToggle("slow");	
}

IncomesExpenses.update_ammount_minus_oncomplete = function(xhr, status, args)
{
	var ammount = args.ammount;
	
	console.log("IncomesExpenses.update_ammount_minus_oncomplete(): ");
	console.log(" > ammount = " + ammount);
};


function format(input)
{	
    var num = input.value.replace("," , "");
    
    num = num.replace(",","");
    num = num.replace(",","");
    
	var ent;
	var dec;
	var point;
	
	console.log(num);
	
	if((num.split(".")).length > 2)
	{
		console.log("numero no valido");

		num = "";
		input.value = num;
		
	    return false;
	}
	
	if((num.split(".")).length > 1)
	{
		ent = num.split(".")[0];
	 	dec = num.split(".")[1];
	 	
	 	point = ".";
	 	
	} else {
		
		ent = num.split(".")[0];
		dec   = "";
		point = "";
	}
	
	if(!isNaN(ent))
	{
		//console.log(" -- cantidad -> "+ent);
		
    	if(ent.length > 3 && ent.length <= 6)
    	{
    		ent = ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    		
    	} else if(ent.length > 6 && ent.length < 9) {
    		
    		ent = + ent.substring(0,(ent.length - 6)) + "," + ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	}
    		    	
    	input.value = ent + point + dec;
    	
    	//console.log(" -- cantidad -> " + ent);
    	
	    return true;
	    
    } 
}


function format(e,place)
{
	var input = e;
    var num = input.value.replace(",","");
    num = num.replace(",","");
    num = num.replace(",","");
	var ent;
	var dec;
	var point;
	if((num.split(".")).length>2){
		alert("numero no valido");
		//num= num.substring(0,num.length-1);
		num = "";
		input.value = num;
	    return false;
	}
	if((num.split(".")).length>1){
		ent=num.split(".")[0];
	 	dec=num.split(".")[1];
	 	point=".";
	}else{
		ent=num.split(".")[0];
		dec="";
		point="";
	}
	if(!isNaN(ent)){
		if(place == 'simulator'){
			
			var vmax2 = $("#montoMax").val();
			var vmax=vmax2.split("\.")[0];
			
			//alert(ent+point+dec+" -- "+vmax);
		    if(parseFloat(num)>parseFloat(vmax)){
		    	
		    	if((vmax.split("\.")).length>1){
		    		ent=vmax.split("\.")[0];
		    	 	dec=vmax.split("\.")[1];
		    	 	point=".";
		    	}else{
		    		ent=vmax.split(".")[0];
		    		dec="";
		    		point="";
		    	}
		    	
		    	vmax = (vmax.split("."))[0];
		    	
		    	if(vmax.length>3&&vmax.length<=6)
		    		vmax= vmax.substring(0,(vmax.length -3))+","+vmax.substring((vmax.length -3),vmax.length);
		    	
		    	else if(vmax.length>6&&vmax.length<9)
		    		vmax= +vmax.substring(0,(vmax.length -6))+","+vmax.substring(vmax.length -6,(vmax.length -3))+","+vmax.substring((vmax.length -3),vmax.length);
		    	
		    	var sval = vmax+point+dec;
		    	
		    	input.value = sval;
		    	
			    //input.value = "50,000";
			    input.blur();
			    alert("La cantidad no debe superar los $"+sval);
		    	return false;
		    
		    }
		}
    
		console.log(" -- cantidad -> "+ent);
		
    	if(ent.length>3&&ent.length<=6)
    		ent= ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	else if(ent.length>6&&ent.length<9)
    		ent= +ent.substring(0,(ent.length -6))+","+ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	input.value = ent+point+dec;
    	
    	console.log(" -- cantidad -> "+ent);
    	
	    return true;
    }

    else{ 
    	
    	if ($("#dvContMenuREG").is(":visible")) {
    		alertify.alert('Solo se permiten numeros');
    	}else {
    		alert('Solo se permiten numeros');
    	}
    
	    num= ""; 
	    input.value = "";
	    return false;
    }
}

function showComment(component){
	if(Number($(component).val().replace(",",""))>0)
		$("#text-comment-for-consolidate").show();
	else
		$("#text-comment-for-consolidate").hide();
}


function validateChangeConsolidate(preValue)
{
	var elementAmmount=$("#txt-ammount-minus-consolidate");
	var elementReason=$("#txt-reason-consolidate");
	
	var numberAmmount=Number($(elementAmmount).val().replace(",",""));
	
	$(elementAmmount).parents(".edit-ammoun-minus").children('.formError').remove();
	
	if(elementAmmount.val()!="" && elementReason.val()!="" && numberAmmount > 0)
	{
		if(Number(preValue)!=numberAmmount)
		{
			elementAmmount.slideUp();
			return true;
		}else{
			$(elementAmmount).validationEngine('showPrompt', 'No se detectaron cambios','error','centerRight', true);
			elementAmmount.parent().find(".formErrorContent").css("width","190px");
			return false;
		}
	} else if(elementAmmount.val()==""){
		$(elementAmmount).validationEngine('showPrompt', 'Capture el monto','error','centerRight', true);
		elementAmmount.parent().find(".formErrorContent").css("width","190px");
		return false;
	} else{
		$(elementReason).validationEngine('showPrompt', 'Obligatorio','error','centerRight', true);
		elementReason.parent().find(".formErrorContent").css("width","190px");
		return false;
	}
}	

function changeValue(component,preValue)
{
	var element=$("#"+component.replace(/:/g, '\\:'));
	
	if(element.val()==preValue)
	{
		return false;
	}else{
		funcWaitBusDetails();		
		return true;
	}
}

function funcWaitBusDetails()
{
	$(".showTotalsProcess").show();
	$(".hideLabelTotal").hide();
}

function reloadDetailIncome()
{
		var object=$("#dvContDetailIncome");
		object.find("tbody td").css("padding","0 0 10px 10px");
	 	object.find("thead th").css("padding","0");
	 	object.find("tbody td.subtitle").attr("colspan","3");
	 	
		$("#update-lstIncometb").trigger("click");
}



function simOnStartVal(){
	console.log("request.js");
	if( validaMontoMin('ammountNeg','simulator') ){
		if( validaPlazo('termNeg') ){
			
			loader();
			var ent = $("#ammountNeg").val().replace(",","");
			var input = $("#ammountNeg");
			if(!isNaN(ent)){
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

function validaMontoMin(e,place){
	
	var input = $("#"+e);
	
    var num = (input.val()).replace(",","");
    num = num.replace(",","");
    num = num.replace(",","");
	var ent;
	var dec;
	var point;
	
	//alert(" -----------------------------");
	
	
	if(num.indexOf("\\.")!=(-1)){
		
		if((num.split("\\.")).length>2){
			alert("numero no valido");
			//num= num.substring(0,num.length-1);
			num = "";
			input.val(num);
		    return false;
		}
		
		if((num.split("\\.")).length>1){
			ent=num.split("\\.")[0];
		 	dec=num.split("\\.")[1];
		 	point=".";
		}else{
			ent=num.split("\\.")[0];
			dec="";
			point="";
		}
	}else{
		ent=num;
		dec="";
		point="";
	}
	
	var vmax2 = $("#montoMax").val();
	//var vmax=vmax2.split("\.")[0];
	var vmax=vmax2;
	// alert(vmax+" -- "+num);
	
		if(place == 'simulator'){
			
			var vmin2 = $("#montoMin").val();
			var vmin=vmin2.split("\.")[0];
			//alert("maximo: " + vmax+ "  minimo: " + vmin +" entero: "+ent);
			
		    if(parseFloat(ent)<parseInt(vmin) || parseFloat(ent)>parseInt(vmax) ) {
		    	//alert( vmax+" -- "+num+" -- "+vmin );
		    	
		    	if(  parseFloat(ent)>parseInt(vmax) ){
		    		vmin = vmax;
		    	}
		    	
		    	if(vmin.length>3&&vmin.length<=6)
		    		vmin= vmin.substring(0,(vmin.length -3))+","+vmin.substring((vmin.length -3),vmin.length);
		    	
		    	else if(vmin.length>6&&vmin.length<9)
		    		vmin= +vmin.substring(0,(vmin.length -6))+","+vmin.substring(vmin.length -6,(vmin.length -3))+","+vmin.substring((vmin.length -3),vmin.length);
		    	
		    	var sval = vmin+point+dec;
		    	
		    	input.val(sval);
		    	
			    //input.value = "50,000";
		    	//alert("num: "+ ent +"min: "+vmin);
		    	if(parseFloat(ent)<parseInt(vmin.replace(",","")) ) {
		    		if ($("#dvContMenuREG").is(":visible")) {
		    			alertify.alert("La cantidad debe ser mayor a los $"+sval);
		    		}else {
		    			alertify.alert("La cantidad debe ser mayor a los $"+sval);
		    		}
		    	}
		    	else if(  parseFloat(ent)>parseFloat(vmax) ){
		    		if ($("#dvContMenuREG").is(":visible")) {
		    			alertify.alert("La cantidad debe ser menor a los $"+sval);
		    		}else {
		    			
		    			alert("La cantidad debe ser menor a los $"+sval);
		    		}
		    	}
		    	//alert( vmax+" -- "+num+" -- "+vmin );
		    	
		    	
			    input.blur();
			    
		    	return false;
		    }
		}
    
    	if(ent.length>3&&ent.length<=6)
    		ent= ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	else if(ent.length>6&&ent.length<9)
    		ent= +ent.substring(0,(ent.length -6))+","+ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	input.val(ent+point+dec);
    	
    	//alert( "return true: "+input.val() );
    	
	    return true;   	
}

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

function loader()
{	
	$("#resultadosSimNeg").css("display","none");
	$("#loaderSimNeg").css("display","block");
	
	 return true;
}

function monto_negotiation_oncomplete(xhr, status, args)
{
	var montoNegotiation = args.montoNegotiation;
	
	console.log("monto_negotiation_oncomplete: " + montoNegotiation);		
	
	$("#modifcaCondiciones").trigger("click");
}

function showRes()
{	
	console.log("showRes()");
	
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}

function closeNegotiation () {

	$("#btnInitialize").click();
	$("#negotiationDv").slideUp("slow");

}

function confirmCondiciones002()
{
	$('.modificarCondiciones .btnGris').click(function()
	{				
		$(".modificarCondiciones").removeClass("show");
		$(".velo").fadeOut();
		
		return false;
	});
	
	$('.modificarCondiciones .btnNaranja').click(function()
	{	
		//displayMessageProcessing('msgprocessing', true);
		
		$(".modificarCondiciones").removeClass("show");
		
		$(".velo").fadeOut();
		
		$(" #negotiationDv .close_negotiation").trigger("click");		  
	});
	
	$(".modificarCondiciones").addClass("show");
	$(".velo").fadeIn();
	
	return false;	
}