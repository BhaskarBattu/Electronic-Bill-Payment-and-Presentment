package com.alacriti.ebpp.biz.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.bo.impl.CustomerBO;
import com.alacriti.ebpp.bo.impl.CustomerValidate;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.PayDetailsVO;

public class CustomerDelegate extends BaseDelegate{
	public static final Logger log= Logger.getLogger(CustomerDelegate.class);
		
		 public CustomerDelegate() {
			super();
	}
		 
	public boolean addCustomer(CustomerVO customer){
		log.debug("=========>> addUser method in CustomerDelegate class ::");
		boolean result=false;
		CustomerBO customerbo=null;
		Connection connection=null;
		boolean rollBack = false;
		
		CustomerValidate customerValidate=null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			customerValidate= new CustomerValidate(connection);
			if(!customerValidate.isCustomerEmailExist(customer.getEmail())){
				customerbo= new CustomerBO(connection);
				result=customerbo.addCustomerBO(customer);	
			}
		}catch(BOException e){
			rollBack=true;
			e.printStackTrace();
			log.error("BOException in addUser : "+ e.getMessage(), e);
		}catch(Exception e){
			rollBack=true;
			log.error("Exception in addUser : "+ e.getMessage(), e);
		}finally{
			endDBTransaction(connection, rollBack);
		}
		return result;
	}	 

	public ArrayList<BillsDetailsVO> getCustomerBills(BillsDetailsVO bills){
		 log.debug("=========>> getCustomerBills method in CustomerDelegate class ::");
		 CustomerBO customerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<BillsDetailsVO> billsList = null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				customerbo= new CustomerBO(connection);
				billsList = customerbo.getCustomerBillsBO(bills);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getCustomerBills : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getCustomerBills : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return billsList;
	 }

	public boolean payCustomerBill(PayDetailsVO payment){
		log.debug("=========>> payCustomerBill method in CustomerDelegate class ::");
		boolean result=false;
		CustomerBO customerbo=null;
		Connection connection=null;
		boolean rollBack = false;
		
		try{
			connection = startDBTransaction();
			setConnection(connection);
			customerbo= new CustomerBO(connection);
			if(customerbo.isPayCustomerBill(payment)){
				result=customerbo.addCustomerBillBO(payment);	
			}
		}catch(BOException e){
			rollBack=true;
			e.printStackTrace();
			log.error("BOException in addUser : "+ e.getMessage(), e);
		}catch(Exception e){
			rollBack=true;
			log.error("Exception in addUser : "+ e.getMessage(), e);
		}finally{
			endDBTransaction(connection, rollBack);
		}
		return result;
	}	 
	
	public ArrayList<PayDetailsVO> getCustomerPaidBills(PayDetailsVO detail){
		log.debug("=========>> getCustomerPaidBills method in CustomerDelegate class ::");
		ArrayList<PayDetailsVO> payBillsList = null;
		CustomerBO customerbo=null;
		Connection connection=null;
		boolean rollBack = false;
		
		try{
			connection = startDBTransaction();
			setConnection(connection);
			customerbo= new CustomerBO(connection);	
			payBillsList =customerbo.getCustomerPaidBillsBO(detail);	
			
		}catch(BOException e){
			rollBack=true;
			e.printStackTrace();
			log.error("BOException in payCustomerBillOnline : "+ e.getMessage(), e);
		}catch(Exception e){
			rollBack=true;
			log.error("Exception in payCustomerBillOnline : "+ e.getMessage(), e);
		}finally{
			endDBTransaction(connection, rollBack);
		}
		return payBillsList;
	}

	public boolean payCustomerBillOnline(PayDetailsVO payment){
		log.debug("=========>> payCustomerBillOnline method in CustomerDelegate class ::");
		boolean result=false;
		CustomerBO customerbo=null;
		Connection connection=null;
		boolean rollBack = false;
		
		try{
			connection = startDBTransaction();
			setConnection(connection);
			customerbo= new CustomerBO(connection);
			if(customerbo.isPayCustomerBill(payment)){
				
				///check
				
				result=customerbo.addCustomerBillOnlineBO(payment);	
			}
		}catch(BOException e){
			rollBack=true;
			e.printStackTrace();
			log.error("BOException in payCustomerBillOnline : "+ e.getMessage(), e);
		}catch(Exception e){
			rollBack=true;
			log.error("Exception in payCustomerBillOnline : "+ e.getMessage(), e);
		}finally{
			endDBTransaction(connection, rollBack);
		}
		return result;
	}

}
