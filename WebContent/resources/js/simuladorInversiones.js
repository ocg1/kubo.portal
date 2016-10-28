var chartB  = null;
var chartA  = null;
var chartAV = null;
var chartARI = null;
var chartAPIRI = null;
var chartARenRI = null;
		

$(document).ready(function(){
	
	$("#ammountInv").focus(function() { $(this).select(); } );
	
	$(".clsRisk").click( function(){
		
		if($(this).hasClass("itemCheck")){
			$(this).removeClass("itemCheck");
		}else{
			$(this).addClass("itemCheck");
		}
		
		var i = 0;
		$(".clsRisk").each(function(){
			if($(this).hasClass("itemCheck")){
				i++;
			}
		});
		
		if( i == 0){
			$(this).addClass("itemCheck");
			return;
		}
		
		reiniciaGraficas();
		$("#riskelement").val("");
		
		$(".clsRisk").each(function(){
			if($(this).hasClass("itemCheck")){
				var valId = $(this).attr("id")+"::";
				var val = $("#riskelement").val()+valId;
				$("#riskelement").val(val);
			}
		});
		
		$("#riskelement").blur();
		setTimeout(function(){ $("#lnkAction").click(); } , 80);
		
	} );
	
	$(".clsTerm").click( function(){
		
		if($(this).hasClass("itemCheck")){
			$(this).removeClass("itemCheck");
		}else{
			$(this).addClass("itemCheck");
		}
		
		var i = 0;
		$(".clsTerm").each(function(){
			if($(this).hasClass("itemCheck")){
				i++;
			}
		});
		
		if( i == 0){
			$(this).addClass("itemCheck");
			return;
		}
		
		reiniciaGraficas();
		$("#termelement").val("");
		
		$(".clsTerm").each(function(){
			if($(this).hasClass("itemCheck")){
				var valId = $(this).attr("id")+"::";
				var val = $("#termelement").val()+valId;
				$("#termelement").val(val);
			}
		});
		
		$("#termelement").blur();
		setTimeout(function(){ $("#lnkAction").click(); } , 80);
		
	} );
	
	$(".clsGender").click( function(){
		
		if($(this).hasClass("itemCheck")){
			$(this).removeClass("itemCheck");
		}else{
			$(this).addClass("itemCheck");
		}
		
		var i = 0;
		$(".clsGender").each(function(){
			if($(this).hasClass("itemCheck")){
				i++;
			}
		});
		
		if( i == 0){
			$(this).addClass("itemCheck");
			return;
		}
		
		reiniciaGraficas();
		$("#genderelement").val("");
		
		$(".clsGender").each(function(){
			if($(this).hasClass("itemCheck")){
				var valId = $(this).attr("id")+"::";
				var val = $("#genderelement").val()+valId;
				$("#genderelement").val(val);
			}
		});
		
		$("#genderelement").blur();
		setTimeout(function(){ $("#lnkAction").click(); } , 80);
		
	} );
	
	$(".clItemTitT2").click( function(){
		
		if($(this).hasClass("itemCheck"))
		{
			$(this).removeClass("itemCheck");
			
		}else{

			$(this).addClass("itemCheck");
			
		}
		
		
		var i = 0;
		$(".clItemTitT2").each(function(){
			if($(this).hasClass("itemCheck")){
				i++;
			}
		});
		
		if( i == 0)
		{
			$(this).addClass("itemCheck");
			return;
		}
		
		reiniciaGraficas();
		
		$("#destinolement").val("");
		
		$(".clItemTitT2").each(function(){
			if($(this).hasClass("itemCheck")){
				var valId = $(this).attr("id")+"::";
				var val = $("#destinolement").val()+valId;
				$("#destinolement").val(val);
			}
		});
		
		$("#destinolement").blur();
		setTimeout(function(){ $("#lnkAction").click(); } , 80);
		
	} );	

	
	
	chartB = new google.visualization.BarChart (document.getElementById('chart_bar_div' ));
	chartA = new google.visualization.AreaChart(document.getElementById('chart_area_div'));
	chartAV = new google.visualization.AreaChart(document.getElementById('chart_area_vig_div'));
	/*chartARI = new google.visualization.AreaChart(document.getElementById('chart_area_div_reinversion_total'));
	chartAPIRI = new google.visualization.AreaChart(document.getElementById('chart_area_div_pagos_interes_reinversion_total'));
	chartARenRI = new google.visualization.LineChart(document.getElementById('linechart_rendimientos'));
	*/
	drawChart();
	
});
		
		
function drawChart() {
	    	  
	    	  /*******************************************************************************/
	    	  

	        var options = {
					          title: 'Distribución del portafolio',
					          legend:{position:'bottom'},
					          titleTextStyle: {fontSize: 15},
					          'colors':['#003366','#7BC9DC','#83BA52','#9DDF3D','#FDC93D','#FDC93D','#FFA220'],
					          'sliceVisibilityThreshold': (1/9720),
							  'is3D': true,
							  tooltip: { isHtml: true }
					      };

	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	        
	        // var dataRev = google.visualization.arrayToDataTable(data);
	        
	        chart.draw(data, options);
	        
	        /****************************************************************************************** /
	        
	        var dataL = google.visualization.arrayToDataTable
	        					([
                                   ['Year', 'Kubo', 'Banco'],
                                   ['12',  17.4,      4],
                                   ['24',  39.1,      8.2],
                                   ['36',  64.2,      12.5],
                                   ['48',  93.3,      17]
                                 ]);

                                 var optionsL = {
                                   title: 'Rendimientos - Kubo vs Banco',
                                   titleTextStyle: {fontSize: 15},
                                   'colors':['#003366','#7BC9DC'],
                                   pointShape: 'diamond',
                                   pointSize: 10,
     							  animation:{
  							        duration: 1000,
  							        easing: 'out'
  							      }
                                 };
                                 
                                 var chartL = new google.visualization.LineChart(document.getElementById('chart_line_div'));
                                 
                                 google.visualization.events.addListener(chartL, 'ready',
                                         function() {
                                           // button.disabled = false;
                                         });

                                 
                                 chartL.draw(dataL, optionsL);
                                 
           /****************************************************************************************************/
           
           

                                 var optionsB = {
                                   title: 'Comportamiento pagos',
                                   isStacked : true,
                                   titleTextStyle: {fontSize: 15},
                                   'colors':['#9DDF3D','#FDC93D'],
                                   orientation: 'horizontal',
                                   tooltip: { isHtml: true },
                                   focusTarget: 'category',
                                   hAxis:	{slantedText: true, slantedTextAngle: 90},
                                   animation:{
     							        duration: 500,
     							        easing: 'out'
     							      }
                                 };

                                
									
                                 
                                 google.visualization.events.addListener(chartB, 'ready',
                                         function() {
                                           // button.disabled = false;
                                         });
                                 
                                 chartB.draw(dataB, optionsB);

                                
			/****************************************************************************************************/
			
              

                                  var optionsA = {
                                    title: 'Pago de intereses',
                                    hAxis: {titleTextStyle: {color: '#333'}},
                                    vAxis: {minValue: 0},
                                    titleTextStyle: {fontSize: 15},
                                    'colors':['#FDC93D','#FFA220'],
                                    pointShape: 'diamond',
                                    pointSize: 10,
                                    tooltip: { isHtml: true },
                                    animation:{
      							        duration: 500,
      							        easing: 'out'
      							      }
                                  };

                                 
                                  
                                  google.visualization.events.addListener(chartA, 'ready',
                                          function() {
                                            // button.disabled = false;
                                          });
                                  
                                  chartA.draw(dataA, optionsA);
                                  
             /****************************************************************************************************/
                                  
			
                                  var optionsAV = {
                                          title: 'Proyectos Activos',
                                          hAxis: {titleTextStyle: {color: '#333'}},
                                          vAxis: {minValue: 0},
                                          titleTextStyle: {fontSize: 15},
                                          'colors':['#FDC93D','#FFA220'],
                                          pointShape: 	'diamond',
                                          pointSize: 	3,
                                          tooltip: 		{ isHtml: true },
                                          hAxis:		{	slantedText: true, slantedTextAngle: 90},
		                                  animation:	{
		            							        	duration: 500,
		            							        	easing: 'out'
		            							      	}
                                        };

                                       
                                        
                                        google.visualization.events.addListener(chartAV, 'ready',
                                                function() {
                                                  // button.disabled = false;
                                                });
                                        
                                        chartAV.draw(dataAV, optionsAV);
                                        
                                        
              /**************************************************************************************************** /
                            			
                                        

                                        var optionsARI = {
                                          title: 'Incremento Inversión - Reinversión',
                                          hAxis: {titleTextStyle: {color: '#333'}},
                                          vAxis: {minValue: 0},
                                          titleTextStyle: {fontSize: 15},
                                          pointShape: 'diamond',
                                          pointSize: 10,
                                          tooltip: { isHtml: true },
                                          animation:{
            							        duration: 500,
            							        easing: 'out'
            							      }
                                        };

                                       
                                        var dataARI  = new google.visualization.DataTable();
                                		
                                        dataARI.addColumn('string', 'Meses');
                               			
                                        dataARI.addColumn('number', 'Intereses');
                                        dataARI.addColumn( { type: 'string', role: 'annotation' });
                                        dataARI.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );
                                        dataARI.addRows([
                                                ['Hoy'	   ,  50000 , '$50,000',createCustomHTMLAreaCntnt( '$50,000')], 
                                                ['12 meses',  57478 , '$57,478',createCustomHTMLAreaCntnt( '$57,478')],
                                                ['18 meses',  62002 , '$62,002',createCustomHTMLAreaCntnt( '$62,002')],
                                                ['24 meses',  66908 , '$66,908',createCustomHTMLAreaCntnt( '$66,908')],
                                                ['36 meses',  77617 , '$77,617',createCustomHTMLAreaCntnt( '$77,617')],
                                                ['48 meses',  89646 , '$89,646',createCustomHTMLAreaCntnt( '$89,646')]
                                                                 ]);
                                        
                                        google.visualization.events.addListener(chartARI, 'ready',
                                                function() {
                                                  // button.disabled = false;
                                                });
                                        
                                        chartARI.draw(dataARI, optionsARI);
                                        
                                        
         / **************************************************************************************************** /
                            			
                                        

                                        var optionsAPIRI = {
                                          title: 'Pago de intereses - Reinversión',
                                          hAxis: {titleTextStyle: {color: '#333'}},
                                          vAxis: {minValue: 0},
                                          titleTextStyle: {fontSize: 15},
                                          pointShape: 'diamond',
                                          pointSize: 10,
                                          tooltip: { isHtml: true },
                                          animation:{
            							        duration: 500,
            							        easing: 'out'
            							      }
                                        };

                                       
                                        var dataAPIRI  = new google.visualization.DataTable();
                                		
                                        dataAPIRI.addColumn('string', 'Meses');
                               			
                                        dataAPIRI.addColumn('number', 'Intereses');
                                        dataAPIRI.addColumn( { type: 'string', role: 'annotation' });
                                        dataAPIRI.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );
                                        dataAPIRI.addRows([
                                                ['Hoy'	   ,      0 ,   '$0.00',createCustomHTMLAreaCntnt(   '$0.00')], 
                                                ['12 meses',   8494 ,  '$8,494',createCustomHTMLAreaCntnt(  '$8,494')],
                                                ['18 meses',  13127 , '$13,127',createCustomHTMLAreaCntnt( '$13,127')],
                                                ['24 meses',  18033 , '$18,033',createCustomHTMLAreaCntnt( '$18,033')],
                                                ['36 meses',  28742 , '$28,742',createCustomHTMLAreaCntnt( '$28,742')],
                                                ['48 meses',  40771 , '$40,771',createCustomHTMLAreaCntnt( '$40,771')]
                                                                 ]);
                                        
                                        google.visualization.events.addListener(chartAPIRI, 'ready',
                                                function() {
                                                  // button.disabled = false;
                                                });
                                        
                                        chartAPIRI.draw(dataAPIRI, optionsAPIRI);
                                    
                                        

       / *************************************************************************************************** /
                            			
                                        

                                        var optionsARenRI = {
                                          title: 'Rendimientos - Reinversión',
                                          hAxis: {titleTextStyle: {color: '#333'}},
                                          vAxis: {minValue: 0},
                                          titleTextStyle: {fontSize: 15},
                                          pointShape: 'diamond',
                                          pointSize: 10,
                                          tooltip: { isHtml: true },
                                          animation:{
            							        duration: 500,
            							        easing: 'out'
            							      }
                                        };

                                       
                                        var dataARenRI  = new google.visualization.DataTable();
                                		
                                        dataARenRI.addColumn('string', 'Meses');
                               			
                                        dataARenRI.addColumn('number', 'Intereses');
                                        dataARenRI.addColumn( { type: 'string', role: 'annotation' });
                                        dataARenRI.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );
                                        dataARenRI.addRows([
                                               
                                                ['12 meses',  15.0 , '15.0%',createCustomHTMLAreaCntnt( '15.0%')],
                                                ['24 meses',  33.8 , '33.8%',createCustomHTMLAreaCntnt( '33.8%')],
                                                ['36 meses',  55.2 , '55.2%',createCustomHTMLAreaCntnt( '55.2%')],
                                                ['48 meses',  79.3 , '79.3%',createCustomHTMLAreaCntnt( '79.3%')]
                                                
                                                                 ]);
                                        
                                       

                                        chartARenRI.draw(dataARenRI, optionsARenRI);
                                  
	        */
	      }

		
			
			
	function onCompleteAction(){
		drawChart();
		
	}
	
	function reiniciaGraficas(){
		var optionsA = {
                title: 'Pago de intereses',
                hAxis: {titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0},
                titleTextStyle: {fontSize: 15},
                pointShape: 'diamond',
                pointSize: 10,
                vAxis: {'minValue': 0 }
              };
		
		var dataA= google.visualization.arrayToDataTable
     			([
        			['Meses', 'Intereses'], 
        		    ['Hoy',  0], 
                    ['6 meses',  0 ], 
                    ['12 meses', 0 ], 
                    ['18 meses', 0 ] 
                 ]);
		
		 chartA.draw(dataA, optionsA);
		 
		 var optionsB = {
                 title: 'Comportamiento pagos',
                 isStacked : true,
                 titleTextStyle: {fontSize: 15},
                 'colors':['#7BC9DC','#003366'],
                 orientation: 'horizontal',
                 hAxis:	{slantedText: true, slantedTextAngle: 90},
                 hAxis: {minValue: 0 }
               };
		 
		 var dataB = google.visualization.arrayToDataTable 
			([ 
				['Meses', 'Capital', 'Intereses'], 
				['1',  0,      0], 
				['2',  0,      0], 
				['3',  0,      0], 
				['4',  0,      0], 
				['5',  0,      0], 
				['6',  0,      0], 
				['7',  0,      0], 
				['8',  0,      0], 
				['9',  0,      0], 
				['10', 0,      0], 
				['11', 0,      0], 
				['12', 0,     0], 
				['13', 0,     0], 
				['14', 0,     0], 
				['15', 0,     0], 
				['16', 0,     0], 
				['17', 0,     0], 
				['18', 0,     0] 
			]);
		 
		 
		 chartB.draw(dataB, optionsB);
		 
		 var options = {
		          title: 'Distribución del portafolio',
		          legend:{position:'bottom'},
		          titleTextStyle: {fontSize: 15},
		          'colors':['#558B2F','#FF9800','#FFC107','#AFB42B'],
		          'sliceVisibilityThreshold': (1/9720),
				  'is3D': true
		      };

		 
		 var data = google.visualization.arrayToDataTable([ 
		                                                   ['Riesgo', 'Proyectos'],
		                                                    ['A', 1] 
		                                                    ]); 
		                                                   
		 
		 
       var chart = new google.visualization.PieChart(document.getElementById('piechart'));
       chart.draw(data, options);
       
       var optionsAV = {
               title: 'Proyectos Activos',
               hAxis: {titleTextStyle: {color: '#333'}},
               vAxis: {minValue: 0},
               titleTextStyle: {fontSize: 15},
               pointShape: 'diamond',
               pointSize: 10,
               hAxis:		{	slantedText: true, slantedTextAngle: 90},
               hAxis: {minValue: 0 }
             };
		
		var dataAV= google.visualization.arrayToDataTable
    			([
       				['Meses', 'Proyectos Activos'],  
       				['1',  	0 ], 
       				['2', 	0 ], 
       				['3', 	0 ], 
       				['4',  	0 ], 
       				['5', 	0 ], 
       				['6', 	0 ],
       				['7',  	0 ], 
       				['8', 	0 ], 
       				['9', 	0 ],
       				['10', 	0 ],
       				['11', 	0 ], 
       				['12', 	0 ], 
       				['13', 	0 ],
       				['14', 	0 ], 
       				['15', 	0 ], 
       				['16', 	0 ],
       				['17', 	0 ], 
       				['18', 	0 ]
       				
                ]);
		
		 chartAV.draw(dataAV, optionsAV);
		
       return true;
       
	}

			
			
