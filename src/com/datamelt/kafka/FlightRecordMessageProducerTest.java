package com.datamelt.kafka;

import com.datamelt.kafka.message.flight.FlightRecordMessage;

public class FlightRecordMessageProducerTest
{

	public static void main(String[] args) throws Exception
	{
		
		String topic = "topic11";
		
		FlightRecordMessage record = new FlightRecordMessage(FlightRecordMessage.TYPE_ARRIVAL);
		
		record.addField("APT","ZRH");
		record.addField("FLC","LX");
		record.addField("FLN","1072");
		record.addField("SDT","20160922");
		
        FlightRecordMessageProducer producer = new FlightRecordMessageProducer(topic);
		producer.sendMessage(record); 

	}

}
