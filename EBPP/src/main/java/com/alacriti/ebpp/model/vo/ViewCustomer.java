package com.alacriti.ebpp.model.vo;

import java.sql.Date;

public class ViewCustomer {
	private String username;
	private String email;
	private String totalOutStanding;
	private Date duedate;
	
	public ViewCustomer(){
		
	}
	public ViewCustomer(String username, String email){
		super();
		this.username = username;
		this.email = email;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTotalOutStanding() {
		return totalOutStanding;
	}
	public void setTotalOutStanding(String totalOutStanding) {
		this.totalOutStanding = totalOutStanding;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public ViewCustomer(String username, String email, String totalOutStanding,
			Date duedate) {
		super();
		this.username = username;
		this.email = email;
		this.totalOutStanding = totalOutStanding;
		this.duedate = duedate;
	}
	
	
}
