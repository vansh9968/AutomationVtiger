package com.eva.agilecrm.pojo.contact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponsePojo {
	
	    public boolean isIs_lead_converted() {
		return is_lead_converted;
	}
	public void setIs_lead_converted(boolean is_lead_converted) {
		this.is_lead_converted = is_lead_converted;
	}
	public boolean isIs_duplicate_existed() {
		return is_duplicate_existed;
	}
	public void setIs_duplicate_existed(boolean is_duplicate_existed) {
		this.is_duplicate_existed = is_duplicate_existed;
	}
	public boolean isIs_duplicate_verification_failed() {
		return is_duplicate_verification_failed;
	}
	public void setIs_duplicate_verification_failed(boolean is_duplicate_verification_failed) {
		this.is_duplicate_verification_failed = is_duplicate_verification_failed;
	}
	public boolean isIs_client_import() {
		return is_client_import;
	}
	public void setIs_client_import(boolean is_client_import) {
		this.is_client_import = is_client_import;
	}
		private long id;
	    private String type;
	    private int created_time;
	    private int updated_time;
	    private int last_contacted;
	    private int last_emailed;
	    private int last_campaign_emaild;
	    private int last_called;
	    private int viewed_time;
	    private ViewedPojo viewed;
	    private int star_value;
	    private int lead_score;
	    private String klout_score;
	    private ArrayList<String> tags;
	    private ArrayList<TagsWithTimePojo> tagsWithTime;
	    private ArrayList<Property> properties;
	    private ArrayList<Object> campaignStatus;
	    private String entity_type;
	    private String source;
		private ArrayList<Object> unsubscribeStatus;
	    private ArrayList<Object> emailBounceStatus;
	    private int formId;
	    private ArrayList<Object> browserId;
	    private int lead_source_id;
	    private int lead_status_id;
	    private boolean is_lead_converted;
	    private int lead_converted_time;
	    private boolean is_duplicate_existed;
	    private int trashed_time;
	    private int restored_time;
	    private boolean is_duplicate_verification_failed;
	    private boolean is_client_import;
	    private boolean concurrent_save_allowed;
	    private OwnerPojo owner;

	
		
	
	
}
