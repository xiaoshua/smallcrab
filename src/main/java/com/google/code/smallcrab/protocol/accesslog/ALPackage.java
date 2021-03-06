/**
 * 
 */
package com.google.code.smallcrab.protocol.accesslog;

import java.util.Map;

import com.google.code.smallcrab.protocol.LinePackege;
import com.google.code.smallcrab.utils.UrlKit;

/**
 * @author seanwang xalinx@gmail.com
 * 
 */
public class ALPackage implements LinePackege {
	private Map<String, String> segs;

	private String line;

	private String path;

	private String query;

	private String domain;

	/*
	 * @notthreadsafe
	 * 
	 * @see com.google.code.smallcrab.scan.Linepac#split(java.lang.String, int)
	 */
	@Override
	public void split(String line) {
		this.segs = ALHelper.defaultSplit(line);
		this.line = line;
	}

	/**
	 * @return query from request url
	 */
	public String getQuery() {
		if (query == null) {
			query = UrlKit.extractQuery(segs.get("request"));
		}
		return query;
	}

	/**
	 * @return path from request url
	 */
	public String getPath() {
		if (path == null) {
			path = UrlKit.extractPath(segs.get("request"));
		}
		return path;
	}

	/**
	 * @return
	 */
	public String getDomain() {
		if (domain == null) {
			domain = UrlKit.extractDomain(segs.get("request"));
		}
		return domain;
	}

	/**
	 * @return request host/ip
	 */
	public String getIP() {
		return segs.get("ip");
	}

	/**
	 * @return request period
	 */
	public String getPeriod() {
		return segs.get("period");
	}

	/**
	 * @return request time
	 */
	public String getTime() {
		return segs.get("time");
	}

	/**
	 * @return request method
	 */
	public String getMethod() {
		return segs.get("method");
	}

	/**
	 * @return request url
	 */
	public String getRequest() {
		return segs.get("request");
	}

	/**
	 * @return response code
	 */
	public String getCode() {
		return segs.get("status");
	}

	/**
	 * @return request body size
	 */
	public String getSize() {
		return segs.get("size");
	}

	/**
	 * @return referer url
	 */
	public String getReferer() {
		return segs.get("referer");
	}

	/**
	 * @return http client agent
	 */
	public String getAgent() {
		return segs.get("agent");
	}

	/**
	 * @return
	 */
	public String getLine() {
		return line;
	}

	@Override
	public String column(int columnIndex) {
		throw new UnsupportedOperationException("" + columnIndex);
	}

}
