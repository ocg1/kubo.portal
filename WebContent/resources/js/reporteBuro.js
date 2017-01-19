console.log("reporteBuro.js");

function customHTMLContConsult(arr) {
		  var str = '<div style="padding:5px 5px 5px 5px;">' +
				      '<table id="consult_id">' +
						  '<tr>' +
							  '<td style="text-align: center;" colspan="3" ><b>Consulta a Buró</b></td>' +
						  '</tr>'  ;
						  
						  
			for(var i = 0 ; i < arr.length ; i++){
						    		  
				str +=	  
					      '<tr>' +
						      '<td style="text-align: left"><b>'+(arr[i])[0]+'</b></td>' +
						     
						      '<td>' + (arr[i])[1] + '</td>' +
					      
						      '<td>' + (arr[i])[2] + '</td>' +
					      '</tr>' ;
			}
			
			
			
				  str += '</table>' +
		      	'</div>';
		      	
		   return str;
	}

//sic = indicador circulo buro
function customHTMLCont(cuenta,limiteCred,credMax,fecha,saldo,sic,frecuencia) {
	  var strRes =  '<div style="padding:5px 5px 5px 5px;position: relative;">' +
		  '<div style="position: absolute; z-index: 101; top: -13px;right: -13px; width: 30px; height: 30px;" >'+
		  	'<img src="../resources/img/close.png" onclick="hideTooltip();" /> ' + 
		  '</div>' ;
			      
	  
	  if(fecha.indexOf('encimient') > 0){
		  strRes += '<table id="medals_layout" style="background-color: #F2F2F2 !important;width: auto; height: auto; overflow:auto;">'+
		  '<tr>' +
		      '<td colspan="2" style="text-align: center"><b>PROYECCIÓN</b></td>' +
	      '</tr>';  
	  }else{
		  strRes += '<table id="medals_layout">';
	  }
	 
	  
	  strRes += '<tr>' +
					      '<td colspan="2" style="text-align: center">'+cuenta+'</td>' +
				      '</tr>' +
				      '<tr>' +
				      		'<td colspan="2" style="text-align: left; font-weight: bold;">'+sic+'</td>' +
				      '</tr>' +
				      '<tr>' +
				      '<td>Frecuencia: </td>' +
				      		'<td><b>' + frecuencia + '</b></td>' +
				      '</tr>' +
			      	  '<tr>' +
					      	'<td colspan="2" style="text-align: left"><b>'+fecha+'</b></td>' +
				      '</tr>' +
				      '<tr>' +
					      '<td>Límite de Crédito:</td>' +
					      '<td><b>' + limiteCred + '</b></td>' +
				      '</tr>' +
				      '<tr>' +
					      '<td>Crédito Máximo:</td>' +
					      '<td><b>' + credMax + '</b></td>' +
				      '</tr>' ;
	  if(cuenta.split("-")[0]=='A'){
		  strRes +=   '<tr>' +
				      '<td>Saldo Actual:</td>' +
				      '<td><b>' + saldo + '</b></td>' ;
	  		}
	  strRes +=      '</tr>' +
			      '</table>' +
	      	'</div>';
	      	
	 return strRes;
	      	
}

function validateNewRecommendation(person,pririty,note){
	
	var elementType=$("#"+person);
	var elementPriority=$("#"+pririty);
	var elementNote=$("#"+note);
	
	var flag = false;
	
	elementType.parents('.editClass').find('.formError').remove();
	
	if(elementType.val()!="" && elementPriority.val()!="" && elementNote.val()!=""){		
		flag =  true;
	}else if(elementType.val()==""){
		alert( 'Seleccione la persona.' );
		flag =  false;
	}else if( elementPriority.val()==""){
		alert('Seleccione la recomendación.' );
		flag =  false;
	}
	else{
		alert('Capture la descripción.');
		flag =  false;
	}
	
	if( flag ){
		
		displayMessageProcessing('msgprocessing', false);
	}
	
	return flag;
	
}

function changeNameCount( key, valores){
	
	//buscar 'arreglo con la llave' dentro de ChartBackBean.java para ver el orden de los campos
	
	var jValores = JSON.parse(valores);  
	
     
   
	$.fancybox({
		'width' : 400,
		'padding' : '0',
		'autoDimensions':false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'speedIn' : '20',
		'speedOut' : '10',
		'modal' : true,
		'type' : 'inline',
		'scrolling' : 'no',
		'centerOnScroll' : true,
		'href': '#dvFrmChange',
		'showCloseButton': 'true',
		'enableEscapeButton': 'true',
		'onComplete' : function(){
			 $('#fancybox-content').height('auto');
			 $('#fancybox-content').children().eq(0).css('height','auto');
			 
			 var htmlStr = "<table><tr><td style='width: 125px;'>Nombre Actual:</td><td style='width: 100px;font-weight: bold;'>"+jValores[0].Entity+"</td></tr></table>";
			 
			 $("#dvContActualName").html( htmlStr );
			 
			 $("#prevName").val(jValores[0].Entity);
			 
			 var jKey = JSON.parse(key);
			 var nuevaCadena = JSON.stringify(jKey); 
			 
			 nuevaCadena = nuevaCadena.replace("[","").replace("]","");
			 
			 $("#jsonKey").val(nuevaCadena);
			 $("#jsonKey").blur();
	    },
	    'onClosed' : function(){
	    	
	    },
		'overlayColor': '#333333'
	});
	
	
}

function mostrar_tooltip_reporte()
{			
	$("div.tooltip_icon").show();
}	

function initChartReload()
{
	console.log("initChartReload(): INIT");
	
	$("#initChartReload").trigger("click");	
}









