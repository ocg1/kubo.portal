var historialListo = false ;

$(document).ready(function(){
		
		 if(!$(".section.historial").length){
			 $("#datosPersonales").trigger("click");
		 }else{
			 $(".section.historial").trigger("click");
				setTimeout(function(){ 
    				
		       		$('html, body').animate({
					       scrollTop: ($('#historialCompleto').offset().top - 150)
					},800);
		      		
		       		
				
						 },2000);
		 }
		 
		 
	
});
function abrirSiguiente() {	
	var siguientePanelBtn= $("#phoneCel_employ").closest("section").next("div.section");	
	if(!siguientePanelBtn.hasClass("active") ){
		siguientePanelBtn.trigger("click");
	}
}
/* funciones para el nuevo paso 1 basicos historial*/
	var preaprobacion = false;

	var regresarMenu = function () {
		$("#dvContMenuREG").show();
		$(".content").addClass("menuVisible");
		$("h2.encabezado12").text("Modifica tus datos personales");
		$("#btn_bcScore").hide();
		$("#testframe").css("height", "680px");
		$(".obten_diagnostico").show();
		$(".obten_diagnostico").text("Guardar y continuar");

		$("div.section.historial").off("click");
		$(".obten_diagnosticoBreve").hide();
		//$(".3minPreAprobacion").hide();
		if(!$("div.section.historial").hasClass("active")) {
			$("div.section.historial").trigger("click");
		}
		if(!$("#area").val() == "I") {
			$(".helpFrm").hide();
		}
		
		$("#frmHistCred").hide();
		$(".logo_12").hide();
		$("#logo").show();
	
		var width = $(window).width();	
			
		if(width <= 850) { 
			$("#dvBtnSimulador, #dvBtnMenu").show();
		}
		
		if( !$("#area").val() == "I") {
			$("#name, #second_name, #aPaterno, #aMaterno").attr("disabled", "disabled");
			$("#name, #second_name, #aPaterno, #aMaterno").addClass("inputLabel");
		}
		
		
		console.log("entra a regresa menú");
	}
	
	var regresarMenuRenovaciones = function () {
		$("h2.encabezado12").text("Solicita tu renovación");
	}
	
	var tab_section =  function (elemnt) 
	{			
		$(elemnt).toggleClass("active");
		$(elemnt).next("section").toggleClass("active");
	    $(elemnt).find("i").toggleClass("fa-chevron-down");
	    $(elemnt).find("i").toggleClass("fa-chevron-up ");	
	}
	
	var obtener_diagnostico =  function ()  
	{       
		var elemento = $('.paso_1_2 .validatorClass[index]:visible, #acSimple input[index]:visible,  #acSimple2 input[index]:visible,  #acSimple3 input[index]:visible');
		var conteo_elementos = elemento.length;
		var i = 0;
	
		console.log(conteo_elementos);
		
		$('.paso_1_2 .validatorClass[index]:visible, #acSimple input[index]:visible,  #acSimple2 input[index]:visible,  #acSimple3 input[index]:visible').each(function() {
			
		 	i += 1;
			
			
			if($(this).is('input') || $(this).is('textarea')) {
				
				if($(this).val().length==0) {
					$(this).removeClass("lleno");
					$(this).addClass("vacio");
				}else {
					$(this).removeClass("vacio");
		    		$(this).addClass("lleno");
				}
				
				if($(this).hasClass("telephone")){
 					if($(this).val().length <10 ){
 						$(this).removeClass("lleno");
 						$(this).addClass("vacio");
 					}else{
 						$(this).removeClass("vacio");
 						$(this).addClass("lleno");
 					}
 				}
			}

	    	 if( $(this).is('select')) {
	    		 if( $(this).val() == '0' || $(this).val() == ''  || $(this).val() == null) {
		    		 $(this).closest(".selectNuevo").removeClass("lleno");
			    	 $(this).closest(".selectNuevo").addClass("vacio");
			    	 console.log("esta vacio " + $(this).attr("id"));
	    		 }else {
	    			 $(this).closest(".selectNuevo").removeClass("vacio");
			    	 $(this).closest(".selectNuevo").addClass("lleno");
			    	 console.log("esta lleno " + $(this).attr("id"));
			    	 
	    		 }
	    	 }
	    	 
	    	if($(this).is('table')){
				if($(this).attr("id") == "aceptar_consulta" ){
					
					if($("#aceptar_consulta input:radio[value=0]").prop('checked') ) {
						$("#aceptar_consulta").removeClass("vacio");
						$("#aceptar_consulta").addClass("lleno");
					} else {
						$("#aceptar_consulta").removeClass("lleno");
						$("#aceptar_consulta").addClass("vacio");	
					}
					
				}
				else {
		    		if($(this).find(":radio").is(":checked")) {
			 
				     	$(this).closest(".conjuntoChecks").find(".btnN").removeClass("vacio");
				    	$(this).closest(".conjuntoChecks").find(".btnN").addClass("lleno");
		    		}else {
		    
			    	 	$(this).closest(".conjuntoChecks").find(".btnN").addClass("vacio");
				    	$(this).closest(".conjuntoChecks").find(".btnN").removeClass("lleno");
		    		}
				}
		
				if($(this).attr("id") == "forma-contacto" ){
					if( !$("#forma-contacto input:checked").length == 0) {
						$("#forma-contacto").removeClass("vacio");
						$("#forma-contacto").addClass("lleno");
					}else {
						$("#forma-contacto").removeClass("lleno");
						$("#forma-contacto").addClass("vacio");
					}
				}
	    	}
	    	if($(this).attr("id") == "nacionalidad" ) {
		    	if($("#nacionalidad").val() == "") {
		    		$("#nacionalidad").closest(".selectNuevo").removeClass("lleno");
		    		$("#nacionalidad").closest(".selectNuevo").addClass("vacio");
 				}else {
 					$("#nacionalidad").closest(".selectNuevo").removeClass("vacio");
 		    		$("#nacionalidad").closest(".selectNuevo").addClass("lleno");
 				}
 		   	}
	    	if($(this).hasClass("vacio") && !$(this).closest("section:not('.monto_proposito')").hasClass("active")) {
				$(this).closest("section").prev("div.section").click();	 
				  
			}
			
			if(i == conteo_elementos) {
				
				obtener_diagnostico_status ();	
			
			}
	 });			
		
		
	
	}
	var obtener_diagnostico_status   = function (bandera) {						
			$(".ayudaPanel").hide();			
			
			var width = $(window).width();	
			if(width <= 1024) { 
				$(".columnaAyuda").hide();
			}
		  	var campos = $('.paso_1_2 .validatorClass[index]:visible, #acSimple input[index]:visible,  #acSimple2 input[index]:visible,  #acSimple3 input[index]:visible, .selectNuevo:visible, .conjuntoChecks:visible .btnN, .validacionLadaField:visible');

		  	var campos_vacios = campos.filter(function () {
			    return $(this).hasClass('vacio');   
			}).length == 0;
			
			if(campos_vacios == false) {
			  	$(".mensaje_campos_obligatorios").slideDown(); 
			  	
			}else {
				$(".plecaForm").hide();
			    $("#llenadoHistorial").hide();
				$(".obten_diagnosticoBreve").hide();
			   
			    $(".mensaje_campos_obligatorios").slideUp(); 
			    $("body").addClass("loaderHistorialBreve"); 
			
			    console.log("llenos campos");
			   if( preaprobacion == false) {
				   gaVirtualPages ('/vp/prospector.html');
				   console.log("triggerClick");
				   console.log("preaprobacion false");
				   mandodatos_hs();
				   $("#hdNext\\:cmdValidaRelationship").trigger("click");
				   
				  // dataLayer.push({'event': 'Datos Personales 1'});
				   
				   GTM_eventos ('Datos Personales 1')
				   // console.log('Datos Personales 1');
				   
				   googleEvents ('solicitud-credito', 'clic consultar prospector', 'boton consultar prospector');
				   facebook_events ('clicConsultarProspector' );
				   hj('trigger', 'id_provider');
					mixPanel ("clicConsultarProspector")
				   
			   }else {
				   sendInteractoa ();
				   console.log("preaprobacion true");
				  
					if($("#dvContMenuREG").is(":hidden") ) {
						if(historialListo == false){
							asignar_nota_del_coach();
							validar_historial_crediticio ();
							console.log("historialListo"+historialListo);
							//dataLayer.push({'event': 'Datos Personales 2'});
							
							   GTM_eventos ('Datos Personales 2')
							 googleEvents ('solicitud-credito', 'clic autorizar buro', 'boton autorizar buro');
							   facebook_events ('clicAutorizarBuro' ); 	
								mixPanel ("clicAutorizarBuro");
						}else {
							closeWindowPrep();
							console.log("historialListo"+historialListo);
						}			
					
						
				    }else {
				    	
				    		closeWindowPrep();			
						
				    		mandodatos_hs();
				    		
				    	console.log("termina");
				    
				    }	
			   }
			  
			}
	}
	function closeWindowPrep(){
		/* parent.$("#valFlagPrep").val("aceptado");
		parent.$.fancybox.close(); */
		
		parent.$("#hdNext\\:siguienteIncomeExpense").click();
		parent.$.scrollTo('#header', 800, { axis:'y' })
		console.log("quiere llegar a income");
		gaVirtualPages ('/vp/id_provider.html');
	
		
	}
	function closeWindow(){
		parent.$("#valFlagPrep").val("NOACEPTADO");
		parent.showIni();
	}
				          
	var isValid = false;
