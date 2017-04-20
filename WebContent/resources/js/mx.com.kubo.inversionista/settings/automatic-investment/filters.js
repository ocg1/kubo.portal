console.log("filters.js");

var scrollea = false;

function buildQueryFilter()
{
	console.log("Filters.buildQueryFilter(): ");
	
	scrollea = true;
	
	var cadStatus = getStringStatus();
	var cadRisk   = getStringRisk();
	var cadTerm   = getCadForTermChk();
	
	var cadgen = "";
	
	if($("#chk1").is(':checked') || $("#gender_Hombre").hasClass("itemCheck") ) 
	{ 
		 cadgen += "1";
	}
			
	 if($("#chk2").is(':checked') || $("#gender_Mujer").hasClass("itemCheck")) 
	 { 
		 
		 if( cadgen.length>0 )
		 {
				cadgen+=",";
		 }
		 
		 cadgen+="2";
	 }
	 
	 $('#cadena3').val(cadStatus!=""?cadStatus.substring(0,cadStatus.length-1):"");
	 $('#cadena1').val(cadRisk!=""?cadRisk.substring(0,cadRisk.length-1):"");
	 $('#cadena2').val(cadTerm!=""?cadTerm:"");
	 $('#cadenaGender').val(cadgen);
	 
	 var strCadenaDest ="";
	 var item_num_destiny = 0;
	 
	 $(".clsItemDest").each(function()
	{			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_destiny != 0 )
				{
					strCadenaDest+=",";
				}
				
				var destinyChar = $(this).attr("id");
				
				strCadenaDest+= destinyChar.replace("purpose_","");
				item_num_destiny++;			
			}				
	});
		
		$("#destiny_str").val( strCadenaDest );
		$("#destiny_str_val").val( strCadenaDest );
	 
	 if(cadRisk.length>0)
	 {
			if(cadStatus.length>0 || cadTerm.length>0 || cadgen.length>0  )
			{
				$('#updateByFiltering').click();				
			}
	 }

}

function getStringStatus()
{
	console.log("Filters.getStringStatus(): ");
	
	var cadStatus= "";
	
	$(".statusclss").each(function( index )
	{
		
		if($(this).is(":checked"))
		{
			var str = $(this).attr("id").split("_")[1];
			cadStatus = cadStatus+"'"+str+"',";
		}
		
	});
	
	return cadStatus;	
}

function getStringRisk()
{		
	console.log("Filters.getStringRisk(): ");
	
	var cadenaChk = "";
	
	if( $("input[name=flagRisk][value=0]'").is(":checked")  )
	{			
		if($("#chkA").is(":checked"))
		{
			cadenaChk = cadenaChk+"'A',";
		}
		
		if($("#chkB").is(":checked"))
		{
			cadenaChk = cadenaChk+"'B',";
		}
		
		if($("#chkC").is(":checked"))
		{
			cadenaChk = cadenaChk+"'C',";
		}
		
		if($("#chkD").is(":checked"))
		{
			cadenaChk = cadenaChk+"'D',";
		}
		
		if($("#chkE").is(":checked"))
		{
			cadenaChk = cadenaChk+"'E',";
		}
		
		if($("#chkF").is(":checked"))
		{
			cadenaChk = cadenaChk+"'F',";
		}
		
		if($("#chkG").is(":checked"))
		{
			cadenaChk = cadenaChk+"'G',";
		}
		
	} else {
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItem").each(function()
		{			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 )
				{
					strCadena+=",";
				}
				
				strCadena+= "'" + $(this).attr("id") + "' ";
				item_num_risk++;			
			}				
		});
		
		if( strCadena.trim().length == 0 || item_num_risk == 0 )
		{
			alert( "Seleccione al menos un tipo de riesgo ");
			cadenaChk = strCadena = "";
		}
		
		cadenaChk = strCadena;
		
	}
		
	return 	cadenaChk;
}

function getCadForTermChk()
{	
	console.log("Filters.getCadForTermChk(): ");
	
	var itemActualInv = 0;
	var maxIdSel = "";
	
	$(".clsTerm").each(function()
	{		
		if( $(this).hasClass("itemCheck") )
		{			
			var this_id_2 = $(this).attr("id");
			
			var this_sec_2 = this_id_2.split("_")[1];
			
			if(parseInt(this_sec_2) > parseInt( itemActualInv ) )
			{				
				//console.log('idSel: '+this_id_2);
				itemActualInv = parseInt(this_sec_2);
				// maxIdSel = this_id_2.split("_")[0];
				
				maxIdSel = this_id_2;
				
				//console.log('idProcesado: '+maxIdSel);
				
			}								
		}
	});
	
	return maxIdSel;
}

