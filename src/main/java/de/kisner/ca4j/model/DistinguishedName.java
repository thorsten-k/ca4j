package de.kisner.ca4j.model;

public class DistinguishedName
{
	private String commonName;
	public String getCommonName() {return commonName;}
	public void setCommonName(String commonName) {this.commonName = commonName;}

	private String organisation;
	public String getOrganisation() {return organisation;}
	public void setOrganisation(String organisation) {this.organisation = organisation;}

	private String organisationalUnit;
	public String getOrganisationalUnit() {return organisationalUnit;}
	public void setOrganisationalUnit(String organisationalUnit) {this.organisationalUnit = organisationalUnit;}

	private String state;
	public String getState() {return state;}
	public void setState(String state) {this.state = state;}
	
	private String coutry;
	public String getCoutry() {return coutry;}
	public void setCoutry(String coutry) {this.coutry = coutry;}
	
	public DistinguishedName()
	{
		
	}	
}