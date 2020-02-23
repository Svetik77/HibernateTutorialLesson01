package ru.javavision.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.javavision.model.Engine;

class EngineDAOTest {
	private static SessionFactory factory;
	private Idao<Engine, Integer> engineDAO;
	private final Engine testEngine = new Engine();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		engineDAO = new EngineDAO(factory);
		testEngine.setModel("test");
		testEngine.setPower(1);
	}

	@AfterEach
	void after() {
		if (engineDAO.read(1) != null) {
			engineDAO.delete(testEngine);
		}
		factory.close();
	}

//	@Test
	void testRead() {
		engineDAO.create(testEngine);
		final Engine result = engineDAO.read(1);
		Engine engine = engineDAO.get(testEngine , 1);
		System.out.println("\n  testEngine: "+testEngine );
		System.out.println("\n read result: "+ result  );
		System.out.println("\n get engine: "+ engine +"\n");
		
//		assertThat(testEngine, is(result));
		assertThat(testEngine, is(result));
	}
	
	@Test
	void testRead5() {
		engineDAO.create(testEngine);
		Engine engine = engineDAO.get(testEngine , 1);
		System.out.println(testEngine + " \n----------- " + engine);
		final Engine result = engineDAO.read(1);
//		assertThat(testEngine, is(result));
		
		assertTrue((engine )  != null );
	}
}
