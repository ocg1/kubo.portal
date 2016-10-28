 // Load the Visualization API and the piechart package.
          google.load('visualization', '1.0', {'packages':['corechart']});

          // Set a callback to run when the Google Visualization API is loaded.
          google.setOnLoadCallback(drawChart);

          // Callback that creates and populates a data table,
          // instantiates the pie chart, passes in the data and
          // draws it.
          function drawChart() {

            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Topping');    
            data.addColumn('number', 'Slices');
            data.addRows([
              ['monto_a', 10],
              ['monto_b', 20],
              ['monto_c', 30],
              ['monto_d', 40],
           
            ]);
            // Create the data table.
            var data2 = new google.visualization.DataTable();
            data2.addColumn('string', 'Topping');
            data2.addColumn('number', 'Slices');
            data2.addRows([
              ['intereses_a', 20],
              ['intereses_b', 20],
              ['intereses_c', 60],
        
            ]);

            var data3 = new google.visualization.DataTable();
            data3.addColumn('string', 'Topping');
            data3.addColumn('number', 'Slices');
            data3.addRows([
              ['distribucion_a', 20],
              ['distribucion_b', 20],
              ['distribucion_c', 60],
         
            ]);

            // Set chart options
            var options = {title: '',
		
				 backgroundColor: { fill:'#f3f3f3' },
              colors: ['#508c43', '#8fc05a', '#979993', '#60615d', '#333434'],
              enableInteractivity:'false',
           	  fontSize:"0",
           	    	  backgroundColor:{stroke: 'none', fill:'#f3f3f3'},
              pieSliceTextStyle:{color: 'black'},
		      legend:'none',
		       tooltip : {
		  	   		trigger: 'none'
			    },
                'width':140,
                'height':100
		};
            // Set chart options
             var options2 = {title: '',
			
	
              colors: ['#508c43', '#8fc05a', '#979993', '#60615d', '#333434'],
              enableInteractivity:'false',
           	  fontSize:"0",
           	  backgroundColor:{stroke: 'none', fill:'#f3f3f3'},
              pieSliceTextStyle:{color: 'black'},
		      legend:'none',
		       tooltip : {
		  	   		trigger: 'none'
			    },
               'width':90,
                'height':90
		};
            // Set chart options
            var options3 = {title: '',
	
				
              colors: ['#508c43', '#8fc05a', '#979993', '#60615d', '#333434'],
              enableInteractivity:'false',
           	  fontSize:"0",
           	  	  backgroundColor:{stroke: 'none', fill:'#f3f3f3'},
              pieSliceTextStyle:{color: 'black'},
		      legend:'none',
		       tooltip : {
		  	   		trigger: 'none'
			    },
              'width':90,
                'height':90
		};
						   
						   
					

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
            var chart2 = new google.visualization.PieChart(document.getElementById('chart_div2'));
            chart2.draw(data2, options2);
            var chart3 = new google.visualization.PieChart(document.getElementById('chart_div3'));
            chart3.draw(data3, options3);

          }