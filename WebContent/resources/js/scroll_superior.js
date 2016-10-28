
/*funciones scroll horizontal para la sabana de inversionistas y mesa de control*/

$(window).load(function(){
	$(".providing_table_fake").insertBefore($("#providing_table"));
		$(function(){
		    $("#proyectInvest").scroll(function(){
		        $(".container_loanlist2").scrollLeft($("#proyectInvest").scrollLeft());
		    });
		    $(".container_loanlist2").scroll(function(){
		
		        $("#proyectInvest").scrollLeft($(".container_loanlist2").scrollLeft());
		    });
		});
		
		$(function(){
			  $("#table_proyect_list").scroll(function(){
			        $(".providing_table_fake").scrollLeft($("#table_proyect_list").scrollLeft());
			    });
			    $(".providing_table_fake").scroll(function(){
			
			        $("#table_proyect_list").scrollLeft($(".providing_table_fake").scrollLeft());
			    });
		});
});

function proyectos_activos_scroll (){
	
	if($("#fancybox-overlay").is(":visible")) {
		$.fancybox.close();
	}
		
		
			var contenedorTablaMedida = $("#proyectInvest").width();
			
			var tablaMedida = $("#proyectInvest > table").width();
			
			console.log("contenedorTablaMedida:"+contenedorTablaMedida);
			console.log("tablaMedida:"+tablaMedida);
			
			if(tablaMedida <= contenedorTablaMedida) {
				$(".container_loanlist2").hide();
		 	}else {
		 		$(".container_loanlist2").show();
		 	}

		    $("#proyectInvest").scroll(function(){
		        $(".container_loanlist2").scrollLeft($("#proyectInvest").scrollLeft());
		    });
		    $(".container_loanlist2").scroll(function(){
		
		        $("#proyectInvest").scrollLeft($(".container_loanlist2").scrollLeft());
		    });
		    

		
		   
		    
	      
}

function proyectos_activos_scroll2 (){
	if($("#fancybox-overlay").is(":visible")) {
		$.fancybox.close();
	}

			$(".providing_table_fake").insertBefore($("#providing_table"));
		    $("#table_proyect_list").scroll(function(){
		        $(".providing_table_fake").scrollLeft($("#table_proyect_list").scrollLeft());
		    });
		    $(".providing_table_fake").scroll(function(){
		
		        $("#table_proyect_list").scrollLeft($(".providing_table_fake").scrollLeft());
		    });

}



