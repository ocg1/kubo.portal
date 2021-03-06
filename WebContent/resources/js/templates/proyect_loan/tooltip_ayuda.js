function mostrar_tooltip(tooltip_id)
{
	/* agregados gabriel */
	console.log("inicio tooltip_ayuda.js")

	$(".tooltip_icon .tooltip").hide();
	 setTimeout(function(){ $("body").addClass("active"); }, 300);
	/*---*/
	
	var msg_score_buro_credito  = "Es el puntaje que tiene en Buró de Crédito";
	var msg_credito_personal    = "Monto del crédito";
	var msg_plazo               = "Plazo";
	var msg_fondeadores         = "Número de inversionistas que han apoyado el proyecto";
	var msg_frecuencia_pago     = "Frecuencia de pagos";
	var msg_ver_perfil_completo = "En el botón de perfil del acreditado podrás observar información detallada del acreditado,"
								+ "excepto sus datos personales como nombre completo, dirección y teléfonos";
	var msg_actividad_economica = "A qué se dedica y desde cuándo";
	var msg_ingresos_mensuales  = "Los ingresos del acreditado se validan con distintos comprobantes"
								+ "mediante entrevistas telefónicas y una visita a su trabajo";
	var msg_egresos_mensuales   = "Sus gastos familiares están en función del número de dependientes económicos, "
								+ "y se comparan con personas que se dedican a la misma actividad para validar su congruencia";
	var msg_excedente			= "Se determina un flujo excedente disponible para cubrir la cuota mensual del crédito";
	var msg_liquidez_mesa_control = "El flujo excedente debe ser al menos 1.5 veces la cuota mensual del crédito";
	var msg_indice_pago_deudas    = "Cuidamos el sobreendeudamiento analizando el monto total de sus deudas en relación a sus ingresos mensuales";
	var msg_lista_de_telefonos    = "Hacemos 2 llamadas a referencias proporcionadas por el cliente para validar congruencia de información y solvencia moral";
	var msg_acerca_del_proyecto   = "Mediante un cuestionario dejamos que el cliente nos describa su proyecto";
	var msg_buro_de_credito       = "En este botón podrás observar información de su historial crediticio";
	var msg_nivel_de_endeudamiento = "En esta gráfica podrás observar si el monto de deudas se ha incrementado en el tiempo."
								    + "También podrás ver cuál es el monto máximo de crédito que ha manejado";
	var msg_quebrantos = "Aqui puedes ver si el cliente ha tenido algún quebranto o ha reestructurado algún crédito";
	
	var msg_tooltip_grafica_MOP = "Cada línea de color representa un crédito que ha sido recibido. </br></br>"
								+ "Las líneas punteadas significan créditos activos que sigue pagando";
	
	var msg_grafica_MOP_lineas  = "Cuando las líneas están entre las barras 2 y 3 significan atrasos de 30 a 60 días. </br></br>"
								+ "Cuando las líneas están entre las barras 3 y 4 significan atrasos de 60 a 90 días";
	
	var msg_efectivo_disponible  		= "Recursos disponibles para invertir o retirar.";
	var msg_inversiones_proceso  		= "Recursos que asignaste a proyectos que están en proceso de fondeo.";
	var msg_inversiones_activas  		= "Recursos en proyectos activos.";
	var msg_inversiones_por_cobrar  	= "Son los intereses de los pagos que están devengandose y recibirás en el próximo pago del proyecto .";
	var msg_minusvalia  				= "Son los pagos de créditos que garantizaste a través de tu cuenta kubo.impulso y que han sido aplicados a tu cargo ya que el acreditado garantizado no pagó.";
	var msg_minusvalia2  				= "Son los intereses que dejaste de ganar ya que el acreditado garantizado no pagó.";
	var msg_interes_plazofijo  			= "Intereses cobrados de kubo.plazofijo.";
	var msg_interes_impulso  			= "Son los intereses de proyectos que hayan pagado y están depositados en tu cuenta kubo.global.";
	var msg_interes_global 				= "Intereses cobrados de kubo.global.";
	var msg_capital  					= "Pagos de capital que has recibido de proyectos en los que invertiste.";
	var msg_moratorios					= "Intereses que cobraste de proyectos que se atrasaron.";
	var msg_inversiones_plazofijo		= "Contratos con fecha de vencimimiento posterior a la fecha del sistema.";
	var msg_intereses_dev_plazofijo		= "Intereses entre la fecha de contratación y la fecha del sistema.";
	var msg_interese_garantia_impulso	= "Son los intereses de los pagos atrasados de proyectos y que forman parte de la garantía de los mismos.";
 	
	
	console.log("\nmostrar_tooltip(): " + tooltip_id);
	
	if(tooltip_id == "#tooltip_score_buro_credito")
	{
		asignar_tooltip_message(tooltip_id, msg_score_buro_credito);
		
	} else if (tooltip_id == "#tooltip_credito_personal") {
		
		asignar_tooltip_message(tooltip_id, msg_credito_personal);
		
	} else if (tooltip_id == "#tooltip_plazo") {
		
		asignar_tooltip_message(tooltip_id, msg_plazo);
		
	} else if (tooltip_id == "#tooltip_fondeadores") {
		
		asignar_tooltip_message(tooltip_id, msg_fondeadores);
		
	} else if (tooltip_id == "#tooltip_frecuencia_pago") {
		
		asignar_tooltip_message(tooltip_id, msg_frecuencia_pago);
		
	} else if (tooltip_id == "#tooltip_ver_perfil_completo") {
		
		asignar_tooltip_message(tooltip_id, msg_ver_perfil_completo);
		
	} else if (tooltip_id == "#tooltip_actividad_economica") {
		
		asignar_tooltip_message(tooltip_id, msg_actividad_economica);
		
	} else if (tooltip_id == "#tooltip_ingresos_mensuales") {
		
		asignar_tooltip_message(tooltip_id, msg_ingresos_mensuales);
		
	} else if (tooltip_id == "#tooltip_egresos_mensuales") {
		
		asignar_tooltip_message(tooltip_id, msg_egresos_mensuales);
		
	} else if (tooltip_id == "#tooltip_excedente") {
		
		asignar_tooltip_message(tooltip_id, msg_excedente);
		
	} else if (tooltip_id == "#tooltip_liquidez_mesa_control") {
		
		asignar_tooltip_message(tooltip_id, msg_liquidez_mesa_control);
		
	} else if (tooltip_id == "#tooltip_indice_pago_deudas") {
		
		asignar_tooltip_message(tooltip_id, msg_indice_pago_deudas);
		
	} else if (tooltip_id == "#tooltip_lista_de_telefonos") {
		
		asignar_tooltip_message(tooltip_id, msg_lista_de_telefonos);
		
	} else if (tooltip_id == "#tooltip_acerca_del_proyecto") {
		
		asignar_tooltip_message(tooltip_id, msg_acerca_del_proyecto);
		
	} else if (tooltip_id == "#tooltip_buro_de_credito") {
		
		asignar_tooltip_message(tooltip_id, msg_buro_de_credito);
		
	} else if (tooltip_id == "#tooltip_nivel_de_endeudamiento") {
		
		asignar_tooltip_message(tooltip_id, msg_nivel_de_endeudamiento);
		
	} else if (tooltip_id == "#tooltip_quebrantos") {
		
		asignar_tooltip_message(tooltip_id, msg_quebrantos);
		
	} else if (tooltip_id == "#tooltip_grafica_MOP") {
		
		asignar_tooltip_message(tooltip_id, msg_tooltip_grafica_MOP);
		
	} else if (tooltip_id == "#tooltip_grafica_MOP_lineas") {
		
		asignar_tooltip_message(tooltip_id, msg_grafica_MOP_lineas);
		
	}else if (tooltip_id == "#tooltip_efectivo_disponible") {
		
		asignar_tooltip_message(tooltip_id, msg_efectivo_disponible);
		
	}else if (tooltip_id == "#tooltip_inversiones_proceso") {
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_proceso);
		
	}else if (tooltip_id == "#tooltip_inversiones_proceso_2") {
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_proceso);
		
	}else if (tooltip_id == "#tooltip_inversiones_activas") {
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_activas);
		
	}else if (tooltip_id == "#tooltip_intereses_por_cobrar") {
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_por_cobrar);
		
	}else if (tooltip_id == "#tooltip_intereses_por_cobrar2") {
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_por_cobrar);
		
	}else if (tooltip_id == "#tooltip_minusvalia") {
		
		asignar_tooltip_message(tooltip_id,msg_minusvalia);
		
	} 
	else if (tooltip_id == "#tooltip_minusvalia2") {
		
		asignar_tooltip_message(tooltip_id, msg_minusvalia2);
		
	} 
	else if (tooltip_id == "#tooltip_intereses_moratorios") {
		
		asignar_tooltip_message(tooltip_id,msg_moratorios);
		
	}else if (tooltip_id == "#tooltip_int_plazofijo") {
		
		asignar_tooltip_message(tooltip_id,msg_interes_plazofijo);
		
	}else if (tooltip_id == "#tooltip_int_kuboimpulso") {
		
		asignar_tooltip_message(tooltip_id,msg_interes_impulso);
		
	}else if (tooltip_id == "#tooltip_global") {

		asignar_tooltip_message(tooltip_id,msg_interes_global);
		
	}else if (tooltip_id == "#tooltip_capital") {
		
		asignar_tooltip_message(tooltip_id,msg_capital);
		
	}else if (tooltip_id == "#tooltip_inversiones_plazofijo"){
		
		asignar_tooltip_message(tooltip_id,msg_inversiones_plazofijo);
		
	}else if (tooltip_id == "#tooltip_intereses_dev_plazofijo"){
		
		asignar_tooltip_message(tooltip_id,msg_intereses_dev_plazofijo);
	
	}else if (tooltip_id == "#tooltip_interese_garantia_impulso" ){
		
		asignar_tooltip_message(tooltip_id,msg_interese_garantia_impulso);
	
	}
		
	$(tooltip_id).show("fast");

	/* agregados gabriel */
	$(document).on('click', 'body.active',function(e){
		  $(".tooltip_icon .tooltip").hide("3000");
		  $("body").removeClass("active");
	});
		/*--*/

	
	

	
	
	
}

function asignar_tooltip_message(tooltip_id, message)
{
	$(tooltip_id).find("p.tooltip_message").html(message);
}

function hideMotive(tooltip_id)
{
	$(tooltip_id).hide("fast");
	
}

