/*
function pasos_ids (){
	
	var str = ""; 

	$("input, textarea, select").each(function() {
		var id = $(this).attr("id"); 
	
		var tipo =	$(this).prop('tagName');
		 if($(this).hasClass("validatorClass" )){
	
			if(  $(this).attr("id").indexOf("uiemp:0:") != (-1) ) {
				id = id.replace("uiemp:0:", "");	
				 str += id +"//"+ tipo+ " "+ '\n';
			}
			else if ( $(this).attr("id").indexOf("frm_questPLD:") != (-1)){
				id = id.replace("frm_questPLD:", "");	
				str += id +"//"+ tipo+ " "+ '\n';	
			}
			else if ( $(this).attr("id").indexOf("uibus:0:") != (-1)){
				id = id.replace("uibus:0:", "");	
				str += id +"//"+ tipo+ " "+ '\n';	
			}
			else {
				str += id +"//"+ tipo+ " "+ '\n';
			}
		 }
	});

console.log(str);

}
*/ 
$(document).ready(function(){
		

		 $(function() {
				$.getJSON("https://api.ipify.org?format=jsonp&;callback=?",
				  function(json) {
					$("#ip_input").val( json.ip);
				  }
				);
			  });

		
	});

	function  mandodatos_hs() {
		
		console.log("");
		console.log("");
		console.log("mandodatos_hs   Portal/resources/js");
		console.log("");
		console.log("");
		
		if( ($("#cbo-promotor").val()).trim() == "" || $("#cbo-promotor").is(":hidden")){
			
			var ip  = $("#ip_input").val();
			var URI = "";
			
			var str = "";
	        jason = [];
	        hs_context = [];
	       
	        $(".hs_class").each(function() {
	        	var id = "";
	        	var flag1 = true;

	        	 if($(this).attr("id") == "name" ){
	        		 
					id = "firstname";
	        	
	        	 }else if(  ( $(this).attr("id") ).indexOf("email") != (-1)  ){

	        		 id = "email";

	        	 }else{

	        		 id = $(this).attr("id");
	        		 
	        		

	        	 }
	        	
	           	
		        	 if($(this).is('select')) {
		        		 str += id + "=" + $(this).find('option:selected').text() + "&"; 
		        	 }else {
		        		 str += id + "=" + $(this).val() + "&";
		        	 }
	        	
	        	
	        	 console.log(str);
	        });
	         
	        context = {}
	    	context ["hutk"] = $.cookie('hubspotutk')
	    	context ["ipAddress"] = ip
	        context ["pageUrl"] = "http://www.kubofinanciero.com/Kubo/Portal/acreditado/preregistro/comenzar-registro.xhtml"
	        context ["pageName"] = "Preregistro"
	        context ["redirectUrl"] = "http://www.kubofinanciero.com/Kubo/Portal/acreditado/preregistro/bienvenida_prestamo.xhtml"
	        
	        	
	        
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
	        
			URI = str + "hs_context="+URI;
	        console.log(str);
	        console.log(URI);
	       //  $("#dvContenidoURI").html(str + "hs_context="+URI );
	        console.log("");
	        console.log("");
	        console.log("");
	           
	        $.ajax({
	        	type: "POST",
	        	url: "https://forms.hubspot.com/uploads/form/v2/477732/883b3749-cbe7-47a0-9bdd-9a66d9464f84",
	        	data: URI,
	        	success: function(){},
	        	dataType: "text"
	        });     
	      
	 		return true;
	 		
		}else{
			return true;
		}
	}