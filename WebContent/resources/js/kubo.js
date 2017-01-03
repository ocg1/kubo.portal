var map = null;
var flagDetail = false;
var flagGraph = true;
var flagDgntc = true;
var flagCargaInfoComplete = true;
var flagActivity = true;

$(document).ready(function()
{	
	flagGraph = true;
	
	initSummaryRequest();
	initHTML();
});

function showRecommended()
{	
	if($("#dvContRecommended").css('display') !== 'none')
	{
		$("#dvContRecommended").hide();
		
	} else {
		
		$("#dvContRecommended").show();
	}	
}

function validaClabe(flagDis){
	
	
	if($("#hasaccount\\:0").is(':checked')) {  
       
	
		var valor = $("#clabe").val();
		if(valor.length==0){
			return false;
		}
		if(valor.length<18){
			alert("La cuenta clabe debe tener 18 dígitos ");
			$("#clabe").focusin();
			return false;
		}
		
//	    if (tecla==8) return true; // 3
//	    patron =/\d/; // Acepta números y letras
//	    te = String.fromCharCode(tecla); // 5
//	    if( !patron.test(valor) ) // 6
//		{
//	    	alert("Algún valor es incorrecto");
//	    	$("#clabe").focusin();
//			return false;
//		}
		
	}
	if(flagDis)
		displayMessageProcessing('msgprocessing',true);
	
	return true;
	
}

function returnRecomended(xhr, status, args)
{			
	$("#dvContRecommended").hide();
	$("#inp_recommended_search_input").val("");			
}

function aceptaInvertirInter()
{
	$("#dvContTableConfInvInterno").hide("220",function()
	{
		$("#dvWaitConfInvInterno").show();
	});
	
	
	$("#btnInvertirInterno").trigger("click");
	
}

function aceptaInverSuccess()
{
	
	$("#dvSuccessInvInterno").hide("220",function(){
		$("#dvWaitConfInvInterno").show();
	});
	
	location.reload(); 
	
}

function aceptaInverError()
{
	
	$("#dvErrorInvInterno").hide("220",function(){
		$("#dvWaitConfInvInterno").show();
	});
	
	location.reload(); 
	
}


function initHTML()
{
	$("#dvInvProc_01").click(function(event ) 
	{
		event.preventDefault();
		/*****************************************/
//		var t = $("#dvInvProc_01").offset().top-13;
//		var l = $("#dvInvProc_01").offset().left+60;
//		$(".proyectList-box").offset({ top: t, left: l});
		/*****************************************/
		
		/***************************************** /
		var t = ($("#dvInvProc_01").position().top)+340;
		var l = ($("#dvInvProc_01").position().left+208+300);
		//alert( "top: "+t+", left: "+l );
		//$(".proyectList-box").offset({ top: t, left: l});
		var strT = t+"px";
		var strL = l+"px";
		//alert(strT+" , "+strL);
		$(".proyectList-box").css({
			
			"top": strT,"left": strL
			
		});
		
		/ *****************************************/
		
//		var htmlStr = "<table><tr><td>top:</td><td>"+t+"</td></tr><tr><td>left:</td><td>"+l+"</td></tr></table>";
//		
//		$("#dvProper").html(htmlStr);
		
		$(".proyectList-box").show("fade");
		$("#btnListProy").click();
		
	});
}


function showListProyect(xhr, status, args){
	$(".proyectList-box").css({
		"width": "auto","height": "auto"
	});
	//alert(args.tablePy);
	$("#contenPyLst").html($("#dvContListPryMsg").html());
	$("#loader").hide("fade",function(){
		$("#contenPyLst").show("fade");
	});
	
}

function hideMessage(){
	
	$(".proyectList-box").hide("fade");
	
}

