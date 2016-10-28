


$(document).ready(function() {
	
    $(".terminosCondicionesInner").scroll_absolute({arrows:true});

	
	 $('#passHelp').hide();

	 $(window).resize(function() {
		 $(".terminosCondicionesInner").scroll_absolute({arrows:true});
	 	
	 });
		 
		$('.crea_contrasena').click(function(){
			 $('html, body').animate({scrollTop:0}, 'slow');
		  	 $('#passHelp').fadeIn();
				
	    });
		
		$("#aceptoTerminos").click(function(){
			 if($("#aceptoTerminos input").prop('checked')) {
				 $("#aceptoTerminos .ui-chkbox-box").removeClass("vacio");
			}else {
				$("#aceptoTerminos .ui-chkbox-box").addClass("vacio");
			}
			 
	    });
		   $('#passHelp .close').click(function(){
			   $('#passHelp').fadeOut();
				if($("#light1").is(':visible')) {
					  $('html, body').animate({
					       scrollTop: ($("#pass3").offset().top - 30)
					   },500);
				}    
				else if($("#light8").is(':visible')) {
					  $('html, body').animate({
					       scrollTop: ($("#pass2").offset().top - 120)
					  },500);  
				}
				else {
					  $('html, body').animate({
					       scrollTop: ($("#pass").offset().top - 120)
					   },500);
				}
		  		
		  });	
		$('.condiciones').click(function(){
			if($('.requiredClass').is(":visible")) {
			   $('html, body').animate({
					       scrollTop: ($('.requiredClass').offset().top - 180)
					   },500);
			   }
		   });
			
		
		
		$('.crea_contrasena2').click(function(){
			
			 $('html, body').animate({scrollTop:0}, 'slow');
		  	 $('#passHelp').fadeIn();
		  		 
		});
		
		
		
		/* si el valor del partner se especifica desde la liga */ 
		checar_partner();
	
		$("#codigo_postal").attr("placeholder", "Código postal*");	


		$('.keyEnter').on("keypress", function(e) {
			 var key = e.which;
			 if(key == 13)  {
				 $(this).blur(); 
				 $("#continuar_registro").click();
			    return false;  
			  }
		});
});



function onFocus(campo) {
	var campo		    = $(campo);
	var contenedorCampo = campo.closest("div");
	contenedorCampo.addClass("activeCampo");
	
}
function onBlur(campo) {
	var campo		    = $(campo);
	var contenedorCampo = campo.closest("div");
	contenedorCampo.removeClass("activeCampo");
}

function contrasenaSeguraLista() {
	//console.log("llenos"+$(".contrasenaSegura .lleno:visible").length);
	// console.log("total de campos"+ $(".contrasenaSegura input:visible").length)
	//	if(!$(".contrasenaSegura .requiredClass:visible").length && $(".contrasenaSegura .lleno:visible").length == $(".contrasenaSegura input:visible").length) {
	if($(".contrasena_segura").is(":visible") && $(".contrasena_segura span").is(":hidden") && $("#confpass").val().length>1) {



		$(".mostrar").each(function() {
			var divMostrar = $(this);
			var textCode = divMostrar.find($(".textCode"));
			if(textCode.is(":visible")) {
				divMostrar.find($("span")).trigger("click");
				$('#passHelp').hide();
			}
		console.log(" Si es valida");
		$(".cargandoNuevo").addClass("show");
		$(".velo").fadeIn();
		$("#btnChangeSecurePass").click();
	
	});

	}else {
		console.log("No es valida");
	}
	
	
	
	
}

function pass_blur()  {
	validaPassColor();
	if( $("#confpass").val().length>0 ) {
		if($("#confpass").val() == $("#pass").val() ) {
			console.log($("#confpass").val());
			console.log($("#pass").val());
			$(".contrasena_segura span").hide();
			
			$("#confpass").removeClass('requiredClass');
			
			
		}else {
			console.log($("#confpass").val());
			console.log($("#pass").val());
			$("#confpass").addClass('requiredClass');
		
			$(".contrasena_segura span").show();
		} 
	}
}
function  minMaxCaracter(elemento) {
	if($(elemento).val().length >= 4  && $(elemento).val().length<20) {
		$(elemento).removeClass("requiredClass");
		alertaQuitar ("#passTemp");
	}else {
		$(elemento).addClass("requiredClass");
		  if($("#passTemp").val().length>=1){
			  alerta ("La contraseña debe tener un mínimo de 4 y un máximo de 20 caracteres.", "#passTemp");
		  }else{
				alertaQuitar ("#passTemp");
		  }
	}
		onBlur(elemento);
}

