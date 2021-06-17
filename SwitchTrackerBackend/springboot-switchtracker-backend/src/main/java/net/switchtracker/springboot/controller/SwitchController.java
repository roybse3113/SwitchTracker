package net.switchtracker.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.switchtracker.springboot.model.Switch;
import net.switchtracker.springboot.repository.SwitchRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class SwitchController {

	@Autowired
	private SwitchRepository switchRepository;
	
	@GetMapping("switches")
	public List<Switch> getSwitches() {
		return this.switchRepository.findAll();
	}
	
}
