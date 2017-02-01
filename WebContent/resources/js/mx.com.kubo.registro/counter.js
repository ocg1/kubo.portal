console.log("mx.com.kubo.registro/counter.js");

var menuOrder;
var AnswRadio;
var radio;
var arrayNames;
var sel;
var text;	
var textarea;
var isAnswered;	
var checkList;
var counter_total;
var porcen;

var print_log_ENABLED = true;

function fieldCount()
{

	var startTime = new Date();
	var startMsec = startTime.getTime();
	var flagDocument = false;
	
	if($(".clsFrmIdentification").length ){
		flagDocument = true;
	}
	
	
	
	console.log( );
	
	console.log("REVISION: ");
	console.log("fieldCount(): ");
	conjuntoCombos();
	var campos = null;
	var flagProfileInvestment =  false;
	if($(".IncomeExpense").is(":visible")) {
		menuOrder = 5;
	}
	else {
	if(document.frmBasicos) 
	{	
		campos = document.frmBasicos.elements;
		menuOrder = 1;
		
	} else if(document.frmHistCred) {
		
		campos = document.frmHistCred.elements;
		menuOrder = 2;
		
	} else if(document.frmMoreInfo) {
		
		campos = document.frmMoreInfo.elements;
		menuOrder = 3;
		
	}else if(document.frm_info_basic_loan){
		campos = document.frm_info_basic_loan.elements;
		menuOrder = 4;
		
	}else if($(".IncomeExpense").is(":visible")){
		menuOrder = 5;
	}
	else if(document.frmCuentaClabe){
		
		campos = document.frmCuentaClabe.elements;
		menuOrder = 6;
		
	}else if( flagDocument || document.credencial_elector_INFO || document.frm_questPLD || document.frm_credFm2 || document.frm_compActEcom || document.frm_loan || document.frm_acredProBus || document.frm_compDomi ||  document.frm_campoNuevo ){
		console.log("DOCUMENTACION: ");
		campos = $("#general").find(".validatorClass");
		menuOrder = 7;
		
	} else if(document.frm_beneficiaries){
		
		campos = document.frm_beneficiaries.elements;
		menuOrder = 8;
		
	}else if(document.frm_ImoreInfo){
		
		campos = document.frm_ImoreInfo.elements;
		menuOrder = 9;
		
	}else if(document.investmentProfile) {
		campos = null;
		flagProfileInvestment= true;
	}
	}
	
	console.log("> menuOrder = " + menuOrder);	
	
	if(campos != null || menuOrder == 5)
	{
		//console.log("> menuOrder = " + menuOrder);		
		
		if( menuOrder != 5 ){
			counter(campos);
		
		}else{
			porcentajeIngresosGastos ();
		}

	}
			
	if(flagProfileInvestment)
	{
		counterProfile();
	}
	
	
	var startTime2 = new Date() ;
	var elapsed = (startTime2.getTime() - startMsec);
	
	console.log( "TERMINA FIELDCOUNT 2 : " + elapsed + " milisegundos" );
	
	
	return true;	
}

function counter(campos)
{		
	console.log("counter(): " + campos.length);
	console.log("> print_log_ENABLED002 = " + print_log_ENABLED);
	
	init_contadores();
	
	contar_inputs(campos);
	contar_radios();
	
	print_conteo_total();
	
	
	if(menuOrder==8){
		
		if (counter_total > 60 ){
			
			var suma = 0;
			
			$("#lstPercBenefi").find('.persentage').each(function() 
					{
						suma = suma + Number( $(this).val() );		
					});
			
			
			if( suma != 100 ){
				counter_total = counter_total - 2;
			}
			
		}
		
	}
	
	if(counter_total > 0)
	{
		actualizar_porcentaje(porcen);	
	}		
}

function init_contadores()
{
	arrayNames = null;
	//menuOrder  = 0;
	AnswRadio  = 0;
	radio      = 0;	
	sel   	   = 0;
	text  	   = 0;	
	textarea   = 0;
	isAnswered = 0;	
	checkList  = 0;
	counter_total = 0;
}

