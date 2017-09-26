package com.alacriti.ebpp.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import com.alacriti.ebpp.util.QuartzMain;

@WebListener
public class ApplicationStartUpListener implements ServletContextListener{
	public static final Logger log= Logger.getLogger(ApplicationStartUpListener.class);
	
	
	public void contextInitialized(ServletContextEvent event) {
        System.out.println("---- initialize servlet context -----");
        log.debug("=========>> contextInitialized method in ApplicationStartUpListener class ::");
        QuartzMain sendMail = new QuartzMain();
        try {
			sendMail.sendBillsToCustomers();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error("Exception in contextInitialized of ApplicationStartUpListener : "+ e.getMessage(), e);
			e.printStackTrace();
		}
      
    }

	
	    public void contextDestroyed(ServletContextEvent event) {
	        System.out.println("---- destroying servlet context -----");
	        // clean up resources
	    }
}
