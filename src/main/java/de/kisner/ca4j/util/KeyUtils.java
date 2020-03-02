package de.kisner.ca4j.util;

import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyUtils
{	
	public static KeyPair randomKeyPair()
	{
	    try
	    {
	      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
	      kpg.initialize(2048);
	      return kpg.generateKeyPair();
	    }
	    catch (final NoSuchAlgorithmException | InvalidParameterException e) {e.printStackTrace();return null;}
	}
}