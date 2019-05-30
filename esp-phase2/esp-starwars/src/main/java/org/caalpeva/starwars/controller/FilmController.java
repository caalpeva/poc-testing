package org.caalpeva.starwars.controller;

import org.caalpeva.starwars.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/films")
public class FilmController {

	//@Autowired
	//private StarWarsApi starWarsApi;
	
	@Autowired
	private PersonService filmService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/import")
	public String goIndex(Model model) {
		User user = new User();
		user.email = "alberto@fsfd.es";
		user.firstName = "alberto";
		user.lastName = "Perez";
		user.id = 1;
		user.isAdmin = true;
		System.out.println(user);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		System.out.println(userDTO);
		//starWarsApi.getPeoples();
		//filmService.importDataFromWsapi();
		//model.addAttribute("movies", movieService.findAll());
		System.out.println("RestTemplate injected" + restTemplate.toString());
		return "films/filmList";
	}
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