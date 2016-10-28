console.log("perfil_inversionista.js");


function tu_perfil_es() {
	$(".tabla_perfil:not('.riesgo') input, .table_perfil_horizontal input").off("click");
	$(".tabla_perfil:not('.riesgo') input, .table_perfil_horizontal input").click(function () {
		 tu_perfil_es2();
	});
}
function tu_perfil_es2() {
	setTimeout(function(){	
		var  noRisk_no = $(".noRisk_no");
		var  noRisk_yes = $(".noRisk_yes");
		if($(".tu_perfil_es").text().indexOf("Tradicional") == (-1)) {
			if (noRisk_yes.prop('checked')) {
				noRisk_yes.prop('checked', false); 
				
				
			}
			if (noRisk_no.prop('checked')) {
			    console.log("chequeado"); 	
			}else {
				 console.log("no chequeado");
				 noRisk_no.prop('checked', true); 
				$("#dvResKuboImpulso").fadeOut();
				
			}
			
		}else {
			console.log("no es Tradicional");
			if (noRisk_no.prop('checked')) {
				 noRisk_no.prop('checked', false); 
				 
			}else {
				 console.log("no chequeado");
			}
		}
		console.log("tu perfil es"+$(".tu_perfil_es").text());
	
	 }, 500);

}