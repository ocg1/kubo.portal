	var x=0;	



	$(document).ready(function(){
		 
		$(".profileId").click(function(){
			$("#descProfile").toggle();
		});
		
		$(".clsItem").click( function(){
			if($(this).hasClass("itemCheck"))
			{
				$(this).removeClass("itemCheck");
			
			}else{
				$(this).addClass("itemCheck");
			}
			
			var item_num_risk = 0;
			var strCadena = "";
			
			$(".clsItem").each(function(){
				
				if($(this).hasClass("itemCheck"))
				{
					if( item_num_risk != 0 ){
						strCadena+=",";
					}
					
					strCadena+= $(this).attr("id");
					item_num_risk++;
				
				}
					
			});
			
			$("#cadena1").val( strCadena );
			$("#risk_str").val( strCadena );
			
		});
		
		
		$(".clsItemDest").click( function(){
			if($(this).hasClass("itemCheck"))
			{
				$(this).removeClass("itemCheck");
			
			}else{
				$(this).addClass("itemCheck");
			}
			
			var item_num_risk = 0;
			var strCadena = "";
			
			$(".clsItemDest").each(function(){
				
				if($(this).hasClass("itemCheck"))
				{
					if( item_num_risk != 0 ){
						strCadena+=",";
					}
					
					strCadena+= ($(this).attr("id")).replace("purpose_", "");
					item_num_risk++;
				
				} 
					
			});
			/*
			while(strCadena.indexOf("purpose_") != (-1)){
				strCadena = strCadena.replace("purpose_", "");
			}
			*/
			$("#destiny_str").val( strCadena );
			$("#destiny_str_val").val( strCadena );
			
		});
		
		
		$(".clsTerm").click( function(){
	
			
			var this_id = $(this).attr("id");
			
			var this_sec = this_id.split("_")[1];
			var i = 0;
			
			var valMax = this_id.split("_")[0];
			
			if(valMax == "gender"){
				
				if($(this).hasClass("itemCheck"))
				{
					$(this).removeClass("itemCheck");
				
				}else{
					$(this).addClass("itemCheck");
				}
				
				var flagGender = false;
				$(".clsTerm").each(function(){
					
					this_id = $(this).attr("id");
					
					var tipo = this_id.split("_")[0];
					
					if( tipo == "gender" ){
					
						if($(this).hasClass("itemCheck")){
							flagGender = true;
						}
					
					}
				});
				
				if( !flagGender ){
					$(this).addClass("itemCheck");
				}
				
				
			}else{
				
				$(".clsTerm").each(function(){
					
					this_id = $(this).attr("id");
					
					var tipo = this_id.split("_")[0];
					
					if( tipo != "gender" ){
					
						if($(this).hasClass("itemCheck")){
							$(this).removeClass("itemCheck")
						}
						
					}
						
				});
				
				$("#cadena2").val("");
				
				$(".clsTerm").each(function(){
					
					var this_id_2 = $(this).attr("id");
					
					var this_sec_2 = this_id_2.split("_")[1];
					
					if( parseInt(this_sec) >=  parseInt(this_sec_2) ){
						$(this).addClass("itemCheck")
					}

				});
				
				$("#cadena2").val( valMax );
				
				$("#cadena2").trigger("blur");
				
				
			}
			
		} );
		
		//initRiskDetailCheck();

	});
	
	
	
	function showConfirmInv(){
		
		setTimeout('actionconfirm();',250);
	}
		
	function actionconfirm(){
		//alert("");
		// $("#dvWaitConfInv").hide();
			$("#dvContTableConfInv").fadeIn();
		
			closeFancy();
		
			
	}
	var flagFocus1 = true;
	function cargaValorActualTMP(element){
		console.log("valor ActualTMP: "+ $(element).val() );
		$(element).next("input").val( $(element).val() );
		$(element).next("input").trigger("focus");
		$(".inversionCelda").css("backgroundColor", "#fff");
		$(".montoSugerido").hide();
		
	}
	
	function cargaValorActual(element){
		if(flagFocus1){
			console.log("valor Actual JSF: "+ $(element).val() );
			tmpInv= false;
			$("#btnValorActual22").val( $(element).val() ).delay(300).trigger("blur");
			flagFocus1 = !flagFocus1;
		}else{
			flagFocus1 = !flagFocus1;
		}
		
		
	}
	
	function showLigthInv(){
		 centrarPnlInv ();
		$("#dvWaitConfInvInterno").show();
		$("#dvPreMyInvestments").addClass("show");
		$(".velo").fadeIn();
		
		return true;
	}
	
	function aceptaInvertir(){
		$(".velo").fadeOut();
		$("#dvPreMyInvestments").removeClass("show");
		setTimeout(function(){ $("#cmdInvestmentAction").trigger("click") },250)
	}
	
	function fncChckInv(){
		
		flagChckInv = false;
		return true;
	
		
	}
	
