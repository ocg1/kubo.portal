package mx.com.kubo.change_control;

public enum ChangeControlEMO 
{
	EDICION_DE_NOMBRE                 ("gn_natural_person", "first_name"),
	EDICION_DE_SEGUNDO_NOMBRE         ("gn_natural_person", "middle_name"),
	EDICION_DE_APELLIDO_PATERNO       ("gn_natural_person", "father_last_name"),
	EDICION_DE_APELLIDO_MATERNO       ("gn_natural_person", "mother_last_name"),
	REGISTRO_EDICION_RFC              ("gn_natural_person", "mx_rfc"),
	REGISTRO_EDICION_CURP             ("gn_natural_person", "mx_curp"),
	REGISTRO_MI_PRESTAMO_OTHER_DEBTS  ("ln_proyect",        "other_debts"),
		
	CAMBIO_ESTATUS                        ("ln_proyect_loan", "status_id"),
	CAMBIO_ESTATUS_FECHA_POSPUESTA        ("ln_proyect_loan", "posposed_date"),
	CAMBIO_ESTATUS_FECHA_PRE_AUTORIZACION ("ln_proyect_loan", "preapproved_date"),
	
	CREDITO_ADICIONAL_CON_CONSULTA ("ln_proyect_loan", "previous_proyect_loan_id"),
	CREDITO_ADICIONAL_SIN_CONSULTA ("ln_proyect_loan", "previous_proyect_loan_id"),
	RENOVACION_CREDITO             ("ln_proyect_loan", "previous_proyect_loan_id"),
	
	EDICION_VIVIENDA ("gn_address", "vivienda_TOKEN");
	
	private String afected_table; 
	private String 	field;
	
	private ChangeControlEMO(String afected_table, String field)
	{		
		this.afected_table = afected_table;
		this.field         = field;
	}

	public final String getField()
	{
		return field;
	}
	
	public final String getAfected_table()
	{
		return afected_table;
	}
}
