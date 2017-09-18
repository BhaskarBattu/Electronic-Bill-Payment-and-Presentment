package com.alacriti.ebpp.resteasy.resource;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.alacriti.ebpp.biz.delegate.CustomerDelegate;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.PayDetailsVO;
import com.alacriti.ebpp.util.SessionUtility;


@Path("/customer")
public class CustomerResource {
	public static final Logger log= Logger.getLogger(CustomerResource.class);
	
	@GET
	@Path("/invalidateSession")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean inValidateSessoin(@Context HttpServletRequest request)
	{
		SessionUtility sessionUtility=new SessionUtility();
		HttpSession session = request.getSession(false);
			if(session!=null)
					session.invalidate();
			//System.out.print(sessionUtility.checkForSession(session)+"gg");
		return sessionUtility.checkForSession(session);
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addCustomer(CustomerVO customer, @Context HttpServletRequest request) {
		log.debug("=========>> addUser method in UserResource class ::");
		boolean result=false;
		CustomerDelegate customerDelegate=null;
		try {
			customerDelegate=new CustomerDelegate();
			result =customerDelegate.addCustomer(customer);
				HttpSession session= request.getSession();
		} catch (Exception e) {
			log.error("Exception in addUser of UserResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	@POST
	@Path("/customerBills")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<BillsDetailsVO> getCustomerBills(BillsDetailsVO bills){
		log.debug("=========>> getCustomerBills method in CustomerResource class ::");
		ArrayList<BillsDetailsVO> billsList = null;
		CustomerDelegate customerDelegate=null;
		try {
			customerDelegate =new CustomerDelegate();
			billsList = customerDelegate.getCustomerBills(bills);
		} catch (Exception e) {
			log.error("Exception in getCustomerBills of CustomerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return billsList;
		
	}
	@POST
	@Path("/payBillThroughCard")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response payCustomerBill(PayDetailsVO payment){
		log.debug("=========>> getCustomerBills method in CustomerResource class ::");
		boolean result= false;
		CustomerDelegate customerDelegate=null;
		try {
			customerDelegate =new CustomerDelegate();
			result = customerDelegate.payCustomerBill(payment);
		} catch (Exception e) {
			log.error("Exception in getCustomerBills of CustomerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return Response.status(200).entity(result).build();
		
	}
	@POST
	@Path("/payBill")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response payCustomerBillOnline(PayDetailsVO payment){
		log.debug("=========>> getCustomerBills method in CustomerResource class ::");
		boolean result= false;
		CustomerDelegate customerDelegate=null;
		try {
			customerDelegate =new CustomerDelegate();
			result = customerDelegate.payCustomerBillOnline(payment);
		} catch (Exception e) {
			log.error("Exception in getCustomerBills of CustomerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return Response.status(200).entity(result).build();
		
	}
	
	@GET
	@Path("/getCustomerPreviousBills")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PayDetailsVO> getCustomerPaidBills(@QueryParam("email") String email){
		log.debug("=========>> getCustomerPaidBills method in CustomerResource class ::");
		ArrayList<PayDetailsVO> payBillsList = null;
		PayDetailsVO detail=new PayDetailsVO();
		detail.setEmail(email);
		CustomerDelegate customerDelegate=null;
		try {
			customerDelegate =new CustomerDelegate();
			payBillsList = customerDelegate.getCustomerPaidBills(detail);
		} catch (Exception e) {
			log.error("Exception in getCustomerPreviousBills of CustomerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return payBillsList;
		
	}
}
