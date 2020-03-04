package de.kisner.ca4j.interfaces;

public interface Ca4jAuthority <DN extends Ca4jDistinguishedName>
{	
	DN getDn();
	void setDn(DN dn);
}