package de.kisner.ca4j.factory.x509;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

public class PemFactory
{	
	public static String toString(X509Certificate certificate)
	{
		try
		{
			StringWriter sw = new StringWriter();
			JcaPEMWriter writer = new JcaPEMWriter(sw);
			writer.writeObject(certificate);
			writer.flush();
			return sw.toString();
		
		}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public static String toString(PrivateKey privateKey)
	{
	    try
	    {
	    	StringWriter sw = new StringWriter();
	    	JcaPEMWriter writer = new JcaPEMWriter(sw);
	        writer.writeObject(privateKey);
	        writer.flush();
	        return sw.toString();
	    }
	    catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public static String toString(PKCS10CertificationRequest csr)
	{
		try
		{
			StringWriter sw = new StringWriter();
			JcaPEMWriter writer = new JcaPEMWriter(sw);
			writer.writeObject(csr);
			writer.flush();
			return sw.toString();
		
		}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public static void toFile(X509Certificate certificate, File file)
	{
		try
		{
			BufferedWriter fw = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8,StandardOpenOption.CREATE);
			JcaPEMWriter writer = new JcaPEMWriter(fw);
			writer.writeObject(certificate);
			writer.flush();
		}
		catch (IOException e) {e.printStackTrace();}
	}
}