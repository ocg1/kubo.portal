console.log("mx.com.kubo.inversionista/registro/beneficiario.js");

Beneficiario.init_validator_class = function()
{
	console.log("Beneficiario.init_validator_class(): INIT");
	
	$(".validatorClass").bind("change blur", function(event) 
	{	
		fieldCount();
		event.preventDefault();
	});
	
	$("#nextInvBen").click(function()
	{
		fieldCount();
		$("#nextToMoreInfo").click();
	});	
	
	fieldCount();
	//borrar_beneficiario ();
	
	setTimeout(function()
	{	
		$("#lstPercBenefi td input").val("0");
		$(".distribuir_benef").removeClass("active");
		$(".distribuir_benef").html("Distribuir a beneficiarios");
				
		$("#lstPercBenefi td input").each(function()
		{			
			$(this).blur();
		});
		
		
		Beneficiario.verificPersentage();
		
	},  500);
	 googleEvents ('registro-inversion', 'clic agregar beneficiario', 'boton agregar beneficiario');
	 
	 console.log("Beneficiario.init_validator_class(): OK");
	   facebook_events ('cilcIrAAgregarBeneficiarios' );
};

Beneficiario.oncompleAddBeneficiaries = function ()
{
	console.log("Beneficiario.oncompleAddBeneficiaries()");
	
	Beneficiario.init_validator_class();
	Beneficiario.verificPersentage();
	
	fieldCount();
	
	 borrar_beneficiario(); 
};

Beneficiario.verificPersentage = function (elmnt)
{
	console.log("Beneficiario.verificPersentage: " + elmnt);
	
	var suma = 0;
	//var flag = false;
	var str  = "";
	
	//alert(elmnt +"  -  "+$("#lstPercBenefi").find('.persentage').size());
	
	/* 
	 * $( document.body ).append( $( "<div>" ) );
	 * var n = $( "div" ).length;
	*/
	
	var  elmnP = $("#lstPercBenefi").find('.persentage');
	
	if(elmnt!=undefined && elmnP.size() == 2)
	{
		var i1 = 0;
		var v1 = 0;
		var v2 = 0;
		var precision;
		
		$("#lstPercBenefi").find('.persentage').each(function() 
		{
			if(i1 == 0)
			{
				v1=Number($(this).val());
			}
				
			if(i1 == 1)
			{
				v2=Number($(this).val());
			}
				
			
			i1++;
		});
		
		var val1 = 0;
		
		if(elmnt==0)
		{
			val1 = v1;
		}else if(elmnt==1){
			val1 = v2;
		}
		
		var val2 = 100 - parseInt(val1);
		i1 = 0;
		var flag = true;
		
		$("#lstPercBenefi").find('.persentage').each(function() 
		{
			
			if(parseInt(elmnt)!=0 && i1==0 && flag)
			{
				$(this).val(val2);
				$(this).blur();
				flag = false;
				
			} else if(parseInt(elmnt)!=1 && i1==1 && flag){
				flag = false;
				$(this).val(val2);
				$(this).blur();
			}
			
			i1++;
		});		
	}
	
	$("#lstPercBenefi").find('.persentage').each(function() 
	{
		var percentaje = $(this).val();
		suma=suma+Number(percentaje);	
	
		precision = suma.toPrecision(4);
		console.log("suma"+suma+"percentaje"+percentaje);
		console.log("precision");
	});
	
	if(precision < 100)
	{
		flag=false;
		console.log("le falta porcentaje%");
		
		if(precision >= 1){
	
			$(".mensajeErroresPorcentaje span").html("le falta porcentaje");
		}
		
		
		
	} else if(precision > 100) {

		console.log("Se esta excediendo el porcentaje de 100%");
	
		flag = false;
		
	} else {
		
		flag=true;
	}
	
	if(!flag)
	{
		$(".mensajeErroresPorcentaje").show();
		$(".mensajeErroresPorcentaje input").show();
		$(".mensajeErroresPorcentaje span").html("La suma de los porcentajes asignados a tus beneficiarios, no debe ser mayor o menor al 100%.");
		str = "<span class='totalPer' style='color:#FF0000;'>"+precision+"%</span>";
		percentageFlag = false;
		$("#totalPerc").html(str);

		
	} else {
		

			$(".mensajeErroresPorcentaje").hide();
			$(".mensajeErroresPorcentaje input").hide();
			$(".mensajeErroresPorcentaje span").html("");
			
			str = "<span class='totalPer' style='color:#439539;'>"+precision+"%</span>";
			percentageFlag = true;
			$("#totalPerc").html(str);
			
		
		

	}
	
	
	
	porcentajeReset2 ();
	 if(precision == 100) {
		setTimeout(function(){ 
			asignadoTodosBeneficiarios(str, precision);
		 },500);
	 }
	fieldCount();
	return true;
	
};

function porcentajeReset (){
	$(".porcentaje-bene").each(function(n){	
		var id =  "porcentaje-bene"+n;	
		$(this).attr("id", id)
		$(this).attr("onblur", "porcentajeReply('#"+id+"')");	 
	});
}

