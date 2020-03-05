package de.kisner.ca4j.factory.ca4j;

import java.security.cert.X509Certificate;

import de.kisner.ca4j.factory.x509.PemFactory;
import de.kisner.ca4j.interfaces.model.Ca4jAuthority;
import de.kisner.ca4j.interfaces.model.Ca4jCertificate;
import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;

public class Ca4jCertificateFactory<CA extends Ca4jAuthority<DN,CERT>,
								DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate<CA,DN>>
{	
	private final Class<CERT> cCERT;
	
	public Ca4jCertificateFactory(Class<CERT> cCERT)
	{
		this.cCERT=cCERT;
	}
	
	public CERT build()
	{
		CERT ca = null;
		try {ca = cCERT.newInstance();}
		catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		return ca;
	}
	
	public CERT build(CA ca, DN dn, X509Certificate x509)
	{
		CERT cert = build();
		cert.setDn(dn);
		cert.setPemX509(PemFactory.toString(x509));
		cert.setTxtSerial(x509.getSerialNumber().toString());
		cert.setValidFrom(x509.getNotBefore());
		cert.setValidUntil(x509.getNotAfter());
		cert.setSignedBy(ca);
		return cert;
	}
}