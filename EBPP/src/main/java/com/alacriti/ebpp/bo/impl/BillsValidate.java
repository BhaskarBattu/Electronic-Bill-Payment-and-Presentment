package com.alacriti.ebpp.bo.impl;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.dao.impl.BillerDAO;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.exception.DAOException;

public class BillsValidate extends BaseBO {
	public static final Logger log= Logger.getLogger(BillsValidate.class);
	 public BillsValidate() {
		super();
	}
	
	public BillsValidate(Connection con) {
		super(con);
	}
	
	public  boolean isBillNumberExistInBillsDetailsList(String billNumber)throws BOException{
		log.debug("=========>> isUserEmailExist method in UserValidate class ::");
		boolean result=false;
		BillerDAO billerDAO=null;
		try{
			billerDAO=new BillerDAO(getConnection());
		result=billerDAO.isBillNumberExistInBillsDetailsList(billNumber);
		}catch (DAOException e) {
			log.error("DAOException in isCustomerEmailExist of CustomerValidate " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in isCustomerEmailExist of CustomerValidate " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}
	
}
