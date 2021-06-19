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
		SwitchParser parse = new SwitchParser();
		for (Entry<Switch, String> entry : parse.getAllSwitches().entrySet()) {
			Switch s = entry.getKey();
			s.setURL(entry.getValue());
			this.switchRepository.save(s);
		}
	}

}