function createCustomHTMLPieCntnt( riesgo, numProyectos , totalProyectos ){
	var color = "";
	
	if(riesgo=="A")
	{
		 color = "#003366";
		 
	}else if(riesgo=="B")
	{
		 color = "#7BC9DC";
		 
	}else if(riesgo=="C"){
		
		 color = "#83BA52";
		 
	}else if(riesgo=="D"){
		
		 color = "#9DDF3D";
		 
	}else if(riesgo=="E"){
		
		 color = "#FDC93D";
		 
	}

	var str = "<div style='padding: 8px; background-color: "+color+"' >" +
			"	<div style='width: 99%; margin-left: auto; margin-right: auto; text-align: center;'>Riesgo "+riesgo+"</div>" +
				"<br />" +
				"<b>"+numProyectos+"</b> de un total de <b>"+totalProyectos+"</b>" +
			"</div>";
	return  str;
	
}

function createCustomHTMLAreaVig(numProyect,mes){
	
	var str = "<div style='padding: 8px;' > " +
					"<table>" +
					"	<tr>" +
					"		<td style='text-align: center;' colspan='2' ><b>Mes "+mes+"</b></td>" +
					"   </tr>" +
					"	<tr>" +
					"		<td style='text-align: center;'><b>"+numProyect+" proyectos activos</b> </td>" +
					"   </tr>" +
					"</table>" +
			   " </div>";
	
	return str;
	
}

function createCustomHTMLBarCntnt(mes, capital, interes, total){
	
	var str = "<div style='padding: 8px;' > " +
				"<table>" +
				"	<tr>" +
				"		<td style='text-align: center;' colspan='2' ><b>Pagos recibidos al mes "+mes+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Capital: </td><td style='text-align: right'><b>"+capital+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Interés: </td><td style='text-align: right'><b>"+interes+"</b></td>" +
				"   </tr>" +
				"	<tr>" +
				"		<td>Total: </td><td style='text-align: right'><b>"+total+"</b></td>" +
				"   </tr>" +
				"</table>" +
			  " </div>";
	
	return str;
	
}

function createCustomHTMLAreaCntnt(monto){
var res = "<div style='padding: 8px;'> ";
	
		res += "<b>"+monto+"</b>";
	
	res += "</div>";
	
	return res;

}

function cambiaMontoSimInv(){
	
	if(validaMontoMin('ammountInv','simulator')){
		return reiniciaGraficas();
	}else{
		return false;
	}
}
			