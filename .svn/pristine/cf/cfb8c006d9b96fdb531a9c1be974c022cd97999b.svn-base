package safisrv.ws.ClienteServicios;

public class SAFIServiciosProxy implements safisrv.ws.ClienteServicios.SAFIServicios {
  private String _endpoint = null;
  private safisrv.ws.ClienteServicios.SAFIServicios sAFIServicios = null;
  
  public SAFIServiciosProxy() {
    _initSAFIServiciosProxy();
  }
  
  public SAFIServiciosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSAFIServiciosProxy();
  }
  
  private void _initSAFIServiciosProxy() {
    try {
      sAFIServicios = (new safisrv.ws.ClienteServicios.SAFIServiciosServiceLocator()).getSAFIServiciosSoap11();
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
  
  public safisrv.ws.ClienteServicios.SAFIServicios getSAFIServicios() {
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios;
  }
  
  public safisrv.ws.ClienteServicios.AltaClienteMenorResponse altaClienteMenor(safisrv.ws.ClienteServicios.AltaClienteMenorRequest altaClienteMenorRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaClienteMenor(altaClienteMenorRequest);
  }
  
  public safisrv.ws.ClienteServicios.AltaClienteResponse altaCliente(safisrv.ws.ClienteServicios.AltaClienteRequest altaClienteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaCliente(altaClienteRequest);
  }
  
  public safisrv.ws.ClienteServicios.ConsultaDirClienteResponse consultaDirCliente(safisrv.ws.ClienteServicios.ConsultaDirClienteRequest consultaDirClienteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaDirCliente(consultaDirClienteRequest);
  }
  
  public safisrv.ws.ClienteServicios.ConsultaClienteResponse consultaCliente(safisrv.ws.ClienteServicios.ConsultaClienteRequest consultaClienteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaCliente(consultaClienteRequest);
  }
  
  public safisrv.ws.ClienteServicios.AltaConocimientoCteResponse altaConocimientoCte(safisrv.ws.ClienteServicios.AltaConocimientoCteRequest altaConocimientoCteRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaConocimientoCte(altaConocimientoCteRequest);
  }
  
  
}