/**
 * PublicProyect.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public interface PublicProyect extends java.rmi.Remote {
    public mx.com.kubo.kubows.WsResponse notificar(mx.com.kubo.kubows.NotificadorConfigRequest request) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse getLista_publicaciones_pendientes() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.PhoneResponse[] getLista_telefonos(java.lang.String prospectus_id, java.lang.String company_id) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse getDepositos_no_identificados() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse getDatosModelo(java.lang.String prospectusId, java.lang.String bur_sol_num) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse verificationStatus(java.lang.String prospectus_id, java.lang.String company_id, java.lang.String proyect_id, java.lang.String status) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse setTransunionCalif(mx.com.kubo.kubows.TransUnionCalifRequest transunion) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse acceptedFile(mx.com.kubo.kubows.AcceptedFileRequest acceptedfile) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse notificaDepositos() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse enviaSMS(mx.com.kubo.kubows.SMSRequestService request) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse notificaSMSSinPublicar() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse setEstatus_tienda(java.lang.String flag) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse getEstatus_tienda() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse getEjecucion_cierre_del_dia() throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse setEjecucion_cierre_del_dia(java.lang.String flag) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse addTagInfusion(mx.com.kubo.kubows.TagInfusionRequest taginfusionreq) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse publishProyect(mx.com.kubo.kubows.EditorEstatusRequest request) throws java.rmi.RemoteException;
    public mx.com.kubo.kubows.WsResponse setProspectRisk(mx.com.kubo.kubows.ProspectRiskRequest request) throws java.rmi.RemoteException;
}