function filtros()
{
	console.log("Filters.filtros(): ");
	
	$(".scores").html("");
	
	$("#sidebar #filter_by_type .ui-checkbox-filter").each(function() 
	{
	    tipo_score = $(this).attr("id").replace("chk", "");
	    
		if($(this).is(":checked")) 
		{ 
			$(".scores").append(tipo_score+", ");
		}
	});
	
	if ($(".scores").is(':empty'))
	{
		$(".scores").html("no definido");
	} 
	
	$("#sidebar #filter_by_funding :radio").each(function() 
	{
		filtro_por_fondeo = $(this).next("label").text();
		
		if($(this).is(":checked")) 
		{ 
			$(".resultados .fondeo").html(filtro_por_fondeo);
		}
	});
	
	 if ($("#gender_Hombre").hasClass("itemCheck")) 
	 {
		 $(".filtro_genero").html("Hombres");
	 }
	 
     if ($("#gender_Mujer").hasClass("itemCheck")) 
     {
    	 $(".filtro_genero").html("Mujeres");
     }
     
     if ( $("#gender_Hombre").hasClass("itemCheck") &&  $("#gender_Mujer").hasClass("itemCheck") ) 
     {
    	  $(".filtro_genero").html("Hombres y Mujeres");
     }
     
     var desde = $("#sidebar #inputFrom").val();
     var hasta = $("#sidebar #inputTo").val();
     
	 $(".no_definido").hide();
	 $(".resultados .desde").html(desde);
	 $(".resultados .hasta").html(hasta);
	
	if ($("#sidebar").is(":visible")) 
	{
		 if ( ($("#inputFrom").val().length==0) &&  ($("#inputTo").val().length==0 )) 
		 {
			  $(".d").hide();
			  $(".h").hide();
			  $(".no_definido").show();
		 } else {
			 $(".d").show();
			 $(".h").show();
		 }
	}
	
	var plazo_meses;
	
	if ($(".plazo_1").hasClass("itemCheck")) 
	{
			plazo_meses = "6";
	}
	
		 if ($(".plazo_2").hasClass("itemCheck")) {
			plazo_meses = "9";
		}
		 if ($(".plazo_3").hasClass("itemCheck")) {
			plazo_meses = "12";
		}
		 if ($(".plazo_4").hasClass("itemCheck")) {
			plazo_meses = "15";
		}
		 if ($(".plazo_5").hasClass("itemCheck")) {
			plazo_meses = "36";
		}
		 
		 $(".resultados .plazo").html(plazo_meses);
		 $(".proyectos_disponibles").html($("#pnlNumCredito span").text());	

		 fondeo_previo();
		 n_boton_distribuir_quitar();
		 
		 if($(".tienda").hasClass("active")) 
		 {
			 $("#sidebar").removeClass("active");
			 $(".velo").fadeOut();
		 }
		 
		 revisarSeleccionados();
		 
		 if($(".tablaTienda").is(":visible") &&  scrollea == true)
		 {
			 	scrollea = false;
			 	
				 $('html, body').animate(
				{
				       scrollTop: ($('#encabezadoTablaTienda').offset().top - 100)
				 },1400);			 
		 }
		
}

function fondeo_previo() 
{
	console.log("Filters.fondeo_previo(): ");
	
   	$(".numero_inversiones").each(function() 
   	{
   			var numero_inversiones = $(this).find("p").length;
   			$(this).find("h6 span").html(numero_inversiones);
   			if(numero_inversiones == 0) {
   				$(this).hide();
   				$(this).next(".monto_inversiones").hide();
   			}
   			if(numero_inversiones != 0) {
   				$(this).show();
   				$(this).next(".monto_inversiones").show();
   			}
   			if(numero_inversiones == 1) {
   				$(this).find("h6 small").html("inversion");
   			}
   		     else if (numero_inversiones > 1) {
   				$(this).find("h6 small").html("inversiones");
   			}
 	});

}

function n_boton_distribuir_quitar() 
{
	console.log("Filters.n_boton_distribuir_quitar(): ");
	
	if( $("#distribuir").is(":visible")) 
	{
		$(".distribuir_quitar span").html("Distribuir");
	}
	
	if( $("#quitar").is(":visible")) 
	{
		$(".distribuir_quitar span").html("Quitar");
	}	
	
	var width = $(window).width();
	
	proyectosMovil();
	
	$(window).resize(function() 
	{
		var resizeId;
		
		clearTimeout(resizeId);
		   
		resizeId = setTimeout(proyectosMovil, 300);   	
	});
	
	 totalFondeado();
	 numeracionTienda(); 	 
}

