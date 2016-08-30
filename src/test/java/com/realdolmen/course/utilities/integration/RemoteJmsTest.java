package com.realdolmen.course.utilities.integration;

import org.junit.After;
import org.junit.Before;

import javax.jms.*;

/**
 * Base class to do all the housekeeping for doing interactions with JMS.
 * Extend from this class to run integration unit-tests that test the behaviour of Message Driven Beans.
 */
public abstract class RemoteJmsTest extends RemoteIntegrationTest {
    protected Connection connection;
    protected Session session;
    protected MessageProducer producer;
    protected String queueLookup = "rd/queues/RealDolmenQueue";

    @Before
    public void initializeJms() throws Exception {
        ConnectionFactory connectionFactory = lookup("jms/RemoteConnectionFactory");
        Queue queue = lookup(queueLookup);
        connection = connectionFactory.createConnection("administrator", "Azerty123!");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(queue);
    }

    @After
    public void destroyJms() throws JMSException {
        if(connection != null) {
            connection.close();
        }
    }

    /**
     * Patiently asserts for all assertions inside runnable to complete within specified time, retrying every specified
     * interval.
     * This method is convenient to assert asynchronous behaviour; it will succeed as soon as possible, but wait for
     * a set amount of time before deciding to fail. (no infinite loop nor unnecessary waiting but you pay the price
     * of assertions being run multiple times).
     * @param assertions The functional interface to contain assert statements (or otherwise throw AssertionError).
     * @param patience The maximum amount of time to wait before failing.
     * @param napTime How long to wait in between retries.
     */
    protected void assertPatiently(Runnable assertions, long patience, long napTime) {
        logger.info("Waiting patiently for " + patience + "ms to allow assertions to become successful.");
        AssertionError error = null;
        long begin = System.nanoTime();
        long patienceLeft = patience;
        while(patienceLeft > 0) {
            try {
                assertions.run();
                return;
            } catch (AssertionError e) {
                logger.info("Assertions not successful yet. Hanging back. (patience left: " + patienceLeft + "ms)");
                error = e;
            }
            powerNap(napTime);
            patienceLeft = patience - ((System.nanoTime() - begin) / 1_000_000); // Nanos to Millis
        }
        logger.info("Out of patience, will fail now.");

        throw new AssertionError("Out of patience", error);
    }

    /**
     * Patiently asserts for 5 seconds, with 0.25 second retry interval.
     * @see #assertPatiently(Runnable, long, long)
     */
    protected void assertPatiently(Runnable runnable) {
        assertPatiently(runnable, 5000, 250);
    }

    private void powerNap(long howLong) {
        try {
            Thread.sleep(howLong);
        } catch (InterruptedException e) {
            throw new RuntimeException("Unable to take power nap", e);
        }
    }
}
