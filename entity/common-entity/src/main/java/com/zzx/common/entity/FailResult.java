package com.zzx.common.entity;

public class FailResult extends ActionResult {
	private static final long serialVersionUID = -1099795343205109524L;
	
	private String code;
	private String msg;
	private String devMsg;
	private String infoUrl;
	
	public FailResult(ActionStatus status, String code, String msg) {
		super(status);
		this.code = code;
		this.msg = msg;
	}

	public FailResult(ActionStatus status) {
		this(status, null, null);
	}

	public String getCode() {
		return code;
	}

	public FailResult setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public FailResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getDevMsg() {
		return devMsg;
	}

	public FailResult setDevMsg(String devMsg) {
		this.devMsg = devMsg;
		return this;
	}

	public String getInfoUrl() {
		return infoUrl;
	}

	public FailResult setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
		return this;
	}
}
