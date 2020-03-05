package de.kisner.ca4j.interfaces.model;

public interface Ca4jDistinguishedName
{
	String getCommonName();
	void setCommonName(String commonName);

	String getOrganisation();
	void setOrganisation(String organisation);

	String getOrganisationalUnit();
	void setOrganisationalUnit(String organisationalUnit);

	String getState();
	void setState(String state);
	
	String getCountry();
	void setCountry(String country);
}