package ru.javavision.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
		factory = new Configuration().configure().buildSessionFactory();
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

	@Test
	void testRead5() {
		engineDAO.create(testEngine);
		final Engine result = engineDAO.read(1);
		assertThat(testEngine, is(result));
	}

}
