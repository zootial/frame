package com.zzx.activemq.support;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	
	public void sendMessage(Destination destination, final String message){
		jmsTemplate.convertAndSend(destination, message);
	}
	
}
