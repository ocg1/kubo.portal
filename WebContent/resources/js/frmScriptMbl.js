$(document).ready(function() 
{
			
	$('#nextCHBASICOS01').click(function(event){
		
		$("#hdNext\\:siguienteBASICOS").click();
		event.preventDefault();
		return false;
	});
			
});
		

function fieldCount()
{			
	
	var campos = null;
	
	if(document.frmBasicos)
	{
		campos = document.frmBasicos.elements;
	}
		
	if(document.frmHistCred)
	{
		campos = document.frmHistCred.elements;
	}
		
	if(document.frmMoreInfo)
	{
		campos = document.frmMoreInfo.elements;
	}
		
	if(document.frm_info_basic_loan)
	{
		campos = document.frm_info_basic_loan.elements;
	}
		
	if(document.frm_income_expense)
	{
		campos = document.frm_income_expense.elements;
	}
		
	if(document.frmCuentaClabe)
	{
		campos = document.frmCuentaClabe.elements;
	}
		
	if(document.frm_questPLD || document.frm_credFm2 || document.frm_compActEcom || document.frm_loan || document.frm_acredProBus || document.frm_compDomi)
	{
		campos = $("#general").find(".validatorClass");
	}
		
	if(document.frm_beneficiaries)
	{
		campos = document.frm_beneficiaries.elements;
	}
		
	if(document.frm_ImoreInfo)
	{
		campos = document.frm_ImoreInfo.elements;
	}
			
	if(campos != null)
	{
		counter(campos);
	}
		
}

function counter(campos)
{
	
	var sel   		= 0;
	var text  		= 0;
	var radio 		= 0;
	var textarea 	= 0;
	var isAnswered	= 0;
	var AnswRadio	= 0;
	var checkList   = 0;
	
	var arrayNames = null;
	
	for(var i = 0; i < campos.length; i++)
	{

		if(campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("validatorClass") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				sel = parseInt(sel) + 1;
				
				if(campos[i].value != null && (campos[i].value != "0" && campos[i].value != ""))
				{
					isAnswered = parseInt(isAnswered) + 1;
				}
			}
		}
			
		if(campos[i].type == 'text' && $(campos[i]).hasClass("validatorClass"))
		{
			if(!$(campos[i]).is(":hidden"))
			{
				text = parseInt(text) + 1;
				
				if(campos[i].value != null &&  campos[i].value != "" && campos[i].value != "0.00")
				{
					isAnswered = parseInt(isAnswered) + 1;
				}
			}
		}
			
		if(campos[i].tagName.toUpperCase() == 'TEXTAREA' && $(campos[i]).hasClass("validatorClass"))
		{
			if(!$(campos[i]).is(":hidden"))
			{	
				textarea = parseInt(textarea) + 1;
				
				if(campos[i].value!=null && (campos[i].value!="0" && campos[i].value!=""))
				{
					isAnswered = parseInt(isAnswered) + 1;
				}
			}
		}

		if(campos[i].tagName.toUpperCase() == 'IMG')
		{
			if($(campos[i]).is(":hidden"))		
			{
				checkList = parseInt(checkList) + 1;
			} else {
				checkList = parseInt(checkList)+1;
				isAnswered = parseInt(isAnswered)+1;
			}
		}		
	}
	
	$(":radio").each(function()
	{
		if(!$(this).is(":hidden"))
		{
			if($(this).is(':checked'))
			{
				AnswRadio =(parseInt(AnswRadio)+1);
			}
			
			var thFlag = false;
			
			if(arrayNames != null)
			{
				var thisArray = arrayNames.split("::");
				
				for(var i = 0; i<thisArray.length; i++ )
				{
					if(thisArray[i] ==  $(this).attr('name'))
					{
						thFlag = true;
						break;
					}
				}
				
				if(!thFlag)
				{
					arrayNames += $(this).attr('name') + "::";
				}
				
			} else {
				
				arrayNames = $(this).attr('name') + "::";
			}
			
			
			if( !thFlag )
			{
				//radioNum = parseInt(radioNum) +1;
				radio = parseInt(radio) +1;
			}
		}
	});
	
	
	//radio =(parseInt(radioNum)/2);
	
	isAnswered += (parseInt(AnswRadio));
	
	var total 	= parseInt(sel)
				+ parseInt(text)
				+ parseInt(radio)
				+ parseInt(textarea)
				+ parseInt(checkList);
	
	var porcen 	= ((parseInt(isAnswered) * 100) / parseInt(total));
	
	var str = "<div>" +
					"isAnswered: "+isAnswered+"<br />" +
					"sel: "+sel+"<br />" +
					"text: "+text+"<br />" +
					"radio: "+radio+"<br />" +
					"textarea: "+textarea+"<br />" +
					"checkList: "+checkList+"<br />" +
					"total: "+total+"<br />" +
					"porcen: "+porcen+" " +
				"</div>";
	
	//alert(str);
	
	$("#dvProp").html(str);

	$("#changeButtons\\:porcBasic").val(porcen);
	$("#changeButtons\\:porcBasic").blur();
	
	$("#changeButtons\\:countPorcent").click(); //llama a la accion
	
	
}

function mapOnOff()
{

	
	
}
			
function isKuboMail(mail){
	
	var flag = false;
	
	if(mail.indexOf("@kubofinanciero.com") != (-1) ){
		
		$("#dvKuboPerson").show();
		$.scrollTo('#header',10, { axis:'y' });
		
		flag = true;
	}
	
	return flag;
	
}
			
