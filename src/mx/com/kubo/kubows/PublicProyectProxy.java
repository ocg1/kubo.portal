package mx.com.kubo.kubows;

public class PublicProyectProxy implements mx.com.kubo.kubows.PublicProyect {
  private String _endpoint = null;
  private mx.com.kubo.kubows.PublicProyect publicProyect = null;
  
  public PublicProyectProxy() {
    _initPublicProyectProxy();
  }
  
  public PublicProyectProxy(String endpoint) {
    _endpoint = endpoint;
    _initPublicProyectProxy();
  }
  
  private void _initPublicProyectProxy() {
    try {
      publicProyect = (new mx.com.kubo.kubows.PublicProyectServiceLocator()).getPublicProyect();
      if (publicProyect != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)publicProyect)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)publicProyect)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (publicProyect != null)
      ((javax.xml.rpc.Stub)publicProyect)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.com.kubo.kubows.PublicProyect getPublicProyect() {
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect;
  }
  
  public mx.com.kubo.kubows.WsResponse publishProyect(mx.com.kubo.kubows.EditorEstatusRequest request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.publishProyect(request);
  }
  
  public mx.com.kubo.kubows.WsResponse getLista_publicaciones_pendientes() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getLista_publicaciones_pendientes();
  }
  
  public mx.com.kubo.kubows.WsResponse getLista_publicaciones_pendientes_inversion() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getLista_publicaciones_pendientes_inversion();
  }
  
  public mx.com.kubo.kubows.PhoneResponse[] getLista_telefonos(java.lang.String prospectus_id, java.lang.String company_id) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getLista_telefonos(prospectus_id, company_id);
  }
  
  public mx.com.kubo.kubows.WsResponse getDepositos_no_identificados() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getDepositos_no_identificados();
  }
  
  public mx.com.kubo.kubows.FileResponse[] getDocumentsByProspect(java.lang.String prospectus_id, java.lang.String proyect_loan, java.lang.String safi_credit_id) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getDocumentsByProspect(prospectus_id, proyect_loan, safi_credit_id);
  }
  
  public mx.com.kubo.kubows.WsResponse setProspectRisk(mx.com.kubo.kubows.ProspectRiskRequest request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.setProspectRisk(request);
  }
  
  public mx.com.kubo.kubows.WsResponse notificar(mx.com.kubo.kubows.NotificadorConfigRequest request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.notificar(request);
  }
  
  public mx.com.kubo.kubows.ReferencesResponse[] getReferencesByProspect(java.lang.String prospectus_id, java.lang.String company_id) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getReferencesByProspect(prospectus_id, company_id);
  }
  
  public mx.com.kubo.kubows.WsResponse getDatosModelo(java.lang.String prospectusId, java.lang.String bur_sol_num) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getDatosModelo(prospectusId, bur_sol_num);
  }
  
  public mx.com.kubo.kubows.WsResponse changeInvStatus(mx.com.kubo.kubows.ChangeInvStatusRequest inv_request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.changeInvStatus(inv_request);
  }
  
  public mx.com.kubo.kubows.WsResponse verificationStatus(java.lang.String prospectus_id, java.lang.String company_id, java.lang.String proyect_id, java.lang.String status) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.verificationStatus(prospectus_id, company_id, proyect_id, status);
  }
  
  public mx.com.kubo.kubows.WsResponse setTransunionCalif(mx.com.kubo.kubows.TransUnionCalifRequest transunion) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.setTransunionCalif(transunion);
  }
  
  public mx.com.kubo.kubows.WsResponse acceptedFile(mx.com.kubo.kubows.AcceptedFileRequest acceptedfile) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.acceptedFile(acceptedfile);
  }
  
  public mx.com.kubo.kubows.WsResponse notificaRetiros() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.notificaRetiros();
  }
  
  public mx.com.kubo.kubows.WsResponse notificaDepositos() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.notificaDepositos();
  }
  
  public mx.com.kubo.kubows.WsResponse enviaSMS(mx.com.kubo.kubows.SMSRequestService request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.enviaSMS(request);
  }
  
  public mx.com.kubo.kubows.WsResponse notificaSMSSinPublicar() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.notificaSMSSinPublicar();
  }
  
  public mx.com.kubo.kubows.WsResponse setEstatus_tienda(java.lang.String flag) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.setEstatus_tienda(flag);
  }
  
  public mx.com.kubo.kubows.WsResponse getEstatus_tienda() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getEstatus_tienda();
  }
  
  public mx.com.kubo.kubows.WsResponse getEjecucion_cierre_del_dia() throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.getEjecucion_cierre_del_dia();
  }
  
  public mx.com.kubo.kubows.WsResponse setEjecucion_cierre_del_dia(java.lang.String flag) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.setEjecucion_cierre_del_dia(flag);
  }
  
  public mx.com.kubo.kubows.WsResponse addTagInfusion(mx.com.kubo.kubows.TagInfusionRequest taginfusionreq) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.addTagInfusion(taginfusionreq);
  }
  
  public mx.com.kubo.kubows.WsResponse clientNotification(mx.com.kubo.kubows.ClientNotificationRequest request) throws java.rmi.RemoteException{
    if (publicProyect == null)
      _initPublicProyectProxy();
    return publicProyect.clientNotification(request);
  }
  
  
}