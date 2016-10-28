var Interface = function(name, methods)
{
	if(arguments.length != 2)
	{
		var msg = "Interface constructor called with " 
				+  arguments.length
				+ "arguments, but expected 2.";
		
		throw new Error(msg);
		
		this.name  = name;
		this.methods = [];
		
		for(var i = 0, len = methods.length; i < len; i++)
		{
			if(typeof metodos[i] !== 'string')
			{
				var msg = "Interface constructor expects method names to be passed in as string.";
				throw new Error(msg);
			}
			
			this.methods.push(methods[i]);
		}
	}
};

Interface.ensureImplements = function(object)
{
	if(arguments.length < 2) 
	{
		var msg = "Function Interface.ensureImplements called with "
			    + arguments.length 
			    + "arguments, but expected at least 2.";
		
		throw new Error(msg);
	}
	
	for(var i = 1, len = arguments.length; i < len; i++) 
	{
		var inter_face = arguments[i];
		
		if(inter_face.constructor !== Interface) 
		{
			throw new Error("Function Interface.ensureImplements expects arguments two and above to be instances of Interface.");
		}
		
		for(var j = 0, methodsLen = inter_face.methods.length; j < methodsLen; j++) 
		{
			var method = inter_face.methods[j];
			
			if(!object[method] || typeof object[method] !== 'function') 
			{
				var msg = "Function Interface.ensureImplements: object does not implement the " 
					    + inter_face.name + " interface. Method " + method + " was not found.";
				
				throw new Error(msg);							
			}
		}
	}
};