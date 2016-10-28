function isNumberKey(evt)
{
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	 
	if(charCode == 46)
	{
		return true;
	}
	 
    if(charCode > 31 && (charCode < 48 || charCode > 57))
    {
    	 return false;	 
    }        

     return true;
}

function format(input)
{	
    var num = input.value.replace("," , "");
    
    num = num.replace(",","");
    num = num.replace(",","");
    
	var ent;
	var dec;
	var point;
	
	console.log(num);
	
	if((num.split(".")).length > 2)
	{
		console.log("numero no valido");

		num = "";
		input.value = num;
		
	    return false;
	}
	
	if((num.split(".")).length > 1)
	{
		ent = num.split(".")[0];
	 	dec = num.split(".")[1];
	 	
	 	point = ".";
	 	
	} else {
		
		ent = num.split(".")[0];
		dec   = "";
		point = "";
	}
	
	if(!isNaN(ent))
	{
		console.log(" -- cantidad -> "+ent);
		
    	if(ent.length > 3 && ent.length <= 6)
    	{
    		ent = ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    		
    	} else if(ent.length > 6 && ent.length < 9) {
    		
    		ent = + ent.substring(0,(ent.length - 6)) + "," + ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	}
    		    	
    	input.value = ent + point + dec;
    	
    	console.log(" -- cantidad -> " + ent);
    	
	    return true;
	    
    } 
}

function toggleOnHeader()
{
	$('.ui-panel-titlebar').each(function()
	{
	    var header = $(this);
	    var widgetId = 'widget_' + header.attr('id').replace(/:/g, '_').replace(/_header$/, '');
	    header.css('cursor', 'pointer');
	    
	    var toggler = header.find('a[id$=_toggler]');
	    toggler.remove();
	    
	    header.click(function()
	    {
	        window[widgetId].toggle();
	    });
	});
}

function ini_panel_liquidacion()
{
	console.log("ini_panel_liquidacion(): OK");
	
	toggleOnHeader();
	
	$(window).resize(function() 
	{	
		centerContent();
	});

	$(window).on("load", function() 
	{	
		centerContent();
	});
}

function centerContent() 
{
	var content = $('.centrar_hv');
	
	content.css(
	{
		position:'absolute',
		left: ($(window).width() - content.outerWidth())/2,
		top: ($(window).height() - content.outerHeight())/2
	});	
}


function panel_referencia_pago_imprimir(panel_id)
{			
	var header = '<html><head>'
		+ ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/referencia_pago/acreditado.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO_panel.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO_panel_SPEI.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO.css" />'	
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/referencia_pago/nota.css" />'
		+ ' </head><body>';
	
	var header_fin = '</body></html>';	
	
	var panel = $('#' + panel_id);	
	
	//console.log(" > panel.html() = " + panel.html());	
	
	var pop_up = window.open('','popimpr');
	
	pop_up.document.write(header);
	pop_up.document.write(panel.html());
	pop_up.document.write(header_fin);	
	pop_up.print();
	pop_up.close();	
	
	/*
	var win = window.open('','printwindow');
	win.document.write('<html><head><title>Print it!</title><link rel="stylesheet" type="text/css" href="styles.css"></head><body>');
	win.document.write($("#content").html());
	win.document.write('</body></html>');
	win.print();
	win.close();
	*/
}


function estado_cuenta_imprimir(panel_id){			
	var header = '<html><head>'
		+ ' <link   type = "text/css" rel="stylesheet"   href = "../resources/css/secciones/estado_cuenta/estado_cuenta.css" />'
		+ ' <link  type="text/css"   rel="stylesheet"    href="../resources/css/frmStyle.css" />'
		+ ' <link  type="text/css"   rel="stylesheet"    href="../resources/css/controlTable.css" />'
		+ ' <link  type="text/css"       href="../resources/css/comprobante_pagos.css" />'
		
		+ ' <link   type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/estado_cuenta/creditos.css" />'

		+ ' </head><body>';
	
	var header_fin = '</body></html>';	
	
	var panel = $('#' + panel_id);	
	
	//console.log(" > panel.html() = " + panel.html());	
	
	var pop_up = window.open('','popimpr');
	
		
				
			pop_up.document.write(header);
			pop_up.document.write(panel.html());
			pop_up.document.write(header_fin);	
			pop_up.print();
			pop_up.close();	



}

