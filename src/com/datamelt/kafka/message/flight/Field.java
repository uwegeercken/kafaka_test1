package com.datamelt.kafka.message.flight;

import java.io.Serializable;

public class Field implements Serializable
{
	private static final long serialVersionUID = -2067612899574867425L;
	
	private String name;
	private String value;
	
	public Field(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getString()
	{
		return "[" + name + ": " + value + "]";
	}
	
}
