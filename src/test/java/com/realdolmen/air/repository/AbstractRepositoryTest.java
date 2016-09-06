package com.realdolmen.air.repository;

import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import com.realdolmen.air.repository.AbstractBaseRepository;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.junit.rules.TestName;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractRepositoryTest<T extends AbstractBaseRepository> {

    /* The repository that's under test. */
    private T repository;

    protected EntityManager entityManager;

    private static EntityManagerFactory entityManagerFactory;

    private static JdbcDatabaseTester jdbcDatabaseTester;

    private static Map<String, Object> replacements = new HashMap();

    private static IDatabaseConnection connection;

    private IDataSet dataset;

    @Rule
    public TestName testName = new TestName();

    @SuppressWarnings("unchecked")
    public Class<?> getRepositoryClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @BeforeClass
    public static void initClass() throws Exception {
        String persistenceUnit = "appPU";
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        Map e = entityManagerFactory.getProperties();
        String driver = (String) e.get("hibernate.connection.driver_class");
        String url = (String) e.get("hibernate.connection.url");
        String username = (String) e.get("hibernate.connection.username");
        String password = (String) e.get("hibernate.connection.password");
        jdbcDatabaseTester = new JdbcDatabaseTester(driver, url, username, password);
        connection = jdbcDatabaseTester.getConnection();
        replacements.put("[NULL]", null);
    }

    private IDataSet createDataset(InputStream dataSet) {
        try {
            IDataSet dataset;
            if (replacements.isEmpty()) {
                dataset = (new FlatXmlDataSetBuilder()).build(dataSet);
            } else {
                dataset = new ReplacementDataSet((new FlatXmlDataSetBuilder()).build(dataSet), new HashMap(this
                        .replacements), (Map) null);

            }
            return dataset;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Initialisation before each method. The testData is inserted using the locations passed in the {@link TestData}
     * annotation and a repository is instantiated using an entityManager for this test data.
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        Class clazz = this.getClass();
        Method method = clazz.getMethod(testName.getMethodName());
        TestData testDataAnnotation = method.getAnnotation(TestData.class);

        repository = (T) getRepositoryClass().newInstance();

        if (testDataAnnotation != null) {
            TestDataLocation dataLocation = testDataAnnotation.dataSet();
            dataset = createDataset(Thread.currentThread().getContextClassLoader().getResourceAsStream(dataLocation
                    .getValue()));
            DatabaseOperation.INSERT.execute(connection, dataset);
        }

        entityManager = entityManagerFactory.createEntityManager();
        repository.setEm(entityManager);
    }

    @After
    public void dispose() throws Exception {
        if(dataset != null) {
            DatabaseOperation.DELETE_ALL.execute(connection, dataset);
        }
        if (entityManager != null) {
            entityManager.close();
            entityManager = null;
        }
        repository = null;
    }

    @AfterClass
    public static void disposeClass() throws Exception {
        entityManagerFactory.close();
        connection.close();
    }

    public T getRepository() {
        return this.repository;
    }

    protected void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    protected void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    protected void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }

    protected void detach(Object entity) {
        entityManager.detach(entity);
    }
}
