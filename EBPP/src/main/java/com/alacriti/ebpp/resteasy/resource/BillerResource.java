package com.alacriti.ebpp.resteasy.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.biz.delegate.BillerDelegate;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.ViewCustomer;
import com.alacriti.ebpp.util.QuartzMain;

@Path("/biller")
public class BillerResource {
	public static final Logger log= Logger.getLogger(CustomerResource.class); 
		
		@POST
		@Path("/addCustomers")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addCustomers(ArrayList<CustomerVO> customer) {
			log.debug("=========>> addCustomers method in BillerResource class ::");
			boolean result=false;
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				result = billerDelegate.addCustomers(customer);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return Response.status(200).entity(result).build();
		}
		
		@POST
		@Path("/addBillsDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addBillsDetails(ArrayList<BillsDetailsVO> billsDetails) {
			log.debug("=========>> addCustomers method in BillerResource class ::");
			boolean result=false;
			BillerDelegate billerDelegate=null;
			QuartzMain sendMail = new QuartzMain();
			try {
				billerDelegate =new BillerDelegate();
				result = billerDelegate.addBills(billsDetails);
				if(result){
					sendMail.sendBillsToCustomers();
				}
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			
			return Response.status(200).entity(result).build();
			
		}

		@GET
		@Path("/getCustomers")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ArrayList<ViewCustomer> getCustomerInfo(){
			log.debug("=========>> getCustomerInfo method in BillerResource class ::");
			ArrayList<ViewCustomer> customersList = null;
			ViewCustomer customer= new ViewCustomer();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				customersList = billerDelegate.getCustomerInfo(customer);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return customersList;
		}
		
		@GET
		@Path("/getCustomersToView")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ArrayList<ViewCustomer> getCustomerInfoToView(){
			log.debug("=========>> getCustomerInfo method in BillerResource class ::");
			ArrayList<ViewCustomer> customersList = null;
			ViewCustomer customer= new ViewCustomer();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				customersList = billerDelegate.getCustomerInfoToView(customer);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return customersList;
		}
		
		@GET
		@Path("/getBills")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ArrayList<BillsDetailsVO> getBillsInfo(){
			log.debug("=========>> getBillsInfo method in BillerResource class ::");
			ArrayList<BillsDetailsVO> billsList = null;
			BillsDetailsVO bills= new BillsDetailsVO();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				billsList = billerDelegate.getBillsInfo(bills);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return billsList;
			
		}
		
		@GET
		@Path("/getSearchCustomers")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<ViewCustomer> getSearchResultsOfCustomers(@QueryParam("name") String searchTerm){
			log.debug("=========>> getSearchResultsOfCustomers method in BillerResource class ::");
			ArrayList<ViewCustomer> customersList = null;
			ViewCustomer customer= new ViewCustomer();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				customersList = billerDelegate.getSearchResultsOfCustomers(customer,searchTerm);
				System.out.println(customersList);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return customersList;
		}
		
		@GET
		@Path("/getSearchBills")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ArrayList<BillsDetailsVO> getSearchBillsInfo(@QueryParam("name") String searchTerm){
			log.debug("=========>> getSearchBillsInfo method in BillerResource class ::");
			ArrayList<BillsDetailsVO> billsList = null;
			BillsDetailsVO bills= new BillsDetailsVO();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				billsList = billerDelegate.getSearchBillsInfo(bills,searchTerm);
			} catch (Exception e) {
				log.error("Exception in getSearchBillsInfo of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return billsList;
			
		}
		
		@POST
		@Path("/getSearchBillsBasedonDate")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ArrayList<BillsDetailsVO> getSearchBillsBasedonDate(BillsDetailsVO bills){
			log.debug("=========>> getSearchBillsInfo method in BillerResource class ::");
			ArrayList<BillsDetailsVO> billsList = null;
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				billsList = billerDelegate.getSearchBillsNameorEmailBasedonDate(bills);
			} catch (Exception e) {
				log.error("Exception in getSearchBillsInfo of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return billsList;
			
		}
}
