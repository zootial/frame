package com.zzx.common.entity;

import java.io.Serializable;

public abstract class ActionResult implements Serializable {
	private static final long serialVersionUID = 4627923951473377223L;
	
	private Integer status;
	
	public ActionResult(ActionStatus status) {
		this.status = status.value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public static <T> SuccessResult<T> success() {
		return success(null);
	}
	
	public static <T> SuccessResult<T> success(T data) {
		SuccessResult<T> success = new SuccessResult<T>();
		success.setData(data);
		return success;
	}
	
	public static FailResult fail(ActionStatus status) {
		FailResult fail = new FailResult(status);
		return fail;
	}
}
