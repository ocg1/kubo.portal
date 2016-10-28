$(document).ready(function() {
	
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
	        vars[key] = value;
	    });
	    var page = vars["page"];
	    if(page=='privacidad'){
	    	elementChild=$('.selected').parent();
			elementChild.find('.selected').removeClass('selected');
			$('#privacidad').addClass('selected');
	    	$("#contentAVCon").load('../html/contenidosAvisoLeg.html #AvisoPriv');
	    }else{
	    	$("#contentAVCon").load('../html/contenidosAvisoLeg.html #terminos_cond');
	    }
	

	$("#teminos").click(function(){
			elementChild=$(this).parent();
			elementChild.find('.selected').removeClass('selected');
			$(this).addClass('selected');
			$("#contentAVCon").load('../html/contenidosAvisoLeg.html #terminos_cond');
	});
	$("#privacidad").click(function(){
			elementChild=$(this).parent();
			elementChild.find('.selected').removeClass('selected');
			$(this).addClass('selected');
			$("#contentAVCon").load('../html/contenidosAvisoLeg.html #AvisoPriv');
	});
	$("#privCandi").click(function(){
			elementChild=$(this).parent();
			elementChild.find('.selected').removeClass('selected');
			$(this).addClass('selected');
			$("#contentAVCon").load('../html/contenidosAvisoLeg.html #privCandiCont');
	});
	$("#redessociales").click(function(){
			elementChild=$(this).parent();
			elementChild.find('.selected').removeClass('selected');
			$(this).addClass('selected');
			$("#contentAVCon").load('../html/contenidosAvisoLeg.html #redessocialesCont');
	});
});