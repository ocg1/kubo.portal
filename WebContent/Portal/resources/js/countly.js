
	//config
	var Countly = Countly || {};
	Countly.q = Countly.q || [];

	//EFL app key
	Countly.app_key = "9fca087b391cebf6e965233f2b05ef889baf15d4";

	//EFL endpoint
	Countly.url = "https://eflglobal.count.ly"; 

	//push session tracking
	Countly.q.push(['track_sessions']);
	 
	//push pageview tracking
	Countly.q.push(['track_pageview']);
	 
	//load countly script asynchronously
	(function() {
	 var cly = document.createElement('script'); cly.type = 'text/javascript'; 
	 cly.async = true;
	 cly.src = 'https://cdn.jsdelivr.net/countly-sdk-web/latest/countly.min.js';
	 cly.onload = function(){Countly.init()};
	 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(cly, s);
	})();


