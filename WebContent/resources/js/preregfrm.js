
$(document).ready(function() 
{		
	inicializaValores();
	
	 $('#passHelp').hide();
	 
	 $('.crea_contrasena').click(function()
	 {
		 $('html, body').animate({scrollTop:0}, 'slow');
		 $('#passHelp').fadeIn();				
	 });
	 
	$('#passHelp .close').click(function()
    {
		 $('#passHelp').fadeOut();
		 
			$('html, body').animate(
			{
				scrollTop: ($("#pass").offset().top - 140)
			
			}, 500);
	});	
		
							
});

function inicializaValores()
{
	$("#confmail").blur(function()
	{
		if($("#confmail").val().length < 1)
		{
			$("#imgMessageConfMail").hide();
			$("#imgCheckConfMail").hide();
		}
	});
	
	$("#mail").blur(function()
	{
		if($("#mail").val().length < 1)
		{
			$("#imgMessageMail").hide();
			$("#imgCheckMail").hide();
		}
	});
	
	$(".validatePass").focus(function(event)
	{
		 if ($(this).val().length==0 ) { 
				$(".item").removeClass("descValGreen");
				$(".item").removeClass("descValOrange");
				$(".item").addClass("descVal");
				 }
				var width = $(window).width();	
				 if (width >= 801) { 
		var t = $('.validatePass').offset().top  - 80;
		var l = $(this).offset().left+285;
		$('#passHelp').css({'top' :  t+'px', 'left' : l+'px'});
		$('#passHelp').slideDown();	
		
		
		event.preventDefault(); 
		 }
	});
	
	
	$(".validatePass").keyup(function(event)
	{		
		event.preventDefault(); 
		validaPassColor();
		event.preventDefault();
		textopass();
	});
	
	$(".validatePass").blur(function(event)
	{
		$('#passHelp').slideUp();	
		validaPass();
	});
	
	$("a.aviso").fancybox(
	{
		'autoDimensions': false,
		'width' : '80%',
		'height' : '70%',
		'autoScale' : true,
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'type' : 'inline',
		'scrolling' : 'auto',
		'centerOnScroll' : false,
		'overlayColor': '#333333',
		'hideOnOverlayClick': false,
		'showCloseButton' : false,
		'href': '#dvPreRegCond',
		'onClosed':function() 
		{
			if($("#valFlag").val() == "acepta")
			{
				//$("#prAction").click();
				preRegAction();
			}
		}

	});

}

/*funcion gabriel  para creacion de la contraseña */
function textopass () {
	 if ($("#message  .descValGreen").length == $("#message .item").length) {
			    $(".crea_contrasena span").remove();
	   			$(".crea_contrasena").append($("<span class='cumple'>cumple la contraseña <small>?</small> </div>"));

				}else {
				 $(".crea_contrasena span").remove();
	   			$(".crea_contrasena").append($("<span class='no_cumple'>no cumple la contraseña <small>?</small> </div>"));
			}
	 
	 if($("#pass").val().length > 0){
			 $(".crea_contrasena").css("display", "block");
		
	}else {
		 $(".crea_contrasena").css("display", "none");
	}
}

function valAlias(){
	if($("#alias").val().length<1){
		$("#imgMessageAlias").hide();
		$("#imgCheckAlias").hide();
	}
}

function valMail(){
	if($("#mail").val().length<1){
		$("#imgMessageMail").hide();
		$("#imgCheckMail").hide();
	}
}

function valConfMail()
{
	

	if($("#confmail").val().length<1){
		$("#imgMessageConfMail").hide();
		$("#imgCheckConfMail").hide();
	}

}

function valConfPass()
{
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
	var email = $("#email").val();
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
	}
	else{
		$("#valMaxLength").removeClass("descVal");
		$("#valMaxLength").removeClass("descValGreen");
		$("#valMaxLength").removeClass("descValOrange");
		$("#valMaxLength").addClass("descValGreen");
	}
	
	if( email.length>0 && email.indexOf("@")!=(-1) ){
		email = email.split("@")[0];
		if((pass.toUpperCase()).indexOf(email.toUpperCase())!=(-1)){
			$("#valUsr").removeClass("descVal");
			$("#valUsr").removeClass("descValGreen");
			$("#valUsr").removeClass("descValOrange");
			$("#valUsr").addClass("descValOrange");
		}else{
			$("#valUsr").removeClass("descVal");
			$("#valUsr").removeClass("descValGreen");
			$("#valUsr").removeClass("descValOrange");
			$("#valUsr").addClass("descValGreen");
		}
	}
	
	if(name.length>0){
		if((pass.toUpperCase()).indexOf(name.toUpperCase())!=(-1)){
			$("#valName").removeClass("descVal");
			$("#valName").removeClass("descValGreen");
			$("#valName").removeClass("descValOrange");
			$("#valName").addClass("descValOrange");
		}else{
			$("#valName").removeClass("descVal");
			$("#valName").removeClass("descValGreen");
			$("#valName").removeClass("descValOrange");
			$("#valName").addClass("descValGreen");
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
	}else if((pass.toUpperCase()).indexOf(inst2.toUpperCase())!=(-1)){
		
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
	}else if((pass.toUpperCase()).indexOf(inst3.toUpperCase())!=(-1)){
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
	}else if((pass.toUpperCase()).indexOf(inst4.toUpperCase())!=(-1)){
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValOrange");
	}else{
		$("#valInst").removeClass("descVal");
		$("#valInst").removeClass("descValGreen");
		$("#valInst").removeClass("descValOrange");
		$("#valInst").addClass("descValGreen");
	}
	
	valCaracteresColor();
}

