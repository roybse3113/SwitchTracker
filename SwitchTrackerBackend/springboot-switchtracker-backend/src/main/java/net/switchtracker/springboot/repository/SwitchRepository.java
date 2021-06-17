package net.switchtracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.switchtracker.springboot.model.Switch;

@Repository
public interface SwitchRepository extends JpaRepository<Switch,Boolean>{

}
