console.log("js/respuestaBuro.js");

var timerID;
var timerSegment;
var actualTimer;
	
		function initWatching(){
			
			console.log("initWatching....");
			clearInterval(timerID);
			
			//console.log( "varInterval antes " + timerID);
			
			timerID = setInterval( function(){ verificaSegmentAction() }, 10000 );
			actualTimer = timerID;
				
				
		}
			
		function verificaSegmentAction(){
			
			if( $("#cmdSegment").length ){
				$("#cmdSegment").click();
			}else if( parent.$("#cmdSegment").length ){
				parent.$("#cmdSegment").click();
			}
				
		}
		
		function initCheckSegment(){
		
		// timerSegment = setInterval( function(){ parent.$("#cmdSegment").click(); }, 10000 );
			
		//	setTimeout( function(){ parent.$("#cmdSegment").click(); }, 10000 );
		
		}
		
		function segmentComplete(xhr, status, args){
			
			console.log( "segmentComplete: " + args.flagRepeat  );
			console.log( "args.flagRepeat: " + args.flagRepeat );
			
			if( args.flagRepeat != undefined  &&   ( (!args.flagRepeat) || (args.flagRepeat+"") == 'false' ) ){
				
				console.log( "regresa -- : "+ args.flagRepeat );
				
				window.clearInterval(timerID);
				window.clearInterval( timerSegment );
				
				console.log( "varInterval " + timerID+"  args.flagRepeat: "+args.flagRepeat+"  args.isvalid: "+args.isvalid +"  args.pageSend: "+args.pageSend );
				
				if( args.isvalid ){
					mostrar_mensaje_preaprobacion();
					console.log( "pagina " + args.pageSend);
				}
				
				//
				
			}else if( args.flagRepeat === undefined && args.flagRepeat === undefined && args.isvalid === undefined   ){
				
				window.clearInterval(timerID);
				window.clearInterval( timerSegment );
				
			}
			
			
		}
		

$(window).load(function() {
	centerContentInt($(".mensaje_preaprobacion"));		
});

$(window).resize(function() {
	 var resizeId;
	   clearTimeout(resizeId);
	   resizeId = setTimeout(centerContentInt($(".mensaje_preaprobacion")), 500);   
});

var centerContentInt= function (elemento){
	$(elemento).css({
	    position:'fixed',
		left: ($(window).width() - $(elemento).outerWidth())/2,
	});
}
//<![CDATA[		       
     function mostrar_mensaje_preaprobacion (){
     		setTimeout(function(){	
     			 var width = $(window).width();	
     			 	 centerContentInt($(".mensaje_preaprobacion"));
     				 $(".mensaje_preaprobacion").addClass("mostrar");
     				 $(".velo").fadeIn(); 
     		 }, 1000);
     	}

     function muestraLienzo(){ 
    	 lienzoPreaprobacion();
    	 setTimeout( function(){ $("#cmdViewScreen").click(); }, 2200 );
			
			if( $("#rechazoPantalla").length ){
				
					$("#rechazoPantalla").trigger("click");	
			
			}
			if( $("#efl").length ){
				
				$("#efl").trigger("click");	
		
			}
		
			 googleEvents ('solicitud-credito', 'clic ver resultado', 'boton ver resultado');
			
     }
     
     
     

	     function lienzoPreaprobacion () {
	     	     $('html, body').animate({scrollTop:0}, 'slow');	
	     		 $(".mensaje_preaprobacion").removeClass("mostrar");
	     		 if($("#frm_content").is(":visible")) {
	     
		     		 $(".velo").fadeOut();
		 			$("#version_publica").unwrap("<div id='light20'>");
	     			$(".lienzo_preaprobacion").unwrap("<div id='version_publica'>");
		     		 $(".lienzo_preaprobacion").slideDown("slow");
		     		 $("#dvContMenuREG").addClass("opacity");
		     		 $(".helpFrm").addClass("opacity");
	     		 }else {
	     			    $(".velo").fadeOut();
	     				$("#light20").fadeIn(); 
	     				centerContent();
	     		 }
 
	     }
	     
	     function  cerrarLienzoPreaprobacion() {
	     	
	     	 if($("#frm_content").is(":visible")) {
	     		 $('html, body').animate({scrollTop:0}, 'slow');	
		     	 $(".lienzo_preaprobacion").slideUp("slow");
		     	 $("#dvContMenuREG").removeClass("opacity");
		     	 $(".helpFrm").removeClass("opacity");
		     	 
		     	 if($("#cerrarLienzoPrimeraVez").length){
					 //dataLayer.push({'event': 'Preaprobación'});
					 
					   GTM_eventos ('Preaprobación')
					   
					   googleEvents ('solicitud-credito', 'clic regresar a la solicitud', 'boton regresar a la solicitud');

					 console.log('Preaprobación');
				 }
		     	 
		     	 
		     	if($(".contOfertas").length || $(".paso_1_2.areaL").length){
		     		displayMessageProcessing('msgprocessing',false);
		     		setTimeout(function(){
					
				  		location.reload();
						
				  	},1000);
		     		
		     	}

		     
		     	$("#frm_cont_lienzo").css("height", "auto");
	     	 }else {
     			 $("#light20").fadeOut(); 
     		 }
	     	 
	     }
function muestraLienzoSegundaVez() {
		if($("#inputKuboScore").val() !=  "" && $("#dvContMenuREG").is(":visible")){
			muestraLienzo();
			$("#cerrarLienzoSegundaVez").show();
		  	$("#cerrarLienzoPrimeraVez").hide();
		  	$(".simulador_pre").show();
		}
	
}     
function call_SGB_on_complete(xhr, status, args)
{
	var blocked_person_ENABLED = args.blocked_person_ENABLED;
	
	console.log("call_SGB_on_complete(): " + blocked_person_ENABLED);
	
}
		