$(document).ready(function() {	
	
	$(".passCode").keyup(function(event){
		var passcode = $(this);			
		var passcodeVal = passcode.val();
		passcode.next("input").val(passcodeVal);	
		
	
	});
	
	$(".textCode").keyup(function(event){
		var textcode = $(this);
		var textcodeVal = textcode.val();
		textcode.prev("input").val(textcodeVal);
	
	});


	
	
});

function passToText(btn) {
	ga('send', 'event', 'Leads', 'Mostrar Password');
	var divMostrar =   $(btn).parent();
	var passBox    =   divMostrar.find(".passCode");
	var textBox    =   divMostrar.find(".textCode");					
	var passBoxId =    passBox.attr("id");
	var textBoxId =    textBox.attr("id");

	

	if (!$(btn).hasClass("active")) {
	
		 passBox.hide().removeAttr("id");
	     textBox.show().attr("id", passBoxId);
		 $(btn).addClass("active");
		 $(btn).html("Ocultar");
			
			if(passBox.hasClass("requiredClass") ) {
				textBox.addClass("requiredClass");
			}else {
				textBox.removeClass("requiredClass");
			}
		 pass_blur();
	}else {
		if(textBox.hasClass("requiredClass") ) {
			passBox.addClass("requiredClass");
		}else {
			passBox.removeClass("requiredClass");
		}
		  textBox.hide().removeAttr("id");
		  passBox.show().attr("id", textBoxId);
		  $(btn).removeClass("active");
		  $(btn).html("Mostrar");
		
		  pass_blur();
	}
}

function checar_partner(){
	setTimeout(function(){	
//		if($("#selectRegistration_Razon").val() != '0') {
//			console.log("viene lleno");
//			console.log($("#selectRegistration_Razon option:selected").text());
//			
//			$(".estadisticos").hide();
//			$(".estadisticos").prev("h6").hide();
//			
//		}else {
//			console.log("viene vacio");
//			$(".estadisticos").hide();
//			$(".estadisticos").prev("h6").hide();
//		}
	}, 300);
}

function condiciones(){
	$('html, body').animate({scrollTop:0}, 'slow');
	$('#conCondiciones').fadeIn();	
	
	 return false;

}
function inicializaValores(){

	
	
	
	$("#mail4").blur(function(){
		if($("#mail4").val().length<1){
			$("#imgMessageMail").hide();
			$("#imgCheckMail").hide();
		}
	});
	
	$("#pass").keyup(function(event){
		var width = $(window).width();			
		console.log($("#pass").val())
			event.preventDefault(); 
			validaPassColor();
			event.preventDefault();
			textopass();
			if( $("#confpass").val().length>0 ) {
				if($("#pass").val() != $("#confpass").val() ) {
					$("#confpass").addClass('requiredClass');
			
				}
				
				if($("#pass").val() == $("#confpass").val()) {
					$("#confpass").removeClass('requiredClass');
				}
			
			}
		
	});
	
	$("#confpass").keyup(function(event){
		console.log($("#confpass").val())
	});
	
	$("#pass").blur(function(event){
		validaPass();	
		$('#passHelp').slideUp();
	});
	despliegaPass ();
	
}

function despliegaPass (){
	$("#email4").blur(function(event){
		validaPassColor()
	
		 textopass ();
	
	});
	$("#name").blur(function(event){
		validaPassColor();
		
		 textopass ();
	});
$("#pass").each(function() {
	$(this).focus(function(event){
		 if ($(this).val().length==0 ) { 
		
			$(".item").removeClass("descValGreen");
			$(".item").removeClass("descValOrange");
			$(".item").addClass("descVal");
			/*contraseña v-mobile */
	
			$(".item_a").find("i").attr("class", "fa fa-circle")
		 }

		var width = $(window).width();	
		
		 if (width >= 1200) { 
			
				var t = $(this).offset().top  - 150;
				var l = $(this).offset().left +  $(this).width() + 20;
				$('#passHelp').css({'top' :  t+'px', 'left' : l+'px'});
				$('#passHelp').slideDown();	
				
			
				event.preventDefault(); 
		  }
		 if (($(window) .width() < 1199) && ($(window) .width() > 800)) {
		
		var t = $(this).offset().top + 37 ;
		var l = $(this).offset().left ;
		$('#passHelp').css({'top' :  t+'px', 'left' : l+'px'});
		$('#passHelp').slideDown();	
		
		event.preventDefault(); 
		 }
			/*contraseña v-mobile */
			
			});

	$("#pass").blur(function(event){
		
		if ($(this).val().length==0 ) { 
			 
			 $(".item_a").find("i").attr("class", "fa fa-circle");
		 }
	});
	
});

}



