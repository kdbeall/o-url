package ourl.store;

/**
 * Operations to add a url and get a url.
 * 
 * @author kdbeall
 *
 */
public interface StoreOperations {

	/**
	 * Add a (url, shorturl) pair to the redis database.
	 * 
	 * @param shorturl
	 *            The key.
	 * @param url
	 *            The value.
	 */
	public void add(String url, String shorturl);

	/**
	 * Looks up corresponding URL in store.
	 * 
	 * @param url
	 *            A shortened URL.
	 * @return The lengthy URL.
	 */
	public String get(String url);

}
