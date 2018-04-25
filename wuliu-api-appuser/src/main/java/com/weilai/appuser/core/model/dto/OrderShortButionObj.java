package com.weilai.appuser.core.model.dto;

import java.io.Serializable;

public class OrderShortButionObj implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String globalOrderNum;
	private String batchNum;
	private String status;
	private String sender;
	private String senderPhone;
	private String sendProvince;
	private String sendCity;
	private String sendArea;
	private String sendDigest;
	private String sendAdd;
	private String receiver;
	private String receiverPhone;
	private String receiveProvince;
	private String receiveCity;
	private String receiveArea;
	private String receiveDigest;
	private String receiveAdd;
	private String goodsTotalWeight;
	private String goodsTotalVolumn;
	private String createTime;

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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getGoodsTotalWeight() {
		return goodsTotalWeight;
	}

	public void setGoodsTotalWeight(String goodsTotalWeight) {
		this.goodsTotalWeight = goodsTotalWeight;
	}

	public String getGoodsTotalVolumn() {
		return goodsTotalVolumn;
	}

	public void setGoodsTotalVolumn(String goodsTotalVolumn) {
		this.goodsTotalVolumn = goodsTotalVolumn;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