function porcentajeReply(input) {
	 var valorInputMirrorVal = $(input).val()
	 var valorInputMirrorId = $(input).attr("id");
	 var lastChar = valorInputMirrorId.substr(valorInputMirrorId.length - 1);
	 if(parseInt(valorInputMirrorVal) >= 0 && parseInt(valorInputMirrorVal) <= 100 ){
		 console.log("lastChar"+lastChar);
		 $("#lstPercBenefi .persentage:eq("+lastChar+")").val(valorInputMirrorVal);
		 $("#lstPercBenefi .persentage:eq("+lastChar+")").change();
		 
		 
	 }else{
		 $(input).val("");
		 porcentajeReset2 ();
	 }
	
}

function porcentajeReset2 () {
	 setTimeout(function(){
			$("#lstPercBenefi .persentage").each(function(n){	
					var valorCamposReales = $(this).val();
					$("#porcentaje-bene"+n).val(valorCamposReales);
			});
	 }, 300);
}

function isNumberKey2(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57)){
	   return false;
   }
    

   return true;
}



function asignadoTodosBeneficiarios(str, precision) {
	var campos =  $(".porcentaje-bene");
	
	console.log ("porcentaje-bene"+$(".porcentaje-bene:eq(0)").val());
	console.log ("porcentaje-bene"+$(".porcentaje-bene:eq(1)").val());
	console.log ("porcentaje-bene"+$(".porcentaje-bene:eq(2)").val());
	var campos_vacios = campos.filter(function () {
    return $(this).val() == 0;
	}).length == 0;
	
	if(campos_vacios == false) {
		$(".mensajeErroresPorcentaje").show();
		$(".mensajeErroresPorcentaje input").show();
		$(".mensajeErroresPorcentaje span").html("Falta asignar porcentaje a alguno de tus beneficiarios.");
		
		str = "<span class='totalPer' style='color:#FF0000;'>"+precision+"%</span>";
		$("#totalPerc").html(str);

		console.log("no todos tienen valor me a 0")
	}else {
		console.log("todos tienen valor mayor a 0")
		fieldCount();
		
	}
}

	
	

	
	
Beneficiario.validateBirthDay = function (element)
{
	var parent = $(element).parent().parent('.myBirthday'); 
	
	$(parent.children()).each(function()
	{	
		if($(this).children().hasClass("elementStlSelDay"))
		{
			dayStr = $(this).children().val();
		}
			
		if($(this).children().hasClass("elementStlSelMonth"))
		{
			thisMonth=$(this).children().val();
		}
			
		if($(this).children().hasClass("elementStlSelYear"))
		{
			yearStr=$(this).children().val();
		}			 			
	});
};

/*
Beneficiario.callback_vivienda_TOKEN = function(xhr, status, args)
{	
	var beneficiarie_id  = args.beneficiarie_id;
	var domicilio_CHANGED = args.domicilio_CHANGED;
	
	console.log("Vivienda.callback_vivienda_TOKEN():");
	console.log(" > beneficiarie_id  = " + beneficiarie_id);
	console.log(" > domicilio_CHANGED = " + domicilio_CHANGED);
	
	Beneficiario.setMismo_domicilio_ENABLED(vivienda_CHANGED, beneficiarie_id);
	
};
*/

Beneficiario.setMismo_domicilio_ENABLED = function(vivienda_CHANGED, beneficiarie_id)
{
	var log_INFO = "Beneficiario.same_address(): " + vivienda_CHANGED;
	
	console.log(log_INFO);
	
	var mismo_domicilio_ID = "#mismo_domicilio_" + beneficiarie_id;

	if(vivienda_CHANGED)
	{			
		$(mismo_domicilio_ID).find("input:radio[value~='N']").attr("checked","checked").trigger("click");
		
	} else {
		
		$(mismo_domicilio_ID).find("input:radio[value~='S']").attr("checked","checked").trigger("click");
	}
};

Beneficiario.callback_mismo_domicilio = function(xhr, status, args)
{
	var beneficiarie_id = args.beneficiarie_id;
	var same_address    = args.same_address;	
	
	console.log("Beneficiario.same_address_oncomplete():");
	console.log(" > beneficiarie_id = " + beneficiarie_id);
	console.log(" > same_address    = " + same_address);
	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-persist-beneficiarie").trigger("click");
	conjuntoCombos();
};

Beneficiario.persist_beneficiarie_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	var save_OK    = args.save_OK;	
	
	console.log("Beneficiario.persist_beneficiarie_oncomplete():");
	console.log(" > update_OK = " + update_OK);
	console.log(" > save_OK   = " + save_OK);
	
	$("div#remote-beneficiarios").find("a.init-lista-beneficiarios").trigger("click");
};

Beneficiario.lista_beneficiarios_oncomplete = function(xhr, status, args)
{	
	var sizeBenefic = args.sizeBenefic;
	
	console.log("Beneficiario.lista_beneficiarios_oncomplete(): " + sizeBenefic);
	
	//Beneficiario.init_validator_class();
	
	console.log("Beneficiario.lista_beneficiarios_oncomplete(): OK");
};