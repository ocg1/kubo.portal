package mx.com.kubo.services.impl;

import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.repositories.ConsultingManualDao;
import mx.com.kubo.services.ConsultingManualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultingManualServiceImp implements ConsultingManualService {
	
	@Autowired
	private ConsultingManualDao consultinganualRepository;
	
	@Override
	public void saveConsultingManual(ConsultingManual consulting){
		consultinganualRepository.saveConsultingManual(consulting);
	}
	
}
