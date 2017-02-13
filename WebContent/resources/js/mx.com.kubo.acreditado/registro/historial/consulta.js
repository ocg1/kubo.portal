console.log("mx.com.kubo.acreditado/registro/historial/consulta.js");

function mis_datos_son_correctos()
{
	console.log("mis_datos_son_correctos()");
	
	guardar_nota_del_coach();
	creaConsulta();
}

function creaConsulta()
{		
	console.log("creaConsulta(): ");
	
	// $('#general').hide();
	
	$.scrollTo('#header',300, { axis:'y' });
	
	$('#dvGenSure').hide();
	$('#dvContPreaprobacion').show();
	
    var testframe = document.createElement("iframe");
    testframe.id = "testframe";
    testframe.src = "../jsf/secciones/registro/pre_aprobacion.xhtml;jsessionid=#{creditHistoryController.sessionId}";
    
    $("#dvContPreaprobacion").empty();
    $("#dvContPreaprobacion").append(testframe);
    
    var styles = {
    		width : "98%",
    		border: "0px",
    		height: "1100px",
    		overflow: "hidden"
    };
    
    $("#testframe").css(styles);
    //ga('send', 'event', 'Leads', 'Consulta Buro', 'Consulta despues de verificar');  
}

function asignar_nota_del_coach()
{	
	var nota_adicional = $.trim($("#nota_del_coach").val());
	
	if(nota_adicional.length > 0)
	{
		$("#listener_asignar_nota_del_coach").val(nota_adicional).trigger("click");
		
		console.log("asignar_nota_del_coach(): " + nota_adicional);	
		
	} else {
		
		console.log("asignar_nota_del_coach(): EMPTY");
	}
}

function guardar_nota_del_coach()
{
	console.log("guardar_nota_del_coach()");
	
	$("#listener_guardar_nota_del_coach").trigger("click");
}