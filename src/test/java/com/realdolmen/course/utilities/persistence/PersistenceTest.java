package com.realdolmen.course.utilities.persistence;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Takes care of some streamlining for getting persistence tests to work correctly in all cases.
 * These measures would likely be taken by a real world project as well, but they are not relevant to the essence of JPA.
 *
 * Advanced course participants are free to explore this code to get some ideas. Beginner course participants can simply
 * ignore the contents of this file, and assume "things will work".
 */
public abstract class PersistenceTest extends Assert {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final DatabaseEngine databaseEngine = selectDatabaseEngine();

    /**
     * Chooses the database engine to run this unit test.
     * Default lets {@link com.realdolmen.course.utilities.persistence.PersistenceTest.DatabaseEngine#current()} choose.
     * Subclasses may override this, if they know what they're doing :)
     * @return The selected database engine.
     */
    protected DatabaseEngine selectDatabaseEngine() {
        return DatabaseEngine.current();
    }

    /**
     * Recreates the SQL schema from scratch.
     * One might wonder why this is necessary when using a JPA property such as "hbm2ddl.auto". This is due to the fact
     * that sometimes a drop-create fails to delete old references. For example, when a previous entity is renamed,
     * the drop-create feature would not "see" that the table that corresponded with the old entity name still exists.
     * This thus creates "lingering" tables which sporadically causes trouble due to foreign key violations.
     * This step may not _always_ be necessary, but it _is_ necessary _sometimes_. As this course is not about dealing
     * with SQL foreign key side-effects, we take this measure to guarantee that problem never happens in the course.
     */
    @Before
    public void recreateSchemaIfRequired() throws SQLException {
        DatabaseEngine databaseEngine = selectDatabaseEngine();
        if(!databaseEngine.isInMemory) {
            logger.info("Recreating database schema '" + databaseEngine.schema + "'");
            try (Connection connection = newJdbcConnection()) {
                connection.createStatement().execute("drop schema " + databaseEngine.schema);
                connection.createStatement().execute("create schema " + databaseEngine.schema);
            }
        }
    }

    /**
     * Obtains a <strong>new</strong> JDBC connection using connection settings defined in {@link #databaseEngine}.
     * Note this connection does not participate in the same transaction as the {@link com.realdolmen.course.utilities.persistence.JpaPersistenceTest#entityManager()}, so be careful
     * when asserting against both.
     * @return A new JDBC connection. Callsite is responsible for closing.
     * @throws java.sql.SQLException When the shit hits the fan.
     */
    protected Connection newJdbcConnection() throws SQLException {
        return DriverManager.getConnection(databaseEngine.url, databaseEngine.username, databaseEngine.password);
    }

    /**
     * Represents all available database engines that can be used for running unit tests.
     * Switching implementations can be done using a {@link #DATABASE_ENGINE_SYSTEM_PARAMETER system parameter}.
     * Remember: inner enums are always static!
     */
    public enum DatabaseEngine {
        /**
         * MySQL based database engine for running against a production-mirror.
         */
        mysql("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/", "root", "", false),

        /**
         * H2 based database enigne for running (fast) in-memory.
         */
        h2("org.h2.Driver", "jdbc:h2:mem:", "sa", "", true);

        private static final DatabaseEngine DEFAULT_DATABASE_ENGINE = DatabaseEngine.mysql;
        private static final String DATABASE_ENGINE_SYSTEM_PARAMETER = "databaseEngine";
        private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseEngine.class);
        private static final String DATABASE_SCHEMA_NAME = "realdolmen";

        public final String url;
        public final String username;
        public final String driverClass;
        public final String password;
        public final String schema = DATABASE_SCHEMA_NAME;
        public final boolean isInMemory;

        DatabaseEngine(String driverClass, String urlPrefix, String username, String password, boolean isInMemory) {
            this.password = password;
            this.driverClass = driverClass;
            this.username = username;
            this.isInMemory = isInMemory;
            this.url = urlPrefix + schema;
        }

        private static DatabaseEngine current() {
            String databaseEngineProperty = System.getProperty(DATABASE_ENGINE_SYSTEM_PARAMETER);
            DatabaseEngine databaseEngine;
            if(databaseEngineProperty == null) {
                databaseEngine = DEFAULT_DATABASE_ENGINE;
                LOGGER.warn("Missing system property -DdatabaseEngine. Using default (" + databaseEngine + ").");
            } else {
                databaseEngine = DatabaseEngine.valueOf(databaseEngineProperty);
            }
            LOGGER.info("Using database engine: " + databaseEngine);
            return databaseEngine;
        }

        /**
         * Fails when the expected database engine is not the currently selected one.
         * Allows clients to mandate a specific environment, without them needing to violate their cohesion.
         * @param expected The expected database engine.
         * @param why Please add a reason why you would have this almost unreasonable request.
         */
        public void assertEquals(DatabaseEngine expected, String why) {
            Assert.assertEquals(why, expected, current());
        }
    }
}
