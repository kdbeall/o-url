/**
 * 
 */
package ourl.store;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * @author kdbeall
 *
 */
public class StoreTest {
	private static final int TEST_DB_NUM = 1;
	private Jedis connector;

	private Store store;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Make sure Reddis is running first.
		// Open a new terminal, cd to redis src directory,
		// ./redis-server

		// Connect to redis
		connector = new Jedis("localhost");
		// Choose which database
		connector.select(TEST_DB_NUM);
		// Empty that database
		connector.flushDB();

		store = new Store(connector);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		connector.close();
	}

	@Test
	public void testGetConnector() {
		assertEquals(0, connector.dbSize().longValue());
		connector.append("shorturl", "longlonglongurl");
		assertEquals("longlonglongurl", connector.get("shorturl"));
	}

	@Test
	public void testAddConnector() {
		assertEquals(0, connector.dbSize().longValue());
		connector.append("shorturl", "superlonglonglongurl");
		assertEquals(1, connector.dbSize().longValue());
		connector.append("shorturl2", "superlonglonglongurl2");
		assertEquals(2, connector.dbSize().longValue());

	}

	@Test
	public void testStore() {
		store.add("shorturl", "superlonglongurl");
		assertEquals("superlonglongurl", store.get("shorturl"));
		store.add("shorturl2", "superlonglongurl2");
		assertEquals("superlonglongurl2", store.get("shorturl2"));

		// If it doesn't exist!
		assertEquals(null, store.get("blahh"));

	}

}
