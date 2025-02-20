package de.kisner.ca4j.factory.ca4j;

import java.lang.reflect.InvocationTargetException;
import java.security.PrivateKey;

import de.kisner.ca4j.factory.x509.PemFactory;
import de.kisner.ca4j.interfaces.model.Ca4jAuthority;
import de.kisner.ca4j.interfaces.model.Ca4jCertificate;
import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;

public class Ca4jAuthorityFactory<CA extends Ca4jAuthority<DN,CERT>,
								DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate<CA,DN>>
{	
	private final Class<CA> cCa;
	
	public Ca4jAuthorityFactory(Class<CA> cCa)
	{
		this.cCa=cCa;
	}
	
	public CA build()
	{
		CA ca = null;
		try {ca = cCa.getDeclaredConstructor().newInstance();}
		catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (NoSuchMethodException e) {e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
		return ca;
	}
	
	public CA build(DN dn, PrivateKey privateKey)
	{
		CA ca = build();
		ca.setDn(dn);
		ca.setPemPrivate(PemFactory.toString(privateKey));
		return ca;
	}
}