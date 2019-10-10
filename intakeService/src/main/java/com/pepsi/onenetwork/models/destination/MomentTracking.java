package com.pepsi.onenetwork.models.destination;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@SuppressWarnings("unused")
public class MomentTracking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994785833091566738L;

	private String movementNumber;
	private Date evtDate;
	private String evtMessage;
	private String evtLocation;
	private String countrty;
	private String street1;
	private String street2;
	private String street3;
	private String state;
	private String zip;
	private String city;
	private String splc;
	private double currentPositionLatitude;
	private double currentPositionLongitude;
	private String transportationControllingEnterpriseName;
	private String transportationControllingOrganizationName;
	private String currentCarrierEnterpriseName;
	private String currentCarrierOrganizationName;
	private double insideTemperature;
	private double outsideTemperature;
	private String eventAttributes;

	/* String Enum For Moment type */
	private String evtType;
	private String reasonCode;

}
