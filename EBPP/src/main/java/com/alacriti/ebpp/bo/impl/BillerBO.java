package com.alacriti.ebpp.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.dao.impl.BillerDAO;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.exception.DAOException;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.ViewCustomer;

public class BillerBO extends BaseBO{
	public static final Logger log= Logger.getLogger(BillerBO.class);
	public BillerBO() {
		super();
	}
	public BillerBO(Connection con) {
		super(con);
	}
	public  void addCustomerBO(CustomerVO customer) throws BOException{
		log.debug("=========>> addCustomer method in CustomerBO class ::");
		BillerDAO billerdao=null;
		try{
			billerdao=new BillerDAO(getConnection());
			billerdao.addCustomerToDB(customer);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
	}
	
	public  void addBillsBO(BillsDetailsVO bill) throws BOException{
		log.debug("=========>> addCustomer method in CustomerBO class ::");
		BillerDAO billerdao=null;
		try{
			billerdao=new BillerDAO(getConnection());
			billerdao.addBillsToDB(bill);
		}catch (DAOException e) {
			log.error("DAOException in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in addcustomer of CustomerBO " + e.getMessage(), e);
			throw new BOException();
		}
	}
	
	public  ArrayList<BillsDetailsVO> sendBillsToCustomersBO(BillsDetailsVO bill) throws BOException{
		log.debug("=========>> sendBillsToCustomers method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<BillsDetailsVO> listOfBills;
		try{
			billerdao=new BillerDAO(getConnection());
			listOfBills= billerdao.sendBillsToCustomersDAO(bill);
		}catch (DAOException e) {
			log.error("DAOException in sendBillsToCustomers of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in sendBillsToCustomers of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return listOfBills;
	}

	public  ArrayList<ViewCustomer> getCustomerInfoBO(ViewCustomer customer) throws BOException{
		log.debug("=========>> getCustomerInfoBO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<ViewCustomer> customersList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			customersList = billerdao.getCustomerInfoDAO(customer);
		}catch (DAOException e) {
			log.error("DAOException in getCustomerInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getCustomerInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return customersList;
	}

	public  ArrayList<ViewCustomer> getCustomerInfoToViewBO(ViewCustomer customer) throws BOException{
		log.debug("=========>> getCustomerInfoToViewDAO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<ViewCustomer> customersList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			customersList = billerdao.getCustomerInfoToViewDAO(customer);
		}catch (DAOException e) {
			log.error("DAOException in getCustomerInfoToViewDAO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getCustomerInfoToViewDAO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return customersList;
	}
	
	public  ArrayList<BillsDetailsVO> getBillsInfoBO(BillsDetailsVO bills) throws BOException{
		log.debug("=========>> getBillsInfoBO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<BillsDetailsVO> billsList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			billsList = billerdao.getBillsInfoDAO(bills);
		}catch (DAOException e) {
			log.error("DAOException in getBillsInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getBillsInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return billsList;
	}

	public  ArrayList<ViewCustomer> getSearchResultsOfCustomersBO(ViewCustomer customer, String searchTerm) throws BOException{
		log.debug("=========>> getSearchResultsOfCustomersBO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<ViewCustomer> customersList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			customersList = billerdao.getSearchResultsOfCustomersDAO(customer, searchTerm);
		}catch (DAOException e) {
			log.error("DAOException in getSearchResultsOfCustomersBO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getSearchResultsOfCustomersBO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return customersList;
	}

	public  ArrayList<BillsDetailsVO> getSearchBillsInfoBO(BillsDetailsVO bills, String searchTerm) throws BOException{
		log.debug("=========>> getSearchBillsInfoBO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<BillsDetailsVO> billsList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			billsList = billerdao.getSearchBillsInfoDAO(bills,searchTerm);
		}catch (DAOException e) {
			log.error("DAOException in getSearchBillsInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getSearchBillsInfoBO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return billsList;
	}
	
	public  ArrayList<BillsDetailsVO> getSearchBillsBasedonDateBO(BillsDetailsVO bills) throws BOException{
		log.debug("=========>> getSearchBillsBasedonDateBO method in BillerBO class ::");
		BillerDAO billerdao=null;
		ArrayList<BillsDetailsVO> billsList=null;
		try{
			billerdao=new BillerDAO(getConnection());
			billsList = billerdao.getSearchBillsBasedonDateDAO(bills);
		}catch (DAOException e) {
			log.error("DAOException in getSearchBillsBasedonDateBO of BillerBO " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in getSearchBillsBasedonDateBO of BillerBO " + e.getMessage(), e);
			throw new BOException();
		}
		return billsList;
	}

}
