/*funciones para la validacion del campo vacio y llenos */

var pasoSiete;

function field_hidden () {
	 $( ".selectchange").change(function(e) {
		 var element = $(this);
		 fieldHiddenId (element,"cbo-who-answered", "99");
		 
		 fieldHiddenId (element, "edoCivil", "2");
		 if($(this).attr("id") == "nivelEst") {
			if($(this).val() == '5' || $(this).val() == '6' || $(this).val() == '7') { 
					show_hidden_field();
			}
		 }
	 });
}

function fieldHiddenId (element, id, value) {
	   if(element.attr("id") == id) {
			if(element.val() == value) { 
				show_hidden_field()
			}
	  }
}


function show_hidden_field() {
	setTimeout(function(){   resetar_indices(); }, 1000);
}

function resetar_indices() {
	$("img.validatorClass, input.validatorClass, textarea.validatorClass, select.validatorClass, table.validatorClass,  #acSimple2 input,  #acSimple3 input, .validatorClass2, .perfilInversionista").each(function(index) {
	    return $(this).attr("index", "");
	});
	
	/*codigo para configurar asignar un orden a las tabs*/
	$("#frm_content input[type='text'], #frm_content input[type='password'], #frm_content input[type='email'], #frm_content textarea, #frm_content select, #frm_content table.validatorClass, table.validatorClass2 .validatorClass2, .ui-selectmanycheckbox, .fileinput-button, .perfilInversionista").each(function(Index) {
		 return $(this).attr("tabindex", "");
	});
	
	crear_indices(); 
	blur_indices();
	focus_indices();

}

function crear_indices() {
	
	$("img.validatorClass, input.validatorClass, textarea.validatorClass, select.validatorClass, .validateChecks, table.validatorClass,  #acSimple2 input,  #acSimple3 input,  table.validatorClass2, .perfilInversionista").each(function(index) {
		return $(this).attr("index", index);
	});
	
	/*codigo para configurar asignar un orden a las tabs*/
	$("#frm_content input[type='text'], #frm_content input[type='password'], #frm_content input[type='email'], #frm_content textarea, #frm_content select, #frm_content table.validatorClass, table.validatorClass2, .ui-selectmanycheckbox,  div.fileupload-buttonbar, table.validator_DISABLED, .perfilInversionista").each(function(Index) { 
		 if ($(this).attr('disabled') !== undefined ) {
		
		 	} else {
				return $(this).attr("tabindex", Index+1);
			}
	 });
	    var locacion = document.location.href; 
	   	if(locacion.indexOf('revisionCampos')>0) {  
	   		setTimeout(function(){  validacionLlenado2(); }, 1000);
	   	}
}
function focus_indices() {
	
	 $( "table.validatorClass input, table.validatorClass2 input").click(function() {
		 
		 	var this_indice = $(this).closest("table").attr("index");
	 	    this_indice = Number(this_indice.replace(/[^0-9\.]+/g,""));
	 		validacion_llenado(this_indice);	
	 	
	 	 	$(this).closest(".conjuntoChecks").find(".btnN").removeClass("vacio");
	    	$(this).closest(".conjuntoChecks").find(".btnN").addClass("lleno");
	 });
	 
	
	 $(".perfilInversionista input").click(function() {
			var this_indice = $(this).closest("table").attr("index");
	 	    this_indice = Number(this_indice.replace(/[^0-9\.]+/g,""));
	 		validacion_llenado(this_indice);
	 		
		 	var tablaChecks = $(this).closest("table");
		 		tablecheckId = tablaChecks.attr("id");
		 
		if(!$("#"+ tablecheckId +" input:checked").length == 0) {
			tablaChecks.removeClass("vacio");
			tablaChecks.addClass("lleno");
		}else {
			tablaChecks.removeClass("lleno");
			tablaChecks.addClass("vacio");
		}
	 });
	
		 $("textarea.validatorClass, input.validatorClass,  select.validatorClass,  #acSimple2 input,  #acSimple3 input").focus(function(){
			    var this_element = $(this);
	    	 	var this_indice = $(this).attr("index");
	    	 	    this_indice = Number(this_indice.replace(/[^0-9\.]+/g,""));
	    	 		validacion_llenado(this_indice);			    	 		
	    	 		total_gastos(this_element);
	    	 		if ( $(this).attr("id") ==  "txt_question-yes") {
				    	 total_expense();
				    }	
		 }); 
	
} 

