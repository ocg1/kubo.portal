function muestraResList(xhr, status, args){
	
	$("#dvmemberHtml").empty();
	
	var stringAppend="";
	
	flagSellAll = false;
	
		if( args.is_valid == "S" ){
			
			var arrayMember =eval('('+ args.miembrosList +')');
			
			stringAppend="<table style='border-collapse: collapse;'>";
			
			stringAppend += "<tr><td colspan='3' style='padding: 15px;' > " +
											"Las siguientes personas cumplen con las especificaciones de busqueda. " +
											"<br /> " +
											"Recuerda que debes contar con la autorización firmada y la copia de la " +
											"credencial de elector del cliente para que puedas realizar la consulta.  " +
							"</td></tr>";
			
			stringAppend += "<tr><td  colspan='3'><div id='dvSelAll' style='text-align:right; cursor:pointer;' onclick='selAllAction();' >Seleccionar Todos</div></td></tr>";
			
			for (target in arrayMember){		
				 
				if(target!='remove')
				{
					
					var member = arrayMember[target];
					
					stringAppend += "<tr><td onclick='muestraDescripttoAcreAconManual(\"persona_"+member.membershipPK.company_id+"::"+member.membershipPK.prospectus_id+"\")'  style='border-top:solid 1px #333; color: #333;padding: 10px;cursor: pointer;'>";
					
					stringAppend += member.person.first_name+ " " + member.person.middle_name + " "  + member.person.father_last_name + " "  + member.person.mother_last_name  ;
					
					stringAppend += "</td>";
					
					stringAppend += "<td style='border-top:solid 1px #333;' ><div onclick='muestraDescripttoAcreAconManual(\"persona_"+member.membershipPK.company_id+"::"+member.membershipPK.prospectus_id+"\")' class='ui-icon ui-icon-triangle-1-s' style='cursor: pointer;' /></td>";
					
					stringAppend += "<td style='border-top:solid 1px #333; color: #333;padding: 10px;'> <input type='checkbox' onclick='validaCheck();' class='checkconsult' value = '"+member.membershipPK.company_id+"::"+member.membershipPK.prospectus_id+"' /> </td></tr>";
					
					stringAppend += "<tr><td colspan='3' > <div id='dv_"+member.membershipPK.company_id+"::"+member.membershipPK.prospectus_id+"' style='width: 99%; border: solid 1px #E6E6E6;display:none; margin-top: 10px; margin-bottom: 10px;'> </div>  </td></tr>";
					
				 }
				
				
				
			}
			
			stringAppend+="</table>";
			
		}else{
			
			stringAppend="<table><tr><td style='padding: 10px; text-align: center;' > No se encontraron resultados para esta busqueda </td></tr></table>";
			
		}
		
		
		$("#dvmemberHtml").empty();
		
		$("#dvmemberHtml").html( stringAppend );
		
		
		$("#dvWaitDetail").hide("fade",500,function(){
			
			$("#dvContMemberHtml").show();
			$("#dvBtnConsultMasiva").show();
			$("#dvmemberHtml").show("fade",500);
			$("#resConsulta").hide();
			
		});
		
		
}

function muestraDescripttoAcreAconManual(persona){
	
	$("#txtValAcreToConsul").val( persona );
	$("#txtValAcreToConsul").trigger( "blur" );
	
}

function muestraDescriptionAction( xhr, status, args ){
	
	//alert( args.nombre );
	
	console.log( args.mi_valor + "  " + args.dv_id );
	
	 // if(true){
	
	 var	htmlStr = 	"<table> " +
						"<tr> " +
							"<td> Nombre completo: </td> " +
							"<td> <b>"+args.nombre+"</b> </td> " +
						"</tr> " +
						"<tr> " +
							"<td> Fecha de nacimiento: </td> " +
							"<td> <b>"+args.fecnac+"</b>  </td> " +
						"</tr> " +
						"<tr> " +
							"<td> RFC: </td>  " +
							"<td> <b>"+args.rfc+"</b> </td>  " +
						"</tr>  " +
						"<tr> " +
							"<td> CURP: </td> " +
							"<td> <b>"+args.curp+"</b> </td> " +
						"</tr> " +
					"</table> " ;
		
	 
	 var str02 = args.dv_id;
	 
	 document.getElementById(str02).innerHTML = "";
	 
		//$( "#"+str02+"" ).html( htmlStr );
		
		//alert( $( "#"+str02+"" ).html() );
	 
	 document.getElementById(str02).innerHTML = htmlStr;
	 
	 document.getElementById(str02).style.display = "block";
		
	// }
	
}

