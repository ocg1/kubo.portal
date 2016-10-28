console.log("estatus/listener_seleccion_fecha.js");

function set_selected_day(select_one_menu)
{
	$("#input_listener_day_selected").val(select_one_menu.value).trigger("click");
}

function set_selected_month(select_one_menu)
{
	$("#input_listener_month_selected").val(select_one_menu.value).trigger("click");	
}

function set_selected_year(select_one_menu)
{
	$("#input_listener_year_selected").val(select_one_menu.value).trigger("click");
}

function set_selected_motive(select_one_menu)
{
	$("#input_listener_motive_selected").val(select_one_menu.value).trigger("click");
	
	console.log("set_selected_motive(): " + select_one_menu.value);
	
	if($("#select_lista_de_motivos").val() != "")
	{
		$("#msg_fecha_valida").html("").hide("fast");
	}
}