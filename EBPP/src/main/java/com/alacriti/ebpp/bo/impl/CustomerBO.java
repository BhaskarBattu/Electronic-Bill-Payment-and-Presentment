package com.alacriti.ebpp.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.dao.impl.CustomerDAO;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.exception.DAOException;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.PayDetailsVO;

public class CustomerBO extends BaseBO{
public static final Logger log= Logger.getLogger(CustomerBO.class);
	
	public CustomerBO() {
		super();
	}
	public CustomerBO(Connection con) {
		super(con);
	}
	public  boolean addCustomerBO(CustomerVO customer) throws BOException{
		log.debug("=========>> addCustomer method in CustomerBO class ::");
		boolean result = false;
		CustomerDAO customerdao=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			result = customerdao.addCustomerToDB(customer);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}

	public  ArrayList<BillsDetailsVO> getCustomerBillsBO(BillsDetailsVO bills) throws BOException{
		log.debug("=========>> getCustomerBillsBO method in CustomerBO class ::");
		CustomerDAO customerdao=null;
		ArrayList<BillsDetailsVO> billsList=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			billsList = customerdao.getCustomerBillsDAO(bills);
		}catch (DAOException e) {
			log.error("DAOException in getCustomerBillsBO of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getCustomerBillsBO of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return billsList;
	}

	public  boolean isPayCustomerBill(PayDetailsVO payment) throws BOException{
		log.debug("=========>> isPayCustomerBill method in CustomerBO class ::");
		boolean result = false;
		CustomerDAO customerdao=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			result = customerdao.deleteCustomerBillFromDB(payment);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}
	
	public  boolean addCustomerBillBO(PayDetailsVO payment) throws BOException{
		log.debug("=========>> isPayCustomerBill method in CustomerBO class ::");
		boolean result = false;
		CustomerDAO customerdao=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			result = customerdao.addCustomerBillToDB(payment);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}
	
	public  boolean addCustomerBillOnlineBO(PayDetailsVO payment) throws BOException{
		log.debug("=========>> isPayCustomerBill method in CustomerBO class ::");
		boolean result = false;
		CustomerDAO customerdao=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			result = customerdao.addCustomerBillOnlineToDB(payment);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}

	public  ArrayList<PayDetailsVO> getCustomerPaidBillsBO(PayDetailsVO detail) throws BOException{
		log.debug("=========>> getCustomerPaidBillsBO method in CustomerBO class ::");
		CustomerDAO customerdao=null;
		ArrayList<PayDetailsVO> payBillsList=null;
		try{
			customerdao=new CustomerDAO(getConnection());
			payBillsList = customerdao.getCustomerPaidBillsDAO(detail);
		}catch (DAOException e) {
			log.error("DAOException in getCustomerPaidBillsBO of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getCustomerPaidBillsBO of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return payBillsList;
	}

}
