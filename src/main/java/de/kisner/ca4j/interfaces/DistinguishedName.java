package de.kisner.ca4j.interfaces;

public interface DistinguishedName
{
	String getCommonName();
	void setCommonName(String commonName);

	String getOrganisation();
	void setOrganisation(String organisation);

	String getOrganisationalUnit();
	void setOrganisationalUnit(String organisationalUnit);

	String getState();
	void setState(String state);
	
	String getCoutry();
	void setCoutry(String coutry);
}