function initSummaryRequest()
{	
	$("#windowsWidth").val(Number($(window).width())-30);
	$("#windowsHeight").val(Number($(window).height())-30);	
	
	$("#detailsTransUnion").click(function()
	{
		$("#dvDetalleTransUnion").show();
		
	});
	
	$("h2.expand_togle").toggle(function()
	{        	
		$(this).children().removeClass("change");
		
		$(this).parent().find(".getTotal").show();
		
	}, function () {
        	$(this).children().addClass("change");
        	$(this).parent().find(".getTotal").hide();
    });
	
    $("h2.expand_togle").click(function(event)
    {   
    	$(this).next(".toggle_container").slideToggle("slow");
    		
    	event.preventDefault();
    });
    
    loadScriptPhotos();
    
	$("#preguntas").fancybox(
	{
		'width' : '50%',
		'height' : '50%',
		'autoScale' : 'false',
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'type' : 'inline',
		'scrolling' : 'auto',
		'centerOnScroll' : 'true',
		'href' : '#displayQuestion',
		'overlayColor': '#000000',
		'overlayOpacity': '0.15',
		'padding': '0',
		'margin': '0',
		'onClosed': function()
		{
			$("#questActUpdA").click();
			$("#questActUpd").click();
		}
	});

	$(".summaryTitle").click(function(e)
	{
		e.preventDefault();
		
		if($(this).next().is(":visible")) 
		{
			$(this).next().hide('slow');
			
		} else {
			
			$(this).next().show('slow');
		}
	});
	
	$('#summaryLinkTop').click(function(e) 
	{
		e.preventDefault();
		
		$(this).text($(this).text() == 'Ver por cantidad' ? 'Ver por monto' : 'Ver por cantidad');
		
		if( $('.numTopInv').is(":visible") ) 
		{
		   $('.numTopInv').hide();
		   $('.monTopInv').show();          
		   
		} else {
			
		   $('.numTopInv').show();
		   $('.monTopInv').hide();        
		}
	});

	$('#summaryLinkBottom').click(function(e) 
	{
		e.preventDefault();
		
		$(this).text($(this).text() == 'Ver por monto' ? 'Ver por cantidad' : 'Ver por monto');

		if( $('.numBottom').is(":visible") ) 
		{
		   $('.numBottom').hide();
		   $('.monBottom').show();            
		   
		} else {
			
		   $('.numBottom').show();
		   $('.monBottom').hide();        
		}

	});
	
	flagDetail = false;
	
	$('#detailsTabLink').click(function(e) 
	{		
		console.log("detailsTabLink.click");
		
		e.preventDefault();
		
		$('#dvCntBuroReport').hide();
		$("#dvCntDiagnostico").hide();
		$("#dvCntActivityPerson").hide();
		$("#dvCntNormativityPerson").hide();
		$("#dvCntActivityPerson").css("display","none");
		
		if(!$('#dvSumaryCnt').is(":visible"))
		{
			$('#dvSumaryCnt').show();
			
			if(flagCargaInfoComplete)
			{
				flagCargaInfoComplete = false;
				
				console.log("$('#cargaInfoAction').click()");
				
				 $('#cargaInfoAction').click();
				 
			} else {
				
				if(flagDetail)
				{
					$('.details').show();
					$('.profile').show();
					$('.profileControlTable').show();
					$(this).text('Ocultar perfil completo');
					
				}else{
					
					$(this).text('Ver perfil completo');
				}
			}
			
		} else {
			
				$(this).text($(this).text() == 'Ver perfil completo' ? 'Ocultar perfil completo' : 'Ver perfil completo');
				 
				if( $('.profile').is(":visible") || $('.profileControlTable').is(":visible") ) 
				{		   
				   // $('.details').hide();
				   flagDetail = false;
				   $('.profile').hide(); 
				   $('.profileControlTable').hide();
				   
				} else {
					$('.details').show();
					flagDetail = true; 
					$('.profile').show(); 
					$('.profileControlTable').show();
					
					if(flagCargaInfoComplete)
					{
						
						flagCargaInfoComplete = false;
						displayMessageProcessing('msgprocessing',false);
						map=null;
						 $('#cargaInfoAction').click();
						 
					}else{
						
						if(map==null)
						{
						   initializeMap();
					   }
						
					}
				}
		}
		
		e.preventDefault();
	});
	
	//<a id = "detailsBuro" href = "#">#{sessionBean.area=='M' ? 'Buró de crédito' : 'Ayuda'}</a>
	
	$(".clsActivityButt").click( function(e)
	{		
		$('.profile').hide(); 
		$('.profileControlTable').hide();
		$("#dvCntDiagnostico").hide();
		 
		 $('#dvSumaryCnt').hide(); 		 
		 $('#dvCntBuroReport').hide(); 
		 
		 $("#dvCntActivityPerson").show();
		 
		 $("#dvCntNormativityPerson").hide();
		
		 if(flagActivity)
		 {			 
			 var datos_init_activity = $('#prospectus_id').val() + "::" + $('#company_id').val();
			 
			 console.log("datos_init_activity = " + datos_init_activity);
				 
			 $('#valActivity').val(datos_init_activity).trigger("blur");
			 
			 $("div#dvContPersonalData").show();
			 
			 flagActivity = false;
		 }
		 
	});
	
	$("#detailsBuro").click(function(e){
		 /*
		 $('#profileFirst').hide();
		 */
		
		 $('.profile').hide(); 
		 $('.profileControlTable').hide();
		 $("#dvCntDiagnostico").hide();
		 
		 $('#dvSumaryCnt').hide(); 		 
		 $('#dvCntBuroReport').show(); 
		 $("#dvCntNormativityPerson").hide();
		 $("#dvCntActivityPerson").hide();
		 
		 $('#detailsTabLink').text('Ver perfil del cliente');
		 $('#dvContKuboData').show();
		 $('#dvContPersonalData').show();
		 $('#dvContAddressBuro').show();
		 $('#dvContCredVig').show();
		 $('#dvContCredCerrUlt6M').show();
		 $('#dvContCredCerrMas6M').show();
		 $('#dvContConsulBuroUlt6M').show();
		 $('#dvContConsulBuroMas6M').show();
		 $('#buroResp').show();
		 
		 if(flagGraph)
		 {
			 
			 $('#valGraphic').val($('#burSolNumSabana').val());
			 
			 //alert($('#valGraphic').val());
			 
			 $('#valGraphic').blur();
			 flagGraph = false;
		 }
		 
		 e.preventDefault();
	});
	

	$('#detailsPortfolioLink').click(function(e) {
		e.preventDefault();
					
		if ($(this).text() == 'Ver detalle de retornos') {
				$(this).text('Volver');
		} else { 
				$(this).text('Ver detalle de retornos');
		}
		
		if( $('#graphPie').is(":visible") ) {
		   $('#graphPie').hide();
		   $('#graphPie2').show();            
		} else {
		   $('#graphPie').show();
		   $('#graphPie2').hide();        
		}			
	});	
	
	$('#projectListAction').click(function(e) {
		e.preventDefault();
		$('.container').hide(1,function(){
			$('#projectList').show();
		} );			
				
	});	
	$('#projectListAction2').click(function(e) {
		e.preventDefault();
		$('.container').hide(1,function(){
			$('#projectList').show();
		} );			
				
	});	
	$('#resumenInvest').click(function(e) {
		e.preventDefault();
		$('#projectList').hide(1,function(){
			$('.container').show();
		} );			
				
	});	
	
	//Seccion de Diagnostico Financiero
	
	$(".clsDiagNosticoButt").click(function(e){
		e.preventDefault();
		$('.profile').hide(); 
		 $('.profileControlTable').hide();
		 $('#dvSumaryCnt').hide(); 		 
		 $("#dvCntDiagnostico").show();
		 $('#dvCntBuroReport').hide();
		 $("#dvCntActivityPerson").hide();
		 $("#dvCntNormativityPerson").hide();
		 
		 if(flagDgntc){
			 $("#btnInitDagns").click();
			 flagDgntc = false;
			 
		 }
		 
		
	});
	
	$(".clsNormativityButt").click( function(e)
			{		
				
				$('.profile').hide(); 
				$('.profileControlTable').hide();
				$("#dvCntDiagnostico").hide();
				 
				 $('#dvSumaryCnt').hide(); 		 
				 $('#dvCntBuroReport').hide(); 
				 
				 $("#dvCntActivityPerson").hide();
				 
				 $("#dvCntNormativityPerson").show();
				 
				 $("#btnInitNormativity").trigger("click");
				 
			});
	
	console.log("initSummaryRequest(): OK");
}