function textopass (){
	
	if ($(".item_b").is(':visible')) { 
		
		$("#passTitle3").show();
		
	}else {
		$("#passTitle3").hide();
	}	
	
	if ($("#message  .descValGreen").length == $("#message .item").length) {
	
		$(".crea_contrasena span").remove();
		$(".crea_contrasena").append($("<span class='cumple'>cumple la contraseña <small>?</small> </div>"));
		
		$(".crea_contrasena span").remove();
		$(".contrasena_segura").show();
		$(".contrasena_novalida").hide();
		
		if($("#pass").val().length >0) {
   	
			$("#pass").removeClass("requiredClass");
   			$(".pass_code").removeClass("requiredClass");
   			$(".text_code").removeClass("requiredClass");
		
		}
		
	}else {
		
		$(".crea_contrasena span").remove();
		$(".crea_contrasena").append($("<span class='no_cumple'>no cumple la contraseña <small>?</small> </div>"));
		$(".contrasena_segura").hide();
		
		if($("#pass").val().length >0) {
			
   			$("#pass").addClass("requiredClass");
   			$(".pass_code").addClass("requiredClass");
   			$(".text_code").addClass("requiredClass");
   			$(".contrasena_novalida").show();
   			
		}
   			
   	}
	   	
} 

function valAlias(){
	
	if($("#alias").val().length<1){
	
		$("#imgMessageAlias").hide();
		$("#imgCheckAlias").hide();
		
	}
}

function valMail(){
	if($("#mail4").val().length<1){
		$("#imgMessageMail").hide();
		$("#imgCheckMail").hide();
	}
}
function valConfMail(){
	if($("#confmail").val().length<1){
		$("#imgMessageConfMail").hide();
		$("#imgCheckConfMail").hide();
	}
}
function valConfPass(){
	if($("#confpass").val().length<1){
		$("#imgMessageConfPass").hide();
		$("#imgCheckPass").hide();
	}
}

function checkHELLO(field, rules, i, options){
	  //if (field.val() != "HELLO") {
	     // this allows the use of i18 for the error msgs
		  alert("ejecuta JS");
	    // return options.allrules.validate2fields.alertText;
	  //}
	}


