package de.kisner.ca4j.interfaces.model;

public interface Ca4jSigningRequest <DN extends Ca4jDistinguishedName>
{	
	DN getDn();
	void setDn(DN dn);
}