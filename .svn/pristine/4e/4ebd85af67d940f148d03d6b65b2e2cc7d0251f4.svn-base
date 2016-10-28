console.log("estatus/listener_guadar_cambios.js");

function listener_editor_estaus_guardar_cambios()
{						
	var RECHAZADO = 4;
	var POSPUESTO = 5;
	var DESISTIDO = 7;
	var PRE_AUTORIZADO = 10;
	
	var selected  = $("input[type='radio'][name='selectStatus1']:checked");	
	var motivo    = $("#txt-reason-status-proyect1").val().trim();
	
	var error_msg_estatus = "Se requiere seleccionar estatus del proyecto";
	
	//alert($("#select_lista_de_motivos").val());
	
	if( ( parseInt( selected.val() ) == parseInt(POSPUESTO) 
	   || parseInt(selected.val())   == parseInt(RECHAZADO) 
	   || parseInt(selected.val())   == parseInt(DESISTIDO)
	   || parseInt(selected.val())   == parseInt(PRE_AUTORIZADO)) 
	   &&  $("#select_lista_de_motivos").val() == "" )
	{
		
		$("#msg_fecha_valida").html("Es necesario seleccionar el motivo del cambio").show("fast");
		
		return;
	}
		
	if(selected.length > 0)
	{
		var estatus = selected.val();		
		
		if(estatus == POSPUESTO || estatus == PRE_AUTORIZADO)
		{			
			if(confirm("Confirmar fecha"))
			{
				guardar_cambio_estatus(estatus, motivo);
			}	
			
		} else {	
			
			guardar_cambio_estatus(estatus, motivo);					
		}
					
	} else {
		
		$("#msg_fecha_valida").html(error_msg_estatus).show("fast");
	}				    
	
	console.log("listener_editor_estaus_guardar_cambios(): OK");
}

function guardar_cambio_estatus(estatus, motivo)
{
	var error_msg_motivo  = "Se requiere indicar el motivo del cambio";
	
	$("#selectStatus2").val(estatus).blur();
	$("#txt-reason-status-proyect2").val(motivo).blur();						
	
	if(motivo.length > 0)
	{
		//comentado hubspot ChangeStatusHubspot();
		$("#btnChangeStatus").click();
		
		$.fancybox.close();
		
		console.log("guardar_cambio_estatus(): OK");
		
		
		
	} else {
		
		$("#msg_fecha_valida").html(error_msg_motivo).show("fast");
	}
}


function ChangeStatusHubspot () {

	var dt = new Date();
	var time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
	var displayDate = (dt.getMonth()+1) + '/' + (dt.getDate()) + '/' + dt.getFullYear();	
	var date_hour = displayDate+":"+time

			 $(function() {
					$.getJSON("https://api.ipify.org?format=jsonp&;callback=?",
					  function(json) {
						$("#ip_input").val( json.ip);
					  }
					);
				  });	
	  /*-- la cadena no debe de tener un ampersand--*/
	var ip  = $("#ip_input").val();
	var URI = "";
	var str = "";
    
   jason = [];
   hs_context = [];

   var mesSeleccionado = $("#select_fecha_lista_mm").find('option:selected').text();
   var mesNumero;

	if(mesSeleccionado == "Enero"){
		mesNumero = "01"
	}
	if(mesSeleccionado == "Febrero"){
		mesNumero = "02"
	}
	if(mesSeleccionado == "Marzo"){
		mesNumero = "03"
	}
	if(mesSeleccionado == "Abril"){
		mesNumero = "04"
	}
	if(mesSeleccionado == "Mayo"){
		mesNumero = "05"
	}
	
	if(mesSeleccionado == "Junio"){
		mesNumero = "06"
	}
	
	if(mesSeleccionado == "Julio"){
		mesNumero = "07"
	}
	if(mesSeleccionado == "Agosto"){
		mesNumero = "08"
	}
	
	if(mesSeleccionado == "Septiembre"){
		mesNumero = "09"
	}
	if(mesSeleccionado == "Octubre"){
		mesNumero = "10"
	}
	if(mesSeleccionado == "Noviembre"){
		mesNumero = "11"
	}
	if(mesSeleccionado == "Diciembre"){
		mesNumero = "12"
	}
	 str += "prospectoID=" + $(".prospectoID").text() + "&";
	 
	 str += "correoAcreditado=" + $("#emailUsuario").text() +  "&";
	 
	   var seleccionarEstatus = $("#seleccionarEstatus input").prop('checked');
		$("#seleccionarEstatus input").each(function() {
			if($(this).prop('checked')) {
			  str +=  "estatusSeleccionado="+  $(this).next("span").text()+ "&"; 
			}
		});     
		 

  
       str += "MotivoStatus="  + $("#select_lista_de_motivos").find('option:selected').text() + "&";
       str += "FechaEstatus=" + $("#select_fecha_lista_dd select").find('option:selected').text() +  mesNumero +  $("#select_fecha_lista_yyyy").find('option:selected').text() +"&";
       str += "DescripcionMotivo=" + $("#txt-reason-status-proyect1").val();
       
       
    context = {}
	context ["hutk"] = $.cookie('hubspotutk')
	context ["ipAddress"] = ip
    context ["pageUrl"] = "http://www.kubofinanciero.com/Kubo/jsf/controltable.xhtml"
    context ["pageName"] = "Mesa control"
    context ["redirectUrl"] = ""
           
   hs_context.push(context);
   	        
   var campo = {}
	campo ["hs_context"] = hs_context;
   jason.push(campo);
          
   console.log(JSON.stringify(hs_context));
   console.log("");
   console.log("");
   console.log("");
   	                	        	    
   URI = encodeURIComponent(JSON.stringify(hs_context));
   URI = URI.substring(3,(URI.length - 3) )
   URI = str+"&"+"hs_context="+URI;
   
   console.log(URI);
      
   $.ajax({
   	type: "POST",
   	url: "https://forms.hubspot.com/uploads/form/v2/477732/883b3749-cbe7-47a0-9bdd-9a66d9464f84",
   	data: URI,
   	success: function(){},
   	dataType: "text"
   });     
 
   
     
}



