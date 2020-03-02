package de.kisner.ca4j.factory.ca4j;

import de.kisner.ca4j.model.Ca4jDistinguishedName;

public class Ca4jDistinguishedNameFactory
{	
	private Ca4jDistinguishedName dn;
	
	public static Ca4jDistinguishedNameFactory instance()
	{
		return new Ca4jDistinguishedNameFactory();
	}
	
	public Ca4jDistinguishedNameFactory()
	{
		dn = new Ca4jDistinguishedName();
	}
	
	public Ca4jDistinguishedName build() {return dn;}
	
	public Ca4jDistinguishedNameFactory cn(String cn) {dn.setCommonName(cn);return this;}
	public Ca4jDistinguishedNameFactory o(String o) {dn.setOrganisation(o);return this;}
	public Ca4jDistinguishedNameFactory ou(String ou) {dn.setOrganisationalUnit(ou);return this;}
	public Ca4jDistinguishedNameFactory s(String s) {dn.setState(s);return this;}
	public Ca4jDistinguishedNameFactory c(String c) {dn.setCoutry(c);return this;}
}