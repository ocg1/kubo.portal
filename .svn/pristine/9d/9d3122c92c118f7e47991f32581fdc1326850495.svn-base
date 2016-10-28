var	BrowserDetect = {
		
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "Navegador-Desconocido";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "Version-Desconocida";
		this.OS = this.searchString(this.dataOS) || "SO-Desconocida";
	},
			
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
			
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]
};

	
	isBrowserCompatible();
	
	function isBrowserCompatible()
	{
		if(BrowserDetect.browser=="Explorer" && BrowserDetect.version<=8){
			window.location = "../jsf/downloadbrowser.xhtml?result=" + BrowserDetect.browser + "-" + BrowserDetect.version;
			
			
			return false;
		}else{

			return true;
		}
		
		
		
	}
	
	
	function setInfoBrawser(namecomponet, versioncomponent, oscomponent, browser_width, browser_height)
	{	
		
		console.log("setInfoBrawser():" + namecomponet +" "+versioncomponent +" "+oscomponent +" "+browser_width +" "+browser_height);
		
		var browser = BrowserDetect.browser;
		var version = BrowserDetect.version;
		var os      = BrowserDetect.OS;
		
		var horizontal_size = $(window).width();
		var vertical_size   = $(window).height();
		
		//browser = platform.description;
		
		$("#" + namecomponet).val(browser);
		$("#" + versioncomponent).val(version);
		$("#" + oscomponent).val(os);
		$("#" + browser_width).val(horizontal_size);
		$("#" + browser_height).val(vertical_size);
		
		console.log("browser_name    = " + browser);
		console.log("browser_version = " + version);
		console.log("browser_OS      = " + os);
		console.log("browser_width   = " + horizontal_size);
		console.log("browser_height  = " + vertical_size);			
	}

	function getInternetExplorerVersion()
	// Returns the version of Internet Explorer or a -1
	// (indicating the use of another browser).
	{
	  var rv = -1; // Return value assumes failure.
	  if (navigator.appName == 'Microsoft Internet Explorer')
	  {
	    var ua = navigator.userAgent;
	    var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	    if (re.exec(ua) != null)
	      rv = parseFloat( RegExp.$1 );
	  }
	  return rv;
	}
	function checkVersion()
	{
	  var msg = "You're not using Internet Explorer.";
	  var ver = getInternetExplorerVersion();

	  if ( ver > -1 )
	  {
	    if ( ver >= 9.0 ) {
	      msg = "You're using a recent copy of Internet Explorer."
	    	 	$(".NavegadorCompatible").show();
			    $(".internetExplorerOld").hide();
	    }else {
	    	$(".NavegadorCompatible").hide();
			$(".internetExplorerOld").show();
			 msg = "You should upgrade your copy of Internet Explorer.";
	    }
	      
	  } else {
		 
			$(".NavegadorCompatible").show();
			$(".internetExplorerOld").hide();
	  }
	
	}
	$(document).ready(function(){
		checkVersion();
		 
	   loadEvent();
	   
		setInfoBrawser('browser_name','browser_version','browser_OS', 'browser_width', 'browser_height'); 
	});