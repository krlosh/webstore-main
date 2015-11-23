package org.chenche.webstore.repository;

import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GenerateDDL {

	@Test
	public void generateDDL() {
		
		Persistence.generateSchema("jpa-persistence",null);
	}

}
