package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="recipient-collection", namespace="urn:xtk:queryDef")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipientCollection {
	
	@XmlElement(name="recipient", namespace="urn:xtk:queryDef")
	private List<Recipient> recipient;
	
	public RecipientCollection() {
		recipient = new ArrayList<Recipient>();
	}

	public List<Recipient> getRecipient() {
		return recipient;
	}

	public void setRecipient(List<Recipient> recipient) {
		this.recipient = recipient;
	}
}
