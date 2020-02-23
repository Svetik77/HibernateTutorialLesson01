package ru.javavision.dao;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javavision.model.Engine;
import static org.hamcrest.core.Is.is;
class EngineDAOTestOld {

	
	private SessionFactory factory;

    private Idao<Engine, Integer> engineDAO;

    private final Engine testEngine = new Engine();

    @BeforeEach
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        engineDAO = new EngineDAO(factory);
        testEngine.setModel("test");
        testEngine.setPower(1);
    }

    @AfterEach
    public void after() {
        if (engineDAO.read(4) != null) {
            engineDAO.delete(testEngine);
        }
        factory.close();
    }

    
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}
 
    
    /**
     * @see ru.javavision.dao.EngineDAO#create(Engine).
     * @see ru.javavision.dao.EngineDAO#read(Object).
     */
    @Test
    public void whenCreateEngineThenEngineIsExist() {
        engineDAO.create(testEngine);
        final Engine result = engineDAO.read(1);
        assertThat(testEngine, is(result));
    }

    /**
     * @see ru.javavision.dao.EngineDAO#update(Engine).
     */
    @Test
    public void whenEngineUpdateThenPowerHasNewValue() {
        engineDAO.create(testEngine);
        testEngine.setPower(2);
        engineDAO.update(testEngine);
        final Engine result = engineDAO.read(2);
        assertThat(result.getPower(), is(2));
    }

    /**
     * @see ru.javavision.dao.EngineDAO#delete(Engine).
     */
    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        engineDAO.create(testEngine);
        final Engine before = engineDAO.read(2);
        engineDAO.delete(testEngine);
        final Engine after = engineDAO.read(2);
        assertNotNull(before.getModel());
        assertNull(after.getModel());
    }
}
