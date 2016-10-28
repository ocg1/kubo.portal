console.log("js/registro/fileUpload.js");
$(window).load(function(){
	fileUploadPreview (); 
	drop_upload ();
	
});

/* funcion  ocultar botones credencial, reset al desplegar el paso, y al cargar los documentos  */

function ocultar_botones_credencial () {
	$("#btns_credencial .fileupload-buttonbar").find(".ui-button-text").html("+ Subir");
	console.log("revision en credencial");
	
	if($("#listCredFm2 div").text().indexOf("Frente") != (-1)) {
		if(!$("#tipoIdentificacion").length){
			$(".cred_image:eq(0)").addClass("opacity");
		}
		
	}
	
	if($("#listCredFm2 div").text().indexOf("Reverso") != (-1)) {
		if(!$("#tipoIdentificacion").length){
			$(".cred_image:eq(1)").addClass("opacity");
		}
	}
	
	if($("#listCredFm2 div").text().indexOf("Pasaporte") != (-1)) {
		if(!$("#tipoIdentificacion").length){
			$(".cred_image:eq(2)").addClass("opacity");
		}
	}
}

var estadosCuenta  = ["11", "126", "127"];
var reciboNomina   = ["10", "57", "58"];
var porHonorarios  = ["128", "129", "130"];
var declaracionImpuestos  = ["12"];

var facturas       = ["9", "48", "49"];
var contratoArrendamiento  = ["13"];
var declaracionImpuestos2  = ["14"];

var credencialElectorIFE = ["1", "42"];
var credencialElectorINE = ["1", "42"];
var pasaporte = ["76"];
var cartilla = ["131"];
var licenciaConducir = ["132", "134"];
var cedulaProfesional = ["133"];


var borradoMultiple = false;
var cambiandoCategoria = false;
var actividadPrevia = false;
var seleccion_actual;
var tipoDocumento;
var tipoDocumento2;


/*intercalar documentos de la credencial conforme se cargan los docs */
/*
var acomodoBtnCredencial = function () {

	var pasaporte_text =  $(".frente_vuelta:contains('Sube una foto de tu Pasaporte ')")
	    frente_text =     $(".frente_vuelta:contains('Sube una foto del frente de tu credencial de elector')"),
		reverso_text =    $(".frente_vuelta:contains('Sube una foto del reverso de tu credencial de elector')");
	
	var pasaporte_btn = pasaporte_text.closest("form"),
		frente_btn =    frente_text.closest("form"),
		reverso_btn = 	reverso_text.closest("form");
	
	

	var pasaporte_doc = $("#listCredFm2 div:contains('Pasaporte')");
		frente_doc = 	$("#listCredFm2 div:contains('Frente')");	 
		reverso_doc = 	$("#listCredFm2 div:contains('Reverso')");

		pasaporte_doc.addClass("pasaporte_doc");
		frente_doc.addClass("frente_doc");
		reverso_doc.addClass("reverso_doc");
		
		acomodoBtnCredencial_docs("frente", frente_btn, frente_doc); 
		acomodoBtnCredencial_docs("reverso", reverso_btn, reverso_doc); 
		acomodoBtnCredencial_docs("pasaporte", pasaporte_btn, pasaporte_doc ); 
	
}
*/
/*
var acomodoBtnCredencial_docs = function (categoria, btn, doc) {
	
	if($('#btns_credencial .'+ categoria +'_doc').length > 0) {
		$('#listCredFm2 .' + categoria +'_doc img').each(function() {
			if($(this).attr("src") != $('#btns_credencial .'+categoria+'_doc img').attr("src")) {
				$(this).parent().parent().insertAfter($(btn));
				$(this).parent().parent().siblings('.'+categoria+'_doc').remove();
			}else {
				$(this).parent().parent().remove();
			}
		});
	}else {
		$(doc).insertAfter($(btn));
	}
}
*/
/*funcion principal para cargar documentos mediante el click */
function fileUploadPreview () {
	$(".custumStlFileUploadPreview").on('change', function() {
		upload_automatic ();
	    $('.combo_tipo_doc').removeClass("seleccionar_tipo");
	});	
} 
/*funcion principal para cargar documentos mediante el drop */
var drop_upload = function () {
$('.custumStlFileUploadPreview').on(
	    'drop',
	    function(e){
	        if(e.originalEvent.dataTransfer){
	        		  upload_automatic ();
	
	        }
	    }
	)
}
/*funcion para quitar la imagen que aparece por default, cuando todavia no hay imagen cargada */
function sinimagen () {
	setTimeout(function(){
	
		if($(".imgLogoProyect img").attr("src") == "../resources/img/sinimagen.jpg") {
			$(".imgLogoProyect img").attr("src", "../resources/img/sinimagen.jpg").hide();
		}else {
		}
  	},300);
}
/* funcion para cambiar una imagen */
function ejecutaAccion(elemt, type, proyect ){
	actividadPrevia = true;
	borradoMultiple = false;
	cambiandoCategoria = false;
	
	if(!$("#tipoIdentificacion").length){
		 if( $(elemt).closest("div").text().indexOf("Frente") != (-1)) {
				$(".cred_image:eq(0) input.custumStlFileUploadPreview").trigger("click");
				//$(".cred_image:eq(0)").addClass("opacity"); 
			}
				
		    if( $(elemt).closest("div").text().indexOf("Reverso") != (-1)) {
				$(".cred_image:eq(1) input.custumStlFileUploadPreview").trigger("click");
				//$(".cred_image:eq(1)").addClass("opacity");
			} 
		    if( $(elemt).closest("div").text().indexOf("Pasaporte") != (-1)) {
				 console.log($(elemt).closest("div").text());
				$(".cred_image:eq(2) input.custumStlFileUploadPreview").trigger("click");
				//$(".cred_image:eq(2)").addClass("opacity");
		    } 
	}
	
    
    if($(elemt).closest("form").find("select").attr("id") != "type_loan"){
    	$(elemt).closest("form").find("select").val(type);
    	$(elemt).closest("form").find("select").change();
    	$(elemt).closest("form").find("select").blur();
    	$(elemt).closest("form").find(".custumStlFileUploadPreview input").trigger("click");
    }
    if($(elemt).closest("form").find("select").attr("id") == "type_loan" ){
    	cambiarArchivo(type, "#file_compIncome", "#rubroIngresos");	
    }
   
    if($(elemt).closest("#listCredFm2").length ){
    	console.log("ya entro a tipoIdentificacion");
    	 cambiarArchivo(type, "#file_credIdentificacion", "#rubroTipoIdentificacion");
    	//$(element).closest("frm_loan").find(".btn_comprobante[tipodoc='"+type+"']");
    }

}

