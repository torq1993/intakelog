package com.pepsi.onenetwork.models.destination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.avutils.jmapper.annotations.JMap;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
@Getter
@Setter
@SuppressWarnings("unused")
public class SalesOrder implements Serializable {

	private static final long serialVersionUID = -4148737634218573218L;
	@JMap("actionName")
	private String actionName;
	@JMap("creationOrgEnterpriseName")
	private String creationOrgEnterpriseName;
	@JMap("creationOrgName")
	private String creationOrgName;
	@JMap("owningOrgEnterpriseName")
	private String owningOrgEnterpriseName;
	@JMap("owningOrgName")
	private String owningOrgName;
	@JMap("buyingOrgEnterpriseName")
	private String buyingOrgEnterpriseName;
	@JMap("buyingOrgName")
	private String buyingOrgName;
	@JMap("sellingOrgEnterpriseName")
	private String sellingOrgEnterpriseName;
	@JMap("sellingOrgName")
	private String sellingOrgName;
	@JMap("customerName")
	private String customerName;
	@JMap("shipFromOrgEnterpriseName")
	private String shipFromOrgEnterpriseName;
	@JMap("shipFromOrgName")
	private String shipFromOrgName;
	private String orderNumber;
	private String shipToSiteEnterpriseName;
	private String shipToSiteOrganizationName;
	private String shipToSiteName;
	private String extShipToSiteName;
	private String shipToAddressStreet1;
	private String shipToAddressStreet2;
	private String shipToAddressCity;
	private String shipToAddressState;
	private String shipToAddressZip;
	private String shipToAddressCountry;
	private String shipFromSiteEnterpriseName;
	private String shipFromSiteOrganizationName;
	private String shipFromSiteName;
	private String extShipFromSiteName;
	private String shipFromAddressStreet1;
	private String shipFromAddressStreet2;
	private String shipFromAddressCity;
	private String shipFromAddressState;
	private String shipFromAddressZip;
	private String shipFromAddressCountry;
	private String transModeName;
	private String fobCode;
	private String FOBPoint;
	private Date RequestShipDate;
	private Date RequestDeliveryDate;
	private String LineNumber;
	private String ItemName;
	private String ExtItemName;
	private String ExtItemDesc;
	private String LineType;
	private double RequestQuantity;
	private double UnitPrice;
	private String QuantityUOM;
	private double LineAmount;
	private String Currency;
	private String BuyerCode;
	private String PlannerCode;
	private boolean IsSpot;
	private String BillToAddressStreet1;
	private String BillToAddressStreet2;
	private String BillToAddressCity;
	private String BillToAddressState;
	private String BillToAddressZip;
	private String BillToAddressCountry;
	private String RemitToAddressStreet1;
	private String RemitToAddressStreet2;
	private String RemitToAddressCity;
	private String RemitToAddressState;
	private String RemitToAddressZip;
	private String RemitToAddressCountry;
	private String PurchasingAddressStreet1;
	private String PurchasingAddressStreet2;
	private String PurchasingAddressCity;
	private String PurchasingAddressState;
	private String PurchasingAddressZip;
	private String PurchasingAddressCountry;
	private String RequestScheduleAction;
	private String RequestScheduleNumber;
	private String ParentOrderOrderNumber;
	private String ExtParentOrderNumber;
	private String DeliveryScheduleNumber;
	private double AgreedQuantity;
	private double PromiseQuantity;
	private double ShippedQuantity;
	private double ReceivedQuantity;
	private Date PromiseDeliveryDate;
	private Date PromiseShipDate;
	private int Priority;
	private String ContractNumber;
	private String ContractTermsNumber;
	private String ContractLineLineNumber;
	private boolean IsConsignment;
	private boolean IsEmergency;
	private boolean IsExpedite;
	private boolean IsPromotion;
	private boolean IsVMI;
	private String PaymentTermsCode;
	private String BPONumber;
	private String BPOLineNumber;
	private Date ActualDeliveryDate;
	private Date ActualReceiptDate;
	private Date ActualShipDate;
	private Date AgreedDeliveryDate;
	private Date AgreedShipDate;
	private String IncoTerms;
	private boolean IsDisableDeliveryDateCompliance;
	private String ServiceLevelName;
	private String OwningSiteEnterpriseName;
	private String OwningSiteOrganizationName;
	private String OwningSiteName;
	private String LnIncoTerms;
	private boolean LnIsDisableDeliveryCompliance;
	private boolean IsDutyFree;
	private String HTSCode;
	private String DutyFreeHTSCode;
	private String CountryOfManufacturing;
	private boolean IsLnExpedite;
	private String LnServiceLevelServiceLevelName;
	private boolean IsSourceInspectionRequired;
	private String SourceInspectionType;
	private String GLAccount;
	private Date PlannedDeliveryDate;
	private String RsServiceLevelServiceLevelName;
	private boolean RsIsExpedite;
	private String VendorName;
	private double TotalAmount;
	private String OrderCurrency;
	private String WmsOrderNumber;
	private String OmoOrderNumber;
	private String OrderPriority;
	private String OrderServiceClassCode;
	private String SettlementCurrency;
	private String PaymentMethod;
	private int Usance;
	private String OrderDescription;
	private String EndCustomerOrderNo;
	private String LineServiceClassCode;
	private double LineTotalRequestQtyAmount;
	private String LineTotalRequestQtyUOM;
	private String LineEndCustomerOrderNo;
	private String RequestScheduleOrderPriority;
	private String FinalDestinationSiteEnterpriseName;
	private String FinalDestinationSiteOrganizationName;
	private String FinalDestinationSiteName;
	private String FinalDestinationAddressStreet1;
	private String FinalDestinationAddressStreet2;
	private String FinalDestinationAddressCity;
	private String FinalDestinationAddressState;
	private String FinalDestinationAddressZip;
	private String FinalDestinationAddressCountry;
	private String RequestScheduleServiceClassCode;
	private double RequestScheduleUnitPriceAmount;
	private String RequestScheduleUnitPriceUOM;
	private String RequestScheduleIncoTerms;
	private String RequestScheduleTransModeName;
	private String RequestScheduleTaxCode;
	private String FCCReasonCode;
	private String FDAReasonCode;
	private String EndCustomerName;
	private double InsuranceAmount;
	private double OtherCostAmount;
	private double TaxAmount;
	private double CustomsDutyAmount;
	private String ExternalCarrierName;
	private String PalletType;
	private double LoadingDimensionsHeight;
	private double LoadingDimensionsLength;
	private String LoadingDimensionsUOM;
	private double LoadingDimensionsWidth;
	private String SellingAgent1EnterpriseName;
	private String SellingAgent1Name;
	private String SellingAgent2EnterpriseName;
	private String SellingAgent2Name;
	private String SellingAgent3EnterpriseName;
	private String SellingAgent3Name;
	private String SellingAgent4EnterpriseName;
	private String SellingAgent4Name;
	private String BuyingAgent1EnterpriseName;
	private String BuyingAgent1Name;
	private String BuyingAgent2EnterpriseName;
	private String BuyingAgent2Name;
	private String BuyingAgent3EnterpriseName;
	private String BuyingAgent3Name;
	private String BuyingAgent4EnterpriseName;
	private String BuyingAgent4Name;
	private String OrderMgmtOrgEnterpriseName;
	private String OrderMgmtOrgName;
	private String ItemEnterpriseName;
	private String PackingResourceName;
	private String PackingResourceOrgEnterpriseName;
	private String PackingResourceOrgName;
	private String ExtPackingResourceName;
	private String OMSEquipment;
	private String OrderClassification;
}
