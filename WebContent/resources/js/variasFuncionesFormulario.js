
	  var width = $(window).width();
var ofertaFinal = false;
$(document).ready(function() {

	//lightboxs funcionalidades		
	$('.open_lightbox').click(function(){
		myapp = {active: false}; 
		$('#light' + $(this).attr('data-div')).fadeIn('slow');
		$(".recuperar").hide(); //aqui cierro recuperar
	});
	$('.close_lightbox').click(function(){
		myapp = {active: true}; 
		$('.lightbox').fadeOut('slow');
	});
	
	
	//
	
$(window).resize(function(event){
			 $('.centrar_modal').css({
				 position:'absolute',
				left: ($(window).width() - $('.centrar_modal').outerWidth())/2,
			  });	
			 var width = $(window).width();	
			
			 
		});
		 
		 //
			
		 	/* menu secundario inversionistas */
			 $('.menu_responsivo').click(function(){	
				 event.stopPropagation();
				 $(".secondaryMenuInv ul").slideToggle();
			 });
			 
			 $('div:not(.menu_responsivo)').click(function(){	
				 var width = $(window).width();
				 if (width <= 750) { 
					 if( $(".secondaryMenuInv ul").is(":visible")){
						 $(".secondaryMenuInv ul").slideUp();
					 }
				 }
				 
			 });
			
			 $('.secondaryMenuInv ul li').click(function(){	
				 var width = $(window).width();	
				 if (width <= 750) { 
				 $(".secondaryMenuInv ul").slideUp();
				 
				 }
				
			 });
			
			 $(window).resize();		

});



function name_section () {
	 var texto_menu =  $('a .menuItemSelCont').text();
	setTimeout(function(){	
			$('.name_section_responsive span').html("");
			$('.name_section_responsive span').empty();
			$('.name_section_responsive span').html(texto_menu + "  ");
	 }, 300);
}
 var asterisk = function (campoForm) {
	 $(campoForm+" small").replaceWith(function() { return $(this).contents(); });
			 $(campoForm+":contains('\*')").html(function(_, html) {
			  return html.replace(/(\*)/g, '<small class="small">$1</small>');
	 });	
 }



 function atravez_pasos (){
	$(".ayudaPanel").fadeOut();
	
	// name_section ();
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	
		$('#frm_content input,#frm_content textarea').focus(function(){
		   $(this).data('placeholder',$(this).attr('placeholder'))
		   $(this).attr('placeholder','');
		});
		$('#frm_content input, #frm_content textarea').blur(function(){
		   $(this).attr('placeholder',$(this).data('placeholder'));
		});
		 var width = $(window).width();	
		 if (width <= 850) { 
			 setTimeout(function(){
						
						 $("#dvBtnMenu").removeClass("active"); 

			  }, 1000);
		 }
		 alertasCentrarH ();
		 conjuntoCombos();
		 scrollAyudas ();
		 pixel ();
		
		 //SegundoPasoInv ();
		 dondeInvierte();
		 //seleccionDomicilio ();
		 revisarNUevosChecks ();
		
 }
 
 
 
 function acordeon_responsivo () {
	 var width = $(window).width();	
	 	if (width <= 600) { 
		 $('.pestania').next("table").hide();
		 $('.pestania:eq(0)').addClass("active");
		 $('.pestania:eq(0)').next("table").show();
		 $('.pestania').click(function(){ 
			 if($(this).hasClass("active")) {
			   $(this).next("table").slideUp();
			   $(this).removeClass("active");
			} 
			else {
			   $('.pestania').next("table").slideUp();
			   $('.pestania').removeClass("active");
			   $(this).next("table").slideDown();
		       $(this).addClass("active");
			}  
		});
	 }
 }
 
 
	function actualizarNombre() {
		$(".privado .menu_principal article span").html( $("#name").val() + " " + $("#aPaterno").val() );
	}


	
	$(window).load(function() {
		alertasCentrarH ();

		
	});





	$(window).resize(function() {
		alertasCentrarH ();
		
	});

	function alertasCentrarH () {

		var content = $('.centrar_h');
		var width = $(window).width();
		content.css({
				position:'absolute',
						left: ($(window).width() - content.outerWidth())/2
						//top: ($(window).height() - content.outerHeight())/2
				});	
		
	}



	function cerrarAlert() {
		$(".alerts").removeClass("show");
		$(".veloBtnCerrar").fadeOut();
	}
	 
	

	
	function conjuntoCombos(){
		
		
		$(".conjunto").each(function(){	
			 var conjunto = $(this);
			 var select   = conjunto.find("select");
			 var nuevoSelect = conjunto.find(".selectNuevo");
			 var selectFieldValue = select.val(); 
			 	conjunto.find(".btnN[data-opcion=" +selectFieldValue +"]").addClass("active");
			
			 	if( nuevoSelect.hasClass("vacio")){
					conjunto.removeClass("lleno");
			 		conjunto.addClass("vacio");
			 	}
			if( nuevoSelect.hasClass("lleno")){
					conjunto.removeClass("vacio");
			 		conjunto.addClass("lleno");
			 	}
			
	   });
		
	
		
		$(".conjuntoChecks").each(function(){	
			 var conjunto = $(this);
		
			 conjunto.find(".btnN").removeClass("active");
				 conjunto.find("input").each(function(){	
					 var check = $(this);
					 if(check.prop("checked") ==  true){
						 
						 conjunto.find(".btnN[data-opcion=" +check.val() +"]").addClass("active"); 
					 } 
				 });

					asterisk(".labelsStl");
					asterisk(".numberAndTitle");
					asterisk(".titleDisabled");
		
	   });
		
		// console.log($('#receipt_payrollField table input[value="1"]').prop('checked'));
	}
 
	function ayuda(ayuda) {
		
		  if (width <= 1024) { 
			  if(ayuda  == ".claveElector" ||  ayuda  == ".noEmision"  || ayuda  == ".noVertical" || ayuda  == ".noSeccion" || ayuda  == ".ayudaCIC"){
				  
			  }
			
			  else {
				  $(".ayudaPanel").hide();
				  $(""+ayuda).fadeIn();
				  $(".columnaAyuda").fadeIn(); 
			  }
			 
			
		  }else{
			  $(".ayudaPanel").fadeOut();
			  $(""+ayuda).fadeIn();
		  }
	}
	
	function ayudaClick(ayuda) {
		 event.stopPropagation();
		 if (width <= 1024) { 
				  $(".ayudaPanel").hide();
				  $(""+ayuda).fadeIn();
				  $(".columnaAyuda").fadeIn(); 
		  }
		
	}

	function ayudaQuitar() {
		$(".ayudaPanel").fadeOut();
		
		  if (width <= 1024) { 
			  $(".columnaAyuda").fadeOut();
			
		  }
		 
	}
