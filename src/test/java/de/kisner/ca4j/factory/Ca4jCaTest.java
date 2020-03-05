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
import de.kisner.ca4j.model.CaDistinguishedName;
import de.kisner.ca4j.util.KeyUtils;

public class Ca4jCaTest
{
	private KeyPair kpCa,kpHost;
	private X509Certificate cCa,cHost;
	
	private CaDistinguishedName dnCa, dnHost;
	
	public Ca4jCaTest()
	{
		
	}
	
	public void rootCa() throws Ca4jException
	{
		kpCa = KeyUtils.randomKeyPair();
		dnCa = Ca4jDnTest.dnCa();
		X509CertificateFactory<CaDistinguishedName> fX509 = new X509CertificateFactory<>(kpCa,dnCa);
		cCa = fX509.signSelf();
		System.out.println(PemFactory.toString(cCa));
		PemFactory.toFile(cCa,new File("/Volumes/ramdisk/ca.pem"));
		PemFactory.toFile(cCa,new File("/Volumes/ramdisk/ca.p12"));
	}
	
	public void csrFactory() throws Ca4jException
	{
		kpHost = KeyUtils.randomKeyPair();
		System.out.println(PemFactory.toString(kpHost.getPrivate()));
		dnHost = Ca4jDnTest.dnHost();
		X509CsrFactory<CaDistinguishedName> fPkcs = new X509CsrFactory<>();
		PKCS10CertificationRequest csr = fPkcs.build(kpHost,dnHost);
		System.out.println(PemFactory.toString(csr));
	}
	
	public void sign() throws Ca4jException
	{
		X509CertificateFactory<CaDistinguishedName> fX509 = new X509CertificateFactory<>(kpCa,dnCa);
		cHost = fX509.sign(dnHost,kpHost.getPublic());
		System.out.println(PemFactory.toString(cHost));
		PemFactory.toFile(cHost,new File("/Volumes/ramdisk/hostJava.p12"));
	}
	
	public void signFile() throws Ca4jException
	{		
		X509CsrFactory<CaDistinguishedName> fPkcs = new X509CsrFactory<>();
		PKCS10CertificationRequest csr = fPkcs.build(new File("/Volumes/ramdisk/csr.pem"));
//		System.out.println(PemFactory.toString(csr));	
		X509CertificateFactory<CaDistinguishedName> fX509 = new X509CertificateFactory<>(kpCa,dnCa);
		cHost = fX509.sign(csr.getSubject(),csr.getSubjectPublicKeyInfo());
		System.out.println(PemFactory.toString(cHost));
		PemFactory.toFile(cHost,new File("/Volumes/ramdisk/hostJava.p12"));
	}
	
	public void chain()
	{
		System.out.println("****************");
//		https://www.digicert.com/kb/ssl-support/pem-ssl-creation.htm
		System.out.println(PemFactory.toString(kpHost.getPrivate())
							+PemFactory.toString(cHost)
							+PemFactory.toString(cCa));
	}
	
	public static void main(final String[] args) throws Ca4jException
	{
		Ca4jBootstrap.init();
		
		Ca4jCaTest cli = new Ca4jCaTest();
		cli.rootCa();
//		cli.sign();
		cli.signFile();
//		cli.chain();
	}
}