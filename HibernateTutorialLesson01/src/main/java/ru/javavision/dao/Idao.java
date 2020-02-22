package ru.javavision.dao;

public interface Idao<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    Entity get(Entity entity, Integer id);
    void update(Entity entity);
    void delete(Entity entity);
}