function btnN(btn) {
		
		var conjunto = $(btn).parent();
		var opcionSeleccionada = $(btn).attr("data-opcion");
		var selectField = conjunto.find('select');
		var selectFieldValue;
		 var nuevoSelect = conjunto.find(".selectNuevo");
			
		if($(btn).hasClass("active")){
				$(btn).removeClass("active");
			 selectFieldValue  = conjunto.find('select').attr("value", "0")
		}else {
			  conjunto.find(".btnN").removeClass("active");
			 $(btn).addClass("active");
			 selectFieldValue = conjunto.find('select').attr("value", opcionSeleccionada);
		}

	
	
	
		selectField.trigger("change");
		selectField.trigger("blur");
	if( nuevoSelect.hasClass("vacio")){
			conjunto.removeClass("lleno");
	 		conjunto.addClass("vacio");
	 	}
	if( nuevoSelect.hasClass("lleno")){
			conjunto.removeClass("vacio");
	 		conjunto.addClass("lleno");
	 	}
	}
	function btnCheck(tabla) {
		var conjunto = $(tabla).parent();
		var opcionSeleccionada = $(btn).attr("data-opcion");
		console.log(opcionSeleccionada);
		var selectField = conjunto.find('table');
		var selectFieldValue;
		
		
		
			

	

		
		

		 conjunto.find(".btnN").removeClass("active");
			
			selectField.find("input[value=" +opcionSeleccionada +"]").click();
			$(btn).addClass("active");
		 
	
	}

function scrollAyudas () {


		$(window).scroll(function(){
			 var alturaHeaderTitulo = $(".push_header").height() + 50;
			
			 var h = $('#contFrmReg').height() + alturaHeaderTitulo;
			  
			  var y = $(this).scrollTop ();
			
			  var width = $(window).width();	
			  if (width >= 1024) { 
				  if( y > alturaHeaderTitulo  && y < h  - $(".ayudaPanel").height() - 80){
				  $(".ayudaPanel").addClass("stick");
				  } else {
				   $('.ayudaPanel').removeClass('stick');
				  }
				  
				  if( y > alturaHeaderTitulo - 80  && y < h  - $(".columaNecesitas").height() -80){
					  $(".columaNecesitas").addClass("stick");
					  } else {
					   $(".columaNecesitas").removeClass("stick");
					  }
				  
				  
			  }else {
				  $('.ayudaPanel').removeClass('stick');
				  $(".columaNecesitas").removeClass("stick");
			  }
		});
		
		
		
	
}
 

