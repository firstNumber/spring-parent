package com.weilai.appuser.core.model;

import java.io.Serializable;

public class OrderShort implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String globalOrderNum;
	private String moduleType;
	private Integer orderType;
	private Integer reverseStatus;
	private String positiveGlobalOrderNum;
	private String batchNum;
	private String packageNum;
	private Integer packageType;
	private Integer status;
	private Integer goodsStatus;
	private String storageNo;
	private java.util.Date createTime;
	private Integer driverId;
	private String driverPhone;
	private String driverName;
	private String agentNo;
	private String shipperCode;
	private String vehicleLicenseNum;
	private String sender;
	private String senderPhone;
	private String sendProvince;
	private String sendCity;
	private String sendArea;
	private String sendDigest;
	private String sendAdd;
	private Float sendLongitude;
	private Float sendLatitude;
	private String receiver;
	private String receiverPhone;
	private String receiveProvince;
	private String receiveCity;
	private String receiveArea;
	private String receiveDigest;
	private String receiveAdd;
	private Float receiveLongitude;
	private Float receiveLatitude;
	private Integer disreceDistance;
	private String orderNum;
	private String dkhCode;
	private String origin;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal damagedAmount;
	private java.math.BigDecimal finalPrice;
	private String remark;
	private String memo;
	private java.util.Date overTime;
	private java.util.Date waitSettlementTime;
	private java.util.Date settlementTime;
	private java.util.Date updateTime;
	private Integer goodsLength;
	private Integer goodsWidth;
	private Integer goodsHeight;
	private Integer goodsTotalWeight;
	private String cargoName;
	private String deliveryType;
	private Float deliveryLongitude;
	private Float deliveryLatitude;
	private java.util.Date takeOrderTime;
	private java.util.Date takeGoodsTime;
	private java.util.Date payTime;
	private java.math.BigDecimal globalnodePrice;
	private java.util.Date confirmReceiptTime;
	private java.util.Date packageTime;
	private java.util.Date robPoolTime;
	private Integer goodsTotalVolume;
	private Integer effThird;
	private Integer effRecp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGlobalOrderNum() {
		return globalOrderNum;
	}

	public void setGlobalOrderNum(String globalOrderNum) {
		this.globalOrderNum = globalOrderNum;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getReverseStatus() {
		return reverseStatus;
	}

	public void setReverseStatus(Integer reverseStatus) {
		this.reverseStatus = reverseStatus;
	}

	public String getPositiveGlobalOrderNum() {
		return positiveGlobalOrderNum;
	}

	public void setPositiveGlobalOrderNum(String positiveGlobalOrderNum) {
		this.positiveGlobalOrderNum = positiveGlobalOrderNum;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getStorageNo() {
		return storageNo;
	}

	public void setStorageNo(String storageNo) {
		this.storageNo = storageNo;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getVehicleLicenseNum() {
		return vehicleLicenseNum;
	}

	public void setVehicleLicenseNum(String vehicleLicenseNum) {
		this.vehicleLicenseNum = vehicleLicenseNum;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSendProvince() {
		return sendProvince;
	}

	public void setSendProvince(String sendProvince) {
		this.sendProvince = sendProvince;
	}

	public String getSendCity() {
		return sendCity;
	}

	public void setSendCity(String sendCity) {
		this.sendCity = sendCity;
	}

	public String getSendArea() {
		return sendArea;
	}

	public void setSendArea(String sendArea) {
		this.sendArea = sendArea;
	}

	public String getSendDigest() {
		return sendDigest;
	}

	public void setSendDigest(String sendDigest) {
		this.sendDigest = sendDigest;
	}

	public String getSendAdd() {
		return sendAdd;
	}

	public void setSendAdd(String sendAdd) {
		this.sendAdd = sendAdd;
	}

	public Float getSendLongitude() {
		return sendLongitude;
	}

	public void setSendLongitude(Float sendLongitude) {
		this.sendLongitude = sendLongitude;
	}

	public Float getSendLatitude() {
		return sendLatitude;
	}

	public void setSendLatitude(Float sendLatitude) {
		this.sendLatitude = sendLatitude;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiveProvince() {
		return receiveProvince;
	}

	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}

	public String getReceiveCity() {
		return receiveCity;
	}

	public void setReceiveCity(String receiveCity) {
		this.receiveCity = receiveCity;
	}

	public String getReceiveArea() {
		return receiveArea;
	}

	public void setReceiveArea(String receiveArea) {
		this.receiveArea = receiveArea;
	}

	public String getReceiveDigest() {
		return receiveDigest;
	}

	public void setReceiveDigest(String receiveDigest) {
		this.receiveDigest = receiveDigest;
	}

	public String getReceiveAdd() {
		return receiveAdd;
	}

	public void setReceiveAdd(String receiveAdd) {
		this.receiveAdd = receiveAdd;
	}

	public Float getReceiveLongitude() {
		return receiveLongitude;
	}

	public void setReceiveLongitude(Float receiveLongitude) {
		this.receiveLongitude = receiveLongitude;
	}

	public Float getReceiveLatitude() {
		return receiveLatitude;
	}

	public void setReceiveLatitude(Float receiveLatitude) {
		this.receiveLatitude = receiveLatitude;
	}

	public Integer getDisreceDistance() {
		return disreceDistance;
	}

	public void setDisreceDistance(Integer disreceDistance) {
		this.disreceDistance = disreceDistance;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDkhCode() {
		return dkhCode;
	}

	public void setDkhCode(String dkhCode) {
		this.dkhCode = dkhCode;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}

	public java.math.BigDecimal getDamagedAmount() {
		return damagedAmount;
	}

	public void setDamagedAmount(java.math.BigDecimal damagedAmount) {
		this.damagedAmount = damagedAmount;
	}

	public java.math.BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(java.math.BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public java.util.Date getOverTime() {
		return overTime;
	}

	public void setOverTime(java.util.Date overTime) {
		this.overTime = overTime;
	}

	public java.util.Date getWaitSettlementTime() {
		return waitSettlementTime;
	}

	public void setWaitSettlementTime(java.util.Date waitSettlementTime) {
		this.waitSettlementTime = waitSettlementTime;
	}

	public java.util.Date getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(java.util.Date settlementTime) {
		this.settlementTime = settlementTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getGoodsLength() {
		return goodsLength;
	}

	public void setGoodsLength(Integer goodsLength) {
		this.goodsLength = goodsLength;
	}

	public Integer getGoodsWidth() {
		return goodsWidth;
	}

	public void setGoodsWidth(Integer goodsWidth) {
		this.goodsWidth = goodsWidth;
	}

	public Integer getGoodsHeight() {
		return goodsHeight;
	}

	public void setGoodsHeight(Integer goodsHeight) {
		this.goodsHeight = goodsHeight;
	}

	public Integer getGoodsTotalWeight() {
		return goodsTotalWeight;
	}

	public void setGoodsTotalWeight(Integer goodsTotalWeight) {
		this.goodsTotalWeight = goodsTotalWeight;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Float getDeliveryLongitude() {
		return deliveryLongitude;
	}

	public void setDeliveryLongitude(Float deliveryLongitude) {
		this.deliveryLongitude = deliveryLongitude;
	}

	public Float getDeliveryLatitude() {
		return deliveryLatitude;
	}

	public void setDeliveryLatitude(Float deliveryLatitude) {
		this.deliveryLatitude = deliveryLatitude;
	}

	public java.util.Date getTakeOrderTime() {
		return takeOrderTime;
	}

	public void setTakeOrderTime(java.util.Date takeOrderTime) {
		this.takeOrderTime = takeOrderTime;
	}

	public java.util.Date getTakeGoodsTime() {
		return takeGoodsTime;
	}

	public void setTakeGoodsTime(java.util.Date takeGoodsTime) {
		this.takeGoodsTime = takeGoodsTime;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public java.math.BigDecimal getGlobalnodePrice() {
		return globalnodePrice;
	}

	public void setGlobalnodePrice(java.math.BigDecimal globalnodePrice) {
		this.globalnodePrice = globalnodePrice;
	}

	public java.util.Date getConfirmReceiptTime() {
		return confirmReceiptTime;
	}

	public void setConfirmReceiptTime(java.util.Date confirmReceiptTime) {
		this.confirmReceiptTime = confirmReceiptTime;
	}

	public java.util.Date getPackageTime() {
		return packageTime;
	}

	public void setPackageTime(java.util.Date packageTime) {
		this.packageTime = packageTime;
	}

	public java.util.Date getRobPoolTime() {
		return robPoolTime;
	}

	public void setRobPoolTime(java.util.Date robPoolTime) {
		this.robPoolTime = robPoolTime;
	}

	public Integer getGoodsTotalVolume() {
		return goodsTotalVolume;
	}

	public void setGoodsTotalVolume(Integer goodsTotalVolume) {
		this.goodsTotalVolume = goodsTotalVolume;
	}

	public Integer getEffThird() {
		return effThird;
	}

	public void setEffThird(Integer effThird) {
		this.effThird = effThird;
	}

	public Integer getEffRecp() {
		return effRecp;
	}

	public void setEffRecp(Integer effRecp) {
		this.effRecp = effRecp;
	}

}
