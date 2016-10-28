console.log("mx.com.kubo.mesa/administracion.js");

var menAntSel="";

/* 
 $(function() 
 {
	$( "#datepicker" ).datepicker();
}); 
*/

function setClassMenu(menu){
	if( menAntSel.length > 0){
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
