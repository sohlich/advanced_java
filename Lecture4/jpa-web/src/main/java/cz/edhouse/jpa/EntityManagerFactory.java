package cz.edhouse.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Not best practice, will be replaced by CDI in following lecture.
 *
 * @author Frantisek Spacek
 */
public class EntityManagerFactory {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        if (ENTITY_MANAGER.get() == null) {
            ENTITY_MANAGER.set(Persistence
                    .createEntityManagerFactory("default-unit")
                    .createEntityManager());
        }
        return ENTITY_MANAGER.get();
    }

    public static void clear() {
        if (ENTITY_MANAGER.get() != null) {
            ENTITY_MANAGER.get().close();
            ENTITY_MANAGER.remove();
        }
    }
}