/* funcion para eliminar una imagen onstart */
function eliminarElemento(elemt, compania, file, filetype, prospectus, proyect, location){
	
	$(".velo").fadeIn();
	
	$(".borrarDoc").addClass("show");
	  $('#cancelaBorrar').click(function(){
			$(".velo").fadeOut();
			$(".borrarDoc").removeClass("show");
	  });
	  $('#aceptaBorrar').removeAttr("onclick");
	  var functionString = "borrarDocBtn('" +compania+"' ,'"+ file+"' ,'"+ filetype+"' ,'"+ prospectus+"' ,'"+ proyect+"' ,'"+ location+"');";
	  console.log("functionString"+functionString);
	  $('#aceptaBorrar').attr("onclick", functionString);
}			


function borrarDocBtn(compania, file, filetype, prospectus, proyect, location){
		borradoMultiple = false;
		cambiandoCategoria = false;
		actividadPrevia = true;
		$(".velo").fadeOut();
		$(".borrarDoc").removeClass("show");
    	  
		displayMessageProcessing('msgprocessing',false);
		 $("#datos_eliminar").val("");
		 $("#datos_eliminar").val(compania+"::"+file+"::"+filetype+"::"+prospectus+"::"+proyect+"::"+location);
		 $("#datos_eliminar").trigger("click");
		 console.log("se esta eliminando");	
}

/* funcion para eliminar una imagen oncomplete */ 
function borradoElemento () {
	/*
	init_listener_file_upload();
	$(".combo_tipo_doc").val("");
	fileUploadPreview ();
	
	
		closeFancy();
	
	*/
}

/* funcion que da el click automatico en el boton de subir, con condiciones */ 
function upload_automatic () {
	if($("#fancybox-wrap").is(":hidden")) {
		displayMessageProcessing('msgprocessing',false);
	}
	 var testimoniosInterval = setInterval(function(){
		  if($(".template-upload").length || $(".template-upload").is(":visible")) {
			  if(!$(".template-upload").hasClass("ui-state-error")) {
				  $(".start button").click();
				  $('.combo_tipo_doc').prop('selectedIndex',0);
		          $('.combo_tipo_doc').change();
		          $('.combo_tipo_doc').removeClass("seleccionar_tipo");
		          ocultar_botones_credencial(); 
		    	  clearInterval(testimoniosInterval);
			  }else {
				 /* if(borradoMultiple == false){*/
						closeFancy();
					/*}*/
				
				  alertify.alert("Este archivo tiene un formato no vÃ¡lido. Recuerda que solo puedes subir archivos en formato JPG, PNG, o PDF.");
				  clearInterval(testimoniosInterval);
			  }
		    		 
		  }else {
		  	
		  }
	}, 1000); 
}

/* funcion que se ejecutan al llegar a este paso y cada vez que se termine de actualizar la lista de documentos cargados */ 
function actualizacionListadoDocumentos (contenedorDocumentos) {
	console.log("actualizacionListadoDocumentos");
	var resizeId;
		clearTimeout(resizeId);
		/*funcion a quitar
			resizeId = setTimeout(acomodoBtnCredencial, 500);
		*/
		asterisk(".labelsStl");
		asterisk(".numberAndTitle");
		asterisk(".titleDisabled");
	sinimagen ();
	fileUploadPreview ();
	ocultar_botones_credencial ();
	//funcion a quitar 
	drop_upload ();
    $('.combo_tipo_doc').removeClass("seleccionar_tipo");
  	
	if($("#fancybox-wrap").is(":visible")) {
		/*if(borradoMultiple == false){*/
			closeFancy();
		/*}*/
	
	}
	
		if($(contenedorDocumentos+ " div").length) {
			if($(contenedorDocumentos).closest("form").find(".avisoDocs").is(":visible")){
				$(contenedorDocumentos).closest("form").find(".avisoDocs").hide();
			}
			
			
			if(contenedorDocumentos == "#listloan") {
				if($("#listloan div").length == 3){
					if(!$("#listloan").closest("form").find(".selectsDocumentos").hasClass("ocultaS")){
						$("#listloan").closest("form").find(".selectsDocumentos").addClass("ocultaS");
					}
				
				
				}
				
			}
			
			else{
					if(!$(contenedorDocumentos).closest("form").find(".selectsDocumentos").hasClass("ocultaS")){
						$(contenedorDocumentos).closest("form").find(".selectsDocumentos").addClass("ocultaS");
					}
					
					if($("#listloan div").length >= 1){
						$("#frm_loan .avisoDocs").hide();
					}
			}
			
		}else{
			$(contenedorDocumentos).closest("form").find(".avisoDocs").show();
		}
		listoPaso ();
		fieldCount();
  
	
}

