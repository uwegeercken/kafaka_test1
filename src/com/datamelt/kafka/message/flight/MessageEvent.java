package com.datamelt.kafka.message.flight;

import com.datamelt.kafka.message.flight.FlightRecordMessage;

public interface MessageEvent
{
	public void processMessage (FlightRecordMessage message);
}
