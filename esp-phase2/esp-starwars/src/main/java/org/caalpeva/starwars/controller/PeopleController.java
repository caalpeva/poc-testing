package org.caalpeva.starwars.controller;

import org.caalpeva.starwars.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase controlador encargada de la navegación de las pantallas de personajes
 * @author Alberto
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private DatabaseService databaseService;
	

	@GetMapping("/index")
	public String goIndex(Model model) {
		model.addAttribute("peopleFilmsMap", databaseService.getPeopleWithFilms());
		return "people/peopleList";
	}
}