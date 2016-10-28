var sectionmail;
var sectionpass;
var sectionmsg;
var sectionname;
var section_preference;
var formcontent;
var labelmsg;

console.log("editperfil.js");

$(document).ready(function() {
	inicializaValoresPerfil();
});

function ponerValidacionPass() {
	$("#pass").addClass("validatePass");
	inicializaValores();
	$("#changePass").click(function () {			
		validaChangePass();
	});
	
}	
function inicializaValoresPerfil() {	
	inicializaValores();
	
	sectionmail = $("#section-update-mail");
	sectionpass = $("#section-update-pass");
	sectionmsg  = $("#section-display-message");
	sectionname = $("#section-update-completename");
	section_questions  = $("#section-update-questions");
	section_preference = $("#section-notification-preference");	
	
	formcontent = $("#frm-change-data").get(0);
	labelmsg    = $("#lbl-msg-server");
	
	$("#link_notification_preference").click(function () 
			{				
				if(section_questions.is(":hidden"))
				{
					sectionmail.hide();
					sectionpass.hide();			
					sectionmsg.hide();
					sectionname.hide();
					section_questions.hide();
					section_preference.slideDown('slow');
					
				} else {
					
					section_preference.slideUp('slow');
				
				}
				
			});	
	
	$("#link_preguntas_seguridad").click(function () 
	{				
		if(section_questions.is(":hidden"))
		{
			sectionmail.hide();
			sectionpass.hide();			
			sectionmsg.hide();
			sectionname.hide();
			section_preference.hide();
			section_questions.slideDown('slow');
			
		} else {
			
			section_questions.slideUp('slow');
		
		}
		
	});	
	
	$("#link-edit-completname").click(function () 
	{
		if(sectionname.is(":hidden"))
		{
			sectionmail.hide();
			sectionpass.hide();			
			sectionmsg.hide();
			section_questions.hide();
			section_preference.hide();
			sectionname.slideDown('slow');
		}	
		
	});	
	
	$("#link-mail").click(function () 
	{
		if(sectionmail.is(":hidden"))
		{			
			formcontent.reset();
			
			sectionpass.hide();
			sectionname.hide();			
			sectionmsg.hide();
			section_preference.hide();
			section_questions.hide();
			sectionmail.slideDown('slow');
		}else {
			sectionmail.slideUp('slow');
		}			
		
		
	});		
	
	$("#link-pass").click(function () 
	{
		if(sectionpass.is(":hidden"))
		{	
			formcontent.reset();
			
			sectionname.hide();
			sectionmail.hide();
			sectionmsg.hide();	
			section_preference.hide();
			section_questions.hide();
			
			$("#pass").val("");
			$("#passConf").val("");
			$("#passAnt").val("");
			
			sectionpass.slideDown('slow');				
		}
		else {
			sectionpass.slideUp('slow');
		}	
	});
}

function oncompleteSubmit(xhr, status, args)
{
	sectionmsg.show();
			
	if(args.isValid)
	{
		if(args.typeedit!=2)
		{
			if(args.typeedit==1)
			{
				closeMessageProcessing();
				$("#img-bullet-error").hide();
				$("#img-bullet-true").show();
				labelmsg.css("color","#333333").text(args.msg);
			
				window.location.reload();
						
			} else {
				
				$("#img-bullet-error").hide();
				$("#img-bullet-true").show();
				labelmsg.css("color","#333333").text(args.msg);
				sectionmail.hide();
				sectionpass.hide();
				sectionname.hide();
				closeMessageProcessing();
				alertify.alert("Ha cambiado tu contraseña");
			}
	
		} else {
			
			window.location = "../jsf/activationCount.xhtml?mail="+args.msg;					
		}
				
	} else {				
		
		alertify.error( args.msg );
		sectionmsg.show();
		$("#img-bullet-true").hide();
		$("#img-bullet-error").show();
		labelmsg.css("color","#DD4B39").val(args.msg);
		closeMessageProcessing();		
	}
	
	inicializaValoresPerfil();
}
		
function hideElement(component)
{			
	$("#"+component).slideUp();		
	sectionmsg.hide();
	formcontent.reset();
}
	       
function validaResp()
{	
	if(validaConfRes('resp','confresp'))
	{		
		if(validaConfRes('resp2','confresp2'))
		{		
			if(validaConfRes('resp3','confresp3'))
			{				
				if($("#preg").val() == $("#preg2").val() || $("#preg").val() == $("#preg3").val() || $("#preg2").val() == $("#preg3").val()  )
				{					
					alert("Por tu seguridad debes elegir preguntas diferentes.");
					
					return false;
					
				} else {
				
					return validateField();
					
				}			
			}			
		}		
	}
	
	return false;
}

function preference_id_on_complete(xhr, status, args)
{
	var preference_id = args.preference_id;
	
	console.log("preference_id_on_complete(): " + preference_id);
}

function persist_preference_id_on_complete(xhr, status, args)
{
	var is_saved_OK       = args.is_saved_OK;
	var change_control_OK = args.change_control_OK;
	
	console.log("persist_preference_id(): ");
	console.log("> is_saved_OK:       " + is_saved_OK);
	console.log("> change_control_OK: " + change_control_OK);
	
	closeMessageProcessing();	
	
	inicializaValoresPerfil();
}


