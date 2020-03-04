package de.kisner.ca4j.factory;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.factory.ca4j.Ca4jDistinguishedNameFactory;
import de.kisner.ca4j.model.CaDistinguishedName;

public class Ca4jDnTest
{
	public Ca4jDnTest()
	{
		
	}
	
	public static CaDistinguishedName dnCa()
	{
		return Ca4jDistinguishedNameFactory.instance(CaDistinguishedName.class).fluid().cn("Ca4j").o("Github Ca4j").ou("Unit").s("X").c("Earth").toDn();
	}
	public static CaDistinguishedName dnHost()
	{
		return Ca4jDistinguishedNameFactory.instance(CaDistinguishedName.class).fluid().cn("192.168.1.16").o("Some Corporation").ou("Unit").s("X").c("Earth").toDn();
	}
	
	public void testDn()
	{
		CaDistinguishedName dn = Ca4jDnTest.dnCa();
		System.out.println(dn.toString());
	}
	
	public static void main(final String[] args)
	{
		Ca4jBootstrap.init();
		
		Ca4jDnTest cli = new Ca4jDnTest();
		cli.testDn();
	}
}