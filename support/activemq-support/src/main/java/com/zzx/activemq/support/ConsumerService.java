package com.zzx.activemq.support;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		System.out.println("Consumer receive: " + text);
	}
}
