console.log("filters.js");

var scrollea = false;

function filtros()
{
	console.log("Filters.filtros(): ");		
	
	$(".scores").html("");
	
	$("#sidebar #filter_by_type .ui-checkbox-filter").each(function() 
	{
	    tipo_score = $(this).attr("id").replace("chk", "");
	    
		if($(this).is(":checked")) 
		{ 
			$(".scores").append(tipo_score + ", ");
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
	
	 if ($(".plazo_2").hasClass("itemCheck")) 
	 {
		plazo_meses = "9";
	 }
	 
	 if ($(".plazo_3").hasClass("itemCheck")) 
	 {
		plazo_meses = "12";
	 }
	 
	 if ($(".plazo_4").hasClass("itemCheck")) 
	 {
		plazo_meses = "15";
	 }
		 
	 if ($(".plazo_5").hasClass("itemCheck")) 
	 {
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
			 
		  }, 1400);			 
	 }		
}

function fondeo_previo() 
{
	console.log("Filters.fondeo_previo(): ");
	
   	$(".numero_inversiones").each(function() 
   	{
		var numero_inversiones = $(this).find("p").length;
		
		$(this).find("h6 span").html(numero_inversiones);
		
		if(numero_inversiones == 0) 
		{
			$(this).hide();
			$(this).next(".monto_inversiones").hide();
		}
		
		if(numero_inversiones != 0) 
		{
			$(this).show();
			$(this).next(".monto_inversiones").show();
		}
		
		if(numero_inversiones == 1) 
		{
			$(this).find("h6 small").html("inversion");
		}
		
	    else if (numero_inversiones > 1) 
	    {
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

Filters.init = function()
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
		
		} else {
			
			$(this).addClass("itemCheck");
		}
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItem").each(function()
		{			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 )
				{
					strCadena += ",";
				}
				
				strCadena += $(this).attr("id");
				
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
				if( item_num_risk != 0 )
				{
					strCadena+=",";
				}
				
				strCadena+= ($(this).attr("id")).replace("purpose_", "");
				item_num_risk++;
			
			} 
				
		});
		
/*		
		while(strCadena.indexOf("purpose_") != (-1))
		{
			strCadena = strCadena.replace("purpose_", "");
		}
*/		
		
		$("#destiny_str").val( strCadena );
		$("#destiny_str_val").val( strCadena );
		
	});
	
	
	$(".clsTerm").click( function()
	{		
		var this_id = $(this).attr("id");
		
		var this_sec = this_id.split("_")[1];
		var i = 0;
		
		var valMax = this_id.split("_")[0];
		
		if(valMax == "gender")
		{			
			if($(this).hasClass("itemCheck"))
			{
				$(this).removeClass("itemCheck");
			
			}else{
				$(this).addClass("itemCheck");
			}
			
			var flagGender = false;
			
			$(".clsTerm").each(function()
			{				
				this_id = $(this).attr("id");
				
				var tipo = this_id.split("_")[0];
				
				if( tipo == "gender" )
				{				
					if($(this).hasClass("itemCheck"))
					{
						flagGender = true;
					}
				
				}
			});
			
			if( !flagGender )
			{
				$(this).addClass("itemCheck");
			}			
			
		} else {
			
			$(".clsTerm").each(function()
			{
				
				this_id = $(this).attr("id");
				
				var tipo = this_id.split("_")[0];
				
				if( tipo != "gender" )
				{			
					if($(this).hasClass("itemCheck"))
					{
						$(this).removeClass("itemCheck")
					}
					
				}					
			});
			
			$("#cadena2").val("");
			
			$(".clsTerm").each(function()
			{				
				var this_id_2 = $(this).attr("id");
				
				var this_sec_2 = this_id_2.split("_")[1];
				
				if( parseInt(this_sec) >=  parseInt(this_sec_2) )
				{
					$(this).addClass("itemCheck")
				}

			});
			
			$("#cadena2").val( valMax );
			
			$("#cadena2").trigger("blur");						
		}
		
	} );
};
