package de.kisner.ca4j.model;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public class Ca4jCertificateExtension
{
	private ASN1ObjectIdentifier oid;
	public ASN1ObjectIdentifier getOid() {return oid;}
	public void setOid(ASN1ObjectIdentifier oid) {this.oid = oid;}
	
	private boolean critical;
	public boolean isCritical() {return critical;}
	public void setCritical(boolean critical) {this.critical = critical;}

	private ASN1Encodable asn1;
	public ASN1Encodable getAsn1() {return asn1;}
	public void setAsn1(ASN1Encodable asn1) {this.asn1 = asn1;}
	
	public Ca4jCertificateExtension()
	{
		
	}

	@Override public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" oid:").append(oid);
		sb.append(" critical:").append(critical);
		sb.append(" asn1:").append(asn1.toString());
		return sb.toString();
	}
}