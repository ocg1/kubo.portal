console.log("menuregistro.js");
			
function showRes()
{
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}

function loader()
{
	
	$("#resultadosSim").css("display","none");
	$("#loaderSim").css("display","block");
	
	 return true;
}

function simulatorMirror(){
	
	$("#ammount").val( $("#ammount_input").val() );
	
	var f = simOnStartVal_e();
	
	console.log( "regrasando: " + f);
	
	if( !f ){
		
			$("#ammount_input").val( $("#ammount").val() );
		
	}
	
	return f  ;
	
}

function simOnStartVal_e()
{
	console.log( "menuregistro.js" );
	if( validaMontoMin('ammount','simulator') )
	{
		//alert("regresa true");
		loader();
		var ent = ($("#ammount").val()+"").replace(",","");
		var input = $("#ammount");
		if(!isNaN(ent))
		{
		 	/* if(parseFloat(ent)>(50000)){
			    input.value = "50,000";
			    //input.blur();
			    showRes()
				return false;
			    
			 }else{ */
				 
				return true;
		 	// }
		}else{
			alert("Cantidad invalida " + ent +"  --  ");
			input.value="";
			//showRes()
			return false;
		}				 
	}				
}
			
function simOnStart()
{		
	if( validaMontoMin('ammount','simulator') )
	{		
		loader();
		
		if(!validaPlazo('term'))
		{
			//showRes();
			return false;
		}else{
			return true;
		}
	}
}
									
function showRelPersonCont()
{		
	$('#dvRelPerson').show( "blind", 500 );		
}

function showSamePersonCont()
{	
	$('#dvSamePerson').show( "blind", 500 );		
}

function msgSameOff()
{	
	$('#dvSamePerson').hide( "blind", 500 );		
}
		
function msgOff()
{	
	$('#dvRelPerson').hide( "blind", 500 );		
}
/*	
function showMenu()
{

	var menuActive;
	
	$("#dvBtnMenu").css("visibility","hidden"); //( function (){ 
		
	$("#dvContMenuREG").show("slide", { direction: "left" }, 200);
		
	// } );
	 var width = $(window).width();	
	 if (width <= 850) { 
	
		 setTimeout(function(){ 
			 $("#dvBtnMenu").addClass("active"); 
		  }, 300);
		 if($("#dvBtnSimulador").hasClass("active")) {
		 	hideSimulador();
		 }
		 
		
		 
	 }

	
}
*/

function showMenu()
{

	var menuActive;

		
	// } );
	 var width = $(window).width();	
	 if (width <= 850) { 
		 $("#dvContMenuREG").css("opacity", "1");
		 
		 if($("#dvBtnMenu").hasClass("active")) {
			 hideMenu();
			 $("#dvBtnMenu").removeClass("active"); 
		 
		 }else {
			 $("#dvContMenuREG").show("slide", { direction: "left" }, 200);
			 if($("#dvBtnSimulador").hasClass("active")) {
			 	hideSimulador();
				
			 }
			 setTimeout(function(){ 
				 $("#dvBtnMenu").addClass("active"); 
			  }, 300);
			
		 }
		
		 
	 }else {
			
			$("#dvBtnMenu").css("visibility","hidden"); //( function (){ 
				
			$("#dvContMenuREG").show("slide", { direction: "left" }, 200);
	 }

	
}


function hideMenu()
{		
	$("#dvBtnMenu").removeClass("active"); 
    $("#dvContMenuREG").hide("slide", { direction: "right" }, 200,function ()
    { 
    	$("#dvBtnMenu").css("visibility","visible"); 
    });
}
			
function showSimulador()
{
	$("#dvBtnSimulador").css("visibility","hidden");
    

	
	
	$(".helpFrm").show("slide", { direction: "right" }, 200);
	
	 $.scrollTo("#header"); 		    
	 
	 var width = $(window).width();	
	 if (width <= 850) { 
		 if($("#dvBtnMenu").hasClass("active")) {
			 hideMenu(); 
		 }
		 $("#dvBtnSimulador").addClass("active");
		  
	 }
	 if (width <= 600) { 
		$(".velo").fadeIn();
	 }
}
			
function hideSimulador()
{		var width = $(window).width();	
	 if (width <= 600) { 
			$(".velo").fadeOut();
		 }
	$("#dvBtnSimulador").removeClass("active");
    $(".helpFrm").hide("slide", { direction: "left" }, 200,function (){ $("#dvBtnSimulador").css("visibility","visible"); });
}
			
			