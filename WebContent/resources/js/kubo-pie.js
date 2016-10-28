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
  ['Efectivo disponible', 1250.0],
  ['Inversiones en proceso', 500.0],
  ['Inversiones activas', 15490.0],
  ['Intereses devengados', 123.4],
]);

// Set chart options
var options = {	'title':'',
				'width':310,
				'height':200,
				'chartArea':{'width':310}
			   };

// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('graphPie'));
//var chart = new google.visualization.BarChart(document.getElementById('graphPie'))
chart.draw(data, options);
}

