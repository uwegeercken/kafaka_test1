package com.datamelt.kafka.message.flight;

import java.io.IOException;

import com.datamelt.kafka.serializer.MessageSerializer;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class FlightRecordMessageEncoder implements Encoder<FlightRecordMessage> 
{

	public FlightRecordMessageEncoder(VerifiableProperties verifiableProperties)
	{
    }
	
	@Override
    public byte[] toBytes(FlightRecordMessage testMessage) 
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
