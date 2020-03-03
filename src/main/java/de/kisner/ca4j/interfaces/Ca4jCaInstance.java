package de.kisner.ca4j.interfaces;

public interface Ca4jCaInstance <DN extends Ca4jDistinguishedName>
{	
	DN getDn();
	void setDn(DN dn);
}