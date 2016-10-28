/* recoger la hora */

var dt = new Date();
var time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
var displayDate = (dt.getMonth()+1) + '/' + (dt.getDate()) + '/' + dt.getFullYear();	
var date_hour = displayDate+":"+time

$(document).ready(function(){
		 $(function() {
				$.getJSON("https://api.ipify.org?format=jsonp&;callback=?",
				  function(json) {
					$("#ip_input").val( json.ip);
				  }
				);
			  });
			var hubspotInterval = setInterval(function(){
				mandodatos_hs();
			}, 540000);			
});
	
 function  datos_hubspot_preaprobacion_publicacion (cadena) {
	 
	     /*-- la cadena no debe de tener un ampersand--*/
		var ip  = $("#ip_input").val();
		var URI = "";
		var str = "";
	     
        jason = [];
        hs_context = [];
               
        //  str = publicacion.val();
        context = {}
    	context ["hutk"] = $.cookie('hubspotutk')
    	context ["ipAddress"] = ip
        context ["pageUrl"] = "http://www.kubofinanciero.com/Kubo/jsf/registro.xhtml"
        context ["pageName"] = "Registro"
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
        URI = cadena+"&"+"hs_context="+URI;
        
        console.log("");
        console.log("");
        console.log(URI);
           
        $.ajax({
        	type: "POST",
        	url: "https://forms.hubspot.com/uploads/form/v2/477732/883b3749-cbe7-47a0-9bdd-9a66d9464f84",
        	data: URI,
        	success: function(){},
        	dataType: "text"
        });     
      
 		return true;
 }
 

	function  mandodatos_hs() {
		
		console.log("");
		console.log("");
		console.log("mandodatos_hs   WebContent/resources/js");
		console.log("");
		console.log("");
		
			var ip  = $("#ip_input").val();
			var URI = "";
			var str = "";
			var thisMail = $("#thisMail").val();
			var thisProspecto = $("#prospectoID").val();
	        jason = [];
	        hs_context = [];
	        
		    $(".hs_class").each(function() {
		    	
		    		var element = null;
		    		
		    		element = $(this); 
		    		
		    		var id = element.attr("id")
		    		
		    		if( id.indexOf("tblBenefi:0:") != (-1) ){
		    			id = id.replace("tblBenefi:0:", "beneficiario_1") ;	
		    		}
		    		
		    		if( id.indexOf("tblBenefi:1:") != (-1) ){
		    			id = id.replace("tblBenefi:1:", "beneficiario_2") ;	
		    		}
		    		
		    		if( id.indexOf("tblBenefi:2:") != (-1) ){
		    			id = id.replace("tblBenefi:2:", "beneficiario_3") ;	
		    		}
		    		
		    		if( id.indexOf("tblBenefi:3:") != (-1) ){
		    			id = id.replace("tblBenefi:3:", "beneficiario_4") ;	
		    		}
		    	
		        	
		        	var flag1 = true;
		        	var select_val = "";
		        	var typeId ="";

		           if(  element.attr("id").indexOf("uiemp:0:") != (-1) ) {
			 				id = id.replace("uiemp:0:", "uiemp_0_");			
			 		}
		        	 else if ( element.attr("id").indexOf("frm_questPLD:") != (-1)){
		 				id = id.replace("frm_questPLD:", "frm_questPLD_");	
		 				
		 			}
		 			else if ( element.attr("id").indexOf("uibus:0:") != (-1)){
		 				id = id.replace("uibus:0:", "uibus_0_");	
		 				 	
		 			}
		       		if ( !element.is('img') ) {
		       			 
		       			if(element.hasClass("hs_private")){
			        		 str += id + "=****&";
			        		  
			        	 }else {
				        		 if( element.is('select')) {
					        		 str += id + "=" + element.find('option:selected').text() + "&"; 
					        	 }
				        		 
				        		 else  if( element.is('table')){
				        			 	var valorRadio= element.find(":radio:checked").next("label").text();
				        			 	str += id + "=" +valorRadio + "&"; 
				        		 }
				        		 
				        		 else {
					        		 str += id + "=" + element.val() + "&"; 
					        	 }
				     
			        	 }
		       		}
		        	 /*
		        	 typeId += id + type;
		        	console.log(id+"//"+type+ "::");
		        		*/
		        });
		      
		  if($("#inputSegmentName").val() != ""){
			   str += "tipo-cliente =" + $("#inputSegmentName").val()+"&";
		  }
		  
		  if($("#inputSegmentName").val() != ""){
			   str += "KuboScore =" + $("#inputKuboScore").val()+"&KuboScore_int"+$("#inputKuboScore").val()+"&";
		  }
		    /*
		    context = {}
	    	context ["hutk"] = $.cookie('hubspotutk')
	    	context ["ipAddress"] = ip
	        context ["pageUrl"] = "http://www.kubofinanciero.com/Kubo/jsf/registro.xhtml"
	        context ["pageName"] = "Registro"
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
			URI = "prospectoID=" + thisProspecto + "&"+ "mail="+thisMail+"&"+ str  + "hs_context="+URI;

	       //  $("#dvContenidoURI").html(str + "hs_context="+URI );
	        console.log("");
	        console.log("");
	        console.log(URI);
	           /**
	        $.ajax({
	        	type: "POST",
	        	url: "https://forms.hubspot.com/uploads/form/v2/477732/883b3749-cbe7-47a0-9bdd-9a66d9464f84",
	        	data: URI,
	        	success: function(){},
	        	complete: function(jqXHR, status) {
	                // alert('Petición realizada');
	            },
	        	dataType: "text"
	        });     
	      
	        */
	        
	        $("#hs_values").val( str ).blur();
	        
	 		return true;
	}

 	
 	