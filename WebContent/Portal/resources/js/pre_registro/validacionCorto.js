console.log("validacion.js");


function validar_pre_registro() {
console.log("entra");

	
	var numeroElementos = $(".empezarRegistro input.validatorClass:visible, .empezarRegistro select.validatorClass:visible").length;
	console.log("numeroElementos"+numeroElementos);	
			
	$(".empezarRegistro input.validatorClass:visible, .empezarRegistro select.validatorClass:visible").each(function(idx) {
		var elemento = $(this);
		 var valTop = (elemento.offset().top ) - 180;
		if( elemento.val().length == 0  || elemento.val() == '0') {
			if($(this).is('input') || $(this).is('textarea')) {
				elemento.removeClass("lleno");
				elemento.addClass("vacio"); 
			}
			  if( $(this).is('select')) {
					 $(this).closest(".selectNuevo").removeClass("lleno");
	 		    	 $(this).closest(".selectNuevo").addClass("vacio");
			  }
			  $.scrollTo(valTop);
			  return false;
			  
				console.log("vacios");
				console.log(idx+"idx");
				
			
		}
		else {
			if(elemento.hasClass("requiredClass")){
				  $.scrollTo(valTop);
				  if($("#codigo_postal").hasClass("requiredClass")) {
					  if($("#codigo_postal").val().length>=1){
							 alerta ("¡Código postal incorrecto!", "#codigo_postal");
						}
			
				  }
				  if($("#email4").hasClass("requiredClass")) {
					  if($("#email4").val().length>=1){
						  alerta ("Formato de correo incorrecto o el correo ya existe", "#email4");
					  }
					 // alertify.error('Formato de correo incorrecto');
				  }
				  if($("#passTemp").hasClass("requiredClass")) {
					  if($("#passTemp").val().length>=1){
						  alerta ("La contraseña debe tener un mínimo de 4 y un máximo de 20 caracteres.", "#passTemp");
					  }
				  }
					console.log("con requered");
				  return false;
			}else {
				
				if(numeroElementos == idx + 1) {
					console.log("llenos y validos");
					console.log(idx+"idx");
					aceptaAcreditado(); 
					/*if(!$("#aceptoTerminos input").prop('checked')) {
					   $("#aceptoTerminos .ui-state-default").addClass("vacio");
					   $.scrollTo(($("#aceptoTerminos").offset().top ) - 180);
					   alertify.error('Tienes que aceptar los términos y condiciones de kubo.financiero.');
					   return false;
					}else {
						
				
					}*/
					
					
				
				}
			}
			
		}
		
	
	 });
	
}


function abrirCondicionesContrato () {
	myapp = {active: false}; 
	 $('html, body').animate({scrollTop:0}, 'slow');
	 $('.cargando').hide();
	 $('.declaracion_portal').show();
	 $('.container1').show();
	 $("#conCondiciones").fadeIn();
   $(".div_scroll").scroll_absolute({arrows:true});
	$("#btnAviso").click();
}
