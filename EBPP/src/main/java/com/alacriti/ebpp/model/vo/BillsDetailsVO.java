package com.alacriti.ebpp.model.vo;

import java.sql.Date;

public class BillsDetailsVO {
	private String name;
	private String email;
	private String month;
	private String billnumber;
	private String amount;
	private Date duedate;
	private Date startDate;
	private Date endDate;
	
	public BillsDetailsVO(){
		
	}

	public BillsDetailsVO(String name,String email){
		this.name=name;
		this.email=email;
	}

	public BillsDetailsVO(String email,String month,String billnumber,String amount, Date duedate) {
		super();
		this.email = email;
		this.month = month;
		this.billnumber = billnumber;
		this.amount = amount;
		this.duedate = duedate;
	}
	
	public BillsDetailsVO(String name, String email, String month,
			String billnumber, String amount, Date duedate) {
		super();
		this.name = name;
		this.email = email;
		this.month = month;
		this.billnumber = billnumber;
		this.amount = amount;
		this.duedate = duedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
