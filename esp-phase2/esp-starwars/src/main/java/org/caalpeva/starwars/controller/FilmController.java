package org.caalpeva.starwars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

//
//	@GetMapping("/paginateIndex")
//	public String goIndexPaginate(Model model, Pageable pageable) {
//		model.addAttribute("moviesPage", movieService.findAll(pageable));
//		return "movies/moviePageableList";
//	}
//	
//	@GetMapping("/create")
//	public String create(@ModelAttribute Movie movie, Model model) {
//		//model.addAttribute("movieTypes", movieService.getMovieTypes());
//		return "movies/movieForm";
//	}
//	
//	@PostMapping("/save")
//	public String save(@ModelAttribute Movie movie, BindingResult result, RedirectAttributes redirectAttributes,
//			@RequestParam("imageFile") MultipartFile multipartFile, HttpServletRequest request, Model model) {
//		if (result.hasErrors()) {
//			System.out.println(result.getAllErrors());
//			//model.addAttribute("movieTypes", movieService.getMovieTypes());
//			return "movies/movieForm";
//		}
//
//		if (!multipartFile.isEmpty()) {
//			String filename = Utils.saveImage(multipartFile, request);
//			movie.setFilename(filename);
//		}
//
//		System.out.println(movie);
//		movieService.save(movie);
//
//		redirectAttributes.addFlashAttribute("successMessage", "El registro fue guardado");
//		//return "redirect:/movies/index";
//		return "redirect:/movies/paginateIndex?page=0"; // URL relativa al context path de la aplicaci�n
//	}
//
//	@GetMapping(value="/edit/{id}")
//	public String edit(@PathVariable("id") int movieId, Model model) {
//		Movie movie = movieService.findById(movieId);
//		model.addAttribute("movie", movie);
//		//model.addAttribute("movieTypes", movieService.getMovieTypes());
//		return "movies/movieForm";
//	}
//	
//	@GetMapping(value="/delete/{id}")
//	public String delete(@PathVariable("id") int movieId, RedirectAttributes attributes) {
//		movieService.delete(movieId);
//		attributes.addFlashAttribute("successMessage", "El registro fue eliminado");
//		return "redirect:/movies/index";
//	}
//
//	@ModelAttribute("movieTypes")
//	public List<FilmType> getMovieTypes() {
//		return movieService.getMovieTypes();
//	}
//	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
}