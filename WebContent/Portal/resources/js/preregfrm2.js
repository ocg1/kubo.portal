function inicializaValores2(){
	
	$('.pass_help').each(function() {
		$(this).keyup(function(event){
			event.preventDefault(); 
			validaPassColor2();
			event.preventDefault();
			textopass2();
	});	

		
		
	});
	
	$('.pass_help').each(function() {
		$(this).blur(function(event){
			$('#passHelp').slideUp();	
			validaPass2();
		});
	});
	despliegaPass2 ();
}

function despliegaPass2 (){
	$('.pass_help').each(function() {
		$(this).focus(function(event){
			 if ($(this).val().length==0 ) { 
			$(".item").removeClass("descValGreen");
			$(".item").removeClass("descValOrange");
			$(".item").addClass("descVal");
			 }
			var width = $(window).width();	
			 if (width >= 801) { 
		
			var t = $(this).offset().top + 37 ;
			var l = $(this).offset().left ;
			$('#passHelp').css({'top' :  t+'px', 'left' : l+'px'});
			$('#passHelp').slideDown();	
			
			event.preventDefault(); 
			 }
		});
	});
	}


function textopass2 (){
	 if ($("#message  .descValGreen").length == $("#message .item").length) {
			    $(".crea_contrasena2 span").remove();
	   			$(".crea_contrasena2").append($("<span class='cumple'>cumple la contraseña <small>?</small> </div>"));
				
			
				}else {
				 $(".crea_contrasena2 span").remove();
	   			$(".crea_contrasena2").append($("<span class='no_cumple'>no cumple la contraseña <small>?</small> </div>"));
			}
	}


function validaPassColor2(){
	var pass = "";
	var name = "";
	var email = "";
	
	if ($("#light8").is(':visible')) {
		pass = $("#pass2").val();
	    name = $("#name2").val();
	    email = $("#email3").val();
	}
	if($(".recuperar").is(':visible')) {
		pass = $("#pass3").val();
		name = $("#name4").val();
		email = $("#email5").val();
	}
	
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
	
	valCaracteresColortwo();
}

function valCaracteresColortwo(){
	var s = "";
	
	if ($("#light8").is(':visible')) {
		s = $("#pass2").val();
	   
	}
	if($(".recuperar").is(':visible')) {
		s = $("#pass3").val();

	}
	
	
		var x = 0;
		var conV = 0;
		var conC = 0;
		var conN = 0;
		var b = false;
		//var cadena = "";
		
		//var cadenaTemp="";
		
		if(s.length>0){
		
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


function validaPass2(){

	var pass = "";
	var name = "";
	var email = "";
	
	if ($("#light8").is(':visible')) {
		pass = $("#pass2").val();
	    name = $("#name2").val();
	    email = $("#email3").val();
	}
	if($(".recuperar").is(':visible')) {
		pass = $("#pass3").val();
		name = $("#name4").val();
		email = $("#email5").val();
	}
	
	
	var inst1 = "kubo";
	var inst2 = "cubo";
	var inst3 = "kuvo";
	var inst4 = "cuvo";
	
	if(pass.length==0) {
		return false;
	}
	if(pass.length<8){
		console.log("La contraseña debe contener al menos 8 caracteres");
		return false;
	}
	
	if(pass.length>12){
		console.log("La contraseña debe contener 12 caracteres como máximo");
		return false;
	}
	
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
	
	return valCaracterestwo();
	
}

function valCaracterestwo(){
	
	var s = "";
	
	if ($("#light8").is(':visible')) {
		s = $("#pass2").val();
	}
	if($(".recuperar").is(':visible')) {
		s = $("#pass3").val();
	}
	
	
	if(s.length>0){
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


function validaNewPass(){
	if($("#passAnt").val()==$("#pass3").val()){
		alert("La contraseña nueva no puede ser igual que la anterior ");
		return false;
	}else
		return true;
}

function validaChangePass(){
	if(validaPass2()&&validaNewPass()){
		
		return true;
	}else{
		return false;
	}
} 


