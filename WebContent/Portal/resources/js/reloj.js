

function cambiaReloj(){
    momentoActual = new Date();
    hora = momentoActual.getHours();
    minuto = momentoActual.getMinutes();
    segundo = momentoActual.getSeconds();
    if(parseInt(segundo)<10)
    	segundo = "0"+segundo;
    if(parseInt(minuto)<10)
    	minuto = "0"+minuto;
    if(parseInt(hora)<10)
    	hora = "0"+hora;
    horaImprimible = hora + " : " + minuto + " : " + segundo;
    
    
    if(parseInt(hora)>=5 && parseInt(hora)<12)
    	 horaImprimible ="¡Buenos Días";
    else if(parseInt(hora)>=12 && parseInt(hora)<19)
   	 	horaImprimible ="¡Buenas Tardes";
    else
    	horaImprimible ="¡Buenas Noches";
    $(".reloj").html(horaImprimible);
    setTimeout("cambiaReloj()",1000) ;
} 

function inicio(){
	cambiaReloj();
	parpadea();
}

function parpadea(){
	/*$("#anuncio").fadeTo("fast",1,function() {
      aparece();
    });*/
	document.getElementById("anuncio").style.visibility="visible";
	setTimeout("aparece()",500);
}
function aparece(){
	/*$("#anuncio").fadeTo("fast",0,function() {
		parpadea();
    });*/
	document.getElementById("anuncio").style.visibility="hidden";
	setTimeout("parpadea()",500);
	
}
function creaComparacion(){
	var numero = document.getElementById("numero").value;
	if(parseInt(numero)<6){
		var myId = 'compara'+numero+'';
		var myDiv = document.createElement("div");
		myDiv.setAttribute("id", myId);
		var comp = document.getElementById("compara");
		var w = parseInt($('#compara').width())+335;
		$('#compara').width(w);
		comp.appendChild(myDiv);
		$('#'+myId).attr('class', "contTablesSim");
		document.getElementById(myId).innerHTML="<div id='comparacion"+numero+"' class='compara'><div style='float: left; width: 200px; margin-left: 50px; margin-right: auto; margin-bottom: 5px; font-size:1.6em; color: #439539; font-weight: bold;overflow: hidden;'>Comparación "+numero+"</div><div onclick='javascript:blindAction("+numero+");' style='margin-bottom: 5px; width: 8px; float:right;' class='blindAction'>X</div>"+getForm(numero)+"</div>";
		document.getElementById("numero").value = parseInt(numero)+1;
		
		
		//$.scrollTo(800,"#"+myId, { axis:'x' });
		//$.scrollTo('#'+myId+'', 800, { axis:'x' });
		$('#conCompara').scrollTo('+=560px', 800, { axis:'x' });

	}

}

