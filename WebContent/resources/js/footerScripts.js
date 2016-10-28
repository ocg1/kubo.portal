/*
------------------------------------------
retornar a footer antiguo

regresar contenido de footerContenido
de registro.xhtml regresar footer.css y footer_registro.css
quitar de footer_mapa.xhtml, la css y scripts de modales
kubo_style.css regresar anterior css
regresar scripts.js

*/


/*cambiar a footer unico */
/*footer contenido llamaria a footer_mapa.xhtml de templates portal, al igual que los lightbox condusef y cat 
 * borrar las funciones del script varias funciones formulario
 * actualizar kubo.css y subir footerStyles y footerscripts/, recordar quitar de todos los htmls las css relacionadas con el footer
 * recordar checar lightbox 
 * */


function altura_footer_fix() {
	var altura_footer_mapa = $(".footer_mapa").height();
	 $('.fix').css({
		 height:  altura_footer_mapa

	 });
}


$(document).ready(function() {
$('.open_lightbox').click(function(){
	
			myapp = {active: false}; 
			
			$('#light' + $(this).attr('data-div')).fadeIn('slow');
			$(".recuperar").hide(); //aqui cierro recuperar		
		});
		
$('.close_lightbox').click(function(){
	myapp = {active: true}; 
	$('.lightbox').fadeOut('slow');	
});

});
 

/*
    	 $(window).resize(function(event){
 
     		   var resizeId2;
     		   clearTimeout(resizeId2);
     		   resizeId2 = setTimeout(altura_footer_fix, 100);

 	});

*/
	


	/*
	$(window).resize(function(event){
				 $('.centrar_modal').css({
					 position:'absolute',
					left: ($(window).width() - $('.centrar_modal').outerWidth())/2,
				  });	
				 var width = $(window).width();	
				
				 
			});
*/