function seleccionDestiny(){ 
	
	var f = false;
	
	if($("#dvSelAllDestiny").html() == 'Seleccionar todos')
		f = true;
	
	$(".clsItemDest").each(function(){
			
			if(f)
			{
				$(this).addClass('itemCheck');
			
			}else
			{
				$(this).removeClass('itemCheck');
			}
		
	});
	
	if($("#dvSelAllDestiny").html() == 'Seleccionar todos')
	{
		$("#dvSelAllDestiny").html( 'Quitar Selección' );
	
	}else
		{
			$("#dvSelAllDestiny").html('Seleccionar todos')
		}
	
	
	var item_num_risk = 0;
	var strCadena = "";
	
	$(".clsItemDest").each(function(){
		
		if($(this).hasClass("itemCheck"))
		{
			if( item_num_risk != 0 ){
				strCadena+=",";
			}
			
			strCadena+= $(this).attr("id");
			item_num_risk++;
		
		}
			
	});
	
	$("#destiny_str").val( strCadena );
	$("#destiny_str_val").val( strCadena );
	
	
	
}
	
function seleccionAllDet(){
		
		var f = false;
		
		if($("#dvSelAllRiskDet").html() == 'Seleccionar todos')
			f = true;
		
		$(".clsItem").each(function(){
				
				if(f)
				{
					$(this).addClass('itemCheck');
				
				}else
				{
					$(this).removeClass('itemCheck');
				}
			
		});
		
		if($("#dvSelAllRiskDet").html() == 'Seleccionar todos')
		{
			$("#dvSelAllRiskDet").html( 'Quitar Selección' );
		
		}else
			{
				$("#dvSelAllRiskDet").html('Seleccionar todos')
			}
		
		
		var item_num_risk = 0;
		var strCadena = "";
		
		$(".clsItem").each(function(){
			
			if($(this).hasClass("itemCheck"))
			{
				if( item_num_risk != 0 ){
					strCadena+=",";
				}
				
				strCadena+= $(this).attr("id");
				item_num_risk++;
			
			}
				
		});
		
		$("#cadena1").val( strCadena );
		$("#risk_str").val( strCadena );
		
		
	}
	
	function seleccionAll(){
		
		var f = false;
		
		if($("#dvSelAllRisk").html() == 'Seleccionar todos')
			f = true;
		
		$(".ui-checkbox-filter").each(function(){
			
				$(this).attr('checked', f);
			
		});
		
		if($("#dvSelAllRisk").html() == 'Seleccionar todos')
		{
			$("#dvSelAllRisk").html( 'Quitar Selección' );
		
		}else
			{
				$("#dvSelAllRisk").html('Seleccionar todos')
			}
		
	}
	
	function seleccionaTerm( elmnt ){
		
		var itemActualInv = elmnt.split("_")[1];
		
		$(".clsTerm").each(function(){
			
			var this_id = $(this).attr("id");
			
			var tipo = this_id.split("_")[0];
			
			if( tipo != "gender" ){
			
				var this_id_2 = $(this).attr("id");
				
				var this_sec_2 = this_id_2.split("_")[1];
				
				if( parseInt( itemActualInv ) >= parseInt(this_sec_2) ){
					
					 $(this).addClass('itemCheck');
					
				}else{
					
					$(this).removeClass('itemCheck');
					
				}
			
			}
		});
	}	
	
	