function loaderDiagnosticoBreve() {
	
		mandodatos_hs
	
  	   $(".consultaHistorialBreveLoader").addClass("show");
  	   $(".consultaHistorialBreveLoader").fadeIn();
  	   $(".veloE").fadeIn();
  	 
	  	 $('html, body').animate({
		       scrollTop: ($('.pLoader').offset().top -  $(".header").height()-20)
		},800);
  	     

}
	

function  returnConsultaBreve(xhr, status, args){
	console.log("returnConsultaBreve"+args.isValid);
		
	if( args.isValid === undefined ){
		
	}else{
		
		if( args.isActive == true ){
	
				$(".section.historial").trigger("click");
				if(args.isValid==true){
					
					$(".obten_diagnosticoBreve").hide();
			        $(".obten_diagnostico.solicitud").show();
					$(".section historial").trigger("click");	 
					console.log(" returnConsultaBreve args.isValid==true");
					$(".ayuda4").fadeIn();
					$(".columnaAyuda").show();
					setTimeout(function(){ 
						$(".ayuda4").fadeOut();
						var width = $(window).width();	
						
						if(width <= 1024) { 
							$(".columnaAyuda").hide();
						}
			       		
					
							 },6000);
					
				}else{
					
					$(".consultaHistorialBreveLoader").removeClass("show");
				    $(".consultaHistorialBreveLoader").fadeOut();
				    console.log(" returnConsultaBreve args.isValid==false");
					$(".veloE").fadeOut(400,function(){ $(".siguienteIncomeExpense").click() });
					
					return;
					
				}
				
		}else{
			$(".consultaHistorialBreveLoader").removeClass("show");
			 $(".consultaHistorialDeshabilitada h3 span").text($(".privado .menu_principal article span").text());
		    	
		    $(".consultaHistorialBreveLoader").fadeOut( 400,function(){ 
		   // $(".veloE").fadeOut();
		    		
			    $(".consultaHistorialDeshabilitada").addClass("show");

				  
			    $(".consultaHistorialDeshabilitada").show();
				$('html, body').animate({
				       scrollTop: ($('.consultaHistorialDeshabilitada').offset().top - 150)
				},800);
				
			
				
		    });
		    
		    return;
		}
	
	}
	
	$(".consultaHistorialBreveLoader").removeClass("show");
	$(".consultaHistorialBreveLoader").fadeOut();
	
	$(".veloE").fadeOut();
	
}
	
