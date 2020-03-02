package de.kisner.ca4j.factory.ca4j;

import de.kisner.ca4j.model.DistinguishedName;

public class Ca4jDistinguishedNameFactory
{	
	private DistinguishedName dn;
	
	public static Ca4jDistinguishedNameFactory instance()
	{
		return new Ca4jDistinguishedNameFactory();
	}
	
	public Ca4jDistinguishedNameFactory()
	{
		dn = new DistinguishedName();
	}
	
	public DistinguishedName build() {return dn;}
	
	public Ca4jDistinguishedNameFactory cn(String cn) {dn.setCommonName(cn);return this;}
	public Ca4jDistinguishedNameFactory o(String o) {dn.setOrganisation(o);return this;}
	public Ca4jDistinguishedNameFactory ou(String ou) {dn.setOrganisationalUnit(ou);return this;}
	public Ca4jDistinguishedNameFactory s(String s) {dn.setState(s);return this;}
	public Ca4jDistinguishedNameFactory c(String c) {dn.setCoutry(c);return this;}
}