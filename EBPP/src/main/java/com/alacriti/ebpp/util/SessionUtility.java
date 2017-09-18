package com.alacriti.ebpp.util;

import javax.servlet.http.HttpSession;

public class SessionUtility {
	public boolean checkForSession(HttpSession session)
	{
	// HttpSession session= request.getSession(false);
		//System.out.println("printing the status of session");
		//System.out.println(session);
		if(session==null)
		return false;
		else 
		return true;
	}
}