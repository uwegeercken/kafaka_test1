package com.datamelt.kafka.message.flight;

import java.io.Serializable;
import java.util.ArrayList;

public class FlightRecordMessage implements Serializable
{
	private static final long serialVersionUID = 5890163889777805495L;

	public static final int TYPE_ARRIVAL 	= 0;
	public static final int TYPE_DEPARTURE 	= 1;
	
	private ArrayList<Field> fields = new ArrayList<Field>();
	private int type;
	
	public FlightRecordMessage(int type) throws Exception
	{
		if(type<0 || type>1)
		{
			throw new Exception("invalid type - must be 0 or 1");
		}
		this.type = type;
	}
	
	public FlightRecordMessage(int type, ArrayList<Field> fields) throws Exception
	{
		if(type<0 || type>1)
		{
			throw new Exception("invalid type - must be 0 or 1");
		}
		this.type = type;
		this.fields = fields;
	}
	
	public void addField(Field field)
	{
		fields.add(field);
	}
	
	public void addField(String name, String value)
	{
		fields.add(new Field(name, value));
	}
	
	public Field getField(String name)
	{
		int fieldIndex = existField(name);
		if(fieldIndex>-1)
		{
			return fields.get(fieldIndex);
		}
		else
		{
			return null;
		}
	}
	
	public Field getField(int index)
	{
		if(fields!=null && fields.size()>=index)
		{
			return fields.get(index);
		}
		return null;
	}
	
	public void removeField(String name)
	{
		int fieldIndex = existField(name);
		if(fieldIndex>-1)
		{
			fields.remove(fieldIndex);
		}
	}
	
	public void removeField(int index)
	{
		if(fields!=null && fields.size()>=index)
		{
			fields.remove(index);
		}
	}
	
	private int existField(String name)
	{
		if(fields!=null && fields.size()>0)
		{
			for(int i=0;i<fields.size();i++)
			{
				if(fields.get(i).getName().equals(name))
				{
					return i;
				}
			}
		}
		return -1;
	}

	public ArrayList<Field> getFields()
	{
		return fields;
	}

	public int getType()
	{
		return type;
	}
}
