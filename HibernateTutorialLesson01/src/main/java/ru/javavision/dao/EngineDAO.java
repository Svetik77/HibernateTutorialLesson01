package ru.javavision.dao;

//import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;

import ru.javavision.model.Engine;

/**
 * Created : 26/11/2017.
 */
//public class EngineDAO implements Idao<Engine, String> {
public class EngineDAO implements Idao<Engine, Integer> {
	public Engine read5(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	
    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public EngineDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Create new engine in engines table.
     *
     * @param engine for add.
     */
    @Override
    public void create(@NotNull final Engine engine) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(engine);

            session.getTransaction().commit();
        }
    }
    @Override
	public Engine get(@NotNull Engine entity, final Integer id) {
        try (final Session session = factory.openSession()) {

            final Engine result = session.get(Engine.class, id);

            return result != null ? result : new Engine();
        }
	}
    /**
     * Get engine by id.
     *
     * @param model for select.
     * @return engine with param model.
     */
    @Override
//    public Engine read(@NotNull final String model) {
    public Engine read(@NotNull final Integer id) {
        try (final Session session = factory.openSession()) {

            final Engine result = session.get(Engine.class, id);

            return result != null ? result : new Engine();
        }
    }

    /**
     * Update engine state.
     *
     * @param engine new state.
     */
    @Override
    public void update(@NotNull final Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(engine);

            session.getTransaction().commit();
        }
    }

    /**
     * Delete engine.
     *
     * @param engine for delete.
     */
    @Override
    public void delete(@NotNull final Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(engine);

            session.getTransaction().commit();
        }
    }

 

}