function llenoVacioFecha() {
	 $("#comboDate select").each(function() {

	 		    	 if( $(this).val() == '0' ) {
	 		    		 $(this).closest(".selectNuevo").removeClass("lleno");
		 		    	 $(this).closest(".selectNuevo").addClass("vacio");
	 		    	 }else {
	 		    		 $(this).closest(".selectNuevo").removeClass("vacio");
		 		    	 $(this).closest(".selectNuevo").addClass("lleno");
	 		    	 }
	
	  });
	 		    	 
}
function blur_indices() {
		  $("textarea.validatorClass, input.validatorClass, select.validatorClass,  #acSimple2 input,  #acSimple3 input").blur(function(){
			    var this_element = $(this);
			    var numeroDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".contenedor_docs div");	
				var selectDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find("select");	
				var areaPunteadaDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".ui-button");	
				
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
					if($(this).hasClass("porcentaje-bene")){
						if($(this).val() == 0){
							$(this).removeClass("lleno");
	 						$(this).addClass("vacio");
						}else{
	 						$(this).removeClass("vacio");
	 						$(this).addClass("lleno");
	 					}
						fieldCount();
					}
					
					
				}
				
				if($(this).attr("id") == "inp_income_otherfamilyE") {
	 		   		if($("#inp_income_otherfamilyE").val() <= 0  || $("#inp_income_otherfamilyE").val() == ""){
	 		   			$("#inp_income_otherfamilyE").removeClass("lleno");
	 		   			$("#inp_income_otherfamilyE").addClass("vacio");
	 		   		}else {
	 		   			$("#inp_income_otherfamilyE").removeClass("vacio");
	 		   			$("#inp_income_otherfamilyE").addClass("lleno");
	 		   		}
	 		   	}
	
		    	 if( $(this).is('select')) {
		    		 if( $(this).val() == '0' || $(this).val() == ''  || $(this).val() == null) {
			    		 $(this).closest(".selectNuevo").removeClass("lleno");
				    	 $(this).closest(".selectNuevo").addClass("vacio");
		    		 }else {
		    			 $(this).closest(".selectNuevo").removeClass("vacio");
				    	 $(this).closest(".selectNuevo").addClass("lleno");
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
		    	 
		    	 if($(this).attr("id") == "Day" ) {
	 		    		if($("#day option:selected").val() == "0") {
	 		    			$("#day").closest(".selectNuevo").removeClass("lleno");
	 		    			$("#day").closest(".selectNuevo").addClass("vacio");
		 				}else {
		 					$("#day").closest(".selectNuevo").removeClass("vacio");
		 					$("#day").closest(".selectNuevo").addClass("lleno");
		 				}
		 		  }
		    	 
		    	 if($(this).attr("id") == "month" ) {
	 		    		if($("#month option:selected").val() == "0") {
	 		    			$("#month").closest(".selectNuevo").removeClass("lleno");
	 		    			$("#month").closest(".selectNuevo").addClass("vacio");
		 				}else {
		 					$("#month").closest(".selectNuevo").removeClass("vacio");
		 					$("#month").closest(".selectNuevo").addClass("lleno");
		 				}
		 		  }
		    	 if($(this).attr("id") == "year" ) {
	 		    		if($("#year option:selected").val() == "0") {
	 		    			$("#year").closest(".selectNuevo").removeClass("lleno");
	 		    			$("#year").closest(".selectNuevo").addClass("vacio");
		 				}else {
		 					$("#year").closest(".selectNuevo").removeClass("vacio");
		 					$("#year").closest(".selectNuevo").addClass("lleno");
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
		    	}
		    	
		    	 if($(this).is('img')){
				    	
			    		if($(this).attr("id") == "checkCredFm2" ){
			    			
			    			if(!$(".reverso_doc").length ||  !$(".frente_doc").length ){
			    				$("#btns_credencial .fileinput-button").removeClass("lleno");
				    			$("#btns_credencial .fileinput-button").addClass("vacio");
				 				$("#checkCredFm2").removeClass("lleno");
				    			$("#checkCredFm2").addClass("vacio");
			    			}else {
			    				$("#btns_credencial .fileinput-button").removeClass("vacio");
				    			$("#btns_credencial .fileinput-button").addClass("lleno");
			    				$("#checkCredFm2").removeClass("vacio");
				    			$("#checkCredFm2").addClass("lleno");
				    	
			    			}	
			    		}
			    		
			    		if($(this).attr("id") == "checkImgLogo" ){
			    			if($(".imgLogoProyect img").attr("src") == "../resources/img/sinimagen.jpg") {
				    			$("#sectionLogos .fileinput-button").removeClass("lleno");
				    			$("#sectionLogos .fileinput-button").addClass("vacio");
				 				$("#checkImgLogo").removeClass("lleno");
				    			$("#checkImgLogo").addClass("vacio");
			    			}else {
			    		
				    			$("#sectionLogos .fileinput-button").removeClass("vacio");
				    			$("#sectionLogos .fileinput-button").addClass("lleno");
				    			$("#checkImgLogo").removeClass("vacio");
				    			$("#checkImgLogo").addClass("lleno");
			    			}
			    		}
			    		if($(this).attr("id") == "checkImgSelfie" ){
			    			if($("#sectionSelfie .subirSelfie").is(":visible")) {
				    			$("#sectionSelfie .subirSelfie .fileinput-button").removeClass("lleno");
				    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("vacio");
				 				$("#checkImgSelfie").removeClass("lleno");
				    			$("#checkImgSelfie").addClass("vacio");
			    			}else {
			    		
				    			$("#sectionSelfie .subirSelfie  .fileinput-button").removeClass("vacio");
				    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("lleno");
				    			$("#checkImgSelfie").removeClass("vacio");
				    			$("#checkImgSelfie").addClass("lleno");
			    			}
			    		}
			    		if($(this).attr("id") == "checkCompDomi" ){
			    			if(!$("#listacompDomi > div").length) {
				    			$("#type_compDomi").closest(".selectNuevo").removeClass("lleno");
				    			$("#type_compDomi").closest(".selectNuevo").addClass("vacio");
				    			$("#checkCompDomi").removeClass("lleno");
				    			$("#checkCompDomi").addClass("vacio");
			    			}else {
			    		
				    			$("#type_compDomi").closest(".selectNuevo").removeClass("vacio");
				    			$("#type_compDomi").closest(".selectNuevo").addClass("lleno");
				    			$("#checkCompDomi").removeClass("vacio");
				    			$("#checkCompDomi").addClass("lleno");
			    			}
			    		}
			    		if($(this).attr("id") == "checkTypeLoan" ){
			    			if(!$("#listloan > div").length || $("#rubroIngresos .btn_comprobante").length >= 1) {
				    			$("#type_loan").closest(".selectNuevo").removeClass("lleno");
				    			$("#type_loan").closest(".selectNuevo").addClass("vacio");
				    			$("#checkTypeLoan").removeClass("lleno");
				    			$("#checkTypeLoan").addClass("vacio");

				    		
			    			}else {
			    		
				    			$("#type_loan").closest(".selectNuevo").removeClass("vacio");
				    			$("#type_loan").closest(".selectNuevo").addClass("lleno");
				    			$("#checkTypeLoan").removeClass("vacio");
				    			$("#checkTypeLoan").addClass("lleno");

			    			}
			    		}
			    		
			    
			      } 	
			     
			     total_gastos(this_element);
			     var campos =  $(".validatorClass[index]:visible, table.validatorClass:visible,  #acSimple2:visible input,  #acSimple3:visible input, table.validatorClass2:visible, .conjuntoChecks:visible .btnN,  .selectNuevo:visible");
					
				    
				    var campos_vacios = campos.filter(function () {
					    return $(this).hasClass('vacio');   
					}).length == 0;
					
					if( $("#area").val() == "L") {
						if(campos_vacios == false) {
							if($(".paso_1_2").is(":hidden")) {
								$(".avisoCamposVacio").fadeIn();
							}
						}else {
							$(".avisoCamposVacio").fadeOut();
						
						}
							
					}
			 }); 

		  $("#txt_that_esdudied").blur(function(){
				    fieldCount();
		   }); 
		  
		    
		 		 
	 }
	function validacion_llenado(this_indice) {
		console.log("validacion llenado");
		 $(".validatorClass[index], table.validatorClass,  #acSimple2 input,  #acSimple3 input, table.validatorClass2, .perfilInversionista").each(function() {
			
			    var numeroDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".contenedor_docs div");	
				var selectDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find("select");	
				var areaPunteadaDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".ui-button");	
				
			    var indice_elemento = $(this).attr("index");
			   	   indice_elemento = Number(indice_elemento.replace(/[^0-9\.]+/g,""));
			   	   
			     
		   	      
	 			if( this_indice > indice_elemento  &&  $(this).val().length==0) {
	 		
	 		       
	 		    	 
	 		    	if($(this).is('table')){
	 		    		if($(this).find(":radio").is(":checked")){
			 		    	$(this).closest(".conjuntoChecks").find(".btnN").removeClass("vacio");
					    	$(this).closest(".conjuntoChecks").find(".btnN").addClass("lleno");
		 		    	}else {
		 		    
		 		    	 	$(this).closest(".conjuntoChecks").find(".btnN").removeClass("lleno");
					    	$(this).closest(".conjuntoChecks").find(".btnN").addClass("vacio");
		 		    	}
	 		    	}
	 		   
		 		   	if($(this).attr("id") == "inp_income_otherfamilyE") {
		 		   		if($("#inp_income_otherfamilyE").val() <= 0  || $("#inp_income_otherfamilyE").val() == ""){
		 		   			$("#inp_income_otherfamilyE").removeClass("lleno");
		 		   			$("#inp_income_otherfamilyE").addClass("vacio");
		 		   		}else {
		 		   			$("#inp_income_otherfamilyE").removeClass("vacio");
		 		   			$("#inp_income_otherfamilyE").addClass("lleno");
		 		   		}
		 		   	}
		 		   	
		 		   	
		 		  
	 		    	
	 			}
	 			
	 			if(this_indice > indice_elemento ) {
	 				
	 				 if( $(this).is('select') &&  $(this).val() == '0' || $(this).is('select') && $(this).val() == ''  || $(this).is('select') && $(this).val() == null  ) {
	 		    		 $(this).closest(".selectNuevo").removeClass("lleno");
		 		    	 $(this).closest(".selectNuevo").addClass("vacio");

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
			    	 if($(this).attr("id") == "day" ) {
		 		    		if($("#day option:selected").val() == "0") {
		 		    			$("#day").closest(".selectNuevo").removeClass("lleno");
		 		    			$("#day").closest(".selectNuevo").addClass("vacio");
			 				}else {
			 					$("#day").closest(".selectNuevo").removeClass("vacio");
			 					$("#day").closest(".selectNuevo").addClass("lleno");
			 				}
			 		  }
			    	 
			    	 if($(this).attr("id") == "month" ) {
		 		    		if($("#month option:selected").val() == "0") {
		 		    			$("#month").closest(".selectNuevo").removeClass("lleno");
		 		    			$("#month").closest(".selectNuevo").addClass("vacio");
			 				}else {
			 					$("#month").closest(".selectNuevo").removeClass("vacio");
			 					$("#month").closest(".selectNuevo").addClass("lleno");
			 				}
			 		  }
			    	 if($(this).attr("id") == "year" ) {
		 		    		if($("#year option:selected").val() == "0") {
		 		    			$("#year").closest(".selectNuevo").removeClass("lleno");
		 		    			$("#year").closest(".selectNuevo").addClass("vacio");
			 				}else {
			 					$("#year").closest(".selectNuevo").removeClass("vacio");
			 					$("#year").closest(".selectNuevo").addClass("lleno");
			 				}
			 		  }
			    	 
			    	  if($(this).hasClass("vecindario")) {
			    		  if($(".vecindario option:selected").val() == "0") {
		 		    			$(".vecindario").closest(".selectNuevo").removeClass("lleno");
		 		    			$(".vecindario").closest(".selectNuevo").addClass("vacio");
			 				}else {
			 					$(".vecindario").closest(".selectNuevo").removeClass("vacio");
			 					$(".vecindario").closest(".selectNuevo").addClass("lleno");
			 				}
			    	  }
	 				if($(this).is('table')  &&  $(this).hasClass("perfilInversionista")){
	 				
	 					if(!$(this).find("input:checked").length == 0) {
	 						$(this).removeClass("vacio");
	 						$(this).addClass("lleno");
	 						
	 					}else {
	 						$(this).removeClass("lleno");
	 						$(this).addClass("vacio");
	 					}
	 					
	 					
	 					
	 					
	 				}
	 				if($(this).is('input:[type="text"]') || $(this).is('textarea')) {
		 				if($(this).val().length==0) {
		 					$(this).removeClass("lleno");
		 					$(this).addClass("vacio");
		 				}else {
		 					$(this).removeClass("vacio");
		 		    		$(this).addClass("lleno");
		 				}
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
	 				if($(this).hasClass("validacionLadaField")){
	 					if($(this).val().length == 0 ){
	 							$(this).removeClass("lleno");
	 							$(this).addClass("vacio");
	 						}else{
	 							$(this).removeClass("vacio");
	 							$(this).addClass("lleno");
	 						}
	 			    }
	 				if($(this).hasClass("porcentaje-bene")){
						if($(this).val() == 0){
							$(this).removeClass("lleno");
	 						$(this).addClass("vacio");
						}else{
	 						$(this).removeClass("vacio");
	 						$(this).addClass("lleno");
	 					}
					}
		 			 if($(this).is('img')){
					    	
				    		if($(this).attr("id") == "checkCredFm2" ){
				    			if($("#rubroTipoIdentificacion .btn_comprobante").length >= 1) {
				    				$("#btns_credencial .fileinput-button").removeClass("lleno");
					    			$("#btns_credencial .fileinput-button").addClass("vacio");
					 				$("#checkCredFm2").removeClass("lleno");
					    			$("#checkCredFm2").addClass("vacio");
					    			$("#rubroTipoIdentificacion .btn_comprobante label").removeClass("lleno");
					    			$("#rubroTipoIdentificacion .btn_comprobante label").addClass("vacio");
					    			$("#btns_credencial .selectNuevo").removeClass("lleno");
					    			$("#btns_credencial .selectNuevo").addClass("vacio");
				    			}else {
				    				$("#btns_credencial .fileinput-button").removeClass("vacio");
					    			$("#btns_credencial .fileinput-button").addClass("lleno");
				    				$("#checkCredFm2").removeClass("vacio");
					    			$("#checkCredFm2").addClass("lleno");
					    			$("#rubroTipoIdentificacion .btn_comprobante label").addClass("lleno");
					    			$("#rubroTipoIdentificacion .btn_comprobante label").removeClass("vacio");
					    			$("#btns_credencial .selectNuevo").removeClass("vacio");
					    			$("#btns_credencial .selectNuevo").addClass("lleno");
				    			}	
				    		}
				    		
				    		if($(this).attr("id") == "checkImgLogo" ){
				    			if($(".imgLogoProyect img").attr("src") == "../resources/img/sinimagen.jpg") {
					    			$("#sectionLogos .fileinput-button").removeClass("lleno");
					    			$("#sectionLogos .fileinput-button").addClass("vacio");
					 				$("#checkImgLogo").removeClass("lleno");
					    			$("#checkImgLogo").addClass("vacio");
				    			}else {
				    		
					    			$("#sectionLogos .fileinput-button").removeClass("vacio");
					    			$("#sectionLogos .fileinput-button").addClass("lleno");
					    			$("#checkImgLogo").removeClass("vacio");
					    			$("#checkImgLogo").addClass("lleno");
				    			}
				    		}
				    		if($(this).attr("id") == "checkImgSelfie" ){
				    			if($("#sectionSelfie .subirSelfie").is(":visible")) {
					    			$("#sectionSelfie .subirSelfie .fileinput-button").removeClass("lleno");
					    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("vacio");
					 				$("#checkImgSelfie").removeClass("lleno");
					    			$("#checkImgSelfie").addClass("vacio");
				    			}else {
				    		
					    			$("#sectionSelfie .subirSelfie  .fileinput-button").removeClass("vacio");
					    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("lleno");
					    			$("#checkImgSelfie").removeClass("vacio");
					    			$("#checkImgSelfie").addClass("lleno");
				    			}
				    		}
				    		if($(this).attr("id") == "checkCompDomi" ){
				    			if(!$("#listacompDomi > div").length) {
					    			$("#type_compDomi").closest(".selectNuevo").removeClass("lleno");
					    			$("#type_compDomi").closest(".selectNuevo").addClass("vacio");
					    			$("#checkCompDomi").removeClass("lleno");
					    			$("#checkCompDomi").addClass("vacio");
				    			}else {
				    		
					    			$("#type_compDomi").closest(".selectNuevo").removeClass("vacio");
					    			$("#type_compDomi").closest(".selectNuevo").addClass("lleno");
					    			$("#checkCompDomi").removeClass("vacio");
					    			$("#checkCompDomi").addClass("lleno");
				    			}
				    		}
				    		if($(this).attr("id") == "checkTypeLoan" ){
				    			if(!$("#listloan > div").length || $("#rubroIngresos .btn_comprobante").length >= 1) {
					    			$("#type_loan").closest(".selectNuevo").removeClass("lleno");
					    			$("#type_loan").closest(".selectNuevo").addClass("vacio");
					    			$("#checkTypeLoan").removeClass("lleno");
					    			$("#checkTypeLoan").addClass("vacio");
					    			$("#rubroIngresos .btn_comprobante label").removeClass("lleno");
					    			$("#rubroIngresos .btn_comprobante label").addClass("vacio");
				    			}else {
				    		
					    			$("#type_loan").closest(".selectNuevo").removeClass("vacio");
					    			$("#type_loan").closest(".selectNuevo").addClass("lleno");
					    			$("#checkTypeLoan").removeClass("vacio");
					    			$("#checkTypeLoan").addClass("lleno");
					    			$("#rubroIngresos .btn_comprobante label").removeClass("vacio");
					    			$("#rubroIngresos .btn_comprobante label").addClass("lleno");
				    			}
				    		}
				    		
				    
				      } 	
	 			}
	 			else if(this_indice > indice_elemento  &&  $(this).val().length!=0) {
	 				if(!$(this).is('select')) {
	 					 $(this).removeClass("vacio");
		 		    	 $(this).addClass("lleno");
	 				}
	 			
	 			}	
		 });
	
	}
	
	function total_gastos(this_element) {
		 if (this_element.hasClass('calculeExpense')  &&  $(".gastos_total").is(":visible")) {
			 total_expense();
		 }
	}
	
	function total_expense() {
				var sumar=0;
				var totalExpense="";
				$('.calculeExpense').each(function(){
					sumar += Number($(this).val().replace(",",""));
				});	
				
				totalExpense=Number($("#inp_total_expense").val(formatNum(parseFloat(sumar).toFixed(2))).val().replace(",",""));		
				
				if((totalExpense)<1){
			   		 $(".gastos_total input.validatorClass[index]").removeClass("lleno");
			    	 $(".gastos_total input.validatorClass[index]").addClass("vacio");
				}
				else{
			   		 $(".gastos_total input.validatorClass[index]").addClass("lleno");
			    	 $(".gastos_total input.validatorClass[index]").removeClass("vacio");
				}		
	}
	
	function radio_reset() {
	
		 show_hidden_field();
		 focus_indices();
		 blur_indices()
	}