function validaPassColor(){
	var pass = $("#pass").val();
	var name = $("#name").val();
	var email = $("#email4").val();
	
	
	
	var inst1 = "kubo";
	var inst2 = "cubo";
	var inst3 = "kuvo";
	var inst4 = "cuvo";

	if(pass.length==0){
		
		
		$("#valLength").removeClass("descVal");
		$("#valLength").removeClass("descValGreen");
		$("#valLength").removeClass("descValOrange");
		$("#valLength").addClass("descVal");
		
		$("#valMaxLength").removeClass("descVal");
		$("#valMaxLength").removeClass("descValGreen");
		$("#valLength").removeClass("descValOrange");
		$("#valLength").addClass("descVal");
		
		$("#valVoc").removeClass("descVal");
		$("#valVoc").removeClass("descValGreen");
		$("#valVoc").removeClass("descValOrange");
		$("#valVoc").addClass("descVal");
		
		$("#valCnsnt").removeClass("descVal");
		$("#valCnsnt").removeClass("descValGreen");
		$("#valCnsnt").removeClass("descValOrange");
		$("#valCnsnt").addClass("descVal");
		
		$("#valNum").removeClass("descVal");
		$("#valNum").removeClass("descValGreen");
		$("#valNum").removeClass("descValOrange");
		$("#valNum").addClass("descVal");
		
		$("#valName").removeClass("descVal");
		$("#valName").removeClass("descValGreen");
		$("#valName").removeClass("descValOrange");
		$("#valName").addClass("descVal");
		
		$("#valUsr").removeClass("descVal");
		$("#valUsr").removeClass("descValGreen");
		$("#valUsr").removeClass("descValOrange");
		$("#valUsr").addClass("descVal");
		
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descVal");
		
		$("#valCharCon").removeClass("descVal");
		$("#valCharCon").removeClass("descValGreen");
		$("#valCharCon").removeClass("descValOrange");
		$("#valCharCon").addClass("descVal");
		
		$("#valNumCon").removeClass("descVal");
		$("#valNumCon").removeClass("descValGreen");
		$("#valNumCon").removeClass("descValOrange");
		$("#valNumCon").addClass("descVal");
		
		$("#valRepChar").removeClass("descVal");
		$("#valRepChar").removeClass("descValGreen");
		$("#valRepChar").removeClass("descValOrange");
		$("#valRepChar").addClass("descVal");
		return;
		
		/*contraseña v-mobile */
		$("#passTitle2").hide();
		
		$(".item_a").removeClass("error");
		$(".ayuda_contrasena_mobile .item_b").hide();
		
	}else{
		if(pass.length<8){
			$("#valLength").removeClass("descVal");
			$("#valLength").removeClass("descValGreen");
			$("#valLength").removeClass("descValOrange");
			$("#valLength").addClass("descValOrange");
			
			
	
		}
		else{
			$("#valLength").removeClass("descVal");
			$("#valLength").removeClass("descValGreen");
			$("#valLength").removeClass("descValOrange");
			$("#valLength").addClass("descValGreen");
		
			
			
		}
	}
	
	if(pass.length>12){
		
		$("#valMaxLength").removeClass("descVal");
		$("#valMaxLength").removeClass("descValGreen");
		$("#valMaxLength").removeClass("descValOrange");
		$("#valMaxLength").addClass("descValOrange");
		$("#valMaxLength2").show();
		
	}
	else{

		$("#valMaxLength").removeClass("descVal");
		$("#valMaxLength").removeClass("descValGreen");
		$("#valMaxLength").removeClass("descValOrange");
		$("#valMaxLength").addClass("descValGreen");
		$("#valMaxLength2").hide();
		
	}
	/*contraseña v-mobile */
	
	if( pass.length<8 ||  pass.length>12 ){
		$("#valLength2").find("i").attr("class", "fa fa-circle");
	}else {
		$("#valLength2").find("i").attr("class", "fa fa-check");
	}

	
	
	if( email.length>0 && email.indexOf("@")!=(-1) ){
		email = email.split("@")[0];
		if((pass.toUpperCase()).indexOf(email.toUpperCase())!=(-1)){
			$("#valUsr").removeClass("descVal");
			$("#valUsr").removeClass("descValGreen");
			$("#valUsr").removeClass("descValOrange");
			$("#valUsr").addClass("descValOrange");
			/*contraseña v-mobile */
			$("#valUsr2").show();
			
		}else{
			$("#valUsr").removeClass("descVal");
			$("#valUsr").removeClass("descValGreen");
			$("#valUsr").removeClass("descValOrange");
			$("#valUsr").addClass("descValGreen");
			/*contraseña v-mobile */
			$("#valUsr2").hide();
		}
	}
	
	if(name.length>0){
		if((pass.toUpperCase()).indexOf(name.toUpperCase())!=(-1)){
			$("#valName").removeClass("descVal");
			$("#valName").removeClass("descValGreen");
			$("#valName").removeClass("descValOrange");
			$("#valName").addClass("descValOrange");
			/*contraseña v-mobile */
			$("#valName2").show();
		}else{
			$("#valName").removeClass("descVal");
			$("#valName").removeClass("descValGreen");
			$("#valName").removeClass("descValOrange");
			$("#valName").addClass("descValGreen");
			
			/*contraseña v-mobile */
			$("#valName2").hide();
		}
	}
	/*if((name.toUpperCase()).indexOf(pass.toUpperCase())!=(-1)){
		$("#valName").removeClass("descVal");
		$("#valName").removeClass("descValGreen");
		$("#valName").removeClass("descValOrange");
		$("#valName").addClass("descValOrange");
	}else{
		$("#valName").removeClass("descVal");
		$("#valName").removeClass("descValGreen");
		$("#valName").removeClass("descValOrange");
		$("#valName").addClass("descValGreen");
	}*/
	
	if((pass.toUpperCase()).indexOf(inst1.toUpperCase())!=(-1)){
	
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
		/*contraseña v-mobile */
		$("#valInst2").show();
	}else if((pass.toUpperCase()).indexOf(inst2.toUpperCase())!=(-1)){
		
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
		/*contraseña v-mobile */
		$("#valInst2").show();
	}else if((pass.toUpperCase()).indexOf(inst3.toUpperCase())!=(-1)){
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
		/*contraseña v-mobile */
		$("#valInst2").show();
	}else if((pass.toUpperCase()).indexOf(inst4.toUpperCase())!=(-1)){
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
		/*contraseña v-mobile */
		$("#valInst2").show();
	}else{
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValGreen");
		/*contraseña v-mobile */
		$("#valInst2").hide();
	}
	
	valCaracteresColor();
	

}



