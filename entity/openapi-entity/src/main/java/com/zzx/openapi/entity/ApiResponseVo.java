package com.zzx.openapi.entity;

import java.io.Serializable;

public class ApiResponseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755624541106886619L;
	private String status;
	private String code;
	private String msg;
	private String devMsg;
	private String infoUrl;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDevMsg() {
		return devMsg;
	}
	public void setDevMsg(String devMsg) {
		this.devMsg = devMsg;
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
}
