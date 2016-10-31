console.log("frmscript.js");

var geocoder;
var map;
var marker;
var timeGlob;
var flag = 0;
var flagIni = 0;
var initialLocation;
//var mexico = new google.maps.LatLng(19.402487, -99.167404);
var browserSupportFlag =  new Boolean();
var lema="";
var isValidZipCode= false;
var strWrongZipCode = "Lo Sentimos, por el momento no podemos brindarte el servicio ya que no contamos con cobertura en la zona donde vives. ";

var edad_fuera_de_rango_flag = false;


$(document).ready(function() 
{

	 $('.close_descProfile').click(function(){	
		 $("#pnlMsgSugMin .ui-icon").click();
		 
	 });

	$("#dvPademovile")
	
	var fieldInver = $(".fieldInver").width();


	init_listener_scroll();
		
	$("#dvPademovile").fancybox(
	{			
		'href': '../resources/img/pademobile-tuturial.png'			
	});		
	
	$(window).resize();
});

function init_listener_scroll()
{	
	$(window).scroll(function()
	{
		var menu         = $('#menuRG');
		var footerpos    = $('#footer').height();
		var scrollBottom =  $(window).height() + $(window).scrollTop();
		
		var print_log_ENABLED = false;	
		var flagScrollB1      = false;
		
		if(parseInt($(this).scrollTop()) > parseInt($('#header').height()))
		{		
			menu.removeClass('menuFrm');
			menu.removeClass('menuFrmBottom');			
			menu.addClass('menuFrmFixed');
			
			menu.css( "bottom", "auto");
			
			// $('#contFrmReg').css("height", ( $("#frm_content").height() + "px"));
			// $('#contFrmReg').css("position","inherit");
			
			if(print_log_ENABLED)
			{
				console.log("---- if 1");
			}
		}
		
		if(parseInt($(this).scrollTop()) <= parseInt($('#header').height()))
		{		
			menu.removeClass('menuFrmFixed');
			menu.removeClass('menuFrmBottom');
			menu.addClass('menuFrm');
			menu.css( "bottom", "auto");
			// $('#contFrmReg').css("position","inherit");
			
			if(print_log_ENABLED)
			{
				console.log("---- if 2");
			}
		}
		
		if(parseInt(scrollBottom) >= (parseInt($(document).height() - footerpos) + 20))
		{			
		//	$('#contFrmReg').css("position","relative");
			
			if(print_log_ENABLED)
			{
				console.log("---- ultimo if");
			}
			
			menu.removeClass('menuFrm');
			menu.removeClass('menuFrmFixed');
		
			menu.addClass('menuFrmBottom');
			/*
			$('#contFrmReg').css("position","relative");
			
			if( $(window).width() > 1024 )
			{			
				if( $("#frm_content").height()>$(".helpFrm").height()+250 )
				{
					$("#contFrmReg").height( ($("#frm_content").height()+250)+"px");
					
					if(print_log_ENABLED)
					{
						console.log("---- se suman 00");
					}
					
					flagScrollB1 = true;
					
				} else {
					
					if(print_log_ENABLED)
					{
						console.log("---- se suman 200");
					}
					
					$("#contFrmReg").height(($(".helpFrm").height() + 350) + "px");
				}	
				
			} else {
				
				$("#contFrmReg").height( ($("#frm_content").height() + 450) + "px");
			}
			*/
			
		}
	});
}


function delayWrongZipCode()
{
	alert( strWrongZipCode );
	setTimeout("closeMessageProcessing()",500);
}

function isKuboMail(mail){
	
	var flag = false;
	
	if(mail.indexOf("@kubofinanciero.com") != (-1) ){
		
		$("#dvKuboPerson").show();
		$.scrollTo('#header',10, { axis:'y' });
		
		flag = true;
	}
	
	return flag;
	
}

function changePage()
{

	  $('html, body').animate({
	       scrollTop: ($('#header').offset().top + 100)
	  },500);
	map = null;
	mapOnOff();
	hideIFEjs();
}

function hideIFEjs()
{
	$('#ife').hide();	
}