$(document).ready(function(){
		
		//initPopup();
		/*
		$("h2.expand_heading").toggle(
				function(){        	
							$(this).children().removeClass("change");	 		    
		        }, 
		        function () {
		        	$(this).children().addClass("change");
		        }
		 );
		 */
		/* COMENTANDO 14 JULIO
	    $("h2.expand_heading").click(function(event){   
	    		$(this).next(".toggle_container").slideToggle("slow");
	    		event.preventDefault();
	    });
	    */     	    
	    /*
	    $('input[name=checkbox-filter]').attr('checked',true);
	    */
		/*
	
		$(window).scroll(function() {
			if($("#creditos").offset()){
		    	var sidebar   = $("#sidebar") ;
		    	var footerpos = $("#footer").offset().top;
		        var scrollBottom =  $(window).height() + $(window).scrollTop();
		    	var tablePosition = $("#creditos").offset().top;
		    	
		    	if(parseInt($(this).scrollTop())>parseInt(tablePosition)){
		    		sidebar.removeClass('menuFrm');
		    		sidebar.removeClass('menuFrmBottom');
		    		sidebar.addClass('menuFrmFixed');
		    		clase = "menuFrmFixed";
				}
				if(parseInt($(this).scrollTop())<=parseInt(tablePosition)){
					sidebar.removeClass('menuFrmFixed');
					sidebar.removeClass('menuFrmBottom');
					sidebar.addClass('menuFrm');
					clase = "menuFrm";
				}
				if((scrollBottom-80)>=footerpos){
					sidebar.removeClass('menuFrm');
					sidebar.removeClass('menuFrmFixed');
					sidebar.addClass('menuFrmBottom');
					clase = "menuFrmBottom";
				}
			}			
				
		    });
		*/
		 
		
	 });

function completeTransationFunding(xhr, status, args){	
	var idcontent=$("#content-result-funding");
	if(args.isFunding){
		idcontent.empty().append("<div class='clTitle' style='margin:0;width:auto;'>El credito se ha fondeado satisfactoriamente.</div>");
	}else{
		var toarray = eval('('+ args.listaerror+')');
		var newContent="<ul style='list-style:square inside;'>";
		for (target in toarray){
			 if(target!='remove')
				 newContent+="<li style='margin-bottom: 10px;'>"+toarray[target]+"</li>";			         
	       }
		newContent+="</ul>";
		idcontent.empty().append(newContent);
	}
	
	$.fancybox({			
		'padding' : '0',
		'margin' : '0',
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'modal' : true,
		'type' : 'inline',
		'scrolling' : 'no',
		'centerOnScroll' : true,
		'href': '#popupfunding',
		'overlayColor': '#333333',
	});
	
}

function initPopup() {
//	$(".invest_list22").click(function(event){
//    	var element=$(this);
//    	var ret=false;
//    	if($("#cuentasUsr").val()!=""){
//			if(confirm("Esta seguro de invertir en este proyecto"))
//				element.next().click();
//		}
//		else{
//			$.fancybox({			
//				'padding' : '0',
//				'margin' : '0',
//				'autoDimensions':true,
//				'transitionIn' : 'elastic',
//				'transitionOut' : 'elastic',
//				'speedIn' : '20',
//				'speedOut' : '10',
//				'modal' : true,
//				'type' : 'inline',
//				'scrolling' : 'no',
//				'centerOnScroll' : true,
//				'href': '#popupData',
//				'onComplete' : function(){
//					$("#btn_cancel").click(function(event) {
//			        	 ret=false;
//			        	 $.fancybox.close();
//			         });
//			         $("#btn_aceptar").click(function(event) {			        	
//			        	 if($("#cuentasUsrPop-up").val()!="")
//			        	 {  ret=true;
//			        	 	$.fancybox.close();
//			        	 }else
//			        		alert("Seleccione una cuenta");
//			         });
//			    },
//			    'onClosed':function() {
//			    	if(ret){
//				    	if(confirm("Esta seguro de invertir en este proyecto"))
//			        			element.next().click();			        	 
//			    	}
//				},
//				'overlayColor': '#333333'
//			});
//		}
//    }); 
	
	window.clearInterval( myVar );
	
	$("#btnUpdateInvest").click();
	
}