function valCaracteresColor()
{
	
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
		}else{
			$("#valRepChar").removeClass("descVal");
			$("#valRepChar").removeClass("descValGreen");
			$("#valRepChar").removeClass("descValOrange");
			$("#valRepChar").addClass("descValGreen");
		}
		if(conV==0){
			$("#valVoc").removeClass("descVal");
			$("#valVoc").removeClass("descValGreen");
			$("#valVoc").removeClass("descValOrange");
			$("#valVoc").addClass("descValOrange");
		}else{
			$("#valVoc").removeClass("descVal");
			$("#valVoc").removeClass("descValGreen");
			$("#valVoc").removeClass("descValOrange");
			$("#valVoc").addClass("descValGreen");
		}
		if(conC==0||conC=='0'){
			$("#valCnsnt").removeClass("descVal");
			$("#valCnsnt").removeClass("descValGreen");
			$("#valCnsnt").removeClass("descValOrange");
			$("#valCnsnt").addClass("descValOrange");
			
		}else{
			
			$("#valCnsnt").removeClass("descVal");
			$("#valCnsnt").removeClass("descValGreen");
			$("#valCnsnt").removeClass("descValOrange");
			$("#valCnsnt").addClass("descValGreen");
		}
		if(conN==0){
			$("#valNum").removeClass("descVal");
			$("#valNum").removeClass("descValGreen");
			$("#valNum").removeClass("descValOrange");
			$("#valNum").addClass("descValOrange");
		}else{
			$("#valNum").removeClass("descVal");
			$("#valNum").removeClass("descValGreen");
			$("#valNum").removeClass("descValOrange");
			$("#valNum").addClass("descValGreen");
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
					break;
					
				}else{
					
					$("#valCharCon").removeClass("descVal");
					$("#valCharCon").removeClass("descValGreen");
					$("#valCharCon").removeClass("descValOrange");
					$("#valCharCon").addClass("descValGreen");
					
				}
		
				cadena = cadena.substring(1,3);
		 }
		}
	}else{
		
		$("#valCharCon").removeClass("descVal");
		$("#valCharCon").removeClass("descValGreen");
		$("#valCharCon").removeClass("descValOrange");
		$("#valCharCon").addClass("descValGreen");
		
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
						break;
					
					}else{
						$("#valNumCon").removeClass("descVal");
						$("#valNumCon").removeClass("descValGreen");
						$("#valNumCon").removeClass("descValOrange");
						$("#valNumCon").addClass("descValGreen");
						
					}
			
					cadena = cadena.substring(1,3);
			 }
		}
	}else{
		$("#valNumCon").removeClass("descVal");
		$("#valNumCon").removeClass("descValGreen");
		$("#valNumCon").removeClass("descValOrange");
		$("#valNumCon").addClass("descValGreen");
	}
	return;
}

function validaPass(){
	
	var pass = $("#pass").val();
	var name = $("#name").val();
	var email = $("#email").val();
	var inst1 = "kubo";
	var inst2 = "cubo";
	var inst3 = "kuvo";
	var inst4 = "cuvo";
	
	if(pass.length==0) {
		return false;
	}
	if(pass.length<8){
	
	
		 if ($(".nombre_correo_contrasenia").is(':visible')) {
				alertify.error("La contraseña debe contener al menos 8 caracteres");
			 }else {
				alert("La contraseña debe contener al menos 8 caracteres");
			 }
			return false;
	}
	
	if(pass.length>12){
		alert("La contraseña debe contener 12 caracteres como máximo");
		return false;
	}
	
	if( email.length>0 && email.indexOf("@")!=(-1) ){
		email = email.split("@")[0];
		if((pass.toUpperCase()).indexOf(email.toUpperCase())!=(-1)){
			alert("La contraseña no debe contener tu nombre de usuario del correo electrónico");
			return false;
		}
	}
	
	if((pass.toUpperCase()).indexOf(name.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener tu nombre");
		return false;
	}
	if((name.toUpperCase()).indexOf(pass.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener tu nombre");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst1.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst2.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst3.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener el nombre de la institución");
		return false;
	}
	if((pass.toUpperCase()).indexOf(inst4.toUpperCase())!=(-1)){
		alert("La contraseña no debe contener el nombre de la institución");
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
					alert("la contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaNUM.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener números consecutivos");
					return false;
				}
				if(cadenaQWE.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaASD.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaZXC.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				
				
				if(cadenaNUMInv.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaQWEInv.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaASDInv.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				if(cadenaZXCInv.indexOf(cadena)!=(-1)){
					alert("La contraseña no puede contener caracteres consecutivos");
					return false;
				}
				cadena = cadena.substring(1,3);
				
			}
			cadenaTemp += s.charAt(i);
		}
		if(b){
			alert("La contraseña no puede repetir un caracter más de 2 veces consecutivas ");
			return false;
		}
		if(conV==0){
			alert("La contraseña debe contener al menos una vocal "+cadenaTemp);
			return false;
		}
		if(conC==0){
			alert("La contraseña debe contener al menos una consonante "+cadenaTemp);
			return false;
		}
		if(conN==0){
			alert("La contraseña debe contener al menos un número "+cadenaTemp);
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
		elemento.removeClass("requiredClass");
	}
	else{
		elemento.addClass("requiredClass");	
	}
	
}

function validaNewPass(){
	if($("#passAnt").val()==$("#pass").val()){
		
		
		 if ($(".nombre_correo_contrasenia").is(':visible')) {
			 	alertify.error("La contraseña nueva no puede ser igual que la anterior ");
			 }else {
				 alert("La contraseña nueva no puede ser igual que la anterior ");
			 }
		
		return false;
	}else
		return true;
}

function validaChangePass(){
	if(validaPass()&&validaNewPass()){
		displayMessageProcessing('msgprocessing',false);
		return true;
	}else{
		return false;
	}
} 