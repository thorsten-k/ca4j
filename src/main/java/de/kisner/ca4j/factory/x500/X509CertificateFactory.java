package de.kisner.ca4j.factory.x500;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import de.kisner.ca4j.exception.Ca4jException;
import de.kisner.ca4j.factory.ca4j.Ca4jCertificateExtensionFactory;
import de.kisner.ca4j.interfaces.DistinguishedName;
import de.kisner.ca4j.model.Ca4jCertificateExtension;

public class X509CertificateFactory <DN extends DistinguishedName>
{
	private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	  
	private JcaX509ExtensionUtils x509ExtensionUtil;
	  
	private DN caDn;
	
	private KeyPair caKeyPair;
	  
	private BigInteger serialNumber;
	  
	private X500NameFactory<DN> fX500;
	  
	public X509CertificateFactory(KeyPair caKeyPair, DN caDn)
	{
		this.caDn=caDn;
		this.caKeyPair=caKeyPair;
		
		try {x509ExtensionUtil = new JcaX509ExtensionUtils();}
		catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		
		serialNumber = new BigInteger(128, new SecureRandom());
		fX500 = new X500NameFactory<>();
	}
	
	public X509Certificate selfSigned() throws Ca4jException
	{
		List<Ca4jCertificateExtension> extensions = new ArrayList<>();
		extensions.add(Ca4jCertificateExtensionFactory.keyUsage(KeyUsage.keyCertSign,KeyUsage.cRLSign));
		extensions.add(Ca4jCertificateExtensionFactory.build(Extension.basicConstraints,false,new BasicConstraints(true)));
		return build(caDn,caKeyPair.getPublic(),extensions);
	}
	
	public X509Certificate build(DN certDn, PublicKey certPubKey, List<Ca4jCertificateExtension> extensions) throws Ca4jException
	{
		try
		{
			X500Name x500Ca = fX500.build(caDn);
			X500Name x500Cert = fX500.build(certDn);
			
			Date validFrom  = Date.from(ZonedDateTime.now().toInstant());
			Date validUntil = Date.from(ZonedDateTime.now().plusYears(1).toInstant());
			
			SubjectPublicKeyInfo sbki = SubjectPublicKeyInfo.getInstance(certPubKey.getEncoded());
	      
			X509v3CertificateBuilder cB = new X509v3CertificateBuilder(x500Ca,serialNumber,validFrom,validUntil,x500Cert,sbki);
			cB.addExtension(Extension.authorityKeyIdentifier, false, x509ExtensionUtil.createAuthorityKeyIdentifier(caKeyPair.getPublic()));
			cB.addExtension(Extension.subjectKeyIdentifier, false, x509ExtensionUtil.createSubjectKeyIdentifier(certPubKey));

			for (Ca4jCertificateExtension e : extensions)
			{
				cB.addExtension(e.getOid(),e.isCritical(),e.getAsn1());
			}

			ContentSigner cs = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(caKeyPair.getPrivate());
			X509CertificateHolder holder = cB.build(cs);
			X509Certificate certificate = new JcaX509CertificateConverter().getCertificate(holder);

			return certificate;
		}
	    catch (CertIOException | OperatorCreationException | CertificateException e) {throw new Ca4jException(e.getMessage());}
	}
}