function archivosCargados (archivo) {
	    var archivoInfo = archivo.find(".eliminar").attr("onclick"); 
	  		archivoInfo = archivoInfo.substring(archivoInfo.indexOf(",") + 1);
	  		archivoInfo = archivoInfo.slice(0,-1)
			archivoInfo = archivoInfo.replace(/,/g , '');
	  		archivoInfo = archivoInfo.replace(/ /g,'');
	  		archivoInfo = archivoInfo.split(/[' ']+/);
	  		return archivoInfo;
}

function btn_comprobante(btnFake, fileBtn, btnMultiples) {
	 actividadPrevia = true;
	 cambiandoCategoria = false;
	 var btnReal = fileBtn+ " label";
	 var tipoDocu = $(btnFake).attr("tipodoc");
	 $(btnMultiples+ " input").val("0");
	 $(btnMultiples+ " input").blur();
	
	 
		 $(btnMultiples+ " input").val(tipoDocu);
		 $(btnMultiples+ " input").blur();
		 $(btnReal).trigger("click");
	
	
	
}
function cambiarArchivo(type, fileBtn, btnMultiples) {
	 actividadPrevia = true;
	 cambiandoCategoria = false;
	 var btnReal = fileBtn+ " label";
	
	 $(btnMultiples+ " input").val(type);
	 $(btnMultiples+ " input").blur();
	 $(btnReal).trigger("click");
}

function comprobantesMultiples (select, btnMultiples, contenedorDocumentos, btnFileUploadReal){
	var btnsFake =  btnMultiples;

	var opcionSeleccionada = $(select+" option:selected").val();
	seleccion_actual = opcionSeleccionada;

	var numeroDocs = $(contenedorDocumentos+ " div");
		if(seleccion_actual != tipoDocumento){
			 cambiandoCategoria = true;
			 borradoMultiple = false;
		}
	if(numeroDocs.length == 0 ){
		cambiandoCategoria = false;
	}
	
	if($(select).attr("id") ==  "type_loan" &&  cambiandoCategoria == false ){
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, reciboNomina, 10, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, estadosCuenta, 11, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, porHonorarios, 128, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, declaracionImpuestos, 12, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, facturas, 9, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, contratoArrendamiento, 13, btnFileUploadReal);
			 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, declaracionImpuestos2, 14, btnFileUploadReal);
	}
	
	console.log("seleccion_actual != tipoDocumento");
	console.log(seleccion_actual != tipoDocumento);
	
	console.log("seleccion_actual != tipoDocumento2");
	console.log(seleccion_actual != tipoDocumento2);
	
	console.log("numero de documentos mayor a 1");
	console.log(numeroDocs.length >= 1)
	
	console.log("cambiandoCategoria");
	console.log(cambiandoCategoria)
	
	console.log("borradoMultiple");
	console.log(borradoMultiple);
	
	console.log("actividadPrevia");
	console.log(actividadPrevia);
	
	if(numeroDocs.length >= 1  && cambiandoCategoria == true && borradoMultiple == false && actividadPrevia == true  ){
			cambiarTipoDocumento(contenedorDocumentos, select);	
	}

}



