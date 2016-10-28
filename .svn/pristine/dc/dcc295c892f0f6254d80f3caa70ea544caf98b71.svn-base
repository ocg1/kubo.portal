
$(document).ready(function() {	
	
	$("form").keypress(function(e) { if (e.which == 13) {return false;}}); 
	
	$("h2.expand_heading").click(function(event){
		var children=$(this).children();
		if(children.hasClass('change')){$(this).next(".toggle_container").slideUp("slow");children.removeClass("change");}
		else{$(this).next(".toggle_container").slideDown("slow");children.addClass("change");}
		event.preventDefault();
	});
	
	
	$(".clMenuExp").click(function(){
		$(".clMenuExpSel").removeClass("clMenuExpSel");
		$(this).addClass("clMenuExpSel");
	});
	
	
});

function initMenu(){
	
	$(".menuIzqC li a").click(function(){
		$(".menuIzqC li a").removeClass("active");
		$(this).addClass("active");
		
		$.scrollTo('.clContMenu', 800, { axis:'y' });
		
		$('.clContMenu').scrollTo(('#'+($(this).attr("id"))+""), 800, { axis:'y' });
		
		
		var h = $(window).height();
		
		h = h - 20;
		
		$(".clContMenu").css( { "height": h+"px" , "width" : "275px"} );
		 
	});
	
	$(window).scroll(function(){
		var menu = $('.clContMenu');
		var footerpos = $('#footer').height();
		var scrollBottom =  $(window).height() + $(window).scrollTop();

		if(parseInt($(this).scrollTop())>(parseInt($('#header').height()+110))){
		
			menu.removeClass('menuFrm');
			menu.removeClass('menuFrmBottom');
			menu.addClass('menuFrmFixed');
		}
		if(parseInt($(this).scrollTop())<=(parseInt($('#header').height()+110))){
		
			menu.removeClass('menuFrmFixed');
			menu.removeClass('menuFrmBottom');
			menu.addClass('menuFrm');
		}
	if(parseInt(scrollBottom)>=(parseInt($(document).height()-footerpos)+10)){
		
			menu.removeClass('menuFrm');
			menu.removeClass('menuFrmFixed');
			menu.addClass('menuFrmBottom');
		}
	});
	
	setMenuProperties();
	
}

function setMenuProperties(){
	var h = $(window).height();
	
	h = h - 25;
	
	$(".clContMenu").css( { "height": h+"px" , "width" : "275px"} );
}

function burAction(){
	
	//$("#valGraphic").val($("#burSolNumExp").val());	
	//alert($("#valGraphic").val());
	//$("#valGraphic").blur();
	drawChartBuro();
	closeFancy();
	flagGraph=false;
	
}
