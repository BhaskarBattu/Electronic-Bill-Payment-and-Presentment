package com.alacriti.ebpp.model.vo;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class Model {
	   private byte[] file;

	  
	   
	public byte[] getFile() {
		return file;
	}
	 @FormParam("file")
	   @PartType("application/octet-stream")
	public void setFile(byte[] file) {
		this.file = file;
	}
	   
	   
}
