console.log("mx.com.kubo.acreditado/registro/documentacion.js");

/* 
if($.mobile != null)
	 $.mobile.ajaxEnabled = true; 
*/
	 
function initUpload()
{
	
	alert("initUpload!!");
	
/* if($.mobile != null)
	{
		$.mobile.ajaxEnabled = true;
	} 
*/
	
	return true;
}

function comienza_a_contar()
{
	fieldCount();
}
function sigDocuments() {
	if( $("#area").val() == "L") {
		buttonClick = true;
		revision_pasos(); 
	}else {
		$("#hdNext\\:siguienteMasInfo").click();
		//$("#hdNext\\:siguienteCierre").click();
	}
}
function init_listeners_documentacion()
{
	console.log("init_listeners_documentacion(): INIT");
	
	$('.sigDocuments').click(function(){
		if( $("#area").val() == "L") {
			buttonClick = true;
			revision_pasos(); 
		}else {
			$("#hdNext\\:siguienteCierre").click();
		}
		//$.scrollTo('#header', 800, { axis:'y' });
	});			
	
	$('.comboSelAction').change(function(e){
		//alert("JQueryAction Change: "+$(this).attr('id'));
		
		//validateFileUploadCombo($(this).attr('id'));
		// event.preventDefault();
	});
	
	$(".formatpld").keyup(function(e) {			
		$(this).formatCurrency({ 
				colorize: true, 
				positiveFormat: '%n', 
				roundToDecimalPlace: -1, 
				eventOnDecimalsEntered: true });
	
	});
	
	$("h2.expand_heading").toggle(function(){        	
		$(this).children().removeClass("change");	 		    
        }, function () {
        	$(this).children().addClass("change");
    });
	
    $("h2.expand_heading").click(function(event){   
    		$(this).next(".toggle_container").slideToggle("slow");
    		event.preventDefault();
    });
    
    $(".imgLogoProyect").click(function(){
    	//alert("click");
		$("#j_idt16\\:0\\:frm_proyect_image\\:loaderCont33").click();
	});

	$("#j_idt16\\:0\\:frm_proyect_image\\:loaderCont33").change(function(){
		/* 
		if($.mobile != null)
		 $.mobile.ajaxEnabled = false; */
		
		$("#j_idt16\\:0\\:frm_proyect_image\\:loadAction33").click();
		
	});	
	
	
	$("#otherLogo").click(function(){
    	alert("otherLogo");
		$("#loaderOtherLogoCont33").click();
	});
	
	$("#loaderOtherLogoCont33").change(function(){
		/* 
		if($.mobile != null)
		 $.mobile.ajaxEnabled = false; */
		
		$("#loadOtherLogoAction33").click();
		
	});	
    
    $(".validatorClass").bind("change blur", function(event) {
    	fieldCount();
    	event.preventDefault();
    });               
        
    /* funciones mostrar imagenes IFE */
	$('.showIFE').focus(function(event) 
	{
		console.log("estoy entrando al showIFEE------------");
		
		var medida_campo = $(this).width();
		var t = $(this).offset().top - 5;
		var l = $(this).offset().left + medida_campo + 20;
		
		$("div.imagenes_ife").hide();
		$('#ife').show();
		
		if($(this).hasClass("clave_elector")) 
		{
			$('#ife').css({'top' :  t+'px', 'left' : l + "px"});
			$("div.clave_elector").show("normal");
			$('.arrow_left').css({'top' :  8});
		}
		
		if($(this).hasClass("emision")) 
		{
			$('#ife').css({'top' :  t-70+'px', 'left' : l + "px"});
			$("div.emision").show("normal");
			$('.arrow_left').css({'top' :  77});
		}
		
		if($(this).hasClass("seccion")) 
		{
			$('#ife').css({'top' :  t-140+'px', 'left' : l + "px"});
			$("div.seccion").show("normal");
			$('.arrow_left').css({'top' :  150});
		}
		
		if($(this).hasClass("numero_vertical")) 
		{
			$('#ife').css({'top' :  t-210+'px', 'left' : l + "px"});
			$("div.numero_vertical").show("normal");
			$('.arrow_left').css({'top' :  217});
		}
		
		if($(this).hasClass("CIC")) 
		{
			$('#ife').css({'top' :  t-5+'px', 'left' : l + "px"});
			$("div.CIC_number").show("normal");
		}
		
		event.preventDefault();
	});
	
	$('.showIFE').blur(function(event) 
	{
		$('#ife').hide(); 
		
	});
	
	console.log("init_listeners(): OK");
	
	 $("#ine_ife label:eq(0)  strong").replaceWith(function() { return $(this).contents(); });
	 $("#ine_ife label:eq(0):contains('\IFE')").html(function(_, html) {
	  return html.replace(/(IFE)/g, '<strong>$1</strong>');
	 });
	 
	 $("#ine_ife label:eq(1)  strong").replaceWith(function() { return $(this).contents(); });
	 $("#ine_ife label:eq(1):contains('\INE')").html(function(_, html) {
	  return html.replace(/(INE)/g, '<strong>$1</strong>');
	 });	 	 
	 
	 console.log("init_listeners_documentacion(): OK");
}




function validaMontoMax(xhr, status, args)
{		
	if(args.flagMDep)
	{
		
		alert ( "El monto de sus depósitos excede el límite permitido de "+args.MONTOMAX );
		
		$("#frm_questPLD\\:momentmaxdepInv").val("");
		$("#frm_questPLD\\:momentmaxdepInv").blur();
		
	} else if (args.flagMBal) {
		
			alert ( "El monto de sus saldos excede el límite permitido de " + args.MONTOMAX );
		
		$("#frm_questPLD\\:montomaxsalInv").val("");
		$("#frm_questPLD\\:montomaxsalInv").blur();
		
	} else if (args.flagMINDep ) {
		
		alert ( "El depósito mínimo requerido es de " + args.MONTOMIN );
	
		$("#frm_questPLD\\:momentmaxdepInv").val("");
		$("#frm_questPLD\\:momentmaxdepInv").blur();
		
	} else if (args.flagMINBal ) {
		
		alert ( "El saldo mínimo requerido es de " + args.MONTOMIN );
	
		$("#frm_questPLD\\:montomaxsalInv").val("");
		$("#frm_questPLD\\:montomaxsalInv").blur();			
	}		
	
	

}