function resInfoCompleta(xhr, status, args)
{
	console.log("\nkubo.resInfoCompleta()");
	
	$('.details').show();
	$('.profile').show();
	$('.profileControlTable').show();
	//$(this).text('Ocultar perfil completo');
	loadScriptPhotos();
	
	$('#detailsTabLink').off();
	
	initSummaryRequest();
	initHTML();
	inicializaModal();
	
	var codigo_postal = args.codigo_postal;	
	
	console.log("\t args.codigo_postal = " + codigo_postal);
	
	init_vivienda_codigo_postal(codigo_postal);
	init_edicion_vivienda();
	
//	if( map==null )
	
//	{
	   initializeMap();
	//}
	
	closeFancy();		
}

function loadScriptPhotos(){
    $("h2.expand_basic").toggle(function(){        	
		$(this).children().removeClass("change");
		$(this).next().hide();
        }, function () {
        	$(this).children().addClass("change");        	
        	$(this).next().show();
    });
}
function showDescriptionIncome() {
	$.fancybox({			
		'padding' : '0',
		'margin' : '0',
		'autoDimensions':true,
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'speedIn' : '20',
		'speedOut' : '10',
		'type' : 'inline',
		'scrolling' : 'no',
		'centerOnScroll' : true,
		'href': '#showDescriptionOtherIncome',
		'overlayColor': '#333333'
	});
}
/*
function validationChangeCurp(component){
	var element=$("#"+component);
	$(element).parent().children('.formError').remove();
	if(element.val()!="" && element.val().length==18 && element.next().val()!=""){
		return true;
	}else if(element.val()=="" || element.val().length<18){
		$(element).validationEngine('showPrompt', 'Formato no válido','error','centerRight', true);
		return false;
	}
	else if(element.next().val()==""){
		$(element.next()).validationEngine('showPrompt', 'Obligatorio','error','centerRight', true);
		return false;
	}
	else{
		return false;
	}
	
}*/

