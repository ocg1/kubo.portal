console.log("simulador.js");

Simulador.init_focus_date = function()
{	
	var remote_command = "div#datos-remote-command";	
	
	var focus_date_ID = "a#focus-ammount"
		
	ayuda('.ayuda1');
	
	console.log("Simulador.init_focus_date(): " + focus_date_ID);	
	
	$(remote_command).find(focus_date_ID).trigger("click");
};

Simulador.info_oncomplete = function(xhr, status, args)
{
	var purpose_id   = args.purpose_id;
	var ammount      = args.ammount;
	var term_id      = args.term_id;
	var frequency_id = args.frequency_id;
	var change_control_OK = args.change_control_OK;
	
	console.log("Simulador.info_oncomplete(): ");
	
	console.log("> change_control_OK = " + change_control_OK);
	
	if(purpose_id != undefined)
	{
		console.log("> purpose_id = " + purpose_id);	
	}
	
	if(ammount != undefined)
	{
		console.log("> ammount = " + ammount);	
	}
	
	if(term_id != undefined)
	{
		console.log("> term_id = " + term_id);
	}
	
	if(frequency_id != undefined)
	{
		console.log("> frequency_id = " + frequency_id);
	}
};
