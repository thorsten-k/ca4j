package de.kisner.ca4j.test;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.factory.ca4j.Ca4jDistinguishedNameFactory;
import de.kisner.ca4j.model.Ca4jDistinguishedName;

public class DistinguishedNameTest
{
	public DistinguishedNameTest()
	{
		
	}
	
	public static Ca4jDistinguishedName dn()
	{
		return Ca4jDistinguishedNameFactory.instance().cn("Snake Oil").o("Some Corporation").ou("Unit").s("X").c("Earth").build();
	}
	
	public void testDn()
	{
		Ca4jDistinguishedName dn = DistinguishedNameTest.dn();
		System.out.println(dn.toString());
	}
	
	public static void main(final String[] args)
	{
		Ca4jBootstrap.init();
		
		DistinguishedNameTest cli = new DistinguishedNameTest();
		cli.testDn();
	}
}