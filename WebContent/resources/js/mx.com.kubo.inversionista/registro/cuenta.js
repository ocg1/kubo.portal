console.log("cuenta.js");


function init_alta_inversionista()
{		
	$(".altaClient").fancybox(
	{
		'width' : '90%',
		'height' : '100%',
		'padding': '0',
		'margin' : '0',
		'autoScale' : 'false',
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'type' : 'iframe',
		'scrolling' : 'auto',
		'centerOnScroll' : 'true',
		'overlayColor': '#333333',
		'hideOnOverlayClick': false,
		'showCloseButton' : false,
		'href': 'secciones/registro/investor/altaClientInvest.xhtml',
		'onClosed':function() {
			if($("#valFlagPrep").val() == "aceptado")
			{
				$("#hdNext\\:siguientePrep").click();
				$.scrollTo('#header', 800, { axis:'y' });
			}
		},
		
		'onStart': function() 
		{			
			var val1 = $("#acSimple2_input").val();
			var val2 = $("#clabe").val();
			
			if(val1.trim() == "" || val2.trim() == "" || $("#tipoVivienda").val() == 0)
			{
				alert("Es necesario que llenes todos los campos para finalizar tu solicitud");
				
				if(val1.length==0)
				{
					$("#acSimple2_input").focus();
					
				} else if(val2.length == 0) {
					
					$("#clabe").focus();
				}
				
				return false;
				
			} else {
				
				if(validateNextMenu('S'))
				{
					return window.confirm('¿Estás seguro que todos tus datos son correctos?');
					
				} else {
					
					return false;
				}
			}
		}
	});
	
	console.log("init_alta_inversionista(): OK");
}

function init_validation_mx_CLABE()
{
	var mx_clabe = $("#clabe").val();
	var patt1    = /\s|-/g;
	
	console.log("init_validation_mx_CLABE(): " + mx_clabe);		
	
	mx_clabe = mx_clabe.replace(patt1,"");
	
	console.log("init_validation_mx_CLABE(): " + mx_clabe);
	
	$("#clabe").val(mx_clabe);
	
	return validateLength('clabe',18);
}

function showSuccess()
{
	 facebook_events ('clicTerminarSolicitud');
	var val1 = $("#banco_cuenta_clabe_input").val();
	var val2 = $("#clabe").val();
	
	alertify.confirm('¿Estás seguro que todos tus datos son correctos?', function (e) 
			
			{
				if(e) 
				{			
					$('#dvcntClabe').hide();
					$.scrollTo('#header',300, { axis:'y' });
					$('#dvcntSuccess').show();
					
					mandodatos_hs();
					
				    var testframe = document.createElement("iframe");
				    
				    testframe.id = "testframe";
				    testframe.src = "../jsf/secciones/registro/investor/altaClientInvest.xhtml";
				    
				    $("#dvcntSuccess").empty();
				    $("#dvcntSuccess").append(testframe);
				    
				    var styles = {
				    		width : "98%",
				    		border: "0px",
				    		height: "900px",
				    		overflow: "hidden"
				    };
				    
				    $("#testframe").css(styles);
					  googleEvents ('registro-inversion', 'clic aceptar', 'boton aceptar datos correctos');
					  facebook_events ('clicAceptarSolicitud' );
					return true;
					
				} else { 
					  googleEvents ('registro-inversion', 'clic cancelar', 'boton cancelar corregir datos');
					  facebook_events ('clicCancelarSolicitud' );
					return false;
				}
			}); 	
	/*
	if(val1.trim() == "" || val2.trim() == "" || $("#tipoVivienda").val() == 0)
	{
		alertify.alert("Es necesario que llenes todos los campos para finalizar tu solicitud");
		
		if(val1.length == 0)
		{
			$("#acSimple2_input").focus();
			
		} else if(val2.length == 0) {
			
			$("#clabe").focus();
		}
		
		return false;
		
	} else {

		alertify.confirm('¿Estás seguro que todos tus datos son correctos?', function (e) 
				
		{
			if(e) 
			{			
				$('#dvcntClabe').hide();
				$.scrollTo('#header',300, { axis:'y' });
				$('#dvcntSuccess').show();
				
				mandodatos_hs();
				
			    var testframe = document.createElement("iframe");
			    
			    testframe.id = "testframe";
			    testframe.src = "../jsf/secciones/registro/investor/altaClientInvest.xhtml";
			    
			    $("#dvcntSuccess").empty();
			    $("#dvcntSuccess").append(testframe);
			    
			    var styles = {
			    		width : "98%",
			    		border: "0px",
			    		height: "900px",
			    		overflow: "hidden"
			    };
			    
			    $("#testframe").css(styles);
				  googleEvents ('registro-inversion', 'clic aceptar', 'boton aceptar datos correctos');
				  facebook_events ('clicAceptarSolicitud' );
				return true;
				
			} else { 
				  googleEvents ('registro-inversion', 'clic cancelar', 'boton cancelar corregir datos');
				  facebook_events ('clicCancelarSolicitud' );
				return false;
			}
		}); 	
	}*/
}

function reload()
{
	window.location.reload();
};