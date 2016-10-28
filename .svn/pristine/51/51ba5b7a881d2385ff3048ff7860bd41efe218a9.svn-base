function cambiaFondo(pnl){
	var select = document.getElementById("menuSelect").value;
	if(select != ""){
		document.getElementById(select).style.backgroundColor = "transparent";
		document.getElementById(pnl).style.color = "#39373a";
	}
    /*document.getElementById(pnl).style.backgroundColor = "#98bf0e";*/
	document.getElementById(pnl).style.backgroundColor = "#E6E6E6";
    document.getElementById(pnl).style.color = "#ff6405";
    document.getElementById("menuSelect").value= pnl;
}

function selecciona(pnl){	
	var select = document.getElementById("menuSelect").value;
	if(select != ""){
		document.getElementById(select).style.backgroundColor = "transparent";
		document.getElementById(select).style.color = "#39373a";
		document.getElementById(select).style.fontWeight = "normal";
	}

	//document.getElementById(pnl).style.backgroundColor = "#E6E6E6";
	document.getElementById(pnl).style.backgroundColor = "#C1D82F";
    document.getElementById(pnl).style.color = "#000000";
    document.getElementById("menuSelect").value= pnl;
}


function TamVentana() {  
  var Tamanyo = [0, 0];  
  if (typeof window.innerWidth != 'undefined')  
  {  
    Tamanyo = [  
       window.innerWidth,  
        window.innerHeight  
    ];  
  }  
  else if (typeof document.documentElement != 'undefined'  
      && typeof document.documentElement.clientWidth !=  
      'undefined' && document.documentElement.clientWidth != 0)  
  {  
 Tamanyo = [  
        document.documentElement.clientWidth,  
        document.documentElement.clientHeight  
    ];  
  }  
  else   {  
    Tamanyo = [  
        document.getElementsByTagName('body')[0].clientWidth,  
        document.getElementsByTagName('body')[0].clientHeight  
    ];  
  }  
  return Tamanyo;  
}  
window.onresize = function() {  
 posiciona();
}; 

function posiciona(){
var Tam = TamVentana();  
}

function principal(){
	
	$("#frase1").fadeOut(400).delay(400);
	setTimeout("muestraFrase2();",1000);
	
}

function muestraFrase2(){
	
	$("#frase2").fadeIn(400).delay(400);
	
}
function ocultaFrase2(){
	
	$("#frase2").fadeOut(400).delay(400);
	setTimeout("principal2();",1000);
	
}

function principal2(){
	
	$("#frase1").fadeIn(400).delay(400);
	setTimeout("principal();",1500);
	
}

function validateField(){
	// var campos = document.frm-change-data.elements;
	var flag=true;
//	for(var i=0;i<campos.length;i++){
	
	var i = 0;
	
	$("#frm-change-data input, #frm-change-data select, #frm-change-data password").each(function(){
		
		
		if( ( typeof $(this).attr('type') ) === 'undefined' ){
			
		}else{
			
			if(($(this).attr('type')).toUpperCase()=='SELECT' && !$(this).is(":hidden") ){
				
					
					if($(this).val() != null && ($(this).val()=="0" || $(this).val()=="")){
						alert("Faltan campos por llenar");
						$(this).focus();
						flag=false;
						return false;
					}
			}
			if($(this).attr('type').toUpperCase()=='TEXT' && !$(this).is(":hidden") ){
				
					if($(this).val()!=null && ($(this).val()=="0" || $(this).val()=="")){
						alert("Faltan campos por llenar");
						$(this).focus();
						flag=false;
						return false;
					}
			
			}
			if($(this).attr('type').toUpperCase()=='PASSWORD'  && !$(this).is(":hidden") ){
				
				if($(this).val()!=null && ( $(this).val()=="")){
					alert("Faltan campos por llenar");
					$(this).focus();
					flag=false;
					return false;
				}
		
			}
		}
		i++;
		
	});
	
	if(flag){
		displayMessageProcessing('msgprocessing',false);
		return flag;
	}
	else{
		return flag;
	}
}

function validaLenght(myId , len){
	var element = $("#"+myId);
	
	if((element.val()).length > 0 ) {
	
		if((element.val()).length <= parseInt(len)){
			alert("La respuesta debe contener más de "+len+" caracteres");
			
			$("#valAns").val(0);
			validaBtn();
			
			element.val("");
			$("#"+myId).focus();
			
			return false;
			
		}else{
			
			$("#valAns").val(1);
			validaBtn();
			return true;
			
		}
	}else{
		return false;
	}
	
}

function validaConfRes( idResp,  idConfResp ){
	if( ( $('#'+idConfResp).val() ).length >0  || ( ($('#'+idResp).val()).length >0 ) ){
		
		if( $('#'+idResp).val() != $('#'+idConfResp).val() ){
			alert("El valor de las respuestas es diferente. Por favor revise sus respuestas.");
			$('#'+idResp).val("");
			$('#'+idConfResp).val("");
			$('#'+idResp).focus();
			return false;
		}
		
		return true;
		
	}	
}


function validaSelect(myId){
	var element = $("#"+myId);
	if(element.val()=="0" || element.val()=="" )
		$("#valQuest").val(0);
	else
		$("#valQuest").val(1);
	validaBtn();
}

function validaBtn(){
	var res = true;
	
	if(parseInt($("#valAns").val()) != 1){
		res = false;
	}
	
		
	if( parseInt($("#valQuest").val()) != 1){
		res = false;
	}
		
	if( parseInt($("#valMail").val()) != 1 ){
		res = false;
	}
		
	if(parseInt($("#valPass").val()) != 1){
		res = false;
	}
	
	if(res)
	{
		
	}
	else{
	}
	return res;
}