function siAutorizo() {
	
		$("#aceptar_consulta").find("input[value='0']").trigger("click");
		
		mandodatos_hs();
		
		setTimeout(function(){	
			obtener_diagnostico();
	   }, 300);
		
		
}

function alerta (mensaje, id){
	$(""+id+"").closest(".field").find(".alerta").html("<i class='fa fa-caret-up'></i><p>"+mensaje+"</p>");
	$(""+id+"").closest(".field").find(".alerta").fadeIn();
}
function alertaQuitar (id){
	$(""+id+"").closest(".field").find(".alerta").fadeOut();
}


function listoPaso (){
//	console.log("listoPaso");


	$(".ui-progressbar").each(function(){	
		var progressbar=  $(this);
		var progressbarValue =  progressbar.attr("aria-valuenow");
//		console.log("progressbarValue"+progressbarValue);
		if(progressbarValue  == "100"){
			progressbar.closest(".itemMenu").addClass("full");	
		}
		if(ofertaFinal == true ){
			$("#menu10").addClass("full");
		}
	});
	 
}




$(window).resize(function() {
	centerContenido();
	
});

function centerContenido() {

	var content = $('.elementoCentrar');
	var width = $(window).width();
	var width2 = $("#frm_content").width();
	var width3 = $(".content").width();
	content.css({
		position:'fixed',
				left: (width - content.outerWidth())/2,
			
	});	
	
	if(content.hasClass("aclaracionesContainer") || content.hasClass("ayudaDocs")){
		content.css({
			position:'absolute',
					left: (width2 - content.outerWidth())/2,
				
			});	
	}
	if(content.hasClass("descripcionOferta")  ){
		content.css({
			position:'absolute ',
					left: (width3 - content.outerWidth())/2,
				
			});	
	}
	if(content.hasClass("detalleRaModal")  ){
				content.css({
			position:'absolute',
					left: (width - content.outerWidth())/2,
				
			});	
	}
	

}


function iniciaPopCaramelo(){
	centerContenido();
	$(".popCaramelo").addClass("show");

};

function closePopCaramelo() {
	$(".popCaramelo").removeClass("show");
	$(".velo").fadeOut();
	$(".velo2").fadeOut();

}

function closePopCaramelo() {
	$(".popCaramelo").removeClass("show");
	$(".velo").fadeOut();
	$(".velo2").fadeOut();
}

function closeSucesssPopUp(){
	$(".sucesssPopUp").removeClass("show");
	$(".velo").fadeOut();
	$(".velo2").fadeOut();
}



function mostrar_edicion(caja, edicion, actual, modificado, linkEditar )
{			
	$(modificado).val($(actual).val());
	
	$(edicion).show('slow');
	$(caja).hide();
	$(linkEditar).hide();
	
	$(modificado).trigger("focus");
	
}

function guardar_edicion(caja, modificado, linkEditar )
{
	var edited = $(modificado).val().trim();
	
	if(edited.length > 0)
	{							
		$("#input_listener").val(edited).click();
	}				
	$(caja).show();
	$(linkEditar).show();
	
}

function complete_edicion(caja, edicion, linkEditar )
{
	$(edicion).hide('slow');
	$(caja).show();
	$(linkEditar).show();
	
}
function cancelar_edicion(caja, edicion, linkEditar ) {
	$(edicion).hide('slow');
	$(caja).show();
	$(linkEditar).show();
  
	
}



















function mostrar_edicionFecha(caja, edicion, linkEditar)
{	
	$(edicion).show('slow');
	$(caja).hide();
	$(linkEditar).hide();
	
	
}

function guardar_edicionFecha(caja, edicion, linkEditar){
	
	
		
	$(caja).show();
	$(linkEditar).show();
	
}

function complete_edicionFecha(caja, edicion, linkEditar )
{
	$(edicion).hide('slow');
	$(caja).show();
	$(linkEditar).show();
	
}
function cancelar_edicionFecha(caja, edicion, linkEditar ) {
	$(edicion).hide('slow');
	$(caja).show();
	$(linkEditar).show();
  
	
}

	

