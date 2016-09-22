package com.datamelt.kafka.message.test;

import com.datamelt.kafka.message.test.TestMessage;

public class ProducerTest
{

	public static void main(String[] args) throws Exception
	{
		
		String topic = "topic10";
		
		TestMessage message = new TestMessage();
        message.setId(100l);
        message.setName("Uwe Geercken");
        message.setDescription("starting with kafka encoding and decoding 20");
		
        MessageProducer producer = new MessageProducer(topic);
		producer.sendMessage(message); 

	}

}
