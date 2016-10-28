console.log("request.js");

function validaLength(){
	if( 18 > $("#txt-mxclabe").val().length ){
		if($("#txt-mxclabe").val().length > 0){
			alert("El campo de contener al menos 18 carateres");
			$("#txt-mxclabe").val("");
		}
	}
}


function funcWait(){
	$("#dvTotalExpensesRes").hide();
	$("#dvTotalExpensesWait").show();
	return true;
}
function funcWaitRes(){
	$("#dvTotalExpensesWait").hide();
	$("#dvTotalExpensesRes").show();
	inicializaModal();
	return true;
}

function funcWaitIncome(){
	$("#dvTotalIncomeRes").hide();
	$("#dvTotalIncomeWait").show();
	return true;
}
function funcWaitResIncome(){
	$("#dvTotalIncomeWait").hide();
	$("#dvTotalIncomeRes").show();
	inicializaModal();
	return true;
}

function alertjs(v){
	alert(v);
}

function confirmCartera(carteraName){
	
	if(confirm("¿Seguro que quiere convertir el crédito en "+ carteraName +"? ")){
		displayMessageProcessing('msgprocessing', false);
		return true;
	}else{
		return false;
	}
}

function regresaNewconsulting(){
	// closeMessageProcessing();
	// reInicia();
	window.location.reload();
}

function mostrar_tooltip_ayuda()
{
	$("div.tooltip_icon").show();
}

function complete_set_iniciales_negotiation(xhr, status, args)
{
	var monto = args.monto_inicial;
	var plazo = args.plazo;
	
	$("#ammountNeg").val(monto);
	$("#termNeg").val(plazo);
	
	inicializaModal();
}

function inicializaModal()
{		
	$("#clientPorcent").fancybox(
	{
		'width' : '90%',
		'height' : '100%',
		'padding': '0',
		'margin' : '0',
		'autoScale' : 'false',
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'scrolling' : 'auto',
		'centerOnScroll' : 'true',
		'overlayColor': '#333333',
		
		'onStart': function() 
		{
			$("#porcentClientAction").click();
			$("#porcentClientDis").css("display","block");
			return true;
		},
		
		'onClosed': function() 
		{
			$("#porcentClientDis").css("display","none");
			$("#claenporcentClientAction").click();
			return true;
		}
	});				
	
	$("#mesaPorcent").fancybox(
	{
		'width' : '90%',
		'height' : '100%',
		'padding': '0',
		'margin' : '0',
		'autoScale' : 'false',
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'type' : 'inline',
		'scrolling' : 'auto',
		'centerOnScroll' : 'true',
		'overlayColor': '#333333',
		'onStart': function() 
		{
			$("#porcentControlAction").click();
			$("#porcentClientDis").css("display","block");
			return true;
		},
		'onClosed':function() 
		{
			$("#porcentClientDis").css("display","none");
			$("#claenporcentClientAction").click();
			return true;
		}
	});
	
	
	$('#negotiationBtn').click(function(e)
	{					
		$("#btnInitialize").click();
		$("#negotiationDv").slideToggle("slow");
	});
	
	
	$('#dvBtnNwCnslt1').click(function ()
	{
		$('#dvBtnNwCnslt1').fadeOut(500,function(){
			$('#dvCntBtnCsl').show('blind',500);
		});
	});
		
	$('#dvCancelConsultation').click(function ()
	{
		$('#dvCntBtnCsl').hide('blind',500,function(){
			$('#dvBtnNwCnslt1').fadeIn(500);
		});
	});
	
	$('#divNewConsultation').click(function()
	{			
		if(confirm("¿Cuentas ya con la copia de la credencial de elector y la hoja de autorización firmada?"))
		{
			$("#btnNewConsultation").click();	
			return true;
		} else {
			return false;
		}
	});
	
	$("#lnkAddTutor").click(function(){
		$("#dvMsgNoTutor").fadeOut("slow",function(){
			$("#dvSelTutor").fadeIn("slow");
		});
	} );
	
	
	
	return true;
}	
function closeNegotiation () {

	$("#btnInitialize").click();
	$("#negotiationDv").slideUp("slow");

}
function muestraResTutor(){
	
	$("#dvtableTutor").fadeOut("slow",function(){
		$("#dvDatosTutorSel").show();
	});
	
}