function verClausula(btn) {
	$(btn).closest("li").find("aside").slideToggle();
	if($(btn).find("i").hasClass("fa-angle-down")){
		$(btn).find("i").removeClass("fa-angle-down");
		$(btn).find("i").addClass("fa-angle-up");
	}else{
		$(btn).find("i").addClass("fa-angle-down");
		$(btn).find("i").removeClass("fa-angle-up");
	}

}


function activarCheckbox(input) {
	
		if( $(input).is(":checked")){
			$(input).closest("span").addClass("active");
			$(input).closest("span").removeClass("falta");
		}else{
			$(input).closest("span").removeClass("active");
		}
 

}
function dondeInvierte()  {
	
	if( $("#dondeInviertes  option:selected").val() ==  "4")
	{
		$(".dondeOtros").slideDown();
	}else {
		$(".dondeOtros").slideUp();
	}
	fieldCount();
}

function revisionCheck() {
	var conteo_elementos = $(".aclaraciones ul li span").length;
	var i = 0;
	$(".aclaraciones ul li span").each(function(){	
		i += 1;
		if($(this).hasClass("active")){
	
		}else {
			$(this).addClass("falta");
		}
		if(i == conteo_elementos) {
			/*
			if(!$(".tablaHorizonte .conjuntoChecks .fullBtn.active").length || $("#dondeInviertes  option:selected").val() ==  "0" || $("#otroLugarInversion:visible").val() ==  ""){
					if(!$(".tablaHorizonte .conjuntoChecks .fullBtn.active").length){
						  $(".tablaHorizonte .conjuntoChecks").addClass("vacio");
					}
					if($("#dondeInviertes  option:selected").val() ==  "0"){
						$("#dondeInviertes").closest(".selectNuevo").addClass("vacio");
					}
					if($("#otroLugarInversion:visible").val() ==  ""){
						$("#otroLugarInversion").addClass("vacio");
					}
					
					$(".aclaraciones fieldset").slideDown();
					$('html, body').animate({
					       scrollTop: ($('#productoSinRiesgo').offset().top - 100)
					},1400);	
			}else{*/
				revisionCheck2();
			/*}*/
			
		 }
	});
	

	

}

function revisionCheck2() {
	console.log("revisionCheck2()");
	var campos =  $(".aclaraciones  ul li span");
	
  	var campos_vacios = campos.filter(function () {
	    return $(this).hasClass('falta');   
	}).length == 0;
	if(campos_vacios == false) {
			$(".velo").fadeIn();
			$(".riesgoLight").fadeIn();
			centerContenido();
		$("#elementRegProfInves").val(""+'noRisk_yes'+"::"+false);
		$("#elementRegProfInves").val(""+'noRisk_no'+"::"+true);
		
		$("#elementRegProfInves").change();
	}else{
		$("#elementRegProfInves").val(""+'noRisk_no'+"::"+false);
		$("#elementRegProfInves").val(""+'noRisk_yes'+"::"+true);
		
		$("#elementRegProfInves").change();
		
		 pasoTres();
	}

}


function pasoTres() {
	$(".velo").fadeOut();
	$(".riesgoLight").fadeOut();
	$("#menuRG .bloqueoSegundoPaso").remove();
	$("#hdNext\\:nextBeneficiaries").click();
	//changeUrlParam('notificaciones', 'v');
	fieldCount();
}

function regresarPerfil() {
	$(".velo").fadeOut();
	$(".riesgoLight").fadeOut();
}



function SegundoPasoInv () {
	var locacion = document.location.href;
	if($("#investmentProfile").is(":visible")){
		
		if(!$("#menuRG .bloqueoSegundoPaso").length){
			$("#menuRG").prepend("<fieldset onclick='aclaracionesPanel ()'  class='bloqueoSegundoPaso'></fieldset>")
		}
	}else {
		//$(".bloqueaAclaraciones").show();
		$("#menuRG .bloqueoSegundoPaso").remove();
		/*
		 setTimeout(function(){
			 $("#menu2").addClass("full");
		 	}, 1000);
		 	*/
	}
}

function aclaracionesPanel () {
	fieldCount();
	revisionCheck();
}



/*
function mostrarAclaraciones() {
	
	$(".aclaracionesContainer").fadeIn();
	$(".velo").fadeIn();
	centerContenido();
	$('html, body').animate({
	       scrollTop: ($('.aclaracionesContainer').offset().top - 100)
	},1400);	
}
*/
function subirSelfie() {
	$("#sectionSelfie label.fileinput-button").trigger("click");
}