function comprobantesMultiples2 (select, btnMultiples, contenedorDocumentos, btnFileUploadReal){
	
	
	
	/*
	var btnsFake =  btnMultiples;

	var opcionSeleccionada = $(select+" option:selected").val();
	seleccion_actual = opcionSeleccionada;

	var numeroDocs = $(contenedorDocumentos+ " div");

		if(seleccion_actual != tipoDocumento2){
			 cambiandoCategoria = true;
			 borradoMultiple = false;
		}
	if(numeroDocs.length == 0 ){
		cambiandoCategoria = false;
	}
	
	if($(select).attr("id") ==  "tipoIdentificacion" &&  cambiandoCategoria == false ){
	//	dataIdentificaciones (select, numeroDocs,  opcionSeleccionada, btnsFake, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, credencialElectorIFE, 1, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, credencialElectorINE, 2, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, pasaporte, 3, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, cartilla, 4, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, licenciaConducir, 5, btnFileUploadReal);
		 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, cedulaProfesional, 6, btnFileUploadReal);
		 console.log("esta entrando a tipo de ID");
	}

	
	console.log("seleccion_actual != tipoDocumento");
	console.log(seleccion_actual != tipoDocumento);
	
	console.log("seleccion_actual != tipoDocumento2");
	console.log(seleccion_actual != tipoDocumento2);
	
	console.log("numero de documentos mayor a 1");
	console.log(numeroDocs.length >= 1)
	
	console.log("cambiandoCategoria");
	console.log(cambiandoCategoria)
	
	console.log("borradoMultiple");
	console.log(borradoMultiple);
	
	console.log("actividadPrevia");
	console.log(actividadPrevia);
	
	if(numeroDocs.length >= 1  && cambiandoCategoria == true && borradoMultiple == false && actividadPrevia == true  ){
		
		//	cambiarTipoDocumento(contenedorDocumentos, select);
		
	}
	if(cambiandoCategoria == false){
		tipoIdentificacion();
	}
	*/	var btnsFake =  btnMultiples;

	var opcionSeleccionada = $(select+" option:selected").val();
	var numeroDocs = $(contenedorDocumentos+ " div");
	if(opcionSeleccionada != tipoDocumento2){
		 cambiandoCategoria = true;
		 borradoMultiple = false;
	}
	if(numeroDocs.length == 0 ){
		cambiandoCategoria = false;
	}

		tipoIdentificacion();
	
	


		
		
		if($(select).attr("id") ==  "tipoIdentificacion" &&  cambiandoCategoria == false ){
			//	dataIdentificaciones (select, numeroDocs,  opcionSeleccionada, btnsFake, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, credencialElectorIFE, 1, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, credencialElectorINE, 2, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, pasaporte, 3, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, cartilla, 4, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, licenciaConducir, 5, btnFileUploadReal);
				 dataTipoDoc (select, numeroDocs, opcionSeleccionada, btnsFake, cedulaProfesional, 6, btnFileUploadReal);
				 console.log("esta entrando a tipo de ID");
	    }
		
		if(numeroDocs.length >= 1 && opcionSeleccionada != tipoDocumento2  && cambiandoCategoria == true ){
			//cambiarTipoDocumento(contenedorDocumentos, select);
			$("#ine_ife input[value='"+tipoDocumento2+ "']").trigger("click");
			$("#ine_ife input[value='"+tipoDocumento2+ "']").change();
			valorSelect2(select, contenedorDocumentos);
			alertify.alert("Para cambiar de tipo de identificación, <strong>primero borra los documentos</strong> que has subido.")
			
		}
		
	
		/*
		if(numeroDocs.length >= 1  && cambiandoCategoria == true && borradoMultiple == false && actividadPrevia == true  ){
			
			//	cambiarTipoDocumento(contenedorDocumentos, select);
			
		}
		*/

}

function comprobantesMultiples3(select, tag) {
	var tipoComprobante =  $(select+" option:selected").text();
	$(tag).html(tipoComprobante);
}

