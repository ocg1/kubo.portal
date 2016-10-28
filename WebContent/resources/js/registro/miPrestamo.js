
//$(document).ready(function(){
	
	//$("#frm_info_basic_loan").validationEngine('attach', {promptPosition : "centerRight",autoPositionUpdate : true});	 
//});



function validateTextArea(component) {
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();	
		if(valor !="" && valor.length>5){
			$(element).parent().children('.formError').remove();	
			fieldCount();
			return true;
		}
		else if(valor==""){
		    //$(element).validationEngine('showPrompt', '*Campo requerido','error','centerRight', true);
			
		    return false;
		}
		else if(valor.length<5){
			//$(element).validationEngine('showPrompt', '*Minimo 5 caracteres','error','centerRight', true);
			return false;
		}   
	
}
function validateMyLoanCombo(component) {
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	if(element.val()==""){
		  // $(element).validationEngine('showPrompt', 'Seleccione una opción','error','centerRight', true);		   
		   return false;	   
	   }
	   else{
		   $(element).parent().children('.formError').remove();	
		   fieldCount();
		   return true;
	   }
}

function validateNotEmpty(component){
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();
	if(valor !=""){
		if(element.val()!=element.next().val()){
			fieldCount();
			return true;
		}else
			return false;
		
	}
	else{
		return false;
	}
}

function validatePhone(phone,lada) {
	var clientPhone = phone.replace(/:/g, '\\:');
	var clientLada = lada.replace(/:/g, '\\:');
	var element=$("#"+clientPhone);
	var ladaValue=$("#"+clientLada).val();
	var phoneValue=element.val();
	if(phoneValue !="" && phoneValue.length>6){
		element.parent().children('.formError').remove();
		fieldCount();
		return true;
	}
	else{
		//element.validationEngine('showPrompt', '*Número telefonico incorrecto','error','centerRight', true);
		return false;
	}
}

function validateTextField(component) {
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();	
		if(valor !="" && valor.length>5){
			$(element).parent().children('.formError').remove();
			fieldCount();
			return true;
		}
		else if(valor==""){
		    //$(element).validationEngine('showPrompt', '*Campo requerido','error','centerRight', true);
		    return false;
		}
		else if(valor.length<5){
			//$(element).validationEngine('showPrompt', '*Minimo 5 caracteres','error','centerRight', true);
			return false;
		}   
}
function validateYears(component){
	var clientId = component.replace(/:/g, '\\:');
	
	
	
	var element=$("#"+clientId);
	
	var strIdT = (element.attr('id'));
	
	var strId = strIdT.split(":")[2];
	
	//alert("validaYear: id = "+strId +"   --  "+strIdT);
	
	if(strId == "inp_company_time"){
		
		if($("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").val()==""){
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").blur();
		}
		
	}else if( strId == "inp_company_time_month" ){
		
		if($("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time").val()=="")
		{
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time").blur();
			
		}
		
		if( $("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").val()=="" )
		{
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"inp_company_time_month").blur();
		}
		
	}else if( strId == "value_many_years2"){
		
		if($("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").val()==""){
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").blur();
		}
		
	}else if( strId == "value_many_years2_month" ){
		
		if($("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2").val()=="")
		{
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2").blur();
			
		}
		if( $("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").val() == "" )
		{
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").val("0");
			$("#"+strIdT.split(":")[0]+"\\:"+strIdT.split(":")[1]+"\\:"+"value_many_years2_month").blur();
		}
	}
	
	var valor=element.val();
	if(valor !=""){
		$(element).parent().children('.formError').remove();
		fieldCount();
		return true;
	}
	else{
		//$(element).validationEngine('showPrompt', '*Campo requerido','error','centerRight', true);
		return true;
	}
}

function validateCheckInCheckOut(component){
	var element=$(component);
	var parent=element.parents('.dvElementLength');
	
	var elementH1=parent.find('.hour1');
	var elementM1=parent.find('.minuts1');
	var elementH2=parent.find('.hour2');
	var elementM2=parent.find('.minuts2');
	
	var checkIn=parent.find('.check_in');
	var checkOut=parent.find('.check_out');
	
	if(Number(elementH1.val())<Number(elementH2.val())){
		checkIn.val(elementH1.val()+":"+elementM1.val());
		checkOut.val( elementH2.val()+":"+elementM2.val());
		checkIn.blur();
		checkOut.blur();
		fieldCount();
		elementM2.parent().parent().children('.formError').remove();
	}
	else{
		//elementM2.parent().validationEngine('showPrompt', '*Horario laboral incorrecto','error','centerRight', true);
	}
}

function validatePhoneExt(component,lada,phone) {
	var clientId = component.replace(/:/g, '\\:');
	var clientLada = lada.replace(/:/g, '\\:');
	var clientPhone = phone.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var ladaValue=$("#"+clientLada).val();
	var phoneValue=$("#"+clientPhone).val();
	if(ladaValue !="" && phoneValue !="" && ladaValue.length>1 && phoneValue.length>6){
		if(element.val()!=""){
			element.parent().children('.formError').remove();
			fieldCount();
			return true;
		}
		else
			return false;
	}
	else if(element.val()==""){
		return false;
	}
	else{
		//element.validationEngine('showPrompt', '*Los numeros telefonicos son incorrectos!','error','centerRight', true);
		return false;
	}
}
/*
var totalCharacter=0;
function saveData(component){
	var element=$("#"+component);
	var elementValue=element.val();
	var tamanoVal=elementValue.length;
	
	if(tamanoVal>5 && tamanoVal > totalCharacter && (tamanoVal - totalCharacter)>20){
		totalCharacter=elementValue.length;
		return true;
	}
	else if(totalCharacter > tamanoVal){
		totalCharacter=elementValue.length;
		return false;
	}
	else{
		return false;
	}
}*/

