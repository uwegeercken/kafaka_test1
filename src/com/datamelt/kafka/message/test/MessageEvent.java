package com.datamelt.kafka.message.test;

import com.datamelt.kafka.message.test.TestMessage;

public interface MessageEvent
{
	public void processMessage (TestMessage message);
}
