package de.kisner.ca4j.interfaces.facade;

import de.kisner.ca4j.interfaces.model.Ca4jAuthority;
import de.kisner.ca4j.interfaces.model.Ca4jCertificate;
import de.kisner.ca4j.interfaces.model.Ca4jDistinguishedName;
import de.kisner.ca4j.interfaces.model.Ca4jSigningRequest;

public interface Ca4jFacade <CA extends Ca4jAuthority<DN,CERT>,
								DN extends Ca4jDistinguishedName,
								CERT extends Ca4jCertificate<CA,DN>,
								CSR extends Ca4jSigningRequest<DN>>
{	
	
}