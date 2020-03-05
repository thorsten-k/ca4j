package de.kisner.ca4j.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.ca4j.factory.ca4j.Ca4jAuthorityFactory;
import de.kisner.ca4j.factory.ca4j.Ca4jCertificateFactory;
import de.kisner.ca4j.factory.ca4j.Ca4jDistinguishedNameFactory;
import de.kisner.ca4j.interfaces.model.Ca4jAuthority;
import de.kisner.ca4j.interfaces.model.Ca4jCertificate;
import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;
import de.kisner.ca4j.interfaces.model.Ca4jSigningRequest;

public class Ca4jFactoryBuilder<CA extends Ca4jAuthority<DN,CERT>,
								DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate<CA,DN>,
								CSR extends Ca4jSigningRequest<DN>>
{
	final static Logger logger = LoggerFactory.getLogger(Ca4jFactoryBuilder.class);

	private final Class<CA> cCa;  public Class<CA> getClassCa() {return cCa;}
	private final Class<DN> cDn;  public Class<DN> getClassDn() {return cDn;}
	private final Class<CERT> cCert;  public Class<CERT> getClassCert() {return cCert;}

	public Ca4jFactoryBuilder(final Class<CA> cCa,
								final Class<DN> cDn,
								final Class<CERT> cCert)
	{
		this.cCa=cCa;
		this.cDn=cDn;
		this.cCert=cCert;
	}
	
	public Ca4jDistinguishedNameFactory<DN> dn() {return Ca4jDistinguishedNameFactory.instance(cDn);}
	public Ca4jAuthorityFactory<CA,DN,CERT> ca() {return new Ca4jAuthorityFactory<>(cCa);}
	public Ca4jCertificateFactory<CA,DN,CERT> cert() {return new Ca4jCertificateFactory<>(cCert);}
}