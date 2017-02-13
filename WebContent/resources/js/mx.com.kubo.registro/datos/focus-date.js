console.log("focus-date.js");

DatosPersonales.init_focus_date = function(focus_id)
{	
	var remote_command = "div#datos-remote-command";
	
	var focus_date_ID = this.init_focus_date_ID(focus_id);
	
	console.log("DatosPersonales.init_focus_date(): " + focus_date_ID);	
	
	$(remote_command).find(focus_date_ID).trigger("click");
};

DatosPersonales.init_focus_date_ID = function(focus_id)
{
	var focus_date;
	
	switch(focus_id)
	{
		case 1:
			focus_date = "a#focus-first-name";			
		break;
		
		case 2:			
			focus_date = "a#focus-birth-date";
		break;
		
		case 3:
			focus_date = "a#focus-citizenship";
		break;
		
		case 4:
			focus_date = "a#focus-birth-place";
		break;
	
		case 5:
			focus_date = "a#focus-gender-id";
		break;
		
		case 6:
			focus_date = "a#focus-birth-country";
		break;
		
		default: break;
	}
	
	return focus_date;
};