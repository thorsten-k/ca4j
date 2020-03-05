package de.kisner.ca4j.interfaces.model;

public interface Ca4jAuthority <DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate<?,DN>>
{	
	DN getDn();
	void setDn(DN dn);
	
	String getPemPrivate();
	void setPemPrivate(String pemPrivate);
	
	CERT getCertificate();
	void setCertificate(CERT cert);
}