

console.log("incomeExpense.js");


$(document).ready(function(){	
	var totalIncome=0;
	var totalExpense=0;
	
	$("#inp_total_salary").attr('disabled','disabled');
	
	//$("#frm_income_expense").validationEngine('attach', {promptPosition : "centerRight",autoPositionUpdate : true});
	
	$('.calculeIncome').each(function(){
		
		if( ( $(this).attr('id') != 'inp_deduction_salary' ) && ( $(this).attr('id') != 'inp_total_salary' ) ){
			
			totalIncome += Number($(this).val().replace(",",""));
			
		}else if($(this).attr('id') == 'inp_deduction_salary'){
			
			totalIncome = Number(totalIncome) - Number($(this).val().replace(",",""));
			
		}
		
		$("#inp_total_income").val(totalIncome);
		
	});
	$('.calculeExpense').each(function(){
		totalExpense += Number($(this).val().replace(",",""));
	});
	
	loadScriptFormat();
	
	
	$('.calculeIncome,.calculeExpense ').bind({
		  focusin: function() {
		    if(Number($(this).val().replace(",",""))==0){
		    	$(this).val("");
		    }
		  },
		  focus: function() {
			  if(Number($(this).val().replace(",",""))==0){
			    	$(this).val("");
			    }
		  }
		});
	

	
	$('.calculeIncome').blur(function() {
		var suma=0;
		$('.calculeIncome').each(function(){
			//suma += Number($(this).val().replace(",",""));	
			
			if( ( $(this).attr('id') != 'inp_deduction_salary' ) && ( $(this).attr('id') != 'inp_total_salary' ) ){
				
				suma += Number($(this).val().replace(",",""));
				
			}else if($(this).attr('id') == 'inp_deduction_salary'){
				
				suma = Number(suma) - Number($(this).val().replace(",",""));
				
			}
			
		});
	
	
	});
	
	
	


	var strSessionID = $("#sessionId").val();

	
});


/*SECCION DE AYUDA CON CALCULOS DE GASTO FAMILIAR*/

function loadScriptFormat(){
$(".formatincomexpenses, #gastoTotal, #inp_income_otherfamilyE").keyup(function(e) {			
	$(this).formatCurrency({ 
			colorize: true, 
			positiveFormat: '%n', 
			roundToDecimalPlace: -1, 
			eventOnDecimalsEntered: true });

});
}



function jsTerminaDetail(){
	
	$("#opert-field").blur();
	$("#selling-field").blur();
	
	var totalValor=$("#inp_value_net").val();
	//alert("total: "+totalValor);
	if(totalValor!=""){
		$("#inp_business_company").val(totalValor);
		$("#inp_business_company").blur();
	}	
	
	$("#dvMobileBackAction a").click();
	
}


function calculatedValue(component,getValue,setValue) {
	var suma = 0;
	var valor=$(component).val();
	if(valor==0){
		$("#"+setValue).val("");
		}	
	else if (valor==1){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*30).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==2){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*4).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==3){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*2).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==4){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*1).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	
	suma = Number($("#inp_month_food_food").val().replace(",",""));
	suma += Number($("#inp_month_lunch_food").val().replace(",",""));
	suma += Number($("#inp_month_dinner_food").val().replace(",",""));
	suma += Number($("#inp_month_insurance_food").val().replace(",",""));
	
	$("#inp_total_food_help").val(formatNum(parseFloat(suma).toFixed(2)));

}

function calculatedValueLoan(component,getValue,setValue) {
	var suma = 0;
	var valor=$(component).val();
	if(valor==0){$("#"+setValue).val("");}	
	else if (valor==1){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*30).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==2){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*4).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==3){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*2).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	else if (valor==4){ 
		var result=parseFloat($("#"+getValue).val().replace(",","")*1).toFixed(2);			
		$("#"+setValue).val(formatNum(result));
	}
	
	suma = Number($("#inp_month_card_credit").val().replace(",",""));
	suma += Number($("#inp_month_payroll_credit").val().replace(",",""));
	suma += Number($("#inp_month_paymentauto_credit").val().replace(",",""));
	suma += Number($("#inp_month_personal_credit").val().replace(",",""));
	suma += Number($("#inp_month_other_credit").val().replace(",",""));
	
	$("#inp_total_credit_help").val(formatNum(parseFloat(suma).toFixed(2)));

}

function formatNum(entero)
{
    var num = entero.replace(",","");
	var ent;
	var dec;
	var point;
	if(num.split(".").length>2){
		alert("numero no valido");
		num= num.substring(0,num.length-1);
		input.value = num;
	    return;
	}
	ent=num.split(".")[0];	
	dec=num.split(".")[1];
	
	if(isNaN(dec)){
		dec="";
		point="";
	}
	else{
		point=".";
		
	}
  
    if(!isNaN(ent)){
    	if(ent.length>3)
    	ent= ent.substring(0,ent.length -3)+","+ent.substring(ent.length -3,ent.length);    	
	    return ent+point+dec;
    }else{
    	return entero;
    }
}