function validationChangeDataIFE(component,typefield,prevValue){
	
	var clientElement = component.replace(/:/g, '\\:');
	var element=$("#"+clientElement);
	element.parents('.editClass').find('.formError').remove();	
	var elementVal=element.val();
	var elementReason=element.next();
	
	if(prevValue==elementVal){
		alert('No se identificaron cambios.');
		return false;
	}
	switch (typefield) {
	case "mx_ife_cveelector":
		if(elementVal.length==18 && elementVal!="" && elementReason.val()!=""){
				return true;	
		}else if(elementVal=="No proporcionado" || elementVal==""){
			alert('Capture la clave de elector.');
			return false;
		}else{
			if(elementReason.val()==""){
				alert( 'Falta el motivo por llenar.');
				return false;	
			}
		}		
		break;
	case "mx_ife_numemision":
		if(elementVal.length==2 && elementVal!="" && elementReason.val()!=""){
			return true;
		}else if(elementVal=="No proporcionado" || elementVal==""){
			alert('Capture número de emisión.');
			return false;
		}else{
			if(elementReason.val()==""){
				alert( 'Falta el motivo por llenar.');
				return false;	
			}
		}		
		break;
	case "mx_ife_seccion":
		if(elementVal.length==4 && elementVal!="" && elementReason.val()!=""){
				return true;
		}else if(elementVal=="No proporcionado" || elementVal==""){
			alert( 'Capture la sección.');			
			return false;
		}else{
			if(elementReason.val()==""){
				alert( 'Falta el motivo por llenar.');
				return false;	
			}
		}
		break;
	case "mx_ife_numvertical":
		if(elementVal.length==13 && elementVal!="" && elementReason.val()!=""){
				return true;
		}else if(elementVal=="No proporcionado" || elementVal==""){
			alert(  'Capture número vertical(OCR).');
			return false;	
		}else{
			if(elementReason.val()==""){
				alert( 'Falta el motivo por llenar.');
				return false;	
			}
		}
		break;

	default:
		break;
	}
}

