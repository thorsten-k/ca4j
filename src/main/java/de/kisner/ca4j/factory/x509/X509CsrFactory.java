package de.kisner.ca4j.factory.x509;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.KeyPair;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.interfaces.Ca4jDistinguishedName;

public class X509CsrFactory <DN extends Ca4jDistinguishedName>
{
	private final X500NameFactory<DN> fX500Name; 
	
	public X509CsrFactory()
	{
		fX500Name = new X500NameFactory<>();
	}
	
	public PKCS10CertificationRequest build(KeyPair kpHost, DN dn) throws Ca4jException
	{
		try
		{
	    	X500Name x500Name = fX500Name.build(dn);
	    	ContentSigner cs = new JcaContentSignerBuilder(X509CertificateFactory.SIGNATURE_ALGORITHM).build(kpHost.getPrivate());
			PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(x500Name, kpHost.getPublic());
	    	PKCS10CertificationRequest csr = builder.build(cs);
	    	return csr;
		}
		catch (OperatorCreationException e) {throw new Ca4jException(e.getMessage());}
	}
	
	public PKCS10CertificationRequest build(File f) throws Ca4jException
	{
	    try
	    {
			Reader reader = new FileReader(f);
			PEMParser parser = new PEMParser(reader);
			Object o = parser.readObject();

			if (o instanceof PKCS10CertificationRequest)
			{
	            return (PKCS10CertificationRequest)o;
			}
			else
			{
	            throw new Ca4jException("File is a "+o.getClass().getName()+", but expected a "+PKCS10CertificationRequest.class.getName());
	        }
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
	}
}