function setPorcent() {
	var incomeTotal=Number($("#inp_total_income").val().replace(",",""));
	var expensesTotal=Number($("#inp_total_expense").val().replace(",",""));
	var objPorcent=$("#changeButtons\\:porcBasic");
	var arrayRadio=$("#radio-question-owe").find(":radio");
	var porcent=0;
	if(incomeTotal>0 && expensesTotal>0){
		porcent+=75;
		arrayRadio.each(function() {
			if($(this).attr('checked')){
				porcent+=12.5;
				if($(this).val()=='S'){
					if($("#txt_question-yes").val()!='')
						porcent+=12.5;	
				}else{
					porcent=100;
				}							
			}
		});
		objPorcent.val(porcent);			
	}
	else if(incomeTotal==0 && expensesTotal==0){
		arrayRadio.each(function() {
			if($(this).attr('checked')){
				porcent+=12.5;
				if($(this).val()=='S'){
					if($("#txt_question-yes").val()!='')
						porcent+=12.5;	
				}							
			}
		});
		objPorcent.val(porcent);
	}
	else if(incomeTotal==0 && expensesTotal>0){
		porcent=25;
		arrayRadio.each(function() {
			if($(this).attr('checked')){
				porcent+=12.5;
				if($(this).val()=='S'){
					if($("#txt_question-yes").val()!='')
						porcent+=12.5;	
				}							
			}
		});
		objPorcent.val(porcent);			
	}
	else if(incomeTotal>0 && expensesTotal==0){
		porcent=25;
		arrayRadio.each(function() {
			if($(this).attr('checked')){
				porcent+=12.5;
				if($(this).val()=='S'){
					if($("#txt_question-yes").val()!='')
						porcent+=12.5;	
				}							
			}
		});
		objPorcent.val(porcent);		
	}
	
	if( porcent > 0  ){
		
		if ( !$("#txt_abaut_other_income").is(":hidden") && $("#txt_abaut_other_income").val().trim().length == 0 ){
			if (porcent >= 8 ){
				porcent -= 8;
			}else{
				porcent = 2;
			}
		}
	
		objPorcent.val(porcent);
		
	}
	
	objPorcent.blur();
	$("#changeButtons\\:countPorcent").click(); //llama a la accion
}

function validateNumberIncome(component) {
	var element=$("#"+component);

	if(element.val()==""){
		element.val("0.00");
	}
	/*
	if( component == 'inp_wages_salary' ||  component == 'inp_deduction_salary' ){
		
		//alert( ($("#inp_wages_salary").val().replace(",","") )  + " " + ($("#inp_deduction_salary").val().replace(",","")) );
		
		var numTot = (parseFloat( $("#inp_wages_salary").val().replace(",","") ) - parseFloat( $("#inp_deduction_salary").val().replace(",","") ) ) ;
		
		
		
		
		$("#inp_total_salary").val( formatNum( (numTot+"") ) );
		
		$("#inp_total_salary").removeAttr('disabled');
		$("#inp_total_salary").blur();
		$("#inp_total_salary").attr('disabled','disabled');
		
		if($("#inp_deduction_salary").val()==""){
			$("#inp_deduction_salary").val("0");
			$("#inp_deduction_salary").blur();
		}
	}
	*/
	
	var elementVal=Number(element.val().replace(",",""));	
	
	if(element.val() !=""){				
		if(isNaN(elementVal)){			
			element.val("0.00");
			element.focus();
			return false;
		}
		else{			
			return true;
		}
	}
	else{
		return false;
	}
}

function validateNumberExpenses(component) {
	var element=$("#"+component);
	
	if(element.val()==""){
		element.val("0.00");
	}
	
	var elementVal=Number(element.val().replace(",",""));	
	
	if( (elementVal+"") != "" ) {
		
		if(isNaN(elementVal)){
			
			element.val("0.00");
			element.focus();
			//return false;
		}
		//else{		
			
			//return true;
		//}
		
	}else if(element.val()==""){
		
		element.val("0.00");
		//return false;
	
	} // else{	
		
		return true;
	// }
}
function hideShowPromt(){
	//$('#frm_income_expense').validationEngine('hideAll');
}

function fieldNumericValue(event) {
	var e=event;
	if(!e)
	 e = window.event;
	if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 8 && e.keyCode != 9){
		if(e.keyCode==190 || e.keyCode==110){
			
		}			
		else{
			if(e.preventDefault){
			    e.preventDefault();
			}else{
			    e.returnValue = false; 
			};
		}	      
		
	}
}


function fieldNotEmpty(component){
	var clientId = component.replace(/:/g, '\\:');
	var element=$("#"+clientId);
	var valor=element.val();
	if(valor !=""){
		return true;				
	}
	else{
		return false;
	}
}

function clickNext(){
	$("#hdNext\\:siguienteMasInfo").click();
	
}