function iniUpdateInv(){
	//alert("Actualizando...");
	return true;
}

function finishInv(){
	closeMessageProcessing();
	window.setTimeout(function(){
					$( "#dvResInv" ).fadeIn( 500 );
							}
							,600
				);
}


function hideMessageRes(){
	 $( "#dvResInv" ).fadeOut( 500 );
}

function initInvestment(element){
	
	//var element=$(this);
	
	if($("#cuentasUsr").val()!=""){
		if(confirm("¿Está seguro de querer invertir en este proyecto?")){
//			alert("regresa true "+element);
//			$("#creditos:0:"+element).click();
			displayMessageProcessing('msgprocessing',false);
			return true;
		}else{
			//alert("regresa false");
			return false;
		}
	}
	else{
		/*
		$.fancybox({			
			'padding' : '0',
			'margin' : '0',
			'autoDimensions':true,
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'speedIn' : '20',
			'speedOut' : '10',
			'modal' : true,
			'type' : 'inline',
			'scrolling' : 'no',
			'centerOnScroll' : true,
			'href': '#popupData',
			'onComplete' : function(){
				$("#btn_cancel").click(function(event) {
		        	 ret=false;
		        	 $.fancybox.close();
		        	 return ret;
		         });
		         $("#btn_aceptar").click(function(event) {			        	
		        	 if($("#cuentasUsrPop-up").val()!="")
		        	 {  ret=true;
		        	 	$.fancybox.close();
		        	 	return ret;
		        	 }else
		        		alert("Seleccione una cuenta");
		        	 return false;
		         });
		    },
		    'onClosed':function() {
		    	if(ret){
			    	if(confirm("Esta seguro de invertir en este proyecto"))
		        			//element.next().click();	
			    	return ret;
		    	}
		    	return ret;
			},
			'overlayColor': '#333333'
		});
		*/
		
		alert("Seleccione una cuenta");
		flashFlag = true;
		flashing();
		return false;
		
	}
	
}

	var flashFlag = true;
	var iGlobal = 0;

	function flashing(){
		
		if(flashFlag){
			
			$('#dvSectionAccount').addClass("withBackGround").delay(850).removeClass("withBackGround");
			
			if(iGlobal > 2){
				
				flashFlag = false;
				
			}
			
			iGlobal++;
			
		}
	}
	
	var checkFlag = 0;
	

	
	function seeInput(element){
		var myId = element.getAttribute('id');
		var num = myId.split("::")[1];
		var newId  = "aInvestStr::"+num+"";
		var newId2 = "aInvestInp::"+num+"";
		var num2 = num -1;
		var newId3 = "contListPres:creditos:"+num2+":aInvesCant";
		document.getElementById(newId).style.display="none";
		document.getElementById(newId2).style.display="block";
		document.getElementById(newId3).focus();
		
	}
	
	function seeStr(element){
		var myId = element.parentNode.getAttribute('id');
		var num = myId.split("::")[1];
		var newId  = "aInvestStr::"+num+"";
		var newId2 = "aInvestInp::"+num+"";
		document.getElementById(newId2).style.display="none";
		document.getElementById(newId).style.display="block";
	}
	
	var scrollea = false;
	
	function buildQueryFilter(){
		scrollea = true;
		var cadStatus=getStringStatus();
		var cadRisk=getStringRisk();
		var cadTerm=getCadForTermChk();
		var cadgen="";
		
		if($("#chk1").is(':checked') || $("#gender_Hombre").hasClass("itemCheck") ) { 
			 cadgen+="1";
		}
		
		
		
		 if($("#chk2").is(':checked') || $("#gender_Mujer").hasClass("itemCheck")) { 
			 
			 if( cadgen.length>0 ){
					cadgen+=",";
			 }
			 
			 cadgen+="2";
		 }
		 
		 $('#cadena3').val(cadStatus!=""?cadStatus.substring(0,cadStatus.length-1):"");
		 $('#cadena1').val(cadRisk!=""?cadRisk.substring(0,cadRisk.length-1):"");
		 $('#cadena2').val(cadTerm!=""?cadTerm:"");
		 $('#cadenaGender').val(cadgen);
		 
		 var strCadenaDest ="";
		 var item_num_destiny = 0;
		 
		 $(".clsItemDest").each(function(){
				
				if($(this).hasClass("itemCheck"))
				{
					if( item_num_destiny != 0 ){
						strCadenaDest+=",";
					}
					var destinyChar = $(this).attr("id");
					strCadenaDest+= destinyChar.replace("purpose_","");
					item_num_destiny++;
				
				}
					
			});
			
			$("#destiny_str").val( strCadenaDest );
			$("#destiny_str_val").val( strCadenaDest );
		 
		 if(cadRisk.length>0){
				if(cadStatus.length>0 || cadTerm.length>0 || cadgen.length>0  ){
					$('#callBean').click();
					
				}
		 }
	
	}
	
	function buildQueryInvFilter(){
		
		var cadStatus=getStringStatus();
//		var cadRisk=getStringRisk();
//		var cadTerm=getCadForTermChk();
//		var cadgen="";
		
//		if($("#chk1").is(':checked') || $("#gender_Hombre").hasClass("itemCheck") ) { 
//			 cadgen+="1";
//		}
//		
//		
//		
//		 if($("#chk2").is(':checked') || $("#gender_Mujer").hasClass("itemCheck")) { 
//			 
//			 if( cadgen.length>0 ){
//					cadgen+=",";
//			 }
//			 
//			 cadgen+="2";
//		 }
		 
		 $('#cadena3').val(cadStatus!=""?cadStatus.substring(0,cadStatus.length-1):"");
//		 $('#cadena1').val(cadRisk!=""?cadRisk.substring(0,cadRisk.length-1):"");
//		 $('#cadena2').val(cadTerm!=""?cadTerm:"");
//		 $('#cadenaGender').val(cadgen);
		 
//		 var strCadenaDest ="";
//		 var item_num_destiny = 0;
		 
//		 $(".clsItemDest").each(function(){
//				
//				if($(this).hasClass("itemCheck"))
//				{
//					if( item_num_destiny != 0 ){
//						strCadenaDest+=",";
//					}
//					var destinyChar = $(this).attr("id");
//					strCadenaDest+= destinyChar.replace("purpose_","");
//					item_num_destiny++;
//				
//				}
//					
//			});
//			
//			$("#destiny_str").val( strCadenaDest );
//			$("#destiny_str_val").val( strCadenaDest );
		 
//		 if(cadRisk.length>0){
//				if(cadStatus.length>0 || cadTerm.length>0 || cadgen.length>0  ){
					$('#callBeanInv').click();
					
//				}
//		 }
	
	}
	
	function returnFilterInv(){
	
		closeFancy();
	}
	
	
	function getStringStatus(){
		var cadStatus= "";
//		if($("#status_creado").is(":checked")){
//			cadStatus = cadStatus+"'0',";
//		}
//		if($("#status_nopublic").is(":checked")){
//			cadStatus = cadStatus+"'1',";
//		}
//		if($("#status_public").is(":checked")){
//			cadStatus = cadStatus+"'2',";
//		}
//		if($("#status_fond").is(":checked")){
//			cadStatus = cadStatus+"'3',";
//		}
//		if($("#status_cancel").is(":checked")){
//			cadStatus = cadStatus+"'4',";
//		}
		
		$(".statusclss").each(function( index ){
			
			if($(this).is(":checked")){
				var str = $(this).attr("id").split("_")[1];
				cadStatus = cadStatus+"'"+str+"',";
			}
			
		});
		
		return cadStatus;
		
	}
	
	function getStringRisk(){		
		var cadenaChk = "";
		
		 if( $("input[name=flagRisk][value=0]").is(":checked")  ){
		
		//if ( ("#tbGeneralRisk").is(":visible") ){
				
			if($("#chkA").is(":checked")){
				cadenaChk = cadenaChk+"'A',";
			}
			if($("#chkB").is(":checked")){
				cadenaChk = cadenaChk+"'B',";
			}
			if($("#chkC").is(":checked")){
				cadenaChk = cadenaChk+"'C',";
			}
			if($("#chkD").is(":checked")){
				cadenaChk = cadenaChk+"'D',";
			}
			if($("#chkE").is(":checked")){
				cadenaChk = cadenaChk+"'E',";
			}
			if($("#chkF").is(":checked")){
				cadenaChk = cadenaChk+"'F',";
			}
			if($("#chkG").is(":checked")){
				cadenaChk = cadenaChk+"'G',";
			}
			
		}else{
			var item_num_risk = 0;
			var strCadena = "";
			$(".clsItem").each(function(){
				
				if($(this).hasClass("itemCheck"))
				{
					if( item_num_risk != 0 ){
						strCadena+=",";
					}
					
					strCadena+= "'" + $(this).attr("id") + "' ";
					item_num_risk++;
				
				}
					
			});
			
			if( strCadena.trim().length == 0 || item_num_risk == 0 ){
				alert( "Seleccione al menos un tipo de riesgo ");
				cadenaChk = strCadena = "";
			}
			
			cadenaChk = strCadena;
			
		}
			
		return 	cadenaChk;
	}
	
	function initViewRisk(){
		
		if( $("input[name=flagRisk][value=0]'").is(":checked") ){
			
			$("#dvRiskDetail").hide();
			$( "#tbGeneralRisk").show();
			
		}else{
			
			$("#dvRiskDetail").show();
			$("#tbGeneralRisk").hide();
			
		}
		
	}
	
	function initRiskDetailCheck(){
		
		
		
		if( $("input[name=flagRisk][value=1]'").is(":checked") ){
			
			$(".clsItem").each(function(){
				var ID = $(this).attr("id");
				
					$(this).removeClass("itemCheck");
					
			});
			
			$("#cadena1").val($("#risk_str").val());
			//alert( $("#cadena1").val() );	
			
		 var inR = 0;
			$(".clsItem").each(function(){
				var ID = $(this).attr("id");
				var cheks = $("#cadena1").val();
				if( cheks.indexOf( ID) != -1)
				{
					$(this).addClass("itemCheck");
					inR++;
				}
				
			});
			
			//alert( "se desactivaron "+ inR + " elementos" );
			
		}else{
			//alert( "else ");
		}
	}
	
	function initDestinyCheck(){
		
		
			
			$(".clsItemDest").each(function(){
				
					$(this).removeClass("itemCheck");
					
			});
			
			$("#destiny_str").val($("#destiny_str_val").val());
			//alert( $("#cadena1").val() );	
		
			var elmnt = $("#destiny_str").val();
		 
			var arr = elmnt.split(",");
			
			for( var i_des = 0 ; i_des < arr.length ; i_des++ ){
				
				var s = arr[i_des];
				
				s = s.replace("'");
				
				console.log("purpose: "+s);
				
				$("#purpose_"+s+"").addClass("itemCheck");
				
			}
			
		
	}
	
	function esInteger(e){
		var charCode;
		if (navigator.appName == "Netscape"){
			charCode = e.which;
		}
		else{
			charCode = e.keyCode;
		}	
		if (48 > charCode || charCode > 57){
			if(charCode == 8 || charCode == 0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
	}  
	
	function addClassCebra(){
		$("#creditos tr:odd").addClass('par');
	}
	
	function selectAllChk(){
		for (var i=0;document.formLL.elements.length>i;i++) 
		      if(document.formLL.elements[i].type == "checkbox")	
		         document.formLL.elements[i].checked=1 ;
	}
	
	function deselectAllChk(){
		for (var i=0;document.formLL.elements.length>i;i++) 
		      if(document.formLL.elements[i].type == "checkbox")	
		         document.formLL.elements[i].checked=0 ;
	}
	
	function checkUncheck(){
		if(checkFlag==0){
			selectAllChk();
			$("#selectAllOrNothing").text="De-seleccionar";
			checkFlag=1;
		}
		else{
			deselectAllChk();
			$("#selectAllOrNothing").text="Seleccionar Todo";
			checkFlag=0;
		}
	}
	
	function getCadForTermChk(){
		
		var itemActualInv = 0;
		var maxIdSel = "";
		
		$(".clsTerm").each(function(){
			
			if( $(this).hasClass("itemCheck") ){
				
				var this_id_2 = $(this).attr("id");
				
				var this_sec_2 = this_id_2.split("_")[1];
				
				if(parseInt(this_sec_2) > parseInt( itemActualInv ) ){
					
					//console.log('idSel: '+this_id_2);
					itemActualInv = parseInt(this_sec_2);
					// maxIdSel = this_id_2.split("_")[0];
					
					maxIdSel = this_id_2;
					
					//console.log('idProcesado: '+maxIdSel);
					
				}
				
				
				
			}
			/*
			if($(this).hasClass("itemCheck")){
				
				var valId = $(this).attr("id");
				valId = (valId.split("_")[0]) + "::";
				var val = $("#cadena2").val()+valId;
				$("#cadena2").val(val);
			}
			*/
		});
		
		return maxIdSel;
	}
	
	function seeAlert() {
		alert("El credito se a fondeado satisfactoriamente.");
	}
	
	
	
	function initActiveInvestor(){
		alertify.confirm("¿Está seguro de querer activar al cliente?", function (e) {
			if (e) {
				displayMessageProcessing('msgprocessing',false);
				return true;
			} else {
				return false;
			}
		}); 
	}
	
	function completeActiveInvestor(){
		closeFancy();
		window.location.reload(true); 
		
	}
	
	var myVar = 0;
	function initInvestmentPackage(){
		
		//var element=$(this);
		
		if($("#cuentasUsr").val()!=""){
			
			var valMax = $("#montoInv").val();
			
			if(confirm("¿Estás seguro de querer invertir "+valMax+" en los proyectos que se muestran en la pantalla?")){
				myVar=setInterval( function () { myTimer(); } , 1000);
				displayMessageProcessing('msgprocessing',false);
				return true;
			}else{
				return false;
			}
		}
		else{
			
			alert("Seleccione una cuenta");
			flashFlag = true;
			flashing();
			return false;
			
		}
		
	}
	
	function myTimer(){
		
		$('#inputTimer').blur();
		
	}

	function fncReturnSolInv(){
	
		 $('#cargaInfoAction').click();
		
	}
	
	function initInvestmentSel(){
		
		//var element=$(this);
		
		if($("#cuentasUsr").val()!=""){
			
			var valMax = $("#montoMaxInv").val();
			
			if(confirm("¿Estás seguro de querer invertir en los proyectos seleccionados?")){
				displayMessageProcessing('msgprocessing',false);
				return true;
			}else{
				return false;
			}
		}
		else{
			
			alert("Seleccione una cuenta");
			flashFlag = true;
			flashing();
			return false;
			
		}
		
	}

	
var tempForSelInv = 0;
	
function muestraInput(xhr, status, args){
	$(".inversionCelda").css("backgroundColor", "#fff");
	$(".montoSugerido").hide();
	
	if(args.displayInput){
		$("#dvBiteDesc_" +args.proyect_loan_id).hide();
		
		
		
		$("#dvBiteInput_"+args.proyect_loan_id).find(".inversion_input").val( 0 );
		//$("#dvBiteInput_"+args.proyect_loan_id).find("input").trigger("focus");
		setTimeout(function(){
			console.log("muestra input");
			var width = $(window).width();
			if (width >= 1025) {
				$("#dvBiteInput_" +args.proyect_loan_id).find(".inversion_input").trigger("focus");
			}
			
		},400);
		//if(tempForSelInv != 0){
		if( false ){
			setTimeout(function(){
				$("#dvBiteInput_"+args.proyect_loan_id).find(".inversion_input").val(0);
				$("#dvBiteInput_"+args.proyect_loan_id).find(".inversion_input").trigger("blur");
				
			},400);
			
			tempForSelInv = 1;
			
		}
		$("#dvBiteInput_"+args.proyect_loan_id).show();
		if($(".btnSwitch").hasClass("on")){
			$("#dvBiteInput_" +args.proyect_loan_id).closest(".inversionCelda").addClass("selected");
		}
	}else{
		$("#dvBiteInput_"+args.proyect_loan_id).hide();
		$("#dvBiteDesc_" +args.proyect_loan_id).show();
		$("#dvBiteInput_" +args.proyect_loan_id).closest(".inversionCelda").removeClass("selected");
	
	}
	
	flagChckInv = true;
	disponible();

}

function resNewValToInv(xhr, status, args){
	
	console.log("regresando a resNewValToInv ..");
	
	if(args.hasError){
		
		if(parseInt(args.montoSugerido)==0)
		{
			$("#ammountToInv").val( '0' );
		
		}else{
		
			$("#ammountToInv").val( args.montoSugerido );
			
		}
		
		
		
		$("#ammountToInv").trigger("blur");
		
		
		$(".errorInversion").addClass("show");
		$(".velo").fadeIn();
		
		$("#mensajeErrorMonto").empty();
		$("#mensajeErrorMonto").html(args.msgInv);
		$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").css("backgroundColor", "#ccc");
		$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").find(".montoSugerido").show();
		
	}
	
	console.log("quitando fancy ..");
	
	setTimeout( function(){ $.fancybox.close(); } ,"550" );
	
	flagValAmmInv = true;

}

function resNewValToInvFloat(xhr, status, args){
	
	console.log("regresando a resNewValToInvFloat ..");
	
	//alert("----------------");
	
	if(args.hasError){
		
		if(parseInt(args.montoSugerido)==0)
		{
			$("#ammountToInvFlot").val( '0' );
		
		}else{
		
			$("#ammountToInvFlot").val( args.montoSugerido );
			
		}
		
		
		
		$("#ammountToInvFlot").trigger("blur");
		$(".errorInversion").addClass("show");
		$(".velo").fadeIn();
		$("#mensajeErrorMonto").empty();
		$("#mensajeErrorMonto").html(args.msgInv);
		$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").css("backgroundColor", "#ccc");
		$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").find(".montoSugerido").show();
	}
	
	console.log("quitando fancy ..");
	
	setTimeout( function(){ $.fancybox.close(); } ,"550" );
	
	flagValAmmInv = true;
}

var flagCompletNewVal = true;

function resNewVal(xhr, status, args){
	
	//if(flagCompletNewVal){
		flagCompletNewVal = false;
		if(args.hasError){
			
			if(parseInt(args.montoSugerido)==0){
				$("#dvBiteInput_"+args.proyect_loan_id).find("input:eq(0)").val( '0' );
			}else{
				$("#dvBiteInput_"+args.proyect_loan_id).find("input:eq(0)").val( args.montoSugerido );
			}
			
			$(".errorInversion").addClass("show");
			$(".velo").fadeIn();
			$("#mensajeErrorMonto").empty();
			$("#mensajeErrorMonto").html(args.msgInv);
			
			$("#dvBiteInput_"+args.proyect_loan_id).find("input:eq(0)").trigger("focus"); //.delay(100).trigger("blur");;
			$("#dvBiteInput_"+args.proyect_loan_id).find("input:eq(0)").trigger("blur");
			$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").css("backgroundColor", "#ccc");
			$("#dvBiteInput_"+args.proyect_loan_id).closest(".inversionCelda").find(".montoSugerido").show();
		
		
		}
	
	/*}else{
		flagCompletNewVal = true;
	}*/
	
	flagValAmmInv = true;
	
}
	

function delayFuncInv(){

		if(flagChckInv && flagValAmmInv){

				$("#cmdConfirmInvActionFlot_01").trigger("click");	
		}else{
			console.log("else inversiones entro ---");
			showMessageAlert();
			setTimeout(function(){ hideMessage_01(); }, 2500);
		}
	}

function hideMessage_01(){
	cargaValorActualTMP
	hideMessageAlert();
	setTimeout(function(){ bridgeFunctionInvPaq(); }, 100);
	
}

function reflejaValor(element){
	
	if( $(element).val() == "" ){
		
		$(element).next("input").val("0");
		 $(element).val("0");
	}else{
	
		$(element).next("input").val($(element).val());
	
	}
	
	console.log( "reflejaValor: "+ $(element).val() );
	
	$(element).next("input").blur();
	
	
}

function showMessageAlert(){
		
	//$(".clssMssgAlrt").show("fast");
	
} 

function hideMessageAlert(){
	
	//$(".clssMssgAlrt").hide("fast");
	
	//$(".clssMssgAlrt").css("display","none");
	
}
