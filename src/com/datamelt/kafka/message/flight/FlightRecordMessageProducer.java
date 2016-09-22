package com.datamelt.kafka.message.flight;

import java.io.IOException;
import java.util.Properties;

import com.datamelt.kafka.message.flight.FlightRecordMessage;
import com.datamelt.kafka.serializer.MessageSerializer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class FlightRecordMessageProducer
{
	public static final String KEY_METADATA_BROKER_LIST 		= "metadata.broker.list";
	public static final String KEY_SERIALIZER_CLASS 			= "serializer.class";
	public static final String KEY_REQUEST_REQUIRED_ACKS 		= "request.required.acks";
	
	private static String DEFAULT_VALUE_METADATA_BROKER_LIST 	= "localhost:9092";
	private static String DEFAULT_VALUE_SERIALIZER_CLASS 		= "kafka.serializer.DefaultEncoder";
	private static String DEFAULT_VALUE_REQUEST_REQUIRED_ACKS	= "1";
	
	private Producer<Long, byte[]> producer;
	private Properties properties = new Properties();
	private String topic;
	
	public FlightRecordMessageProducer(String topic)
	{
		this.topic = topic;
		setDefaultProperties();
	}
	
	public FlightRecordMessageProducer(String topic, Properties properties)
	{
		this.topic = topic;
		this.properties = properties;
	}
	
	private void setDefaultProperties()
	{
		properties.put(KEY_METADATA_BROKER_LIST, DEFAULT_VALUE_METADATA_BROKER_LIST);
		properties.put(KEY_SERIALIZER_CLASS, DEFAULT_VALUE_SERIALIZER_CLASS);
		properties.put(KEY_REQUEST_REQUIRED_ACKS, DEFAULT_VALUE_REQUEST_REQUIRED_ACKS);
	}

	private KeyedMessage<Long, byte[]> getKeyedMessage(FlightRecordMessage message) throws IOException
	{
		return new KeyedMessage<Long, byte[]>(topic, MessageSerializer.serialize(message));
	}
	
	public void sendMessage(FlightRecordMessage message) throws IOException
	{
		producer = new Producer<Long, byte[]>(new ProducerConfig(properties));
		producer.send(getKeyedMessage(message)); 
	}
}
