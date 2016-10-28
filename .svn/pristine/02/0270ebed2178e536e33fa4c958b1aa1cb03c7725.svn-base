console.log("js/general.js");
 
function checaemailElement(thisID){
	var element=document.getElementById(thisID);
	checaemail(element);
	
	
}

function checaemail(oField)
{
	var valor = oField.value;
	if(valor.length>0)
	{
	   if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valor))
	   {
		   return (true);
 	   }
	   else
	   {
		   alert("La dirección de email: " + valor + ", es incorrecta.");
		   return (false);
	   }
    }
    else
    {
		return (true);
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
		    			alert("La cantidad debe ser mayor a los $"+sval);
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
//funcion que construye un pop-up con contenido personalizado. (thisElementID= 'Id del elemento div a mostrar en el pop-up y si va ser scroll')
function displayMessageProcessing(thisElementID,withScroll) {
	/*ajustes gabriel*/
		$("#fancybox-wrap").removeClass("contenido_light");
		$("#fancybox-content").css("min-height", "inherit")
	/*ajustes gabriel*/
		$.fancybox({
			'width' : 310,
			'padding' : '0',
			'margin' : '0',
			'autoDimensions':false,
			'transitionIn' : 'none',
			'transitionOut' : 'none',
			'speedIn' : '20',
			'speedOut' : '10',
			'modal' : true,
			'type' : 'inline',
			'scrolling' : 'no',
			'overlayColor': '#333333',
			'centerOnScroll' : true,
			'href': '#'+thisElementID,
			'onStart':function() {
				if(withScroll){
					if($("#headCoach").is(":visible")){
						$.scrollTo('#headCoach',300, { axis:'y' });
					}else{
						$.scrollTo('#header',300, { axis:'y' });
					}
					
					
				}
					
			},
			'onComplete' : function(){
				 $('#fancybox-content').height('auto');
				 $('#fancybox-content').children().eq(0).css('height','auto');
		    }
			
		});
				
		return true;
		//setTimeout("$.scrollTo('#header', 50, { axis:'y' });",200);
		//setTimeout("$.scrollTo('#header', 100, { axis:'y' });",200);
	
}

function showPopUpChanges(){
	$.fancybox({		
		padding : 	0,
		margin 	:	0,
		width	:	800,
		height  :	400,
		transitionIn: 'none',
		transitionOut : 'none',	
		scrolling : 'auto',
		centerOnScroll : true,
		type : 'iframe',
		href: '../jsf/templates/showChanges.xhtml',
		overlayColor: '#333333'

	});
}

function showEflProgress(){
	$.fancybox({		
		padding : 	0,
		margin 	:	0,
		width	:	800,
		height  :	400,
		transitionIn: 'none',
		transitionOut : 'none',	
		scrolling : 'auto',
		centerOnScroll : true,
		type : 'iframe',
		href: '../jsf/templates/eflProgress.xhtml',
		overlayColor: '#333333'

	});
}

function showEditCropperImage(xhr, status, args){	

	var WDivRotate;
	var HDivRotate;	
	
	if(args.format=="PDF"){
		WDivRotate=550;
		HDivRotate=510;
	}else{
		var widthWindows=Number($(window).width())-30;
		var heightWindows=Number($(window).height())-30;			
		
		var WimgRotate=Number(args.Height);
		var HimgRotate=Number(args.Width);
			
		if(WimgRotate>widthWindows){
			WDivRotate=widthWindows-170;
		}else if(WimgRotate<widthWindows && WimgRotate>=500){
			WDivRotate=WimgRotate;
		}else{
			WDivRotate=500;
		}
		
		if(HimgRotate>heightWindows){
			HDivRotate=heightWindows-140;
		}else if(HimgRotate<heightWindows && HimgRotate>=500){
			HDivRotate=heightWindows-200;
		}else{
			HDivRotate=500;
		}	
	}
	
	
	$.fancybox({		
		padding : 	0,
		margin 	:	0,
		width	:	(WDivRotate+30)<=500?500:WDivRotate+30,
		height  :	(HDivRotate+120)<=500?500:HDivRotate+120,
		transitionIn: 'none',
		transitionOut : 'none',	
		'modal' : true,
		type : 'iframe',
		scrolling : 'auto',
		centerOnScroll : true,
		hideOnOverlayClick : false,
		showCloseButton : false,
		href: 'templates/croppedImage.xhtml',
		overlayColor: '#333333'

	});
}

function validaSigCreditHistory(){
	
	var flagChangePage = false;
	
	var mailKubo = $("#thisMail").val();
		
	//alert(mailKubo);
	
		if(validateFields()){
			if(!isKuboMail(mailKubo)){
				if(displayMessageProcessing('msgprocessing',true)){
					
					$("#callSGBWS").click(); //llamada al SGb 
					flagChangePage = true;
					
				}else{
					
					flagChangePage = false;
					
				}
			}
		}
		
	
	return flagChangePage;
}

function validateNextMenu(isobligatorio){
	var flagChangePage02 = true;
	if(isobligatorio=="S"){
		if(validateFields()){
			if(displayMessageProcessing('msgprocessing',true)){
				flagChangePage02 = true;
			}else{
				flagChangePage02 = false;
			}
		}else{
			flagChangePage02=false;
		}
	}else{
		if(displayMessageProcessing('msgprocessing',true)){
			flagChangePage02 = true;
		}
	}
	return flagChangePage02;
}


function closeMessageProcessing() {
	setTimeout("closeFancy()",500);
}
function closeFancy(){
	
	$.fancybox.close();
		
	 if ($(".tabla_scroll").is(':visible')) { 
		 $(window).resize();
	 }
	 
	

}
/*
function validatePhone(phone,lada) {
	var element=$("#"+phone);
	var ladaValue=$("#"+lada).val();
	var phoneValue=element.val();	
	if(ladaValue !="" && phoneValue !="" && ladaValue.length>2 && phoneValue.length>6){
		element.parent().children('.formError').remove();
		fieldCount();
		return true;
	}
	else{
		element.validationEngine('showPrompt', '*Número telefonico incorrecto','error','centerRight', true);
		return false;
	}
}*/

function fieldNotEmpty(component) {
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();
	if(valor !="")
		return true;
	else
		return false;
}




function validateNumberPhone(phone,lada) {
	var clientPhone = phone.replace(/:/g, '\\:');
	var clientLada = lada.replace(/:/g, '\\:');
	var element=$("#"+clientPhone);
	//var ladaValue=$("#"+clientLada).val();
	var phoneValue=element.val();
	if(phoneValue !="" && phoneValue.length>6){
		element.parent().children('.formError').remove();
		$("#"+clientPhone).removeClass("requiredClass");
		$("#"+clientLada).removeClass("requiredClass");
		return true;
	}
	else{
		element.validationEngine('showPrompt', '*Número telefonico incorrecto','error','centerRight', true);
		return false;
	}
}


function validaNombre(component){
	var element=$("#"+component);
	var elementVal=element.val();
	
	if(elementVal.length>0){
		var s = elementVal;
		var x = 0;
		var conV = 0;
		var conC = 0;
		var b = false;
		var i = 0;
		for(i; i<elementVal.length;i++){
				if(i<elementVal.length-1){
					  if((s.charAt(i)+"")==((s.charAt(i+1)+""))){
						  x++;
						  if(x>1){
							  b=true;
							  break;
							  }
					  }
					  if ((s.toLowerCase().charAt(i)=='a') || (s.toLowerCase().charAt(i)=='e') || (s.toLowerCase().charAt(i)=='i') || (s.toLowerCase().charAt(i)=='o') || (s.toLowerCase().charAt(i)=='u'))
						    conV++;						  
					  if ((s.toLowerCase().charAt(i)!='a') && (s.toLowerCase().charAt(i)!='e') && (s.toLowerCase().charAt(i)!='i') && (s.toLowerCase().charAt(i)!='o') && (s.toLowerCase().charAt(i)!='u'))
						    conC++;						  
				}
		}
		if(b||conV==0||conC==0){
			element.validationEngine('showPrompt', '*Formato de nombre incorrecto','error','centerRight', true);			
			return false;
		}
		else{
			element.parent().children('.formError').remove();
			return true;
			}	
	}else{
		element.parent().children('.formError').remove();
		return true;
	}

}

function validateFileUpload(showProcessing){
	console.log("entro a fileuoload");
	var arrayFields=null;
	var flagSuccess=true;
	if(document.frm_credFm2 || document.frm_compActEcom || document.frm_loan || document.frm_acredProBus || document.frm_compDomi){
		arrayFields = $("#general").find(".template-upload");
		if(arrayFields!=null){
			for(var i=0;i<arrayFields.length;i++){			
				if(arrayFields[i].tagName!=null && arrayFields[i].tagName.toUpperCase()=='TR' && $(arrayFields[i]).hasClass("template-upload")){						
					if(!$(arrayFields[i]).is(":hidden")){
						$(arrayFields[i]).addClass("requiredClass");	
						$.scrollTo($(arrayFields[i]), 800, { axis:'y' });
						flagSuccess=false;
						break;
					}
				}
			}
		}
	}	
	if(!flagSuccess){
		alert("Existen documentos pendientes.\nFavor de dar click en el botón subir o cancelar");
	}		
	else if(showProcessing=="S"){
		displayMessageProcessing('msgprocessing',true);
	}
		
	
	return flagSuccess;
}

function validateFields()
{
	console.log("general.validateFields()");
	
	var campos = null;
	/*
	if(document.frmBasicos)
	{
		$("#acbusiSimple_input").addClass("validatorClass");
		$("#acemSimple_input").addClass("validatorClass");
		campos = document.frmBasicos.elements;
	}
	
	if(document.frmHistCred )
	{
		console.log("\t document.frmHistCred");
		
		campos = document.frmHistCred.elements;
	}
	*/
	if($(".paso_1_2").is(":visible")){
		$("#acbusiSimple_input").addClass("validatorClass");
		$("#acemSimple_input").addClass("validatorClass");
		console.log("\t document.formularioBasicos");
		campos = $('.paso_1_2 .validatorClass[index]:visible, #acSimple input[index]:visible,  #acSimple2 input[index]:visible,  #acSimple3 input[index]:visible');
	}
	
	

	
	
	
	
	if(document.frmMoreInfo)
	{
		campos = document.frmMoreInfo.elements;
	}
	
	if(document.frm_info_basic_loan)
	{
		campos = document.frm_info_basic_loan.elements;
	}
	/*
	if(document.frm_income_expense)
	{
		console.log("\n frm_income_expense.elements");
		campos = document.frm_income_expense.elements;
	}
	*/
	if(document.frm_questPLD)
	{
		campos = document.frm_questPLD;
	}

	if(document.frm_other_income_family)
	{
		campos = document.frm_other_income_family.elements;
	}

	if(document.frmCuentaClabe)
	{
		campos = document.frmCuentaClabe.elements;
	}
	
	if(campos != null)
	{
		return validar_formulario(campos);
		
	} else {		
		return true;
	}
}

function validar_formulario(campos)
{
	console.log("general.validar_formulario()");
	
	//var campos=document.signin.elements;
	var bandera = true;
	
	for(var i = 0; i < campos.length; i++)
	{
		if(campos[i].tagName != null 
		&& campos[i].tagName.toUpperCase() == 'SELECT' 
		&& $(campos[i]).hasClass("validatorClass"))
		{
			//console.log("\t validar_campos_select");
			
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value == null || campos[i].value == "0" || campos[i].value == "")
				{
					
					if( $(campos[i]).attr("id") != 'nacionalidad' ){
						
					
						if(! $(campos[i]).hasClass("requiredClass") )
							$(campos[i]).addClass("requiredClass");		
						
						bandera = false;
						
						var element = ( $(campos[i]).parent().parent().parent() );
								
						var valTop = (element.offset().top ) - 280;
						
						//alert("boolean: "+flagScrollB1);
						
						if(flagScrollB1){
							valTop = valTop - 200;
						}
						
						//alert("valorTop: "+valTop);
						$.scrollTo( valTop );
						
						//$(campos[i]).focus();
						
						break;
						
					}else if( $(campos[i]).attr("id") == 'nacionalidad' && campos[i].value == "" ) {
						
						if(! $(campos[i]).hasClass("requiredClass") )
							$(campos[i]).addClass("requiredClass");		
						
						bandera = false;
						
						var element = ( $(campos[i]).parent().parent().parent() );
								
						var valTop = (element.offset().top ) - 280;
						
						//alert("boolean: "+flagScrollB1);
						
						if(flagScrollB1){
							valTop = valTop - 200;
						}
						
						//alert("valorTop: "+valTop);
						$.scrollTo( valTop );
						
						//$(campos[i]).focus();
						
						break;
						
					}else{
						
						$(campos[i]).removeClass("requiredClass");
					}
						
				} else {
					
					$(campos[i]).removeClass("requiredClass");
				}					
			}
		}
		
		if(campos[i].type!=null 
		&& campos[i].type.toUpperCase()=='TEXT' 
		&& $(campos[i]).hasClass("validatorClass"))
		{						
			if(!$(campos[i]).is(":hidden"))
			{
				//console.log("\t validar_campos_input_text:hidden");
				
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value==""))
				{
					if(! $(campos[i]).hasClass("requiredClass") )
						$(campos[i]).addClass("requiredClass");
					
					bandera = false;
					
					//$.scrollTo($(campos[i]), 800, { axis:'y' });
					
					var element = ( $(campos[i]).parent().parent() );
					
					var valTop = (element.offset().top ) - 280;
					
					//alert("boolean: "+flagScrollB1);
					
					if(flagScrollB1){
						valTop = valTop - 200;
					}
					
					//alert("valorTop: "+valTop);
					$.scrollTo( valTop );
					
					//$(campos[i]).focus();
					
					break;
					
				} else {
					
					$(campos[i]).removeClass("requiredClass");
				}
					
			}
		}
		
		if(campos[i].tagName!=null 
		&& campos[i].tagName.toUpperCase()=='TEXTAREA' 
		&& $(campos[i]).hasClass("validatorClass"))
		{
			//console.log("\t validar_campos_textarea");
			
			if(!$(campos[i]).is(":hidden"))
			{
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value==""))
				{
					if(! $(campos[i]).hasClass("requiredClass") )
						$(campos[i]).addClass("requiredClass");
					
					bandera=false;
					
					//$.scrollTo($(campos[i]), 800, { axis:'y' });
					
					var element = ( $(campos[i]).parent().parent() );
					
					var valTop = (element.offset().top ) - 280;
					
					//alert("boolean: "+flagScrollB1);
					
					if(flagScrollB1){
						valTop = valTop - 200;
					}
					
					//alert("valorTop: "+valTop);
					$.scrollTo( valTop );
					
					//$(campos[i]).focus();
					
					break;
					
				} else {
					$(campos[i]).removeClass("requiredClass");
				}
					
			}
		}
	}
	
	if(bandera)
	{					
		var rdId = "";
		
		$(":radio").each(function()
		{
			//console.log("\t validar_campos_radio");
			
			if(!$(this).is(":hidden"))
			{
				var f2 = false;
				rdId = $(this).attr('name');
				
				//console.log("\t\t rdId = " + rdId);
				
				var rdElem = document.getElementsByName(rdId);
				
				for(var y = 0; y < rdElem.length; y++)
				{							
					//console.log("\t\t rdElem = " + rdElem[y].value);
					
					if(rdElem[y].checked)
					{
						f2 = true;
					}
				}
				
				if(!f2)
				{
					$.scrollTo($(this).parent(), 800, { axis:'y' });
					
					 return bandera = false;					
				}
				
//				if($("#"+idFrm+" input[name='"+$(this).attr('name')+"']:radio")){
//					$("#"+idFrm+" input[name='"+$(this).attr('name')+"']:radio").each(function(i){
//						
//				        if ($(this).is(':checked')) {
//				            f2 = true;
//				        }
//				    });
//					
//					if(!f2){
//						alert("fallo en "+rdId);
//						$.scrollTo($(this), 800, { axis:'y' });
//						 return bandera = false;
//						
//					}
//				}
			}
		});
	}
	/*if(bandera){
		if($('#recaptcha_response_field').val()==''){
			$.scrollTo('#recaptcha_response_field', 800, { axis:'y' });
			return false;
		}
	}*/
	
	/*if(bandera){
		displayMessageProcessing('processPR',false);
		$("#btnAviso").click();
		return bandera;
		}
		
	//else{*/
		//$.scrollTo('#scrollhere', 800, { axis:'y' });
	
	if(!bandera)
	{		
		alert("Por favor llene todos los campos requeridos.");
	}
	
		return bandera;
	//}


}

