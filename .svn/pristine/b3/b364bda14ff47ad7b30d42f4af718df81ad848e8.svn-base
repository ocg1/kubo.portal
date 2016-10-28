package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.model.ViewEconomicInfo;
import mx.com.kubo.repositories.ViewClientInfoDao;
import mx.com.kubo.services.ViewClientInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewClientInfoServiceImp implements ViewClientInfoService {

	@Autowired
	private ViewClientInfoDao viewclientinfoRepository;
	
	public ViewClientInfo getClientInfo(Integer prospectus_id,Integer company_id){
		return viewclientinfoRepository.getClientInfo( prospectus_id, company_id);
	}

	/*
	@Override
	public List<ClientView> getListClientByName(String query) {
		return viewclientinfoRepository.getListClientByName(query);
	}
	*/
	
	@Override
	public List<ViewEconomicInfo> getListEconomicByDescription(String query) {
		return viewclientinfoRepository.getListEconomicByDescription(query);
	}

	@Override
	public ViewEconomicInfo getEconomicById(String bmxId) {
		return viewclientinfoRepository.getEconomicById(bmxId);
	}

	@Override
	public List<ClientView> getListClientViewAllByName(String name) {
		return viewclientinfoRepository.getListClientViewAllByName(name);
	}
	
	/*
	@Override
	public List<ClientView> getListClientByEmail(String strEmail) {
		return viewclientinfoRepository.getListClientByEmail(strEmail);
	}
	*/
}
