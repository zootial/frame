package com.zzx.quartz.support;

import org.quartz.DisallowConcurrentExecution;

@DisallowConcurrentExecution
public abstract class SerializableTask extends BaseTask {

}
