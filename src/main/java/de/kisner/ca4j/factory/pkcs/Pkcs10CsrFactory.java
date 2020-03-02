package de.kisner.ca4j.factory.pkcs;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.factory.x500.X500NameFactory;
import de.kisner.ca4j.interfaces.DistinguishedName;
import de.kisner.ca4j.util.KeyUtils;

public class Pkcs10CsrFactory <DN extends DistinguishedName>
{
	private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

	private final X500NameFactory<DN> fX500Name; 
	
	public Pkcs10CsrFactory()
	{
		fX500Name = new X500NameFactory<>();
	}
	
	public PKCS10CertificationRequest build(DN dn) throws Ca4jException
	{
		try
		{
			KeyPair test = KeyUtils.randomKeyPair();

	    	X500Name x500Name = fX500Name.build(dn);
	    	ContentSigner signGen;
			signGen = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(test.getPrivate());
			PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(x500Name, test.getPublic());
	    	PKCS10CertificationRequest csr = builder.build(signGen);
	    	return csr;
		}
		catch (OperatorCreationException e) {throw new Ca4jException(e.getMessage());}
    	
	}
}