function mapOnOff()
{
	flag = 0;
	//geocoder=null;
	//map=null;
	
	$('#mapa').slideUp();

	
}





function initialize(component) 
{
/*	
	var parent = $(component).parents(".sectionAddress");
	var latLongVal = parent.find(".latlongClass").val();
	var objNext    = parent.find(".latlongClass");
	
	var latLong;	
	var newLatLong;
	var myOptions = {zoom: 10,mapTypeId: google.maps.MapTypeId.ROADMAP,  scrollwheel: false};
	
	console.log("initialize(): " + latLongVal);
	
	if(document.getElementById("map_canvas"))
	{
    	map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
    	
    	google.maps.event.addListener(map, 'click', function(event) 
    	{
	
   		    map.setOptions({ scrollwheel: true });

    		marker.setMap(null);
    		marker = new google.maps.Marker({map: map,position:event.latLng,title:lema});
    		newLatLong = event.latLng;
    		newLatLong = newLatLong.toString().replace("(","");
    		newLatLong = newLatLong.replace(")","");
    		
    		objNext.val(newLatLong.trim() + "," + lema);
    		objNext.blur();  
    		
    		return false;
    	  });
    	
    	geocoder = new google.maps.Geocoder();
    	
    		$('body').on('mouseover', function(event) {
    			if($("#map_canvas").is(":visible")) {
		    	    var clickedInsideMap = $(event.target).parents('#mapa_canvas').length > 0;
		    	    
		    	    if(!clickedInsideMap) 
		    	    {	
		    	    	if($("#fancybox-overlay").is(":hidden") && $("#mapa_canvas").is(":visible")) {
		    	    		map.setOptions({ scrollwheel: false });
		    	    	}
		    	    } else {
		    	    	
		    	    	map.setOptions({ scrollwheel: true});
			    	}	
    			} 
	    	});
       	
	}
		
	if(latLongVal != undefined)
	{
		if(latLongVal.length > 0)
		{
			latLong = latLongVal.split(",",3);
			lema    = latLong[2];
			
			initialLocation = new google.maps.LatLng(latLong[0], latLong[1]);
			
		    map.setCenter(initialLocation);
		    map.setZoom(17);
		    
	        marker = new google.maps.Marker({map: map,position:initialLocation,title:lema});
	        
		} else {
			
			marker = new google.maps.Marker({});
			map.setCenter(mexico);
		}
	}	
*/	
}

