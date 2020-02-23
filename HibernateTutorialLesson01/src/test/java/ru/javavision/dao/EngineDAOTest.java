package ru.javavision.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.core.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.javavision.model.Engine;

class EngineDAOTest {
	private static SessionFactory factory;
	private static Idao<Engine, Integer> engineDAO;
	private final Engine testEngine = new Engine();
	private static int unique1ID;
	private static int createID;
	private static int delete2ID;
	private static int update3ID;
	private static Engine test1;
	private static Engine test2;
	private static Engine test3;
	public final static Class<Logger> logger = Logger.class;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		engineDAO = new EngineDAO(factory);

		createTestData();
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	private static void createTestData() {
		System.out.println("======INSERT==========");
		try {
			test1 = new Engine("BMW", 111);
			engineDAO.create(test1);
			unique1ID = test1.getId();
			System.out.println("id: " + unique1ID + ": uniqueID test1 ");
		} catch (Exception e) {
			System.out.println(e + " createID " + unique1ID);
		}
		try {
			test2 = new Engine("Audio", 200);
			engineDAO.create(test2);
			delete2ID = test2.getId();
			System.out.println("id: " + delete2ID + ": deleteID test2 ");
		} catch (Exception e) {
			System.out.println(e + " test2 ");
		}
		try {
			test3 = new Engine("Volvo", 300);
			engineDAO.create(test3);
			update3ID = test3.getId();
			System.out.println("id: " + update3ID + ": updateID test3 ");
		} catch (Exception e) {
			System.out.println(e + " test3 ");
		}
		System.out.println("======INSERT==end========");
	}

  	@Test
	void testCreate() {
		System.err.println("-----testCreate------");
		Engine test1 = new Engine("Nissan", 123);

		engineDAO.create(test1);
		createID = test1.getId();
		final Engine result = engineDAO.read(test1.getId());
		System.out.println("\n  testEngine: " + test1);
		System.out.println("\n read result: " + result);

		assertThat(test1, is(result));
		assertEquals(test1.getId(), createID);
		System.err.println("-----testCreate-end-----");
	}

 	@Test
	void testDelete() {
 		System.err.println("-----testDelete------");
 		System.out.println("\n before delete testDelete result: " + test2);
		Engine testDelete = new Engine();

		engineDAO.delete(test2);
		final Engine result = engineDAO.read(delete2ID);
		System.out.println("\n after testDelete result: " + result);

		assertThat(testDelete, is(result));
		assertEquals(testDelete, result);
		System.err.println("-----testDelete-end-----");
	}

	@Test // Engine [id=89, model=Volvo, power=300] test3
	void testUpdate() {
		System.err.println("-----testupdate------");
		Engine readData = engineDAO.read(test3.getId());
		System.out.println("\n before   update result: " + readData);

		readData.setModel("Moscwich");
		engineDAO.update(readData);
		System.out.println("\n after update result: " + readData);

		assertThat(readData.getModel(), is("Moscwich"));
		assertEquals(readData.getModel(), "Moscwich");
		System.err.println("-----testupdate-end-----");
	}

	@AfterAll
	static void after() {
		deleteData();
	}

	private static void deleteData() {
		System.err.println("======DELETE==========");
		try {

			if (engineDAO.read(unique1ID) != null) {
//	 			engineDAO.delete(Engine.class, createID);
				engineDAO.delete(test1);
				System.out.println("\n delete(test1): " + test1);
			}
			if (engineDAO.read(delete2ID) != null) {
//	 			engineDAO.delete(Engine.class, createID);
				engineDAO.delete(test2);
				System.out.println("\n delete(test2): " + test2);
			}
			if (engineDAO.read(update3ID) != null) {
//	 			engineDAO.delete(Engine.class, createID);
				engineDAO.delete(test3);
				System.out.println("\n delete(test3): " + test3);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (factory != null) {
				factory.close();
			}
		}
		System.err.println("======DELETE=END=========");
	}
 

 	@Test
	void testRead () {
		engineDAO.create(testEngine);
		Engine engine = engineDAO.get(testEngine, 1);
		System.out.println(testEngine + " \n----------- " + engine);
		final Engine result = engineDAO.read(1);
 		assertThat(testEngine, is(result));

		assertTrue((engine) != null);
	}
}
