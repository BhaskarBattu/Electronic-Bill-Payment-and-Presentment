package com.alacriti.ebpp.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMain {
	/*public static void main(String[] args) throws SchedulerException {
		final Logger logging = Logger.getLogger(QuartzMain.class);
		 BasicConfigurator.configure(); 
		// TODO Auto-generated method stub
			JobDetail job=JobBuilder.newJob(QuartzJob.class).build();
			 //Trigger t1= TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
			Trigger t1= TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(
					CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
			Scheduler sc=StdSchedulerFactory.getDefaultScheduler();
			sc.start();
			sc.scheduleJob(job,t1);
			logging.error(sc);
			// every minute: 0 0/1 * 1/1 * ? *            0 0/3 * 1/1 * ? *
			// evry hour:0 0 0/1 1/1 * ? *
			 // every day : 0 0 12 1/1 * ? *
	}*/
	
    public void sendBillsToCustomers() throws SchedulerException {
			final Logger logging = Logger.getLogger(QuartzMain.class);
			 BasicConfigurator.configure(); 
			// TODO Auto-generated method stub
				JobDetail job=JobBuilder.newJob(QuartzJob.class).build();
				 //Trigger t1= TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
				Trigger t1= TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(
						CronScheduleBuilder.cronSchedule("0 0 0/5 1/1 * ? *")).build();
				Scheduler sc=StdSchedulerFactory.getDefaultScheduler();
				sc.start();
				sc.scheduleJob(job,t1);
				logging.error(sc);
    }
    
    
    
    
}