function cancelTutor(){
	$("#dvSelTutor").fadeOut("slow",function(){
		$("#dvMsgNoTutor").fadeIn("slow");
	});
}

function regresaTutor(){
	
	$("#dvDatosTutorSel").fadeOut("slow",function(){
		$("#dvtableTutor").fadeIn("slow");
	});
	
}

function onCompleteAddInvestor( xhr, status, args ){
	
	reInicia();
	closeFancy();
	
	console.log("onCompleteAddInvestor(): OK");
	
	location.reload();
	
	if( !args.isValidInvestor )
	{		
		 $("#dvSummaryCont").delay(500,function()
		 {
				
			 alert(""+args.investorMessage);
				
			}
		 );
		
	}
}

function reInicia(){
	closeFancy();
	inicializaModal();
	map=null;
	initSummaryRequest();
	par();
}

function confirmCondiciones002(){
	$('.modificarCondiciones .btnGris').click(function(){			
		
		$(".modificarCondiciones").removeClass("show");
		$(".velo").fadeOut();
		return false;
	});
	
	$('.modificarCondiciones .btnNaranja').click(function(){	
		displayMessageProcessing('msgprocessing', true);
		$(".modificarCondiciones").removeClass("show");
		$(".velo").fadeOut();
		$(" #negotiationDv .close_negotiation").trigger("click");
		  
	});
	$(".modificarCondiciones").addClass("show");
	$(".velo").fadeIn();
	
	return false;
	
}
function closeCondiciones(){
	$('#negotiationBtn').click();
}

