package com.datamelt.kafka;

import com.datamelt.kafka.message.flight.FlightRecordMessage;
import com.datamelt.kafka.message.flight.MessageEvent;

public class FlightRecordMessageConsumerTest implements MessageEvent
{
	private String groupId;
	private String topic;
	
	public FlightRecordMessageConsumerTest(String groupId, String topic)
	{
		this.groupId = groupId;
		this.topic = topic;
	}
	
	public static void main(String[] args) throws Exception
	{

		String groupId = "group1";
		String topic = "topic11";
		
		FlightRecordMessageConsumerTest consumer = new FlightRecordMessageConsumerTest(groupId, topic);
		consumer.consume(); 

	}

	private void consume()
	{
		FlightRecordMessageConsumer consumer = new FlightRecordMessageConsumer(this,groupId,topic);
		consumer.consume();
	}
	
	@Override
	public void processMessage(FlightRecordMessage message)
	{
		System.out.println("Message consumed from topic: [" + topic + "] - " + message.getField(0).getString() + " - " + message.getField(1).getString() + " - " + message.getField(2).getString());
		
	}

}
