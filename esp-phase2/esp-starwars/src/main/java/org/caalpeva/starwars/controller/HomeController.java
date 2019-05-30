package org.caalpeva.starwars.controller;

import java.io.IOException;

import org.caalpeva.starwars.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private ImportService importService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("users", "");
		//Date startDate = getStartDate();
		//model.addAttribute("banners", bannerService.findAllActives());
		//model.addAttribute("dates", Utils.getNextDays(startDate, NUMBER_OF_DAYS));
		//model.addAttribute("searchDate", dateFormat.format(startDate));
		//model.addAttribute("movies", movieService.findAllByShowtimeDate(startDate));
		//model.addAttribute("newsList", newsService.findLatest10());
		return "home";
	}
	
	@GetMapping("/import")
	public String goImport(Model model) {
//		User user = new User();
//		user.email = "alberto@fsfd.es";
//		user.firstName = "alberto";
//		user.lastName = "Perez";
//		user.id = 1;
//		user.isAdmin = true;
//		System.out.println(user);
//		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//		System.out.println(userDTO);
//		//starWarsApi.getPeoples();
//		//filmService.importDataFromWsapi();
//		//model.addAttribute("movies", movieService.findAll());
//		System.out.println("RestTemplate injected" + restTemplate.toString());
		try {
			importService.importDataFromWsapi();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "films/filmList";
	}
//	
//	@RequestMapping(value="/search", method=RequestMethod.POST)
//	public String searchMovies(@RequestParam("date") Date searchDate, Model model) {
//		//model.addAttribute("banners", bannerService.findAllActives());
//		model.addAttribute("dates", Utils.getNextDays(getStartDate(), NUMBER_OF_DAYS));
//		model.addAttribute("searchDate", dateFormat.format(searchDate));
//		model.addAttribute("movies", movieService.findAllByShowtimeDate(searchDate));
//		//model.addAttribute("newsList", newsService.findLatest10());
//		return "home";
//	}
//	
//	@RequestMapping(value="detail/{id}/{searchDate}")
//	public String goMovieDetail(@PathVariable("id") int movieId, @PathVariable("searchDate") Date date, Model model) {
//		model.addAttribute("searchDate", dateFormat.format(date));
//		model.addAttribute("showtimes", showtimesService.getShowTimes(movieId, date));
//		model.addAttribute("movie", movieService.findById(movieId));
//		return "movieDetail";
//	}
//
//	@ModelAttribute("banners")
//	public List<Banner> getBanners() {
//		return bannerService.findAllActives();
//	}
//	
//	@ModelAttribute("newsList")
//	public List<News> getNews() {
//		return newsService.findLatest10();
//	}
//	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
//	
//	private Date getStartDate() {
//		// Se calcula una fecha de inicio en la que se conoce que existen datos
//		Date startDate = showtimesService.findLatestShowtimesDate();
//		if (startDate != null) {
////			Calendar calendar = Calendar.getInstance();
////			calendar.setTime(startDate);
////			calendar.add(Calendar.DATE, -NUMBER_OF_DAYS);
////			startDate = calendar.getTime();
//		} else {
//			startDate = new Date();			
//		}
//		return startDate;
//	}
}