package de.kisner.ca4j.factory;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.factory.txt.PemCertificateFactory;
import de.kisner.ca4j.factory.x500.X509CertificateFactory;
import de.kisner.ca4j.model.Ca4jDistinguishedName;
import de.kisner.ca4j.test.DistinguishedNameTest;
import de.kisner.ca4j.util.KeyUtils;

public class X509CertificateFactoryTest
{
	public X509CertificateFactoryTest()
	{
		
	}
	
	public void testDn() throws Ca4jException
	{
		KeyPair keys = KeyUtils.randomKeyPair();
		Ca4jDistinguishedName dn = DistinguishedNameTest.dn();
		X509CertificateFactory<Ca4jDistinguishedName> fX509 = new X509CertificateFactory<>(keys,dn);
		X509Certificate certificate = fX509.selfSigned();
		System.out.println(PemCertificateFactory.build(certificate));
		PemCertificateFactory.save(certificate,new File("/Volumes/ramdisk/ca.p12"));
	}
	
	public static void main(final String[] args) throws Ca4jException
	{
		Ca4jBootstrap.init();

		X509CertificateFactoryTest cli = new X509CertificateFactoryTest();
		cli.testDn();
	}
}