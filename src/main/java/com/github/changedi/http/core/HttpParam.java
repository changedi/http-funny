package com.github.changedi.http.core;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;

import com.google.common.collect.Lists;

public class HttpParam {

	public static RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig
			.custom()
			.setCookieSpec(CookieSpecs.DEFAULT)
			.setExpectContinueEnabled(true)
			.setTargetPreferredAuthSchemes(
					Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
			.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
			.build();
	private URI uri;
	private RequestConfig requestConfig;
	private List<Header> headers = Lists.newArrayList();

	public HttpParam() {
		super();
	}

	public HttpParam addHeader(String key, String value) {
		headers.add(new BasicHeader(key, value));
		return this;
	}

	/**
	 * @return the headers
	 */
	public List<Header> getHeaders() {
		return headers;
	}

	public Header[] getHeadersArray() {
		Header[] hs = new Header[0];
		for (Header h : headers) {
			hs = ArrayUtils.add(hs, h);
		}
		return hs;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HttpParam [uri=" + uri + ", requestConfig=" + requestConfig
				+ ", headers=" + headers + "]";
	}

}
