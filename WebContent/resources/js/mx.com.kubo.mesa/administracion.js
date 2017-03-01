console.log("administracion.js");

var menAntSel="";

Administracion.notificar = function()
{
	$("#btn-administracion-notificar").trigger("click");
	
	$("#administracion-notificar-estatus").hide();
};

Administracion.delete_blocked_person = function()
{
	console.log("Administracion.delete_blocked_person():");
};

/* 
	onstart    = "displayMessageProcessing('msgprocessing',false);"
	oncomplete = "Administracion.blocked_person_oncomplete(xhr, status, args);"
 */

Administracion.notificar_oncomplete = function(xhr, status, args)
{
	var notificar_OK = args.notificar_OK;
	var response     = args.response;
	var email_date_ENABLED = args.email_date_ENABLED;
	
	console.log("Administracion.notificar_oncomplete():");
	console.log("> notificar_OK = " + notificar_OK);
	console.log("> email_date_ENABLED = " + email_date_ENABLED);
	console.log("> response = " + response);
	
	if(notificar_OK)
	{
		$("#administracion-notificar-estatus").show();
	}
	
	closeMessageProcessing();
};

Administracion.blocked_person_oncomplete = function(xhr, status, args)
{
	var notificar_OK = args.path_file_LOG;;
	
	console.log("Administracion.notificar_oncomplete():");
	console.log("> path_file_LOG = " + path_file_LOG);	
	
	closeMessageProcessing();
};

function setClassMenu(menu)
{
	if( menAntSel.length > 0)
	{
		$("#"+menAntSel).removeClass('clsMenuSel');
	}
	
	$("#"+menu).addClass('clsMenuSel');
	menAntSel = menu;
	
}

function completeAction(msg){
	
	closeMessageProcessing();
	$("#"+msg).fadeIn('slow').delay(2000).fadeOut('slow');
	
}

function initAction(elemtH,elemtS){
	
	$('#'+elemtH).fadeOut('slow',function(){
		
		$('#'+elemtS).fadeIn('slow');
		
			return true;
		/* $('#'+elemtS).fadeIn('slow',function(){
			displayMessageProcessing('msgprocessing',false);
		}); */
	});
	
	
}

function completeDisplayElements(elemH,elemS){
	
	$('#'+elemH).fadeOut('slow',function(){
		
		$('#'+elemS).fadeIn('slow',function(){
			
			setTimeout('clickAction()',5000);
			
		});
		
		return true;
		
	} );
	
}

function clickAction(){
	$("#updateAction").click();
}

function displayElements(elemH,elemS,elemtD)
{	
	$('#'+elemH).fadeOut('slow',function()
	{		
		$('#'+elemS).fadeIn('slow');
		
		if(elemtD!=null)
		{		
			$('#'+elemtD).css('display','block');			
		}
		
		return true;
	} );
}
