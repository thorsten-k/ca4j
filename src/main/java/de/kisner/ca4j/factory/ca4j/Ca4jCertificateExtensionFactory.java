package de.kisner.ca4j.factory.ca4j;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Extension;

import de.kisner.ca4j.model.Ca4jCertificateExtension;

public class Ca4jCertificateExtensionFactory
{	

	public static Ca4jCertificateExtension build(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable asn1)
	{
		Ca4jCertificateExtension extension = new Ca4jCertificateExtension();
		extension.setOid(oid);
		extension.setCritical(critical);
		extension.setAsn1(asn1);
		return extension;
	}
	
	public static Ca4jCertificateExtension keyUsage(Integer... list)
	{
		ASN1ObjectIdentifier oid = Extension.keyUsage;
		ASN1Encodable asn1 = new org.bouncycastle.asn1.x509.KeyUsage(toKeyUsageInt(list));
		
		return build(oid,false,asn1);
	}
	
	private static int toKeyUsageInt(Integer... list)
	{
		int result = 0;
		for (Integer usage : list)
		{
			result = result | usage;
		}
		return result;
	}
}