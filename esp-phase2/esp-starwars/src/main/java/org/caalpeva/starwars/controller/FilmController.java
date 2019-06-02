package org.caalpeva.starwars.controller;

import java.util.List;
import java.util.Random;

import org.caalpeva.starwars.repository.model.Film;
import org.caalpeva.starwars.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase controlador encargada de la gestión de la información de películas. 
 * @author Alberto
 */
@Controller
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private DatabaseService databaseService;
	
	@GetMapping("/index")
	public String goIndex(@ModelAttribute("filmFormInstance") FilmForm filmForm, Model model) {
		return "films/filmForm";
	}
	@PostMapping("/index")
	public String save(@ModelAttribute("filmFormInstance") FilmForm filmForm, Model model) {
		String pilot = "Pilot of Starship " + new Random().nextInt(87);
// TODO: Descomentar cuando se solucionen los inconvenientes de la consulta		
//		List<People> peoples = databaseService.getPilotOfStarshipThatMostHasAppeared(filmForm.films);
//		if (peoples != null && peoples.size() > 0) {
//			pilot = peoples.get(0).getName();
//		}

		model.addAttribute("result", pilot);
		return "films/filmForm";
	}

	@ModelAttribute("availableFilms")
	public List<Film> getMovieTypes() {
		return databaseService.findAllFilms();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				Integer id = null; 
				try {
		        	 if (element instanceof String) {
			             id = new Integer(String.valueOf(element));
		        	 }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return id;
			}
		});
	}
	
	/**
	 * Clase auxiliar para manejar la información del formulario
	 * @author Alberto
	 *
	 */
	public class FilmForm {
		private List<Integer> films;

		public List<Integer> getFilms() {
			return films;
		}

		public void setFilms(List<Integer> films) {
			this.films = films;
		}

		@Override
		public String toString() {
			return "FilmForm [films=" + films + "]";
		}
	}
}