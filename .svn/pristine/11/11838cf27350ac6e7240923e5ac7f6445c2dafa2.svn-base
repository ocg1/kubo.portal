
		





	function pausa(){
		clearInterval(interval);
	}
	function inicia(){
		if($(window).width()>500){
			interval = setInterval('changeImg();',8500);
		}else{
			interval = setInterval('changeImg();',5000);
		}
	}
	
	$(window).load(function() {
		var proper ="";
		proper+="width: "+$(window).width()+ "   height: "+$(window).height();
		
		$("#thisProperties").html(proper); 
		//if($(window).width()>400){
			
		//}
		
		$("#contFrases").fadeIn(50);
		$("#sim").fadeIn(50);
		
		if(typeof fraseSig === "undefined" ){
		}else{
			if(fraseSig == 0){
				$("#shadowFrase2").css('width','416px');
				
			}else{
				$("#shadowFrase2").css('width','758px');
			}
		
			if(350 > $(window).width()){
				inicialzaModal();
			}
		}
		$(window).bind("resize", function() {
		    
			proper ="";
			proper+="width: "+$(window).width()+ "   height: "+$(window).height();
			
			$("#thisProperties").html(proper); 
			
			if(350 > $(window).width()){
				inicialzaModal();
			}
			
			
		    changeImgonResize();
		    //updateBackground();
		  });
		
		
		if($(window).width()>500){
			interval = setInterval('changeImg();',8500);
		}else{
			interval = setInterval('changeImg();',5000);
		}
		
		$("a.simula").fancybox({
			'width' : '90%',
			'height' : '88%',
			'autoScale' : 'false',
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'type' : 'iframe',
			'scrolling' : 'auto',
			'centerOnScroll' : 'true',
			'overlayColor': '#000000',
			'overlayOpacity': '0.15',
			'padding': '0',
			'margin': '0',
			'onStart':function() {
				executeInit();
			},
			'onClosed':function() {
				verficaReenvio();
			}
		});
		
		$(".various").fancybox({
			'transitionIn'	: 'none',
			'transitionOut'	: 'none',
			'padding': '0',
			'margin': '0',
			'autoDimensions' : true,
			'onStart':function() {
				pausa();
			},
			'onClosed':function() {
				inicia();
			}
		});
		
		changeImgonResize();
		//updateBackground();
		
	});

function updateBackground() {
			  screenWidth = $(window).width();
			  screenHeight = $(window).height();
			  var bg = $(".bgCl");

			  for(var f = 0; f<2; f++){
				  var elemnt = $(bg[f]);
				  var thisImg = elemnt.find("img");
					  	thisImg.height("auto");
				 		thisImg.width("100%");
					  	var imgH = elemnt.height();
					  	var imgW = elemnt.width();
					
						if(imgH<$(window).height()){
							var pH = ($(window).height()*100)/imgH;
							var newW = (pH * imgW)/100;
							thisImg.width(newW);
							thisImg.height($(window).height());
						}
						
						if (elemnt.width() > 0) {
							elemnt.css({'left':0,'top':0});
						  }  
			  	
			 
				  
			  
			  }
			  
			  
			  
			}

function loadImage(){
	if(!flagload){
		flagload = true;
		changeImgonResize();
	}
}

function changeImgonResize(){
	
	loadImgs();
	  
//	//proper = "width: "+$(window).width();
//	if (typeof proper === 'undefined') {
//	}else{
//	$("#thisProperties").html(proper);
//	  
	var temp="";
		if((typeof fraseSig === 'undefined' )){
			
		}else{
			temp = fraseSig;
		}
	  if(temp < 0){
		  temp = arr.length -1;
	  }
	  
	  if(temp%2==0){
		  $("#bg01").html(arr[temp]);
		  
		  temp = temp +1;
		  
		  if(temp == arr.length){
			  temp = 0;
			}
		  
		  $("#bg02").html(arr[temp]);
		  
	  }else{
		  $("#bg02").html(arr[temp]);
		  
		  temp = temp +1;
		  
		  if(temp == arr.length){
			  temp = 0;
			}
		  
		  $("#bg01").html(arr[temp]);
		  
	  }
	  
	  updateBackground();
//	}
}