function descripcionOferta (){
	$(".descripcionOferta").fadeIn();
	$(".velo").fadeIn();
	centerContenido();
	
} 
function pixel (){
	var prospectoID = $("#prospectoID").val();
	var kuboScoreVal = $("#inputKuboScore").val();
	//var contenedorTracksacai = document.getElementById("tracksacai");
	var valuePartner = $("#valuePartner").val();
	console.log("kuboScoreVal"+kuboScoreVal);
	
	
	var scores = ['A1','A2','A3','A4','A5','B1','B2','B3','B4','B5','C1','C2','C3','C4','C5', 'D1','D2', 'D3','D4','D5','E1','E2', 'E3','E4','E5'];

	
    if($.inArray(kuboScoreVal, scores) >= 0) {
    	
    	$("#offerConversion").attr("src", "https://tracking.global-analitics.net/aff_l?offer_id=1249&adv_sub="+''+prospectoID+''+"");
    	$("#inboxlabs").attr("src", "https://inboxlabs.go2cloud.org/aff_l?offer_id=1249&adv_sub="+''+prospectoID+''+"");
    
    	console.log("Pixel tracking.global-analitics   "+"entra dentro del rango del score A1 - E5: "+kuboScoreVal+" https://tracking.global-analitics.net/aff_l?offer_id=1249&adv_sub="+''+prospectoID+''+"");
    	console.log("Pixel inboxlabs.go2cloud   "+"entra dentro del rango del score A1 - E5: "+kuboScoreVal+" https://inboxlabs.go2cloud.org/aff_l?offer_id=1249&adv_sub="+''+prospectoID+''+"");
    	if(valuePartner == "KRW" ){
    		$("#tracksacai").attr("src", "https://clean.tracksacai.com/aff_l?offer_id=1665" );
    		/*
    		if($("#tracksacai").length){
	        	contenedorTracksacai.innerHTML = '<img src="https://clean.tracksacai.com/aff_l?offer_id=1665" width="1" height="1" />';
	        	console.log("Pixel tracksacai"+contenedorTracksacai.innerHTML);
	        }
    		*/
    	}
    	
		
		if(valuePartner == "S18" ){
			
				var inputAdv = $("#advBackInput").val();
				$("#advBack").attr("src", inputAdv);
				console.log(inputAdv);
				
		}
		
		console.log("");
		console.log("");
		console.log($("#valuePartner").val());
		console.log("");
		console.log("");
		
		if(valuePartner == "OCC"){
			console.log("");
			console.log("");
			console.log("################################################");
			console.log("############## PIXEL OCC #######################");
			console.log("################################################");
			console.log("");
			console.log("");
			
			$("#occPixel").attr("src", "http://bit.ly/kubofinancieroOCC");
		}
		if(valuePartner == "SVC"){
			 $("#sivinco").attr("src", "https://sivinco.rurl.me/api/pixel/?cvt=lead&cvn=Kubo+Lead&cva=9.30&guid=");
		}
		
		if(valuePartner == "CDY" ){
			var inputAdv = $("#advBackInput").val();
	
			var transactionId= inputAdv.substr(inputAdv.indexOf("tid=") + 4);

			if( transactionId.indexOf("&") != (-1) ){

				transactionId= transactionId.substr(0, transactionId.indexOf("&")) ;

			}


			$("#goCloud").attr( "src", "http://tracking.credy.eu/aff_lsr?offer_id=161&adv_sub="+''+prospectoID+''+"&transaction_id="+''+transactionId+''+""); 
				console.log($("#goCloud").attr("src"));
		}
		var inputAdv = $("#advBackInput").val();
		
		if(inputAdv.indexOf("optimus") != (-1)){
			facebook_events ('TAG' )
		}
		
	}else{
    	console.log("no entra dentro del rango del score A1 - E5");
    	 /*$("#frmMoreInfo .field").hide();
    	 $("#frmMoreI .field input").hide();
    	 fieldCount();*/
    }
    
    
	console.log( "kuboScoreVal"+kuboScoreVal);
	
}



function mostrarOferta() {
	$("#o1").trigger("click");
	$(".mostrarOferta").slideDown(function (){
		setTimeout(function(){	
			$("#o2").trigger("click");
		});
	});
	
	$(".mensajeAprobacion").hide();
	$(".nuevosProductos").slideUp();
}



function noGracias(nPantalla) {
	$(".mensajeAprobacion").hide();
	$(".nuevosProductos").slideUp();
	$(".noAceptoOferta").slideDown();
	if(nPantalla == 1){
		$("#o3").trigger("click");
	}
	
	if(nPantalla == 2){
		$("#o4").trigger("click");
		$(".aceptoOferta").slideUp();
		$(".descripcionOferta").slideUp();
	}
	
}