function dataTipoDoc (select, numeroDocs,  opcionSeleccionada, btnsFake, arreglo, valorActual, btnFileUploadReal) {

	var textoCredencial = [" Sube una foto del frente de tu credencial de elector.", "Sube una foto de la parte de atrás de tu credencial de elector."]
	var textoLicencia	= ["Sube una foto del frente de tu Licencia de conducir.", "Sube una foto de la parte de atrás de tu Licencia de conducir."]
	
	if( opcionSeleccionada ==  valorActual){
			 $(btnsFake+ " .btn_comprobante").remove();
			 console.log(arreglo);
			 for (var i = 0; i < arreglo.length; i++){
				 if(arreglo[0] == "1" &&  arreglo[1] == "42" ){
					 console.log("entro al arreglo");
					 var btn = "<div class='btn_comprobante' tipodoc='"+arreglo[i]+"' onclick='btn_comprobante(this,  \""+btnFileUploadReal+"\", \""+btnsFake+"\" )' ><p>"+textoCredencial[i]+"</p><label><strong class='textBtn' >+ Subir</strong></label></div>";

					 
				 }
				 else if(arreglo[0] == "132" &&  arreglo[1] == "134" ){
					 console.log("entro al arreglo");
					 var btn = "<div class='btn_comprobante' tipodoc='"+arreglo[i]+"' onclick='btn_comprobante(this,  \""+btnFileUploadReal+"\", \""+btnsFake+"\" )' ><p>"+textoLicencia[i]+"</p><label><strong class='textBtn' >+ Subir</strong></label></div>";

					 
				 }
				 else{
					 var btn = "<div class='btn_comprobante' tipodoc='"+arreglo[i]+"' onclick='btn_comprobante(this,  \""+btnFileUploadReal+"\", \""+btnsFake+"\" )' ><p>"+(i+1)+".</p><label><strong class='textBtn' >+ Subir</strong></label></div>";

					 
				 }
			
				 $(btnsFake).append(btn);
				
				 if( i+1 == arreglo.length ){
					 $(numeroDocs).each(function(n) {
						 var archivoRevisar = $(this);
					   	 var archivoInfo =  archivosCargados (archivoRevisar);
						 var filetype =   archivoInfo[3];
							
							 if($.inArray(filetype, arreglo) >= 0) {
								 $(btnsFake+" .btn_comprobante[tipodoc='"+filetype+"']").remove();
							 }
							 console.log("esta creando a tipo de ID");
							 console.log("arreglo"+arreglo);
							 
					});
					 
					 if( $(btnsFake+" .btn_comprobante").length == 1 ){
						 if($(select).attr("id") ==  "tipoIdentificacion" && opcionSeleccionada != "1" && opcionSeleccionada != "2" && opcionSeleccionada != "5" ){
							 $(btnsFake+" .btn_comprobante p").remove();
						 }
					 }
					
				 }
				 
			 }
			 
			 if(btnsFake == "#rubroIngresos"){
				 if($("#rubroIngresos .btn_comprobante").length == 0){
					 $(".tituloTipo").hide();
				 }else{
					 $(".tituloTipo").show();
				 }
				 if($("#rubroIngresos .btn_comprobante").length > 1){
					 $(".tituloTipo sup").html("tus");
					 $(".tituloTipo u").html("más recientes.");
					 $(".tituloTipo span").show();
					 $(".tituloTipo span").html($("#rubroIngresos .btn_comprobante").length);
					
				 }else{
					 $(".tituloTipo sup").html("tu");
					 $(".tituloTipo u").html("más reciente.");
					 $(".tituloTipo span").hide();
				 }
				 if( $("#rubroIngresos .btn_comprobante").length == 1){
					 	$("#rubroIngresos .btn_comprobante p").hide()
				 }else{
					 $("#rubroIngresos.btn_comprobante p").show()
				 }
			
				 if(opcionSeleccionada == "11"){
					 if($("#rubroIngresos .btn_comprobante").length == 1){
							$(".tituloTipo strong").html("Estado de cuenta bancaria");
					 }else{
						 $(".tituloTipo strong").html("Estados de cuenta bancaria");
					 } 
				 }
				 if(opcionSeleccionada == "10"){
					 if($("#rubroIngresos .btn_comprobante").length == 1){
							$(".tituloTipo strong").html("Recibo de nómina");
					 }else{
						 $(".tituloTipo strong").html("Recibos de nómina");
					 } 
				 }
				 if(opcionSeleccionada == "128"){
					 if($("#rubroIngresos .btn_comprobante").length == 1){
							$(".tituloTipo strong").html("Recibo de honorarios");
					 }else{
						 $(".tituloTipo strong").html("Recibos de honorarios");
					 } 
				 }
				 if(opcionSeleccionada == "12"){
							$(".tituloTipo strong").html("Constancia de declaración de impuestos");
					
				 }
				 if(opcionSeleccionada == "9"){
					 if($("#rubroIngresos .btn_comprobante").length == 1){
						$(".tituloTipo strong").html("Factura o nota de compra");
					 }else{
						$(".tituloTipo strong").html("Facturas o notas de compra");
					 }
				 }
				 if(opcionSeleccionada == "13"){
						$(".tituloTipo strong").html("Contrato de arrendamiento");
				 }
				 if(opcionSeleccionada == "14"){
						$(".tituloTipo strong").html("Declaración de impuestos");
				 }
			 } 
				 
		}
}


function tipoIdentificacion() {
	var opcionSeleccionada = $("#tipoIdentificacion option:selected").val();
	$("#btns_credencial .avisoDocs").slideUp();
	if(opcionSeleccionada == ""){
		$(".textoIndicacion").slideUp();
	}
	if(opcionSeleccionada == 1 || opcionSeleccionada == 2 || opcionSeleccionada == 5){
		$(".otrasIdentificaciones").slideUp();
		$(".credencialEle").slideDown();
	}else{
		if(!opcionSeleccionada == ""){
			$(".credencialEle").slideUp();
			$(".otrasIdentificaciones").slideDown();
		}else{
			$(".credencialEle").slideUp();
			$(".otrasIdentificaciones").slideUp();
		}
	}

	if(opcionSeleccionada == 1){
		if(!$("#ine_ife input[value='1']").is(":checked")){
			$("#ine_ife input[value='1']").trigger("click");
			$("#ine_ife input[value='1']").change();
		}
	}
	if(opcionSeleccionada == 2){
		if(!$("#ine_ife input[value='2']").is(":checked")){
			$("#ine_ife input[value='2']").trigger("click");
			$("#ine_ife input[value='2']").change();
		}
	}
	if(opcionSeleccionada == 3){
		if(!$("#ine_ife input[value='3']").is(":checked")){
			$("#ine_ife input[value='3']").trigger("click");
			$("#ine_ife input[value='3']").change();
			$(".tipoDocID").html($("#tipoIdentificacion option:selected").text());
		}
	}
	if(opcionSeleccionada == 4){
		if(!$("#ine_ife input[value='4']").is(":checked")){
			$("#ine_ife input[value='4']").trigger("click");
			$("#ine_ife input[value='4']").change();
			$(".tipoDocID").html($("#tipoIdentificacion option:selected").text());
		}
	}
	if(opcionSeleccionada == 5){
		if(!$("#ine_ife input[value='5']").is(":checked")){
			$("#ine_ife input[value='5']").trigger("click");
			$("#ine_ife input[value='5']").change();
			$(".tipoDocID").html($("#tipoIdentificacion option:selected").text());
		}
	}
	if(opcionSeleccionada == 6){
		if(!$("#ine_ife input[value='6']").is(":checked")){
			$("#ine_ife input[value='6']").trigger("click");
			$("#ine_ife input[value='6']").change();
			$(".tipoDocID").html($("#tipoIdentificacion option:selected").text());
		}
	}
    
	$("#tipoIdentificacion").parent(".selectNuevo").removeClass("vacio");

}

