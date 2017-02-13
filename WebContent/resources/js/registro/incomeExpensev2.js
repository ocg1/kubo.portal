

$.fn.digits = function(){ 
    return this.each(function(){ 
        $(this).val( $(this).val().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") ); 
    })
}

$.fn.digitsText = function(){ 
    return this.each(function(){ 
        $(this).text( $(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") ); 
    })
}

var ingresosMensuales =  function  () {
		otro_aporte ();	
		opExpenses_a_gastosNegocio (); 
		dineroDisponibleNegocio();
		mismaDireccionFamiliar ();
		especificacionesPrevRealizadas ();

		$(".formatincomexpenses, #gastoTotal, #section-selling-details input").blur(function(){	
			if($(this).val() == "" || parseFloat( $(this).val() ) == parseFloat("0.00") ){
					$(this).val("0.00");
			}
		});
		
		$(".formatincomexpenses, #gastoTotal, #section-selling-details input").focus(function(){	
			if( parseFloat( $(this).val() ) == parseFloat("0.00") ){
					$(this).val("");
			}
		});
		
}
function blurFormatincomexpenses () {
$(".formatincomexpenses").blur(function(){	

	if($("#inp_total_expense").is(":visible")) {
		$("#gastoTotal").val($("#inp_total_expense").val());
		$("#gastoTotal").blur();
	}
	
	dineroDisponibleNegocio();
		if($(this).val() == "" ){
			$(this).val("0.00");
		}
}); 

$("#inp_income_otherfamilyE").blur(function(){	
	dineroDisponibleNegocio();
		
}); 

}

function opExpenses_a_gastosNegocio () {
	if($("#inp_value_opExpenses").is(":visible")) {
		$("#gastosNegocio").val($("#inp_value_opExpenses").val());
		$("#gastosNegocio").blur();
	}
}

function oncompleteOperativeCost ( element , idStr ){
	
	$("#operating_cost_"+idStr).val( ($("#operating_cost_"+idStr).next("input").val() ));
	
	opExpenses_a_gastosNegocio();

}

function oncompleteSelling(idStr){
	
	$("#selling_"+idStr).val( ($("#selling_"+idStr).next("input").val() ));
	
	dineroDisponibleNegocio();
}


var dineroDisponibleNegocio  = function  () {

	
	console.log("dineroDisponibleNegocio(); ");
	/*---------- empleo ----------*/
	
	var sueldo = $("#inp_wages_salary").val(),
		sueldo = Number(sueldo.replace(/[^0-9\.]+/g,""));
    var  ingresoAcreditadoEmpleo;
	 
	/*---------- comerciante ----------*/
    
	var GastosNegocio = 	$("#gastosNegocio").val(),
		GastosNegocio = Number(GastosNegocio.replace(/[^0-9\.]+/g,""));
	
	var comprasNegocio = 	$("#inp_shopping_monthly").val(),
		comprasNegocio = Number(comprasNegocio.replace(/[^0-9\.]+/g,""));

	var ingresoNeto;
	var otrosIngresos;
	
	/*---------- todos ----------*/
	
	var otroIngresoFamiliar =  $("#inp_income_otherfamilyE").val(),
		otroIngresoFamiliar =  Number(otroIngresoFamiliar.replace(/[^0-9\.]+/g,""));
		
	var otroIngresoDiferente =  $("#inp_other_income").val(),
		otroIngresoDiferente =  Number(otroIngresoDiferente.replace(/[^0-9\.]+/g,""));

	var gastoTotal = $("#gastoTotal").val(),
		gastoTotal = Number(gastoTotal.replace(/[^0-9\.]+/g,""));
			console.log("este es el gasto total"+ gastoTotal);
			
	/*---------- empleo operacion----------*/
 	
	if($(".operacionEmpleo").is(":visible")) {
  		    ingresoAcreditadoEmpleo = sueldo + otroIngresoFamiliar + otroIngresoDiferente;
  		    
		 	$(".IngresosEmpleo").text("$"+ingresoAcreditadoEmpleo).digitsText();
		 
			if( $("#inp_total_expense").val() != 0 ) {
				$("#gastoTotal").val($("#inp_total_expense").val());
			}
			$(".totalGastosEmpleo").text("$"+gastoTotal).digitsText();
			
			/*----------------------TOTAL OPERACION EMPLEO ----------------------*/
			
			totalIngresoAcreditado = ingresoAcreditadoEmpleo - gastoTotal;
			
			if (totalIngresoAcreditado >= 0) {	
				 $("#total_incomeExpense span").text("$" + totalIngresoAcreditado).digitsText();
			} else {
				$("#total_incomeExpense span").text("-$"+(totalIngresoAcreditado).toFixed(2).slice(1)).digitsText();
			}
			$("#inp_difenrent_income_expense").val(totalIngresoAcreditado);
			
	 } 
 	 
 	/*---------- negocio operacion ----------*/
	 if($(".operacionNegocio").is(":visible")) {
		 
		 if($("#inp_value_total_daily_Sales").is(":visible")) {
				var ventaMensual = 	$("#inp_value_total_daily_Sales").val()
				ventaMensual = Number(ventaMensual.replace(/[^0-9\.]+/g,""));
			    ingresoNeto = ventaMensual - GastosNegocio -comprasNegocio;
		 }
		 if( $("#inp_value_total_daily_monthly_Sales").is(":visible")) {
			    var ventaMensual2 = 	$("#inp_value_total_daily_monthly_Sales").val()
				ventaMensual2 = Number(ventaMensual2.replace(/[^0-9\.]+/g,""));
			  ingresoNeto = ventaMensual2 - GastosNegocio -comprasNegocio;
		 }
		 
		  if (ingresoNeto  >= 0) {
			  	 $(".utilidadNeta").text("$" + ingresoNeto).digitsText(); 
		  } else {
			     $(".utilidadNeta").text("-$"+(ingresoNeto).toFixed(2).slice(1)).digitsText();
		  } 
		  
		  $("#inp_business_company").val( ingresoNeto );
		  
		  $("#inp_business_company").blur();
		  
		  otrosIngresos = otroIngresoFamiliar + otroIngresoDiferente;
		  $(".otrosIngresos").text("$"+otrosIngresos).digitsText();	

		  if( $("#inp_total_expense").is(":visible")  ) {
				$("#gastoTotal").val($("#inp_total_expense").val());
		  }else{
			  $("#inp_total_expense").val($("#gastoTotal").val());
		  }
		  $(".totalGastos").text("$"+gastoTotal).digitsText();
			
		  /*----------------------TOTAL OPERACION NEGOCIO ----------------------*/
		  
		  totalIngresosNegocio =  ingresoNeto + otrosIngresos - gastoTotal;

		  if (totalIngresosNegocio  >= 0) {
				 $("#total_incomeExpense span").text("$" + totalIngresosNegocio).digitsText(); 
		  } else {
				 $("#total_incomeExpense span").text("-$"+(totalIngresosNegocio).toFixed(2).slice(1)).digitsText();
		  }
		  
		  $("#inp_difenrent_income_expense").val(totalIngresosNegocio);
	 }
	 
		asterisk(".labelsStl");
		asterisk(".numberAndTitle");
		asterisk(".titleDisabled");	
		
}

var especificarIEBtn = function(btn,conjunto, bloque) {
	if(!$(".especificar"+bloque).hasClass("active")) {
		$(".especificar"+bloque).trigger("click");
	}
}
var especificarIEBtnCerrar = function(btn, conjunto, bloque) {
	if($(".especificar"+bloque).hasClass("active")) {
		$(".especificar"+bloque).trigger("click");
		
	}
}
var especificarIE = function(tab) {
	
	if($(tab).hasClass("active")) {	
		if($(tab).hasClass("tab_otrosIngresos")) {
			if($("#inp_income_otherfamilyE").val() != 0 || $("#inp_other_income").val() != 0) {
				mensajeConfirmacionOtrosIngresos();
			}else {
				cerrarOtrosIngresos();
			}
		}
		
		if($(tab).hasClass("tab_gastosFam")) {
			if( $("#inp_total_expense").val() != 0 ) {
				mensajeConfirmacionGastosFam();
			}else {
				cerrarGastosFamiliares();
			}	
		}
		
		if($(tab).hasClass("tab_gastosNegocio")) {
			if( $("#inp_value_opExpenses").val() != 0 ) {
				mensajeConfirmacionGastosNeg();
			}else {
				cerrarGastosNegocio();
			}
		}

	}else {
		if($(tab).hasClass("tab_otrosIngresos")) {	
			$(tab).find("small").text("Cerrar");
			$(tab).find("i").removeClass("fa-angle-down").addClass("fa-times");
			$(".conjunto.otrosIngresosFleld div[data-opcion='2']").removeClass("active");
			$(".conjunto.otrosIngresosFleld div[data-opcion='1']").addClass("active");
		}
		
		if($(tab).hasClass("tab_gastosFam")) {	
			
			$("#gastoTotal").val($("#inp_total_expense").val());
			$("#gastoTotal").blur();
			$(".gastoTotalContainer").addClass("opacityHeight");
			$(tab).find("small").text("Cerrar");
			$(tab).find("i").removeClass("fa-angle-down").addClass("fa-times");
		    blurFormatincomexpenses ();
		    $(".conjunto.gastosFam div[data-opcion='2']").removeClass("active");
			$(".conjunto.gastosFam div[data-opcion='1']").addClass("active");
		}
		if($(tab).hasClass("tab_gastosNegocio")) {
			limpiarMontoTotal();
			$(".gastoNegocioContainer").addClass("opacityHeight");
			$(tab).find("small").text("Cerrar");
			$(tab).find("i").removeClass("fa-angle-down").addClass("fa-times");
		$(".conjunto.gastosNegocio div[data-opcion='2']").removeClass("active");
		$(".conjunto.gastosNegocio div[data-opcion='1']").addClass("active");
		}

		$(tab).addClass("active");
		$(tab).next(".especificacionesBloque").slideDown();

	}
	
}

var limpiarMontoTotal= function () {
	var camposDetalleIngresoFamilia = $('.gastos_total .dvContent input');
	var camposDetalleIngresoNegocio = $('#detailsOp ul li input');

  

  	var camposVaciosIngresoNegocio = camposDetalleIngresoNegocio.filter(function () {
	    return $(this).val() != 0;   
	}).length == 0 ;
	
	
	if(camposVaciosIngresoNegocio == true) {
		$("#gastosNegocio").val("0").blur();
	}
}

var especificacionesPrevRealizadas = function () {

	var camposDetalleIngresoFamilia = $('.gastos_total .dvContent input');
	var camposDetalleIngresoNegocio = $('#detailsOp ul li input');
	
  	var camposVaciosIngresoFamilia = camposDetalleIngresoFamilia.filter(function () {
	    return $(this).val() != 0;   
	}).length == 0;
  

  	var camposVaciosIngresoNegocio = camposDetalleIngresoNegocio.filter(function () {
	    return $(this).val() != 0;   
	}).length == 0;
  	
	$(".conjunto.otrosIngresosFleld div[data-opcion='2']").addClass("active");
  	$(".conjunto.gastosFam div[data-opcion='2']").addClass("active");
	$(".conjunto.gastosNegocio div[data-opcion='2']").addClass("active");
	
	if(camposVaciosIngresoFamilia == false) {
		$(".tab_gastosFam").click();
		$("#inp_food").blur();
		$(".conjunto.gastosFam div[data-opcion='2']").removeClass("active");
		$(".conjunto.gastosFam div[data-opcion='1']").addClass("active");
	}

	if(camposVaciosIngresoNegocio == false) {
		$(".tab_gastosNegocio").click();
		$('#detailsOp ul li:first-child input').blur();
		$(".conjunto.gastosNegocio div[data-opcion='2']").removeClass("active");
		$(".conjunto.gastosNegocio div[data-opcion='1']").addClass("active");
	}


	if($("#inp_income_otherfamilyE").val() != 0 || $("#inp_other_income").val() != 0) {
		$(".tab_otrosIngresos").click();
		otro_aporte_reset ();
		$(".conjunto.otrosIngresosFleld div[data-opcion='2']").removeClass("active");
		$(".conjunto.otrosIngresosFleld div[data-opcion='1']").addClass("active");
	}

	
}


function mensajeConfirmacionOtrosIngresos() {
	var nombreUsuario = $("#inputNombre").val();
	
	alertify.labels.cancel = "No cerrar";
	alertify.labels.ok = "Cerrar";
	
	alertify.confirm(nombreUsuario+", al cerrar esta sección se borrarán los datos que ingresaste.", function (e) {
		if (e) {
			displayMessageProcessing('msgprocessing', false);
		
			$("#otro_aporte_familiar input:radio[value=1]").click();
			$("#inp_other_income").val("");
			$("#inp_other_income").blur();
			$("#txt_abaut_other_income").val("");
			$("#txt_abaut_other_income").blur("");
			
			cerrarOtrosIngresos();
			dineroDisponibleNegocio(); 
			
			return true;
		} else { 
			
			return false;
		}
	}); 
}

function cerrarOtrosIngresos() {
	$(".tab_otrosIngresos").find("small").text("Especificar otros ingresos (opcional)");
	$(".tab_otrosIngresos").find("i").removeClass("fa-times").addClass("fa-angle-down");
	$(".tab_otrosIngresos").removeClass("active");
	$(".tab_otrosIngresos").next(".especificacionesBloque").slideUp();
	closeFancy();
	$(".conjunto.otrosIngresosFleld div[data-opcion='1']").removeClass("active");
	$(".conjunto.otrosIngresosFleld div[data-opcion='2']").addClass("active");
}

function mensajeConfirmacionGastosFam() {
	var nombreUsuario = $("#inputNombre").val();
	alertify.labels.cancel = "No cerrar";
    alertify.labels.ok = "Cerrar";
	alertify.confirm(nombreUsuario+", al cerrar esta sección se borrarán los datos que ingresaste.", function (e) {
		if (e) {
			displayMessageProcessing('msgprocessing', false);
			
			// $(".tab_gastosFam").next(".especificacionesBloque").find("input").val("");	
			$("#CMD_clear_expenses").trigger("click");
			
			
			return true;
		} else { 
			$(".conjunto.gastosFam div[data-opcion='2']").removeClass("active");
			$(".conjunto.gastosFam div[data-opcion='1']").addClass("active");
			return false;
		}
	}); 
}

function cerrarGastosFamiliares() {
	

	closeFancy();
	$(".conjunto.gastosFam div[data-opcion='1']").removeClass("active");
	$(".conjunto.gastosFam div[data-opcion='2']").addClass("active");
	
	$("#gastoTotal").val($("#inp_total_expense").val());
	dineroDisponibleNegocio(); 
	$(".tab_gastosFam").find("small").text("Especificar gastos familiares (opcional)");
	$(".tab_gastosFam").find("i").removeClass("fa-times").addClass("fa-angle-down");
	$(".tab_gastosFam").removeClass("active");
	$(".gastoTotalContainer").removeClass("opacityHeight");
	$(".tab_gastosFam").next(".especificacionesBloque").slideUp();
}
function mensajeConfirmacionGastosNeg() {
	
	var nombreUsuario = $("#inputNombre").val();
	
	 alertify.labels.cancel = "No cerrar";
	 alertify.labels.ok = "Cerrar";
	 
	alertify.confirm(nombreUsuario+", al cerrar esta sección se borrarán los datos que ingresaste.", function (e) {
		if (e) {
			
			displayMessageProcessing('msgprocessing', false);
			 $("#CMD_clear_costOperating").click();
			
			cerrarGastosNegocio();
			dineroDisponibleNegocio(); 
			return true;
		} else { 
	
			return false;
		}
	}); 
}

function cerrarGastosNegocio() {
	$("#gastosNegocio").val("0").blur();
	
	$('#detailsOp ul li input').each(function () {
		$(this).val("0");
	});
	$(".conjunto.gastosNegocio div[data-opcion='1']").removeClass("active");
	$(".conjunto.gastosNegocio div[data-opcion='2']").addClass("active");
	
	$(".tab_gastosNegocio").find("small").text("Especificar gastos de negocio (opcional)");
	$(".tab_gastosNegocio").find("i").removeClass("fa-times").addClass("fa-angle-down");
	$(".tab_gastosNegocio").removeClass("active");
	$(".gastoNegocioContainer").removeClass("opacityHeight");
	$(".tab_gastosNegocio").next(".especificacionesBloque").slideUp();
	dineroDisponibleNegocio(); 
	closeFancy();
	
	
}


var abiertoOtroAporte ; 

/*otro aporte familiar */
function otro_aporte_reset () {
	
	if( $("#inp_income_otherfamilyE").val() != 0 ) {
		$("#otro_aporte_familiar input:radio[value=0]").trigger("click");
		$(".otro_aporte_familiar").slideDown();
		$(".otroAporteField div[data-opcion='0']").addClass("active");
		
	}else {
		$("#otro_aporte_familiar input:radio[value=1]").trigger("click");
		
	}
}
function otro_aporte () {
	
	$("#otro_aporte_familiar input:radio[value=0]").click(function() {
		$(".otro_aporte_familiar").slideDown();
		$(".otroAporteField div[data-opcion='0']").addClass("active");
	
		
		setTimeout(function (){
			
			//alert( $(".bloqueOtrosIngresos .validatorClass:last-child").attr("id") );
					
			//$(".bloqueOtrosIngresos .validatorClass:last-child").blur();
			
			conjuntoCombos();
			$("#inp_income_otherfamilyE").trigger("focus");
			$("#otro_aporte_familiar input[value='0']").trigger("focus");
		
			console.log($("#coloniaEmp").val());
			
			abiertoOtroAporte = true;
			blurFormatincomexpenses ();
			
		}, 1500);
		fieldCount();
		
	});
	
	$("#otro_aporte_familiar input:radio[value=1]").click(function() {
		blurFormatincomexpenses ();
		cerrarIngresoFamiliar ();
		fieldCount();
		abiertoOtroAporte = false;
	
	});
}




function incomeFamily () {
	if( $("#inp_income_otherfamilyE").val() == "0" || $("#inp_income_otherfamilyE").val() == "" || $("#inp_income_otherfamilyE").val() == 0  ) {
		 $("#inp_income_otherfamilyE").removeClass("lleno");
		 $("#inp_income_otherfamilyE").addClass("vacio");
	}
}
function cerrarIngresoFamiliar () {

	$("#inp_income_otherfamilyE").val("0");
	$("#inp_income_otherfamilyE").blur();
	$(".otro_aporte_familiar").slideUp();
}


function abrir_imagenreferencia (btnImagen) {

	$(btnImagen).closest(".ContenedorImagenMuestra").find(".imagen_referencia").show("normal");
	
	$('html, body').animate({
	       scrollTop: ($(btnImagen).closest(".ContenedorImagenMuestra").find(".imagen_referencia").show("normal").offset().top - 100)
	},1400);	

	if($(btnImagen).hasClass("tipoCrede")){
		console.log("tiene crede");
		
	}
	if($(btnImagen).hasClass("ClaveEle")){
		console.log("tiene ClaveEle");
		
	}
	if($(btnImagen).hasClass("numEmision")){
		console.log("tiene numEmision");
		
	}
	
	if($(btnImagen).hasClass("nOCR")){
		console.log("nOCR");
	   
	}
	if($(btnImagen).hasClass("Selfiee")){
		console.log("Selfiee");
		
	}
}
function cerrar_imagenreferencia (btnCerrar) {
	$(btnCerrar).closest(".ContenedorImagenMuestra").find(".imagen_referencia").hide("normal");
}

function mismaDireccionFamiliar () {
	$("input[name=address_you]:radio").click(function() {
		
	    if($(this).val() == 'N') {
	    	$("#show_address").slideDown();
			fieldCount();
	    } else if($(this).val() == 'S') {
	    	$("#show_address").slideUp();
			fieldCount();
	    }
	    
	
	});
}

function updateBusinessIncome(){
	var totalValor=$("#inp_value_net").val();


	if(totalValor!=""){
		if($("#isMobile").val() == "true"){
			$("#inp_business_company").val(totalValor);
			$("#inp_business_company").blur();
		}else{
			parent.$("#inp_business_company").val(totalValor);
			parent.$("#inp_business_company").blur();
		}
	}	
}
/*nuevo incomeExpense  */
function calculeExpense () {
$('.calculeExpense').blur(function(event) {
	var sumar=0;
	$('.calculeExpense').each(function(){
		sumar += Number($(this).val().replace(",",""));
	});	
	totalExpense=Number($("#inp_total_expense").val(formatNum(parseFloat(sumar).toFixed(2))).val().replace(",",""));		
	$("#inp_total_expense").blur();
					
	event.preventDefault();
});
}
function resetFunctionIncome() {
	
	calculeExpense ();
	opExpenses_a_gastosNegocio ();
	dineroDisponibleNegocio(); 

	resetar_indices();
	loadScriptFormat();

}



function porcentajeIngresosGastos () {

	
	var registroVentas = $("#cbo_sales").val();
	var ventasSegmento  = $("#section-selling-details input");
	
	
	var gastoTotalFamiliaries = $("#gastoTotal").val();
	var gastoNegocio = $("#gastosNegocio").val();
	var comprasMensuales = $("#inp_shopping_monthly").val();	

	var camposOtroAporteFamiliar =  $(".otroAporteFamiliar input[type='text'].validatorClass, .otroAporteFamiliar textarea.validatorClass, .otroAporteFamiliar select.validatorClass");

	/*solo empleo*/
	var salarioIngresos = $("#inp_wages_salary").val();
	
	
	var objPorcent=$("#changeButtons\\:porcBasic");
	var porcent=0;

	if($(".ventasNegocioContenedor").is(":visible")) {
		
	
			if(registroVentas != ''){
				porcent +=20;
				console.log("registroVentas lleno");
			}else {
				console.log("registroVentas vacio");
			}
			
			if( gastoTotalFamiliaries.length != 0 ){
				porcent +=20;
				console.log("gastoTotalFamiliaries lleno");
			}else {
				console.log("gastoTotalFamiliaries vacio");
			}
			
			if(gastoNegocio.length != 0  ){
				porcent += 20;
				console.log("gastoNegocio lleno");
			}else {
				console.log("gastoNegocio vacio");
			}
			
			if(comprasMensuales.length !=0 ){
				porcent +=20;
			
				console.log("comprasMensuales lleno");
			}else {
				console.log("comprasMensuales vacio");
			}
			

			var ventasSegmentoValidacion = ventasSegmento.filter(function () {
			    return $(this).val() != "";   
			}).length == ventasSegmento.length ;
			
			if(ventasSegmentoValidacion == true) {
				porcent +=20;
				console.log("ventas parciales llenas"+ventasSegmentoValidacion )
			}else {
				console.log("ventas parciales vacias o alguna vacia"+ventasSegmentoValidacion );
			}

			
			if(abiertoOtroAporte == true) {
				
				console.log("abierto otro aporte");
				var campoAporteFamiliar = $(".bloqueOtrosIngresos .basicosOtroFamiliar:visible .validatorClass, .bloqueOtrosIngresos #show_address:visible .validatorClass").length;
				var campoAporteFamiliaLleno = $(".bloqueOtrosIngresos .basicosOtroFamiliar:visible .lleno, .bloqueOtrosIngresos #show_address:visible .lleno").length;
				
				console.log(campoAporteFamiliar);
				console.log(campoAporteFamiliaLleno);
				
				porcent = porcent/2;
				console.log("otro aporte familiar es visible"+ porcent);
				
			
				
				porcentAporteFamiliar = (campoAporteFamiliaLleno/campoAporteFamiliar)*50; 
				porcent = porcent + porcentAporteFamiliar; 
				console.log("otro aporte familiar es visible"+ porcent);
				
				
			}

	
	}else {

		if( gastoTotalFamiliaries.length != 0 ){
			porcent +=50;
			console.log("gastoTotalFamiliaries lleno");
		}else {
			console.log("gastoTotalFamiliaries vacio");
		}
		
		if( salarioIngresos.length != 0 ){
			porcent +=50;
			console.log("salarioIngresos lleno");
		}else {
			console.log("salarioIngresos vacio");
		}
		
		
		if(abiertoOtroAporte == true) {
			console.log("abierto otro aporte");
			var campoAporteFamiliar = $(".bloqueOtrosIngresos .basicosOtroFamiliar:visible .validatorClass, .bloqueOtrosIngresos #show_address:visible .validatorClass").length;
			var campoAporteFamiliaLleno = $(".bloqueOtrosIngresos .basicosOtroFamiliar:visible .lleno, .bloqueOtrosIngresos #show_address:visible .lleno").length;
			
			console.log(campoAporteFamiliar);
			console.log(campoAporteFamiliaLleno);
			
			porcent = porcent/2;
			console.log("otro aporte familiar es visible"+ porcent);

			porcentAporteFamiliar = (campoAporteFamiliaLleno/campoAporteFamiliar)*50; 
			porcent = porcent + porcentAporteFamiliar; 
			console.log("otro aporte familiar es visible"+ porcent);	
		}else {
			console.log("cerrado otro aporte");
		}
		
	}
	console.log("es empleo");
	objPorcent.val(porcent);
	objPorcent.blur();
	$("#changeButtons\\:countPorcent").click(); 
	console.log(porcent);
	console.log("es negocio");
	
	
}





function cleanField(field) {
	if($(field).val() ==  0 ||  $(field).val() ==  "0.00" ) {
		$(field).val(" ");
	}else{
		console.log("no es 0")
	}
	
}

function returnZero(field) {
	if($(field).val() == " " || $(field).val() == "" || $(field).val() === undefined ) {
		$(field).val("0.00");
		console.log("hola returnZero")
	}
}





