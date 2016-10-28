$(document).ready(function()
{	
	hover_header_menu_items();
});



function hover_header_menu_items()
{
	
	

		$("a.iframe2").fancybox({
			'width' : '75%',
			'height' : '75%',
			'autoScale' : 'false',
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'type' : 'iframe',
			'scrolling' : 'auto',
			'centerOnScroll' : 'true',
			'overlayColor': '#E6E6E6'
		});
			
	
	$("div.main_lista_secciones div").hover(function() 
	{
		$( this ).find("a").css("color","white");
		
	}, function() {
		$( this ).find( "a" ).css("color","black");
	});
}

function selecciona_menu_item(id)
{	
	var select = document.getElementById("menuSelect").value;
	
	
	if(select != "")
	{
		document.getElementById(select).style.backgroundColor = "transparent";
		document.getElementById(select).style.color = "#39373a";
		document.getElementById(select).style.fontWeight = "normal";
	}

	//document.getElementById(id).style.backgroundColor = "#E6E6E6";
	document.getElementById(id).style.backgroundColor = "#C1D82F";
    document.getElementById(id).style.color = "#000000";
    document.getElementById("menuSelect").value= id;
}

function imprimir_panel(panel_id)
{		
	var header = '' 
		+ '<html><head>'
		+ '<link type = "text/css" rel = "stylesheet" href = "../resources/css/tipografia.css" />'
		+ '<link type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/fichas_producto/tipos_credito.css" />'
		+ '</head><body>';
	
	var header_fin  = '</body></html>';
	var header_logo = '<img src="../resources/img/LOGO.png" style="height:100px; width: 100px; border: 0px; "/>';
	var razon_social = ''
		+ '<div style ="height:100px; width: 265px; border:0px solid black; float:right ">'
		+ '<b>ku-bo financiero,'
		+ 'S.A. DE C.V., S.F.P. </b><br />'
		+ 'Barranca del Muerto 92, Col. Florida, <br />'
		+ 'C.P. 01030, México D.F.'
		+ '</div>';
	
	var panel = $('#' + panel_id);

	var pop_up = window.open('','popimpr');
	
	pop_up.document.write(header);
	pop_up.document.write(header_logo);
	pop_up.document.write(razon_social);
	pop_up.document.write(panel.html());
	pop_up.document.write(header_fin);
	
	pop_up.print();
	pop_up.close();	
}

function init_google_analytics()
{

/******************GOOGLE*ANALYTICS**************************************/  
	
	_gaq.push(['_setAccount', 'UA-40649010-1']);
	_gaq.push(['_trackPageview']);

	(function() 
	{

		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;

		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';

		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);

	})();

  /*****************FIN*GOOGLE*ANALYTICS**********************************/
}  

var _gaq = _gaq || [];
init_google_analytics();