/*consulta historial Breve */



	
	function enviaHistorialBreve (){
		
	
		var mesSeleccionado = $("#month").find('option:selected').text();
		var mesNumero;

		if(mesSeleccionado == "Enero"){
			mesNumero = "01"
		}
		if(mesSeleccionado == "Febrero"){
			mesNumero = "02"
		}
		if(mesSeleccionado == "Marzo"){
			mesNumero = "03"
		}
		if(mesSeleccionado == "Abril"){
			mesNumero = "04"
		}
		if(mesSeleccionado == "Mayo"){
			mesNumero = "05"
		}
		
		if(mesSeleccionado == "Junio"){
			mesNumero = "06"
		}
		
		if(mesSeleccionado == "Julio"){
			mesNumero = "07"
		}
		if(mesSeleccionado == "Agosto"){
			mesNumero = "08"
		}
		
		if(mesSeleccionado == "Septiembre"){
			mesNumero = "09"
		}
		if(mesSeleccionado == "Octubre"){
			mesNumero = "10"
		}
		if(mesSeleccionado == "Noviembre"){
			mesNumero = "11"
		}
		if(mesSeleccionado == "Diciembre"){
			mesNumero = "12"
		}
		
		var stringBasicos ;
	

		function formToJSON() {
            return JSON.stringify({
            	
            			"apellidoPaterno"    :  $("#fatherLastName").val(),
            			"apellidoMaterno"    :  $("#motherLastName").val(),
            			"primerNombre"       :  $("#firstName").val(), 
            			"segundoNombre"      :	$("#middleName").val(), 
            			"fechaNacimineto"    :  $("#day").find('option:selected').text() + mesNumero + $("#year").find('option:selected').text(), 
            			"RFC"                :  $("#rfc").val(),
            			"Calle" 			 :  $("#street").val(),
            			"numeroExterior"     :  $("#address_number").val(),
            			"numeroInterior"     :  $("#numInt").val(),
            			"colonia" 			 :  $("#coloniaEmp").val(),
            			"municipio" 		 :  $("#delegMun").val(),
            			"estado" 			 :  $("#estado").val(),
            			"codigoPostal" 		 :  $("#zip_code").val(),
            			"clientId"			 :  $("#prospectoID").val()
            		
            });		
		}
            $.ajax({
            	contentType: 'application/json',
                url: "http://192.168.11.222:9090/prospector/",
                type: "post",
                crossDomain: true, 
                data: formToJSON(),
                dataType : "jsonp",
                context: document.body,
               
                success: function(data){
                	 console.log(data.score);
					 console.log(data.burSolNum);
					 console.log(data.valido);
					 console.log(data.clientId);
                 },
              error:function(jqXHR, textStatus, errorThrown) {
                   alert("failure");
                 }   
           }); 

		/*
			$.post(urlH, stringBasicos)
				 .done(function() {
					 	
					 console.log(data.score);
					 console.log(data.burSolNum);
					 console.log(data.valido);
					 console.log(data.clientId);
				 })
				 .success(function(data, estado, jqXHR){
					   
					isValid = (data.valido == 'true' );
					ejecutaRegreso ();
					 console.log(data.score);
					 console.log(data.burSolNum);
					 console.log(data.valido);
					 console.log(data.clientId);
				})
				
				.error(function() {

					
				});
				
				
				
			
				setTimeout(function(){ 
					 console.log(stringBasicos);
				 },1000);
				
				$(".consultaHistorialBreveLoader").addClass("show");
				*/
            
	}
	function cerraAlertEdad () {
		$(".obten_diagnosticoBreve").show();
		$(".velo2").fadeOut();
		$(".alerts").removeClass("show");
		
		
	}

	function ejecutaRegreso (){

		if(isValid){
			
		
		}else{
	
		}
	

	}

	
	
	function ultimoCampo(lastField) {	
		var lastField =  $(lastField);
	   console.log(lastField);
	   console.log($(lastField));
	    var actividadTiempo = setTimeout(noActivo, 5000);
	  
	    function resetearactivo(){

	        $("body").addClass('actividad');
	        $("body").removeClass('no-actividad');
	        clearTimeout(actividadTiempo);
	           if( !$(".consultaHistorialBreveLoader").hasClass("show")){
	        	   actividadTiempo = setTimeout(noActivo, 5000);
	           }
	       
	        console.log("arranca tiempo");
	    }
	    
	    function noActivo(){
	        $("body").addClass('no-actividad');
	        $("body").removeClass('actividad');
		        if( !$(".consultaHistorialBreveLoader").hasClass("show")){
		        	if( !$("body").hasClass("loaderHistorialBreve") || $(".nuevoLoader").is(":visible")){
		        		if($("#general select").is(":focus") || $("#general input").is(":focus") || $("#general textarea").is(":focus")){
		        		
		        			if(lastField.is(":focus")) {
		        				obtener_diagnostico();
		        			}else{
		        				console.log("esta en focus")
		        			}
		        		}else{
		        			console.log("obtener_diagnostico();");
		        			obtener_diagnostico();
		        		}
		        		
		        	}else {
		        		console.log("tiene loader");
		        	}
		        }
		        console.log("tiempo terminado");
	    }
    
    	   console.log("ultimo campo");
           $(document).bind('mousemove', function(){resetearactivo()});
   
           $(".obten_diagnosticoBreve" ).click(function() {
   	    		clearTimeout(actividadTiempo);
   	    		console.log("limpiar tiempo");
   	    		actividadTiempo = setTimeout(noActivo, 150000);
   	       });
    
    }

	
	