function validationChangePhone(phone,lada,prevPhone,prevLada){
	var clientPhone = phone.replace(/:/g, '\\:');
	var clientLada = lada.replace(/:/g, '\\:');
	
	var element=$("#"+clientPhone);
	element.parents('.editClass').find('.formError').remove();
	var elementReason=element.parent().next();
	var ladaValue=$("#"+clientLada).val();
	var phoneValue=element.val();
	
	if(ladaValue !="" && phoneValue !="" && ladaValue.length>1 && phoneValue.length>6 && elementReason.val() !="" ){
		if(prevPhone==phoneValue && prevLada==ladaValue){
			element.validationEngine('showPrompt', 'No se identificaron cambios en el telefono','error','centerRight', true);
			return false;
		}else{
			return true;
			}
	}else{
		if(elementReason.val()==""){
			$(elementReason).validationEngine('showPrompt', 'Obligatorio','error','centerRight', true);
			return false;	
		}else{
			element.validationEngine('showPrompt', 'Número telefonico incorrecto','error','centerRight', true);
			return false;
		}
	}
	
}

function validationAddNewPhone(phone,lada,typePhone, phone_extension){
	var clientPhone = phone.replace(/:/g, '\\:');
	var clientLada = lada.replace(/:/g, '\\:');
	var phoneType=typePhone.replace(/:/g, '\\:');
	
	var element=$("#"+clientPhone);
	element.parents('.editClass').find('.formError').remove();
	//var elementReason=element.parent().next();
	var elementReason = $("#txt-reason-add-phone");
	var ladaValue=$("#"+clientLada).val();
	var phoneValue=element.val();
	var elemenType=$("#"+phoneType);
	
	if(  phoneValue !=""   && phoneValue.length>6 && elementReason.val() !="" && elemenType.val()!=""){		
			return true;			
	}else if(elemenType.val()==""){
		alert( 'Seleccione una opción' );
		return false;
	}
	else{
		if(elementReason.val()==""){
			$(elementReason).validationEngine( 'Obligatorio','error' );
			return false;	
		}else{
			alert( 'Número telefonico incorrecto' );
			return false;
		}
	}
	
}

function validateNewNote(type,pririty,note)
{
	//alert("validateNewNote()");
	
	var elementType=$("#"+type);
	var elementPriority=$("#"+pririty);
	var elementNote=$("#"+note);
	elementType.parents('.editClass').find('.formError').remove();
	if(elementType.val()!="" && elementPriority.val()!="" && elementNote.val()!=""){		
		return true;
	}else if(elementType.val()==""){
		$(elementType).parent().validationEngine('showPrompt', 'Seleccione el tipo de nota.','error','centerRight', true);
		return false;
	}else if( elementPriority.val()==""){
		$(elementPriority).parent().validationEngine('showPrompt', 'Seleccione prioridad de la nota.','error','centerRight', true);
		return false;
	}
	else{
		$(elementNote).validationEngine('showPrompt', 'Capture la nota.','error','centerRight', true);
		return false;
	}
}


function validationChangeCurp(component)
{
	var element = $("#"+component);
	
	$(element).parent().children('.formError').remove();
	
	if(element.val() != "" && element.val().length == 18 && element.next().val() != "")
	{
		return true;
		
	} else if(element.val() == "" || element.val().length < 18) {
		
		alert( 'Formato no válido');
		
		return false;
		
	} else if(element.next().val() == "") {
		
		alert( 'El motivo es obligatorio');
		
		return false;
		
	} else {
		
		return false;
	}
}

function validationClabe(component){
	var clabe=$("#"+component);
	if(clabe.val()!="" && clabe.val().length==18){
		return true;
	}else{
		alert("Formato incorrecto");
		return false;
	}
}