function showRes(){
	$("#loaderSimNeg").css("display","none");
	$("#resultadosSimNeg").css("display","block");
	inicializaModal();
	return true;
}
function loader(){
	
	$("#resultadosSimNeg").css("display","none");
	$("#loaderSimNeg").css("display","block");
	
	 return true;
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

function simOnStart(){
	if( validaMontoMin('ammountNeg','simulator')   ){

			if(!validaPlazo('termNeg')){
				//showRes();
				return false;
			}else{
				return true;
			}
	}
	
}

function toggledvAddDocument(){
	 $("#dvAdjDocSel").slideToggle("slow");
	 
	 
}

function finalizeAddDocument(){
	$("#dvContSectionDocumentaction").show();
	//alert("Documento adjuntado satisfactoriamente");
	$("#successUpload").slideDown("slow");
	setTimeout("hideSuccessUpload()",5000) ;
}
function hideSuccessUpload(){
	$("#successUpload").slideUp("slow");
} 

function showDetailIncome(){

	$(".detail_income_bussines").fadeIn();
	var object=$("#dvContDetailIncome");
		object.slideToggle("slow");			
	 	object.find("tbody td").css("padding","0 0 10px 10px");
	 	object.find("thead th").css("padding","0");
	 	object.find("tbody td.subtitle").attr("colspan","3");

	 	
}

function  closeDetailIncome(){
	var object=$("#dvContDetailIncome");
	$(".velo").fadeOut();
	$(".detail_income_bussines").fadeOut();
	object.slideToggle("slow");	
}
function reloadDetailIncome(){
	var object=$("#dvContDetailIncome");
	object.find("tbody td").css("padding","0 0 10px 10px");
 	object.find("thead th").css("padding","0");
 	object.find("tbody td.subtitle").attr("colspan","3");
 	inicializaModal();
}

function inicializaEdoCta()
{
	
	$( ".clSolInv" ).off();
	$('.detailsTabAllControlTable').removeClass("clSolInv");
	
	$('.profileControlTable').show();
	$('#carterakubo').show();
	$("#dvCntMyInvestment").hide();
	
	$('.detailsTabAllControlTable').addClass("clEdoCtaInv");
	
	$('.detailsTabAllControlTable').text( 'Ver Estado de Cuenta');
	
	$('.detailsTabAllControlTable').css("top","-26");
	
	$("#dvCntProfile").removeClass("clCntProfileWidth");
	
	$("#dvCntSearchProfile").removeClass("clCntSearchProfile");
	$("#dvCntTitleProfile").removeClass("clCntSearchProfile");
	
	$('.clEdoCtaInv').click(function(e)
	{
		e.preventDefault();
		
		inicializaSolInv();
		
	});
}


PrimeFaces.locales['es'] = {
	    closeText: 'Cerrar',
	    prevText: 'Anterior',
	    nextText: 'Siguiente',
	    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
	    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
	    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
	    dayNamesMin: ['D','L','M','X','J','V','S'],
	    weekHeader: 'Semana',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix: '',
	    timeOnlyTitle: 'Sólo hora',
	    timeText: 'Tiempo',
	    hourText: 'Hora',
	    minuteText: 'Minuto',
	    secondText: 'Segundo',
	    currentText: 'Fecha actual',
	    ampm: false,
	    month: 'Mes',
	    week: 'Semana',
	    day: 'Día',
	    allDayText : 'Todo el día'
	};

function showBCDesc(){
	
	$.fancybox({		
		padding : 	15,
		margin 	:	0,
		width	:	800,
		height  :	400,
		transitionIn: 'none',
		transitionOut : 'none',	
		type : 'inline',
		href: '#divBCDesc',
		overlayColor: '#333333'

	});
}


function initActiveInvestor(){
	if(confirm("¿Está seguro de querer activar al cliente?")){
		displayMessageProcessing('msgprocessing',false);
		return true;
	}else{
		return false;
	}
}

function intCargaPhonesREF( thisId ){
	var idDiv=$("#"+thisId);
	
	
	if(!idDiv.is(":hidden")){
			
		idDiv.fadeOut( "slow" );
		//alert("false");
		return false;
		
	}
	else{
		//alert("true");
		return true;
	}
}

function intCargaPhones(thisId){
	
	var idDiv=$("#"+thisId);
	
	
	if(!idDiv.is(":hidden")){
			
		idDiv.fadeOut( "slow" );
		//alert("false");
		return false;
		
	}
	else{
		//alert("true");
		return true;
	}
}

function completeActiveInvestor(){
	
	closeFancy();
	window.location.reload(true);
	
}

function muestraCancel(){
	$("#dvSummaryCont").fadeOut('slow',function(){
		$.scrollTo('#header',300, { axis:'y' });
		$("#dvCntCancelAction").fadeIn('slow');
	});
}

function muestraSolicitud(){
	$("#dvCntCancelAction").fadeOut('slow',function(){
		$.scrollTo('#header',300, { axis:'y' });
		$("#dvSummaryCont").fadeIn('slow');
	});
}

function initCancelAccount(){
	if( confirm("La cuenta será CANCELADA ¿ Deseas continuar ?") ){
		displayMessageProcessing('msgprocessing', false);
		return true;
	}else{
		return false;
	}
}

function completeCancelAction( xhr, status, args ){
	
	window.location.reload();
	
}

function validaAltaMenor( xhr, status, args ){
	
	var SIN_TUTOR 		  = 1;
	var TUTOR_SIN_ACTIVAR = 2;
	var EXITO = 3;
	var ERROR = 4;
	
	if( parseInt(args.resultado) == SIN_TUTOR ){
		alert( "El usuario es un menor de edad y no cuenta con un tutor asignado. Es necesario asignarle uno." );
		closeFancy();
	}else if( parseInt(args.resultado) == TUTOR_SIN_ACTIVAR ){
		if ( confirm( "El usuario tiene asignado a "+args.nombreTutor+" como tutor, pero este aun no se encuentra activado. ¿Desea activar ambos clientes?" ) ){
			$("#btnActivaAmbos").click();
		}else{
			closeFancy();
		}
		
	}else if( parseInt(args.resultado) == EXITO ){
		alert( args.resultadoMsg );
		closeFancy();
	}else if( parseInt(args.resultado) == ERROR ){
		alert( args.resultadoMsg );
		closeFancy();
	}	
}

function initActiveAccount(){
	if( confirm("La cuenta será ACTIVADA ¿ Deseas continuar ?") ){
		displayMessageProcessing('msgprocessing', true);
		return true;
	}else{
		return false;
	}
}

function personalAlerts(){
	
	$.fancybox({		
		'padding'		:15,
		'margin'		:0,
		'width'			:800,
		'height' 		:400,
		'transitionIn'	:'none',
		'transitionOut'	:'none',	
		'type'			:'iframe',
		'href'			:'templates/alertsContent.xhtml',
		'overlayColor'	:'#333333',
		'onClosed':function() {
			$("#btnCargaAlerts").click();
		}

	});
	
	
}

function proyectsAlerts(){
	
	$.fancybox({		
		'padding' 		: 	15,
		'margin' 		:	0,
		'width'			:	800,
		'height'  		:	400,
		'transitionIn'	: 'none',
		'transitionOut'	: 'none',	
		'type'			: 'iframe',
		'href'			: 'templates/alertsContent.xhtml',
		'overlayColor'	: '#333333',
		'onClosed':function() {
			$("#btnCargaAlerts").click();
		}

	});
	
}

function checkSendSMSContract(){
	
	alertify.confirm("¿Deseas enviar SMS para firma de contrato?", function (e) {
		
		if (e) {	
			setTimeout(function(){
				displayMessageProcessing('msgprocessing', true);
				$("#btnSendContract").click();
			},500);
			return true;
		} else { 
			
			return false;
		}
		
	});
	
}

function muestraValidFile( str_in ){
	
	$("#inp_ValAutorizaDoc").val( str_in );
	
	$(".botones_lightbox").show();
	
	$(".botones_lightbox_message p").empty();
	
	$(".botones_lightbox_message").hide();
	
	$(".dvValidaArchivo > p  ").show();
	
	$(".velo").fadeIn();
	$(".dvValidaArchivo").addClass("show");
	
}

function cerrar_modal_validaDoc(){

	$(".velo").fadeOut();
	$(".dvValidaArchivo").removeClass("show");
	
}


function autoriza_documento(){
	
	$(".botones_lightbox").fadeOut(500,function(){
	
			$(".botones_lightbox_wait").fadeIn(500,function(){
			
					var str_in = $("#inp_ValAutorizaDoc").val();
					
					str_in += "::S"
					
					$("#inp_ValAutorizaDoc").val( str_in );
					
					$("#inp_ValAutorizaDoc").blur();
					
					$(".clsAutorizaDoc").trigger("click");
			});
					
	});
	
}

function rechaza_documento(){
	
	$(".botones_lightbox").fadeOut(500,function(){
		
		$(".botones_lightbox_wait").fadeIn(500,function(){
	
					var str_in = $("#inp_ValAutorizaDoc").val();
					
					str_in += "::N"
					
					$("#inp_ValAutorizaDoc").val( str_in );
					
					$("#inp_ValAutorizaDoc").blur();
					
					$(".clsAutorizaDoc").trigger("click");
					
		});
		
	});
	
}

var flgValidaDoc = true;
var str_message = "" ;

function regresaValidaDoc( xhr, status, args ){
	
	
	
	
		$(".dvValidaArchivo").addClass("show");
		
		$(".dvValidaArchivo > p  ").hide();
		
		$(".botones_lightbox").hide();
		
		
		
		$(".botones_lightbox_message").show();
	
	if(flgValidaDoc){	
		flgValidaDoc =false;
		str_message = args.mensage_txt;
		$(".botones_lightbox_message p").html( args.mensage_txt );
	
	
	}else{
		flgValidaDoc =true;
		$(".botones_lightbox_message p").html( str_message );
	}
	
}




