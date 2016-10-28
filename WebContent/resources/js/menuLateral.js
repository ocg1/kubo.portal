
$(window).load(function() {
	colLeftFloating();
	abrirMenuLateral (); 
	
});


function abrirMenuLateral () {

 	 $('.menu_interior h6').click(function(){
		 if($('.menu_interior h6').hasClass("active")) {
			 $(".contentMenu > ul").slideUp();	
			 $('.menu_interior h6').removeClass("active");	
		} else {
			 $(".contentMenu > ul").slideDown();	
			 
				 $(this).addClass("active");	 
		}
	});
}

function colLeftFloating() {
	$('.FloatingMenu').removeAttr("style");
    var width = $(window).width();	
	if (width <= 1024 && width >= 801 )  { 
        var widthColLeft = $(".containerFloatingMenu").width();
        $('.FloatingMenu').css({
					width:  widthColLeft
		 });
	}else {
		    $('.FloatingMenu').removeAttr("style");
	}

}

$(window).resize(function() {

	var resizeId;
	   clearTimeout(resizeId);
	   resizeId = setTimeout(colLeftFloating, 500);

	$(window).scroll(function(){
		 var alturaHeaderTitulo = $(".push_header").height() + $(".titulo").height() + 50;
		
		 var h = $('.inner_content').height() + alturaHeaderTitulo;
		  
		  var y = $(this).scrollTop ();
		
		  var width = $(window).width();	
		  if (width >= 801) { 
			  if( y > alturaHeaderTitulo  && y < h  - $(".contentMenu").height()){
			  $(".FloatingMenu").addClass("stick");
			  } else {
			   $('.FloatingMenu').removeClass('stick');
			  }
		  }else {
			  $('.FloatingMenu').removeClass('stick');
			  
		  }
	});
});