function selAllAction(){
	
	if($("#dvSelAll").html() == "Seleccionar Todos" ){
	
		$(".checkconsult").each(function( index ){
			
			$(this).attr('checked',true);
			
		});
	
		$("#dvSelAll").html( "Quitar Selección" );
		
	}else{
		$(".checkconsult").each(function( index ){
			
			$(this).attr('checked',false);
			
		});
	
		$("#dvSelAll").html( "Seleccionar Todos" );
	}
}

function onstartConsultaMasiva(){
	
	var itemSelStr = "";
	var numItem = 0;
	
	$(".checkconsult").each(function( index ){
	
			if($(this).is(':checked')) { 
				 
				itemSelStr += $(this).val()+"&&";
				numItem++;
			 }
			
	});
	
	if ( confirm( "Estás seguro de querer consultar a las "+numItem+" personas que seleccionste?" ) ){
		
		$("#txtValoresAcreditadosConsultarAction").val(itemSelStr);
		
		$("#txtValoresAcreditadosConsultarAction").blur();
		
		$("#dvContMemberHtml").hide("fade",500,function(){
			
			$("#dvWaitDetail").show("fade",500);
			$("#resConsulta").hide();
			
		});
		
		return true;
		
	}else{
		return false;
	}
	
}

function regresaConsultaMasiva(xhr, status, args){
	
	var strHtml = "";
	
	if(args.haveErrors){
	
	strHtml="<table>" +
			
				"<tr> <td colspan='3'> De un total de "+args.totalconsultas+" personas se logró la consulta satisfactoria de "+args.intConsultas+" </td> </tr>" +
				"<tr> <td colspan='3'> Se encontraron los siguientes errores  </td> </tr>" ;
				
				var arrayMember =eval('('+ args.lstErrors +')');

				for (target in arrayMember){		
					 
					if(target!='remove')
					{
						var errorItem = arrayMember[target];
						
						strHtml += "<tr>" +
										"<td>"+ errorItem.member. membershipPK.prospectus_id+"</td>" +
										"<td>" +
						                	errorItem.member.person.first_name+ " " + 
						                	errorItem.member.person.middle_name + " " + 
						                	errorItem.member.person.father_last_name + " "  + 
						                	errorItem.member.person.mother_last_name  + "" +
						                "</td>" +
						                "<td>"+errorItem.error_descript+"</td>" +
						             "</tr>";
						
					}
					
				}
				
				strHtml +=  "</table>" ;
				
	}else{
		
		strHtml="<table>" +
		
					"<tr>" +
					"	<td colspan='3'> " +
							"	De un total de "+args.totalconsultas+" personas se logró la consulta satisfactoria de "+args.intConsultas+" " +
						"</td> " +
					"</tr>" +
		
				"</table>" ;
		
	}
	
	document.getElementById("dvmemberHtml").innerHTML = "";
	document.getElementById("dvContMemberConsultados").innerHTML = "";
	document.getElementById("dvContMemberConsultados").innerHTML = strHtml;
	
	//$("#dvContMemberConsultados").html( strHtml );
	
	$("#dvWaitDetail").hide("fade",500,function(){
		$("#dvContMemberConsultados").show();
		$("#dvBtnConsultMasiva").hide();
		
		$("#dvContMemberHtml").show("fade",500,function(){
			
			$("#dvWaitDetail").hide();
			
		});
	});
	
	//$("#resConsulta").hide();
	
}

function validaCheck(){
	
	
	
}