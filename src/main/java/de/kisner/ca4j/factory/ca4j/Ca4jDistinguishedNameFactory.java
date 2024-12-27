package de.kisner.ca4j.factory.ca4j;

import java.lang.reflect.InvocationTargetException;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;

public class Ca4jDistinguishedNameFactory<DN extends Ca4jDistinguishedName>
{	
	private final Class<DN> c;
	private DN dn;
	
	public static <DN extends Ca4jDistinguishedName> Ca4jDistinguishedNameFactory<DN> instance(Class<DN> c)
	{
		return new Ca4jDistinguishedNameFactory<DN>(c);
	}
	
	public Ca4jDistinguishedNameFactory(Class<DN> c)
	{
		this.c=c;
	}
	
	public DN toDn() {return dn;}
	public Ca4jDistinguishedNameFactory<DN> fluid()
	{
		dn = build();
		return this;
	}
	
	public DN build()
	{
		DN dn = null;
		try {dn = c.getDeclaredConstructor().newInstance();}
		catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (NoSuchMethodException e) {e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
		return dn;
	}
	
	public Ca4jDistinguishedNameFactory<DN> cn(String cn) {dn.setCommonName(cn);return this;}
	public Ca4jDistinguishedNameFactory<DN> o(String o) {dn.setOrganisation(o);return this;}
	public Ca4jDistinguishedNameFactory<DN> ou(String ou) {dn.setOrganisationalUnit(ou);return this;}
	public Ca4jDistinguishedNameFactory<DN> s(String s) {dn.setState(s);return this;}
	public Ca4jDistinguishedNameFactory<DN> c(String c) {dn.setCountry(c);return this;}
	
	public DN build(PKCS10CertificationRequest csr)
	{
		DN dn = build();
		for(RDN r : csr.getSubject().getRDNs())
		{
		
			System.out.println("r: "+r.getFirst().getType()+" "+r.getFirst().getValue());
		}
		System.out.println("X: "+csr.getSubject().toString());
		dn.setCommonName(csr.getSubject().toString());
		return dn;
	}
}