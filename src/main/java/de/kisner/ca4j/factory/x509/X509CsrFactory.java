package de.kisner.ca4j.factory.x509;

import java.security.KeyPair;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.interfaces.DistinguishedName;

public class X509CsrFactory <DN extends DistinguishedName>
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
}