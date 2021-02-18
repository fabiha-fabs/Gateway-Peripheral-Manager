package rest.api.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.api.project.models.Gateway;
import rest.api.project.services.GatewayService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GatewayController {

	@Autowired
	GatewayService gatewayService;
	
	@RequestMapping(method=RequestMethod.GET, value="/allGateways")
	public List<Gateway> getAllGateways() {
		return gatewayService.getAllGatewaays();
	}

	@RequestMapping(method=RequestMethod.GET, value="/availableGateways")
	public List<Gateway> getAvailableGateways() {
		return gatewayService.getAvailableGatewaays();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/gateways/{serialNo}")
	public Gateway getGatewayInfo(@PathVariable String serialNo) {
		return gatewayService.getGatewayInfo(serialNo);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addGateway")
	public Gateway addGateway(@Valid @RequestBody Gateway gateway) /* throws Exception */ {
		//if(true) throw new RuntimeException("\"excetion handler check\"");
		return gatewayService.addGateway(gateway);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateGateway/{serialNo}" )
	public Gateway updateGateway(@Valid @RequestBody Gateway gateway, @PathVariable String serialNo) {
		return gatewayService.updateGateway(serialNo, gateway);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteGateway/{serialNo}" )
	public void updateGateway(@PathVariable String serialNo) {
		gatewayService.deleteGateway(serialNo);
	}
	
}
