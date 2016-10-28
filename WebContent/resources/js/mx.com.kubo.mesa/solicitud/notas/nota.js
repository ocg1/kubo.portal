console.log("nota.js");

/*
define(function()
{
	var modulo = 
	{		
		init: function()
		{
			console.log("nota.init(): OK");
		}
	};
	
	return modulo;
});
*/

function update_tabla_notas_alta_prioridad()
{
	$('#link_actualizar_tabla_notas_alta_prioridad').click();
}

function agregar_nota()
{
	$('#section-add-notes').slideToggle('slow');
	$('#btn-add-notes').hide('slow');	
	
	$('html, body').animate({
        scrollTop: $("#section-add-notes").offset().top - 200
    }, 800);
}


function validar_nueva_nota()
{
	var is_nota_OK =  validateNewNote('select-type-note','select-note-priority','txt-capture-note');
	
	console.log("validar_nueva_nota(): " + is_nota_OK);
	
	return is_nota_OK;
}

function guardar_nueva_nota(xhr, status, args)
{
	$('#select-type-note').val('');
	$('#select-note-priority').val('');
	$('#txt-capture-note').val('');
	
	console.log("\nguardar_nueva_nota(): " + args.values);
	
	$('#input_cambio_estatus_TOKEN').val(args.values);
	
	$('html, body').animate({
        scrollTop: $("#titulo_notas_del_caso").offset().top
    }, 800);
}

function cancelar_agregar_nota()
{	
	$('#btn-add-notes').click();
	$('#btn-add-notes').show('slow');
}

function guardar_edicion_nota()
{
	$('#select-type-note').val('');
	$('#select-note-priority').val('');
	$('#txt-capture-note').val('');
	
	$('html, body').animate({
        scrollTop: $("#titulo_notas_del_caso").offset().top
    }, 800);
}
 
function cancelar_edicion_prioridad() 
{
	$('#main_edicion_nota').hide('slow');	
	$('#btn-add-notes').show('slow');
	
	$('html, body').animate({
        scrollTop: $("#titulo_notas_del_caso").offset().top + 500
    }, 800);
}

function checkNoteType(xhr, status, args)
{
	var tipo_nota_SELECTED = parseInt($("#select-type-note").val());
	
	console.log("checkNoteType(): " + tipo_nota_SELECTED);
	
	switch(tipo_nota_SELECTED)
	{
		case 7:
		case 10:
		case 11:
		case 12:
			$('#dvMotiveNote').show("fast");
			$('#meta_INFO_visita_domiciliaria').hide("fast");
		break;
		
		case 9:
			$('#meta_INFO_visita_domiciliaria').show("fast");			
			$('#dvMotiveNote').hide("fast");
		break;
			
		default:
			$('#dvMotiveNote').hide("fast");
			$('#meta_INFO_visita_domiciliaria').hide("fast");
		break;
	}
}

function callback_ver_todas_las_notas(xhr, status, args)
{
	var is_todas_las_notas_ENABLED = args.todas_las_notas_ENABLED;
	
	console.log("\ncallback_ver_todas_las_notas():");
	console.log("todas_las_notas_ENABLED = " + is_todas_las_notas_ENABLED);
	
	if(is_todas_las_notas_ENABLED == "true")
	{
		$("div#titulo_notas_del_caso").find("p#ver_todas_las_notas").hide();
		$("div#titulo_notas_del_caso").find("p#cargar_notas_by_proyect").show();
		
	} else {
		
		$("div#titulo_notas_del_caso").find("p#ver_todas_las_notas").show();
		$("div#titulo_notas_del_caso").find("p#cargar_notas_by_proyect").hide();
	}
}