function cambiarTipoDocumento(contenedorDocumentos, select) {
	var numeroDocs = $(contenedorDocumentos+ " div");

	alertify.confirm("Al cambiar de tipo de documento se borrarán los documentos cargados, ¿estás de acuerdo?" , function (e) {
		
		if (e) {
			console.log("se ha borrado todo");
			// $("#datos_eliminar").val(compania+"::"+file+"::"+filetype+"::"+prospectus+"::"+proyect+"::"+location);

			displayMessageProcessing('msgprocessing',false);
			borradoMultiple = true;
			console.log("seleccion_actual"+seleccion_actual);
			
		    $(numeroDocs).each(function(n) {
			
			    var archivoBorrar = $(this);
			  
			   	 var archivoInfo =  archivosCargados (archivoBorrar);
				 
			   	 var compania =   archivoInfo[1];
				 var file =       archivoInfo[2];
				 var filetype =   archivoInfo[3];
				 var prospectus = archivoInfo[4];
				 var proyect =    archivoInfo[5];
				 var location =   archivoInfo[6];
				 
				 $("#datos_eliminar").val("");
				 $("#datos_eliminar").val(compania+"::"+file+"::"+filetype+"::"+prospectus+"::"+proyect+"::"+location);
				 $("#datos_eliminar").trigger("click");
				 
				if(n+ 1 == numeroDocs.length){
					setTimeout(function(){
						closeFancy();
						
				  	},3200);
				
				}
				
		  });
			return true;
		} else { 
			cambiandoCategoria = false;
			if($(select).attr("id") ==  "type_loan"){
				valorSelect(select, contenedorDocumentos);
			}
			if($(select).attr("id") ==  "tipoIdentificacion"){
				$("#ine_ife input[value='"+tipoDocumento2+ "']").trigger("click");
				$("#ine_ife input[value='"+tipoDocumento2+ "']").change();
				valorSelect2(select, contenedorDocumentos);
		
			}
			
			return false;
		}
	}); 
}

function valorSelect( select, container) {
	 var archivoRevisar = $(container+ " div:eq(0)");
	 if(archivoRevisar.is(":visible") ){
		 
		 var archivoInfo =  archivosCargados (archivoRevisar);
		 
		 var filetype =   archivoInfo[3];
	
				 if($.inArray(filetype, estadosCuenta) >= 0) {
					 $(select).val("11");
					 tipoDocumento = "11";
				 }
				 
				 if($.inArray(filetype, facturas) >= 0) {
					 $(select).val("9");
					 tipoDocumento = "9";
				 }
				 if($.inArray(filetype, reciboNomina) >= 0) {
					 $(select).val("10");
					 tipoDocumento = "10";
				 }
				 if($.inArray(filetype, porHonorarios) >= 0) {
					 $(select).val("128");
					 tipoDocumento = "128";
				 }
				 if($.inArray(filetype, declaracionImpuestos) >= 0) {
					 $(select).val("12");
					 tipoDocumento = "12";	
				 }
				 if($.inArray(filetype, contratoArrendamiento) >= 0) {
					 $(select).val("13");
					 tipoDocumento = "13";
				 }
				 if($.inArray(filetype, declaracionImpuestos2) >= 0) {
					 $(select).val("14");
					 tipoDocumento = "14";
					
				 }
		 
	 }
	 

	 $(select).change();
	
     console.log("change");
	 actividadPrevia = true;
	 fieldCount();
	 console.log("seleccion_actual"+seleccion_actual);
}



function valorSelect2( select, container) {

	 $("#inputTipoIdentificacion").val("");
	 $("#inputTipoIdentificacion").focus();
	 $("#inputTipoIdentificacion").blur();

	/*
	var archivoRevisar = $(container+ " div:eq(0)");
	 if(archivoRevisar.is(":visible") ){
		 
		 var archivoInfo =  archivosCargados (archivoRevisar);
		 
		 var filetype =   archivoInfo[3];
			 if(filetype == "1" || filetype == "42"  ){
				 	if($("#ine_ife input[value='1']").is(":checked")){
				 		 $(select).val("1");
				 		 tipoDocumento2 = "1";
				 		 console.log("entra en ife");
				 	}
				 	if($("#ine_ife input[value='2']").is(":checked")){
				 		 $(select).val("2");
				 		 tipoDocumento2 = "2";
				 		 console.log("entra en ine");
				 	}
				 
			 }
			 if($.inArray(filetype, pasaporte) >= 0) {
				 $(select).val("3");
				 tipoDocumento2 = "3";
			 }
			 if($.inArray(filetype, cartilla) >= 0) {
				 $(select).val("4");
				 tipoDocumento2 = "4";
			 }console.log("seleccion_actual"+seleccion_actual);
			 if($.inArray(filetype, licenciaConducir) >= 0) {
				 $(select).val("5");
				 tipoDocumento2 = "5";
			 }
			 if($.inArray(filetype, cedulaProfesional) >= 0) {
				 $(select).val("6");
				 tipoDocumento2 = "6";
			 }
			 
		 }
	 $(select).change();
	
     console.log("change");
	 actividadPrevia = true;
	 fieldCount();
	 console.log("seleccion_actual"+seleccion_actual);
	 */
	
	
	var archivoRevisar = $(container+ " div:eq(0)");
	
	 
	    if($("#ine_ife input[value='1']").is(":checked")){
	 		 $(select).val("1");
	 		 tipoDocumento2 = "1";
	 	}
	 	if($("#ine_ife input[value='2']").is(":checked")){
	 		 $(select).val("2");
	 		 tipoDocumento2 = "2";
	 	}

	    if($("#ine_ife input[value='3']").is(":checked")){
	 		 $(select).val("3");
	 		 tipoDocumento2 = "3";
	 	}
	 	if($("#ine_ife input[value='4']").is(":checked")){
	 		 $(select).val("4");
	 		 tipoDocumento2 = "4";
	 	}
	 	if($("#ine_ife input[value='5']").is(":checked")){
	 		 $(select).val("5");
	 		 tipoDocumento2 = "5";
	 	}
	 	if($("#ine_ife input[value='6']").is(":checked")){
	 		 $(select).val("6");
	 		 tipoDocumento2 = "6";
	 	}
		 	
	

	 	    $(select).change();
	 	
	
	   
}


