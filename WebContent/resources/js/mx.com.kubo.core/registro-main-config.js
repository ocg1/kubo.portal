console.log("mx.com.kubo.core/registro-main-config.js");

var Kubo = window.Kubo || {};

Kubo.Registro                           = window.Kubo.Registro || {};
Kubo.Registro.Simulador                 = window.Kubo.Registro.Simulador || {};
Kubo.Registro.Domicilio                 = window.Kubo.Registro.Domicilio || {};
Kubo.Registro.DatosPersonales           = window.Kubo.Registro.DatosPersonales || {};
Kubo.Registro.DatosPersonales.Historial = window.Kubo.Registro.DatosPersonales.Historial || {};
Kubo.Registro.Telefono                  = window.Kubo.Registro.Telefono || {};
Kubo.Registro.Documentacion             = window.Kubo.Registro.Documentacion || {};


Kubo.Acreditado                               = window.Kubo.Acreditado || {};
Kubo.Acreditado.Registro                      = window.Kubo.Acreditado.Registro || {};
Kubo.Acreditado.Registro.MiPrestamo           = window.Kubo.Acreditado.Registro.MiPrestamo || {};
Kubo.Acreditado.Registro.Employment           = window.Kubo.Acreditado.Registro.Employment || {};
Kubo.Acreditado.Registro.Employment.Domicilio = window.Kubo.Acreditado.Registro.Employment.Domicilio || {};
Kubo.Acreditado.Registro.ActividadEconomica   = window.Kubo.Acreditado.Registro.ActividadEconomica || {};
Kubo.Acreditado.Registro.ActividadEconomica.Domicilio = window.Kubo.Acreditado.Registro.ActividadEconomica.Domicilio || {};

Kubo.Inversionista                                 = window.Kubo.Inversionista || {};
Kubo.Inversionista.Registro                        = window.Kubo.Inversionista.Registro || {};
Kubo.Inversionista.Registro.PersonaMoral           = window.Kubo.Inversionista.Registro.PersonaMoral || {};
Kubo.Inversionista.Registro.Beneficiario           = window.Kubo.Inversionista.Registro.Beneficiario || {};
Kubo.Inversionista.Registro.Beneficiario.Domicilio = window.Kubo.Inversionista.Registro.Beneficiario.Domicilio || {}; 

var percentageFlag = false;

var Simulador       = Kubo.Registro.Simulador;
var Domicilio       = Kubo.Registro.Domicilio;
var DatosPersonales = Kubo.Registro.DatosPersonales;
var Historial       = Kubo.Registro.DatosPersonales.Historial;
var Documentacion   = Kubo.Registro.Documentacion;
var Telefono        = Kubo.Registro.Telefono;

var MiPrestamo = Kubo.Acreditado.Registro.MiPrestamo;
var Employment = Kubo.Acreditado.Registro.Employment;
var ActividadEconomica = Kubo.Acreditado.Registro.ActividadEconomica;

var Inversionista = Kubo.Inversionista;
var PersonaMoral  = Kubo.Inversionista.Registro.PersonaMoral;
var Beneficiario  = Kubo.Inversionista.Registro.Beneficiario; 
var Vivienda      = Beneficiario.Vivienda || {};

MiPrestamo.PATH   = "mx.com.kubo.acreditado/registro/mi-prestamo";
Beneficiario.PATH = "mx.com.kubo.inversionista/registro/paso_3_beneficiario";



