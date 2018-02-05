package com.zzx.common.entity;

public class SuccessResult<T> extends ActionResult {
	private static final long serialVersionUID = 7136992050787061657L;
	
	private T data;
	
	public SuccessResult() {
		super(ActionStatus.OK);
	}
	
	public T getData() {
		return this.data;
	}

	public SuccessResult<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}