/*
function eliminarOpciones  (valueOptions, docUno, docDos, docTres, option1, option2, option3, text) {

	var doc1   = $("#listloan div small:contains('"+docUno+"')");
	var doc2   = $("#listloan div small:contains('"+docDos+"')");
	var doc3  =  $("#listloan div small:contains('"+docTres+"')");

	$("#type_loan option").each(function() {
		  if($.inArray( $(this).val(), valueOptions) >= 0) {
			  $(this).remove();
		  }
	});
	  if( doc1.length ){
		  if(!doc2.length ){
			  addOption ("#type_loan option[value='"+option2+"']", option2, text);
		  }else{
			  if(!doc3.length ){
				  addOption ("#type_loan option[value='"+option3+"']", option3, text);
			  }
		  }
	  }else{
		  addOption ("#type_loan option[value='"+option1+"']", option1, text);
	  }

}
function addOption (optionValue, valueNumber, text){
	  if(!$(optionValue).length) {
		    var x = document.getElementById("type_loan");
		    var option = document.createElement("option");
		    option.text = text;
		    option.value = ""+valueNumber+""
		    x.add(option);
		  }
}
*/
function init_listener_file_upload(){	
	$(".custumStlFileUploadPreview").on('change', 'drop', function() 
	{
		
		if($.mobile == null)
		{		
				var fileThis=this;
				var objectUpload=$(this).parents(".custumStlFileUploadPreview");
				var sectionFiles=objectUpload.find(".files");		
				sectionFiles.find(".template-upload").remove();		
				sectionFiles.hide();
				
				var nameFile   = $(this).val();
				var fileFormat = nameFile.substring(nameFile.lastIndexOf(".")+1).toLowerCase();
				
				if((fileFormat=="pdf" || fileFormat=="png" || fileFormat=="jpg" || fileFormat=="jpeg" || fileFormat=="gif") && window.FileReader)
				{
					
						
					upload_automatic (); // agrgegado gabriel
				
					if(fileThis.files[0].size<=5242880)
					{
						objectUpload.delay(100).show('fast', function() 
						{
							
							var preview= sectionFiles.find(".preview");
						
							preview.parent().addClass("validatorClass");
						
						if (fileThis.files && fileThis.files[0]) 
						{											
						        var reader = new FileReader();
						        
						        reader.onload = function (e) 
						        {
						        	
						        	 
						        	if(fileFormat!="pdf")
						        	{
						        		var imgObject = $("<img class='img_complet_view' id='show_"+fileThis.name+"'></img>");	
						        		
						        		var widthWindows=Number($(window).width())-30;
						        		var heightWindows=Number($(window).height())-30;				        		
						        		var divWidth=550;
						        		var divHeight=510;
						        		var flag=false;		
						        		
							        	$(imgObject).load(function () 
							        	{							        		
							        		if(this.width>widthWindows && this.height>heightWindows)
							        		{
							        			divWidth=widthWindows-100;
							        			divHeight=heightWindows-50;
							        			flag=true;
							        		}else if(this.width<widthWindows){
							        			divWidth=this.width;
							        			if(this.height<heightWindows)
							        				divHeight=this.height;
							        			else{
							        				divHeight=heightWindows-50;	
							        				flag=true;
							        			}		        									        			
							        		}					        		
							        		preview.html("<a class='showScreen' title='Ver imagen completa' href='#img_"+fileThis.name+"'>" +
								        			"<img  width='150' height='150'  src='"+e.target.result+"'></img></a>"+
								        			"<div style='display:none;' ><div class='parent-content' style='border:10px solid #444;width:"+divWidth+"px;height:"+divHeight+"px;' id='img_"+fileThis.name+"'></div></div>");					        		
							        		$("div.parent-content").append(imgObject);		
							        		sectionFiles.show();
							        		
							        			
								        
							        	     
							        	       
							        	
							        		if(flag)
							        		{
								        		$(this).imgscale({ 
								    			    parent : '.parent-content',
								    			    scale: 'fit'
								    			  });		
							        		}
							        		
							        		$(".showScreen").fancybox({		
							        			padding : 	0,
							        			margin 	:	0,
							        			transitionIn: 'none',
							        			transitionOut : 'none',	
							        		
							        			scrolling : 'none',
							        			centerOnScroll : false,
							        			titleShow:false,
							        			overlayColor: '#444'
		
							        		});
							        		$("div.parent-content").width("auto").height("auto");
							        		
							        	}).attr("src",e.target.result);
							        	
						        	}else{
						        		
						        	 if(fileThis.files[0].size<=1048576)
						        	 {
						        		preview.html("<a class='showScreen' title='Ver archivo completo' href='#frame_"+fileThis.name+"'>ver archivo</a>" +
							        			"<embed width='150' height='150' src='"+e.target.result+"'></embed>"	+
							        			"<div style='display:none;' ><div id='frame_"+fileThis.name+"' style='border:10px solid #444;' ><embed width='600' height='500'  src='"+e.target.result+"'></embed></div></div>"
							        			);
						        		}else{
						        			preview.html("<img width='150' height='150' src='../resources/img/icon-pdf.png' ></img>"
								        			//"<div style='display:none;' ><div id='frame_"+fileThis.name+"' style='border:10px solid #444;' ><iframe width='600' height='500'  src='"+e.target.result+"'></iframe></div></div>"
								        			);
						        		}
						        		sectionFiles.show();
						        		$(".showScreen").fancybox({		
						        			padding : 	0,
						        			margin 	:	0,
						        			transitionIn: 'none',
						        			transitionOut : 'none',	
						        			scrolling : 'none',
						        			centerOnScroll : false,
						        			titleShow:false,
						        			overlayColor: '#444'
						        		});
						        	}
						        					        	
						        };
						        reader.readAsDataURL(fileThis.files[0]);
						    }else{
						    	sectionFiles.show();
						    }
						});
						
					} else {
						
						sectionFiles.show();
					}
						
						
				}else{
					objectUpload.delay(100).show('fast', function() {
						sectionFiles.show();
						var tdName=sectionFiles.find(".name");
						var nameFile=tdName.text();
						
						if(nameFile.length>=10)
						{
							tdName.attr("title",nameFile);
							if(nameFile.length-13>5){
								tdName.text(nameFile.substring(0,10)+"...."+nameFile.substring(nameFile.lastIndexOf(".")-5)).removeClass("name");
							}else{
								tdName.removeClass("name");
							}					
						}else{
							tdName.removeClass("name");
						}				
					});
				}		
				return false;
				
		}
		 
	});
	
	
}

