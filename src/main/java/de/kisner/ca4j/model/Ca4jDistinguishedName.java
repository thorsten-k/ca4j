package de.kisner.ca4j.model;

import de.kisner.ca4j.interfaces.DistinguishedName;

public class Ca4jDistinguishedName implements DistinguishedName
{
	private String commonName;
	@Override public String getCommonName() {return commonName;}
	@Override public void setCommonName(String commonName) {this.commonName = commonName;}

	private String organisation;
	@Override public String getOrganisation() {return organisation;}
	@Override public void setOrganisation(String organisation) {this.organisation = organisation;}

	private String organisationalUnit;
	@Override public String getOrganisationalUnit() {return organisationalUnit;}
	@Override public void setOrganisationalUnit(String organisationalUnit) {this.organisationalUnit = organisationalUnit;}

	private String state;
	@Override public String getState() {return state;}
	@Override public void setState(String state) {this.state = state;}
	
	private String coutry;
	@Override public String getCoutry() {return coutry;}
	@Override public void setCoutry(String coutry) {this.coutry = coutry;}
	
	public Ca4jDistinguishedName()
	{
		
	}	
}