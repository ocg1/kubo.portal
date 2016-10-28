package mx.com.kubo.managedbeans.navigation;

public interface NavigationBeanIMO 
{
	void init();
	void asignar_menu_item_selected(String menu_item_selected);
	void setPaginaActual(String paginaActual);
	void registrar_bitacora_acceso();
	void redirect(String url);
}
