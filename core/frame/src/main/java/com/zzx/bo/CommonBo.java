package com.zzx.bo;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CommonBo<T, ID extends Serializable> {

	protected Log log = LogFactory.getLog(getClass());
	
	
}
