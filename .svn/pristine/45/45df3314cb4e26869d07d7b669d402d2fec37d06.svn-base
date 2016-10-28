package mx.com.kubo.services;

import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ViewClientInfo;
import safisrv.ws.ClienteServicios.AltaClienteResponse;
import safisrv.ws.ClienteServicios.AltaConocimientoCteResponse;
import safisrv.ws.CreditosServicios.CreaCreditoRequest;
import safisrv.ws.CreditosServicios.CreaCreditoResponse;
import safisrv.ws.CreditosServicios.SegurosVidaRequest;
import safisrv.ws.CreditosServicios.SegurosVidaResponse;
import safisrv.ws.CuentasServicios.AltaConocimientoCtaResponse;
import safisrv.ws.CuentasServicios.AltaCuentaRequest;
import safisrv.ws.CuentasServicios.AltaCuentaResponse;

public interface ServiciosSAFIService {
	
	public abstract AltaClienteResponse createClientSAFI(ViewClientInfo view);
	public abstract AltaCuentaResponse  createCuentaSAFI(AltaCuentaRequest alta,int prospectus_id, int company_id);
	public abstract CreaCreditoResponse creaCreditoSAFI(CreaCreditoRequest creaCreditoRequest,int prospectus_id, int company_id);
	public abstract AltaConocimientoCteResponse createPLDSAFI(PrevencionLD pld,String safi_client_id,int prospectus_id, int company_id);
	public abstract AltaConocimientoCtaResponse createPLDCuentaSAFI(PrevencionLD pld,String account,int prospectus_id, int company_id);
	public abstract SegurosVidaResponse creaSeguroVidaSAFI(SegurosVidaRequest segurosVidaRequest,int prospectus_id, int company_id);
}
