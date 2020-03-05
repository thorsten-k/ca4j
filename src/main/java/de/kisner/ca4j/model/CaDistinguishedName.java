package de.kisner.ca4j.model;

import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;

public class CaDistinguishedName implements Ca4jDistinguishedName
{
	private String country;
	@Override public String getCountry() {return country;}
	@Override public void setCountry(String country) {this.country = country;}
	
	private String state;
	@Override public String getState() {return state;}
	@Override public void setState(String state) {this.state = state;}
	
	private String organisationalUnit;
	@Override public String getOrganisationalUnit() {return organisationalUnit;}
	@Override public void setOrganisationalUnit(String organisationalUnit) {this.organisationalUnit = organisationalUnit;}

	private String organisation;
	@Override public String getOrganisation() {return organisation;}
	@Override public void setOrganisation(String organisation) {this.organisation = organisation;}

	private String commonName;
	@Override public String getCommonName() {return commonName;}
	@Override public void setCommonName(String commonName) {this.commonName = commonName;}

	
	public CaDistinguishedName()
	{
		
	}	
}