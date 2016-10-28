$(document).ready(function() {
	$("a.unidadModal").fancybox({
				'autoScale' : 'false',
				'transitionIn' : 'elastic',
				'transitionOut' : 'elastic',
				'type' : 'iframe',
				'scrolling' : 'auto',
				'centerOnScroll' : 'false',
				'overlayColor': '#000000',
				'overlayOpacity': '0.45'
			});
	$("a#inline").fancybox({
		'autoScale' : 'false',
		'transitionIn' : 'elastic',
		'transitionOut' : 'elastic',
		'type' : 'inline',
		'scrolling' : 'auto',
		'centerOnScroll' : 'false',
		'overlayColor': '#000000',
		'overlayOpacity': '0.45',
		'width':'300px',
		'height':'300px'
	});
});


function redirectavisoLegal(){
	var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    var page = vars["page"];
    if(page=='privacidad'){
    	elementChild=$('.selected').parent();
		elementChild.find('.selected').removeClass('selected');
		$('#privacidad').addClass('selected');
    	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #AvisoPriv');
    }else{
    	$("#contentAVCon").load('../kubo/html/contenidosAvisoLeg.html #terminos_cond');
    }
}

//js para el funcionamiento de tweeter
!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
