
function resumen_saldos () {
	 saldo_kubo_impulso ();
	 $(".tituloInv h2").html("Resumen de saldos");
	 botonRegresar();
	 var width = $(window).width();	
	 if (width <= 768) { 
		 $(".invertirOtroBtn").show();
		 $(".saldosBtn").hide();
		
	 }
	 $( ".header").css("position", "fixed");
	 raShow();
}

function saldo_kubo_impulso (){
	var inv_activas = $(".inv_activas").text();
	    inv_activas = Number(inv_activas.replace(/[^0-9\.]+/g,""));
	    //console.log(inv_activas);
	var inv_activasAtra = $(".inv_activasAtra").text();
		inv_activasAtra = Number(inv_activasAtra.replace(/[^0-9\.]+/g,""));
	    //console.log(inv_activasAtra);
	var int_cobrar = $(".int_cobrar").text();
	    int_cobrar = Number(int_cobrar.replace(/[^0-9\.]+/g,""));
	    //console.log(int_cobrar);
	var int_cobrarAtra = $(".int_cobrarAtra").text();
		int_cobrarAtra = Number(int_cobrarAtra.replace(/[^0-9\.]+/g,""));
		//console.log(int_cobrarAtra)
	var saldo_total_kubo_impulso =  inv_activas + inv_activasAtra + int_cobrar + int_cobrarAtra; 	   
	$(".saldo_total_kubo_impulso").text("$" +(saldo_total_kubo_impulso).toFixed(2)).digits();
}