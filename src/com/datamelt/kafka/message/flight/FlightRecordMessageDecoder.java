package com.datamelt.kafka.message.flight;

import com.datamelt.kafka.serializer.MessageSerializer;

import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;

public class FlightRecordMessageDecoder implements Decoder<FlightRecordMessage>
{
	public FlightRecordMessageDecoder(VerifiableProperties verifiableProperties)
	{
    }
	
	@Override
    public FlightRecordMessage fromBytes(byte[] bytes)
	{
        try 
        {
        	return (FlightRecordMessage)MessageSerializer.deserialize(bytes);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        return null;
    }
}
