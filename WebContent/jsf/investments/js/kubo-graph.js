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
  ['A, 5', 5],
  ['B, 3', 3],
  ['C, 6', 6],
  ['D, 9', 9],
  ['E, 2', 2],
  ['F, 2', 2],
  ['G, 1', 1]
]);

// Set chart options
var options = {'title':'Portafolio total',
			   'width':260,
			   'height':200};

// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('graph'));
chart.draw(data, options);
}