function initWithLatLong(component)
{
	var parent     = $(component).parents(".sectionAddress");
	var latLongVal = parent.find(".latlongClass").val();
	var latLong;
	var coordenadas;
	var latLong_ENABLED;
	
	console.log("initWithLatLong(): " + latLongVal);
	
	/*if( latLongVal.length && latLongVal.length > 0)
	{
		coordenadas = latLongVal.split(",");
		
	} else {*/
		
		coordenadas = [];
	//}
	
	latLong_ENABLED = coordenadas.length > 1;
	
	if(latLong_ENABLED)
	{
		console.log("initWithLatLong(): " + marker);
		console.log("initWithLatLong(): " + map);
		 
		marker.setMap(null);
		
		latLong = latLongVal.split(",",3);
		
		if((latLongVal.split(",")).length > 2)
		{
			lema = latLong[2];
			
		} else {
			
			lema = "";
		}
			
		initialLocation = new google.maps.LatLng(latLong[0],latLong[1]);
		
	    map.setCenter(initialLocation);
	    map.setZoom(17);
	    
        marker = new google.maps.Marker({map: map,position:initialLocation,title:lema});
        
	} else {
		
		 marker = new google.maps.Marker({});
		 map.setCenter(mexico);
		 
		 console.log("initWithLatLong(): " + marker);
		 console.log("initWithLatLong(): " + map);
	}
	
	console.log("initWithLatLong(): " + latLong_ENABLED);
}


	 
function generateStringAddress(component)
{
	/*
	var street = address_number = neighborhood = town = zipcode = state = search = neighborhood_text = null;
	var objSection = $(component).parents(".sectionAddress");
	
	street = objSection.find(".street");
	
	console.log("generateStringAddress(): "+ street.val());
		
	
	if(street.val() != undefined )
	{	
		address_number    = objSection.find(".address_number");
		neighborhood      = objSection.find(".neighborhood");
		neighborhood_text = objSection.find(".neighborhood_text");
		town              = objSection.find(".town");
		zipcode           = objSection.find(".zipcode");
		state             = objSection.find(".state");
		
	 	if(street.val().length > 0)
	 	{
	 		street = street.val(); 
	 	}	
			
		if(address_number != undefined && address_number.val().length>0)
		{
			address_number = " " + address_number.val() + ", ";
			
		} else {
			
			street = street + ", ";
		}
				 			
		if(neighborhood != undefined && neighborhood.val()!="" && !neighborhood.is(":hidden") )
		{				
			neighborhood.each(function() 
			{
				neighborhood=$(this).find('option:selected').text()+",";
			});
		
		} else if(neighborhood_text != undefined && neighborhood_text.val() != "" ) {
			
			neighborhood = $('#neighborhood_text').val();
			
		} else {
			
			neighborhood = "";				
		}
			
		if(town && town.val().length!=0)
		{
			town = " "+town.val()+",";
		}
		
		if(zipcode.val().length==5)
		{
			zipcode = " " + zipcode.val() + ",";
		}
							
		if(state.val() + "" == 'México' )
		{
			state = " Estado de México";
			
		} else {
			
			state = " " + state.val();
		} 
								
		search = town +  " C.P. " + zipcode + street + address_number + " Colonia " + neighborhood + state;
				
		if(search.length > 0)
		{
			search += ", Mexico ";
			
		} else {
			
			search = "Mexico";
		}
					
		console.log();
		
		codeAddress(search, objSection); 
	}
*/	
}

function codeAddress(address, objSection) 
{	 
	var newLatLong;
	var objZC = objSection.find(".zipcode");
	
	console.log("codeAddress(): " + address);
	
	if(geocoder!=null)
	{
	    geocoder.geocode( { 'address': address}, function(results, status) 
	    {
	      if (status == google.maps.GeocoderStatus.OK && map!=null) 
	      {
	    	  marker.setMap(null);
	    	  
	    	  map.setCenter(results[0].geometry.location);
	    	  map.setZoom(17);
	    	  
	    	  marker = new google.maps.Marker({map: map,position: results[0].geometry.location,title:lema});
	        	
	        if(objSection!=null)
	        {
	        	newLatLong=results[0].geometry.location;
	    		newLatLong=newLatLong.toString().replace("(","");
	    		newLatLong=newLatLong.replace(")","");
	        	objZC.next().val(newLatLong);
	    		objZC.next().blur();	        	
	        }
	        	
	      } else {
	    	  
	        marker.setMap(null);
	        
	        var latlng = new google.maps.LatLng(19.402487,-99.167404);
	        
		    var myOptions = {
		      zoom: 10,
		      center: latlng,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    };
		    
		    map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
		    
		    google.maps.event.addListener(map, 'click', function(event) 
		    {
		    	var objNext = objZC.find(".latlongClass");
		    	
	    		marker.setMap(null);
	    		marker = new google.maps.Marker({map: map,position:event.latLng,title:lema});
	    		
	    		newLatLong=event.latLng;
	    		newLatLong=newLatLong.toString().replace("(","");
	    		newLatLong=newLatLong.replace(")","");
	    		
	    		objNext.val(newLatLong.trim());
	    		objNext.blur(); 
  		
	    		return false;
		    });
		    
	    	geocoder = new google.maps.Geocoder();
		    /*
		    $("#latLong").val(",");
        	$("#latLong").blur();*/
		    
	      }
	   });
	}
}




function contieneString(attrName, arrayNames)
{
	if(arrayNames != null){
		var thisArray = arrayNames.split("::");
		var thFlag = false;
		for(var i = 0; i<thisArray.length; i++ ){
			if(thisArray[i] ==  attrName){
				thFlag = true;
				break;
			}
		}
		if(!thFlag){
			arrayNames += attrName+"::";
		}
		return thFlag;
	}else{
		arrayNames = attrName+"::";
		return false;
	}
}