function recibirOferta (nPantalla) {
	$(".descripcionOferta").slideUp();
	$(".aceptoOferta").slideDown();
	if(nPantalla == 1){
		$("#o5").trigger("click");
	}
	if(nPantalla == 2){

		$("#o6").trigger("click");
	}
}

function enviarComentario() {
	//if( $("#motive_rejection option:selected").val() ==  "0" || $("#otroTextarea:visible").val() == "" ){
     if($("#otroTextarea:visible").val() == ""){
			
			$("#otroTextarea:visible").addClass("vacio");
			return false;
	}else{
		$("#otroTextarea").removeClass("vacio");
		return true;
		
	}
	
}




function otraRazon ()
{
	
	
	if( $("#motive_rejection option:selected").val() ==  "1")
	{
		$("#otroTextarea").slideDown();
	
	}else{
		
		$("#otroTextarea").slideUp();
	}
	if( !$("#motive_rejection option:selected").val() ==  "0" ){
		$("#motive_rejection").closest(".selectNuevo").removeClass("vacio");
	}
}

function otroTextArea (){
	if(!$("#otroTextarea:visible").val() == ""){
		$("#otroTextarea:visible").removeClass("vacio");
	}else{
		$("#otroTextarea:visible").addClass("vacio");
	}
}

function pruebaClick() {
	
	//console.log("no desconfies de la calors del fers");
	
}

var mostrarAyudaDoc;
function abrirAyudaDoc() {
	setTimeout(function()
    	    {
		
		$("#cmdSaveHelp").trigger("click");
		
		$(".ayudaDocs").fadeIn();
		$(".velo").fadeIn();
		
		$('html, body').animate({
		       scrollTop: ($('.ayudaDocs').offset().top - 100)
		},1400);
    				//clickCheckSegment ();
			centerContenido();
        	    	
        	    }, 500);
	
	
	 googleEvents ('solicitud-credito', 'clic solicitar ayuda', 'boton soliciar ayuda');
	 facebook_events ('clicSolicitarAyuda'); 
}



function abrirAyudaDoc2() {
	$(".ayudaDoc1").slideUp();
	$(".ayudaDoc2").slideDown();
	$("#cmdNeedSGBHelp").trigger("click");
	//dataLayer.push({'event': 'Ayuda documentos'});
	
	 GTM_eventos ('Ayuda documentos')
	 console.log('Ayuda documentos');
}

function cerrarAyudaDoc(pantalla) {
	$(".ayudaDocs").fadeOut();
	$(".velo").fadeOut();
	if(pantalla == 1){
		$("#cmdNoSGBHelp").trigger("click");
	}
	
}

function showGraph(btn, graph) {
		$(".menuGraficas li").removeClass("active");
		$(btn).addClass("active");

		$(".graficaN").addClass("hide");
		$(graph).removeClass("hide");
		if( graph == "#chart_area_div"){
			$(".izqNota").hide();
			$(".derNota").hide();
		}else{
			$(".izqNota").show();
			$(".derNota").show();
		}
}

function pixelSegundaMano() {
	var contenedor = document.getElementById("pixelSegundaMano");
	contenedor.innerHTML = '<scr'+'ipt language="javascript1.1" src="https://adserver.adtechus.com/bind?ckey1=funnel;cvalue1=fin;expiresDays=90;adct=text/html;misc=123"></scri'+'pt>'
	
	
	
	console.log("pixel  SegundaMano : "+'<script language="javascript1.1" src="https://adserver.adtechus.com/bind?ckey1=funnel;cvalue1=fin;expiresDays=90;adct=text/html;misc=123"></script>');
}

