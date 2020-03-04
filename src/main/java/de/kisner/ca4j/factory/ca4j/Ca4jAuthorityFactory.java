package de.kisner.ca4j.factory.ca4j;

import de.kisner.ca4j.interfaces.Ca4jAuthority;
import de.kisner.ca4j.interfaces.Ca4jDistinguishedName;

public class Ca4jAuthorityFactory<CA extends Ca4jAuthority<DN>,
								DN extends Ca4jDistinguishedName>
{	
	private final Class<CA> cCa;
	
	public Ca4jAuthorityFactory(Class<CA> cCa)
	{
		this.cCa=cCa;
	}
	
	public CA build()
	{
		CA ca = null;
		try {ca = cCa.newInstance();}
		catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		return ca;
	}
}