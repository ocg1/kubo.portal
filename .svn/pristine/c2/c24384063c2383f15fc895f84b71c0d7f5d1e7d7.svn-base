console.log("solicitud.js");

/*
define(function()
{
	var modulo = 
	{		
		init: function()
		{
			console.log("solicitud.init(): OK");						
		}
	};
	
	return modulo;
});
*/

Solicitud.init_acreditado_sin_publicar = function()
{
 /*	$('.detailsControlTable').hide();
	$('.clsDiagNosticoButt').hide();
	
	$('.profileControlTable').show();
	/ *$('.detailsTabAllControlTable').hide();* /
	$('.detailsTabHelpControlTable').hide();
	
	$('.detailsTabAllControlTable').text( 'Ver Estado de Cuenta');
	
	$('.detailsTabAllControlTable').css({'left':'694px' });
	
	$('.detailsTabAllControlTable').addClass("clEdoCtaInv");
	
	$('.clEdoCtaInv').click(function(e)
	{					
		e.preventDefault();
		
		inicializaSolInv();					
	});
	
	map = null;
	
	initializeMap();
	
	console.log("Solicitud.init_acreditado_sin_publicar(): OK"); */
};

Solicitud.init_actividad = function()
{
	//displayMessageProcessing('msgprocessing', false);
	
	$(".clsActivityButt").css("left","550px");
	
	setTimeout( function()
	{								
		$('#valActivity').val($('#prospectus_id').val()+"::"+$('#company_id').val());
		//alert($('#valGraphic').val());
		 $('#valActivity').blur();
		 
		closeMessageProcessing();	
		
	}, 300);	
	
	console.log("Solicitud.init_actividad(): OK");
};

Solicitud.init_investor = function()
{				
	//$(".clsActivityButt").trigger("click");
	
	console.log("Solicitud.init_investor(): OK");
	
	//inicializaSolInv();					
	
	//$('div.detailsTabHelpControlTable').hide();
	//$('.clsDiagNosticoButt').hide();
	
	closeMessageProcessing();
	
	 //$("h2.expand_heading").click();	
};

function inicializaSolInv()
{
	console.log("inicializaSolInv(): OK");
	
	displayMessageProcessing('msgprocessing',true);
	
	$(".clEdoCtaInv").off();
	
	$('.detailsTabAllControlTable').removeClass("clEdoCtaInv");
	
	$('.profileControlTable').hide();
	
	$('#carterakubo').hide();
	
	$("#dvCntMyInvestment").show();
					
	
	$('.detailsTabAllControlTable').text( 'Ver Solicitud de Inversión');
	
	$("#dvCntProfile").addClass("clCntProfileWidth");
	
	$("#dvCntSearchProfile").addClass("clCntSearchProfile");
	
	$("#dvCntTitleProfile").addClass("clCntSearchProfile");
	
	$('.detailsTabAllControlTable').addClass("clSolInv");
	
	$('#valEdoCtaInv').val( $("#prospectus_id").val()+"::"+$("#company_id").val() );
	
	$('#btnInitEdoCtaInv').blur();	
	
	$('#lnkInitEdoCtaInv').click();
}

Solicitud.returnIFrameFuntion = function()
{
	
	console.log("Solicitud.returnIFrameFuntion(): OK");
	
	var testframe = document.createElement("iframe");
    testframe.id = "testframe";
    testframe.src = "../jsf/edoCuentaInv.xhtml";
    
    $("#dvCntMyInvestment").empty();
    $("#dvCntMyInvestment").append(testframe);
    
    var styles = 
    {
		width : "98%",
		border: "0px",
		height: "1600px",
		overflow: "hidden"
    };
    
    $("#testframe").css(styles);
	
	$('.clSolInv').click(function( e )
	{
		e.preventDefault();
		inicializaEdoCta();
		
	});
	
	closeFancy();
};