function contar_inputs(campos)
{		
	console.log("contar_inputs()");
	
	for(var i = 0; i < campos.length; i++)
	{					
		if(campos[i].tagName.toUpperCase() == 'SELECT' 
	  && $(campos[i]).hasClass("validatorClass") 
	  && !$(campos[i]).hasClass("persentage") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				sel = parseInt(sel) + 1;
				
				if(campos[i].value != null && (campos[i].value != "0" && campos[i].value != ""))
				{
					isAnswered = parseInt(isAnswered) + 1;
					
					if(print_log_ENABLED)
					{
						console.log("> select " + isAnswered);
					}
					
				} else if( $(campos[i]).attr("id") == 'nacionalidad' && campos[i].value == "0" ){
					isAnswered = parseInt(isAnswered) + 1;
				}
			}
		}
			
		if(campos[i].type=='text' && $(campos[i]).hasClass("validatorClass") && !$(campos[i]).hasClass("watermark") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				text = parseInt(text) + 1;
				
				if(campos[i].value!=null &&  campos[i].value!="" && campos[i].value!="0.00")
				{
					isAnswered = parseInt(isAnswered) + 1;
					
					if(print_log_ENABLED)
					{
						console.log("> text " + isAnswered);
					}
				}
			}
		}
		
		if(campos[i].type=='text' && $(campos[i]).hasClass("watermark") )
		{
			if(!$(campos[i]).is(":hidden"))
			{
				text = parseInt(text) + 1;
				
				if(campos[i].value!=null &&  campos[i].value!="" && campos[i].value!="Escribe aquí el nombre de tu colonia"  && campos[i].value!="0.00")
				{
					isAnswered = parseInt(isAnswered) + 1;
					
					if(print_log_ENABLED)
					{
						console.log(campos[i].value);
						console.log("\t text " + isAnswered);
					}
				}
			}
		}
		
		if(campos[i].tagName.toUpperCase()=='TEXTAREA' && $(campos[i]).hasClass("validatorClass"))
		{
			if(!$(campos[i]).is(":hidden"))
			{	
				textarea = parseInt(textarea) + 1;
				
				if(campos[i].value!=null && (campos[i].value!="0" && campos[i].value!=""))
				{
					isAnswered = parseInt(isAnswered)+1;
					
					if(print_log_ENABLED)
					{
						console.log("\t TEXTAREA " + isAnswered);
					}
				}
			}
		}
			
		if( $(campos[i]).hasClass("ui-autocomplete-input") && $(campos[i]).parent().hasClass("validatorClass") )
		{
			
			if(!$(campos[i]).is(":hidden"))
			{
				
				text = parseInt(text) + 1;
				
				if(campos[i].value!=null &&  campos[i].value!="" && campos[i].value!="0.00")
				{
					
					isAnswered = parseInt(isAnswered)+1;
					
					if(print_log_ENABLED)
					{
						console.log("\t TEXTAREA " + isAnswered);
					}
				}				
			}			
		}

		if(campos[i].tagName.toUpperCase()=='IMG' && $(campos[i]).hasClass("validatorClass"))
		{
			
			console.log("\t \t\t\t\t\t************************* IMG ***************************** \t\t\t\t\t\t\t\t\t\t" );
			
			if($(campos[i]).is(":hidden"))	
			{
				checkList= parseInt(checkList) + 1;
				
				if(print_log_ENABLED)
				{
					console.log("\t hidden " + isAnswered + " ID:  " + $(campos[i]).attr("id") );
				}
				
			} else {
				checkList= parseInt(checkList)+1;
				isAnswered = parseInt(isAnswered)+1;
	
				if($(campos[i]).closest(".ingresosTitulo").length && $("#rubroIngresos .btn_comprobante").length >= 1){
					
						checkList = -1;
						isAnswered =-1;
				

				}
			
				/*
				if($(".btn_comprobante").length >= 1){
					checkList = -1;
					console.log("falta archivo");
				}
				*/
				if(print_log_ENABLED)
				{
					console.log("\t IMG " + isAnswered + " ID:  " + $(campos[i]).attr("id") );
				}
			}
		}
			
		if(menuOrder==8 && campos[i].tagName.toUpperCase()=='SELECT' && $(campos[i]).hasClass("persentage")  )
		{
			sel = parseInt(sel) + 1;
			
			if(percentageFlag)
			{
				isAnswered = parseInt(isAnswered)+1;
				
				if(print_log_ENABLED)
				{
					console.log("\t select menuorder8 " + isAnswered);
				}
			}
		}		
	}	
}

