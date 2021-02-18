package rest.api.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.project.dao.GatewayRepository;
import rest.api.project.models.Gateway;

@Service
public class GatewayService {
	
	@Autowired
	private GatewayRepository gatewayRepo;
	@Autowired
	PeripheralService peripheralService;

	public List<Gateway> getAllGatewaays() {
		List<Gateway> gatewayList = new ArrayList<>();
		gatewayRepo.findAll().forEach(gatewayList::add);
		return gatewayList;
	}
	
	public List<Gateway> getAvailableGatewaays() {
		List<Gateway> gatewayList = new ArrayList<>();
		gatewayRepo.findAll().forEach(gateway -> {
			if (peripheralService.isPeripheralConnectionValid(gateway.getSerialNo()))
				gatewayList.add(gateway);
			});
		return gatewayList;
	}
	
	public Gateway addGateway(Gateway gateway) {
		return gatewayRepo.save(gateway);
	}
	
	public Gateway getGatewayInfo(String serialNo) {
		return gatewayRepo.findById(serialNo).get();
	}
	
	public Gateway updateGateway(String serialNo, Gateway gateway) {
		return gatewayRepo.save(gateway);
	}
	
	public void deleteGateway(String serialNo) {
		gatewayRepo.deleteById(serialNo);
	}
	
}
