	/* inicia Charts */
	var dataArea = null;
	
	function initLog()
	{		
		$("#inpLog").val( $("#confpass").val() );
		
		$("#inpLogUsr").val( $("#usr").val() );
		
		$("#inpLogUsr").blur();
		$("#inpLog").blur();
		
		return true;	
	}
	
	function returnInit(xhr, status, args)
	{		
		var isLogged = args.isLogged;	
		
		if(isLogged)
		{
			$('#strMsg').empty();
			$.fancybox.close();
			
			//$("#initAction").click();
			//$('#valGraphic').val($('#burSolNum').val());
			$('#valGraphic').blur();
			flagGraph=false;
		
			
		} else {

			var str = args.msg;
			$('#strMsg').empty();
			$('#strMsg').html(str);
			
		}		
	}
	
	function keyEventIntro(e, component)
	{
		var key = e.keyCode || e.which;
				
		if (key === 13) 
		{
			$(component).blur(); 
			$("#btnLoggin").click(); 					
		}
	}
	
	function hideTableNote()
	{
		
		$("#dvContRecommendationNote").fadeOut(
				{
					duration:  1000,
					easing:		'easeOutBack',
					complete: function(){ $("#btnRecommen").fadeIn({duration:1000, easing:'easeOutBack'}); } 
				});		
	}
	
	function onClckJsonAct()
	{
		$("#cmdJsonAction").blur();
	}
	
	function completeJson()
	{
		closeMessageProcessing();
		flagF = true;
		drawChartBuro();		
	}
	
	function startJson()
	{
		
		var jSonStr = "{'newCompany':'"+ $("#acSimple_input").val()+"','Prev_Entity':'"+ $("#prevName").val() +"'}";
		
		$("#jsonVal").val(jSonStr);
		$("#jsonVal").blur();
		
		$.fancybox.close();
		$("#acSimple_input").val("");
		displayMessageProcessing('msgprocessing', false);
	
		return true;		
	}
	
	var flagF = true;
	   	
   	function inicializaTable()
   	{    		 
	   	$("#btnRecommen").click(function(e)
	   	{
	   		e.preventDefault();
	   					
	   		$("#btnRecommen").fadeOut(
			{
				duration:  1000,
    			easing:		'easeOutBack',
    			complete: function()
    			{ 
    				$("#dvContRecommendationNote").fadeIn({duration:1000, easing:'easeOutBack'}); 
    			} 
			});
		});
	   		
			    		//maxWidthTableVig = $("#maxWidthTableVig").val();    		
			    		 maxWidthTableVig = 788;
	   		 
	   	if(flagF)
	   	{	   		
		    flagF = false;
		    				    				    		
    		$('.fixme').fixheadertable(
    		{
                caption : 'Créditos Vigentes',
               
                width	: maxWidthTableVig ,
                zebra   : false,
                resizeCol      : true,
                colratio	: [65,180,280,100,80,100,100,90,90,110,100,180,110,115,140,95,105,105,105,105,135,90,90,135,135,135,135,200,135,320,320,650,5]
           });
		    		
    		$('.fixmeCom').fixheadertable({
                caption : 'Créditos Vigentes Comunicaciones',
    
                width	: maxWidthTableVig ,
                zebra   : false,
                resizeCol      : true,
                colratio	: [55,85,150,200,350,80,200,140,140,120,170,140,140,145,135,155,135,180,120,180,200,200,200,200,200,190,320,320,200,650,5]
           });
		    		
    		$('.fixmeConsultUlt').fixheadertable({
                caption : 'Consultas en los Últimos 6 meses',
               
                width	: maxWidthTableVig ,
                zebra   : false,
                resizeCol      : true,
                colratio	: [55,200,200,200,200,200,180,140]
           });
		    		
    	 $('.fixmeConsultMS').fixheadertable({
                caption : 'Consultas hace más de 6 meses',
               
                width	: maxWidthTableVig ,
                zebra   : false,
                resizeCol      : true,
                colratio	: [55,200,200,200,200,200,180,140]
           });
		    		
    		$('.tableDomicilios').fixheadertable({
                caption : 'Domicilios',
               
                width	: maxWidthTableVig ,
                zebra   : false,
                resizeCol      : true,
                colratio	: [55,200,200,200,200,200,200,200,200,180,140]
           });
		    		
		    		$('.tableCredClose').fixheadertable({
		                caption : 'Créditos Cerrados',
		            
		                width	: maxWidthTableVig ,
		                zebra   : false,
		                resizeCol      : true,
		                colratio	: [55,85,200,200,200,200,200,200,350,200,200,200,200,200,200,200,200,200,200,200,200,180,650,5]
		           });
		    		
		    		$('.tableCredCloseM6').fixheadertable({
		                caption : 'Créditos Cerrados hace más de 6 meses',
		               
		                width	: maxWidthTableVig ,
		                zebra   : false,
		                resizeCol      : true,
		                colratio	: [55,85,200,200,200,200,200,200,350,200,200,200,200,200,200,200,200,200,200,200,200,180,650,5]
		           });
		    		
		    		$('.tableAlerts01').fixheadertable({
		                caption : 'Datos Alertas Consultas',
		              
		                width	: maxWidthTableVig ,
		                zebra   : false,
		                resizeCol      : true,
		                colratio	: [200,200,200,200,200,200]
		           });
		    		
		    		$('.tableAlerts02').fixheadertable({
		                caption : 'Datos Alertas Reporte',
		               
		                width	: maxWidthTableVig ,
		                zebra   : false,
		                resizeCol      : true,
		                colratio	: [200,200,200,200,200,200]
		           });
		    		
	   		}
	   	}
	
	function returnNoteFunction()
	{
		closeMessageProcessing();
		$('#person_kubo_select').val('');
		$('#recommentaion_kubo_select').val('');
		$('#txt-capture-recommendation').val('');
		hideTableNote();
	}
	   	
	function initGraph()
	{
		 // alert("initGraph .. solnum: "+$('#valGraphic').val());
		 $('#valGraphic').blur();
		
	}
	
	var rowData;
	var myColors;   		
	var rowData = new google.visualization.DataTable();
	var chart;
	var gridV;
	var maxWidthTableVig;
	
	function chartReload_oncomplete(xhr, args, status)
	{
		var delete_cache_OK = args.delete_cache_OK
		
		console.log("chartReload_oncomplete(): ");
		console.log("delete_cache_OK = " + delete_cache_OK);
		
		drawChartBuro();
	}
	
	function drawChartBuro() 
	{ 		
		if(google.setOnLoadCallback(chartDrawBuro))
		{
			
		} else {
			
			chartDrawBuro();
		}
	}
	
	function hideDetTScore()
	{
		$("#dvContDetTScore").hide();
	}
	
	function chartDrawBuro() 
	{ 
	
		if( rowData )
		{
			if(rowData != "SIN_RESPUESTA")
			{
				
	 					$("#loaderGraph").hide();
	 					$("#chart_warning").hide();
	 					$("#chart_divCont").show();
		    	  	//var data = google.visualization.arrayToDataTable(rowData);
		    	  	//title: 'Gráfica MOP histórico'// #{chartBackBean.nombreCompleto}',
			        var options = {
				          title: 'Gráfica MOP histórico',
				          hAxis: {title: 'Meses',  titleTextStyle: {color: '#000'}},
				          vAxis: {title: 'MOP',    titleTextStyle: {color: '#000'}, gridlines: {color: '#d9d9d9',  count: gridV},textPosition:'bottom', textStyle: { fontSize: 13},minValue: 0 },
				          chartArea:{left:50,top:50,width:"70%",height:"70%"},
				          hAxis:	{slantedText: true, slantedTextAngle: 90, textStyle: { fontSize: 13}},
				          width: 845,
				          height: 500,
				          legend: {position: 'right ',alignment: 'end', textStyle: { fontSize: 13}},
				          colors: myColors,
				          pointSize: 1,
				          interpolateNulls: true,
				          tooltip: {isHtml: true,trigger: 'selection'}
				        };
			        
			       if (document.getElementById('chart_div') != null ){
			        
			        chart = new google.visualization.LineChart(document.getElementById('chart_div'));
			        setTimeout(function(){callGraph(options);},500);
			        
			       }
			        
			        
			        
			        inicializaTable();
			        
			        inicializaSaldosDeuda();
			        
					closeMessageProcessing();
		        	
	 				}else{
	 					$("#loaderGraph").hide();
	 					$("#chart_divCont").hide();
	 					$("#chart_warning").show();
	 					$("#chart_divCont").show();
	 				}
			
			
		}else{
			$("#loaderGraph").hide();
			$("#chart_divCont").hide();
			$("#chart_warning").show();
			$("#chart_divCont").hide();
		}
		
		sustituyeNombres();
		
//		$("#cmdCargaBloqueos").click();
//		$("#cmdCargaConsultBuro").click();
		
		
	    }
	
	function callGraph(options){
		chart.draw(rowData, options);
		
	/* 	$("#loaderGraph").hide();
			$("#chart_divCont").show(); */
		
	}
	
	function inicializaSaldosDeuda(){
		
		if(dataArea != null){
		
			var optionsArea = {
			          title: '',
			          hAxis:	{slantedText: true,title: 'Fecha', slantedTextAngle: 90, textStyle: { fontSize: 13},titleTextStyle: {color: '#333'}},
			          vAxis: {minValue: 0},
			          width: 860,
			          chartArea: {left:85,top:30,width:'78%'},
			          crosshair: { focused: { color: '#fff', opacity: 0.1 },trigger: 'both',orientation: 'horizontal' },
			          focusTarget: 'category',
			          isStacked : true,
			          lineWidth: 0.3,
			          areaOpacity: 0.5,
			          tooltip: { isHtml: true },
			          'colors':['#558B2F','#FF9800','#FFC107','#AFB42B','#4CAF50']
			        };

			        var chart = new google.visualization.AreaChart(document.getElementById('chart_div_area'));
			        chart.draw(dataArea, optionsArea);
		        
		}else{
			
			$("#chart_div_area").html("<div style='font-size:1.3em;font-weight: bold; width:99%; text-align: center;'>Usuario sin Datos de Créditos a Pagos Fijos</div>");
			
		}

	}
	
	function validaSolNum()
	{	
		console.log("validaSolNum(): ");
		console.log($('#valGraphic').val() + ' --  bursol: ' + $('#burSolNum').val());
		
		if( $('#valGraphic').val() == null )
		{			
			$('#valGraphic').val($('#burSolNum').val());			
		}
		
		console.log("regresando ........."+$('#valGraphic').val());
		
		displayMessageProcessing('msgprocessing', false);
		
		return true;
	}
	
	function hideTooltip(){
		try{
			
			//$(".google-visualization-tooltip").hide(); 
			chart.setSelection();
		
		}
		catch(err) {
		    console.log(err);
		}
		
	}
	
	function sustituyeNombres(){
		
		console.log( " Ejecutando sustituyeNombres() --- " );
		
		var indentifyVal = $("#identifyValStr").val();
		
		console.log( " cadena --- " + indentifyVal);
		
		if( indentifyVal != null && indentifyVal.length > 0 ){
		
			$( ".clIdentified" ).each(function( index ) {
				
				var classLst = $(this).attr("class").split(' ');
				
				for(var i = 0 ; i < classLst.length ; i ++){
					
					var strTmp = "clIdentTD";
					
					if( classLst[i].indexOf(strTmp) != (-1) ){
						var strCls_tmp =  classLst[i];
						
						console.log();
						
						var strCls = strCls_tmp.substring( strTmp.length )
						
						var credit =  $("#indentHidden"+strCls).val() ;
						
						console.log( "creditVal: indentHidden"+strCls+" -- "+ credit )
						
						if( credit != null && credit.length > 0 ){
						
							if(indentifyVal.indexOf(credit) != (-1)){
								
								var intSub1 = indentifyVal.indexOf(credit) + (credit+"::").length;
								var sub1 = indentifyVal.substring(intSub1);
								var indentificado = sub1.substring(0,sub1.indexOf("##"));
								
								var element = $("."+strCls_tmp)[0];
								
								//element.prev().empty();
								element.innerHTML = "<b>"+indentificado+"</b>" ;
								
								console.log( "indentificado: " + indentificado );
								break;
							}
							
						}
					}
					
				}
				
			});
		
		}else{
			console.log( "Sin creditos identificados ... " );
		}
		
	}
	
	function resultadoCargaConsultas(){
		
		//alert( "regresando: " + $("#dvcsltPy") );
		
		$("#dvWaithConsult").hide(  );
		
		$("#dvcsltPy").show();
		
	}
	
	/*fin de charts*/	