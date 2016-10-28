function contador() {
	
	var tiempoAsignado = 120000;
	var time = new Date(tiempoAsignado);
	var intervalo;
	$(".mensajeFinTiempo").hide();
    $(".validarCode").show();

	function finTiempo(){
	    $(".mensajeFinTiempo").show();
	    $(".validarCode").hide();
	    
	}
	
	setTimeout(function(){
		$(".reset").trigger("click");
		$(".start").trigger("click");
	},300);
	    
	    $(".start").on("click", function(){
		    intervalo = setInterval(function(){
		        time = new Date(time - 1000);
		        if(time<=0){
		        	finTiempo();
		            clearInterval(intervalo);
		        }
		        displayTime();
		    }, 1000);
	    });
	    
	    $(".stop").on("click", function(){
	        clearInterval(intervalo);
	        time = new Date(tiempoAsignado);
	        displayTime();
	    });
	    
	    $(".pause").on("click", function(){
	        clearInterval(intervalo);
	    });
	    
	    $(".reset").on("click", function(){
	        time = new Date(tiempoAsignado);
	        displayTime();
	    });


	function displayTime(){
	    $(".time").text(fillZeroes(time.getMinutes()) + ":" + fillZeroes(time.getSeconds()));
	}

	function fillZeroes(t){
	    t = t+"";
	    if(t.length==1)
	        return "0" + t;
	    else
	        return t;
	}

}