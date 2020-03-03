package de.kisner.ca4j.factory.ca4j;

import de.kisner.ca4j.interfaces.Ca4jDistinguishedName;

public class Ca4jDistinguishedNameFactory<DN extends Ca4jDistinguishedName>
{	
	private final Class<DN> c;
	private DN dn;
	
	public static <DN extends Ca4jDistinguishedName> Ca4jDistinguishedNameFactory<DN> instance(Class<DN> c)
	{
		return new Ca4jDistinguishedNameFactory<DN>(c);
	}
	
	public Ca4jDistinguishedNameFactory(Class<DN> c)
	{
		this.c=c;
	}
	
	public Ca4jDistinguishedNameFactory<DN> fluid()
	{
		try {dn = c.newInstance();}
		catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		return this;
	}
	
	public DN build() {return dn;}
	
	public Ca4jDistinguishedNameFactory<DN> cn(String cn) {dn.setCommonName(cn);return this;}
	public Ca4jDistinguishedNameFactory<DN> o(String o) {dn.setOrganisation(o);return this;}
	public Ca4jDistinguishedNameFactory<DN> ou(String ou) {dn.setOrganisationalUnit(ou);return this;}
	public Ca4jDistinguishedNameFactory<DN> s(String s) {dn.setState(s);return this;}
	public Ca4jDistinguishedNameFactory<DN> c(String c) {dn.setCoutry(c);return this;}
}