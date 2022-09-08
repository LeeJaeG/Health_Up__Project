package info.thecodinglive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.thecodinglive.repository.EatingPlanRepository;

@SpringBootTest
class StartExamApplicationTests {
@Autowired
EatingPlanRepository eatingPlanRepository;
	@Test
	void contextLoads() {
	String username="123";
//	System.out.println(eatingPlanRepository.eatList(username));
//	eatingPlanRepository.eatList(username);
	
	}

}