function tradeDoubler (){
    var prospectoID = $("#prospectoID").val();
	var contenedorTrade = document.getElementById("TradeDoubler");
    
    var protocol = window.location.protocol;
    if (protocol.indexOf(":")) {
    		protocol = protocol.substring(0,protocol.indexOf(":"));
    }
    
    
    contenedorTrade.innerHTML = '<img src="'+protocol+'://tbl.tradedoubler.com/report?organization=2073495&amp;event=352057&amp;leadNumber=' + prospectoID + '" border=0>';

	console.log('Pixel '+'<img src="'+protocol+'://tbl.tradedoubler.com/report?organization=2073495&amp;event=352057&amp;leadNumber=' + prospectoID + '" border=0>');
    
    
}
function validacionLada(inputEspejo) {		
	var inputEspejoValor = $(inputEspejo).val();		
	var inputReal = $(inputEspejo).next("input");		
	inputReal.val(inputEspejoValor);		
			
}		
function validacionLada2(inputEspejo) {		
	var inputEspejo = $(inputEspejo);		
	var inputEspejoValor = $(inputEspejo).val();		
	var inputReal = $(inputEspejo).next("input");		
			
			
		var posibleLada =  inputEspejoValor.slice(0,3);		
		var restoNumero =  inputEspejoValor.slice(3);		
				
		if(posibleLada == "044" ||posibleLada == "045"){		
			inputReal.val(restoNumero);		
		}		
				
		inputReal.blur();		
			
		validacionLLenadoMirror (inputEspejo, posibleLada);		
		mixPanel("telefonoCelular");	
}		
function resetMirrorTelefono (container){		
	var contenedorTelefono = $(container);		
	var inputFake = $(container).find(".validacionLadaField");		
	var inputRealValue = inputFake.next("input").val();		
			
	if(inputRealValue != ""){		
		inputFake.val(inputRealValue);		
		inputFake.blur();		
	}		
			
}		
function validacionLLenadoMirror (inputEspejo, posibleLada) {		
	console.log("validacion espejo");		
	var inputEspejo = $(inputEspejo);		
	var inputEspejoValor = inputEspejo.val();		
	if(inputEspejoValor != "" ){		
				
		if($("#nacionalidad option:selected").val() == 1){		
				if(inputEspejoValor.length == 10 || inputEspejoValor.length  == 13 ){		
					if(inputEspejoValor.length == 10 ){		
						if(posibleLada != "044" && posibleLada != "045"){		
							inputEspejo.removeClass("vacio");		
							console.log("no incluye lada")		
							$(".error-telefono").slideUp();		
							$(".iconoValidacion").addClass("fa-check");		
							$(".iconoValidacion").removeClass("fa-times");		
							$(".iconoValidacion span").html("Correcto");		
			
						}else{		
							inputEspejo.addClass("vacio");		
							console.log("10 e incluye lada")		
							$(".error-telefono").slideDown();		
							$(".iconoValidacion").removeClass("fa-check");		
							$(".iconoValidacion").addClass("fa-times");		
							$(".iconoValidacion span").html("Incorrecto");		
									
						}		
					}		
					if(inputEspejoValor.length == 13 ){		
						if( posibleLada == "044" || posibleLada == "045"){		
							inputEspejo.removeClass("vacio");		
							$(".error-telefono").slideUp();		
							$(".iconoValidacion").addClass("fa-check");		
							$(".iconoValidacion").removeClass("fa-times");		
							$(".iconoValidacion span").html("Correcto");		
						}else{		
							inputEspejo.addClass("vacio");		
							$(".error-telefono").slideDown();		
							$(".iconoValidacion").removeClass("fa-check");		
							$(".iconoValidacion").addClass("fa-times");		
							$(".iconoValidacion span").html("Incorrecto");		
						}		
					}		
				}else{		
					inputEspejo.addClass("vacio");		
					$(".error-telefono").slideDown();		
					console.log("diferente a 10 o 13 caracteres");		
					$(".iconoValidacion").removeClass("fa-check");		
					$(".iconoValidacion").addClass("fa-times");		
					$(".iconoValidacion span").html("incorrecto");		
							
				}		
		}else{		
			inputEspejo.removeClass("vacio")		
		}		
				
	}else{		
		console.log("vacio en blanco");		
		inputEspejo.addClass("vacio");		
	}		
			
	console.log(posibleLada);		
			
}		
function validacionLLenadoMirror2(campoTelefonoLada) {		
	$(campoTelefonoLada).blur();		
}		

function parentescoReply(select){

	var padreSelect = $(select).closest(".selectNuevo");
	var campoReply = $(select).closest(".selectNuevo").next("input");
	
	if( $(select+ " option:selected").val() != "0" ){
		if( $(select+ " option:selected").val() != "8" ){
		
			campoReply.val("");
			campoReply.val($(select+" option:selected").text());
			campoReply.blur();
			campoReply.slideUp()
			
		}else{
			campoReply.slideDown()
			campoReply.blur()
			campoReply.val("");
			
		}
	}else{
		campoReply.slideUp()
		campoReply.val("");
		campoReply.blur();
		
	}
}

