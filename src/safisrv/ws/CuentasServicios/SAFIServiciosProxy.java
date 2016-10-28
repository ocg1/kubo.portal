package safisrv.ws.CuentasServicios;

public class SAFIServiciosProxy implements safisrv.ws.CuentasServicios.SAFIServicios {
  private String _endpoint = null;
  private safisrv.ws.CuentasServicios.SAFIServicios sAFIServicios = null;
  
  public SAFIServiciosProxy() {
    _initSAFIServiciosProxy();
  }
  
  public SAFIServiciosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSAFIServiciosProxy();
  }
  
  private void _initSAFIServiciosProxy() {
    try {
      sAFIServicios = (new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator()).getSAFIServiciosSoap11();
      if (sAFIServicios != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sAFIServicios)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sAFIServicios)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sAFIServicios != null)
      ((javax.xml.rpc.Stub)sAFIServicios)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public safisrv.ws.CuentasServicios.SAFIServicios getSAFIServicios() {
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios;
  }
  
  public safisrv.ws.CuentasServicios.AltaRelacionadoCtaResponse altaRelacionadoCta(safisrv.ws.CuentasServicios.AltaRelacionadoCtaRequest altaRelacionadoCtaRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaRelacionadoCta(altaRelacionadoCtaRequest);
  }
  
  public safisrv.ws.CuentasServicios.AltaConocimientoCtaResponse altaConocimientoCta(safisrv.ws.CuentasServicios.AltaConocimientoCtaRequest altaConocimientoCtaRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaConocimientoCta(altaConocimientoCtaRequest);
  }
  
  public safisrv.ws.CuentasServicios.AltaCuentaResponse altaCuenta(safisrv.ws.CuentasServicios.AltaCuentaRequest altaCuentaRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaCuenta(altaCuentaRequest);
  }
  
  public safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse consultaCuentasPorCliente(safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest consultaCuentasPorClienteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaCuentasPorCliente(consultaCuentasPorClienteRequest);
  }
  
  public safisrv.ws.CuentasServicios.ConsultaDisponiblePorClienteResponse consultaDisponiblePorCliente(safisrv.ws.CuentasServicios.ConsultaDisponiblePorClienteRequest consultaDisponiblePorClienteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaDisponiblePorCliente(consultaDisponiblePorClienteRequest);
  }
  
  public safisrv.ws.CuentasServicios.AutorizaCuentaResponse autorizaCuenta(safisrv.ws.CuentasServicios.AutorizaCuentaRequest autorizaCuentaRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.autorizaCuenta(autorizaCuentaRequest);
  }
  
  
}