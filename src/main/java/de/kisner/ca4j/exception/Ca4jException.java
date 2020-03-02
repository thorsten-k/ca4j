package de.kisner.ca4j.exception;

import java.io.Serializable;

public class Ca4jException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1;

	public Ca4jException(String s)
	{ 
		super(s);
	}	
}