function fieldNumeric(event)
{
	var e=event;
	if(!e)
	 e = window.event;
	if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 8 && e.keyCode != 9){

		if(e.preventDefault){
		    e.preventDefault();
		}else{
		    e.returnValue = false; 
		}	
	}  
}


function validaPass()
{
	
	var pass = $("#pass").val();
	var name = $("#name").val();
	
	if((pass.toUpperCase()).indexOf(name.toUpperCase())){
		alert("Password Incorrecto");
		return false;
	}
}


function validaClabe(flagDis)
{
	mandodatos_hs();
	console.log("valida clabe");
	if($("#hasaccount\\:0").is(':checked')) 
	{  
       
		/*
		var valor = $("#clabe").val();
		if(valor.length==0){
			return false;
		}
		if(valor.length<18){
			alert("La cuenta clabe debe tener 18 dígitos ");
			$("#clabe").focusin();
			return false;
		}*/
		
//	    if (tecla==8) return true; // 3
//	    patron =/\d/; // Acepta números y letras
//	    te = String.fromCharCode(tecla); // 5
//	    if( !patron.test(valor) ) // 6
//		{
//	    	alert("Algún valor es incorrecto");
//	    	$("#clabe").focusin();
//			return false;
//		}
		
	}
	
	if(flagDis)
	{
		displayMessageProcessing('msgprocessing',true);
	}		
	
	return true;
}


function validateFieldReferences(thisID)
{
	var ref = thisID.split("_")[2];
	
	var flagRef=false;
	if(	($("#inp_name_"+ref).val().replace(" ","")).length>3||($("#inp_secondName_"+ref).val().replace(" ","")).length>3||
		($("#inp_lastName_"+ref).val().replace(" ","")).length>3||($("#inp_surName_"+ref).val().replace(" ","")).length>3
	   ){
		flagRef=true;
	}
	
	if(!flagRef)
	{
		if(ref == "ref1"){
			$("#lada_phone1").removeClass("requiredClass");
			$("#phone1").parent().children('.formError').remove();
		}
		if(ref == "ref2"){
			$("#lada_phone2").removeClass("requiredClass");
			$("#phone2").parent().children('.formError').remove();
		}
		if(ref == "ref3"){
			$("#lada_phone3").removeClass("requiredClass");
			$("#phone3").parent().children('.formError').remove();
		}
		if(ref == "ref4"){
			$("#lada_phone4").removeClass("requiredClass");
			$("#phone4").parent().children('.formError').remove();
		}
		return;
	}
	
	if(ref == "ref1" && flagRef)
	{
		if(($("#lada_phone1").val().replace(" ","")).length<2||($("#phone1").val().replace(" ","")).length<6){
			$("#lada_phone1").addClass("requiredClass");
			$("#phone1").addClass("requiredClass");
//			$("#phone1").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone1").removeClass("requiredClass");
			$("#phone1").removeClass("requiredClass");
			$("#phone1").parent().children('.formError').remove();
		}
	}
	else if(ref == "ref2" && flagRef){
		if(($("#lada_phone2").val().replace(" ","")).length<2||($("#phone2").val().replace(" ","")).length<6){
			$("#lada_phone2").addClass("requiredClass");
			$("#phone2").addClass("requiredClass");
//			$("#phone2").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone2").removeClass("requiredClass");
			$("#phone2").removeClass("requiredClass");
			$("#phone2").parent().children('.formError').remove();
		}
	} else if(ref == "ref3" && flagRef){
		if((($("#lada_phone3").val()).replace(" ","")).length<2||(($("#phone3").val()).replace(" ","")).length<6){
			$("#lada_phone3").addClass("requiredClass");
			$("#phone3").addClass("requiredClass");
//			$("#phone3").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone3").removeClass("requiredClass");
			$("#phone3").removeClass("requiredClass");
			$("#phone3").parent().children('.formError').remove();
		}
	}
	else if(ref == "ref4" && flagRef){
		if(($("#lada_phone4").val().replace(" ","")).length<2||($("#phone4").val().replace(" ","")).length<6){
			$("#lada_phone4").addClass("requiredClass");
			$("#phone4").addClass("requiredClass");
//			$("#phone4").validationEngine('showPrompt', '*Número telefónico requerido','error','centerRight', true);
		}else{
			$("#lada_phone4").removeClass("requiredClass");
			$("#phone4").removeClass("requiredClass");
			$("#phone4").parent().children('.formError').remove();
		}
	}
}


