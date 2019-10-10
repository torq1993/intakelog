package com.pepsi.onenetwork.models.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@Getter
@Setter
@SuppressWarnings("unused")
public class SourceModel {
	private static final long serialVersionUID = -4148737634217322345L;
	private String actionName;
	private String creationOrgEnterpriseName;
	private String creationOrgName;
	private String owningOrgEnterpriseName;
	private String owningOrgName;
	private String buyingOrgEnterpriseName;
	private String buyingOrgName;
	private String sellingOrgEnterpriseName;
	private String sellingOrgName;
	private String customerName;
	private String shipFromOrgEnterpriseName;
	private String shipFromOrgName;
	public SourceModel() {
		
	}
}
