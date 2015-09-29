package com.github.changedi.http.core;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;

public class HttpParam {

	private URI uri;
	private RequestConfig requestConfig;

	public HttpParam() {
		super();
	}

	public HttpParam setURI(URI uri) {
		this.uri = uri;
		return this;
	}

	public HttpParam setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
		return this;
	}

	/**
	 * @return the uri
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * @return the requestConfig
	 */
	public RequestConfig getRequestConfig() {
		return requestConfig;
	}

}
