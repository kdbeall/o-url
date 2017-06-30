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
	public void add(String shorturl, String url);

	/**
	 * Looks up corresponding URL in store.
	 * 
	 * @param shorturl
	 *            A shortened URL.
	 * @return The lengthy URL or null if shorturl doesn't exist.
	 */
	public String get(String shorturl);

}
