package de.kisner.ca4j.factory.x509;

import org.bouncycastle.asn1.x500.X500Name;

import de.kisner.ca4j.interfaces.Ca4jDistinguishedName;

public class X500NameFactory <DN extends Ca4jDistinguishedName>
{  
	public X500NameFactory()
	{
	}
	
	public X500Name build(DN dn)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("CN=").append(dn.getCommonName());
		sb.append(", O=").append(dn.getOrganisation());
		
		X500Name x500 = new X500Name(sb.toString());
		return x500;
	}
}