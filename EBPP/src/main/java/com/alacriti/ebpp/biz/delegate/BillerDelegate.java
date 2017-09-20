package com.alacriti.ebpp.biz.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.bo.impl.BillerBO;
import com.alacriti.ebpp.bo.impl.BillsValidate;
import com.alacriti.ebpp.bo.impl.CustomerValidate;
import com.alacriti.ebpp.exception.BOException;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.ViewCustomer;
import com.alacriti.ebpp.util.MailingService;

public class BillerDelegate extends BaseDelegate {
	public static final Logger log= Logger.getLogger(BillerDelegate.class);
	
	MailingService sendMailToCustomers= new MailingService();
	
	 public BillerDelegate() {
		super();
	 }
	 
	 public boolean addCustomers(ArrayList<CustomerVO> customers){
			log.debug("=========>> addUser method in BillerDelegate class ::");
			boolean result=true;
			BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			
			CustomerValidate customerValidate=null;
				
			try{
				connection = startDBTransaction();
				setConnection(connection);
				customerValidate= new CustomerValidate(connection);
				for(CustomerVO cust: customers)
				{
					if(!customerValidate.isCustomerEmailExistInCustomersList(cust.getEmail())){
						billerbo= new BillerBO(connection);
						billerbo.addCustomerBO(cust);
					}
				}
			}catch(BOException e){
				rollBack=true;
				result= false;
				e.printStackTrace();
				log.error("BOException in addCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				result=false;
				log.error("Exception in addCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return result;
		}
	 
	 public boolean addBills(ArrayList<BillsDetailsVO> billsDetails){
			log.debug("=========>> addUser method in BillerDelegate class ::");
			boolean result=true;
			BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			
			CustomerValidate customerValidate=null;
			BillsValidate billValidate=null;
				
			try{
				connection = startDBTransaction();
				setConnection(connection);
				customerValidate= new CustomerValidate(connection);
				billValidate= new BillsValidate(connection);
				for(BillsDetailsVO bill: billsDetails)
				{
					if(customerValidate.isCustomerEmailExistInCustomersList(bill.getEmail())){
						System.out.println("name:shar");
						if(!billValidate.isBillNumberExistInBillsDetailsList(bill.getBillnumber())){
								billerbo= new BillerBO(connection);
								billerbo.addBillsBO(bill);
						}
					}else{
						result=false;
						System.out.println("name: Bhaskar");
						break;
					}
				}
			}catch(BOException e){
				rollBack=true;
				result= false;
				e.printStackTrace();
				log.error("BOException in addCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				result=false;
				log.error("Exception in addCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			System.out.println("result:"+result);
			return result;
		}

	 public void sendBillsToCustomers(BillsDetailsVO billsDetails){
			log.debug("=========>> sendBillsToCustomers method in BillerDelegate class ::");
			BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<BillsDetailsVO> listOfBills;	
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				listOfBills = billerbo.sendBillsToCustomersBO(billsDetails);
				for(BillsDetailsVO sendBill: listOfBills)
				{
					sendMailToCustomers.send("bhasiidrbt@gmail.com", "Y11@cs412", sendBill.getEmail(), "Due Bill for the month of "+sendBill.getMonth(), "Dear "+ sendBill.getEmail()+",\n\n\t"
							+ "please clear your due for the month of "+ sendBill.getMonth()+", billnumber is "+sendBill.getBillnumber()+" amount: "+sendBill.getAmount()+
							"  On or before "+sendBill.getDuedate()+"\n\n\n\n Thanks & Regards \n\nB Bhaskar");
				}
					
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in addCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in addCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
		}
	 
	 public int getpaginationCountOfCustomerBillsInfo(){
		 log.debug("=========>> getpaginationCountOfCustomerBillsInfo method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			int count=0;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				count = billerbo.getpaginationCountOfCustomerBillsInfoBO();
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getpaginationCountOfCustomerBillsInfo : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getpaginationCountOfCustomerBillsInfo : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return count;
	 }
	 
	 public int getpaginationCountOfCustomersInfo(){
		 log.debug("=========>> getpaginationCountOfCustomersInfo method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			int count=0;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				count = billerbo.getpaginationCountOfCustomersInfoBO();
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getpaginationCountOfCustomersInfo : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getpaginationCountOfCustomersInfo : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return count;
	 }
	 
	 public List<ViewCustomer> getCustomerInfo(ViewCustomer customer,int start,int end){
		 log.debug("=========>> getCustomerInfo method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<ViewCustomer> customersList = null;
			List<ViewCustomer> resultList=null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				resultList = billerbo.getCustomerInfoBO(customer,start,end);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in addCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in addCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return resultList;
	 }

	 public List<ViewCustomer> getCustomerInfoToView(ViewCustomer customer,int start,int end){
		 log.debug("=========>> getCustomerInfoToView method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<ViewCustomer> customersList = null;
			List<ViewCustomer> resultList=null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				resultList = billerbo.getCustomerInfoToViewBO(customer,start,end);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getCustomerInfoToView : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getCustomerInfoToView : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return resultList;
	 }
	 
	 public ArrayList<BillsDetailsVO> getBillsInfo(BillsDetailsVO bills){
		 log.debug("=========>> getBillsInfo method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<BillsDetailsVO> billsList = null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				billsList = billerbo.getBillsInfoBO(bills);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in addCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in addCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return billsList;
	 }

	 public ArrayList<ViewCustomer> getSearchResultsOfCustomers(ViewCustomer customer, String searchTerm){
		 log.debug("=========>> getSearchResultsOfCustomers method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<ViewCustomer> customersList = null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				customersList = billerbo.getSearchResultsOfCustomersBO(customer, searchTerm);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getSearchResultsOfCustomers : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getSearchResultsOfCustomers : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return customersList;
	 }

	 public ArrayList<BillsDetailsVO> getSearchBillsInfo(BillsDetailsVO bills, String searchTerm){
		 log.debug("=========>> getSearchBillsInfo method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<BillsDetailsVO> billsList = null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				billsList = billerbo.getSearchBillsInfoBO(bills, searchTerm);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getSearchBillsInfo : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getSearchBillsInfo : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return billsList;
	 }

	 public ArrayList<BillsDetailsVO> getSearchBillsNameorEmailBasedonDate(BillsDetailsVO bills){
		 log.debug("=========>> getSearchBillsNameorEmailBasedonDate method in BillerDelegate class ::");
		 BillerBO billerbo=null;
			Connection connection=null;
			boolean rollBack = false;
			ArrayList<BillsDetailsVO> billsList = null;
			
			try{
				connection = startDBTransaction();
				setConnection(connection);
				billerbo= new BillerBO(connection);
				billsList = billerbo.getSearchBillsBasedonDateBO(bills);
			}catch(BOException e){
				rollBack=true;
				e.printStackTrace();
				log.error("BOException in getSearchBillsNameorEmailBasedonDate : "+ e.getMessage(), e);
			}catch(Exception e){
				rollBack=true;
				log.error("Exception in getSearchBillsNameorEmailBasedonDate : "+ e.getMessage(), e);
			}finally{
				endDBTransaction(connection, rollBack);
			}
			
			return billsList;
	 }

}
