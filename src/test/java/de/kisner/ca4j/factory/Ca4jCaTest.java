package de.kisner.ca4j.factory;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import de.kisner.ca4j.Ca4jBootstrap;
import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.factory.x509.PemFactory;
import de.kisner.ca4j.factory.x509.X509CertificateFactory;
import de.kisner.ca4j.factory.x509.X509CsrFactory;
import de.kisner.ca4j.model.Ca4jDistinguishedName;
import de.kisner.ca4j.util.KeyUtils;

public class Ca4jCaTest
{
	private KeyPair kpCa,kpHost;
	
	private Ca4jDistinguishedName dnCa, dnHost;
	
	public Ca4jCaTest()
	{
		
	}
	
	public void rootCa() throws Ca4jException
	{
		kpCa = KeyUtils.randomKeyPair();
		dnCa = Ca4jDnTest.dnCa();
		X509CertificateFactory<Ca4jDistinguishedName> fX509 = new X509CertificateFactory<>(kpCa,dnCa);
		X509Certificate certificate = fX509.signSelf();
		System.out.println(PemFactory.toString(certificate));
		PemFactory.toFile(certificate,new File("/Volumes/ramdisk/ca.p12"));
	}
	
	public void csr() throws Ca4jException
	{
		kpHost = KeyUtils.randomKeyPair();
		System.out.println(PemFactory.toString(kpHost.getPrivate()));
		dnHost = Ca4jDnTest.dnHost();
		X509CsrFactory<Ca4jDistinguishedName> fPkcs = new X509CsrFactory<>();
		PKCS10CertificationRequest csr = fPkcs.build(kpHost,dnHost);
//		System.out.println(PemFactory.toString(csr));
	}
	
	public void sign() throws Ca4jException
	{
		X509CertificateFactory<Ca4jDistinguishedName> fX509 = new X509CertificateFactory<>(kpCa,dnCa);
		X509Certificate certificate = fX509.sign(dnHost,kpHost.getPublic());
		System.out.println(PemFactory.toString(certificate));
		PemFactory.toFile(certificate,new File("/Volumes/ramdisk/host.p12"));
	}
	
	public void chain()
	{
//		https://www.digicert.com/kb/ssl-support/pem-ssl-creation.htm
	}
	
	public static void main(final String[] args) throws Ca4jException
	{
		Ca4jBootstrap.init();
		
		Ca4jCaTest cli = new Ca4jCaTest();
		cli.rootCa();
		cli.csr();
		cli.sign();
	}
}