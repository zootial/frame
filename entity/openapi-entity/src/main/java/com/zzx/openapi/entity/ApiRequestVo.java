package com.zzx.openapi.entity;

import java.io.Serializable;

public class ApiRequestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2165537450119623843L;
	private String accessCode;
	private String requestIp;
    private String data;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
