// Carga API de Google Charts
google.load('visualization', '1.0', {'packages':['corechart']});

// Ejecuta función al cargar
google.setOnLoadCallback(drawCharts);

// Función que dibuja las tres gráficas
function drawCharts() {
	/************* 
	 * GRÁFICA 1 *
	 *************/
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([
	  ['A', 5],
	  ['B', 3],
	  ['C', 6],
	  ['D', 9],
	  ['E', 2],
	  ['F', 2],
	  ['G', 1],
	]);
	
	var options = {	'title':'',
					'width':310,
					'height':200,
					'chartArea':{'width':310}
				   };
	
	var chart = new google.visualization.PieChart(document.getElementById('graphPie'));
	chart.draw(data, options);
		
	/************* 
	 * GRÁFICA 2 *
	 *************/
	data = google.visualization.arrayToDataTable([
		['', 'A', 'B', 'C', 'D', 'E', 'F', 'G'], 
		['Tasa ponderada', 8.6, 9.8, 10.6, 12.7, 15.3, 17.2, 16]
	]);

	options = {
		title: '',
		hAxis: {title: ''},
		chartArea: {width: '220'},
		width: 310,
		'is3D': false
	};

	chart = new google.visualization.ColumnChart(document.getElementById('graphPie2'));
	chart.draw(data, options);	
	
	
	/************* 
	 * GRÁFICA 3 *
	 *************/
	data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([
		['Efectivo disponible', 1250.0],
		['Inversiones en proceso', 500.0],
		['Inversiones activas', 15490.0],
		['Intereses devengados', 123.4],
	]);

	options = {
		'title':'',
		'width':310,
		'height':200,
		'chartArea':{'width':310}
	};

	chart = new google.visualization.PieChart(document.getElementById('graphPie3'));
	chart.draw(data, options);	
}
