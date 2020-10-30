package cegepst.engine;

import cegepst.engine.entity.StaticEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollidableRepository implements Iterable<StaticEntity>{

    private static CollidableRepository instance;
    private final List<StaticEntity> registeredCollidableEntitites;

    public static CollidableRepository getInstance() {
        if (instance == null) {
            instance = new CollidableRepository();
        }
        return instance;
    }

    public void registerEntity(StaticEntity entity) {
        registeredCollidableEntitites.add(entity);
    }

    public void registerEntities(Collection<StaticEntity> entities) {
        registeredCollidableEntitites.addAll(entities);
    }

    public void unregisterEntity(StaticEntity entity) {
        registeredCollidableEntitites.remove(entity);
    }

    public int count() {
        return registeredCollidableEntitites.size();
    }

    private CollidableRepository() {
        registeredCollidableEntitites = new ArrayList<>();
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return registeredCollidableEntitites.iterator();
    }
}