function validationChangeClabe(component){
	var clabe=$("#"+component);
	var descBank=$("#acSimple").children().eq(0);
	var txtRazon=$("#txt-reason-clabe");
	$(clabe).parent().children('.formError').remove();
	if(clabe.val()!="" && clabe.val().length==18 && txtRazon.val()!="" && descBank.val()!=""){
		return true;
	}else if(clabe.val()=="" || clabe.val().length<18){
		$(clabe).validationEngine('showPrompt', 'Formato no válido','error','centerRight', true);
		return false;
	}else if(descBank.val()==""){
		$($("#acSimple")).validationEngine('showPrompt', 'Capture la actividad económica','error','centerRight', true);
		return false;
	}
	else if(txtRazon.val()==""){
		$(txtRazon).validationEngine('showPrompt', 'Obligatorio','error','centerRight', true);
		return false;
	}
	else{
		return false;
	}
}
function bankDescComplete(xhr, status, args) {
	var element=$("#acSimple_input");
	var isValid = args.isValid;	
	if(isValid){
		
	}else{
		element.val("");
	}
}
function showReviewRef(thisId){
	var idDiv=$("#"+thisId);
	var pariente=idDiv.parents(".custumTdEditPhone");
	if(pariente.hasClass('review')){	
		
		pariente.siblings('.review').hide();
		
		
		
		//pariente.slideToggle('slow');
		
		//if(idDiv.is(":hidden")){
		$("#"+thisId).fadeIn( "slow" );
		//}
		
		return false;
	}else{
		
		$("#"+thisId).html($("#dvContPnlPreferencesPhone").html());
		pariente.after('<tr class="custumTdEditPhone review"><td colspan="3"></td></tr>');
		idDiv.clone().appendTo(pariente.next().children().eq(0)).slideDown('slow');
		idDiv.parent().remove();
		pariente.next().siblings('.review').hide();
		return true;
		
	}
}

function showReview(thisId)
{
	var idDiv=$("#"+thisId);
	var pariente=idDiv.parents(".custumTdEditPhone");
	
	if(pariente.hasClass('review'))
	{			
		pariente.siblings('.review').hide();
						
		//pariente.slideToggle('slow');
		
		//if(idDiv.is(":hidden")){
		pariente.fadeIn( "slow" );
		//}
		
		return false;
		
	} else {
		
		$("#" + thisId).html($("#dvConPnlOtherPersonPhone").html());
		
		pariente.after('<tr class="custumTdEditPhone review"><td colspan="3"></td></tr>');
		
		idDiv.clone().appendTo(pariente.next().children().eq(0)).slideDown('slow');
		idDiv.parent().remove();
		
		pariente.next().siblings('.review').hide();
		
		return true;		
	}
}

