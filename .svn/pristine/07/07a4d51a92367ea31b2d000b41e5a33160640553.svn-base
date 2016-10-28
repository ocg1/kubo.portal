console.log("ammount.js");

function validaMontoMin(ammount_id,place)
{
	
	var input_ammount_id = $("#"+ ammount_id);
	
    var num = input_ammount_id.val().replace(",","");
    
    num = num.replace(",","");
    num = num.replace(",","");
	var ent;
	var dec;
	var point;
	
	//alert(" -----------------------------");
	
	
	if(num.indexOf("\\.")!=(-1)){
		
		if((num.split("\\.")).length>2){
			alert("numero no valido");
			//num= num.substring(0,num.length-1);
			num = "";
			input_ammount_id.val(num);
		    return false;
		}
		
		if((num.split("\\.")).length>1){
			ent=num.split("\\.")[0];
		 	dec=num.split("\\.")[1];
		 	point=".";
		}else{
			ent=num.split("\\.")[0];
			dec="";
			point="";
		}
	}else{
		ent=num;
		dec="";
		point="";
	}
	
	var vmax2 = $("#montoMax").val();
	//var vmax=vmax2.split("\.")[0];
	var vmax=vmax2;
	// alert(vmax+" -- "+num);
	
		if(place == 'simulator'){
			
			var vmin2 = $("#montoMin").val();
			var vmin=vmin2.split("\.")[0];
			//alert("maximo: " + vmax+ "  minimo: " + vmin +" entero: "+ent);
			
		    if(parseFloat(ent)<parseInt(vmin) || parseFloat(ent)>parseInt(vmax) ) {
		    	//alert( vmax+" -- "+num+" -- "+vmin );
		    	
		    	if(  parseFloat(ent)>parseInt(vmax) ){
		    		vmin = vmax;
		    	}
		    	
		    	if(vmin.length>3&&vmin.length<=6)
		    		vmin= vmin.substring(0,(vmin.length -3))+","+vmin.substring((vmin.length -3),vmin.length);
		    	
		    	else if(vmin.length>6&&vmin.length<9)
		    		vmin= +vmin.substring(0,(vmin.length -6))+","+vmin.substring(vmin.length -6,(vmin.length -3))+","+vmin.substring((vmin.length -3),vmin.length);
		    	
		    	var sval = vmin+point+dec;
		    	
		    	input_ammount_id.val(sval);
		    	
			    //input.value = "50,000";
		    	//alert("num: "+ ent +"min: "+vmin);
		    	if(parseFloat(ent)<parseInt(vmin.replace(",","")) ) {
		    		if ($("#dvContMenuREG").is(":visible")) {
		    			alertify.alert("La cantidad debe ser mayor a los $"+sval);
		    		}else {
		    			alertify.alert("La cantidad debe ser mayor a los $"+sval);
		    		}
		    	}
		    	else if(  parseFloat(ent)>parseFloat(vmax) ){
		    		if ($("#dvContMenuREG").is(":visible")) {
		    			alertify.alert("La cantidad debe ser menor a los $"+sval);
		    		}else {
		    			
		    			alert("La cantidad debe ser menor a los $"+sval);
		    		}
		    	}
		    	//alert( vmax+" -- "+num+" -- "+vmin );
		    	
		    	
		    	input_ammount_id.blur();
			    
		    	return false;
		    }
		}
    
    	if(ent.length>3&&ent.length<=6)
    		ent= ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	else if(ent.length>6&&ent.length<9)
    		ent= +ent.substring(0,(ent.length -6))+","+ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	input_ammount_id.val(ent+point+dec);
    	
    	//alert( "return true: "+input.val() );
    	
	    return true;   	
}