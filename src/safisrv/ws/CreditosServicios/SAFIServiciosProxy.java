package safisrv.ws.CreditosServicios;

public class SAFIServiciosProxy implements safisrv.ws.CreditosServicios.SAFIServicios {
  private String _endpoint = null;
  private safisrv.ws.CreditosServicios.SAFIServicios sAFIServicios = null;
  
  public SAFIServiciosProxy() {
    _initSAFIServiciosProxy();
  }
  
  public SAFIServiciosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSAFIServiciosProxy();
  }
  
  private void _initSAFIServiciosProxy() {
    try {
      sAFIServicios = (new safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator()).getSAFIServiciosSoap11();
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
  
  public safisrv.ws.CreditosServicios.SAFIServicios getSAFIServicios() {
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios;
  }
  
  public safisrv.ws.CreditosServicios.CreaCreditoResponse creaCredito(safisrv.ws.CreditosServicios.CreaCreditoRequest creaCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.creaCredito(creaCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.SegurosVidaResponse segurosVida(safisrv.ws.CreditosServicios.SegurosVidaRequest segurosVidaRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.segurosVida(segurosVidaRequest);
  }
  
  public safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse consultaDetallePagos(safisrv.ws.CreditosServicios.ConsultaDetallePagosRequest consultaDetallePagosRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaDetallePagos(consultaDetallePagosRequest);
  }
  
  public safisrv.ws.CreditosServicios.SolicitudCreditoResponse solicitudCredito(safisrv.ws.CreditosServicios.SolicitudCreditoRequest solicitudCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.solicitudCredito(solicitudCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse simuladorCuotaCredito(safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest simuladorCuotaCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.simuladorCuotaCredito(simuladorCuotaCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.CancelaCreditoResponse cancelaCredito(safisrv.ws.CreditosServicios.CancelaCreditoRequest cancelaCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.cancelaCredito(cancelaCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.ReestrucCreditoResponse reestrucCredito(safisrv.ws.CreditosServicios.ReestrucCreditoRequest reestrucCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.reestrucCredito(reestrucCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse consultaActividadCredito(safisrv.ws.CreditosServicios.ConsultaActividadCreditoRequest consultaActividadCreditoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.consultaActividadCredito(consultaActividadCreditoRequest);
  }
  
  public safisrv.ws.CreditosServicios.AltaProspectoResponse altaProspecto(safisrv.ws.CreditosServicios.AltaProspectoRequest altaProspectoRequest) throws java.rmi.RemoteException{
    if (sAFIServicios == null)
      _initSAFIServiciosProxy();
    return sAFIServicios.altaProspecto(altaProspectoRequest);
  }
  
  
}