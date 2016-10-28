

console.log("detailsOtherIncomeFamily.js");

$(document).ready(function(){	
	
	$("input[name=address_you]:radio").click(function() {
		
        if($(this).val() == 'N') {
        	$("#show_address").slideDown();
        } else if($(this).val() == 'S') {
        	$("#show_address").slideUp();        	
        }
        
		});
	
	$(".formatincomexpenses").keyup(function(e) {				 
				$(this).formatCurrency({ 
						colorize: true, 
						positiveFormat: '%n', 
						roundToDecimalPlace: -1, 
						eventOnDecimalsEntered: true });
			
	});
	
	$("#btn_cancel_otherIncomef").click(function(event) 
	{
	
			$("#fancybox-wrap").removeClass("contenido_light");
			
		parent.$.fancybox.close();
		
		
	});
	
	$("#btn_end_otherIncomef").click(function(event) 
	{
		console.log("> Terminar baby");

	
	
		if(validateFields())
		{
			console.log("validateFields_OK");
			
			var valueActivity = $('#activityEconomic').val();
			var sumTotal = "0.00";
		
			if(valueActivity!='')
			{
				switch (valueActivity) 
				{
					case '1':
						sumTotal=$("#inp_income_otherfamilyN").val();
					break;
					
					case '2':
						sumTotal=$("#inp_income_otherfamilyE").val();
					break;
					
					case '3':
						sumTotal=parseFloat(Number($("#inp_income_otherfamilyE").val().replace(",",""))+Number($("#inp_income_otherfamilyN").val().replace(",",""))).toFixed(2);
					break;
		
					default: break;
				}
			
				parent.$.fancybox.close();
			}
			
			parent.$("#inp_income_other_family").val(sumTotal).blur();
		}
		
	});
	
});


function validateMyLoanCombo(component) {
	var element=$("#"+component);
	if(element.val()==""){
		   $(element).validationEngine('showPrompt', 'Seleccione una opción','error','centerRight', true);		   
		   return false;	   
	   }
	   else{
		   $(element).parent().children('.formError').remove();			  
		   return true;
	   }
}

function validateNumberIncome(component) {
	var element=$("#"+component);
	var elementVal=element.val();	
	//alert("val: "+elementVal);
	if(elementVal !="" && parseFloat(elementVal) > 0){		
		return true;
	}else{
		element.val("0.00");
		element.focus();
		return false;
	}
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
