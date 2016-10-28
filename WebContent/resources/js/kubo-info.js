
		function goToHome(){
			window.location = "../Portal/index.xhtml";
		}
		function goToReg(){
			window.location = "preregistro.xhtml?sm_first_name=";
		}
		function goToSim(){
			window.location = "simulator2.xhtml";
		}
		function goToVid(){
			$.fancybox({
				'width' : 500,
				'height' : 300,
				'padding' : '0',
				'margin' : '0',
				'autoDimensions' : true,
				'speedIn' : '20',
				'speedOut' : '10',
				'type' : 'iframe',
				'centerOnScroll' : true,
				'href': 'https://www.youtube.com/embed/lD5ZP2bq1KI?wmode=transparent&rel=0&autoplay=1',
				'overlayColor': '#333333',
				'hideOnOverlayClick':true,
				'enableEscapeButton':true,
				'showCloseButton':true
			});
			return true;
		}
		function goToAvLe(){
			window.location = "avisolegal.xhtml";
		}
		function goToFB(){
			window.location = "https://www.facebook.com/Kubofinanciero";
		}
		function goToTW(){
			window.location = "https://twitter.com/kubofinanciero";
		}
		function goLT(){
			window.location = "leyTransparencia.xhtml";
		}
		function goToUE(){
			$('.unidadModal').click();
		}
		function goToCat(){
			$("#inline").click();
		}
		function goToQuest(){
			window.location = "https://kubofinanciero.com/libro";
		} 
		function goToBlog(){
			window.location = "https://kubofinanciero.com/blog";
		} 
		function goToCredit(){
			window.location = "creditos_personales.xhtml";
		} 
		function goToAccess(){
			window.location = "access.xhtml";
		} 