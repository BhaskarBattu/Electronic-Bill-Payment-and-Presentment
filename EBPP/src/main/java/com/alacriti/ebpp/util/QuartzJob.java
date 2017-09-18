package com.alacriti.ebpp.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alacriti.ebpp.biz.delegate.BillerDelegate;
import com.alacriti.ebpp.model.vo.BillsDetailsVO;

public class QuartzJob implements Job{

	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		final Logger log= Logger.getLogger(QuartzJob.class);
		// TODO Auto-generated method stub
		System.out.println("Hello");
		System.out.println(new Date());
		BillsDetailsVO billsDetails = null;
		BillerDelegate billerDelegate=null;
		try {
			billerDelegate =new BillerDelegate();
			billerDelegate.sendBillsToCustomers(billsDetails);
		} catch (Exception e) {
			log.error("Exception in addCustomers of BillerResource : "+ e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
