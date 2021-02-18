package rest.api.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.api.project.models.Peripheral;
import rest.api.project.services.PeripheralService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PeripheralController {

	@Autowired
	PeripheralService peripheralService;
	
	@RequestMapping(method=RequestMethod.GET, value="/gateways/{serialNo}/peripherals")
	public List<Peripheral> getPeripheralsUnderGateway(@PathVariable String serialNo) {
		return peripheralService.getByGateway(serialNo);
	}

	@RequestMapping(method=RequestMethod.GET, value="/allPeripherals")
	public List<Peripheral> getAllPeripherals() {
		return peripheralService.getAllPeripherals();
	}

	@RequestMapping(method=RequestMethod.POST, value="/gateway/{serialNo}/addPeripheral")
	public Peripheral addPeripheralToGateway(@PathVariable String serialNo, @RequestBody Peripheral peripheral) {
		return peripheralService.addPeripheralToGateway(peripheral, serialNo);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addDiscritPeripheral")
	public Peripheral addPeripheral(@RequestBody Peripheral peripheral) {
		return peripheralService.addPeripheral(peripheral);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{uid}/disconnectPeripheral")
	public Peripheral disconnectPeripheralFromGateway(@PathVariable String uid) {
		return peripheralService.disconnectPeripheralFromGateway(uid);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{uid}/connectPeripheral/{serialNo}/")
	public Peripheral connectPeripheralFromGateway(@PathVariable String uid, @PathVariable String serialNo) {
		return peripheralService.connectPeripheralToGateway(uid, serialNo);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{uid}/peripheralStatus/{status}/")
	public Peripheral updatePeripheralStatus(@PathVariable String uid, @PathVariable String status) {
		return peripheralService.updateStatus(uid, status);
	}
	
}
