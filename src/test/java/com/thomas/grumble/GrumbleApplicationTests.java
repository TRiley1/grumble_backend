package com.thomas.grumble;

import com.thomas.grumble.models.Grumble;
import com.thomas.grumble.repositories.GrumbleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.thomas.grumble.repositories.UserRepository;
import com.thomas.grumble.models.UserEntity;


@SpringBootTest
class GrumbleApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	GrumbleRepository grumbleRepository;

	@Test
	void contextLoads() {
	}



	@Test
	public void canGrumble() {
		UserEntity Krusty = new UserEntity("Krusty","HeyHeyKids");
		Grumble grumble1 = new Grumble(Krusty,"What's a guy gotta do");
		userRepository.save(Krusty);
		grumbleRepository.save(grumble1);
	}

	@Test
	public void userCanGrumbleMultipleTimes(){
		UserEntity Burns = new UserEntity("Burns", "excellent");
		userRepository.save(Burns);
		Grumble grumble1 = new Grumble(Burns, "muhahaha");
		grumbleRepository.save(grumble1);
		Grumble grumble2 = new Grumble(Burns, "Why do I need to pay my employees?");
		grumbleRepository.save(grumble2);

	}

}
