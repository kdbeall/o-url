package ourl.store;

/**
 * Operations to add a url and get a url.
 * 
 * @author kdbeall
 *
 */
public interface StoreOperations {

	/**
	 * Adds a URL to the store. Generate a new shortened URL.
	 * 
	 * @param url
	 *            A length URL to shorten.
	 * 
	 */
	public void add(String url);

	/**
	 * Looks up corresponding URL in store.
	 * 
	 * @param url
	 *            A shortened URL.
	 * @return The lengthy URL.
	 */
	public String get(String url);

}