function changeImg(){
	
	loadImgs();
	
	//proper = "width: "+$(window).width();
//	if (typeof proper === 'undefined') {
//	}else{
//		$("#thisProperties").html(proper);
//		
		if(imgSig%2 == 0){
			$("#bg01").fadeOut(600,function(){
				
				
				if(imgSig == arr.length){
					imgSig = 0;
				}
				
				$("#bg01").html(arr[imgSig]);
				
				imgSig = imgSig +1;
			});
			$("#bg02").fadeIn(600);
		}else{
			$("#bg02").fadeOut(600,function(){
				
				if(imgSig == arr.length){
					imgSig = 0;
				}
				
				$("#bg02").html(arr[imgSig]);
				
				imgSig = imgSig +1;
			});
			$("#bg01").fadeIn(600);
		}
		
		
		
		setTimeout('changeFrase()',1);
		
		
			
		updateBackground();
	//}
}
function changeFrase(){
	if( typeof fraseSig === "undefined" ){
	}else{
		fraseSig=fraseSig+1; 
		$("#frase02 h2").fadeOut(300,function(){
			if($(window).width()>800){
				if(fraseSig == 1|| fraseSig == 2|| fraseSig == 4 || fraseSig == 5){
					$("#shadowFrase2").css('width','416px');
				}else{
					$("#shadowFrase2").css('width','758px');
				}
			}else{
				if(fraseSig == 1|| fraseSig == 2|| fraseSig == 4 || fraseSig == 5){
					$("#shadowFrase2").css('width','286px');
				}else{
					$("#shadowFrase2").css('width','658px');
				}
			}
			
			$('#frase02 h2').html(phrases[fraseSig]);
			if(fraseSig == 5){
				fraseSig=(-1);
			}
			
			$("#frase02 h2").fadeIn(300);
			
		});
	}
}

function loadImgs(){
	if(parseInt($(window).width())>1100){
		arr = new Array();
//		$('li.clPC').each(function() { 
			  arr.push('<img src="../resources/img/fotoshome-06-PC.jpg"  class="imgbg01" onload="loadImage();" />');
			  arr.push('<img src="../resources/img/fotoshome-01-PC.jpg" />');
			  arr.push('<img src="../resources/img/fotoshome-02-PC.jpg" />'); 
			  arr.push('<img src="../resources/img/fotoshome-03-PC.jpg" />'); 
			  arr.push('<img src="../resources/img/fotoshome-04-PC.jpg" />'); 
			  arr.push('<img src="../resources/img/fotoshome-05-PC.jpg" />'); 
			   
//			}); 
	}
	else if(parseInt($(window).width())<1100 && parseInt($(window).width())>800){
	  arr = new Array();
//		$('li.clTHor').each(function() { 
	  		  arr.push('<img src="../resources/img/fotosipad-06-H.jpg" class="imgbg01" onload="loadImage();"  />');
			  arr.push('<img src="../resources/img/fotosipad-01-H.jpg"  />');
			  arr.push('<img src="../resources/img/fotosipad-02-H.jpg"  />'); 
			  arr.push('<img src="../resources/img/fotosipad-03-H.jpg"  />'); 
			  arr.push('<img src="../resources/img/fotosipad-04-H.jpg"  />'); 
			  arr.push('<img src="../resources/img/fotosipad-05-H.jpg"  />'); 
//			}); 

	  }else{
		  arr = new Array();
//		$('li.clTVer').each(function() { 
//			  arr.push(this.innerHTML); 
//			});
		  arr.push('<img src="../resources/img/fotosipad-06-V.jpg" class="imgbg01" onload="loadImage();" />');
		  arr.push('<img src="../resources/img/fotosipad-01-V.jpg"  />');
		  arr.push('<img src="../resources/img/fotosipad-02-V.jpg"  />'); 
		  arr.push('<img src="../resources/img/fotosipad-03-V.jpg"  />'); 
		  arr.push('<img src="../resources/img/fotosipad-04-V.jpg"  />'); 
		  arr.push('<img src="../resources/img/fotosipad-05-V.jpg"  />'); 
		   
		  
	  }
	
	
	
}

function inicialzaModal(){
	
	$("#menuBut").fancybox({
		'transitionIn'	: 'none',
		'transitionOut'	: 'none',
		'padding': '0',
		'margin': '0',
		'onStart':function() {
			pausa();
		},
		'onClosed':function() {
			inicia();
		}
	});
}