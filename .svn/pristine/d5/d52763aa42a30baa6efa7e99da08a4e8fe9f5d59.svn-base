$(document).ready(function()
{	
	$(".summaryContent").hide();
	$('.numTop').hide();
	$('.numBottom').hide();
	$('#graphPie2').hide();
	$('.detailsClient').hide();

	$(".summaryTitle").click(function(e){
		e.preventDefault();
		if($(this).next().is(":visible")) {
			$(this).next().hide('slow');	
		} 
		else {
			$(this).next().show('slow');
		}
	});
	
	$('#summaryLinkTop').click(function(e) {
		e.preventDefault();
		$(this).text($(this).text() == 'Ver por cantidad' ? 'Ver por monto' : 'Ver por cantidad');
		
		if( $('.numTop').is(":visible") ) {
		   $('.numTop').hide();
		   $('.monTop').show();            
		} else {
		   $('.numTop').show();
		   $('.monTop').hide();        
		}
	});

	$('#summaryLinkBottom').click(function(e) {
		e.preventDefault();
		$(this).text($(this).text() == 'Ver por monto' ? 'Ver por cantidad' : 'Ver por monto');

		if( $('.numBottom').is(":visible") ) {
		   $('.numBottom').hide();
		   $('.monBottom').show();            
		} else {
		   $('.numBottom').show();
		   $('.monBottom').hide();        
		}

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
	


	/*
	var currentValue = $('#detailsCurrentValue');
	var balanceValue = $('#detailsBalance');

	$("#detailsInvestSlider").slider({
		max: 1250,
		min: 250,
		slide: function(event, ui) {
			currentValue.html(ui.value);
			balanceValue.html(1250-ui.value);
		},
		step: 50,
		animate: true
	});
	*/

});
