package com.realdolmen.course.utilities.persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Loads a DBUnit test set before every unit test.
 */
public abstract class DataSetPersistenceTest extends PersistenceTest {
    @Before
    public void loadDataSet() throws Exception {
        initialize();
        logger.info("Loading datasets");
        try(Connection jdbcConnection = newJdbcConnection()) {
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
            if(databaseEngine == DatabaseEngine.mysql) {
                connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning
            }
            DatabaseOperation.INSERT.execute(connection, createDataSet());
        }
    }

    @After
    public void unloadDataSet() throws Exception {
        // Nothing to see here, move along...
        destroy();
    }

    /**
     * Callback hook for subclasses to trigger their behaviour before initializing datasets.
     */
    protected abstract void initialize() throws Exception;

    /**
     * Callback hook for subclasses to trigger their behaviour before unloading datasets.
     */
    protected abstract void destroy() throws Exception;

    /**
     * Builds a combination dataset, including replacing {null} by null.
     * @return The combo dataset that should be executed.
     * @throws org.dbunit.dataset.DataSetException When the stars are in the wrong position.
     */
    private IDataSet createDataSet() throws DataSetException {
        final FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();
        HashMap<String, Object> replacements = new HashMap<>();
        replacements.put("{null}", null);
        return new ReplacementDataSet(new CompositeDataSet(Arrays.stream(dataSets()).<IDataSet>map(s -> {
            try {
                return dataSetBuilder.build(getClass().getResource(s));
            } catch (DataSetException e) {
                throw new RuntimeException("Problem loading DBUnit dataset", e);
            }
        }).toArray(IDataSet[]::new)), replacements, null);
    }

    /**
     * Provides a list of datasets that should be loaded.
     * Subclasses can override this to provide customization.
     * @return An array of Strings pointing to class-path relative dataset XML files.
     */
    protected String[] dataSets() {
        return new String[] { "/data.xml" };
    }

}
