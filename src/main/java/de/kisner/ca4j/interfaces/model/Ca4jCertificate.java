package de.kisner.ca4j.interfaces.model;

import java.util.Date;

public interface Ca4jCertificate<CA extends Ca4jAuthority<DN,?>,
								DN extends Ca4jDistinguishedName>
{	
	DN getDn();
	void setDn(DN dn);
	
	String getPemX509();
	void setPemX509(String pemX509);
	
	String getTxtSerial();
	void setTxtSerial(String txtSerial);
	
	Date getValidFrom();
	void setValidFrom(Date validFrom);

	Date getValidUntil();
	void setValidUntil(Date validUntil);
	
	CA getSignedBy();
	void setSignedBy(CA signedBy);
}