var validacionLlenado2 = function () {
	
		var elemento = $('.validatorClass[index], table.validatorClass,  #acSimple2 input,  #acSimple3 input, table.validatorClass2, .perfilInversionista');
		var conteo_elementos = elemento.length;
		var i = 0;
	
$(".validatorClass[index], table.validatorClass,  #acSimple2 input,  #acSimple3 input, table.validatorClass2, .perfilInversionista").each(function() {
	
	var numeroDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".contenedor_docs div");	
		var selectDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find("select");	
		var areaPunteadaDocumentos  =  $(this).closest(".containerTitleUpload").next(".dvContent2").find(".ui-button");	

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
			if($(this).hasClass("porcentaje-bene")){
				if($(this).val() == 0){
					$(this).removeClass("lleno");
						$(this).addClass("vacio");
				}else{
						$(this).removeClass("vacio");
						$(this).addClass("lleno");
					}
			}
		
			
			
		}
		
		if($(this).attr("id") == "inp_income_otherfamilyE") {
	   		if($("#inp_income_otherfamilyE").val() <= 0  || $("#inp_income_otherfamilyE").val() == ""){
	   			$("#inp_income_otherfamilyE").removeClass("lleno");
	   			$("#inp_income_otherfamilyE").addClass("vacio");
	   		}else {
	   			$("#inp_income_otherfamilyE").removeClass("vacio");
	   			$("#inp_income_otherfamilyE").addClass("lleno");
	   		}
	   	}

   	 if( $(this).is('select')) {
   		 if( $(this).val() == '0' || $(this).val() == ''  || $(this).val() == null) {
	    		 $(this).closest(".selectNuevo").removeClass("lleno");
		    	 $(this).closest(".selectNuevo").addClass("vacio");
   		 }else {
   			      $(this).closest(".selectNuevo").removeClass("vacio");
		    	 $(this).closest(".selectNuevo").addClass("lleno");
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
	  if($(this).hasClass("vecindario")) {
		  if($(".vecindario option:selected").val() == "0") {
	    			$(".vecindario").closest(".selectNuevo").removeClass("lleno");
	    			$(".vecindario").closest(".selectNuevo").addClass("vacio");
				}else {
					$(".vecindario").closest(".selectNuevo").removeClass("vacio");
					$(".vecindario").closest(".selectNuevo").addClass("lleno");
				}
	  }
   	if($(this).is('table')){
  	     
   		 tableCheck("experienciaInvertido");
	    tableCheck("tablaHorizonte");
	     tableCheck("objetivoInversion");
	     tableCheck("garantizarRendimiento");
	     tableCheck("condicionPerfil");
	     tableCheck("productoSinRiesgo");
	     tableCheck("productoSinRiesgoCheck");
	
   	  
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
   		
        //tableCheck("forma-contacto");
   	}
   	
   	 if($(this).is('img')){
		    	
   		if($(this).attr("id") == "checkCredFm2" ){
			if($("#rubroTipoIdentificacion .btn_comprobante").length >= 1) {
				$("#btns_credencial .fileinput-button").removeClass("lleno");
    			$("#btns_credencial .fileinput-button").addClass("vacio");
 				$("#checkCredFm2").removeClass("lleno");
    			$("#checkCredFm2").addClass("vacio");
    			$("#rubroTipoIdentificacion .btn_comprobante label").removeClass("lleno");
    			$("#rubroTipoIdentificacion .btn_comprobante label").addClass("vacio");
    			$("#btns_credencial .selectNuevo").removeClass("lleno");
    			$("#btns_credencial .selectNuevo").addClass("vacio");

			}else {
				$("#btns_credencial .fileinput-button").removeClass("vacio");
    			$("#btns_credencial .fileinput-button").addClass("lleno");
				$("#checkCredFm2").removeClass("vacio");
    			$("#checkCredFm2").addClass("lleno");
    			$("#rubroTipoIdentificacion .btn_comprobante label").addClass("lleno");
    			$("#rubroTipoIdentificacion .btn_comprobante label").removeClass("vacio");
    			$("#btns_credencial .selectNuevo").addClass("lleno");
    			$("#btns_credencial .selectNuevo").removeClass("vacio");
    	
			}	
		}
	    		
	    		if($(this).attr("id") == "checkImgLogo" ){
	    			if($(".imgLogoProyect img").attr("src") == "../resources/img/sinimagen.jpg") {
		    			$("#sectionLogos .fileinput-button").removeClass("lleno");
		    			$("#sectionLogos .fileinput-button").addClass("vacio");
		 				$("#checkImgLogo").removeClass("lleno");
		    			$("#checkImgLogo").addClass("vacio");
	    			}else {
	    		
		    			$("#sectionLogos .fileinput-button").removeClass("vacio");
		    			$("#sectionLogos .fileinput-button").addClass("lleno");
		    			$("#checkImgLogo").removeClass("vacio");
		    			$("#checkImgLogo").addClass("lleno");
	    			}
	    		}
	    		if($(this).attr("id") == "checkImgSelfie" ){
	    			if($("#sectionSelfie .subirSelfie").is(":visible")) {
		    			$("#sectionSelfie .subirSelfie .fileinput-button").removeClass("lleno");
		    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("vacio");
		 				$("#checkImgSelfie").removeClass("lleno");
		    			$("#checkImgSelfie").addClass("vacio");
	    			}else {
	    		
		    			$("#sectionSelfie .subirSelfie  .fileinput-button").removeClass("vacio");
		    			$("#sectionSelfie .subirSelfie  .fileinput-button").addClass("lleno");
		    			$("#checkImgSelfie").removeClass("vacio");
		    			$("#checkImgSelfie").addClass("lleno");
	    			}
	    		}
	    		if($(this).attr("id") == "checkCompDomi" ){
	    			if(!$("#listacompDomi > div").length) {
		    			$("#type_compDomi").closest(".selectNuevo").removeClass("lleno");
		    			$("#type_compDomi").closest(".selectNuevo").addClass("vacio");
		    			$("#checkCompDomi").removeClass("lleno");
		    			$("#checkCompDomi").addClass("vacio");
	    			}else {
	    		
		    			$("#type_compDomi").closest(".selectNuevo").removeClass("vacio");
		    			$("#type_compDomi").closest(".selectNuevo").addClass("lleno");
		    			$("#checkCompDomi").removeClass("vacio");
		    			$("#checkCompDomi").addClass("lleno");
	    			}
	    		}
	    		if($(this).attr("id") == "checkTypeLoan" ){
	    			if(!$("#listloan > div").length || $("#rubroIngresos .btn_comprobante").length >= 1) {
		    			$("#type_loan").closest(".selectNuevo").removeClass("lleno");
		    			$("#type_loan").closest(".selectNuevo").addClass("vacio");
		    			$("#checkTypeLoan").removeClass("lleno");
		    			$("#checkTypeLoan").addClass("vacio");
		    			$("#rubroIngresos .btn_comprobante label").removeClass("lleno");
		    			$("#rubroIngresos .btn_comprobante label").addClass("vacio");
	    			}else {
	    		
		    			$("#type_loan").closest(".selectNuevo").removeClass("vacio");
		    			$("#type_loan").closest(".selectNuevo").addClass("lleno");
		    			$("#checkTypeLoan").removeClass("vacio");
		    			$("#checkTypeLoan").addClass("lleno");
		    			$("#rubroIngresos .btn_comprobante label").removeClass("vacio");
		    			$("#rubroIngresos .btn_comprobante label").addClass("lleno");
	    			}
	    		}
	    		
	    
	      } 	
   	 /*
		if($(this).attr("id") == "forma-contacto" ){
			if( !$("#forma-contacto input:checked").length == 0) {
				$("#forma-contacto").removeClass("vacio");
				$("#forma-contacto").addClass("lleno");
			}else {
				$("#forma-contacto").removeClass("lleno");
				$("#forma-contacto").addClass("vacio");
			}
		}
		*/

	 	//console.log(i);
   	
	 	/*
	 	if(i == conteo_elementos) {
   		setTimeout(function(){
   			$('html, body').animate({
 		       scrollTop: ($('.vacio:eq(0)').offset().top - 100)
   			},1400);
   		},700);
   		
	
	   }
	   */
});	
}

