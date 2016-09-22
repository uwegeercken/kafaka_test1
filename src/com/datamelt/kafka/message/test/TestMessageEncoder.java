package com.datamelt.kafka.message.test;

import java.io.IOException;

import com.datamelt.kafka.serializer.MessageSerializer;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class TestMessageEncoder implements Encoder<TestMessage> 
{

	public TestMessageEncoder(VerifiableProperties verifiableProperties)
	{
    }
	
	@Override
    public byte[] toBytes(TestMessage testMessage) 
	{
		byte[] messageBytes = null;
        try
        {
        	messageBytes = MessageSerializer.serialize(testMessage);
        }
        catch(IOException ioe)
        {
        	ioe.printStackTrace();
        }
		
		return messageBytes;
    }
}
