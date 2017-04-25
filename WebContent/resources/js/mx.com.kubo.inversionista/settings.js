console.log("settings.js");

Settings.init_menu = function(menu_item)
{
	console.log("Settings.init_menu():" + menu_item);
	
	$("#settings-menu-item").val(menu_item).trigger("click");
};

Settings.menu_oncomplete = function(xhr, status, args)
{
	var menu_item = args.menu_item;
	
	console.log("Settings.menu_oncomplete():" + menu_item);
	
	var LIMITES = 1;
	var AUTOMATIC_INVESTMENT = 2;
	
	switch(menu_item)
	{
		case LIMITES:
			
			$("#investment-param").show();			
			$(".automatic-investment-list").hide();	
		break;
		
		case AUTOMATIC_INVESTMENT:

			$("#investment-param").hide();					
			$(".automatic-investment-list").show();		
		break;
		
		default: break;
	}
	
	closeFancy();
};

function openVerMás( val )
{
	// alert( $("#explica_"+val).is(":visible") );
	
	if( $("#explica_"+val).is(":visible") )
	{
	
		$("#explica_"+val).hide();
	
	}else{
		
		dibujaLimites( val );
		
		$("#explica_"+val).show();
		
	}
	
}

var valClick = 0;

function callBean( val )
{
	valClick = val;
	$("#callBean").trigger("click");
	
}

function formatCurrency(total) {
    var neg = false;
    if(total < 0) {
        neg = true;
        total = Math.abs(total);
    }
    return (neg ? "-$" : '$') + parseFloat(total, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString();
}

function editar( val ){
	
	$("#param_id_"+val).prop( "disabled", false ); 
	$("#btnOk_"+val).show();
	$("#param_id_"+val).focus();
	$(".clsIconEdit_"+val).hide();
	$("#btnCambiar_"+val).hide();
	
}

function returnChange(  ){
	
	$("#param_id_"+valClick).prop( "disabled", true ); 
	$("#btnOk_"+valClick).hide();
	$(".clsIconEdit_"+valClick).show();
	$("#btnCambiar_"+valClick).show();
	
	
	closeFancy();
}