function showReviewAll(xhr, status, args) {
	var arrayPerPhone =eval('('+ args.personPhone +')');
	var appendhere=$("#"+args.appendhere);
	var pariente=appendhere.parents(".custumTdEditPhone");
	var stringAppend="";
	for (target in arrayPerPhone){		
		 if(target!='remove'){
			stringAppend+="<tr><td style='border-top:solid 1px #333; color: #333;'>";
			var phonePerson=arrayPerPhone[target];
			stringAppend+="<span style='font-weight: bold;'>"+phonePerson.person.first_name+" "+phonePerson.person.middle_name+" "+phonePerson.person.father_last_name+" "+phonePerson.person.mother_last_name+"</span>";			
			var repeatArrayOne=phonePerson.phoneRepeat;
			stringAppend+="<div style='margin-left:30px;'>";
			for(targetOne in repeatArrayOne){
				 if(targetOne!='remove')
					 stringAppend+="<span>"+repeatArrayOne[targetOne].phoneType.name+"-"+repeatArrayOne[targetOne].phone_number+"</span><br/>";					 
			}		
			
			if(phonePerson.proyectloan!=null){
				var statusid=phonePerson.proyectloan.status_id;
				var setcolor=statusid==4?"orange":"" || statusid==3?"#439539":"";
				stringAppend+="<span style='font-weight: bold;'>Proyecto:"+phonePerson.proyectloan.proyect.name+"</span><br/>";
				stringAppend+="<span style='font-weight: bold;color:"+setcolor+"'>Estatus:"+phonePerson.proyectloan.statusProyect.name+"</span><br/>";
			}else{
				stringAppend+="<span style='font-weight: bold;'>Proyecto:No publicado</span><br/>";
				stringAppend+="<span style='font-weight: bold;'>Estatus:No publicado</span><br/>";
			}			
			stringAppend+="<span style='font-weight: bold;'>Número de folio:"+phonePerson.person.prospectus.tracking_id+"</span>";
			stringAppend+="</div>";
			stringAppend+="<td></tr>";
			var arrayPhoneOtherPer=phonePerson.otherPersonPhone;
			for(targetOther in arrayPhoneOtherPer){
				if(targetOther!='remove'){
					stringAppend+="<tr><td style='padding-left: 80px; border-top: 1px dotted green;color: #333;'>";
					var phoneOtherPer=arrayPhoneOtherPer[targetOther];
					stringAppend+="<span style='font-weight: bold;'>"+phoneOtherPer.person.first_name+" "+phoneOtherPer.person.middle_name+" "+phoneOtherPer.person.father_last_name+" "+phoneOtherPer.person.mother_last_name+"</span>";
					var repeatArrayTwo=phoneOtherPer.phoneRepeat;
					stringAppend+="<div style='margin-left:30px;'>";
					for(targetTwo in repeatArrayTwo){
						 if(targetTwo!='remove')
							 stringAppend+="<span>"+repeatArrayTwo[targetTwo].phoneType.name+"-"+repeatArrayTwo[targetTwo].phone_number+"</span><br/>";
					}					
					if(phoneOtherPer.proyectloan!=null){
						var statusidother=phoneOtherPer.proyectloan.status_id;
						var setcolorother=statusidother==3?"#439539":"" || statusidother==4?"orange":"";
						stringAppend+="<span style='font-weight: bold;'>Proyecto:"+phoneOtherPer.proyectloan.proyect.name+"</span><br/>";
						stringAppend+="<span style='font-weight: bold;color:"+setcolorother+"'>Estatus:"+phoneOtherPer.proyectloan.statusProyect.name+"</span><br/>";						
					}else{
						stringAppend+="<span style='font-weight: bold;'>Proyecto:No publicado</span><br/>";
						stringAppend+="<span style='font-weight: bold;'>Estatus:No publicado</span><br/>";
					}			
					stringAppend+="<span style='font-weight: bold;'>Número de folio:"+phoneOtherPer.person.prospectus.tracking_id+"</span>";
					stringAppend+="</div>";
					stringAppend+="<td></tr>";
				}
			}
		 }
		 
	}
	//appendhere.after(stringAppend);
	//appendhere.remove();
	appendhere.html(stringAppend);
	appendhere.clone().appendTo(pariente.next().children().eq(0)).slideDown('slow');
	pariente.siblings('.review').hide();
	
}

function addnumladacel(component)
{
	var TRABAJO_FIJO = 1;
	
	var tipo_telefono = parseInt($('#'+component).val());
	
	console.log("addnumladacel(): "+ tipo_telefono);
	
	if(tipo_telefono % 2 ==0)
	{
		$('#lada-phone-cell').show();
		
		
	} else {
		
		$('#lada-phone-cell').hide();
		
	}
	
	if(tipo_telefono == TRABAJO_FIJO)
	{
		$("div#value_telExt").slideDown();
		
	} else {
		
		$("div#value_telExt").slideUp();
	}
}

