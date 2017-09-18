package com.alacriti.ebpp.model.vo;


import java.sql.Date;
public class PayDetailsVO {
	
	private String email;
	private String month;
	private String billno;
	private String amount;
	private String paymentType;
	private String paymentOption;
	private Date paymentDate;
	private String payDate;
	
	public PayDetailsVO(){
		
	}
	
	public String getPaymentOption() {
		return paymentOption;
	}


	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public PayDetailsVO(String email,String month, String billno,
			String amount, String paymentType,String paymentOption, String payDate) {
		super();
		this.email = email;
		this.month = month;
		this.billno = billno;
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentOption = paymentOption;
		this.payDate = payDate;
	}
	
	public PayDetailsVO(String email, String month, String billno,
			String amount, String paymentType, Date paymentDate) {
		super();
		this.email = email;
		this.month = month;
		this.billno = billno;
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String payOption) {
		this.paymentType = payOption;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
