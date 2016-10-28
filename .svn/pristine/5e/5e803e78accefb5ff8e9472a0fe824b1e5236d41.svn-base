$(document).ready(function() {
	resetar_indices();

});		
	



function resetar_indices() {
	 $("input.validatorClass, textarea.validatorClass, select.validatorClass").each(function(index) {
	    return $(this).attr("index", "");
	 });
	crear_indices(); 
	blur_indices();
	focus_indices();
	
}
function crear_indices() {
	$("input.validatorClass, textarea.validatorClass, select.validatorClass, .validateChecks").each(function(index) {
		return $(this).attr("index", index);
	});
}
function focus_indices() {
		 $("textarea.validatorClass, input.validatorClass,  select.validatorClass").focus(function(){
			    var this_element = $(this);
	    	 	var this_indice = $(this).attr("index");
	    	 	    this_indice = Number(this_indice.replace(/[^0-9\.]+/g,""));
	    	 		validacion_llenado(this_indice);			    	 		
	    	 	
		 }); 
	
} 
function blur_indices() {
	
		  $("textarea.validatorClass, input.validatorClass, select.validatorClass").blur(function(){
			    var this_element = $(this);
				if($(this).is('input') || $(this).is('textarea')) {
			    	 if($(this).val().length==0)  {
			    	
			    		 $(this).removeClass("lleno");
				    	 $(this).addClass("vacio");
				    	 
			    	 }else {
			    		 $(this).removeClass("vacio");
			    		 $(this).addClass("lleno");
			    		
			    	 }
				}
			     if( $(this).is('select')) {
			    	if($(this).val() == '0' || $(this).val() == ''  || $(this).val() == null ) {
				    	 $(this).closest(".selectNuevo").removeClass("lleno");
		 		    	 $(this).closest(".selectNuevo").addClass("vacio");
			    	 }else{
			    		 $(this).closest(".selectNuevo").addClass("lleno");
		 		    	 $(this).closest(".selectNuevo").removeClass("vacio");
			    	 }
 		    	 }
			 }); 

	 }

	function validacion_llenado(this_indice) {
		 $(".validatorClass[index]").each(function() {
			   var indice_elemento = $(this).attr("index");
			   	   indice_elemento = Number(indice_elemento.replace(/[^0-9\.]+/g,""));
			   	   
	 			if( this_indice > indice_elemento ) {
	 				if($(this).is('input') && $(this).val().length==0){
	 					
	 						 $(this).removeClass("lleno");
	 		 		    	 $(this).addClass("vacio");

	 				}
	 				
	 				if($(this).is('input') && $(this).val().length!=0){
		 		    	$(this).removeClass("vacio");
		 	    		$(this).addClass("lleno");
	 		    	}
	 				
	 				if($(this).is('input') || $(this).is('textarea')) {
				    	 if($(this).val().length==0)  {
				    	
				    		 $(this).removeClass("lleno");
					    	 $(this).addClass("vacio");
					    	 
				    	 }else {
				    		 $(this).removeClass("vacio");
				    		 $(this).addClass("lleno");
				    		
				    	 }
					}
				     if( $(this).is('select')) {
				    	if($(this).val() == '0' || $(this).val() == ''  || $(this).val() == null ) {
					    	 $(this).closest(".selectNuevo").removeClass("lleno");
			 		    	 $(this).closest(".selectNuevo").addClass("vacio");
				    	 }else{
				    		 $(this).closest(".selectNuevo").addClass("lleno");
			 		    	 $(this).closest(".selectNuevo").removeClass("vacio");
				    	 }
	 		    	 }
	 		    	 
	 			}
	 		
	 			
	 			
	 			
		 });
	}
	

	
