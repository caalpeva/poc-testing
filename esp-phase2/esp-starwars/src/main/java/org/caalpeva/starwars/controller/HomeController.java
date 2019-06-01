package org.caalpeva.starwars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("users", "");
		return "home";
	}
	
	@GetMapping("/database")
	public String goImport(Model model) {
		return "database/status";
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
//	@ModelAttribute("banners")
//	public List<Banner> getBanners() {
//		return bannerService.findAllActives();
//	}
//	
//	@ModelAttribute("newsList")
//	public List<News> getNews() {
//		return newsService.findLatest10();
//	}
}