package com.datamelt.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.datamelt.kafka.message.MessageEvent;
import com.datamelt.kafka.message.test.TestMessage;
import com.datamelt.kafka.message.test.TestMessageDecoder;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;


public class MessageConsumer
{
	
	public static final String KEY_ZOOKEEPER_CONNECT	 				= "zookeeper.connect";
	public static final String KEY_ZOOKEEPER_SESSION_TIMEOUT_MS			= "zookeeper.session.timeout.ms";
	public static final String KEY_ZOOKEEPER_SYNC_TIME_MS				= "zookeeper.sync.time.ms";
	public static final String KEY_AUTO_COMMIT_INTERVAL_MS				= "auto.commit.interval.ms";
	public static final String KEY_GROUP_ID								= "group.id";
	
	private static String DEFAULT_VALUE_ZOOKEEPER_CONNECT 				= "localhost:2181";
	private static String DEFAULT_VALUE_ZOOKEEPER_SESSION_TIMEOUT_MS	= "400";
	private static String DEFAULT_VALUE_ZOOKEEPER_SYNC_TIME_MS			= "300";
	private static String DEFAULT_VALUE_AUTO_COMMIT_INTERVAL_MS			= "1000";
	
	private MessageEvent messageEvent;
	
	private Properties properties = new Properties();
	private String groupId;
	private String topic;
	
	public MessageConsumer(MessageEvent event, String groupId, String topic)
	{
		this.messageEvent = event;
		this.groupId = groupId;
		this.topic = topic;
		setDefaultProperties();
	}
	
	public MessageConsumer(MessageEvent event, String groupId, String topic, Properties properties)
	{
		this.messageEvent = event;
		this.topic = topic;
		this.properties = properties;
	}
	
	
	private void setDefaultProperties()
	{
		properties.put(KEY_ZOOKEEPER_CONNECT, DEFAULT_VALUE_ZOOKEEPER_CONNECT);
		properties.put(KEY_ZOOKEEPER_SESSION_TIMEOUT_MS, DEFAULT_VALUE_ZOOKEEPER_SESSION_TIMEOUT_MS);
		properties.put(KEY_ZOOKEEPER_SYNC_TIME_MS, DEFAULT_VALUE_ZOOKEEPER_SYNC_TIME_MS);
		properties.put(KEY_AUTO_COMMIT_INTERVAL_MS, DEFAULT_VALUE_AUTO_COMMIT_INTERVAL_MS);
		
		properties.put(KEY_GROUP_ID, groupId);
	}
	
	private ConsumerConnector getConsumerConnector()
	{
		ConsumerConfig conConfig = new ConsumerConfig(properties);
		return Consumer.createJavaConsumerConnector(conConfig);
	}
	
	public void consume()
	{
		
		ConsumerConnector consumerConnector = getConsumerConnector();
		
        //Key = topic name, Value = No. of threads for topic
		Map<String, Integer> topicCount = new HashMap<String, Integer>();       
        topicCount.put(topic, new Integer(1));
       
        //ConsumerConnector creates the message stream for each topic
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumerConnector.createMessageStreams(topicCount);         
 
        // Get Kafka stream for topic
        List<KafkaStream<byte[], byte[]>> kStreamList = consumerStreams.get(topic);
        // Iterate stream using ConsumerIterator
        for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) 
        {
               ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();
              
               while (consumerIte.hasNext())
               {
            	   TestMessageDecoder decoder = new TestMessageDecoder(null);
            	   TestMessage message = (TestMessage) decoder.fromBytes(consumerIte.next().message());
            	   
            	   // fire event
            	   messageEvent.processMessage(message);
               }
        }
        //Shutdown the consumer connector
        if (consumerConnector != null)
    	{
        	consumerConnector.shutdown();   
        }
	}

	
}
