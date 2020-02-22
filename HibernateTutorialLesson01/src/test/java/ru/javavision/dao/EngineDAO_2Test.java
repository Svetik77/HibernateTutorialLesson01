package ru.javavision.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.javavision.model.Engine;

public class EngineDAO_2Test {
	private static ServiceRegistry serviceRegistry;
    private static SessionFactory factory;

//    private Idao<Engine, String> engineDAO;
    private Idao<Engine, Integer> engineDAO;

    private final Engine testEngine = new Engine();
    private static SessionFactory configureSessionFactory() throws HibernateException {
  	  Configuration configuration = new Configuration();
  	    configuration.configure();
  	    
  	    
  	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
  	            configuration.getProperties()).build();
  	    
  	    
  	  factory = configuration.buildSessionFactory(serviceRegistry);
  	    
  	    return factory;
  }
    public static SessionFactory getSessionFactory() {
        return configureSessionFactory();

    }
    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
//        engineDAO = new EngineDAO(factory);
        engineDAO = new EngineDAO(factory);
        testEngine.setModel("test");
        testEngine.setPower(1);
    }

    @After
    public void after() {
//    	if (engineDAO.read("test") != null) {
        if (engineDAO.read(1) != null) {
            engineDAO.delete(testEngine);
        }
        factory.close();
    }

    /**
     * @see ru.javavision.dao.EngineDAO#create(Engine).
     * @see ru.javavision.dao.EngineDAO#read(Object).
     */
    @Test
    public void whenCreateEngineThenEngineIsExist() {
        engineDAO.create(testEngine);
//        final Engine result = engineDAO.read("test");
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
//        final Engine result = engineDAO.read("test");
        final Engine result = engineDAO.read(2);
        assertThat(result.getPower(), is(2));
    }

    /**
     * @see ru.javavision.dao.EngineDAO#delete(Engine).
     */
    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        engineDAO.create(testEngine);
//        final Engine before = engineDAO.read("test");
        final Engine before = engineDAO.read(2);
        engineDAO.delete(testEngine);
//        final Engine after = engineDAO.read("test");
        final Engine after = engineDAO.read(2);
        assertNotNull(before.getModel());
        assertNull(after.getModel());
    }
}