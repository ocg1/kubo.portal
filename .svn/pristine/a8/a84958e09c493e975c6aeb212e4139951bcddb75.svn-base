

/*  boton del menu depositos movimientos */
var depositos;

var retiros;

function retirosMov () {

	$("#cashWithdrawal").trigger("click");
	 retiros = true;
}



function depositosMov () {

	$("#cashWithdrawal").trigger("click");
	 depositos = true;
}

	function callback_movimientos()
	{
		closeFancy();
		console.log("depositos"+ depositos);
		console.log("retiros"+ retiros);
		if(depositos == true) {
			$("#tipo-movimiento-deposito").click();
			depositos = false;
		}
		else if(retiros == true) {
			$("#tipo-movimiento-disposicion").click();
			retiros = false;
		}else {
		
		}
		resetMov();
		 $(".tituloInv h2").html("Movimientos");
		 $( ".header").css("position", "fixed");
		 var width = $(window).width();	
		 if (width <= 768) { 
			 $(".invertirOtroBtn").show();
			 $(".saldosBtn").hide();
		 }
	}
	function resetMov() {
		 $(window).resize();
		 abrirMenuLateral ();
		 romperRegreso();
	}


