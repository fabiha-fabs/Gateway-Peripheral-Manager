package rest.api.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping(method=RequestMethod.GET, value="/greetings")
	public String getGreettings() {
		
		return "life is Ays.. without you, we're nothing dear";
	}
	
}