function getForm(numero){
	var myTable="<form id='frm"+numero+"'> " +
	

	"<div id='contTableCompara"+numero+"' class='tableCompara'><table>";
	
	myTable+="<tr><td colspan='2'><div class='labelsStl' style='font-size: 1.3em; text-align: center;'>Institución</div></td></tr>"+
	"<tr><td colspan='2'><input type='Text' id='nameInst"+numero+"' size='15' class='elementStl help'  title='Ingresa el nombre de la institución que te otorgó el préstamo' ></input> </td></tr>"+
				
	"<tr> <td></td></tr>" +
				"<tr><td colspan='2'><div class='labelsStl' style='font-size: 1.3em; text-align: center;'>Préstamo</div></td></tr>"+
				"<tr><td  colspan='2'><input type='Text' size='15' onkeyup='javascript:format(this);' class='elementStl help' title='Ingresa el monto que te prestó la institución' style='text-align: right;' id='monto"+numero+"' ></input> </td></tr>"+
				
				"<tr> <td  colspan='2'></td></tr>" +
				"<tr><td><div class='labelsStl' style='font-size: 1.3em; text-align: center;'>¿Cuánto pagas?</div></td>"+
				"<td><div class='labelsStl' style='font-size: 1.3em; text-align: center;'>Frecuencia</div></td></tr>"+
				
				"<tr>" +
				"	<td style='width: 60px;'>" +
				"		<input type='Text' id='cuota"+numero+"' size='15' onkeyup='javascript:format(this);' class='elementAddresMin  help' style='width: 50px; text-align: right;'  title='Ingresa el monto de cada pago que realizas' ></input> " +
				"	</td>"+
               "<td>" +
               "<div class='styled-select-Month' style='width: 120px;'>" +
               "<select id='freq"+numero+"' onchange='javascript:changeName("+numero+");' class='elementStlSelMonth' style='margin-left: 0px !important;position: absolute; left: 0px; width: 150px !important;' >"+
							"<option value='0'> </option> " +
							"<option value='1'> Semanal </option> " +
							"<option value='2'> Catorcenal </option> " + 
							"<option value='3'> Quincenal</option> " +
							"<option value='4'> Mensual</option> " +
						"</select>				" +
				"</div>"+
				 " </td></tr>"+
				
				"<tr> <td  colspan='2'></td></tr>" +
				"<tr><td  colspan='2'><div id='titleTerm"+numero+"' class='labelsStl' style='font-size: 1.3em; text-align: center;'>Plazo</div></td></tr>"+
				"<tr><td  align='right'>"+
                	 "<input type='Text' size='15' class='elementAddresMin help' style='text-align: right'  title='Ingresa el plazo total en el cual te fue otorgado el préstamo de acuerdo a la frecuencia'  id='plazo"+numero+"' ></input>"+  
               " </td>" +
               "<td align= 'left'><div id='termDesc"+numero+"' class='labelsStl' style='font-size: 1.3em; text-align: left;'>Meses</div></td>" +
               "</tr>"+
               
               "<tr> <td></td></tr>" +
               
				 "<tr><td  colspan='2'>"+
				"<div class='buttonPR'>"+
				"	<div id='callDesc'>"+
				"		<div class='botSimComp' onclick='javascript:muestraPropuesta("+numero+")'>"+
				"			<div  class='labelsStl' style='font-size: 1.3em; text-align: center;font-weight: bold;'>¡Compáranos!</div>"+
				"		</div>" +
				"	</div>"+
				"</div></td></tr></table></div>" +
				"<div id='espera"+numero+"' class='espera'><img src='../resources/img/ajax-loader.gif'><div id='esperaCont"+numero+"' style='height: 200px;line-height: 60px !important;'></div></div>";
	
	
myTable+="<div id='contTableComparaKubo"+numero+"' class='tableComparaKubo'>"+
				"</div>";
	myTable+="</form>";
	
	
	
	return myTable; 
}

function blindAction(numero){
    $("#compara"+numero).hide( "blind", 
                {direction: "horizontal"}, 1000,function(){
                	var w = parseInt($('#compara').width())-335;
                	$('#compara').width(w);
                } );
    var myDiv = document.getElementById("compara"+numero);
	var comp = myDiv.parentNode;
	comp.removeChild(myDiv);
	document.getElementById("numero").value = parseInt(document.getElementById("numero").value)-1;
	
 }

function muestraPropuesta(numero){
	if(calculaTotal(numero))
	{
		$("#contTableCompara"+numero+"").hide( "fade", 1000,function(){
			 $("#espera"+numero+"").show( "fade", 1000 );
		 } );
		
		var c = $("#cuota"+numero+"").val();
			$("#frmSimHide\\:cuota").val(c);
			$("#frmSimHide\\:cuota").blur();
		
		var m = $("#monto"+numero+"").val();
			$("#frmSimHide\\:monto").val(m);
			$("#frmSimHide\\:monto").blur();
		
		var f = $("#freq"+numero+"").val();
			$("#frmSimHide\\:freq").val(f);
			$("#frmSimHide\\:freq").blur();
		
		var p = $("#plazo"+numero+"").val();
			$("#frmSimHide\\:plazo").val(p);
			$("#frmSimHide\\:plazo").blur();
		
		var i = $("#nameInst"+numero+"").val();
			$("#frmSimHide\\:nameInst").val(i);
			$("#frmSimHide\\:nameInst").blur();
		
			$("#frmSimHide\\:comparaAction").blur(); //llama a la accion
		
		 
			$("#numeroSel").val(numero); //guardamos el numero del elemento en que nos quedamos
	 
	}else{
		alert("¡Revise los montos capturados, y la frecuencia seleccionada, ya que el cálculo no concuerda con el total!");
	}
 }

function calculaTotal(numero){
	
	var dc = parseFloat($("#cuota"+numero+"").val().replace(",",""));
	var dm = parseFloat($("#monto"+numero+"").val().replace(",",""));
	var df = parseFloat($("#freq"+numero+"").val().replace(",",""));
	var dp = parseFloat($("#plazo"+numero+"").val().replace(",",""));
	
	var num = dp;
	
	if(df == 3){ //Quincenal
		num = dp*2;
	}/*
	if(df == 4){ //Mensual
		num = dp;
	} */
	
	var total = 0;
	
	total = dc * num;
	
	if(total<=dm){
		return false;
	}else
		return true;
	
}

