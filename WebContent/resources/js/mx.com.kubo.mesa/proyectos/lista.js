console.log("mx.com.kubo.mesa/proyectos/lista.js");

	
function showConfirmInv()
{		
	setTimeout('actionconfirm();',250);
}
	
function actionconfirm()
{
	//alert("");
	$("#dvWaitConfInv").fadeOut(500,function()
	{
		$("#dvContTableConfInv").fadeIn(500,function()
		{
			$.fancybox.center();
		});
	});		
}

function cargaValorActual(element)
{
	console.log("valor Actual: "+ $(element).val() );
	tmpInv= false;
	$("#btnValorActual").val( $(element).val() );
	$("#btnValorActual").trigger("blur");
}
	
function showLigthInv()
{
	
	$("#dvWaitConfInv").show();
	$("#dvContTableConfInv").hide();
	
	$.fancybox(
	{
		'showCloseButton': true,
		'width': 350,
		'height': 450,
		'type':'inline',
		'speedIn': 20,
		'speedOut': 10,
		'modal': true,
		'scrolling': 'auto',
		'centerOnScroll': true,
		'href': '#dvPreMyInvestments',
		'overlayColor': '#333333',
		'hideOnOverlayClick': true,
		'enableEscapeButton': true,
		'onStart': function(){
							
						},
		'onComplete' : function(){
							 $('#fancybox-content').height('auto');
							 $('#fancybox-content').children().eq(0).css('height','auto');
							 $('#fancybox-content').width('359px');
							 $('#fancybox-content').children().eq(0).css('width','359px');
							 $('#fancybox-content').children().eq(0).css('background-color','#FFFFFF');
							 $.fancybox.center();
					    }
	});
	
	return true;
}
	
function aceptaInvertir()
{
	$.fancybox.close();
	
	setTimeout(function()
	{ 
		$("#cmdInvestmentAction").trigger("click");
		
	} , 250);
}
	
function fncChckInv()
{	
	flagChckInv = false;
	
	return true;		
}
	
function seleccionAll()
{		
	var f = false;
	
	if($("#dvSelAllRisk").html() == 'Seleccionar todos')
	{
		f = true;	
	}			
	
	$(".ui-checkbox-filter").each(function()
	{		
			$(this).attr('checked', f);			
	});
	
	if($("#dvSelAllRisk").html() == 'Seleccionar todos')
	{
		$("#dvSelAllRisk").html( 'Quitar Selección' );
	
	} else {
		
		$("#dvSelAllRisk").html('Seleccionar todos');
	}
	
}