function validaRelationShipBasic(xhr, status, args)
{	
	res = args.isRelation;
	
	if(res == 'S')
	{		
		closeMessageProcessing();
		setTimeout('showRelPersonCont();',600);
		return false;
		
	}else{
		
		$("#hdNext\\:siguienteBASICOSACTION").click();
		return true;
	}
}
			
			function itemFunctionMbl(element){
				
				var elmnt = $("#"+element+"");
				var flag = false;
				
				//alert(elmnt.attr("id") +" :  anterior: "+$("#anterior").val());
				
				if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2" || $("#anterior").val() == "menu6"){
					if($("#anterior").val() == "menu6")
						flag  = validateFileUpload("N");
					else	
						flag  = validateFields();
					//alert("regreso: "+flag);
				}else{
					flag = true;
				}
				
				if( flag && $("#anterior").val() == "menu1" && $("#area").val() == "L" ){
					
					if(isKuboMail($("#thisMail").val())){
						flag = false;
					}
					
//				/*cambio que te envia a la parte de siguiente siempre y cuando seas acreditado */	$("#hdNext\\:siguienteBASICOS").click();
//					
//					flag = false;
				}
				
				
				
				if(flag){
					 flag = displayMessageProcessing('msgprocessing',true);
						if(elmnt.hasClass("menuItem")){
						//$(".menuItem").click(function(){
					//		var flag = true;
							//alert($("#anterior").val());
					//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
					//			flag  = validateFields();
					//			//alert("regreso: "+flag);
					//		}
							if(flag){
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
							if(flag){
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
						}
						
						
						else if(elmnt.hasClass("menuItem1")){
						//$(".menuItemSel").click(function(){
							
					//		var flag = true;
							//alert($("#anterior").val());
					//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
					//			flag  = validateFields();
					//			//alert("regreso: "+flag);
					//		}
							if(flag){
							
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
			
function changePage(){
	mapOnOff();
	hideIFEjs();
	

}

function hideIFEjs(){
	$('#ife').hide();	
}

function validateFieldReferences(thisID){
	var ref = thisID.split("_")[2];
	
	var flagRef=false;
	if(	($("#inp_name_"+ref).val().replace(" ","")).length>3||($("#inp_secondName_"+ref).val().replace(" ","")).length>3||
		($("#inp_lastName_"+ref).val().replace(" ","")).length>3||($("#inp_surName_"+ref).val().replace(" ","")).length>3
	   ){
		flagRef=true;
	}
	
	if(!flagRef){
		if(ref == "ref1"){
			$("#lada_phone1").removeClass("requiredClass");
			$("#phone1").parent().children('.formError').remove();
		}
		if(ref == "ref2"){
			$("#lada_phone2").removeClass("requiredClass");
			$("#phone2").parent().children('.formError').remove();
		}
		if(ref == "ref3"){
			$("#lada_phone3").removeClass("requiredClass");
			$("#phone3").parent().children('.formError').remove();
		}
		if(ref == "ref4"){
			$("#lada_phone4").removeClass("requiredClass");
			$("#phone4").parent().children('.formError').remove();
		}
		return;
	}
	
	if(ref == "ref1" && flagRef){
		if(($("#lada_phone1").val().replace(" ","")).length<2||($("#phone1").val().replace(" ","")).length<6){
			$("#lada_phone1").addClass("requiredClass");
			$("#phone1").addClass("requiredClass");
//			$("#phone1").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone1").removeClass("requiredClass");
			$("#phone1").removeClass("requiredClass");
			$("#phone1").parent().children('.formError').remove();
		}
	}
	else if(ref == "ref2" && flagRef){
		if(($("#lada_phone2").val().replace(" ","")).length<2||($("#phone2").val().replace(" ","")).length<6){
			$("#lada_phone2").addClass("requiredClass");
			$("#phone2").addClass("requiredClass");
//			$("#phone2").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone2").removeClass("requiredClass");
			$("#phone2").removeClass("requiredClass");
			$("#phone2").parent().children('.formError').remove();
		}
	}
	else if(ref == "ref3" && flagRef){
		if((($("#lada_phone3").val()).replace(" ","")).length<2||(($("#phone3").val()).replace(" ","")).length<6){
			$("#lada_phone3").addClass("requiredClass");
			$("#phone3").addClass("requiredClass");
//			$("#phone3").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone3").removeClass("requiredClass");
			$("#phone3").removeClass("requiredClass");
			$("#phone3").parent().children('.formError').remove();
		}
	}
	else if(ref == "ref4" && flagRef){
		if(($("#lada_phone4").val().replace(" ","")).length<2||($("#phone4").val().replace(" ","")).length<6){
			$("#lada_phone4").addClass("requiredClass");
			$("#phone4").addClass("requiredClass");
//			$("#phone4").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone4").removeClass("requiredClass");
			$("#phone4").removeClass("requiredClass");
			$("#phone4").parent().children('.formError').remove();
		}
	}
}

function onExit(){
	
	if(confirm("Estás seguro qué quieres terminar tu sesión?")){
		displayMessageProcessing('msgprocessing',true);
		return true;
	}
	else{
		return false;
	}
}

function initialize(component) {
	
}
function initWithLatLong(component){
	
}
function generateStringAddress(component){
	
}
			