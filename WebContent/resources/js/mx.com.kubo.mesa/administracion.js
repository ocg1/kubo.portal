console.log("administracion.js");

var menAntSel="";

Administracion.notificar = function()
{
	$("#btn-administracion-notificar").trigger("click");
	
	$("#administracion-notificar-estatus").hide();
};

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

Administracion.delete_blocked_person = function()
{
	console.log("Administracion.delete_blocked_person():");
	
	$("a#delete-blocked-person").trigger("click");
};

Administracion.delete_oncomplete = function(xhr, status, args)
{
	var delete_OK = args.delete_OK;
	
	console.log("Administracion.delete_oncomplete():");
	console.log(" > delete_OK = " + delete_OK);
	
	closeMessageProcessing();
	
	Administracion.init_blocked_person_list();
};

Administracion.file_upload_oncomplete = function()
{	
	console.log("Administracion.file_upload_oncomplete(): OK");		
	
	Administracion.init_blocked_person_list();
};

Administracion.init_blocked_person_list = function()
{		
	$("a#init-blocked-person-list").trigger("click");
};

Administracion.blocked_person_list_oncomplete = function(xhr, status, args)
{
	var blocked_person_number = args.blocked_person_number;
	var citizenship = args.citizenship;
	
	console.log("Administracion.blocked_person_list_oncomplete():");
	console.log(" > citizenship = " + citizenship);
	console.log(" > blocked_person_number = " + blocked_person_number);
	
	closeMessageProcessing();
};

Administracion.init_init_citizenship = function(citizenship)
{
	console.log("Administracion.init_citizenship(): " + citizenship);
	
	$("#init_citizenship").val(citizenship).trigger("click");
};

Administracion.citizenship_oncomplete = function(xhr, status, args)
{
	var blocked_person_number = args.blocked_person_number;
	var citizenship = args.citizenship;
	
	console.log("Administracion.citizenship_oncomplete():");
	console.log(" > citizenship = " + citizenship);
	console.log(" > blocked_person_number = " + blocked_person_number);
	
	closeMessageProcessing();
};

Administracion.change_page_oncomplete = function(xhr, status, args)
{	
	var section = args.section;
	
	console.log("Administracion.change_page_oncomplete():");
	console.log(" > section = " + section);
	
	setClassMenu(section);
	
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
