package com.datamelt.kafka.message;

import com.datamelt.kafka.message.test.TestMessage;

public interface MessageEvent
{
	public void processMessage (TestMessage message);
}
