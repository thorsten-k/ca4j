package de.kisner.ca4j.factory.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.cert.X509Certificate;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

public class PemCertificateFactory
{	

	public static String build(X509Certificate certificate)
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
	
	public static void save(X509Certificate certificate, File file)
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