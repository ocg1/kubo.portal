$(document).ready(function(){
    var plot1 = $.jqplot('graphPie', [[['A',5],['B',3],['C',6],['D',9],['E',6],['F',1], ['G',1]]], {
        gridPadding: {top:0, bottom:38, left:0, right:0},
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer, 
            trendline:{ show:false }, 
            rendererOptions: { padding: 8, showDataLabels: true }
        },
        seriesColors: [ "#3366CC", "#990099", "#109618", "#FF9900", "#DC3912", "#66AA00",
        "#DD4477", "#0099C6"], 
        legend:{
            show:false, 
            //placement: 'outside', 
            rendererOptions: {
                numberRows: 2
            }, 
            location:'s',
            marginTop: '15px'
        },
        cursor: {
			//style: 'crosshair',
			show: true,
			showTooltip: true,
			tooltipLocation: 'se',
			followMouse: true
		},
		grid: { borderWidth:0, shadow:false, background: "#FFF"},
		textColor:"#FF0000",      
    });
    
    var plot2 = $.jqplot('graphPie2', [[['A',5],['B',3],['C',6],['D',9],['E',6],['F',1], ['G',1]]], {
        gridPadding: {top:0, bottom:38, left:0, right:0},
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer, 
            trendline:{ show:false }, 
            rendererOptions: { padding: 8, showDataLabels: true }
        },
        legend:{
            show:true, 
            //placement: 'outside', 
            rendererOptions: {
                numberRows: 1
            }, 
            location:'s',
            marginTop: '15px'
        }       
    });

    
});


































/*
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
var options = {'title':'',
			   'width':260,
			   'height':200};

// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('graphPortfolio'));
chart.draw(data, options);
}
*/
