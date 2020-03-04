package de.kisner.ca4j.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.ca4j.factory.ca4j.Ca4jAuthorityFactory;
import de.kisner.ca4j.factory.ca4j.Ca4jDistinguishedNameFactory;
import de.kisner.ca4j.interfaces.Ca4jAuthority;
import de.kisner.ca4j.interfaces.Ca4jCertificate;
import de.kisner.ca4j.interfaces.Ca4jDistinguishedName;
import de.kisner.ca4j.interfaces.Ca4jSigningRequest;

public class Ca4jFactoryBuilder<CA extends Ca4jAuthority<DN,CERT>,
								DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate,
								CSR extends Ca4jSigningRequest<DN>>
{
	final static Logger logger = LoggerFactory.getLogger(Ca4jFactoryBuilder.class);

	private final Class<CA> cCa;  public Class<CA> getcCa() {return cCa;}
	private final Class<DN> cDn;  public Class<DN> getcDn() {return cDn;}

	public Ca4jFactoryBuilder(final Class<CA> cCa,
									final Class<DN> cDn)
	{
		this.cCa=cCa;
		this.cDn=cDn;
	}
	
	public Ca4jDistinguishedNameFactory<DN> dn() {return Ca4jDistinguishedNameFactory.instance(cDn);}
	public Ca4jAuthorityFactory<CA,DN,CERT> ca() {return new Ca4jAuthorityFactory<>(cCa);}
}