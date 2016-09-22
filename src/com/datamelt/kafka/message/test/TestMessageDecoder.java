package com.datamelt.kafka.message.test;

import com.datamelt.kafka.serializer.MessageSerializer;

import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;

public class TestMessageDecoder implements Decoder<TestMessage>
{
	public TestMessageDecoder(VerifiableProperties verifiableProperties)
	{
    }
	
	@Override
    public TestMessage fromBytes(byte[] bytes)
	{
        try 
        {
        	return (TestMessage)MessageSerializer.deserialize(bytes);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        return null;
    }
}
