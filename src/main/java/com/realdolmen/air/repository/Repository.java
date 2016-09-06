package com.realdolmen.air.repository;

import java.io.Serializable;
import java.util.List;


public interface Repository<T, PK extends Serializable> {
    /**
     * Find an entity with a matching identifier.
     *
     * @param id The identifier of the entity to look for.
     * @return T The entity.
     */
    T findByPrimaryKey(Long id);

    /**
     * Find all entities of type T.
     *
     * @return {@link List} A list containing entities of type T.
     */
    List<T> findAll();

    /**
     * Persist (update) an entity.
     *
     * @param entity The entity to persist.
     * @return T The persisted (or updated) version of the entity.
     */
    T update(T entity);

    /**
     * Persist (insert) an entity.
     *
     * @param entity The entity to persist.
     * @return T The persisted version of the entity.
     */
    T insert(T entity);

    /**
     * Convenience method to delete an entity with only specifying the identifier.
     *
     * @param id The identifier of the entity to be deleted.
     */
    void deleteById(PK id);

    /**
     * Remove an entity from the database.
     *
     * @param entity The entity to be removed, must be a subclass of {@link be.fgov.mobilit.hrm.repositories.Repository}.
     */
    void delete(T entity);

    /**
     * Provides access to EntityManager#flush() when it is required to execute certain queries first.
     */
    void flush();
}
