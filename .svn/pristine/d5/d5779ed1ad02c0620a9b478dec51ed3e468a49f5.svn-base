console.log("mx.com.kubo.acreditado/registro/basicdata.js");

if($.mobile != null)
{
	$.mobile.ajaxEnabled = true;
}

$(document).ready(function() 
{	
	$(".validatorClass").bind("change blur",function(event) 
	{			
		fieldCount();
	
		if($(this).val() != "" )
		{
			$(this).removeClass("requiredClass");
		}				
		
		event.preventDefault();	
	});
			
	fieldCount();
			
	$(".mapOff").focus(function(event)
	{		 
		mapOnOff();		
	});
					
	$(".relativeLoad33").click(function()
	{		
		$("#actionFrm\\:loaderCont33").click();
		
	});

	$("#actionFrm\\:loaderCont33").change(function()
	{	
		if($.mobile != null)
		{
			$.mobile.ajaxEnabled = false;	
		}			
		
		$("#actionFrm\\:loadAction33").click();
		
	});	
			
	$("h2.expand_heading").toggle(function()
	{        	
		$(this).children().removeClass("change");	
		
	}, function () {
        	$(this).children().addClass("change");
    });
	
    $("h2.expand_heading").click(function(event)
    {   
		$(this).next(".toggle_container").slideToggle("slow");
		event.preventDefault();
    });
});
	 
function uploadStart()
{
	alert("Empazando ...  !!!");
}

function validaAddress()
{
	if( $("#coloniaEmp").val() == 0 )
	{
		alert(" La colonia elegida es incorrecta ");
		
		$("#coloniaEmp").addClass("requiredClass");
		
		return false;
	}
	
	return true;
}

function redirectAction()
{
	window.location = "forgotpass.jsf";
}

function clickForward()
{
	$("#btnForward").click();
}

function clickExit()
{	
	$("#btnExit").click();
}

var xWater = 0;