function totalFondeado() 
{
	console.log("Filters.totalFondeado(): ");
	
	$(".filaTienda").each(function() 
	{
		var filaProyecto = $(this);
		
		var montoFondear = filaProyecto.find($(".montoFondear")).text(), 
			montoFondear	=  Number(montoFondear.replace(/[^0-9\.]+/g,""));
			
		var disponibleFondear = filaProyecto.find($(".disponibleFondear")).text(),
			disponibleFondear =	Number(disponibleFondear.replace(/[^0-9\.]+/g,""));
		
		var porcentajeFondeo = ((montoFondear - disponibleFondear)*100)/montoFondear;
		var progresoFondeoLetra = filaProyecto.find($(".progresoFondeoLetra"));
		
			filaProyecto.find($(".progresoFondeo")).css({
				width: porcentajeFondeo+ "%"
			});
			
			progresoFondeoLetra.text(porcentajeFondeo.toFixed(2)+"%");
		
	});
}

function numeracionTienda () 
{
	console.log("Filters.numeracionTienda(): ");
	
	setTimeout(function()
	{
		var numberRegistro = 0;
		
		$("#creditos .filaTienda .numberRegistro").each(function(idx) 
		{
			numberRegistro = numberRegistro + 1;
			
			$(this).html(numberRegistro);
			
			console.log(numberRegistro);		
		});	
		
	},300);
	
}

function  proyectosMovil() 
{	
	console.log("Filters.proyectosMovil(): ");
	
	var numberFila;
	
	 var width = $(window).width();
	 
	 if (width >= 1101) 
	 { 
		 $(".saldo_cuenta").prependTo('#sidebar');		
	 }
	 if (width <= 1100) { 
		 $(".saldo_cuenta").insertBefore('.individual_paquete');				 
	 }
	 if (width >= 821) { 

		 if(!$("#pnlMsgSugMin .max_recomendada").length){
			$("#copyRecomendacion .max_recomendada").insertAfter($(".datoQueTuPerfil"));
			$(".maxRecMovil").removeClass("show");
			$(".velo2").fadeOut();
		 }	
   
	 }
	if (width <= 820  && width >=651) {
		$(".filaTienda").removeClass("par");
		$(".filaTienda:odd").css("float", "right");

	}
	
	if ( width <= 650) {
		$(".filaTienda:odd").css("float", "none");
		$(".filaTienda").removeClass("par");

	}
	
	if ($(".tablaTienda").is(":visible") && width <= 820 && showMovil == false ) { 
		 $(".max_recomendada").appendTo($("#copyRecomendacion"));
		 $(".maxRecMovil").addClass("show");
		 $(".velo2").fadeIn();
		 showMovil = true;
	}

	 if (width <= 1100  && width >=651) { 
			$(".tablaTipoProyectos ul:odd").addClass("derecha");
	 }
	 
	 if (width >= 1101) {		
		 $(".tablaTipoProyectos ul:odd").removeClass("derecha");
	 }
	 
	 kuboScoreTasa(width);
	 kuboScoreTasaDos(width);
	 
	 if($("#ammountToInvFlot").length) 
	 {
		 disponible();
	 }

		
	nuevoRepetido();
}

function kuboScoreTasa() 
{
	console.log("Filters.kuboScoreTasa(): ");
	
	var width = $(window).width();
	
	if (width >= 821) 
	{ 
		 $(".modoLista .tasaAnualCelda .testForScores").each(function() {
			    $(this).appendTo( $(this).closest(".filaTienda").find(".kuboScoreCelda"));
		 });
	}
	
	if (width <= 820) 
	{
		$(".modoLista .filaTienda .kuboScoreCelda .testForScores").each(function() 
		{
		    $(this).appendTo( $(this).closest(".filaTienda").find(".tasaAnualCelda"));
	   });
	}	
}

function kuboScoreTasaDos() 
{
	console.log("Settings.kuboScoreTasaDos(): ");
	
	$(".modoMosaico .tasaAnualCelda .testForScores").each(function() 
	{
		$(this).appendTo( $(this).closest(".filaTienda").find(".kuboScoreCelda"));
	});
}

function disponible ()
{
	console.log("Filters.disponible(): ");
	
	var monto_invertir = $("#ammountToInvFlot").val();
	var monto_invertir = Number(monto_invertir.replace(/[^0-9\.]+/g,""));	
	var monto_seleccionado = $("#valAmmountInvFlot").text();
	var monto_seleccionado = Number(monto_seleccionado.replace(/[^0-9\.]+/g,""));
	var disponible_val = monto_invertir - monto_seleccionado
	
	if (disponible_val  > 0) 
	{
		 $(".disponible_val").html("$" + (monto_invertir-monto_seleccionado).toFixed(2));
		 $(".disponible_val").digits();
		 
	} else if (disponible_val  < 0) {
		 $(".disponible_val").html("- $" + (monto_invertir-monto_seleccionado).toFixed(2).slice(1));
		 $(".disponible_val").addClass("red");
		 $(".disponible_val").digits();
	}
	 else if (disponible_val  == 0)  {
		 $(".disponible_val").html("$ 0.00")
	
	}

}

