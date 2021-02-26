package rest.api.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rest.api.project.dao.GatewayRepository;
import rest.api.project.dao.PeripheralReporsitory;
import rest.api.project.models.Gateway;
import rest.api.project.models.Peripheral;

@Service
public class PeripheralService {

	@Autowired
	private GatewayRepository gatewayRepo;
	
	@Autowired
	PeripheralReporsitory peripheralRepo;
	
	@Value("${gateway.peripheral.connection.limit}")
	private String peripheralConnectionLimit;
	
	public Peripheral addPeripheralToGateway(Peripheral peripheral, String serialNo) {
		if (isPeripheralConnectionValid(serialNo)) {
			peripheral.setGateway(new Gateway(serialNo, "", ""));
			return peripheralRepo.save(peripheral);
		}
		return null;
	}
	
	public Peripheral addPeripheral(Peripheral peripheral) {
		return peripheralRepo.save(peripheral);
	}
	
	public List<Peripheral> getByGateway(String serialNo) {
		List<Peripheral> peripheralsUnderGateway = new ArrayList<>();
		peripheralRepo.findByGatewaySerialNo(serialNo).forEach(peripheralsUnderGateway::add);
		return peripheralsUnderGateway;
	}
	
	public List<Peripheral> getAllPeripherals() {
		List<Peripheral> peripherals = new ArrayList<>();
		peripheralRepo.findAll().forEach(peripherals::add);
		return peripherals;
	}
	
	public Peripheral disconnectPeripheralFromGateway(String uid) {
		Peripheral peripheral = peripheralRepo.findById(uid).get();
		peripheral.setGateway(null);
		return peripheralRepo.save(peripheral);
	}
	
	public Peripheral connectPeripheralToGateway(String uid, String serialNo) {
		if (isPeripheralConnectionValid(serialNo)) {
			Peripheral peripheral = peripheralRepo.findById(uid).get();
			Gateway gateway = gatewayRepo.findById(serialNo).get();
			peripheral.setGateway(gateway);
			return peripheralRepo.save(peripheral);
		}
		return null;
	}
	
	public Peripheral updateStatus(String uid,String status) {
		Peripheral peripheral = peripheralRepo.findById(uid).get();
		peripheral.setStatus(status);
		return peripheralRepo.save(peripheral);
	}
	
	public boolean isPeripheralConnectionValid(String serialNo) {
		return peripheralRepo.findByGatewaySerialNo(serialNo).size() < Integer.parseInt(peripheralConnectionLimit);
	}
}
