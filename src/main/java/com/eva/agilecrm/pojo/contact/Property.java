package com.eva.agilecrm.pojo.contact;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property {
	private String type;
	private String name;
	private String value;
	private String subtype;
	public Property(String type, String name, String value, String subtype) {
//		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.subtype = subtype;
	}
	
	public Property() {
		
	}
	
	
	
}