function contar_radios()
{	
	console.log("contar_radios()");
	
	radio = 0;
	
	arrayNames = null;
	
	$(":radio").each(function()
	{
		var validator_DISABLED = $(this).closest("table").hasClass("validator_DISABLED");
		
		if(!$(this).is(":hidden") && !validator_DISABLED)
		{
			if($(this).is(':checked'))
			{
				AnswRadio = (parseInt(AnswRadio) + 1);
				
			} else {
				
				radio = parseInt(radio) + 1;
			}
			
			var thFlag = false;
			
			if(arrayNames != null)
			{
				var thisArray = arrayNames.split("::");
				
				for(var i = 0; i<thisArray.length; i++ )
				{
					if(thisArray[i] ==  $(this).attr('name'))
					{
						thFlag = true;
						break;
					}
				}
				
				if(!thFlag)
				{
					arrayNames += $(this).attr('name')+"::";
				}
				
			} else {
				
				arrayNames = $(this).attr('name')+"::";
			}
			
			
			if( !thFlag )
			{
				//radioNum = parseInt(radioNum) +1;
				radio = parseInt(radio) +1;
			}
		}
	});

	//radio =(parseInt(radioNum)/2);
	isAnswered +=  ( parseInt( AnswRadio ) );
	
	if( arrayNames != null )
	{		
		radio = parseInt( arrayNames.split("::").length );
		
	} else {
		
		radio = 1;
	}
	
	radio = radio - 1;
	
	if(print_log_ENABLED)
	{
		console.log("> radio_names   = " + arrayNames);
		console.log("> radio_pairs   = " + radio);
		console.log("> radio_checked = " + isAnswered);
	}
}



function print_conteo_total()
{
	counter_total 	= parseInt(sel)
				+ parseInt(text)
				+ parseInt(radio)
				+ parseInt(textarea)
				+ parseInt(checkList);

	porcen 	= ((parseInt(isAnswered) * 100) / parseInt(counter_total));
	
	var str = "\n> isAnswered: " + isAnswered
			+ "\n> sel: "   + sel
			+ "\n> text: "  + text
			+ "\n> radio: " + radio
			+ "\n> textarea: "  + textarea
			+ "\n> checkList: " + checkList
			+ "\n> total: "  + counter_total
			+ "\n> porcen: " + porcen;
			
//	if(print_log_ENABLED)
//	{
		console.log(str);
//	}
}

function actualizar_porcentaje(porcentaje)
{
	console.log("actualizar_porcentaje(): " + porcentaje);
	
	$("#changeButtons\\:porcBasic").val(porcentaje).blur();	
	$("#changeButtons\\:countPorcent").click();
}

function counterProfile()
{	

	var checkHorizonte = false;
	var dondeInviertes = false;
	var otroLugarInversion = false;
	var porcentaje = 0;
	
		   var campos =  $("#tablaHorizonte input");
			
		  	var campos_check = campos.filter(function () {
			    return $(this).prop("checked");  
			}).length == 0;
		  	
			if(campos_check == true) {
				 checkHorizonte = false;
			}else{
				checkHorizonte = true;
			}
	
	 if($("#dondeInviertes option:selected").val() != 0 ){
		 dondeInviertes = true;
	 }else{
		 dondeInviertes = false;
	 }
	 
	 if($("#otroLugarInversion").is(":visible")){
		 if(!$("#otroLugarInversion").val() ==  ""){
			 otroLugarInversion = true;
		 }else{
			 otroLugarInversion = false;
		 }
	 }else{
		 otroLugarInversion = true;
	 }
	 
	 
	 console.log("dondeInviertes"+$("#dondeInviertes option:selected").val());
	 if(checkHorizonte && dondeInviertes && otroLugarInversion ){
		porcentaje = 100;
		
	 }
	 /*
	var elements = null;
	var count = 0;
	var total = 7;
	var flagH = false; 
	
	for(var x = 1 ; x <= total; x++)
	{
		elements = $(".field" + x);
		
		for( var i = 0 ; i < elements.length ; i++  )
		{
			if(!$(elements[i]).is(":hidden"))
			{
				if(elements[i].checked)
				{
					count++;
					break;
				}
				
			} else {
				
				flagH = true;
			}
		}
	}
	
	if( flagH )
	{
		total = total -1;
	}
			
	var porcentaje = (count*100) / total;
	
	// alert("count: "+count+"  total: "+total+"  porcen: "+porcen);
	*/
	$("#changeButtons\\:porcBasic").val(porcentaje).blur();	
	$("#changeButtons\\:countPorcent").click();
}

