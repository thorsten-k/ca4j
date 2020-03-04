package de.kisner.ca4j.interfaces;

public interface Ca4jAuthority <DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate>
{	
	DN getDn();
	void setDn(DN dn);
}