package org.caalpeva.starwars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Clase controlador encargada principalmente de la navegación a la página por defecto.
 * @author Alberto
 */
@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome(Model model) {
		return "home";
	}
	
	@GetMapping("/database")
	public String goImport(Model model) {
		return "database/status";
	}
}