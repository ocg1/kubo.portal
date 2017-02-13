 
console.log("historial_crediticio.js");


//[data-item-label='Bancoppel']



function  bancoppel () {
	if( $("#acSimple_input").val() == 'BANCOPPEL' || $("#acSimple_input").val() == 'Bancoppel' || $("#acSimple_input").val() == 'Coppel' || $("#acSimple_input").val() == 'COPPEL' ) {
		  console.log("bancopel");
          $(".imagen_coppel").slideDown();
          $(".tarjeta_frente").slideUp();
          $(".numero_credito").show();
          $(".tarjeta").hide();
        }else {
        	  console.log("no bancopel");
              $(".imagen_coppel").slideUp();
              $(".tarjeta_frente").slideDown();
              $(".numero_credito").hide();
              $(".tarjeta").show();
        }
	return true;
}

function showMessage()
{
	var element = $('#fourDig');
	var t = $('.showMessage').offset().top;
	var l = element.offset().right;
	l=0;
	$('#credNum').css({'top' :  (t-20)+'px', 'right' : (l+300)+'px'});
	
	$("#creditDesc").hide();
	$("#cardDesc").show();
	
	if( 
			$("#acSimple_input").val().toUpperCase().indexOf("LIVERP") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("PALACIO") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("IERRO") != (-1)

	)
	{
		$("#infoDepVisa").show();
		$("span#msgCredit").show();
		$("#credNum").css("height","230px");
		$("#message").css("height","210px");
	}
	else if( 
			$("#acSimple_input").val().toUpperCase().indexOf("COPPEL") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("COPEL") != (-1)
	)
	{
		$("#creditDesc").show();
		$("#cardDesc").hide();
		$("span#msgCredit").hide();
		$("#credNum").css("height","150px");
		$("#message").css("height","130px");
	}
	else if( 
			$("#acSimple_input").val().toUpperCase().indexOf("C&A") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("C & A") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("C Y A") != (-1) ||
			$("#acSimple_input").val().toUpperCase().indexOf("CYA") != (-1)
			
	)
	{
		$("#infoDepVisa").show();
		$("span#msgCredit").show();
		$("#credNum").css("height","230px");
		$("#message").css("height","210px");
	}
	else
	{
		$("#infoDepVisa").hide();
		$("span#msgCredit").show();
		$("#credNum").css("height","230px");
		$("#message").css("height","210px");
	}
	
	$('#credNum').slideDown();	
}

function hideDvSureCont(){
		$('#dvGenSure').hide("blind", 500);
}


function out_firstSecond () {
	$('#frmBasicos,  div.historial, section.historial_crediticio #general, .obten_diagnostico, .PrimerSegundoPaso, .encabezado12').fadeOut();
	$('.historial_crediticio').addClass("active").css("width", "100%"); 
}

function showSureCont()
{	
		out_firstSecond (); 
/* 		
		$.scrollTo($("#selSixMonth"),10, { axis:'y' });
*/
		$.scrollTo('#header',300, { axis:'y' });
		
		$('#dvGenSure').fadeIn( 500);
		
		$("#cmdDatosReview333").click(); 
		

}

function hideMessage(){
	$('#credNum').slideUp();	
}

function displayWait(){
	// $('#dvWait').show();
	return true;
}

function showIni(){
	$('#dvContPreaprobacion').fadeOut( 500,function(){
		$.scrollTo('#header',300, { axis:'y' });
		$("#dvContPreaprobacion").empty();
		$('#dvGenSure').css({'display' : 'none'});
		$('#general').fadeIn( 500);
		/* $.scrollTo($("#selSixMonth"),10, { axis:'y' });*/
		
	});
}

function dataReview(){
	$("#hdNext\\:siguienteBasicosReview").click();
}