function saveData(component)
{
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var elementValue=element.val();
	var tamanoVal=elementValue.length;
	
	if((tamanoVal%20)==0){
		return true;
	}
	else{
		return false;
	}
}


function changeNextValYear(event,component) {
	var e=event;
	if(!e)
	 e = window.event;
	if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 8 && e.keyCode != 9) {
		
		if(e.preventDefault){
		    e.preventDefault();
		}else{
		    e.returnValue = false; 
		}	
	}else{
		if($(component).val()!=""){
			if(Number($(component).val())>1){
				$(component).next().text("Años");
			}
			else{
				$(component).next().text("Año");
			}
		}
	}	
}

function changeNextValMonth(event,component) {
	var e=event;
	if(!e)
	 e = window.event;
	if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 8 && e.keyCode != 9) {
		
		if(e.preventDefault){
		    e.preventDefault();
		}else{
		    e.returnValue = false; 
		}	
	}else{
		if($(component).val()!=""){
			if(Number($(component).val())>1){
				if(Number($(component).val())>12){
					$(component).val("12");
					$(component).next().text("Meses");
				}
				else
					$(component).next().text("Meses");
				
			}
			else{
				$(component).next().text("Mes");
			}
		}
	}	
}

//Validate NSS
function validateDigitIMSS(component){
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var imss=element.val();
	
	if(imss==""){
		return false;
	}
	
    var ver=parseInt(imss.charAt(10));
        var con=0;
        var aux=0;
        var aux2=0;
        var no1=0;
        var no2=0;
        if(imss.length<11 && (imss+"")!= ""){            
            //$(element).validationEngine('showPrompt', 'Número de seguro social incorrecto!','error','centerRight', true);
            $(element).val("");
            $(element).focus();           
            return false;
        }
        var temp="";
        for(var k=1;k<=10;k++)
        {
            aux=parseInt(imss.charAt(k-1));
            if( k%2!=0)//impar se multiplica por uno
            {
                con+=parseInt(aux);
            }
            else
            {
                aux2=parseInt(aux)*2;
                if(aux2>9)
                {
                    temp=""+aux2;//paso a cadena el numero mayor de 10
                    no1=parseInt(temp.charAt(0));
                    no2=parseInt(temp.charAt(1));
                    con+=parseInt(no1)+parseInt(no2);
                    
                }
                else{
                    con+=parseInt(aux2);
                    
                }
            }
        }

        temp="" + con;
        aux=parseInt(temp.charAt(1));
        aux2=10-parseInt(aux);
        no1=parseInt(con)+parseInt(aux2);//saca la decena proxima
        no2=parseInt(no1)-parseInt(con);//calcula el digito verificador
        if(parseInt(no2)>9){
            no2=0;
        }
        if(no2==ver){
        	$(element).parent().children('.formError').remove();
        	fieldCount();
            return true;
        }
        else{
        	//$(element).validationEngine('showPrompt', 'Número de seguro social incorrecto!','error','centerRight', true);
            $(element).val("");
            return false;
        }
}


function counterField(campos){
	
	var sel   		= 0;
	var text  		= 0;
	var radio 		= 0;
	var radioNum	= 0;
	var textarea 	= 0;
	var isAnswered	= 0;
	var AnswRadio	= 0;
	
	for(var i=0;i<campos.length;i++){
		
		
	
			if(campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("validatorClass") ){
				if(!$(campos[i]).is(":hidden")){
					sel = parseInt(sel) + 1;
					if(campos[i].value!=null && (campos[i].value!="0" && campos[i].value!="")){
						isAnswered = parseInt(isAnswered)+1;
					}
				}
			}
			if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass")){
				if(!$(campos[i]).is(":hidden")){
					text = parseInt(text) + 1;
					if(campos[i].value!=null && (campos[i].value!="0" && campos[i].value!="")){
						isAnswered = parseInt(isAnswered)+1;
					}
				}
			}
			
			if(campos[i].tagName.toUpperCase()=='TEXTAREA' && $(campos[i]).hasClass("validatorClass")){
				if(!$(campos[i]).is(":hidden")){	
					textarea = parseInt(textarea) + 1;
					if(campos[i].value!=null && (campos[i].value!="0" && campos[i].value!="")){
						isAnswered = parseInt(isAnswered)+1;
					}
				}
			}
		
	}
	$(":radio").each(function(){
		if(!$(this).is(":hidden")){
			if($(this).attr('checked')){
				AnswRadio =(parseInt(AnswRadio)+1);
			}
			radioNum = parseInt(radioNum) +1;
		}
	});
	
	radio =(parseInt(radioNum)/2);
	isAnswered +=(parseInt(AnswRadio));
	var total 	= parseInt(sel)+parseInt(text)+parseInt(radio)+parseInt(textarea);
	
	return isAnswered+"|"+total;
	
}