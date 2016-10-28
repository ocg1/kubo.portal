function Dia_de_Semana (d1,d2,d3,d4,d5,d6,d7) {
	this[0]=d1;
	this[1]=d2;
	this[2]=d3;
	this[3]=d4;
	this[4]=d5;
	this[5]=d6;
	this[6]=d7;
}
function Mes_del_Anio (d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12) {
	this[0]=d1;
	this[1]=d2;
	this[2]=d3;
	this[3]=d4;
	this[4]=d5;
	this[5]=d6;
	this[6]=d7;
	this[7]=d8;
	this[8]=d9;
	this[9]=d10;
	this[10]=d11;
	this[11]=d12;
}
Semana = new Dia_de_Semana ("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
Mes = new Mes_del_Anio ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
var today= new Date;
diahoy = today.getDay();
fechahoy = today.getDate();
meshoy = today.getMonth();
anio = today.getFullYear();

function dia () {
	document.write (Semana[diahoy]+' '+fechahoy);
	document.write (' de '+Mes[meshoy]+' de '+anio);
}