function oncompleterequest(component){
	if(component!=null){
	var element=$("#"+component);
	$('.template-upload.ui-state-error').remove();
	element.css("display","inline");							 
	var elementChild=element.parents(".containerTitleUpload");
	if(elementChild.children().eq(0).hasClass("corner")){
	
		elementChild.find(".corner").removeClass("corner").addClass("cornerDisabled");
		elementChild.find(".numberAndTitle").removeClass('numberAndTitle').addClass("numberAndTitleDisabled");
		elementChild.find(".number").removeClass("number").addClass("numberDisabled");
		elementChild.find(".title").removeClass("title").addClass("titleDisabled");
		console.log("esto se está haciendo");
		
		
	}	
	
	fieldCount();
	}
	

	console.log("element"+element);
}

function validateSelectDocument(component) {
	var element=$("#"+component);
	if(element.val()==""){
		   element.validationEngine('showPrompt', 'Seleccione un documento','error','centerRight', true);		   
		   return false;	   
	   }
	   else{
		   element.parent().children('.formError').remove(); 
		   return true;
	   }
	
}

function validateFileUploadCombo(component) {

	

	var element=$("#"+component);
	var parent=element.parent();
	
	var text="Presiona para subir tu "+element.find('option:selected').text();
	if(element.val()!=""){	
		
		   //parent.parent().children('.formError').remove();
		   //parent.next().show();
		   //parent.next().stop();
		
		$("#dv"+component).delay(400).show();
	
//		   parent.next().children().eq(0).validationEngine('showPrompt',text,'error','centerRight', true).prev().delay(5000).hide(800, function () {
//		        $(this).remove();
//		   });
		
		  // agregado gabriel
		   $('.combo_tipo_doc').removeClass("seleccionar_tipo"); 
		   element.closest('.selectsDocumentos').find('.avisoDocs').hide();
		   return true;	   
			
	   }
	   else{
		   parent.next().hide();
		// agregado gabriel
		   if( !element.closest('.selectsDocumentos').find('.avisoDocs').is(":visible") ){
			   element.closest('.selectsDocumentos').find('.avisoDocs').show();
		   }
		 
		    if ($("#"+component).closest("form").find(".contenedor_docs div").is(":visible")) {
			}else {
				$("#"+component).closest("form").find(".contenedor_docs article").hide();
			}
		   return false;
		   
		   
	   }
}



/*pendientes identificacion
 
crear funcion para poder cambiar el archivo en especifico
crear funcion para darle click en los radios de tipo de identificación/ife e ine
crear funciones cuando el caso sea ine o ife y que pasa si cambia de ahi


crear variante de las funciones creadas para nacional y extranjero, por ejemplo
por que lanza otra vez la funcion que informa que se va borrar los documentos
 
 flujo de inverionista
 
 */


