/**
 * 
 */
package com.google.code.smallcrab.config.matcher.accesslog;

import com.google.code.smallcrab.protocol.accesslog.ALPackage;

/**
 * @author xalinx at gmail dot com
 * @date Dec 31, 2010
 */
public class ALCodeMatcher extends AbstractContainALMatcher {

	/**
	 * 
	 */
	public ALCodeMatcher() {
		super();
	}

	/**
	 * @param contain
	 */
	public ALCodeMatcher(String contain) {
		super(contain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.smallcrab.scan.apache.AbstractContainApacheMatcher#getAllView(com.google.code.smallcrab.scan.apache.Apachepac)
	 */
	@Override
	protected String getAllView(ALPackage pac) {
		return pac.getCode();
	}

}
