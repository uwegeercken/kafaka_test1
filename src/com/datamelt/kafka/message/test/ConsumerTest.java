package com.datamelt.kafka.message.test;

import com.datamelt.kafka.message.test.MessageEvent;
import com.datamelt.kafka.message.test.TestMessage;

public class ConsumerTest implements MessageEvent
{
	private String groupId;
	private String topic;
	
	public ConsumerTest(String groupId, String topic)
	{
		this.groupId = groupId;
		this.topic = topic;
	}
	
	public static void main(String[] args) throws Exception
	{

		String groupId = "group1";
		String topic = "topic10";
		
		ConsumerTest consumerTest = new ConsumerTest(groupId, topic);
		consumerTest.consume(); 

	}

	private void consume()
	{
		MessageConsumer consumer = new MessageConsumer(this,groupId,topic);
		consumer.consume();
	}
	
	@Override
	public void processMessage(TestMessage message)
	{
		System.out.println("Message consumed from topic: [" + topic + "] - " + message.getId() + " - " + message.getName() + " - " + message.getDescription());
		
	}

}