function imprimir_referencia_pago_liquidacion(panel_id)
{		
	console.log("panel_referencia_pago_imprimir(): " + panel_id);
	
	var header = '<html><head>'
		+ ' <link   type = "text/css" rel = "stylesheet" href = "../../../resources/css/secciones/estado_cuenta/referencia_pago/acreditado.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../../../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO_panel.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../../../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO_panel_SPEI.css" />'
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../../../resources/css/secciones/estado_cuenta/referencia_pago/referencia_DMO.css" />'	
	    + ' <link   type = "text/css" rel = "stylesheet" href = "../../../resources/css/secciones/estado_cuenta/referencia_pago/nota.css" />'
		+ ' </head><body>';
	
	var header_fin = '</body></html>';	
	
	var panel = $('#' + panel_id);	
	
	//console.log(" > panel.html() = " + panel.html());	
	
	var pop_up = window.open('','popimpr');
	
	pop_up.document.write(header);
	pop_up.document.write(panel.html());
	pop_up.document.write(header_fin);	
	pop_up.print();
	pop_up.close();	
}

function toggleOnHeader_DECAPRICATED()
{
	$(document).find(".ui-panel:has(.ui-panel-titlebar-icon) .ui-panel-titlebar")
		       .find("a.ui-panel-titlebar-icon")
		       .click(function(e)
	{		
		if (e.srcElement != null)
		{
			console.log("stopImmediatePropagation()");
			event.stopImmediatePropagation();
		}
		
	});
	
	$(document).on("click", ".ui-panel:has(.ui-panel-titlebar-icon) .ui-panel-titlebar", function(e) 
	{		    
	    if (e.srcElement != null) // avoid infinite loop
	    {     	
	    	console.log("panel_toogled()");
	        $(this).find("a.ui-panel-titlebar-icon").click();
		}
	});
}

function listener_fecha_del_pago(xhr, status, args)
{
	var fecha_pago_ENABLED = args.fecha_pago_ENABLED;
	
	console.log("listener_fecha_del_pago(): " + fecha_pago_ENABLED);	
	
	if(fecha_pago_ENABLED)
	{
		$("p#msg_fecha_de_pago").slideUp();
		
	} else {
		
		$("p#msg_fecha_de_pago").slideDown();
	}
}

var modal_notificacion = "div#modal_notificacion p.MSG_modal_notificacion";

function mostar_modal_notificacion()
{
	$('.loader').show();
	
	$("#modal_notificacion").fadeIn("normal", function ()
	{ 
		$(".telefono").addClass("show");
		
	});
	
	$(modal_notificacion).html("");
	$("#modal_notificacion_OK").css("display", "none");
	
	return true;
}

function cerrar_modal_liquidacion()
{
	$('.lightbox').fadeOut('slow');
}

function listener_modal_notificacion(xhr, status, args)
{
	var SUCCESS_MSG = "Tu solicitud ha sido enviada, en breve la aplicaremos y podrás verificarlo en tu estado de cuenta";
	var ERROR_MSG   = "Error en el envío de la notificación. Intentar más tarde.";
	
	var notificar_pago_OK = args.notificar_pago_OK;
	
	console.log("mostar_modal_notificacion() = " + notificar_pago_OK);
	
	$('.loader').hide();
	
	if(notificar_pago_OK)
	{
		$(modal_notificacion).html(SUCCESS_MSG);
		
		$("#modal_notificacion_OK").css("display", "block");
		
	} else {
		
		$(modal_notificacion).html(ERROR_MSG);
	}
}