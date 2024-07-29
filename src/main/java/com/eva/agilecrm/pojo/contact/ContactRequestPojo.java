package com.eva.agilecrm.pojo.contact;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestPojo {

	private String star_value;
	private String lead_score;
	private List<String> tags;
	private List<Property> properties;

	public ContactRequestPojo(String star_value, String lead_score, List<String> tags, List<Property> properties) {
		this.star_value = star_value;
		this.lead_score = lead_score;
		this.tags = tags;
		this.properties = properties;
	}

	

}
