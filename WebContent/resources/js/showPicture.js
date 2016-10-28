
var jQ = jQuery.noConflict();

jQ(window).load(function(){
	var isPdfFile=''+jQ("#isPdfFile").val();
	if(isPdfFile=='false'){
		jQ("#realImage").one('load', function() {			
			if(jQ(this).width()>jQ(window).width()-30 &&  jQ(this).height()>jQ(window).height()-30 ){				
				jQ("#contentImg").width(jQ(window).width()-30).height(jQ(window).height()-30);
				jQ(this).imgscale({ 
				    parent : '.parent-content',
				    scale: 'fit'
				  });
				jQ(this).fadeIn();
			}else{
				jQ(this).fadeIn();
			}          
        }).attr('src', jQ("#realImage").attr('src'))
        .each(function() {
          if(this.complete){	  
        	  jQ(this).trigger('load');
          }         
        });
	}
});