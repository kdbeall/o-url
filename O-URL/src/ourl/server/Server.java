package ourl.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.common.hash.Hashing;

import fi.iki.elonen.NanoHTTPD;
import ourl.store.Store;

public class Server extends NanoHTTPD {
	private static final String HOME = "/";

	// Allows us to perform operations on the Redis database.
	private Store store;

	public Server() throws IOException {
		super(8080);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
		store = new Store();
	}

	@Override
	public Response serve(IHTTPSession session) {
		String msg = "<html><body><h1>Hello, welcome to OURL</h1>\n";
		Map<String, String> parms = session.getParms();

		// Want user to input a url to shorten
		if (parms.get("url") == null && session.getUri().equals(HOME)) {
			msg += "<form action='?' method='get'>\n  <p>Your long url: <input type='text' name='url'></p>\n"
					+ "</form>\n";

			// Handling input of url to shorten, add to storage.
		} else if (parms.get("url") != null && session.getUri().equals(HOME)) {
			String shorturl = Hashing.murmur3_32().hashString(parms.get("url"), StandardCharsets.UTF_8).toString();
			store.add(shorturl, parms.get("url"));
			msg += "<p>Added, " + parms.get("url") + " </p>";
			msg += "<p> Short address is localhost:8080/" + shorturl + " </p>";
		} else if (!session.getUri().equals(HOME)) {
			if (store.get(session.getUri().substring(1)) != null) {
				msg += "<p> Long url is " + store.get(session.getUri().substring(1)) + " </p>";
			} else {
				msg += "<p> Resource not found </p>";
			}
		}
		return newFixedLengthResponse(msg + "</body></html>\n");
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
		}
	}

}
