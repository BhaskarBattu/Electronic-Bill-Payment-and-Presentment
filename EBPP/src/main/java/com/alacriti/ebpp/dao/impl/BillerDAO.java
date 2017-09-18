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
import com.alacriti.ebpp.model.vo.ViewCustomer;

public class BillerDAO extends BaseDAO{
public static final Logger log= Logger.getLogger(BillerDAO.class);
	
	public BillerDAO(){
		super();
	}
	
	public BillerDAO(Connection con){
		super(con);
	}
	
	public void addCustomerToDB(CustomerVO customer) throws DAOException{
		log.debug("=========>> addCustomerToDB method in BillerDAO class ::");
		
		PreparedStatement pst=null;
		try{
			int i=0;
			pst=getPreparedStatement(getConnection(), addCustomerToDbSqlCmd());
			pst.setString(++i,customer.getEmail());
			pst.setString(++i,customer.getUsername());
			pst.executeUpdate();
		}catch(SQLException e){
			log.error("SQL Exception Occured in addCustomerToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in preparedStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in addCustomerToDb: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(pst);
		}
	}
	
	private String addCustomerToDbSqlCmd(){
		return "insert into bhaskarb_ebpp_customers_list_tbl("
				+DBColumnConstants.CUSTOMERS_LIST_TBL_EMAIL+","
				+DBColumnConstants.CUSTOMERS_LIST_TBL_NAME
				+")values(?,?);";
	}
	
	public void addBillsToDB(BillsDetailsVO bill) throws DAOException{
		log.debug("=========>> addBillsToDB method in BillerDAO class ::");
		
		PreparedStatement pst=null;
		try{
			int i=0;
			pst=getPreparedStatement(getConnection(), addBillsToDbSqlCmd());
			pst.setString(++i,bill.getEmail());
			pst.setString(++i,bill.getMonth());
			pst.setString(++i,bill.getBillnumber());
			pst.setString(++i,bill.getAmount());
			pst.setDate(++i,bill.getDuedate());
			pst.executeUpdate();
		}catch(SQLException e){
			log.error("SQL Exception Occured in addBillsToDB: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in preparedStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in addBillsToDB: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(pst);
		}
	}
	
	private String addBillsToDbSqlCmd(){
		return "insert into bhaskarb_ebpp_billsDetails_tbl("
				+DBColumnConstants.BILLS_TBL_EMAIL+","
				+DBColumnConstants.BILLS_TBL_MONTH+","
				+DBColumnConstants.BILLS_TBL_BILLNUMBER+","
				+DBColumnConstants.BILLS_TBL_AMOUNT+","
				+" duedate"
				+")values(?,?,?,?,?);";
	}
	
	public boolean isBillNumberExistInBillsDetailsList(String billnumber) throws DAOException{
		log.debug("=========>> isBillNumberExistInBillsDetailsList method in BillerDAO class ::");
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(isBillNumberExistInBillsDetailsSqlCmd(billnumber));
			if(rs.next()){
				result=true;
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in isBillNumberExistInBillsDetailsList: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in isBillNumberExistInBillsDetailsList: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return result;
	}
	
	private String isBillNumberExistInBillsDetailsSqlCmd(String billnumber){
		return "select "+DBColumnConstants.CUSTOMERS_LIST_TBL_EMAIL
				+" from bhaskarb_ebpp_billsDetails_tbl "
				+" where "+DBColumnConstants.BILLS_TBL_BILLNUMBER+"=\""+billnumber+"\";";
	}
	
	public ArrayList<BillsDetailsVO> sendBillsToCustomersDAO(BillsDetailsVO bill) throws DAOException{
		log.debug("=========>> isBillNumberExistInBillsDetailsList method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BillsDetailsVO> listOfBills = new ArrayList<BillsDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllBillsDetailsSqlCmd(bill));
			while(rs.next()){
				listOfBills.add(new BillsDetailsVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in isBillNumberExistInBillsDetailsList: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in isBillNumberExistInBillsDetailsList: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return listOfBills;
	}
	
	private String getAllBillsDetailsSqlCmd(BillsDetailsVO bill){
		return "select "+DBColumnConstants.BILLS_TBL_EMAIL+"," 
				+DBColumnConstants.BILLS_TBL_MONTH+","
				+DBColumnConstants.BILLS_TBL_BILLNUMBER+","
				+DBColumnConstants.BILLS_TBL_AMOUNT+","
				+"duedate"
				+" from bhaskarb_ebpp_billsDetails_tbl";
	}
	
	public ArrayList<ViewCustomer> getCustomerInfoDAO(ViewCustomer customer) throws DAOException{
		log.debug("=========>> getCustomerInfoDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<ViewCustomer> customersList = new ArrayList<ViewCustomer>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllCustomerDetailsSqlCmd(customer));
			while(rs.next()){
				customersList.add(new ViewCustomer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getCustomerInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getCustomerInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return customersList;
	}
	
	private String getAllCustomerDetailsSqlCmd(ViewCustomer customer){
		return "select cl.name,bd.email,sum(bd.amount), bd.duedate from bhaskarb_ebpp_billsDetails_tbl as bd, "
				+ "bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email group by bd.email";
	}
	
	public ArrayList<ViewCustomer> getCustomerInfoToViewDAO(ViewCustomer customer) throws DAOException{
		log.debug("=========>> getCustomerInfoToViewDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<ViewCustomer> customersList = new ArrayList<ViewCustomer>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllCustomerDetailsToViewSqlCmd(customer));
			while(rs.next()){
				customersList.add(new ViewCustomer(rs.getString(1),rs.getString(2)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getCustomerInfoToViewDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getCustomerInfoToViewDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return customersList;
	}
	
	private String getAllCustomerDetailsToViewSqlCmd(ViewCustomer customer){
		return "select name,email from bhaskarb_ebpp_customers_list_tbl group by email";
	}
	
	
	
	public ArrayList<BillsDetailsVO> getBillsInfoDAO(BillsDetailsVO bills) throws DAOException{
		log.debug("=========>> getBillsInfoDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BillsDetailsVO> billsList = new ArrayList<BillsDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllBillsDetailsShowToBillerSqlCmd(bills));
			while(rs.next()){
				billsList.add(new BillsDetailsVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getBillsInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getBillsInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return billsList;
	}
	
	private String getAllBillsDetailsShowToBillerSqlCmd(BillsDetailsVO bills){
		return "select cl.name,bd.email,bd.month,bd.billnumber,bd.amount, bd.duedate from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl "
				+ "where bd.email=cl.email order by bd.email";
	}
	
	public ArrayList<ViewCustomer> getSearchResultsOfCustomersDAO(ViewCustomer customer, String searchTerm) throws DAOException{
		log.debug("=========>> getSearchResultsOfCustomersDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<ViewCustomer> customersList = new ArrayList<ViewCustomer>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getSearchResultsOfCustomersSqlCmd(customer, searchTerm));
			while(rs.next()){
				customersList.add(new ViewCustomer(rs.getString(1),rs.getString(2)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getSearchResultsOfCustomersDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getSearchResultsOfCustomersDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return customersList;
	}
	
	private String getSearchResultsOfCustomersSqlCmd(ViewCustomer customer, String searchTerm){
		return "select "+DBColumnConstants.CUSTOMERS_LIST_TBL_NAME+","+DBColumnConstants.CUSTOMERS_LIST_TBL_EMAIL+" from bhaskarb_ebpp_customers_list_tbl where email like '%"+searchTerm+"%' or name like '%"+searchTerm+"%'";
	}
	
	public ArrayList<BillsDetailsVO> getSearchBillsInfoDAO(BillsDetailsVO bills, String searchTerm) throws DAOException{
		log.debug("=========>> getSearchBillsInfoDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BillsDetailsVO> billsList = new ArrayList<BillsDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllSearchBillsDetailsShowToBillerSqlCmd(bills,searchTerm));
			while(rs.next()){
				billsList.add(new BillsDetailsVO(rs.getString(1),rs.getString(2)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getSearchBillsInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getSearchBillsInfoDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return billsList;
	}
	
	private String getAllSearchBillsDetailsShowToBillerSqlCmd(BillsDetailsVO bills,String searchTerm){
		return "select cl.name,bd.email from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl "
				+ "where bd.email=cl.email and ( bd.email like '%"+searchTerm+"%' or cl.name like '%"+searchTerm+"%' ) group by bd.email";
	}
	
	public ArrayList<BillsDetailsVO> getSearchBillsBasedonDateDAO(BillsDetailsVO bills) throws DAOException{
		log.debug("=========>> getSearchBillsBasedonDateDAO method in BillerDAO class ::");
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BillsDetailsVO> billsList = new ArrayList<BillsDetailsVO>();
		try{
			st=getConnection().createStatement();
			rs=st.executeQuery(getAllSearchBillsDetailsBasedonDatesSqlCmd(bills));
			while(rs.next()){
				billsList.add(new BillsDetailsVO(rs.getString(1),rs.getString(2)));
			}
		}catch(SQLException e){
			log.error("SQL Exception Occured in getSearchBillsBasedonDateDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException("SQL Exception Occured in selectStatement");
		}
		catch(Exception e){
			log.error("Exception Occured in getSearchBillsBasedonDateDAO: " + e.getMessage(), e);
			e.printStackTrace();
			throw new DAOException();
		}finally{
			close(rs);
			close(st);
		}
		return billsList;
	}
	
	private String getAllSearchBillsDetailsBasedonDatesSqlCmd(BillsDetailsVO bills){
		return "select cl.name,bd.email from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where "
				+ "bd.email=cl.email and ( bd.duedate between '"+bills.getStartDate()+"' and '"+bills.getEndDate()+"' ) group by bd.email";
	}
	
}
