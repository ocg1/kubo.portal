console.log("format.js");

function format(e,place)
{
	var input = e;			
    var num = input.value.replace(",","");
    
    num = num.replace(",","");
    num = num.replace(",","");
    
	var ent;
	var dec;
	var point;
	
	if((num.split(".")).length>2)
	{
		alert("numero no valido");
		//num= num.substring(0,num.length-1);
		num = "";
		input.value = num;
	    return false;
	}
	
	if((num.split(".")).length>1)
	{
		ent=num.split(".")[0];
	 	dec=num.split(".")[1];
	 	point=".";
	}else{
		ent=num.split(".")[0];
		dec="";
		point="";
	}
	
	if(!isNaN(ent))
	{
		if(place == 'simulator')
		{
			
			var vmax2 = $("#" + input.id).val();
			var vmax=vmax2.split("\.")[0];
			
			//alert(ent+point+dec+" -- "+vmax);
		    if(parseFloat(num)>parseFloat(vmax))
		    {
		    	
		    	if((vmax.split("\.")).length>1)
		    	{
		    		ent=vmax.split("\.")[0];
		    	 	dec=vmax.split("\.")[1];
		    	 	point=".";
		    	}else{
		    		ent=vmax.split(".")[0];
		    		dec="";
		    		point="";
		    	}
		    	
		    	vmax = (vmax.split("."))[0];
		    	
		    	if(vmax.length>3&&vmax.length<=6)
		    		vmax= vmax.substring(0,(vmax.length -3))+","+vmax.substring((vmax.length -3),vmax.length);
		    	
		    	else if(vmax.length>6&&vmax.length<9)
		    		vmax= +vmax.substring(0,(vmax.length -6))+","+vmax.substring(vmax.length -6,(vmax.length -3))+","+vmax.substring((vmax.length -3),vmax.length);
		    	
		    	var sval = vmax+point+dec;
		    	
		    	input.value = sval;
		    	
			    //input.value = "50,000";
			    input.blur();
			    alert("La cantidad no debe superar los $"+sval);
		    	return false;
		    
		    }
		}
    
		console.log(" -- cantidad -> "+ent);
		
    	if(ent.length>3&&ent.length<=6)
    		ent= ent.substring(0,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	else if(ent.length>6&&ent.length<9)
    		ent= +ent.substring(0,(ent.length -6))+","+ent.substring(ent.length -6,(ent.length -3))+","+ent.substring((ent.length -3),ent.length);
    	input.value = ent+point+dec;
    	
    	console.log(" -- cantidad -> "+ent);
    	
	    return true;
    }

    else{ 
    	
    	if ($("#dvContMenuREG").is(":visible")) {
    		alertify.alert('Solo se permiten numeros');
    	}else {
    		alert('Solo se permiten numeros');
    	}
    
	    num= ""; 
	    input.value = "";
	    return false;
    }
}