function nuevoRepetido() 
{	
	console.log("Filters.nuevoRepetido(): ");
	
	setTimeout(function()
	{
		$(".filaTienda").each(function() 
		{
			var fila = $(this);
			var tipoCreditoget = fila.find($(".tipoCreditoget"));
			var nuevoRepetido = fila.find($(".nuevoRepetido"));
		
			if( tipoCreditoget.text().indexOf("Primera vez") != (-1)) {
				nuevoRepetido.html("Nuevo")
			}
			if( tipoCreditoget.text().indexOf("Repetido") != (-1)) {
				nuevoRepetido.html("Repetido")
			}
		});
		
	},300);
}

function revisarSeleccionados ()
{
	console.log("Filters.revisarSeleccionados(): ");
	
	if($(".btnSwitch").hasClass("on"))
	{
		 seleccionadosPin();
		 
	} else {
		
		removerSeleccionado();
	}
}


function seleccionadosPin()
{
	console.log("Filters.seleccionadosPin(): ");
	
	$(".inversionCelda").each(function() 
	{
		var inversionCelda = $(this);
		var inversionCeldaCheck = $(this).find("input:checkbox");
		
			if(inversionCeldaCheck.prop('checked'))
			{
				inversionCelda.addClass("selected");
			}
	});
}

function removerSeleccionado()
{
	console.log("Filters.removerSeleccionado(): ");
	
	$(".inversionCelda").removeClass("selected")
}

$(document).ready(function()
{
	console.log("Filters.init(): ");
	
	$(".profileId").click(function()
	{
		$("#descProfile").toggle();
	});
	
	$(".clsItem").click( function()
	{
		if($(this).hasClass("itemCheck"))
		{
			$(this).removeClass("itemCheck");
		
		}else{
			$(this).addClass("itemCheck");
		}
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItem").each(function()
		{			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 ){
					strCadena+=",";
				}
				
				strCadena+= $(this).attr("id");
				item_num_risk++;
			
			}
				
		});
		
		$("#cadena1").val( strCadena );
		$("#risk_str").val( strCadena );
		
	});
	
	
	$(".clsItemDest").click( function()
	{
		if($(this).hasClass("itemCheck"))
		{
			$(this).removeClass("itemCheck");
		
		}else{
			$(this).addClass("itemCheck");
		}
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItemDest").each(function(){
			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 ){
					strCadena+=",";
				}
				
				strCadena+= ($(this).attr("id")).replace("purpose_", "");
				item_num_risk++;
			
			} 
				
		});
		/*
		while(strCadena.indexOf("purpose_") != (-1)){
			strCadena = strCadena.replace("purpose_", "");
		}
		*/
		$("#destiny_str").val( strCadena );
		$("#destiny_str_val").val( strCadena );
		
	});
	
	
	$(".clsTerm").click( function(){

		
		var this_id = $(this).attr("id");
		
		var this_sec = this_id.split("_")[1];
		var i = 0;
		
		var valMax = this_id.split("_")[0];
		
		if(valMax == "gender"){
			
			if($(this).hasClass("itemCheck"))
			{
				$(this).removeClass("itemCheck");
			
			}else{
				$(this).addClass("itemCheck");
			}
			
			var flagGender = false;
			$(".clsTerm").each(function(){
				
				this_id = $(this).attr("id");
				
				var tipo = this_id.split("_")[0];
				
				if( tipo == "gender" ){
				
					if($(this).hasClass("itemCheck")){
						flagGender = true;
					}
				
				}
			});
			
			if( !flagGender ){
				$(this).addClass("itemCheck");
			}
			
			
		}else{
			
			$(".clsTerm").each(function(){
				
				this_id = $(this).attr("id");
				
				var tipo = this_id.split("_")[0];
				
				if( tipo != "gender" ){
				
					if($(this).hasClass("itemCheck")){
						$(this).removeClass("itemCheck")
					}
					
				}
					
			});
			
			$("#cadena2").val("");
			
			$(".clsTerm").each(function(){
				
				var this_id_2 = $(this).attr("id");
				
				var this_sec_2 = this_id_2.split("_")[1];
				
				if( parseInt(this_sec) >=  parseInt(this_sec_2) ){
					$(this).addClass("itemCheck")
				}

			});
			
			$("#cadena2").val( valMax );
			
			$("#cadena2").trigger("blur");
			
			
		}
		
	} );
});
