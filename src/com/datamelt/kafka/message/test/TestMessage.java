package com.datamelt.kafka.message.test;

import java.io.Serializable;

public class TestMessage implements Serializable
{
	private static final long serialVersionUID = -8332454158279955L;

	private long id;
	private String name;
	private String description;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
	
}
