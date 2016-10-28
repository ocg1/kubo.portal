package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.InvestmentsAtrasadasEdoCta;
import mx.com.kubo.repositories.InvestmentsAtraEdoCtaDao;
import mx.com.kubo.services.InvestmentsAtraEdoCtaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentsAtraEdoCtaServiceImp implements InvestmentsAtraEdoCtaService, Serializable{
		private static final long serialVersionUID = 1L;
		
		@Autowired
		protected InvestmentsAtraEdoCtaDao investmentsAtraEdoCtaDao;
		
		public InvestmentsAtrasadasEdoCta getInvestmentsAtraEdoCta(String cuentaAhoID){
			return investmentsAtraEdoCtaDao.getInvestmentsAtraEdoCta(cuentaAhoID);
		}
}
