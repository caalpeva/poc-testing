package org.caalpeva.starwars.ws.exception;

import java.io.IOException;
import java.net.URL;

public class HttpCommunicationException extends IOException {

	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	private URL url; 

	public HttpCommunicationException(int statusCode, URL url) {
		this.statusCode = statusCode;
		this.url = url;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public URL getUrl() {
		return url;
	}
}