package de.kisner.ca4j.factory;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.factory.pkcs.Pkcs10CsrFactory;
import de.kisner.ca4j.factory.txt.PemCertificateFactory;
import de.kisner.ca4j.model.Ca4jDistinguishedName;
import de.kisner.ca4j.test.DistinguishedNameTest;

public class Pkcs10CsrFactoryTest
{
	public Pkcs10CsrFactoryTest()
	{
		
	}
	
	public void testDn() throws Ca4jException
	{
		Ca4jDistinguishedName dn = DistinguishedNameTest.dnWww();
		Pkcs10CsrFactory<Ca4jDistinguishedName> fPkcs = new Pkcs10CsrFactory<>();
		PKCS10CertificationRequest csr = fPkcs.build(dn);
		System.out.println(PemCertificateFactory.build(csr));
	}
	
	public static void main(final String[] args) throws Ca4jException
	{
		Ca4jBootstrap.init();

		Pkcs10CsrFactoryTest cli = new Pkcs10CsrFactoryTest();
		cli.testDn();
	}
}