function valCaracteresColor(){
	
		var s = $("#pass").val();
		var x = 0;
		var conV = 0;
		var conC = 0;
		var conN = 0;
		var b = false;
		//var cadena = "";
		
		//var cadenaTemp="";
		
		if($("#pass").length>0){
		
			for(var i = 0; i<=s.length;i++){
					if(i<(s.length-1)){
						  if((s.charAt(i)+"")==(s.charAt(i+1)+"")){
							  x++;
							  if(x>1){
								  b=true;
								  break;
							  }
						  }
					}
				
				  if ((s.toLowerCase().charAt(i)=='a') || (s.toLowerCase().charAt(i)=='e') || (s.toLowerCase().charAt(i)=='i') || (s.toLowerCase().charAt(i)=='o') || (s.toLowerCase().charAt(i)=='u')){
					    conV++;
					  }
				  if ((s.toLowerCase().charAt(i)!='') && (s.toLowerCase().charAt(i)!=' ') &&(s.toLowerCase().charAt(i)!='a') && (s.toLowerCase().charAt(i)!='e') && (s.toLowerCase().charAt(i)!='i') && (s.toLowerCase().charAt(i)!='o') && (s.toLowerCase().charAt(i)!='u')&&
				  (s.charAt(i)!='0') && (s.charAt(i)!='1') && (s.charAt(i)!='2') && (s.charAt(i)!='3') && (s.charAt(i)!='4') && (s.charAt(i)!='5') && (s.charAt(i)!='6') && (s.charAt(i)!='7') && (s.charAt(i)!='8') && (s.charAt(i)!='9')
				  ){
					    conC++;
					  }
				  if ((s.charAt(i)=='0') || (s.charAt(i)=='1') || (s.charAt(i)=='2') || (s.charAt(i)=='3') || (s.charAt(i)=='4') || (s.charAt(i)=='5') || (s.charAt(i)=='6') || (s.charAt(i)=='7') || (s.charAt(i)=='8') || (s.charAt(i)=='9')){
				    conN++;
				  }
			
				
			}
		}
		if(b){
			$("#valRepChar").removeClass("descVal");
			$("#valRepChar").removeClass("descValGreen");
			$("#valRepChar").removeClass("descValOrange");
			$("#valRepChar").addClass("descValOrange");
			
			/*contraseña v-mobile */
			$("#valRepChar2").show();
			
		}else{
			$("#valRepChar").removeClass("descVal");
			$("#valRepChar").removeClass("descValGreen");
			$("#valRepChar").removeClass("descValOrange");
			$("#valRepChar").addClass("descValGreen");
			/*contraseña v-mobile */
			$("#valRepChar2").hide();
		}
		if(conV==0){
			$("#valVoc").removeClass("descVal");
			$("#valVoc").removeClass("descValGreen");
			$("#valVoc").removeClass("descValOrange");
			$("#valVoc").addClass("descValOrange");
			/*contraseña v-mobile */
			$("#valVoc2").find("i").attr("class", "fa fa-circle");
		}else{
			$("#valVoc").removeClass("descVal");
			$("#valVoc").removeClass("descValGreen");
			$("#valVoc").removeClass("descValOrange");
			$("#valVoc").addClass("descValGreen");
			/*contraseña v-mobile */
			$("#valVoc2").find("i").attr("class", "fa fa-check");
		}
		if(conC==0||conC=='0'){
			$("#valCnsnt").removeClass("descVal");
			$("#valCnsnt").removeClass("descValGreen");
			$("#valCnsnt").removeClass("descValOrange");
			$("#valCnsnt").addClass("descValOrange");
			/*contraseña v-mobile */
			$("#valCnsnt2").find("i").attr("class", "fa fa-circle");
			
		}else{
			
			$("#valCnsnt").removeClass("descVal");
			$("#valCnsnt").removeClass("descValGreen");
			$("#valCnsnt").removeClass("descValOrange");
			$("#valCnsnt").addClass("descValGreen");
			/*contraseña v-mobile */
			$("#valCnsnt2").find("i").attr("class", "fa fa-check");
		}
		if(conN==0){
			$("#valNum").removeClass("descVal");
			$("#valNum").removeClass("descValGreen");
			$("#valNum").removeClass("descValOrange");
			$("#valNum").addClass("descValOrange");
			/*contraseña v-mobile */
			$("#valNum2").find("i").attr("class", "fa fa-circle");
		}else{
			$("#valNum").removeClass("descVal");
			$("#valNum").removeClass("descValGreen");
			$("#valNum").removeClass("descValOrange");
			$("#valNum").addClass("descValGreen");
			/*contraseña v-mobile */
			$("#valNum2").find("i").attr("class", "fa fa-check");
		}
		
		validaCharConsecutivos(s);
		validaNumConsecutivos(s);
		
		return true;
	/*}else{
		
		return true;
	}*/
}

