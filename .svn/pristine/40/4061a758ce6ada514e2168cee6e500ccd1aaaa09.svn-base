var widthWindows;
var heightWindows;
var fancyContent;

$(window).load(function(){
	var isPdfFile=$("#isPdfFile").val();
	fancyContent=parent.$('#fancybox-content');
	if(isPdfFile=='false'){
		widthWindows=parent.$("#windowsWidth");
		heightWindows=parent.$("#windowsHeight");			
		$("#contentImg").width(fancyContent.width()-50);
		$("#contentImg").height(fancyContent.height()-120);			
					
		parent.$.fancybox.center();
		fancyContent.css({"background-color":"#FFFFFF","border":"10px solid #333333"});	
		//Mostrar barra de progreso en lo que termina de cargar la imagen.
		$("#realImage").one('load', function() { 
			$(this).imgscale({ 
			    parent : '.parent-content',
			    scale: 'fit'
			  });
			$(this).fadeIn();          
        }).attr('src', $("#realImage").attr('src'))
        .each(function() {
          if(this.complete){	  
        	  $(this).trigger('load');
          }         
        });
		
		$("#setCropToImage").toggle(function(){       
			$(this).children().eq(0).css({"background":"none","width":"auto","height":"auto","margin":"18px 5px","color":"#0066FF"});
			$(this).children().eq(0).text("Confirmar recorte");
			showCompleteImg();
			$("#realImage").Jcrop({
				 setSelect: [ 150,150, 50, 100 ],
				 onSelect: showCoords,
		         onChange: showCoords
		      });
	        }, function () {
	        	$(this).next().click();
	    });
	}else{		
		fancyContent.css({"background-color":"#FFFFFF","border":"10px solid #333333"});
	}
	
});

function showCoords(c) {
	$("#x").val(parseInt(c.x));
	$("#y").val(parseInt(c.y));
	$("#x2").val(parseInt(c.x2));
	$("#y2").val(parseInt(c.y2));
}

function setValueCoords() {
	$("#x").blur();
	$("#y").blur();
	$("#x2").blur();
	$("#y2").blur();
}
function showCompleteImg(){
	var realImage=$("#realImage");
	realImage.removeAttr("width");
	realImage.removeAttr("height");
	realImage.width("");
	realImage.height("");
	//Div contenedor de la imagen a rotar/cortar
	var divImgRotate=$("#contentImg");
	divImgRotate.css({"overflow":"auto"});
	
}

//Funcion que ejecuta un confirmacion
function checkSelection(){
	var element=$("#cbo-list-type");
	
	if(element.val()<0){
		var texto=$("#cbo-list-type option:selected").text();
		if(texto.length>7){
			if(confirm("Ya existe una foto asignada como "+texto+", si procede, la anterior foto se eliminará.\n  ¿Desea continuar?")){
				displayMessageProcessing('msgprocessing',false);
				return true;
			}else
				return false;
		}else{
			displayMessageProcessing('msgprocessing',false);
			return true;
		}
	}else{
		displayMessageProcessing('msgprocessing',false);
		return true;
	}
}

function cancel_crop()
{			
	parent.$.fancybox.close();
}

function closeAndSave(isEditLogo)
{			
	if(parent.refreshPanelView != undefined)
	{
		parent.location.reload(true);
	}	
	
	if(typeof parent.refreshSections === 'undefined')
	{
		
	} else {
		
		if($("#cbo-list-type").val()<0 || isEditLogo)
		{
			parent.refreshLogos();
		}
		
		parent.refreshSections();
	}
	
	if(typeof parent.refreshExpedient === 'undefined')
	{
		
	} else {
		
		parent.refreshExpedient();
	}
	
	parent.$.fancybox.close();	
}
