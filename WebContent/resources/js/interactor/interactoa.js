var Interactoa = function (config) {
	// Call Initialization on Interactoa Call
	this.__init__(config);
	console.log("init interactoa");
};
ga(function(tracker) {
	  var clientId = tracker.get('clientId');
	  idUserGoogle(clientId);
});
var clienteId;

function idUserGoogle   (clientId) {
	 clienteId = clientId;
}

var prospectoID = $("#prospectoID").val();


Interactoa.prototype = {
		
	// Initialization
	__init__: function (config) {
		// Argument Assignment  // Type Checks 																			// Default Values
		this.interactions 		= typeof(config.interactions) 				== "boolean" 	? config.interactions 		: true,
		this.interactionElement = typeof(config.interactionElement) 		== "string" 	? config.interactionElement :'interaction',
		this.interactionEvents 	= Array.isArray(config.interactionEvents) 	=== true 		? config.interactionEvents 	: ['click','keypress', 'mouseup', 'touchend'],
		this.endpoint 			= typeof(config.endpoint) 					== "string" 	? config.endpoint 			: '/Kubo/SrvInteractor',
		this.async 				= typeof(config.async) 						== "boolean" 	? config.async 				: true,
		this.records 			= [];
		this.loadTime 			= new Date();
		this.cliente      		=  clienteId;
		this.prospecto          = prospectoID;
		
		// Call Event Binding Method
		this.__bindEvents__();
		
		return this;
	},

	// Create Events to Track
	__bindEvents__: function () {
		console.log("init interactoa");
		var interactoa 	= this;
		// Set Interaction Capture
		if (interactoa.interactions === true) {
			for (var i = 0; i < interactoa.interactionEvents.length; i++) {
				var ev 		= interactoa.interactionEvents[i],
					targets = document.getElementsByClassName(interactoa.interactionElement);
				for (var j = 0; j < targets.length; j++) {
					targets[j].addEventListener(ev, function (e) {
						e.stopPropagation();
						interactoa.__addInteraction__(e, interactoa.interactionElement);
					});
				}
			}	
		}


		// Bind onbeforeunload Event
		/*
		window.onbeforeunload = function (e) {
			interactoa.__sendInteractions__();
		};
		*/
		
		return this;
	},

	// Add Interaction Object Triggered By Events to Records Array
	__addInteraction__: function (e, type) {
		var interactoa 	= this,

			// Interaction Object
			interaction 	= {
				type 			: type,
				event 			: e.type,
				//targetTag 		: e.path[0].tagName,
				tagName    		: e.target.name,
				//targetClasses 	: e.path[0].className,
				//content 		: e.path[0].innerText,
				Text			: String.fromCharCode(e.which),
				//TextNw			: e.targetTouches[1],
				//TouchchangedText: e.targetTouches.item(),
				//clientPosition  : {
				//	x 				: e.clientX,
				//	y 				: e.clientY
				//},
				//screenPosition 	: {
				//	x 				: e.screenX,
				//	y 				: e.screenY
				//},
				createdAt 		: new Date()
			};
		// Insert into Records Array
		
		interactoa.records.push(interaction);
		return this;
	},

	// Gather Additional Data and Send Interaction(s) to Server
	__sendInteractions__: function () {
		
		var interactoa 	= this,
			
			// Generate Session Data Object
			session 		= {
				prospecto     : prospectoID,
				cliente       : clienteId,
				loadTime 		: interactoa.loadTime,
				unloadTime 		: new Date(),
				language 		: window.navigator.language,
				platform 		: window.navigator.platform,
				port 			: window.location.port,
				location        : window.location,
				client 			: {
					name 			: window.navigator.appVersion,
					innerWidth 		: window.innerWidth,
					innerHeight 	: window.innerHeight,
					outerWidth 		: window.outerWidth,
					outerHeight 	: window.outerHeight
				},
				page 			: {
					location 		: window.location.pathname,
					href 			: window.location.href,
					origin 			: window.location.origin,
					title 			: document.title
				},
				interactions 	: interactoa.records
			},

			// Initialize Cross Header Request
			xhr  			= new XMLHttpRequest();

		// Post Session Data Serialized as JSON
		xhr.open('POST', interactoa.endpoint, interactoa.async);
		xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
		//alert("JSon"+JSON.stringify(session));
		console.log("hello"+JSON.stringify(session));
		
		xhr.send(JSON.stringify(session));
		return this;
	}
};