function validaCharConsecutivos(s){
	var cadenaABC = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	var cadenaQWE = "QWERTYUIOP";
	var cadenaASD = "ASDFGHJKLÑ";
	var cadenaZXC = "ZXCVBNM";
	var cadenaQWEInv = "POIUYTREWQ";
	var cadenaASDInv = "ÑLKJHGFDSA";
	var cadenaZXCInv = "MNBVCXZ";
	var cadena = "";
	if(s.length>2){
		for(var i = 0; i<=s.length;i++){
			cadena += s.toUpperCase().charAt(i)+"";
			if(cadena.length == 3){
				if(cadenaQWE.indexOf(cadena)!=(-1) || (cadenaABC.toUpperCase()).indexOf(cadena)!=(-1) || cadenaASD.indexOf(cadena)!=(-1) || cadenaZXC.indexOf(cadena)!=(-1) || 
				   cadenaQWEInv.indexOf(cadena)!=(-1) || cadenaASDInv.indexOf(cadena)!=(-1) || cadenaZXCInv.indexOf(cadena)!=(-1)	
				){
					$("#valCharCon").removeClass("descVal");
					$("#valCharCon").removeClass("descValGreen");
					$("#valCharCon").removeClass("descValOrange");
					$("#valCharCon").addClass("descValOrange");
					/*contraseña v-mobile */
					$("#valCharCon2").show();
					break;
					
				}else{
					
					$("#valCharCon").removeClass("descVal");
					$("#valCharCon").removeClass("descValGreen");
					$("#valCharCon").removeClass("descValOrange");
					$("#valCharCon").addClass("descValGreen");
					/*contraseña v-mobile */
					$("#valCharCon2").hide();
					
				}
		
				cadena = cadena.substring(1,3);
		 }
		}
	}else{
		
		$("#valCharCon").removeClass("descVal");
		$("#valCharCon").removeClass("descValGreen");
		$("#valCharCon").removeClass("descValOrange");
		$("#valCharCon").addClass("descValGreen");
		/*contraseña v-mobile */
		$("#valCharCon2").hide();
	}
	return;
}

function validaNumConsecutivos(s){
	
	var cadenaNUM = "01234567890";
	var cadenaNUMInv = "09876543210";

	var cadena ="";
	if(s.length>2){
		for(var i = 0; i<=s.length;i++){
			cadena += s.toUpperCase().charAt(i)+"";
			 if(cadena.length == 3){
					if((cadenaNUM.indexOf(""+cadena+""))!=(-1) || (cadenaNUMInv.indexOf(""+cadena+""))!=(-1) ){
						$("#valNumCon").removeClass("descVal");
						$("#valNumCon").removeClass("descValGreen");
						$("#valNumCon").removeClass("descValOrange");
						$("#valNumCon").addClass("descValOrange");
						/*contraseña v-mobile */
						$("#valNumCon2").show();
						break;
					
					}else{
						$("#valNumCon").removeClass("descVal");
						$("#valNumCon").removeClass("descValGreen");
						$("#valNumCon").removeClass("descValOrange");
						$("#valNumCon").addClass("descValGreen");
						/*contraseña v-mobile */
						$("#valNumCon2").hide();
					}
			
					cadena = cadena.substring(1,3);
			 }
		}
	}else{
		$("#valNumCon").removeClass("descVal");
		$("#valNumCon").removeClass("descValGreen");
		$("#valNumCon").removeClass("descValOrange");
	
		$("#valNumCon").addClass("descValGreen");
		/*contraseña v-mobile */
		$("#valNumCon2").hide();
	
	}
	return;
}

