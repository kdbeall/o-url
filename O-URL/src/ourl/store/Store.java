package ourl.store;

import redis.clients.jedis.Jedis;

/**
 * Operations to be done on our store.
 * 
 * @author kdbeall
 *
 */
public class Store implements StoreOperations {
	private static final int DB_NUM = 2;
	private Jedis connector;

	public Store(Jedis connector) {
		this.connector = connector;
	}

	public Store() {
		connector = new Jedis("localhost");
		connector.select(DB_NUM);
	}

	@Override
	public void add(String shorturl, String url) {
		if (connector.exists(shorturl)) {
			return;
		}
		connector.append(shorturl, url);
	}

	@Override
	public String get(String shorturl) {
		// TODO Auto-generated method stub
		if (connector.exists(shorturl)) {
			return connector.get(shorturl);
		}
		return null;
	}

}