function showComment(component){
	if(Number($(component).val().replace(",",""))>0)
		$("#text-comment-for-consolidate").show();
	else
		$("#text-comment-for-consolidate").hide();
}
function validateChangeConsolidate(preValue){
	var elementAmmount=$("#txt-ammount-minus-consolidate");
	var elementReason=$("#txt-reason-consolidate");
	var numberAmmount=Number($(elementAmmount).val().replace(",",""));
	$(elementAmmount).parents(".edit-ammoun-minus").children('.formError').remove();
	if(elementAmmount.val()!="" && elementReason.val()!="" && numberAmmount>0){
		if(Number(preValue)!=numberAmmount){
			elementAmmount.slideUp();
			return true;
		}else{
			$(elementAmmount).validationEngine('showPrompt', 'No se detectaron cambios','error','centerRight', true);
			elementAmmount.parent().find(".formErrorContent").css("width","190px");
			return false;
		}
	}else if(elementAmmount.val()==""){
		$(elementAmmount).validationEngine('showPrompt', 'Capture el monto','error','centerRight', true);
		elementAmmount.parent().find(".formErrorContent").css("width","190px");
		return false;
	}
	else{
		$(elementReason).validationEngine('showPrompt', 'Obligatorio','error','centerRight', true);
		elementReason.parent().find(".formErrorContent").css("width","190px");
		return false;
	}
}		

function validateChangeEconActivity(component,coment,preVal){
	var autocomplet=$("#"+component.replace(/:/g, '\\:'));
	var reason=$("#"+coment.replace(/:/g, '\\:'));
	$(autocomplet).parent().children('.formError').remove();
	// alert("pre: "+preVal +"autocomplet: "+autocomplet.val()+" reason: "+reason.val() );
	if(autocomplet.val()!="" && reason.val()!=""){
		if(preVal!=autocomplet.val()){
			//alert("regersando true");
			return true;
		}else{
			//$(autocomplet).validationEngine('showPrompt', 'No se detectaron cambios.','error','centerRight', true);
			alert("No se detectaron cambios");
			return false;
		}
	}else{
		if(autocomplet.val()==""){
			//$(autocomplet).validationEngine('showPrompt', 'Seleccione una actividad.','error','centerRight', true);
			alert("Seleccione una actividad");
			return false;
		}else{
			//$(reason).validationEngine('showPrompt', 'Obligatorio.','error','centerRight', true);
			alert("Llene todos los campos ");
			return false;	
		}		
	}
}
function changeValue(component,preValue){
	var element=$("#"+component.replace(/:/g, '\\:'));
	if(element.val()==preValue){
		return false;
	}else{
		funcWaitBusDetails();		
		return true;
	}
}

function funcWaitBusDetails(){
	$(".showTotalsProcess").show();
	$(".hideLabelTotal").hide();
}
function funcWaitResBusDetails(){
	$(".showTotalsProcess").hide();
	$(".hideLabelTotal").show();
}

function hideTransUnion(){
	
	$("#dvDetalleTransUnion").hide();
	
}

function completeActiveInvestorAction( xhr, status, args ){
	
	var strRes = "";
	/*
	var idcontent = $("#contentAltaInversionista");
	*/
	
	if( args.isError ){
		
		strRes = "<ul style='list-style:square inside;'>";
		
		var valores = args.respuestaStr;
		
		var arr = valores.split("::");
		
		for ( var i = 0 ; i<arr.length ; i++ ){
			strRes += "<li>"+arr[i]+"</li>";
		}
		
		
		strRes += "</ul>";
		
		
	} else {
		
		strRes = args.respuestaStr;
		
	}
	
	$("#contentAltaInversionista").html( strRes );
	
	closeFancy();
	
	 $("#contentAltaInversionista").delay(600,function(){
		 
		 $.fancybox({			
				'padding' : '0',
				'margin' : '0',
				'transitionIn' : 'none',
				'transitionOut' : 'none',
				'modal' : true,
				'type' : 'inline',
				'scrolling' : 'no',
				'centerOnScroll' : true,
				'href': '#showDescriptionNewInvestment',
				'overlayColor': '#333333',
			});
		 
	 });
	

	
}

function returnActivityAction(){
	
	$('.tblAttmp').fixheadertable({
		    caption 	: 'Intentos de Consulta',
		    height  	: 200,
		    width		: 810 ,
		    zebra   	: false,
		    resizeCol   : true,
		    colratio	: [350,230,330,180,330,330,180,210,180,180,180,180,250,210,100,100,150,150,230,100,100,100,100]
	});
	
	closeFancy();
	
}