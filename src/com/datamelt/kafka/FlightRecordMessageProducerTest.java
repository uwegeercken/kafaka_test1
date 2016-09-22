package com.datamelt.kafka;

import com.datamelt.kafka.message.flight.FlightRecordMessage;
import com.datamelt.kafka.message.flight.FlightRecordMessageProducer;

public class FlightRecordMessageProducerTest
{

	public static void main(String[] args) throws Exception
	{
		
		String topic = "topic11";
		
		FlightRecordMessage record = new FlightRecordMessage(FlightRecordMessage.TYPE_ARRIVAL);
		
		record.addField("APT","ZRH");
		record.addField("FLC","TP");
		record.addField("FLN","344");
		record.addField("SDT","20160923");
		
        FlightRecordMessageProducer producer = new FlightRecordMessageProducer(topic);
		producer.sendMessage(record); 

	}

}