function tableCheck (id) {
	//console.log(id);

				if( !$("#"+id+" input:checked").length == 0) {
					$("#"+id).closest(".conjuntoChecks").find(".btnN").removeClass("vacio");
					$("#"+id).closest(".conjuntoChecks").find(".btnN").addClass("lleno");
				}else {
					$("#"+id).closest(".conjuntoChecks").find(".btnN").removeClass("lleno");
					$("#"+id).closest(".conjuntoChecks").find(".btnN").addClass("vacio");
				}
			
}

function validationBtn (btn) {
	 buttonClick = true;
	 revision_pasos(btn);
	 console.log(btn);
}

var buttonClick; 
	var revision_pasos = function (element) {
		    validacionLlenado2();
			var campos =  $("#general .validatorClass[index]:visible, #general  table.validatorClass:visible, #general  #acSimple2:visible input, #general  #acSimple3:visible input, #general  table.validatorClass2:visible, .fileinput-button:visible, #type_compDomi, #type_loan, .selectNuevo:visible, .conjuntoChecks:visible .btnN, .validacionLadaField:visible");
		
		  	var campos_vacios = campos.filter(function () {
			    return $(this).hasClass('vacio');   
			}).length == 0;
			
			if(campos_vacios == false) {
				/*if( $("#area").val() == "L" ||  $("#primerPasoInv").is(":visible")) {*/
					$('html, body').animate({scrollTop:0}, '3000');
						$(".avisoCamposVacio").fadeIn();
						return false;
				/*}*/
				
			}else {
				
				console.log(element);
					if ( buttonClick == true) {
					
						if($("#frm_info_basic_loan").is(":visible")) {
							$("#hdNext\\:siguienteDocPLD").click();
						}
						if($(".IncomeExpense").is(":visible")) {
					
							$("#hdNext\\:siguientePrep").click();
						}
						if($("#frmMoreInfo").is(":visible")) {
							hideIFEjs();
							$("#hdNext\\:siguienteCierre").click();
							$.scrollTo('#header', 800, { axis:'y' });
						}
						
						if($("#buttons > #menu6").hasClass("menuItemSel") && $("#area").val() == "L"){
							//$("#hdNext\\:siguienteCierre").click();
							$("#hdNext\\:siguienteMasInfo").click();
							console.log("entro a esta que no tiene que ver");
		
						}
						if(element == "datosPersonales" ) {
							$("#hdNext\\:nextInvestor").click();
							
						}
						
						if(element == "PerfilInv"  ){
							revisionCheck();
						}
						if(element == "Beneficiarios"){
							$("#nextToMoreInfo").click();
						}
						if(element == "nextPLD"){
							$("#nextToPLD").click();
							
						}
						if(element == "Adicionales"){
							$("#nextToDocument").click();
	
							
						}
						if(element == "Documentacion"){
							$("#nextToClabe").click();
							
						}
						
						
						buttonClick = false;
					}
					else {
						if($("#investmentProfile").is(":visible")){
							
							revisionCheck();
							return false;
						}else{
							verficar_menu_item_SELECT(element);
						}
						console.log("menu lateral");
						
					}	
			}
			sendInteractoa ();
			
			mandodatos_hs();
	}
	
	
	function sendInteractoa () {
		console.log($("#enabledAnalytics").val());
		/*if(navigator.userAgent.indexOf("Chrome") != -1 || (navigator.userAgent.indexOf("MSIE") != -1 ) || (!!document.documentMode == true )){*/
			
				if($("#enabledAnalytics").val() == 'S' && $("#area").val() == "L" ) {
					interactions.__sendInteractions__();
				}
			
			
		/*}*/
		
	}