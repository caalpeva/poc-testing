package org.caalpeva.starwars.controller;

import java.util.List;

import org.caalpeva.starwars.repository.model.Film;
import org.caalpeva.starwars.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("result", "Pilot of Starship");
		//System.out.println(filmForm);
		//redirectAttributes.addFlashAttribute("successMessage", "Datos enviados correctamente");
		//return "redirect:index";
		return "films/filmForm";
	}

	@ModelAttribute("availableFilms")
	public List<Film> getMovieTypes() {
		return databaseService.findAllFilms();
	}

	public class FilmForm {
		private List<String> films;

		public List<String> getFilms() {
			return films;
		}

		public void setFilms(List<String> films) {
			this.films = films;
		}

		@Override
		public String toString() {
			return "FilmForm [films=" + films + "]";
		}
	}
}