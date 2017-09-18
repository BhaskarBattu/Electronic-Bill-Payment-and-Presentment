package com.alacriti.ebpp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.constants.DBColumnConstants;
import com.alacriti.ebpp.exception.DAOException;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.PayDetailsVO;

public class CustomerDAO extends BaseDAO{
	public static final Logger log= Logger.getLogger(CustomerDAO.class);
	
	public CustomerDAO(){
		super();
	}
	
	public CustomerDAO(Connection con){
		super(con);
	}
	
	public boolean addCustomerToDB(CustomerVO customer) throws DAOException{
		log.debug("=========>> addCustomerToDB method in CustomerDAO class ::");
		
		boolean result=false;
		PreparedStatement pst=null;
		try{
			int i=0;
			pst=getPreparedStatement(getConnection(), addCustomerToDbSqlCmd());
			pst.setString(++i,customer.getEmail());
			pst.setString(++i,customer.getUsername());
			pst.setString(++i,"MailPasswordNotRequired");
			pst.executeUpdate();
			result=true;
		}catch(SQLException e){
			log.error("SQL Exception Occured in addUserToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in preparedStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in addUserToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(pst);
		}
		return result;
	}
	
	private String addCustomerToDbSqlCmd(){
		return "insert into bhaskarb_ebpp_customer_profile_tbl("
				+DBColumnConstants.CUSTOMER_PROFILE_TBL_EMAIL+","
				+DBColumnConstants.CUSTOMER_PROFILE_TBL_NAME+","
				+DBColumnConstants.CUSTOMER_PROFILE_TBL_PASSWORD
				+")values(?,?,?);";
	}
	
	public boolean isCustomerEmailExist(String email) throws DAOException{
		log.debug("=========>> isCustomerEmailExist method in UserDAO class ::");
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(isCustomerEmailExistSqlCmd(email));
			if(rs.next()){
				result=true;
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in isCustomerEmailExist: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in isCustomerEmailExist: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return result;
	}
	
	private String isCustomerEmailExistSqlCmd(String email){
		return "select "+DBColumnConstants.CUSTOMER_PROFILE_TBL_EMAIL
				+" from bhaskarb_ebpp_customer_profile_tbl "
				+" where "+DBColumnConstants.CUSTOMER_PROFILE_TBL_EMAIL+"=\""+email+"\";";
	}
	
	public boolean isCustomerEmailExistInCustomersList(String email) throws DAOException{
		log.debug("=========>> isCustomerEmailExist method in UserDAO class ::");
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(isCustomerEmailExistInCustomersListSqlCmd(email));
			if(rs.next()){
				result=true;
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in isCustomerEmailExist: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in isCustomerEmailExist: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return result;
	}
	
	private String isCustomerEmailExistInCustomersListSqlCmd(String email){
		return "select "+DBColumnConstants.CUSTOMERS_LIST_TBL_EMAIL
				+" from bhaskarb_ebpp_customers_list_tbl "
				+" where "+DBColumnConstants.CUSTOMERS_LIST_TBL_EMAIL+"=\""+email+"\";";
	}
	
	public ArrayList<BillsDetailsVO> getCustomerBillsDAO(BillsDetailsVO bills) throws DAOException{
		log.debug("=========>> getCustomerBillsDAO method in CustomerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BillsDetailsVO> billsList = new ArrayList<BillsDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllBillsDetailsSqlCmd(bills));
			while(rs.next()){
				billsList.add(new BillsDetailsVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getCustomerBillsDAO in CustomerDAO : " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getSearchBillsBasedonDateDAO: in CustomerDAO" + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		
		return billsList;
	}
	
	private String getAllBillsDetailsSqlCmd(BillsDetailsVO bills){
		 return "select email,month,billnumber,amount,duedate from bhaskarb_ebpp_billsDetails_tbl where email='"+bills.getEmail()+"'";
		//return "select email,month from bhaskarb_ebpp_billsDetails_tbl where email like '"+bills.getEmail()+"'";
	}

	public boolean deleteCustomerBillFromDB(PayDetailsVO payment) throws DAOException{
		log.debug("=========>> deleteCustomerBillFromDB method in CustomerDAO class ::");
		boolean result=false;
		Statement st=null;
		
		try{
			st=getConnection().createStatement();
			int i=st.executeUpdate(isDeletedCustomerBillSqlCmd(payment));
			if(i>0){
				result=true;
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in deleteCustomerBillFromDB: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in deleteCustomerBillFromDB: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(st);
		}
		return result;
	}
	
	private String isDeletedCustomerBillSqlCmd(PayDetailsVO payment){
		return "delete from bhaskarb_ebpp_billsDetails_tbl where "
				+ "email='"+payment.getEmail()+"' and billnumber='"+payment.getBillno()+"' and"
						+ " month='"+payment.getMonth()+"' and amount='"+payment.getAmount()+"'";
	}

	public boolean addCustomerBillToDB(PayDetailsVO payment) throws DAOException{
			log.debug("=========>> addCustomerBillToDB method in CustomerDAO class ::");
		
		boolean result=false;
		PreparedStatement pst=null;
		try{
			int i=0;
			pst=getPreparedStatement(getConnection(), isInsertedCustomerBillSqlCmd(payment));
			pst.setString(++i,payment.getEmail());
			pst.setString(++i,payment.getMonth());
			pst.setString(++i,payment.getBillno());
			pst.setString(++i,payment.getAmount());
			pst.setString(++i,payment.getPaymentType());
			pst.executeUpdate();
			result=true;
		}catch(SQLException e){
			log.error("SQL Exception Occured in addUserToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in preparedStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in addUserToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(pst);
		}
		return result;
	}
	
	private String isInsertedCustomerBillSqlCmd(PayDetailsVO payment){
		return "insert into bhaskarb_ebpp_customer_previous_bills_tbl(email,month,billno,amount,payoption,paymentdate) values "
				+ "(?,?,?,?,?,CURRENT_TIMESTAMP)";
	}
	
	public boolean addCustomerBillOnlineToDB(PayDetailsVO payment) throws DAOException{
		log.debug("=========>> addCustomerBillToDB method in CustomerDAO class ::");
	
	boolean result=false;
	PreparedStatement pst=null;
	try{
		int i=0;
		pst=getPreparedStatement(getConnection(), isInsertedCustomerBillOnlineSqlCmd(payment));
		pst.setString(++i,payment.getEmail());
		pst.setString(++i,payment.getMonth());
		pst.setString(++i,payment.getBillno());
		pst.setString(++i,payment.getAmount());
		pst.setString(++i,payment.getPaymentType());
		pst.setString(++i,payment.getPaymentOption());
		pst.executeUpdate();
		result=true;
	}catch(SQLException e){
		log.error("SQL Exception Occured in addUserToDb: " + e.getMessage(), e);
		e.printStackTrace();
		throw new DAOException("SQL Exception Occured in preparedStatement");
	}
	catch(Exception e){
		log.error("Exception Occured in addUserToDb: " + e.getMessage(), e);
		e.printStackTrace();
		throw new DAOException();
	}finally{
		close(pst);
	}
	return result;
}

	private String isInsertedCustomerBillOnlineSqlCmd(PayDetailsVO payment){
	return "insert into bhaskarb_ebpp_customer_previous_bills_tbl(email,month,billno,amount,payoption,payoptiondetails,paymentdate) values "
			+ "(?,?,?,?,?,?,CURRENT_TIMESTAMP)";
}

	public ArrayList<PayDetailsVO> getCustomerPaidBillsDAO(PayDetailsVO detail) throws DAOException{
		log.debug("=========>> getCustomerPaidBillsDAO method in CustomerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<PayDetailsVO> payBillsList = new ArrayList<PayDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getCustomerPaidBillsSqlCmd(detail));
			while(rs.next()){
				payBillsList.add(new PayDetailsVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getCustomerPaidBillsDAO in CustomerDAO : " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getCustomerPaidBillsDAO: in CustomerDAO" + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		
		return payBillsList;
	}
	
	private String getCustomerPaidBillsSqlCmd(PayDetailsVO detail){
		 return "select email,month, billno, amount, payoption, payoptiondetails, paymentdate from bhaskarb_ebpp_customer_previous_bills_tbl where"
		 		+ " email='"+detail.getEmail()+"'";
		//return "select email,month from bhaskarb_ebpp_billsDetails_tbl where email like '"+bills.getEmail()+"'";
	}

}