function validateIsValidZipCode(){
	
	if(!isValidZipCode){
		alert( strWrongZipCode );
		closeMessageProcessing();
		return false;
	}else{
		return true;
	}
	
}



function validaRelationShipBasicInv(xhr, status, args)
{
	console.log("validaRelationShipBasicInv(): ");
	console.log("args.isRelation   = " + args.isRelation);
	console.log("args.sameProspect = " + args.sameProspect);
	
	var res  = args.isRelation;
	var res2 = args.sameProspect;
	
	if (args.edad_fuera_de_rango == true) {
		
	}else{
		   $("#envioConsultaCorta").trigger("click");
		
	}
	
	if(res == 'S')
	{
		closeMessageProcessing();
		setTimeout('showRelPersonCont();', 600);
		
		return false;
		
	} else if(res2 == 'S') {
		closeMessageProcessing();
		
		var strRres = args.lstPros;
		var arr = strRres.split("::");
		var str1 = "<ul>";
		
		for(var iA = 0; ((arr.length)-1) > iA; iA++)
		{
			str1 += "<li>"+(arr[iA].split(","))[0]+" - "+(arr[iA].split(","))[1]+"</li>";
		}
		
		str1 += "</ul>";
		
		$("#lstMembers").html(str1);
		
		setTimeout('showSamePersonCont();',600);
		return false;
	}else{
		
		$("#hdNext\\:nextInvestorAction").click();
		return true;
	}
}


