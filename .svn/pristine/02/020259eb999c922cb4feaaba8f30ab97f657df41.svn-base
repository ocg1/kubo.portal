function drawChartsDgn()
{
		
	if(dataArea != null)
	{
		
		var optionsArea = {
	          title: '',
	          hAxis:	{slantedText: true,title: 'Fecha', slantedTextAngle: 90, textStyle: { fontSize: 13},titleTextStyle: {color: '#fff'}},
	          vAxis: {minValue: 0},
	          width: 860,
	          chartArea: {left:85,top:30,width:'78%'},
	          crosshair: { focused: { color: '#fff', opacity: 0.1 },trigger: 'both',orientation: 'horizontal' },
	          focusTarget: 'category',
	          isStacked : true,
	          lineWidth: 0.3,
	          areaOpacity: 0.5,
	          interpolateNulls: true,
	          tooltip: { isHtml: true },
	          'colors':['#558B2F','#FF9800','#FFC107','#AFB42B','#4CAF50'],
	        };

	        var chart = new google.visualization.AreaChart(document.getElementById('chart_div_area3'));
	        chart.draw(dataArea, optionsArea);
	        
	        //$("#chart_div_area3").css("min-height","300px");	        
	        
		} else {
			
			$("#chart_div_area3").html("<div style='font-size:1.3em;font-weight: bold; width:99%; text-align: center;'><b>Usuario sin datos de Créditos a Pagos Fijos</b></div>");
			$("#chart_div_area3").css("height","50px"); 						
		}
		 
		if(graphic4 != null)
		{
	        var data2 = new google.visualization.DataTable();
			data2.addColumn('string', 'Topping');
			data2.addColumn('number', 'Slices');
			//alert(graphic4)
			data2.addRows(graphic4);
			
			var options = {
						'title':'',
						'width':310,
						'height':200,
						'chartArea':{'width':310},
						'colors':['#558B2F','#FF9800','#FFC107','#AFB42B'],
						'is3D': false,
						'sliceVisibilityThreshold': (1/9720),
						'legend':{'textStyle':{'fontSize': 16}}
					};
	
			var chart2 = new google.visualization.PieChart(document.getElementById('creditosGrp'));
			chart2.draw(data2, options);
			
			$("#nivel_endeudamiento_container").css("page-break-after", "always");
			
		} else {
			$("#creditosGrp").html("<div><b>Usuario sin Datos de Créditos Anteriores</b></div>");
		}	
				
		/* Saldo de créditos revolventes */

		if(graphicRevolventes != null)
		{
			
			var dataRev = google.visualization.arrayToDataTable(graphicRevolventes);
		
			var optionsRev = {
	                        width: 500,
	                        height: 300,
	                        legend: { position: 'top', maxLines: 3 },
	                 		bar: { groupWidth: '30%' },
	                        isStacked: true,
	                 		orientation: 'horizontal',
	                 		 'colors':['#558B2F','#FF9800','#FFC107','#AFB42B','#4CAF50']
	                       };
	
	        var chartRev = new google.visualization.BarChart(document.getElementById('chart_div_rev'));
	        chartRev.draw( dataRev, optionsRev );
		}else{
			$("#chart_div_rev").html("<div><b>Usuario sin Líneas de Créditos Abiertas actualmente</b></div>");
		}
		
		closeMessageProcessing();
	}

function createCustomHTMLDiagCntnt(status,total){
	
	var res = "";
	
	if(total != '-'){
		
		res = "<b>"+status+"</b><br /><br /><b>Total: "+total+"</b>";
		
	}else{
		res = "<b>"+status+"</b>";
	}
	
	return res;
	
}

function customHTMLDiagCnsltCnt(fecConsul){
	
	return "<div style='padding: 8px;' ><div style='width: 99%; margin-left: auto; margin-right: auto; text-align: center;'>Fecha de Consulta</div><br />KUBOFINANCIERO: <b>"+fecConsul+"</b></div>";
	
}

var imprimir_panel_ENABLED = false;

function imprimir_panel(panel_id)
{				
	var header = '' 
		+ '<html><head>'		
		+ '<link type = "text/css" rel = "stylesheet" href = "../resources/css/templates/tipografia/tipografia.css" />'
		+ '<link type = "text/css" rel = "stylesheet" href = "../resources/css/secciones/diagnostico_financiero/diagnostico.css" />'
		+ '</head><body>';
	
	var header_logo = '<img src="../resources/img/LOGO.png" style="height:100px; width: 100px; border: 0px; margin-bottom:10px" />';
	
	var razon_social = ''
		+ '<div style ="height:100px; width: 265px; border:0px solid black; float:right; ">'
		+ '<b>ku-bo financiero,'
		+ 'S.A. DE C.V., S.F.P. </b><br />'
		+ 'Barranca del Muerto 92, Col. Florida, <br />'
		+ 'C.P. 01030, México D.F. <br />' 
		+ 'Tel. 62-69-00-24'
		+ '</div>';
	
	var clear_div = '<div style = "clear:both;"/>';
	
	var header_fin  = '</body></html>';
	
	var panel = $('#' + panel_id);

	var pop_up = window.open('','popimpr');

	pop_up.document.write(header);
	pop_up.document.write(header_logo);
	pop_up.document.write(razon_social);
	pop_up.document.write(clear_div);
	pop_up.document.write(panel.html());
	pop_up.document.write(header_fin);
	
	if(imprimir_panel_ENABLED)
	{
		pop_up.print();	
		pop_up.close();
		
		imprimir_panel_ENABLED = false;
		
	} else {
				
		pop_up.close();
		
		imprimir_panel_ENABLED = true;
		
		imprimir_panel(panel_id);
	}
	
}

