$(document).ready(function(){	
	$("#id_2013").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('contLeyTrans.html #repFin2013');
	});
	
	$("#id_2014").click(function(){
		elementChild=$(this).parent();
		elementChild.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		$("#contentAVCon").load('contLeyTrans.html #repFin2014');
	});
	
	 $("#id_2014").click();
	
});