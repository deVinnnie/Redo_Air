package com.realdolmen.course.utilities.persistence;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Prepares a persistence context for testing with JPA.
 * Use {@link #properties()} to configure database properties
 */
public abstract class JpaPersistenceTest extends DataSetPersistenceTest {
    private static final String JPA_DRIVER_PROPERTY = "javax.persistence.jdbc.driver";
    private static final String JPA_URL_PROPERTY = "javax.persistence.jdbc.url";
    private static final String JPA_USER_PROPERTY = "javax.persistence.jdbc.user";
    private static final String JPA_PASSWORD_PROPERTY = "javax.persistence.jdbc.password";

    private static final String PERSISTENCE_UNIT_NAME = "MyTestPersistenceUnit";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Override
    public final void initialize() throws Exception {
        logger.info("Creating EntityManagerFactory from persistence unit " + PERSISTENCE_UNIT_NAME);
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties());
        logger.info("Creating transacted EntityManager");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @Override
    public final void destroy() {
        completeTransaction();
        closeEntityManager();
        closeEntityManagerFactory();
    }

    /**
     * Provides connection settings for the database. These settings will merge with the ones already in the test persistence.xml.
     * Subclasses can override this to customize.
     * @return Map of JPA properties.
     */
    protected Map<String, String> properties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(JPA_DRIVER_PROPERTY, databaseEngine.driverClass);
        properties.put(JPA_URL_PROPERTY, databaseEngine.url);
        properties.put(JPA_USER_PROPERTY, databaseEngine.username);
        properties.put(JPA_PASSWORD_PROPERTY, databaseEngine.password);
        return Collections.unmodifiableMap(properties);
    }

    private void closeEntityManagerFactory() {
        logger.info("Closing EntityManagerFactory");
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    private void closeEntityManager() {
        logger.info("Closing EntityManager");
        if(entityManager != null) {
            entityManager.close();
        }
    }

    private void completeTransaction() {
        logger.info("Committing and closing transacted EntityManager");
        if(transaction != null) {
            if(transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        }
    }

    /**
     * Convenience for unit tests that assert entity counts.
     * @param entityClass The entity class for which to count records.
     * @param <T> Type of the entity.
     * @return The number of said entities found in the database.
     */
    protected <T> long count(Class<T> entityClass) {
        CriteriaBuilder builder = entityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(entityClass)));
        return entityManager().createQuery(query).getSingleResult();
    }

    /**
     * Obtains the current EntityManager. Use this to write tests against.
     */
    protected EntityManager entityManager() {
        return this.entityManager;
    }
}
