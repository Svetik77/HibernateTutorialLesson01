package ru.javavision;

 
import org.apache.logging.log4j.core.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javavision.dao.Idao;
import ru.javavision.dao.EngineDAO;
import ru.javavision.model.Engine;

/** Error: Could not find or load main class ru.javavision.ApplicationDemo
Caused by: java.lang.ClassNotFoundException: ru.javavision.ApplicationDemo
 * Created : 26/11/2017.
 */
public class ApplicationDemo {
 	public final static Class<Logger> logger = Logger.class ;
	
    public static void main(String[] args) {
//        PropertyConfigurator.configure("/log4j.properties");
        SessionFactory factory = null;
        

        try {
//        	logger.debug("Log4j appender configuration is successful !!");
       	factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
     //           factory = new Configuration().configure().buildSessionFactory();
//       	Idao<Engine, String> engineDAO = new EngineDAO(factory);
        Idao<Engine, Integer> engineDAO = new EngineDAO(factory);

            final Engine engine = new Engine(); 
            engine.setModel("engine_model_aa");
            engine.setPower(111);

            // Object transient // Object noch nicht benutzt
             engineDAO.create(engine);
            // inside create -> open session-> session.save : 
            // persistent
            // before session close
            // detached // //Object schon benutzt
//             logger.info("\n.......create.......\n");
//            System.out.println("read(empty obj) : " + engineDAO.read("engine_model_03"));
            System.out.println("read(empty obj) : " + engineDAO.read(1));

//            meth1(engineDAO);
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

	private static void meth1(Idao<Engine, String> engineDAO) {
		final Engine result = engineDAO.read("engine_model_03");
		System.out.println("Created : " + result);
		System.out.println();

		result.setPower(54321);
		engineDAO.update(result);

		final Engine update = engineDAO.read("engine_model_03");
		System.out.println("Updated : " + update);
		System.out.println();

		engineDAO.delete(new Engine("engine_model_03", 54321));

		System.out.println("Deleted(empty obj) : " + engineDAO.read("engine_model_03"));
	}
}
