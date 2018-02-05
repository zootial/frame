package com.zzx.common.entity;

public enum ActionStatus {
	OK(200, "OK"),
	
	BAD_REQUEST(400, "Bad Request"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	REQUEST_TIMEOUT(408, "Request Timeout"),
	
	INTERNAL_ERROR(500, "Internal Server Error"),
	NOT_IMPLEMENTED(501, "Not Implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	GATEWAY_TIMEOUT(504, "Gateway Timeout")
	;
	
	
	public final int value;

	public final String reason;
	
	ActionStatus(int value, String reason) {
		this.value = value;
		this.reason = reason;
	}
	
	public String toString() {
		return Integer.toString(this.value);
	}
	
	public static ActionStatus valueOf(int statusCode) {
		for (ActionStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
