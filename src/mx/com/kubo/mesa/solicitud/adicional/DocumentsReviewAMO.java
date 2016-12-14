package mx.com.kubo.mesa.solicitud.adicional;

import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.model.SimulatorBean;

public abstract class DocumentsReviewAMO extends DocumentsReviewDMO 
{
	protected Proyect validaPurpose( Proyect proyect){
		
		if( proyect.getPurpose_id() != null && proyect.getType_id() != null )
		{			
			return proyect;
			
		}else{
			
			if( proyect.getPurpose() != null){
				
				proyect.setType_id( proyect.getPurpose().getType_id()  );
				proyectService.update(proyect);
				
			}else{
				
				Proyect prTmp = proyectService.getMaxProyectWithPurpose( proyect.getProyectoPk().getProspectus_id(), proyect.getProyectoPk().getCompany_id() );
				
				if(prTmp != null){
				
					proyect.setPurpose_id( prTmp.getPurpose_id() );
					
					proyect.setType_id( prTmp.getType_id());
					
					proyectService.update(proyect);
					
				}else{
					
					SimulatorBean simulator = simulatorService.getMaxSimulationProspectWithPurpose ( proyect.getProyectoPk().getProspectus_id(), proyect.getProyectoPk().getCompany_id() );
					
					if( simulator != null ){
						
						proyect.setPurpose_id( simulator.getPurpose_id() );
						
						PurposePK ppk = new PurposePK();
						
						ppk.setCompany_id(1);
						ppk.setPurpose_id(simulator.getPurpose_id());
						
						Purpose purpose = purposeService.getPurposeById(ppk);
						
						proyect.setType_id( purpose.getType_id());
						
						proyectService.update(proyect);
						
					}else{
						
						proyect.setPurpose_id( 1 );
						
						proyect.setType_id( 1100);
						
						proyectService.update(proyect);
						
					}
					
				}
				
				
				
			}
			
		}
		
		proyect = proyectService.getProyectById(proyect.getProyectoPk());
		
		return proyect;
		
	}
}
