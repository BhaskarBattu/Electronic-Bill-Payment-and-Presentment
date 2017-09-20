package com.alacriti.ebpp.resteasy.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.alacriti.ebpp.biz.delegate.BillerDelegate;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;
import com.alacriti.ebpp.model.vo.CustomerVO;
import com.alacriti.ebpp.model.vo.Model;
import com.alacriti.ebpp.model.vo.ViewCustomer;
import com.alacriti.ebpp.util.QuartzMain;

@Path("/biller")
public class BillerResource {
	public static final Logger log= Logger.getLogger(CustomerResource.class); 
		
	
	@POST
	@Path("/uploadCustomersList")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("multipart/form-data")
	public ArrayList<Integer> uploadCustomersList(@MultipartForm Model form) throws IOException {
		log.debug("=========>> uploadCustomersList method in BillerResource class ::");
		ArrayList<Integer> wrongLines= new ArrayList<Integer>(); 
        String fileLocation = "/home/bhaskararaob/Documents/wildfly-8.0.0.Final/csv";
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = fileLocation + "/" + df.format(new Date())+".csv";
       
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(form.getFile());
        fop.flush();
        fop.close();
        String line = "";
        Pattern pattern;
        int count=2;
        final String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        CustomerVO cust= new CustomerVO();
        ArrayList<CustomerVO> customer= new ArrayList<CustomerVO>();
        BillerDelegate billerDelegate=null;
        boolean result=false;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        while ((line = br.readLine()) != null) {
        	String[] country = line.split(",");
        	String email = country[1];
        	pattern = Pattern.compile(emailPattern);
          if(pattern.matcher(email).matches() == false){
        	wrongLines.add(count);
        	System.out.println(country[1]);
          }else{
        	  cust.setEmail(email);
        	  cust.setUsername(country[0]);
        	  customer.add(cust);
          }
          ++count;
         
         }
        try {
			billerDelegate =new BillerDelegate();
			result = billerDelegate.addCustomers(customer);
		} catch (Exception e) {
			log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
        
		return wrongLines;
	}
	
	@GET
	@Path("/getpaginationCountOfCustomerBills")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getpaginationCountOfCustomerBills(){
		log.debug("=========>> getpaginationCountOfCustomerBills method in BillerResource class ::");
		int count=0;
		BillerDelegate billerDelegate=null;
		try {
			billerDelegate =new BillerDelegate();
			count = billerDelegate.getpaginationCountOfCustomerBillsInfo();
		} catch (Exception e) {
			log.error("Exception in getpaginationCountOfCustomerBills of BillerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return Response.status(200).entity(count).build();
	}
	
	@GET
	@Path("/getpaginationCountOfCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getpaginationCountOfCustomers(){
		log.debug("=========>> getpaginationCountOfCustomers method in BillerResource class ::");
		int count=0;
		BillerDelegate billerDelegate=null;
		try {
			billerDelegate =new BillerDelegate();
			count = billerDelegate.getpaginationCountOfCustomersInfo();
		} catch (Exception e) {
			log.error("Exception in getpaginationCountOfCustomers of BillerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
		return Response.status(200).entity(count).build();
	}
	
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
		public List<ViewCustomer> getCustomerInfo(@QueryParam("start") int start, @QueryParam("end") int end){
			log.debug("=========>> getCustomerInfo method in BillerResource class ::");
			ArrayList<ViewCustomer> customersList = null;
			List<ViewCustomer> resultList=null;
			ViewCustomer customer= new ViewCustomer();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				resultList = billerDelegate.getCustomerInfo(customer,start,end);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return resultList;
		}
		
		@GET
		@Path("/getCustomersToView")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<ViewCustomer> getCustomerInfoToView(@QueryParam("start") int start, @QueryParam("end") int end){
			log.debug("=========>> getCustomerInfo method in BillerResource class ::");
			ArrayList<ViewCustomer> customersList = null;
			List<ViewCustomer> resultList=null;
			ViewCustomer customer= new ViewCustomer();
			BillerDelegate billerDelegate=null;
			try {
				billerDelegate =new BillerDelegate();
				resultList = billerDelegate.getCustomerInfoToView(customer,start,end);
			} catch (Exception e) {
				log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
				e.printStackTrace();
			}
			return resultList;
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
