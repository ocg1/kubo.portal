	
          $(window).load(function() {
  // The slider being synced must be initialized first
  $('#carousel').flexslider({
    animation: "slide",
    controlNav: true,
	
    animationLoop: false,
    slideshow: false,
    itemWidth: 112,
    itemMargin: 0,
    asNavFor: '#slider'
  });
   
  $('#slider').flexslider({
    animation: "slide",
	directionNav: true,
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    sync: "#carousel"
  });
});