function validateNumAdrress(component){
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();
	if(valor !=""){		
		if(element.val()!=element.next().val()){
			return true;
		}else
			return false;		
	}
	else{	
		return false;
	}
}

function alertSessionOut() {
	alert("Expired session out");
}

function validateLength(element,num)
{
	if($('#'+element).val().length < parseInt(num) )
	{
		if($('#'+element).val().length > 0)
		{
			alertify.alert("Longitud incorrecta");
			
		} else {
			
			return false;
		}
	}
	
	return true;
}

function fieldNumeric(event) {
	var e=event;
	if(!e)
	 e = window.event;
	if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 8 && e.keyCode != 9) {
		
		if(e.preventDefault){
		    e.preventDefault();
		}else{
		    e.returnValue = false; 
		}	
	}
}

function isNumberKey(evt){
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	         if (charCode > 31 && (charCode < 48 || charCode > 57))
	            return false;

	 return true;
}

//Validar Telefono maximo 6 digitos.
function valueGreaterSix(input){

	var campo = "Teléfono";
	if(input.id.indexOf("phoneFixed") != -1  || input.id.indexOf("phoneCel") != -1 ){
		if(input.id.indexOf("phoneFixed") != -1){
			campo = "Teléfono";
		}
		
		if(input.id.indexOf("phoneCel") != -1){
			campo = "Teléfono Celular";
		}
	}
	
	if(input.value.length <10 && input.value.length != 0){

		alerta ('El '+ campo +' debe tener 10 dígitos, no olvides incluir tu LADA', "#"+$(input).attr("id"));
		console.log("#"+$(input).attr("id"));
	}else{
		
		alertaQuitar ("#"+$(input).attr("id"));
	}
	
}

