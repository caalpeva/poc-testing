package org.caalpeva.starwars.controller;

import org.caalpeva.starwars.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {
	
	@Autowired
	private DatabaseService importService;
	
	@GetMapping("/import")
	public boolean importData() {
//		try {
//			importService.importData();
//		} catch (Exception e) {
//			return false;
//		}
		
		return true;
	}
	
	@GetMapping("/delete")
	public boolean delete() {
//		try {
//			importService.deleteData();
//		} catch (Exception e) {
//			return false;
//		}
		
		return false;
	}
}