function validaRelationShipBasicInvClickMenu(xhr, status, args)
{
	console.log("validaRelationShipBasicInv(): ");
	console.log("args.isRelation   = " + args.isRelation);
	console.log("args.sameProspect = " + args.sameProspect);
	
	var res  = args.isRelation;
	var res2 = args.sameProspect;
	
	if(res == 'S')
	{
		closeMessageProcessing();
		setTimeout('showRelPersonCont();', 600);
		
		return false;
		
	} else if(res2 == 'S') {
		closeMessageProcessing();
		
		var strRres = args.lstPros;
		var arr = strRres.split("::");
		var str1 = "<ul>";
		
		for(var iA = 0; ((arr.length)-1) > iA; iA++)
		{
			str1 += "<li>"+(arr[iA].split(","))[0]+" - "+(arr[iA].split(","))[1]+"</li>";
		}
		
		str1 += "</ul>";
		
		$("#lstMembers").html(str1);
		
		setTimeout('showSamePersonCont();',600);
		return false;
		
	}else{
		
		 var elmnt = elmntGRAL;
		
			 flag = displayMessageProcessing('msgprocessing',true);
			 
				if(elmnt.hasClass("menuItem"))
				{
				//$(".menuItem").click(function(){
			//		var flag = true;
					//alert($("#anterior").val());
			//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
			//			flag  = validateFields();
			//			//alert("regreso: "+flag);
			//		}
					
					if(flag)
					{
						$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
						elmnt.removeClass('menuItem').addClass('menuItemSel');
						$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
						var myId= "#"+elmnt.attr("id")+"ItemBar";
						var myIdPor= "#"+elmnt.attr("id")+"Porcent";
						var myIdPorAnt= $("#selectedPor").val();
						var antId = $("#selected").val();
						$(myId).removeClass('displayNone').addClass('displayBlock');
						$(antId).removeClass('displayBlock').addClass('displayNone');
						$(myIdPor).removeClass('porcent').addClass('porcentSel');
						$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
						$("#selected").val(myId);
						$("#selectedPor").val(myIdPor);
						$("#anterior").val(elmnt.attr("id"));
					}
				//});
				}
				
				else if(elmnt.hasClass("menuItem1")){
				//$(".menuItem1").click(function(){
			//		var flag = true;
					//alert($("#anterior").val());
			//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
			//			flag  = validateFields();
			//			//alert("regreso: "+flag);
			//		}
					if(flag)
					{
						$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
						$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
						elmnt.removeClass('menuItem1').addClass('menuItem1Sel');
						var myId= "#"+elmnt.attr("id")+"ItemBar";
						var myIdPor= "#"+elmnt.attr("id")+"Porcent";
						var myIdPorAnt= $("#selectedPor").val();
						var antId = $("#selected").val();
						$(myId).removeClass('displayNone').addClass('displayBlock');
						$(antId).removeClass('displayBlock').addClass('displayNone');
						$(myIdPor).removeClass('porcent').addClass('porcentSel');
						$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
						$("#selected").val(myId);
						$("#selectedPor").val(myIdPor);
						$("#anterior").val(elmnt.attr("id"));
					}
				//});
				} else if(elmnt.hasClass("menuItem1")) {
				//$(".menuItemSel").click(function(){
					
			//		var flag = true;
					//alert($("#anterior").val());
			//		if($("#anterior").val() == "menu1"||$("#anterior").val() == "menu2"){
			//			flag  = validateFields();
			//			//alert("regreso: "+flag);
			//		}
					if(flag)
					{
					
						$(".menuItemSel").removeClass('menuItemSel').addClass('menuItem');
						$(".menuItem1Sel").removeClass('menuItem1Sel').addClass('menuItem1');
						elmnt.removeClass('menuItem').addClass('menuItemSel');
						var myId= "#"+elmnt.attr("id")+"ItemBar";
						var myIdPor= "#"+elmnt.attr("id")+"Porcent";
						var myIdPorAnt= $("#selectedPor").val();
						var antId = $("#selected").val();
						$(myId).removeClass('displayNone').addClass('displayBlock');
						$(antId).removeClass('displayBlock').addClass('displayNone');
						$(myIdPor).removeClass('porcent').addClass('porcentSel');
						$(myIdPorAnt).removeClass('porcentSel').addClass('porcent');
						$("#selected").val(myId);
						$("#selectedPor").val(myIdPor);
						$("#anterior").val(elmnt.attr("id"));
					}
				//});
				}
		}
}


function validaRelationShipBasic(xhr, status, args)
{	
	var res     = args.isRelation;
	var res2    = args.sameProspect; 
	var strRres = args.lstPros;
	
	var edad_fuera_de_rango = args.edad_fuera_de_rango;
	
	console.log("validaRelationShipBasic(): ");
	console.log("> isRelation          = " + res);
	console.log("> sameProspect        = " + res2);
	console.log("> edad_fuera_de_rango = " + edad_fuera_de_rango);
	 
	
	if(res == "S")
	{
		closeMessageProcessing();
		
		setTimeout('showRelPersonCont();',600);
		
		return false;
		
	} else if(res2 == "S") {
		
		closeMessageProcessing();
		
		console.log("args.lstPros = " + strRres);
		
		
		var arr = strRres.split("::");
		var str1 = "<ul>";
		
		for(var iA = 0; ((arr.length)-1) > iA; iA++)
		{
			str1 += "<li>"+(arr[iA].split(","))[0]+" - "+(arr[iA].split(","))[1]+"</li>";
		}
		
		str1 += "</ul>";
		
		$("#lstMembers").html(str1);
		
		setTimeout('showSamePersonCont();',600);
		
		return false;
		
	}else if( edad_fuera_de_rango ){
		 console.log("edad fuera de rango");
		 $(".edadFueraRango").addClass("show");
		 $(".velo2").fadeIn();
		
	}else if ( !edad_fuera_de_rango ){
		  console.log("envio consulta corta");
		$("#envioConsultaCorta").trigger("click");
		
	} else {
		
//		if(edad_fuera_de_rango)
//		{
//			$("#hdNext\\:edad_fuera_de_rango").click();
//			
//		} else {
//			
//			$("#hdNext\\:siguienteBASICOSACTION").click();
//		}
	}				
}