function validateTelephono(evt, input){
	var campo = "Teléfono";
	
	if(input.id.indexOf("phoneFixed") != -1){
		campo = "Teléfono";
	}
	if(input.id.indexOf("phoneCel") != -1){
		campo = "Celular";
	}
	
	var splitNumero = input.value.split("");
	var valorEntrante = String.fromCharCode(evt.charCode);
	var error = false;
	
	
	for(var i = 0; i < splitNumero.length; i++){
		if(splitNumero.length > 3){
			//Numeros iguales (555555)
				if(valorEntrante == splitNumero[i] && valorEntrante == splitNumero[i+1] && valorEntrante == splitNumero[i+2] && valorEntrante == splitNumero[i+3] && valorEntrante == splitNumero[i+4]){
					error = true;
					input.value = "";
				}
				
		/*
			//Numeros consecutivos (12345)
				if(parseInt(valorEntrante) -4 == splitNumero[i] && parseInt(valorEntrante)-3 == splitNumero[i+1] && parseInt(valorEntrante)-2 == splitNumero[i+2] && parseInt(valorEntrante)-1 == splitNumero[i+3]){
					error = true;
					input.value = "";
				}
			//Numeros (54321)
				if((parseInt(valorEntrante) + 4 == splitNumero[i]) && (parseInt(valorEntrante) + 3 == splitNumero[i+1]) && (parseInt(valorEntrante) + 2 == splitNumero[i+2]) && (parseInt(valorEntrante) + 1 == splitNumero[i+3])){
					error = true;
					input.value = "";
				}
			//2 numeros iguales (151515)
				if((splitNumero[i] +""+ splitNumero[i+1] == splitNumero[i+2] +""+ splitNumero[i+3]) && (splitNumero[i] +""+ splitNumero[i+1] == splitNumero[i+4] +""+ splitNumero[i+5])){
					error = true;
					input.value = "";
				}
			*/
		}
	}
	
	if(error){
		alertify.error('Número de ' + campo + ' incorrecto.');
		return false;
	}
	
	var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
       return false;

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

function validateMinCaracteres(component,min){
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();
	if(valor.length<parseInt(min)){
		$("#"+clientId).addClass("requiredClass");
		$("#"+clientId).validationEngine('showPrompt', '*Contenido inválido ','error','centerRight', true);
		return false;
	}
	else{
		
		$("#"+clientId).removeClass("requiredClass");
		$("#"+clientId).parent().children('.formError').remove();
		return true;
		
	}
}



function createCustomHTMLComboInv(mes, capital, interes, total, disponible, isProy){
	
	if(isProy){
	
		var str = "<div style='padding: 8px;' > " +
			"<table>" +
			"	<tr>" +
			"		<td style='text-align: center;' colspan='2' ><b>Comportamiento de pagos <br /> "+mes+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Capital Pagado: </td><td style='text-align: right'><b>"+capital+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Interés Pagado: </td><td style='text-align: right'><b>"+interes+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Garantía Aplicada: </td><td style='text-align: right'><b>"+total+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Saldo Disponible: </td><td style='text-align: right'><b>"+disponible+"</b></td>" +
			"   </tr>" +
			"</table>" +
		  " </div>";
		
		return str;

	}else{
		
		var str = "<div style='padding: 8px;' > " +
		"<table>" +
		"	<tr>" +
		"		<td style='text-align: center;' colspan='2' ><b>Comportamiento de pagos <br /> "+mes+"</b></td>" +
		"   </tr>" +
		"	<tr>" +
		"		<td>Capital por Recibir: </td><td style='text-align: right'><b>"+capital+"</b></td>" +
		"   </tr>" +
		"	<tr>" +
		"		<td>Interés por Recibir: </td><td style='text-align: right'><b>"+interes+"</b></td>" +
		"   </tr>" +
		"	<tr>" +
		"		<td>Garantía por Ejercer: </td><td style='text-align: right'><b>"+total+"</b></td>" +
		"   </tr>" +
		"	<tr>" +
		"		<td>Saldo Disponible: </td><td style='text-align: right'><b>"+disponible+"</b></td>" +
		"   </tr>" +
		"</table>" +
	  " </div>";
	
	return str;
		
	}

	
}

function createCustomHTMLBarRendInv(mes, capital, interes, total, isProy){
	
	if( isProy ){
	
		var str = "<div style='padding: 8px;' > " +
					"<table>" +
					"	<tr>" +
					"		<td style='text-align: center;' colspan='2' ><b>Comportamiento de pagos <br /> "+mes+"</b></td>" +
					"   </tr>" +
					"	<tr>" +
					"		<td>Capital Pagado: </td><td style='text-align: right'><b>"+capital+"</b></td>" +
					"   </tr>" +
					"	<tr>" +
					"		<td>Interés Pagado: </td><td style='text-align: right'><b>"+interes+"</b></td>" +
					"   </tr>" +
					"	<tr>" +
					"		<td>Garantía Aplicada: </td><td style='text-align: right'><b>"+total+"</b></td>" +
					"   </tr>" +
					"</table>" +
				  " </div>";
		
		return str;
	
	}else{
		
		var str = "<div style='padding: 8px;' > " +
				"<table>" +
				"	<tr>" +
				"		<td style='text-align: center;' colspan='2' ><b>Comportamiento de pagos <br /> "+mes+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Capital por Recibir: </td><td style='text-align: right'><b>"+capital+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Interés por Recibir: </td><td style='text-align: right'><b>"+interes+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Garantía por Ejercer: </td><td style='text-align: right'><b>"+total+"</b></td>" +
				"   </tr>" +
				"</table>" +
			  " </div>";
		
		return str;
		
	}
	
}

function createCustomHTMLAreaSaldoDispCntnt(monto,deposito,retiro, mes){
	
	var str = "<div style='padding: 8px;' > " +
			"<table>" +
			"	<tr>" +
			"		<td style='text-align: center;' colspan='2' ><b>Saldo Disponible - Depósitos - Retiros <br /> "+mes+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Depósitos: </td><td style='text-align: right'><b>"+deposito+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Retiros: </td><td style='text-align: right'><b>"+retiro+"</b></td>" +
			"   </tr>" +
			"	<tr>" +
			"		<td>Saldo Disponible: </td><td style='text-align: right'><b>"+monto+"</b></td>" +
			"   </tr>" +
			"</table>" +
		  " </div>";

return str;

	}
