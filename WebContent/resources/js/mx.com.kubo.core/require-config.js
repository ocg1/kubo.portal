/*
require.config(
{
	baseUrl: '../resources/js',

    paths: 
    {
    	beneficiario:			   Beneficiario.PATH + '/beneficiario/verificacion',
    	callback_mismo_domicilio:  Beneficiario.PATH + '/beneficiario/callback_mismo_domicilio',
    	vivienda:                  Beneficiario.PATH + '/vivienda/vivienda',    	
    	callback_codigo_postal:    Beneficiario.PATH + '/vivienda/callback_codigo_postal',
    	asignacion_lista_colonias: Beneficiario.PATH + '/vivienda/asignacion_lista_colonias',
    	asignacion_otra_colonia:   Beneficiario.PATH + '/vivienda/asignacion_otra_colonia'
    }
});

require (
[
	'beneficiario',
	'callback_mismo_domicilio',
	'vivienda',
	'callback_codigo_postal',
	'asignacion_lista_colonias',
	'asignacion_otra_colonia'
	
], function (
		
	beneficiario,
	callback_mismo_domicilio,
	vivienda, 
	callback_codigo_postal,
	asignacion_lista_colonias,
	asignacion_otra_colonia
){		
	fieldCount();
	
	Beneficiario.init_validator_class();
	Beneficiario.verificPersentage();
	
	beneficiario.init();
	callback_mismo_domicilio.init();
	
	vivienda.init();		
	callback_codigo_postal.init();
	asignacion_lista_colonias.init();
	asignacion_otra_colonia.init();
});
*/