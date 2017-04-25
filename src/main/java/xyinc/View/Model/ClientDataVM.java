package xyinc.View.Model;

import java.util.Date;
import xyinc.Entity.Client;

public class ClientDataVM {
	
	private long id;
	private String name;
	private String document;
	private Date createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date date) {
		this.createDate = date;
	}
	
	public static ClientDataVM convertFrom(Client client){
		
		ClientDataVM clientDataVM = new ClientDataVM();
		
		clientDataVM.setId(client.getId());
		clientDataVM.setName(client.getName());
		clientDataVM.setDocument(client.getDocument());
		clientDataVM.setCreateDate(client.getCreateDate());
		
		return clientDataVM;
		
	}

}