function muestraComparacion(){
	var numero =  $("#numeroSel").val();
	var res = $("#frmSimHide\\:resultado").val();
	var freq="";
	
	var df = parseFloat($("#freq"+numero+"").val().replace(",",""));
	
	if(df == 1){//Semanal
		freq="semanas";
	}
	if(df == 2){//Catorcenal
		freq="catorcenas";
	}
	if(df == 3){ //Quincenal
		freq="meses";
	}
	if(df == 4){ //Mensual
		freq="meses";
	} 
	
	var myTable ="<div >" +
			"<table class='contComparaTable' style='width: 280px;' >" +
			"" +
			"<tr>" +
				"<td colspan='2' >" +
					"<div id='msg"+numero+"' style='font-size: 1.3em; padding-top: 5px; text-align: center;'>Con <span style='color: #439539;'>kubo.financiero</span> vas a pagar <span  style='font-size: 1.6em;color: #439539'><b>" +(res.split("::"))[8]+" MENOS</b></span>.<br /> Cantidad que podrás ahorrar e invertir en tu familia.</div>"+ 
				"</td>" +
			"</tr>" +
			"" +
			"<tr><td style='vertical-align:top;'>"+
		"<table class='comparaTableRes'>" +
			"<tr>" +
			"	<td class='titulo' style='text-align: center; color: #439539; font-weight: bold;'>"+(res.split("::"))[7]+"</td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td style = 'text-align: center; padding-top: 0px;'>Monto</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[0]+"</b><br /></td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td style = 'text-align: center; padding-top: 0px;'>Plazo</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[4]+" "+freq+"</b><br /></td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Frecuencia</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[3]+"</b><br /></td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;padding-bottom: 0px;vertical-align:bottom;'><br /></td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;padding-top: 0px;vertical-align:top;'><br /></td>" +
			"</tr>" +

			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 5px;'>Cuota</td>" +
			"</tr>" +
			"<tr >" +
			"	<td class='couta' style = 'text-align: center;'>" +
			"		<span style='font-size: 1.15em;	font-weight: bold; color: #439539;'> <b>"+(res.split("::"))[1]+"</b></span><br /></td>" +
			
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Total a pagar</td>" +
			"</tr>" +
			"<tr>" +
			"	<td class='total' style = 'text-align: center;'>" +
			"		<span style='font-size: 1.25em;	font-weight: bold; color: #439539;'><b>"+(res.split("::"))[5]+"</b></span></td>" +
			
			"</tr>" +
			
		"</table>" +
		"</td><td>" +
		"<table class='comparaTableRes'>" +
			"<tr>" +
			"	<td class='titulo' style='text-align: center; color: #439539; font-weight: bold;'>kubo.financiero</td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Monto</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[0]+"</b><br /></td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Plazo</td>" +
			"</tr>" +
			"<tr>" +
			
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[4]+" "+freq+"</b><br /></td>" +
			"</tr>" +
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Frecuencia</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;'><b>"+(res.split("::"))[3]+"</b><br /></td>" +
			"</tr>" +
		
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;padding-bottom: 0px;vertical-align:bottom;'>Tasa</td>" +
			"</tr>" +
			"<tr>" +
			"	<td style = 'text-align: center;padding-top: 0px;vertical-align:top;'><b>"+(res.split("::"))[11]+"% sin IVA<sup style='font-size:0.8em'>(a)</sup></b><br /></td>" +
			"</tr>" +				

			"<tr>" +
			"	<td  style = 'text-align: center; padding-top:0px;'>Cuota</td>" +
			"</tr>" +
			"<tr >" +
			"	<td class='couta' style = 'text-align: center;'><span style='font-size: 1.15em;	font-weight: bold; color: #439539;'>"+(res.split("::"))[2]+"</span><br /></td>" +
			"</tr>" +
			
			
			"<tr>" +
			"	<td  style = 'text-align: center; padding-top: 0px;'>Total a pagar</td>" +
			"</tr>" +
			"<tr >" +
			
			"	<td class='total' style = 'text-align: center;'><span style='font-size: 1.25em;	font-weight: bold; color: #439539;'>"+(res.split("::"))[6]+"</span></td>" +
			"</tr>" +
			
		"</table>" +
		"</td>" +
		"</tr>" +
		"</table>" +
		"<div style='position: absolute; bottom: 24px; right:4px;font-size: 1.0em;font-weight: normal;color: #439539;'>" +
			"<a class='refTbAmort' href='/Kubo/jsf/tabla_de_amortizacion.xhtml?monto="+((res.split("::"))[0]).replace("$","")+"&term="+((res.split("::"))[4])+"&rate="+(res.split("::"))[11]+"&payment="+((res.split("::"))[2]).replace("$","")+"&frequency="+((res.split("::"))[3])+"&comision=5.0&totalPayment="+((res.split("::"))[6]).replace("$","")+"&cat="+(res.split("::"))[9]+"' target='popup' onClick='window.open(this.href, this.target, \"width=544,height=400,scrollbars=1\"); return false;'>"+
				"Tabla de Amortización" +
			"</a>"+
		"</div>"+
		"<div style='position: absolute; bottom: 4px; right:4px;font-size: 1.49em;font-weight: bold;'>CAT "+(res.split("::"))[9]+"% sin IVA<sup style='font-size:0.8em'>(b)</sup></div>"+
		"</div>";
	
	$("#contTableComparaKubo"+numero+"").html(myTable);
	$("#legalString").html("<sup style='font-size:0.9em'>(a)</sup>Tasa fija anual simple. <br />" +
			"<sup style='font-size:0.9em'>(b)</sup>"+
			
			"Costo Anual Total. Para fines informativos y de comparación. "+
    		"Fecha de cálculo: "+(res.split("::"))[10]+". Los <a  href='avisolegal.jsf?page=condiciones' class='ahref' >términos, condiciones</a> y <a href='leyTransparencia.jsf?page=comisiones' class='ahref'>comisiones</a> aplicables al "+
    		"Crédito Personal kubo.financiero son los incluidos en el <a href='leyTransparencia.xhtml?page=contrato' class='ahref'>Contrato de Adhesión</a> relacionado en la solicitud. Puedes consultar nuestros <a  href='avisolegal.jsf?page=requisitos' class='ahref' >requisitos y modalidades de contratación</a>.	" +
			
			
			"<div style='font-size: 1.4em !important; margin-top: 20px !important; margin-left: 30px !important;'>"+
   			"Al solicitar tu crédito considera:"+
			"<ol>"+
			"	<li>Incumplir tus obligaciones te puede generar comisiones e intereses moratorios.</li>"+
			"	<li>Contratar créditos por arriba de tu capacidad de pago puede afectar tu historial crediticio.</li>"+
			"	<li>En caso de que el préstamo requiera avalista, obligado solidario o coacreditado: El avalista, obligado solidario o coacreditado responderá como obligado principal frente a la Entidad Financiera.</li>"+
			"</ol>"+
   		"</div>");
	
	$("#legalString").fadeIn(500);
	
	var s= "";
	
	var r = ((res.split("::"))[8]);
	
	if((r.indexOf('-'))!= (-1)){
		s = "<br /> <span  style='font-size: 3.6em;color: #439539'><b>Lo sentimos</b></span>";
		$("#msg"+numero+"").html("Lo sentimos, por el momento <span style='color: #439539;'>kubo.financiero</span> no es tu opción");
	}
	else
		s = "<span style='font-size: 2.6em; color: #439539;'>Que harías con </span> <br /> <span  style='font-size: 3.6em;color: #439539'><b>" +r+"</b></span>";
	
	//$("#espera"+numero+"").css({'heigth':'200px','top':'20px','left':'20px'});
	
	$("#esperaCont"+numero+"").html(s);
	
	$("#espera"+numero+"").delay(3000).hide( "fade", 700,function(){
		$("#contTableComparaKubo"+numero+"").show( "fade", 500 );
	});
}

function muestraPropuestaFin(numero){
	
}

function changeName(numero){
	var df = parseFloat($("#freq"+numero+"").val().replace(",",""));

	if(df == 1){//Semanal
		$("#titleTerm"+numero+"").html("Número de semanas");
		$("#termDesc"+numero+"").html("Semanas");
	}
	else if(df == 2){//Catorcenal
		$("#titleTerm"+numero+"").html("Número de catorcenas");
		$("#termDesc"+numero+"").html("Catorcenas");
	}
	else if(df == 3){ //Quincenal
		$("#titleTerm"+numero+"").html("Número de meses");
		$("#termDesc"+numero+"").html("Meses");
	}
	else if(df == 4){ //Mensual
		$("#titleTerm"+numero+"").html("Número de meses");
		$("#termDesc"+numero+"").html("Meses");
	}
	else{
		$("#titleTerm"+numero+"").html("Plazo");
		$("#termDesc"+numero+"").html("Meses");
	}
}


