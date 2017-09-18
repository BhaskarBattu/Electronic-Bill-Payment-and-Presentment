package com.alacriti.ebpp.bo.impl;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.dao.impl.CustomerDAO;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.exception.DAOException;

public class CustomerValidate extends BaseBO{
	public static final Logger log= Logger.getLogger(CustomerValidate.class);
	
	public CustomerValidate() {
		super();
	}
	
	public CustomerValidate(Connection con) {
		super(con);
	}
	
	public  boolean isCustomerEmailExist(String email)throws BOException{
		log.debug("=========>> isUserEmailExist method in UserValidate class ::");
		boolean result=false;
		CustomerDAO customerDAO=null;
		try{
			customerDAO=new CustomerDAO(getConnection());
		result=customerDAO.isCustomerEmailExist(email);
		}catch (DAOException e) {
			log.error("DAOException in isCustomerEmailExist of CustomerValidate " + e.getMessage(), e);
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception in isCustomerEmailExist of CustomerValidate " + e.getMessage(), e);
			throw new BOException();
		}
		return result;
	}
	
	public  boolean isCustomerEmailExistInCustomersList(String email)throws BOException{
		log.debug("=========>> isUserEmailExist method in UserValidate class ::");
		boolean result=false;
		CustomerDAO customerDAO=null;
		try{
			customerDAO=new CustomerDAO(getConnection());
		result=customerDAO.isCustomerEmailExistInCustomersList(email);
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
