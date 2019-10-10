package com.pepsi.onenetwork.models.destination;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
@Getter
@Setter
@SuppressWarnings("unused")
public class TenderAccepted {

	private String enterpriseName;
	private String organizationName;
	private String evtDate;
	private String evtType;
	private String accept;
	private String appointmentCancelled;
	private String evtMessage;
	private String evtLocation;
	private String country;
	private String street1;
	private String street2;
	private String street3;
	private String state;
	private String zip;
	private String city;
	private String reasonCode;
	private String splc;
	private String customEvtType;
	private String transportationControllingEnterpriseName;
	private String transportationControllingOrganizationName;
	private String pro;
	private String bol;
	private String receivedQty;
	private String receivedQtyUOM;
}