function replySelect(select) {
	var campoReal =   $(select).closest(".selectNuevo").next("input");
	var campoRealValue = campoReal.val();
	console.log("tipo parentesco"+ campoReal);
	campoReal.slideUp();
	switch(campoRealValue){
	case "":
	   $(select).val("0");	
	break;
	case "Madre":
	   $(select).val("1");	
	break;
	case "Padre":
		 $(select).val("2");	
	break;
	case "Hermana(o)":
		 $(select).val("3");	
	break;
	case "Abuela(o)":
		 $(select).val("4");	
	break;
	case "Tia(o)":
		 $(select).val("5");	
	break;
	case "Prima(o)":
		 $(select).val("6");	
	break;
	case "Sobrina(o)":
		 $(select).val("7");	
	break;
	default:
		 $(select).val("8");	
		 $(campoReal).slideDown();
	}
	
}



function parentescoReset (){

	$(".parentesctoSelect").each(function(n){	
		var id =  "parentescoSelect-"+n;	
		var campoReal = $(id).next("input");
		$(this).attr("id", id)
		$(this).attr("onchange", "parentescoReply('#"+id+"')");	
		   
		   replySelect('#'+id);   
	});
	
	
	
	
	
}




function checksNuevos(check, id){
	var idCheck = id;
	
    var contenedor = $(check).closest(".input-checkbox-simple");
    var tabla = $(check).closest("table");
    	tabla.removeClass("vacio");
      if($(check).is(":checked")){
    	  	contenedor.addClass("fa-check");
    	    	var str = $(id).attr("id");
			 str = str.split("_")[1];
			 
			 var str2 = "true::"+str;
			 
			 $("#contactWayValue").val(str2);
			 $("#contactWayValue").blur();
			 
			 $("#cmdContactWay").trigger("click");
    	  	
      }else{
          contenedor.removeClass("fa-check");
      	 var str = $(id).attr("id");
			 str = str.split("_")[1];
			 
			 var str2 = "false::"+str;
			 
			 $("#contactWayValue").val(str2);
			 $("#contactWayValue").blur();
			 
			 $("#cmdContactWay").trigger("click");
	     
      }
}


function revisarNUevosChecks (){
	$(".input-checkbox-simple input").each(function(){	
		  var contenedor = $(this).closest(".input-checkbox-simple");
	      if($(this).is(":checked")){
	    	  	contenedor.addClass("fa-check");   
	      }else{
	          contenedor.removeClass("fa-check");
	      }
  });
	
	
}

function acordeonN(pestana) {
	
	$(pestana).next(".pestana__contenido").slideToggle();
 	$(pestana).find(".iconoAbrirCerrar").toggleClass("fa-angle-up fa-angle-down");
 		
}	

function comentarioRealizado(textarea){
	$(".comentario-realizado p").html("");
	$(".comentario-realizado p").html($(textarea).val());
}
function clicBtnGuardar() {
	displayMessageProcessing('msgprocessing',false);	
	$(".guardarProblemaDocs").trigger("click");
	console.log("clickBtnProblema");
	 //dataLayer.push({'event': 'Sin documentos'});
	 
	 GTM_eventos ('Sin documentos')
	 console.log('Sin documentos');
}
function enviarComentarioProblema() {
	$(".campo-textarea-btn").slideUp();
	$(".comentarioEnviado").slideDown();
	closeFancy();
}
function editarComentario() {
	$(".campo-textarea-btn").slideDown();
	$(".comentarioEnviado").slideUp();
}
function consolelog() {
	//console.log("clickkkkkkk!");
	return true;
}

function resetComentario() {
	if($("#comentarioProblemaDocs").val() != ""){
		$(".comentario-realizado p").html($("#comentarioProblemaDocs").val());
		$(".problemas-doc .pestana").trigger("click");
	
		$(".campo-textarea-btn").slideUp();
		$(".comentarioEnviado").slideDown();
	}
}





$(window).load(function() {

		 var width = $(window).width();	
		 	if (width >= 851) {
				avisoCliente ();
				
		 	}
		 	
		 	


});



function cerrarPopUp (modal) {
	$(".velo").fadeOut();
	$(modal).removeClass("show-alert");
}

function abrirModalesSecuencia (){
	cerraAlert();
	avisoCliente ();
}
function avisoCliente (){
	var locacion = document.location.href; 
	if(locacion.indexOf('avisoCliente=true')<0 && $(".aviso-cliente").length) {  
		setTimeout(function(){	
			$(".aviso-cliente").addClass("show-alert");
			$(".velo").fadeIn();
	    }, 300);
		changeUrlParam('avisoCliente', true);
		$('html, body').animate({
		       scrollTop: ($('.content').offset().top - 100)
		},1400);
	}
	
}



