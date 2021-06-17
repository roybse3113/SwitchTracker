package net.switchtracker.springboot;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.switchtracker.springboot.model.Switch;
import net.switchtracker.springboot.model.SwitchParser;
import net.switchtracker.springboot.repository.SwitchRepository;

@SpringBootApplication
public class SpringbootSwitchtrackerBackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSwitchtrackerBackendApplication.class, args);
	}

	@Autowired
	private SwitchRepository switchRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		this.switchRepository.save(new Switch("Gateron Brown", 0.65, false));
//		this.switchRepository.save(new Switch("Gateron Red", 0.60, true));
		SwitchParser parse = new SwitchParser();
		for (Entry<Switch, String> entry : parse.getAllSwitches().entrySet()) {
			this.switchRepository.save(entry.getKey());
		}
	}

}
