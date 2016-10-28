package safisrv.ws.InvKuboServicios;

public class SAFIServiciosProxy implements safisrv.ws.InvKuboServicios.SAFIServicios {
  private String _endpoint = null;
  private safisrv.ws.InvKuboServicios.SAFIServicios sAFIServicios = null;
  
  public SAFIServiciosProxy() {
    _initSAFIServiciosProxy();
  }
  
  public SAFIServiciosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSAFIServiciosProxy();
  }
  
  private void _initSAFIServiciosProxy() {
    try {
      sAFIServicios = (new safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator()).getSAFIServiciosSoap11();
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
  
  public safisrv.ws.InvKuboServicios.SAFIServicios getSAFIServicios() {
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios;
  }
  
  public safisrv.ws.InvKuboServicios.ConsultaInverResponse consultaInver(safisrv.ws.InvKuboServicios.ConsultaInverRequest consultaInverRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaInver(consultaInverRequest);
  }
  
  public safisrv.ws.InvKuboServicios.ConsultaDetalleInverResponse consultaDetalleInver(safisrv.ws.InvKuboServicios.ConsultaDetalleInverRequest consultaDetalleInverRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaDetalleInver(consultaDetalleInverRequest);
  }
  
  public safisrv.ws.InvKuboServicios.CancelarInversionResponse cancelarInversion(safisrv.ws.InvKuboServicios.CancelarInversionRequest cancelarInversionRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.cancelarInversion(cancelarInversionRequest);
  }
  
  public safisrv.ws.InvKuboServicios.SolicitudInversionResponse solicitudInversion(safisrv.ws.InvKuboServicios.SolicitudInversionRequest solicitudInversionRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.solicitudInversion(solicitudInversionRequest);
  }
  
  
}