function validaPass(){
	
	var pass = $("#pass").val();
	var name = $("#name").val();
	var email = $("#email4").val();
	var inst1 = "kubo";
	var inst2 = "cubo";
	var inst3 = "kuvo";
	var inst4 = "cuvo";
	
	if(pass.length==0)
		return false;
	
	if(pass.length<8){
			 console.log("La contraseña debe contener al menos 8 caracteres");
		return false;
	}
	
	/*
	if($("#pass").val().length>12){
		 console.log("La contraseña debe contener 12 caracteres como máximo");
		 $("#pass").addClass("requiredClass");
		 return false;

	}
	
	if($("#pass").val().length<12){
		 $("#pass").removeClass("requiredClass");
	}
	*/
	
	if( email.length>0 && email.indexOf("@")!=(-1) ){
		email = email.split("@")[0];
		if((pass.toUpperCase()).indexOf(email.toUpperCase())!=(-1)){
			 console.log("La contraseña no debe contener tu nombre de usuario del correo electrónico");
			return false;
		}
	}
	
	if((pass.toUpperCase()).indexOf(name.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener tu nombre");
		return false;
	}
	if((name.toUpperCase()).indexOf(pass.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener tu nombre");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst1.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst2.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst3.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst4.toUpperCase())!=(-1)){
		 console.log("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	
	return valCaracteres();
	
}

function valCaracteres(){
	if($("#pass").length>0){
		var s = $("#pass").val();
		var x = 0;
		var conV = 0;
		var conC = 0;
		var conN = 0;
		var b = false;
		var cadena = "";
		var cadenaABC = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		var cadenaNUM = "01234567890";
		var cadenaQWE = "QWERTYUIOP";
		var cadenaASD = "ASDFGHJKLÑ";
		var cadenaZXC = "ZXCVBNM";
		var cadenaNUMInv = "09876543210";
		var cadenaQWEInv = "POIUYTREWQ";
		var cadenaASDInv = "ÑLKJHGFDSA";
		var cadenaZXCInv = "MNBVCXZ";
		var cadenaTemp="";
		
		for(var i = 0; i<=s.length;i++){
			if(i<(s.length-1)){
				  if((s.charAt(i)+"")==(s.charAt(i+1)+"")){
					  x++;
					  if(x>1){
						  b=true;
						  break;
					  }
				  }
			}
			
		  if ((s.toLowerCase().charAt(i)=='a') || (s.toLowerCase().charAt(i)=='e') || (s.toLowerCase().charAt(i)=='i') || (s.toLowerCase().charAt(i)=='o') || (s.toLowerCase().charAt(i)=='u')){
			    conV++;
			  }
		  if ((s.toLowerCase().charAt(i)!='') && (s.toLowerCase().charAt(i)!=' ') && (s.toLowerCase().charAt(i)!='a') && (s.toLowerCase().charAt(i)!='e') && (s.toLowerCase().charAt(i)!='i') && (s.toLowerCase().charAt(i)!='o') && (s.toLowerCase().charAt(i)!='u')&&
				  (s.charAt(i)!='0') && (s.charAt(i)!='1') && (s.charAt(i)!='2') && (s.charAt(i)!='3') && (s.charAt(i)!='4') && (s.charAt(i)!='5') && (s.charAt(i)!='6') && (s.charAt(i)!='7') && (s.charAt(i)!='8') && (s.charAt(i)!='9')
		  ){
			    conC++;
			  }
		  if ((s.charAt(i)=='0') || (s.charAt(i)=='1') || (s.charAt(i)=='2') || (s.charAt(i)=='3') || (s.charAt(i)=='4') || (s.charAt(i)=='5') || (s.charAt(i)=='6') || (s.charAt(i)=='7') || (s.charAt(i)=='8') || (s.charAt(i)=='9')){
			    conN++;
			  }
			
			
			cadena += s.toUpperCase().charAt(i);
			if(cadena.length == 3){
				if((cadenaABC.toUpperCase()).indexOf(cadena)!=(-1)){
					//  08 dic alert("la contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaNUM.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener números consecutivos");
					return false;
				}
				if(cadenaQWE.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaASD.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaZXC.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				
				
				if(cadenaNUMInv.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaQWEInv.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaASDInv.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaZXCInv.indexOf(cadena)!=(-1)){
					//  08 dic alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				cadena = cadena.substring(1,3);
				
			}
			cadenaTemp += s.charAt(i);
		}
		if(b){
			//  08 dic alert("La contraseña no puede repetir un caracter más de 2 veces consecutivas ");
			return false;
		}
		if(conV==0){
			//  08 dic alert("La contraseña debe contener al menos una vocal "+cadenaTemp);
			return false;
		}
		if(conC==0){
			//  08 dic alert("La contraseña debe contener al menos una consonante "+cadenaTemp);
			return false;
		}
		if(conN==0){
			//  08 dic alert("La contraseña debe contener al menos un número "+cadenaTemp);
			return false;
		}
		
		return true;
	}else{
		
		return true;
	}
}

function validateSelect(element) {
	var elemento=$(element);
	if(elemento.val() !=''){
	//	elemento.removeClass("requiredClass");
	}
	else{
	//	elemento.addClass("requiredClass");	
	}
	
}
function validateformPR() {
	
	$('#inp_recommended_search_input').removeClass('requiredClass');
	$('#cbo-promotor').removeClass('requiredClass');
	$('#pass').removeClass('requiredClass');
	$('#confpass').removeClass('requiredClass');
	$('#email4').removeClass('requiredClass');
	$('#confemail').removeClass('requiredClass');
	
	var campos=document.signin.elements;
	var bandera=true;
	for(var i=0;i<campos.length;i++){
		if(campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("validatorClass") ){
			if(!$(campos[i]).is(":hidden")){
				if(campos[i].value==null || campos[i].value=="0" || campos[i].value==""){
					$(campos[i]).parent().addClass("requiredClass");					
					bandera=false;
					alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					break;
				}
				else
					$(campos[i]).parent().removeClass("requiredClass");
			}
		}
		
		if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass")){
			if(!$(campos[i]).is(":hidden")){
				if(campos[i].value==null || (campos[i].value=="0" || campos[i].value=="")){
					$(campos[i]).addClass("requiredClass");					
					bandera=false;
					alert( $(campos[i].attr( "id") ) );
					
					var element = ( $(campos[i]).parent().parent() );
					var valTop = (element.offset().top ) - 180;
					
					$.scrollTo(valTop);
					
					break;
				}
				else
					$(campos[i]).removeClass("requiredClass");
			}
		}
	}
	
	if( !bandera ){
		return false;
	}
	if($("#pass").hasClass("requiredClass")) {
		return false;
	}
	if( (parseInt($("#selectRegistration_Razon").val())==6) && ( $.trim( $('#cbo-promotor').val() )) == "" ){
		$('#cbo-promotor').addClass('requiredClass');
		//alert('#cbo-promotor');
		
		var element = ( $('#cbo-promotor').parent().parent() );
		var valTop = (element.offset().top ) - 180;
		
		$.scrollTo(valTop);
		
		return false;
	}
	
	if(varEmail && varPass && varConfEmail && varName && varPromo ){
	
		if(bandera){
			if($('#recaptcha_response_field').val()==''){
				//alert('#recaptcha_response_field');
				$.scrollTo('#recaptcha_response_field', 800, { axis:'y' });
				return false;
			}
			if( !($('#valInvestment').val() == 'true') )
				bandera = validaPass();
		}
		
		if( !$("#cbo-promotor").is(":hidden") && $("#cbo-promotor").val() == "" ){
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if( !$("#inp_recommended_search_input").is(":hidden") && $("#inp_recommended_search_input").val() == "" ){
			$('#inp_recommended_search_input').addClass('requiredClass');
			//alert('#cbo-promotor');
			//$.scrollTo('#inp_recommended_search_input', 800, { axis:'y' });
			
			var element = ( $('#inp_recommended_search_input').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		if(bandera){
			//displayMessageProcessing('msgprocessing',false);
			myapp = {active: false}; 
			 $('html, body').animate({scrollTop:0}, 'slow');
			 $('.cargando').hide();
			 $('.declaracion_portal').show();
			 
			 $('.container1').show();
		 	 $("#conCondiciones").fadeIn();
		
			 
		    $(".div_scroll").scroll_absolute({arrows:true});

			$("#btnAviso").click();
			
			return bandera;
			}
			
		else{
			
			
			
		}
		
	}else{
		
		if(!varEmail){
			$('#email4').addClass('requiredClass');
			//alert('#email4');
			//$.scrollTo('#email4', 800, { axis:'y' });
			
			var element = ( $('#email4').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varPass){
			
			var element = null;
			
			if( $("#pass").val() == "" ){
				
				$('#pass').addClass('requiredClass');
				element = ( $('#pass').parent().parent() );
				
				
			}else{
			
				$('#confpass').addClass('requiredClass');
				element = ( $('#confpass').parent().parent() );
				
			}
			//alert('#confpass');
			//$.scrollTo('#confpass', 800, { axis:'y' });
			
			
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		
		}
		if(!varConfEmail){
			$('#confemail').addClass('requiredClass');
			//alert('#confemail');
			//$.scrollTo('#confemail', 800, { axis:'y' });
			
			var element = ( $('#confpass').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varName){
			$('#name').addClass('requiredClass');
			//alert('#name');
				$.scrollTo('#name', 800, { axis:'y' });
			
			var element = ( $('#name').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		if(!varPromo){
			$('#cbo-promotor').addClass('requiredClass');
			//alert('#cbo-promotor');
			$.scrollTo('#cbo-promotor', 800, { axis:'y' });
			
			var element = ( $('#cbo-promotor').parent().parent() );
			var valTop = (element.offset().top ) - 180;
			
			$.scrollTo(valTop);
			
			return false;
		}
		
		
	}
	
}

function validaNewPass(){
	if($("#passAnt").val()==$("#pass").val()){
		alert("La contraseña nueva no puede ser igual que la anterior ");
		return false;
	}else
		return true;
}

function validaChangePass(){
	if(validaPass()&&validaNewPass()){
		
		return true;
	}else{
		return false;
	}
} 
function selectOptionsInv (){
			var selectOptions = ['3',
			                     '66',
			                     '6',
			                     '14',
			                     '57'];
			$("#selectRegistration_Razon option").each(function() {
					if($.inArray( $(this).val(), selectOptions) >= 0) {
								$(this).remove();
					}
			});	
}
