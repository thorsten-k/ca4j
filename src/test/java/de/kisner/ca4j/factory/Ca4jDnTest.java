package de.kisner.ca4j.factory;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.factory.ca4j.Ca4jDistinguishedNameFactory;
import de.kisner.ca4j.model.Ca4jDistinguishedName;

public class Ca4jDnTest
{
	public Ca4jDnTest()
	{
		
	}
	
	public static Ca4jDistinguishedName dnCa()
	{
		return Ca4jDistinguishedNameFactory.instance().cn("Snake Oil").o("Some Corporation").ou("Unit").s("X").c("Earth").build();
	}
	public static Ca4jDistinguishedName dnHost()
	{
		return Ca4jDistinguishedNameFactory.instance().cn("192.168.1.16").o("Some Corporation").ou("Unit").s("X").c("Earth").build();
	}
	
	public void testDn()
	{
		Ca4jDistinguishedName dn = Ca4jDnTest.dnCa();
		System.out.println(dn.toString());
	}
	
	public static void main(final String[] args)
	{
		Ca4jBootstrap.init();
		
		Ca4jDnTest cli = new Ca4jDnTest();
		cli.testDn();
	}
}