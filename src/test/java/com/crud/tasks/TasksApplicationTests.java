package com.crud.tasks;

import com.crud.tasks.config.Profiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.LOCAL)
public class TasksApplicationTests {

	@Test
	public void contextLoads() {
	}

}
