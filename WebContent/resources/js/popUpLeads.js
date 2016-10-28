
function envioCorreoLead() {


   var emailAddress = $("#emailLead").val();

   function isValidEmailAddress() {
	
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
   }

if( !isValidEmailAddress( emailAddress ) ) {
	 alertify.alert("Escribe un correo válido.");
	 console.log(emailAddress);
 }else {
	     $("#inputTextPopLeads").val(emailAddress);
 	     $("#inputTextPopLeads").blur();
 	     
 	     $(".clsBtnPopLeads").trigger("click");
 	     
 	    console.log("inputTextPopLeads: " + $("#inputTextPopLeads").val() );

    	$(".texto.uno").slideUp("slow");
    	$(".texto.dos").slideDown("slow");
		
    	
   
    	/*comentado hubspot
    	var ip  = $("#ip_input").val();
		var URI = "";
		var str = "";
	     
        jason = [];
        hs_context = [];
               
        //  str = publicacion.val();
        context = {}
    	context ["hutk"] = $.cookie('hubspotutk')
    	context ["ipAddress"] = ip
        context ["pageUrl"] = "http://www.kubofinanciero.com"
        context ["pageName"] = "PopUpHome"
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
        URI = "email="+emailAddress+"&"+"hs_context="+URI;
        
        console.log("");
        console.log("");
        console.log(URI);
           
        $.ajax({
        	type: "POST",
        	url: "https://forms.hubspot.com/uploads/form/v2/477732/1b3e596c-c892-458f-bae1-7ba2359c4823",
        	data: URI,
        	success: function(){},
        	dataType: "text"
        });     
      */
 		return true;
 		
	 }
	
}
function abre_popLeads() {
	
	
		if (document.cookie.indexOf('visitedPop=true') == -1) {
	        var fifteenDays = 1000000*60*60*24*5;
	        var expires = new Date((new Date()).valueOf() + fifteenDays);
	        document.cookie = "visitedPop=true;expires=" + expires.toUTCString();
	    	/*
		if(!window.localStorage.getItem("visited") ){
	*/
	    	setTimeout(function(){ 
	       		$(".popUpLeads").addClass("show");
	       		$(".velo").fadeIn();
	    	   	},2000);
	    	 $(".closePopUp, .velo" ).click(function() {
	    			$(".popUpLeads").removeClass("show");
	    			$(".velo").fadeOut();
	    	 });
			   window.localStorage.setItem("visited", true);
			   
		}
		
	
	    	
	   else {
	    
	    }
	   
	   
	
   
}

$(document).ready(function(){
	if($(".banner.bannerFondo").length) {
		// abre_popLeads();
	}
	console.log("abre_popLeads();");
	$('.closePopUp').click(function(){
		$(".popUpLeads").removeClass("show");
		$(".velo").fadeOut();
   });
});




